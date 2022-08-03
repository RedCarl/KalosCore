package red.kalos.core.util.api;

import de.tr7zw.nbtapi.NBTItem;
import red.kalos.core.Main;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class CustomItem {
    public static ItemStack MaxLegend = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/78d62f18b744e7fbfd6f1f8dad17e0a2a0b608b1186f40cee7bee572117c7930",
            ColorParser.parse("&cKalos &f// &c顶级传奇宝可梦"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7使用该物品可以生成一只顶级传奇宝可梦。"),
            ColorParser.parse("&7请谨慎选择，手滑概不负责，使用后物品消失。")
    );
    public static ItemStack NickCard = ItemFactoryAPI.getItemStack(Material.NAME_TAG,
            ColorParser.parse("&6Kalos &f// &6改名卡"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7可以更改自己的名字，但无法更改登录的ID。"),
            ColorParser.parse("&7注意，登录和显示的名字不一样。")
    );




    //对物品进行加密
    public static ItemStack getEncryptionItem(ItemStack itemStack){
        NBTItem nbtItem = new NBTItem(itemStack);
        String id = PokemonAPI.getRandomString(16);
        nbtItem.setString("id", id);

        //储存文件
        File file = new File(Main.getInstance().getDataFolder(), "EncryptionItems/"+id+".kalos");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("kalos",true);
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nbtItem.getItem();
    }

    //对物品进行解密
    public static Boolean useEncryptionItem(Player player, ItemStack itemStack){
        NBTItem nbtItem = new NBTItem(itemStack);
        String id = nbtItem.getString("id");
        File file = new File(Main.getInstance().getDataFolder(), "EncryptionItems/"+id+".kalos");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        if (data.getBoolean("kalos")){
            data.set("kalos",false);
            player.getInventory().remove(itemStack);
            try {
                data.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        player.getInventory().remove(itemStack);
        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，你的这个物品无法使用，请联系管理员。"));
        Bukkit.broadcastMessage(ColorParser.parse("&7玩家 &c"+player.getName()+" &7使用了一个 &c违法 &7的物品。"));
        return false;
    }
}
