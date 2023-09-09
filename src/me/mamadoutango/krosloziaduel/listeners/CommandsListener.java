package me.mamadoutango.krosloziaduel.listeners;

import me.mamadoutango.krosloziaduel.manager.DuelDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandsListener implements Listener {

    @EventHandler
    public void onCommands(PlayerCommandPreprocessEvent e){
        if(DuelDataManager.getDuelEnCours().containsKey(e.getPlayer()) || DuelDataManager.getDuelEnCours().containsValue(e.getPlayer())){
            String[] commande = e.getMessage().split(" ");
            if(e.getMessage().equalsIgnoreCase("/spawn")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/tpa")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/tpaccept")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/tpdeny")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/tpahere")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/tpyes")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/tpno")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/home")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/e:home")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/ehome")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/ec")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/enderchest")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/kit")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/feed")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/repair")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/rtp")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            } else if(commande[0].equalsIgnoreCase("/duel")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " §cpendant le duel !");
            }
        }
    }
}
