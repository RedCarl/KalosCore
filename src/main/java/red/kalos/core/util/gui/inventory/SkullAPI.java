package red.kalos.core.util.gui.inventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class SkullAPI {
   private static Field profile_field;
   private static Base64.Encoder encoder = Base64.getEncoder();

   static{
      try {
         Class<? extends ItemMeta> class_to_nms = (new ItemStack(Material.SKULL_ITEM)).getItemMeta().getClass();
         profile_field = class_to_nms.getDeclaredField("profile");
         profile_field.setAccessible(true);
      } catch (SecurityException | NoSuchFieldException var5) {
         var5.printStackTrace();
      }
   }

   public static ItemStack getSkullItem(String url,String name,String... lores) {
      ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
      SkullMeta meta = (SkullMeta)is.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(Arrays.asList(lores));
      GameProfile profile = new GameProfile(UUID.randomUUID(), null);
      String before = "{\"textures\":{\"SKIN\":{\"url\":\"";
      String after = "\"}}}";
      url = before + ("http://textures.minecraft.net/texture/"+url) + after;
      profile.getProperties().put("textures", new Property("textures", encoder.encodeToString(url.getBytes())));
      try {
         profile_field.set(meta, profile);
      } catch (IllegalAccessException | IllegalArgumentException var4) {
         var4.printStackTrace();
      }
      is.setItemMeta(meta);
      return is;
   }

   public static ItemStack getPlayerSkull(String name, String... lores){
      return getSkullItem("e65ac43ca13db2703afcede4e8fdc9bf7d1c65d154cea8e195de59d05c73da82",name,lores);
   }
}
