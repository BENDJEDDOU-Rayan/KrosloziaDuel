package me.mamadoutango.krosloziaduel.manager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class StuffManager {

    private static HashMap<String, ItemStack[]> inventaireAvantDuel = new HashMap<>();
    private static HashMap<String, ItemStack[]> armureAvantDuel = new HashMap<>();
    private static HashMap<String, Integer> xpAvantDuel = new HashMap<>();

    private Player joueur;

    public StuffManager(Player joueur ){
        this.joueur = joueur;
    }

    public void saveInventory( ){
        inventaireAvantDuel.put(joueur.getName(), joueur.getInventory().getContents());
        armureAvantDuel.put(joueur.getName(), joueur.getInventory().getArmorContents());
        xpAvantDuel.put(joueur.getName(), joueur.getLevel());

    }

    public void clearArmor(){
        joueur.getInventory().setHelmet(null);
        joueur.getInventory().setChestplate(null);
        joueur.getInventory().setLeggings(null);
        joueur.getInventory().setBoots(null);
    }

    public void clearInventory( ){
        joueur.getInventory().clear();
        clearArmor();
    }

    public void restoreInventory(){
        joueur.getInventory().setContents(inventaireAvantDuel.get(joueur.getName()));
        joueur.getInventory().setArmorContents(armureAvantDuel.get(joueur.getName()));
        joueur.setLevel(xpAvantDuel.get(joueur.getName()));
    }

    public void giveStuffDuel(){
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        helmet.addEnchantment(Enchantment.DURABILITY, 3);

        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        chestplate.addEnchantment(Enchantment.DURABILITY, 3);

        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        leggings.addEnchantment(Enchantment.DURABILITY, 3);

        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        boots.addEnchantment(Enchantment.DURABILITY, 3);

        sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        sword.addEnchantment(Enchantment.DURABILITY, 3);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);

        joueur.getInventory().setItem(17, new ItemStack(373, 1, (short)8226));
        joueur.getInventory().setItem(26, new ItemStack(373, 1, (short)8226));
        joueur.getInventory().setItem(35, new ItemStack(373, 1, (short)8226));
        joueur.getInventory().setItem(2, new ItemStack(373, 1, (short)8226));
        joueur.getInventory().setItem(0, sword);
        joueur.getInventory().setItem(1, new ItemStack(Material.ENDER_PEARL, 16));
        joueur.getInventory().setItem(3, new ItemStack(373, 1, (short)8259));
        joueur.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 64));

        for(ItemStack potion : joueur.getInventory().getContents()){
            if(potion == null){
                joueur.getInventory().addItem(new ItemStack(373, 1, (short)16421));
            }
        }

        joueur.getInventory().setHelmet(helmet);
        joueur.getInventory().setChestplate(chestplate);
        joueur.getInventory().setLeggings(leggings);
        joueur.getInventory().setBoots(boots);

    }
}
