package kim.pokemon.kimexpand.kits;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.GlazedPayDataSQLReader;
import kim.pokemon.kimexpand.armourers.guis.ArmourersGUI;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.nametag.NameTag;
import kim.pokemon.kimexpand.playerinfo.PlayerInfo;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;

public class PlayerKits  extends InventoryGUI {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    public PlayerKits(Player player) {

        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 礼包系统"), player, 6);

        //内测礼包
        CMIUser cmiUser = CMI.getInstance().getPlayerManager().getUser(player);
        Button BateKit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/d6e12fdae1fceba6879f659796132a7ffa08cd92a26cb7a1067d49703d7b1b4b",
                        ColorParser.parse("&b内测礼包 &7// &fBate Kits"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7进服时间 &c2021年12月31日 &7之前,并当天累计游玩 &c20 &7分钟"),
                        ColorParser.parse("&7累计充值大于等于 &c6 &7满足所有条件即可领取."),
                        ColorParser.parse("&7该礼包请在 2022年1月5日 之前领取,逾期作废,无法补领."),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7礼包门槛:"),
                        ColorParser.parse("&r          &c"+ GlazedPayDataSQLReader.getPlayer(player.getName()).getAmount() +"&7/&f6.0 &f元 &7(累计充值)"),
                        ColorParser.parse("&r          &c"+ cmiUser.getTotalPlayTime()/1000/60 +"&7/&f20 &f分钟 &7(累计在线)"),
                        ColorParser.parse("&r          &c"+ simpleDateFormat.format(player.getFirstPlayed()) +" &7(进服日期)"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c内测 &9[先锋] &c头衔*1"),
                        ColorParser.parse("&r          &c幸运方块*16"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c普通球*64"),
                        ColorParser.parse("&r          &c卡洛币*2000"),
                        ColorParser.parse("&r          &c大师球*1"),
                        ColorParser.parse("&r          &c卡点*10"),
                        ColorParser.parse("&r          &c牧场*1")

                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (GlazedPayDataSQLReader.getPlayer(player.getName()).getAmount()>=6){
                            if (cmiUser.getTotalPlayTime()/1000/60>=20){
                                if (player.getFirstPlayed()<=1640880000000L){
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.tag.先锋 server="+ Main.luckPerms.getServerName());
                                    Main.econ.depositPlayer(player,2000);
                                    Main.ppAPI.giveAsync(player.getUniqueId(),10);
                                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),16));
                                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),32));
                                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),64));
                                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),1));
                                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH"),1));

                                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7非常感谢您对服务器的支持与陪伴，我们往后会做的更好！"));
                                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_YES,1,1);
                                    player.closeInventory();
                                }else {
                                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                                }
                            }else {
                                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                            }
                        }else {
                            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                        }

                    }
                });
        if (!player.hasPermission("kim.tag.先锋")){
            this.setButton(0, BateKit);
        }





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
