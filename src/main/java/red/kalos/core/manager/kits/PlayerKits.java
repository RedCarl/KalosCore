package red.kalos.core.manager.kits;

import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class PlayerKits  extends InventoryGUI {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    DecimalFormat decimalFormat = new DecimalFormat("#0.##");
    public PlayerKits(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 礼包系统"), player, 6);
        //皮卡丘月礼包
        this.setButton(0,new Button(
                SkullAPI.getSkullItem("a5c6944593820d13d7d47db2abcfcbf683bb74a07e1a982db9f32e0a8b5dc466",
                        ColorParser.parse("&e皮卡丘每月礼包 &7// &fPikachu Kits"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7给 &e皮卡丘 &7玩家准备的礼包，每个自然月可以领取一次。"),
                        ColorParser.parse("&7如果 &e皮卡丘 &7到期则无法继续领取，除非重新开通."),
                        ColorParser.parse("&7请在 &c菜单 &f> &c商城(绿宝石) &f> &c礼包商城 &7进行开通."),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7礼包门槛:"),
                        ColorParser.parse("&r          &7拥有 &e皮卡丘 &7会员头衔"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c糖果*"+10),
                        ColorParser.parse("&r          &c大师球*"+4),
                        ColorParser.parse("&r          &c先机球*"+32),
                        ColorParser.parse("&r          &c超级球*"+64),
                        ColorParser.parse("&r          &c高级球*"+32),
                        ColorParser.parse("&r          &c熟牛排*"+64*3),
                        ColorParser.parse("&r          &c卡洛币*"+15000),
                        ColorParser.parse("&r          &c幸运方块*"+10)
                ),
                type -> {
                    try {
                        if (KitsManager.isGetKits("皮卡丘月礼包",player)){
                            ////////////////////

                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),10));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),4));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_QUICK_BALL"),32));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),64));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),32));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.COOKED_BEEF,64*3));
                            Main.getEcon().depositPlayer(player,15000);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),10));

                            ////////////////////
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7非常感谢您对服务器的支持与陪伴，我们往后会做的更好！"));
                            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_YES,1,1);
                            player.closeInventory();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));

        //伊布月礼包
        this.setButton(1,new Button(
                SkullAPI.getSkullItem("80d8414cfcbbdfdb383a8b3f31d289146f38672d574e154296e110aad9e11428",
                        ColorParser.parse("&6伊布每月礼包 &7// &fEevee Kits"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7给 &6伊布 &7玩家准备的礼包，每个自然月可以领取一次。"),
                        ColorParser.parse("&7如果 &6伊布 &7到期则无法继续领取，除非重新开通."),
                        ColorParser.parse("&7请在 &c菜单 &f> &c商城(绿宝石) &f> &c礼包商城 &7进行开通."),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7礼包门槛:"),
                        ColorParser.parse("&r          &7拥有 &6伊布 &7会员头衔"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c糖果*"+32),
                        ColorParser.parse("&r          &c大师球*"+12),
                        ColorParser.parse("&r          &c先机球*"+64*2),
                        ColorParser.parse("&r          &c超级球*"+64*3),
                        ColorParser.parse("&r          &c高级球*"+64*2),
                        ColorParser.parse("&r          &c熟牛排*"+64*6),
                        ColorParser.parse("&r          &c卡洛币*"+50000),
                        ColorParser.parse("&r          &c幸运方块*"+32)
                ),
                type -> {
                    try {
                        if (KitsManager.isGetKits("伊布月礼包",player)){
                            ////////////////////

                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),32));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),12));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_QUICK_BALL"),64*2));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),64*3));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64*2));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.COOKED_BEEF,64*6));
                            Main.getEcon().depositPlayer(player,50000);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),32));

                            ////////////////////
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7非常感谢您对服务器的支持与陪伴，我们往后会做的更好！"));
                            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_YES,1,1);
                            player.closeInventory();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));

        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }

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
