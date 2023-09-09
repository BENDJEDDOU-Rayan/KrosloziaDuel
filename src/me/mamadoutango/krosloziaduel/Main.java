package me.mamadoutango.krosloziaduel;

import me.mamadoutango.krosloziaduel.commandes.Duel;
import me.mamadoutango.krosloziaduel.listeners.CommandsListener;
import me.mamadoutango.krosloziaduel.listeners.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Main extends JavaPlugin {

    private static File fichierLog;

    @Override
    public void onEnable() {
        fichierLog = new File(this.getDataFolder() + File.separator + "log_duel.txt");
        getCommand("duel").setExecutor(new Duel());
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new CommandsListener(), this);
        System.out.println("Plugin de duel en route !");
    }

    @Override
    public void onDisable() {

        System.out.println("Arrêt du plugin duel..");
    }

    public static void logMessage(String message) {
        try {
            FileWriter writer = new FileWriter(fichierLog, true); // true pour ajouter au fichier existant
            writer.write(message + System.lineSeparator()); // Ajoute une nouvelle ligne à la fin
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
