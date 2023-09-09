package me.mamadoutango.krosloziaduel.commandes;

import me.mamadoutango.krosloziaduel.manager.DuelDataManager;
import me.mamadoutango.krosloziaduel.manager.StuffManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class Duel implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (msg.equalsIgnoreCase("duel") && sender instanceof Player) {

            Player joueur = (Player) sender;

            StuffManager joueurStuff = new StuffManager(joueur);

            if (args.length == 0) {
                joueur.sendMessage("§e§m§l----------§6§l<Aide duel>§e§m§l----------");
                joueur.sendMessage("§e/duel §6<joueur> §eenvoyer une demande de duel à un joueur.");
                joueur.sendMessage("§e/duel §6<accept/deny> §eaccepter/refuser une demande de duel.");
                return true;
            }

            if (args.length == 1) {

                String pseudoAdversaire = args[0];

                if(args[0].equalsIgnoreCase("hashduel")){
                    joueur.sendMessage("Taille de la HashMap DuelEnCours : " + DuelDataManager.getDuelEnCours().size());
                    return true;
                }

                if(args[0].equalsIgnoreCase("hashattente")){
                    joueur.sendMessage("Taille de la HashMap DuelEnAttente : " + DuelDataManager.getDuelEnAttente().size());
                    return true;
                }

                if(args[0].equalsIgnoreCase("accept")){
                    if(DuelDataManager.getDuelEnAttente().containsKey(joueur)){
                        Player adversaire = DuelDataManager.getDuelEnAttente().get(joueur);
                        StuffManager adversaireStuff = new StuffManager(adversaire);
                        DuelDataManager.addPlayerInDuelEnCours(joueur, adversaire);
                        try{
                            DuelDataManager.removePlayerInDuelEnAttente(joueur);
                            DuelDataManager.removePlayerInDuelEnAttente(adversaire);
                            DuelDataManager.addPlayerLastLocation(joueur);
                            DuelDataManager.addPlayerLastLocation(adversaire);
                            DuelDataManager.addHiddenPlayer(joueur);
                            DuelDataManager.addHiddenPlayer(adversaire);
                            Iterator<Player> iterateur = DuelDataManager.getHiddenPlayer().iterator();
                            while(iterateur.hasNext()){
                                Player joueurCacher = iterateur.next();
                                if (joueurCacher != joueur && joueurCacher != adversaire){
                                    adversaire.hidePlayer(joueurCacher);
                                    joueur.hidePlayer(joueurCacher);
                                }
                            }
                        } catch(NullPointerException e){
                            return true;
                        }

                        // notification acceptation du duel
                        adversaire.sendMessage("§e" + joueur.getName() + " §aviens d'accepter la demande de duel.");
                        // téléportation des combattants
                        joueur.teleport(new Location(Bukkit.getWorld("world"), 97, 67, 400, 0, 0));
                        adversaire.teleport(new Location(Bukkit.getWorld("world"), 97, 67, 428, 180, 0));
                        // sauvegarde inventaire + give stuff du joueur
                        joueurStuff.saveInventory();
                        joueurStuff.clearInventory();
                        joueurStuff.giveStuffDuel();
                        // sauvegarde inventaire + give stuff adversaire
                        adversaireStuff.saveInventory();
                        adversaireStuff.clearInventory();
                        adversaireStuff.giveStuffDuel();
                        // Message aux combattants
                        joueur.sendMessage("§aLe combat commence !");
                        adversaire.sendMessage("§aLe combat commence !");
                        return true;
                    } else {
                        joueur.sendMessage("§cVous n'avez pas de demande de duel en attente.");
                        return true;
                    }
                    
                } else if (args[0].equalsIgnoreCase("deny")) {
                    if(DuelDataManager.getDuelEnAttente().containsKey(joueur)){
                        Player adversaire = DuelDataManager.getDuelEnAttente().get(joueur);
                        joueur.sendMessage("§aVous venez de refusé le duel.");
                        adversaire.sendMessage("§e" + joueur.getName() + " §cviens de refusé le duel.");
                        DuelDataManager.removePlayerInDuelEnAttente(joueur);
                    } else {
                        joueur.sendMessage("§cVous n'avez pas de demande de duel en attente.");
                        return true;
                    }
                }

                if (Bukkit.getPlayer(pseudoAdversaire) != null) {
                    Player adversaire = Bukkit.getPlayer(pseudoAdversaire);

                    if (adversaire.getName().equalsIgnoreCase(joueur.getName())) {
                        joueur.sendMessage("§eKroslozia §7» §cVous ne pouvez pas demander vous-même en duel.");
                        return true;
                    }

                    if(DuelDataManager.getDuelEnAttente().containsKey(adversaire)){
                        joueur.sendMessage("§eKroslozia §7» §cCe joueur a déjà une demande de duel en attente");
                        return true;
                    }

                    if(DuelDataManager.getDuelEnCours().containsKey(adversaire) || DuelDataManager.getDuelEnCours().containsValue(adversaire)){
                        joueur.sendMessage("§eKroslozia §7» §cCe joueur est actuellement en duel.");
                        return true;
                    }

                    DuelDataManager.addPlayerInDuelEnAttente(joueur, adversaire);
                    joueur.sendMessage("§aVous demander §e" + adversaire.getName() + " §aen duel.");
                    adversaire.sendMessage("§e" + joueur.getName() + " §avous propose un duel.");
                    adversaire.sendMessage("§aTapez §e/duel <accept/deny> §apour accepter ou refuser. ");
                    return true;
                } else {
                    joueur.sendMessage("§eKroslozia §7» §e" + pseudoAdversaire + " §cn'est pas connecté !");
                }
            }
        }
        return false;
    }
}
