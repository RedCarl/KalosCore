package red.kalos.core.util.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemFacAPI {
   public static ItemStack getItemStack(Material material) {
      ItemStack is = new ItemStack(material);
      return is;
   }

   public static ItemStack getItemStack(Material material, String name) {
      ItemStack is = new ItemStack(material);
      ItemMeta im = Bukkit.getItemFactory().getItemMeta(material);
      im.setDisplayName(name);
      is.setItemMeta(im);
      return is;
   }

   public static ItemStack getItemStack(Material material, String name, String... lores) {
      ItemStack is = new ItemStack(material);

      ItemMeta im = Bukkit.getItemFactory().getItemMeta(material);
      im.setDisplayName(name);
      im.setLore(Arrays.asList(lores));
      is.setItemMeta(im);
      return is;
   }

   public static ItemStack getItemStackWithDurability(Material material, short d){
      ItemStack is = new ItemStack(material);
      is.setDurability(d);
      return is;
   }

   public static ItemStack getItemStackWithDurability(Material material, int amount){
      ItemStack is = new ItemStack(material);
      is.setAmount(amount);
      return is;
   }

   public static ItemStack getItemStackWithDurability(Material material, short d, String name) {
      ItemStack is = new ItemStack(material);
      is.setDurability(d);
      ItemMeta im = Bukkit.getItemFactory().getItemMeta(material);
      im.setDisplayName(name);
      is.setItemMeta(im);
      return is;
   }

   public static ItemStack getItemStackWithDurability(Material material, short d, String name, String... lores) {
      ItemStack is = new ItemStack(material);
      is.setDurability(d);
      ItemMeta im = Bukkit.getItemFactory().getItemMeta(material);
      im.setDisplayName(name);
      im.setLore(Arrays.asList(lores));
      is.setItemMeta(im);
      return is;
   }
}
