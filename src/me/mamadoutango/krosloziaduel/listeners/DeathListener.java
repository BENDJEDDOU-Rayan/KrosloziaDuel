package me.mamadoutango.krosloziaduel.listeners;

import me.mamadoutango.krosloziaduel.manager.DuelDataManager;
import me.mamadoutango.krosloziaduel.Main;
import me.mamadoutango.krosloziaduel.manager.StuffManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;

public class DeathListener implements Listener {

    private final Main plugin;

    public DeathListener(Main plugin) {
        this.plugin = plugin;
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private String dateHeureFormatee;

    @EventHandler
    public void onDuelDeath(PlayerDeathEvent e) {
        Player joueur = e.getEntity();
        StuffManager looserStuff = new StuffManager(joueur);
        Player adversaire = joueur.getKiller();
        StuffManager stuffDuWinner = new StuffManager(adversaire);
        if (DuelDataManager.getDuelEnCours().containsKey(joueur) || DuelDataManager.getDuelEnCours().containsValue(joueur)) {
            if(joueur.getKiller() != null){
                e.getDrops().clear();
                BukkitScheduler scheduler = Bukkit.getScheduler();
                scheduler.runTask(plugin, () -> {
                    // Broadcast d'un message de victoire
                    Bukkit.broadcastMessage("§c✕ §e" + adversaire.getName() + " §7viens de battre §e" + joueur.getName() + " §7en duel !");
                    // Respawn de la victime instant + tp joueur/adversaire à dernière location
                    joueur.spigot().respawn();
                    joueur.teleport(DuelDataManager.getDerniereLoc().get(joueur));
                    adversaire.teleport(DuelDataManager.getDerniereLoc().get(adversaire));
                    // Suppression dernière location du joueur/adversaire
                    DuelDataManager.removePlayerLastLocation(joueur);
                    DuelDataManager.removePlayerLastLocation(adversaire);
                    // Restauration des items, armures et xp
                    looserStuff.restoreInventory();
                    stuffDuWinner.clearInventory();
                    stuffDuWinner.restoreInventory();
                    // Message aux duelistes
                    joueur.sendMessage("§cTu as perdu le duel ;(");
                    adversaire.sendMessage("§aBravo tu as gagné le duel !");
                    // LOG DU DUEL
                    LocalDateTime heureActuelle = LocalDateTime.now();
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
                    dateHeureFormatee = heureActuelle.format(formatter);
                    String message = "[" + dateHeureFormatee + "] " + adversaire.getName() + " vient de battre " + joueur.getName() + " en duel !";
                    Main.logMessage(message);
                    // Rendre visible les joueurs
                    for(int i = 0; i < DuelDataManager.getHiddenPlayer().size(); ++i){
                        if(DuelDataManager.getHiddenPlayer().get(i) != joueur && DuelDataManager.getHiddenPlayer().get(i) != adversaire){
                            joueur.showPlayer(DuelDataManager.getHiddenPlayer().get(i));
                            adversaire.showPlayer(DuelDataManager.getHiddenPlayer().get(i));
                        }
                    }
                    // Suppresion du joueur de la HashMap PlayerInDuelEnCours
                    try{
                        DuelDataManager.removePlayerInDuelEnCours(joueur);
                        DuelDataManager.removePlayerInDuelEnCours(adversaire);
                        DuelDataManager.removeHiddenPlayer(joueur);
                        DuelDataManager.removeHiddenPlayer(adversaire);
                    } catch(NullPointerException erreur) {
                        System.out.println("Erreur dans DeathLister.java - try & catch ligne 64");
                        erreur.printStackTrace();
                    }
                });
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player joueur = e.getPlayer();
        StuffManager looserStuff = new StuffManager(joueur);
        if(DuelDataManager.getDuelEnCours().containsKey(joueur)){
            Player adversaire = DuelDataManager.getDuelEnCours().get(joueur);
            StuffManager stuffDuWinner = new StuffManager(adversaire);
            joueur.teleport(DuelDataManager.getDerniereLoc().get(joueur));
            adversaire.teleport(DuelDataManager.getDerniereLoc().get(adversaire));
            // Suppression dernière location du joueur/adversaire
            DuelDataManager.removePlayerLastLocation(joueur);
            DuelDataManager.removePlayerLastLocation(adversaire);
            // Restauration des items, armures et xp
            looserStuff.clearInventory();
            looserStuff.restoreInventory();
            stuffDuWinner.clearInventory();
            stuffDuWinner.restoreInventory();
            // Message à l'adversaire
            adversaire.sendMessage("§4✕ §c" + joueur.getName() + " §eviens de se déconnecter. Duel annulé !");
            // LOG DU DUEL
            LocalDateTime heureActuelle = LocalDateTime.now();
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            dateHeureFormatee = heureActuelle.format(formatter);
            String message = "[" + dateHeureFormatee + "] " + joueur.getName() + " se déconnecte en plein duel face à " + adversaire.getName() + " !";
            Main.logMessage(message);
            // Rendre visible les joueurs
            for(int i = 0; i < DuelDataManager.getHiddenPlayer().size(); ++i){
                if(DuelDataManager.getHiddenPlayer().get(i) != joueur && DuelDataManager.getHiddenPlayer().get(i) != adversaire){
                    joueur.showPlayer(DuelDataManager.getHiddenPlayer().get(i));
                    adversaire.showPlayer(DuelDataManager.getHiddenPlayer().get(i));
                }
            }
            /*Iterator<Player> iterateur = DuelDataManager.getHiddenPlayer().iterator();
            while(iterateur.hasNext()){
                Player joueurCacher = iterateur.next();
                if (joueurCacher != joueur && joueurCacher != adversaire){
                    joueur.showPlayer(joueurCacher);
                    adversaire.showPlayer(joueurCacher);
                }
            }*/
            // Suppresion du joueur de la HashMap PlayerInDuelEnCours
            DuelDataManager.removeHiddenPlayer(joueur);
            DuelDataManager.removeHiddenPlayer(adversaire);
            DuelDataManager.removePlayerInDuelEnCours(joueur);
        } else if(DuelDataManager.getDuelEnCours().containsValue(joueur)) {
            for(Entry<Player, Player> entry : DuelDataManager.getDuelEnCours().entrySet()){
                if(entry.getValue() == joueur){
                    Player adversaire = entry.getKey();
                    StuffManager stuffDuWinner = new StuffManager(adversaire);
                    joueur.teleport(DuelDataManager.getDerniereLoc().get(joueur));
                    adversaire.teleport(DuelDataManager.getDerniereLoc().get(adversaire));
                    // Suppression dernière location du joueur/adversaire
                    DuelDataManager.removePlayerLastLocation(joueur);
                    DuelDataManager.removePlayerLastLocation(adversaire);
                    // Restauration des items, armures et xp
                    looserStuff.clearInventory();
                    looserStuff.restoreInventory();
                    stuffDuWinner.clearInventory();
                    stuffDuWinner.restoreInventory();
                    // Message à l'adversaire
                    adversaire.sendMessage("§4✕ §c" + joueur.getName() + " §eviens de se déconnecter. Duel annulé !");
                    // LOG DU DUEL
                    LocalDateTime heureActuelle = LocalDateTime.now();
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
                    dateHeureFormatee = heureActuelle.format(formatter);
                    String message = "[" + dateHeureFormatee + "] " + joueur.getName() + " se déconnecte en plein duel face à " + adversaire.getName() + " !";
                    Main.logMessage(message);
                    // Rendre visible les joueurs
                    for(int i = 0; i < DuelDataManager.getHiddenPlayer().size(); ++i){
                        if(DuelDataManager.getHiddenPlayer().get(i) != joueur && DuelDataManager.getHiddenPlayer().get(i) != adversaire){
                            joueur.showPlayer(DuelDataManager.getHiddenPlayer().get(i));
                            adversaire.showPlayer(DuelDataManager.getHiddenPlayer().get(i));
                        }
                    }
                    /*Iterator<Player> iterateur = DuelDataManager.getHiddenPlayer().iterator();
                    while(iterateur.hasNext()){
                        Player joueurCacher = iterateur.next();
                        if (joueurCacher != joueur && joueurCacher != adversaire){
                            joueur.showPlayer(joueurCacher);
                            adversaire.showPlayer(joueurCacher);
                        }
                    }*/
                    // Suppresion du joueur de la HashMap PlayerInDuelEnCours
                    DuelDataManager.removeHiddenPlayer(joueur);
                    DuelDataManager.removeHiddenPlayer(adversaire);
                    DuelDataManager.removePlayerInDuelEnCours(adversaire);
                    break;
                }
            }
        }
    }
}
