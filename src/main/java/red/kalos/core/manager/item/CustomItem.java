package red.kalos.core.manager.item;

import de.tr7zw.nbtapi.NBTItem;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
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
    public static ItemStack MaxLegend = SkullAPI.getSkullItem("c285dd77c64e2368fe77c31ff7c3d42b700fb7b74f2b463e696916c90f5d27ab",
            ColorParser.parse("&cKalos &f// &b顶级传奇宝可梦"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7使用该物品可以 &a自选生成 &7一只顶级传奇宝可梦。"),
            ColorParser.parse("&7请谨慎选择，手滑概不负责，使用后物品消失。")
    );
    public static ItemStack Pokemon = SkullAPI.getSkullItem("56d7fdb50f14c731c727b0e0d189b6a874319fc0d79c8a099acfc77c7b2d9196",
            ColorParser.parse("&cKalos &f// &e宝可梦自选包"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7使用该物品可以生成一只指定范围的宝可梦。"),
            ColorParser.parse("&7请谨慎选择，手滑概不负责，使用后物品消失。")
    );

    public static ItemStack RandomLegend = SkullAPI.getSkullItem("6f9020c07d875bad1440337adb55a08c15db06b994646a691795f4cd293fe3de",
            ColorParser.parse("&cKalos &f// &b传奇宝可梦"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7使用该物品可以 &a随机生成 &7一只顶级传奇宝可梦。"),
            ColorParser.parse("&7不会生成 &c可以跑图获得(三鸟) &7的传奇宝可梦。"),
            ColorParser.parse("&7请谨慎选择，手滑概不负责，使用后物品消失。")
    );
    public static ItemStack RandomEvolution = SkullAPI.getSkullItem("92defbe3cde326d4511bb53339d777afa703f3ec4daa697d61a4402744cbb0cd",
            ColorParser.parse("&cKalos &f// &b宝可梦进化石"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7使用该物品可以 &a随机生成 &7一个宝可梦进化石。"),
            ColorParser.parse("&7请谨慎选择，手滑概不负责，使用后物品消失。")
    );

    public static ItemStack NickCard = ItemFactoryAPI.getItemStack(Material.NAME_TAG,
            ColorParser.parse("&6Kalos &f// &6改名卡"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7可以更改自己的名字，但无法更改登录的ID。"),
            ColorParser.parse("&7注意，登录和显示的名字不一样。")
    );

    public static ItemStack IVsScroll = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HP_UP"),
            ColorParser.parse("&6Kalos &f// &6个体卷轴"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7能够更改背包中一个宝可梦的某个个体值。")
    );

    public static ItemStack PlotAdder = ItemFactoryAPI.getItemStack(Material.DIAMOND_HOE,
            ColorParser.parse("&6Kalos &f// &e地皮扩充"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7您的地皮资格将会 &a+1 &7拥有更多的地皮。")
    );

    public static ItemStack PikachuKit = SkullAPI.getSkullItem("a5c6944593820d13d7d47db2abcfcbf683bb74a07e1a982db9f32e0a8b5dc466",
            ColorParser.parse("&eKalos &f// &e皮卡丘礼包"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7使用物品可以获得 &e皮卡丘 &7礼包。"),
            ColorParser.parse("&7请注意背包空位，礼包内容很多，尽量清空背包。")
    );

    public static ItemStack EeveeKit = SkullAPI.getSkullItem("80d8414cfcbbdfdb383a8b3f31d289146f38672d574e154296e110aad9e11428",
            ColorParser.parse("&6Kalos &f// &6伊布礼包"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7使用物品可以获得 &6伊布 &7礼包。"),
            ColorParser.parse("&7请注意背包空位，礼包内容很多，尽量清空背包。")
    );

    public static ItemStack Star = SkullAPI.getSkullItem("7f9bc035cdc80f1ab5e1198f29f3ad3fdd2b42d9a69aeb64de990681800b98dc",
            ColorParser.parse("&cKalos &f// &c国庆限定宝箱"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7需要花费 &c1 &7卡点来开启这个宝箱。"),
            ColorParser.parse("&7请谨慎选择，手滑概不负责，使用后物品消失。")
    );

    public static ItemStack SignCard = ItemFactoryAPI.getItemStack(Material.NAME_TAG,
            ColorParser.parse("&3Kalos &f// &3补签卡"),
            ColorParser.parse("&8消耗品"),
            ColorParser.parse(""),
            ColorParser.parse("&7您可以使用此补签卡在未签到的日期补签."),
            ColorParser.parse(""),
            ColorParser.parse("&f使用方法:"),
            ColorParser.parse("&7将这个物品放入您的物品栏种,点击您以前"),
            ColorParser.parse("&7未曾签到的日期,即可完成补签成功.")
    );

    public static ItemStack Armourers = ItemFactoryAPI.getItemStack(Material.PAPER,
            ColorParser.parse("&c"+ Data.SERVER_NAME_CN+"の时装碎片"),
            ColorParser.parse("&f范围: &a1~10 &f个"),
            ColorParser.parse("&r"),
            ColorParser.parse("&r  &e■ &7售 价:"),
            ColorParser.parse("&r      &7(左键) &c" + -1 + " &7"+Data.SERVER_VAULT),
            ColorParser.parse("&r      &7(右键) &c" + 3 + " &7"+Data.SERVER_POINTS),
            ColorParser.parse("&r"),
            ColorParser.parse("&7&o时装碎片可以去兑换时装，需要非常多哦!"));

    //对物品进行加密
    public static ItemStack getEncryptionItem(ItemStack itemStack,String type){
        NBTItem nbtItem = new NBTItem(itemStack);
        String id = PokemonAPI.getRandomString(16);
        nbtItem.setString("id", id);

        //储存文件
        File file = new File(Main.getInstance().getDataFolder(), "EncryptionItems/"+id+".kalos");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("kalos",true);
        data.set("type",type);
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nbtItem.getItem();
    }

    //对物品进行解密
    public static String useEncryptionItem(Player player, ItemStack itemStack){
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
            return data.getString("type");
        }
//        player.getInventory().remove(itemStack);
//        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，你的这个物品无法使用，请联系管理员。"));
//        Bukkit.broadcastMessage(ColorParser.parse("&7玩家 &c"+player.getName()+" &7使用了一个 &c违法 &7的物品。"));
        return null;
    }

}
