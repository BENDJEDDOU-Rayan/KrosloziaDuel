package me.mamadoutango.krosloziaduel.listeners;

import me.mamadoutango.krosloziaduel.manager.DuelDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandsListener implements Listener {

    @EventHandler
    public void onCommands(PlayerCommandPreprocessEvent e){
        if(DuelDataManager.getDuelEnCours().containsKey(e.getPlayer()) || DuelDataManager.getDuelEnCours().containsValue(e.getPlayer())){
            if(e.getMessage().equalsIgnoreCase("/spawn")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/tpa")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/tpaccept")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/tpdeny")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/tpahere")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/tpyes")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/tpno")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/home")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/e:home")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/ehome")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/ec")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/enderchest")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/kit")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/feed")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/repair")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/rtp")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            } else if(e.getMessage().equalsIgnoreCase("/duel")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§eKroslozia §7» §cVous ne pouvez pas exécuté la commande §e" + e.getMessage() + " &cpendant le duel !");
            }
        }
    }
}
