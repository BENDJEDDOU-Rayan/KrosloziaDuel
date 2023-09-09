package me.mamadoutango.krosloziaduel.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;

public class DuelDataManager {

    private static final HashMap<Player, Player> DuelEnAttente = new HashMap<>();
    private static final ArrayList<Player> hiddenPlayer = new ArrayList<>();
    private static final HashMap<Player, Player> DuelEnCours = new HashMap<>();
    private static final HashMap<Player, Integer> tabVictoire = new HashMap<>();
    private static final HashMap<Player, Integer> tabDefaite = new HashMap<>();
    private static final HashMap<Player, Double> KillDeathRatio = new HashMap<>();
    private static final HashMap<Player, Location> derniereLoc = new HashMap<>();
    public static HashMap<Player, Player> getDuelEnAttente() {
        return DuelEnAttente;
    }

    public static HashMap<Player, Player> getDuelEnCours() {
        return DuelEnCours;
    }

    public static ArrayList<Player> getHiddenPlayer (){
        return hiddenPlayer;
    }

    public static HashMap<Player, Location> getDerniereLoc() {
        return derniereLoc;
    }

    public static HashMap<Player, Integer> getTabVictoire(){
        return tabVictoire;
    }

    public static HashMap<Player, Integer> getTabDefaite(){
        return tabDefaite;
    }

    public static void addHiddenPlayer(Player joueur){
        hiddenPlayer.add(joueur);
    }
    public static void addPlayerInDuelEnCours(Player joueur, Player adversaire){
        DuelEnCours.put(adversaire, joueur);
    }

    public static void addPlayerInDuelEnAttente(Player joueur, Player adversaire){
        DuelEnAttente.put(adversaire, joueur);
    }

    public static void addPlayerLastLocation(Player joueur){
        derniereLoc.put(joueur, joueur.getLocation());
    }

    public static void removePlayerInDuelEnCours(Player joueur){
        DuelEnCours.remove(joueur);
    }

    public static void removePlayerInDuelEnAttente(Player joueur) {
        DuelEnAttente.remove(joueur);
    }

    public static void removePlayerLastLocation(Player joueur){
        derniereLoc.remove(joueur);
    }
    public static void removeHiddenPlayer(Player joueur){
         for(int i = 0; i < hiddenPlayer.size(); ++i){
             if(hiddenPlayer.get(i) == joueur){
                 hiddenPlayer.remove(i);
             }
         }
    }

    public static void emptyTabVictoire(){
        tabVictoire.clear();
    }

    public static void emptyTabDefaite(){
        tabDefaite.clear();
    }

    public static void emptyDuelEnCours() {
        DuelEnCours.clear();
    }
    public static void emptyDuelEnAttente() {
        DuelEnAttente.clear();
    }

    public static void emptyHiddenplayer() {
        hiddenPlayer.clear();
    }
}
