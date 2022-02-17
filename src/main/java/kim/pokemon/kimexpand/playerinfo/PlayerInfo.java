package kim.pokemon.kimexpand.playerinfo;

import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.armourers.guis.ArmourersGUI;
import kim.pokemon.kimexpand.kits.PlayerKits;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.nametag.NameTag;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerInfo extends InventoryGUI {
    public PlayerInfo(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 玩家信息"), player, 6);




        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }

        //个人信息
        ItemStack PlayerInfo = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/f4885633f0c91a6fe8652fff2414198f69a943f38b648e4739880a1703b24ce1",
                ColorParser.parse("&e个人信息"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o查看您的各种数据统计信息."));
        Button PlayerInfoButton = new Button(PlayerInfo, type -> {
            if (type.isLeftClick()) {
                PlayerInfo playerInfo = new PlayerInfo(player);
                playerInfo.openInventory();
            }
        });
        this.setButton(45, PlayerInfoButton);

        //头衔系统
        ItemStack NameTag = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e333e741c7182f28f43e1804c2bb0f8d79a73d747c8265297f5ed0e5d24e35e8",
                ColorParser.parse("&6头衔仓库"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o管理您的头衔，装配或者取下."));
        Button NameTagButton = new Button(NameTag, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.nametag.NameTag nameTag = new NameTag(player);
                nameTag.openInventory();
            }
        });
        this.setButton(46, NameTagButton);

        //礼包系统
        ItemStack Kits = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e0a65a188e23457fab9ae73427ab5dde73fcb79ac59257d3509ebd9c386a8eab",
                ColorParser.parse("&b礼包系统"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里有各种礼包可以供您领取."));
        Button KitsButton = new Button(Kits, type -> {
            if (type.isLeftClick()) {
                PlayerKits playerKits = new PlayerKits(player);
                playerKits.openInventory();
            }
        });
        this.setButton(47, KitsButton);

        //时装系统
        ItemStack Armourers = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/911db4b3eefb72e661c9a5388753a2cee5e6453582641a1f0b866eb4af5ab22c",
                ColorParser.parse("&d时装仓库"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里储存着您拥有的时装，随时都可以穿戴."));
        Button ArmourersButton = new Button(Armourers, type -> {
            if (type.isLeftClick()) {
                ArmourersGUI armourersGUI = new ArmourersGUI(player);
                armourersGUI.openInventory();
            }
        });
        this.setButton(48, ArmourersButton);

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至主菜单."));
        Button CloseButton = new Button(Close, type -> {
            if (type.isLeftClick()) {
                MainMenu mainMenu = new MainMenu(player);
                mainMenu.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }
}
