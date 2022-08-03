package kim.pokemon.manager.kits;

import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.PlayerDataManager;
import kim.pokemon.manager.menu.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
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

        //读取配置文件
        File file = new File(Main.getInstance().getDataFolder(), "Kits/"+player.getUniqueId()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);

        //内测礼包
        this.setButton(0,new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/d6e12fdae1fceba6879f659796132a7ffa08cd92a26cb7a1067d49703d7b1b4b",
                        ColorParser.parse("&b内测礼包 &7// &fBate Kits"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7进服时间 &c2021年12月31日 &7之前,并当天累计游玩 &c20 &7分钟"),
                        ColorParser.parse("&7累计充值大于等于 &c6 &7满足所有条件即可领取."),
                        ColorParser.parse("&7该礼包请在 2022年1月5日 之前领取,逾期作废,无法补领."),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7礼包门槛:"),
                        ColorParser.parse("&r          &c"+ PlayerDataManager.getPlayerData(player.getUniqueId()).getRecharge() +"&7/&f6.0 &f元 &7(累计充值)"),
                        ColorParser.parse("&r          &c"+ PlayerDataManager.getPlayerData(player.getUniqueId()).getPlayTime()/60 +"&7/&f1200 &f分钟 &7(累计在线)"),
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
                    try {
                        if (KitsManager.isGetKits("内测礼包",player)){
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
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));

        //皮卡丘月礼包
        this.setButton(1,new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/a5c6944593820d13d7d47db2abcfcbf683bb74a07e1a982db9f32e0a8b5dc466",
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
                        ColorParser.parse("&r          &c糖果*"+64),
                        ColorParser.parse("&r          &c大师球*"+10),
                        ColorParser.parse("&r          &c先机球*"+64*2),
                        ColorParser.parse("&r          &c超级球*"+64*5),
                        ColorParser.parse("&r          &c高级球*"+64*4),
                        ColorParser.parse("&r          &c熟牛排*"+64*3),
                        ColorParser.parse("&r          &c卡洛币*"+20000),
                        ColorParser.parse("&r          &c幸运方块*"+16)
                ),
                type -> {
                    try {
                        if (KitsManager.isGetKits("皮卡丘月礼包",player)){
                            ////////////////////

                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),64));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),10));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_QUICK_BALL"),64*2));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),64*5));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64*4));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.COOKED_BEEF,64*3));
                            Main.econ.depositPlayer(player,20000);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),16));

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
        this.setButton(2,new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/80d8414cfcbbdfdb383a8b3f31d289146f38672d574e154296e110aad9e11428",
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
                        ColorParser.parse("&r          &c糖果*"+64*3),
                        ColorParser.parse("&r          &c大师球*"+24),
                        ColorParser.parse("&r          &c先机球*"+64*4),
                        ColorParser.parse("&r          &c超级球*"+64*6),
                        ColorParser.parse("&r          &c高级球*"+64*5),
                        ColorParser.parse("&r          &c熟牛排*"+64*6),
                        ColorParser.parse("&r          &c卡洛币*"+40000),
                        ColorParser.parse("&r          &c幸运方块*"+32)
                ),
                type -> {
                    try {
                        if (KitsManager.isGetKits("伊布月礼包",player)){
                            ////////////////////

                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),64*3));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),24));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_QUICK_BALL"),64*4));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),64*6));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64*5));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.COOKED_BEEF,64*6));
                            Main.econ.depositPlayer(player,40000);
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


//        //补偿礼包
//        this.setButton(3,new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/6fc0bbee99cb7c5b88dcba3375a9c37e43bafcaa366a2f66cd0630e7822c3a73",
//                        ColorParser.parse("&f补偿礼包 &7// &fReimburse"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7很抱歉清档，我们会在以后对账户进行严格管控。"),
//                        ColorParser.parse("&7防止此次事件再次发生，很抱歉，感谢各位一直支持。"),
//                        ColorParser.parse("&7请在 &c4月15日 &7之前领取完毕，过期失效。"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c疾风跑鞋*"+1),
//                        ColorParser.parse("&r          &c雷之套装*"+1),
//                        ColorParser.parse("&r          &c极巨糖果*"+8),
//                        ColorParser.parse("&r          &c幸运方块*"+20),
//                        ColorParser.parse("&r          &c许愿星*"+1),
//                        ColorParser.parse("&r          &c超级球*"+64*4),
//                        ColorParser.parse("&r          &c高级球*"+64*2),
//                        ColorParser.parse("&r          &c大师球*"+3),
//                        ColorParser.parse("&r          &c熟牛肉*"+64),
//                        ColorParser.parse("&r          &c牧场*"+2),
//                        ColorParser.parse("&r          &c钥石*"+1)
//
//                ),
//                type -> {
//
//                    try {
//                        if (KitsManager.isGetKits("补偿礼包4月13日",player)){
//                            ////////////////////
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_NEW_RUNNING_BOOTS"),1));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_THUNDER_STONE_HELM"),1));
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_THUNDER_STONE_PLATE"),1));
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_THUNDER_STONE_LEGS"),1));
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_THUNDER_STONE_BOOTS"),1));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DYNAMAX_CANDY"),8));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),20));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_WISHING_STAR"),1));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"), 64*4));
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"), 64*2));
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"), 3));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.COOKED_BEEF, 64));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH"), 2));
//
//                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_KEY_STONE"), 1));
//
//                            ////////////////////
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7非常感谢您对服务器的支持与陪伴，我们往后会做的更好！"));
//                            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_YES,1,1);
//                            player.closeInventory();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }));




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
