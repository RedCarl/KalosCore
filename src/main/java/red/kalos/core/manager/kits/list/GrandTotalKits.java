package red.kalos.core.manager.kits.list;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import red.kalos.core.Main;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.armourers.guis.ArmourersShop;
import red.kalos.core.manager.kits.KitsGUI;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.recharge.recharge.RankShop;
import red.kalos.core.manager.recharge.recharge.RechargeMenu;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.manager.item.CustomItem;
import red.kalos.core.util.gui.inventory.SkullAPI;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GrandTotalKits extends InventoryGUI {

    double RMB;

    public GrandTotalKits(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 累计充值"), player, 6);

        RMB = PlayerDataManager.getInstance().getPlayerData(player.getUniqueId()).getRecharge();

        double APrice = 6;
        String APermission = "A";
        Button A = new Button(
                SkullAPI.getSkullItem("c9e89600ba75dc346b73762024ef25b0dd9dfc6fc4f26a684f8812a86ae3dbdc",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+APrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ APrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c高级球*64"),
                        ColorParser.parse("&r          &c卡洛币*6666")
                ),
                type -> {
                    if (RMB>=APrice){
                        if (!player.hasPermission("kim.grandtotal."+APermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+APermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64));
                            Main.getEcon().depositPlayer(player,6666);

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
        });
        this.setButton(0, A);





        double BPrice = 68;
        String BPermission = "B";
        Button B = new Button(
                SkullAPI.getSkullItem("db80706f882e7762c11d5ab3cf190f828145dc30a64cb453c7f18842f0aa74d9",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+BPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ BPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c强制锻炼器*1"),
                        ColorParser.parse("&r          &c普通学习装置*1"),
                        ColorParser.parse("&r          &c毒贝比(3v)*1"),
                        ColorParser.parse("&r          &c幸运方块*20"),
                        ColorParser.parse("&r          &c卡洛币*13600")
                ),
                type -> {
                    if (RMB>=BPrice){
                        if (!player.hasPermission("kim.grandtotal."+BPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+BPermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MACHO_BRACE"),1));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_EXP_SHARE"),1));
                            PokemonAPI.GivePokemon(player,false,3,0,false,PokemonAPI.SpawnPokemon("Poipole"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),20));
                            Main.getEcon().depositPlayer(player,13600);

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
                });
        this.setButton(1, B);





        double CPrice = 128;
        String CPermission = "C";
        Button C = new Button(
                SkullAPI.getSkullItem("e18af14cb4046c32678e9a97bd78461b97524b41022335a12bbc45bc6985a5b4",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+CPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ CPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c半神(3v 自选 基拉斯,路卡利欧,暴飞龙)*1"),
                        ColorParser.parse("&r          &c大师球*5"),
                        ColorParser.parse("&r          &c糖果*32"),
                        ColorParser.parse("&r          &c幸运方块*64"),
                        ColorParser.parse("&r          &c卡洛币*25600")
                ),
                type -> {
                    if (RMB>=CPrice || player.hasPermission("kalos.admin")){
                        if (!player.hasPermission("kim.grandtotal."+CPermission) || player.hasPermission("kalos.admin")){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+CPermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.Pokemon,"累充128"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),5));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),32));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),64));
                            Main.getEcon().depositPlayer(player,25600);

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
                });
        this.setButton(2, C);



        double DPrice = 328;
        String DPermission = "D";
        Button D = new Button(
                SkullAPI.getSkullItem("5e8d16836e13786469a77bd17983b909ef59a33b4d4dd67d7a9d76ae810a09f5",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+DPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ DPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c异兽(3v 自选 铁火辉夜,纸御剑)*1"),
                        ColorParser.parse("&r          &c大师球*16"),
                        ColorParser.parse("&r          &c糖果*64"),
                        ColorParser.parse("&r          &c幸运方块*128"),
                        ColorParser.parse("&r          &c卡洛币*65600")
                ),
                type -> {
                    if (RMB>=DPrice){
                        if (!player.hasPermission("kim.grandtotal."+DPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+DPermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.Pokemon,"累充328"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),16));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),64));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),128));
                            Main.getEcon().depositPlayer(player,65600);

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
                });
        this.setButton(3, D);



        double EPrice = 648;
        String EPermission = "E";
        Button E = new Button(
                SkullAPI.getSkullItem("aeae6fe18f27bbb3b62a03e7c0da2a3d88d30b655098feb377a32f672e33b7f4",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+EPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ EPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c随机神兽*1"),
                        ColorParser.parse("&r          &c幸运方块*256"),
                        ColorParser.parse("&r          &c大师球*32"),
                        ColorParser.parse("&r          &c糖果*128"),
                        ColorParser.parse("&r          &c卡洛币*129600")
                ),
                type -> {
                    if (RMB>=EPrice){
                        if (!player.hasPermission("kim.grandtotal."+EPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+EPermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),256));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),32));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),128));
                            Pokemon pokemon = PokemonAPI.getRandomLegendaryPokemon();
                            PokemonAPI.GivePokemon(player,false,3,0,false,pokemon);
                            player.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse("&f恭喜您,获得了 &6"+pokemon.getLocalizedName()+" &f传说宝可梦."),0,60,0);
                            Main.getEcon().depositPlayer(player,129600);

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
                });
        this.setButton(4, E);



        double FPrice = 1280;
        String FPermission = "F";
        Button F = new Button(
                SkullAPI.getSkullItem("7984814a53275ddc5a21d2415b7ca50c05e3d2edceb001ca384878bf3171aaf6",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+FPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ FPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c一级神(自选)*1"),
                        ColorParser.parse("&r          &c幸运方块*512"),
                        ColorParser.parse("&r          &c大师球*64"),
                        ColorParser.parse("&r          &c糖果*256"),
                        ColorParser.parse("&r          &c卡洛币*512000")
                ),
                type -> {
                    if (RMB>=FPrice){
                        if (!player.hasPermission("kim.grandtotal."+FPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+FPermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),512));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),64));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),256));
                            player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.MaxLegend,"MaxLegend"));
                            Main.getEcon().depositPlayer(player,512000);

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
                });
        this.setButton(5, F);


        ItemStack Armourers = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&c"+Data.SERVER_NAME_CN+"の时装碎片"),
                ColorParser.parse("&f范围: &a1~10 &f个"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + -1 + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + 3 + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o时装碎片可以去兑换时装，需要非常多哦!"));
        double GPrice = 1680;
        String GPermission = "G";
        Button G = new Button(
                SkullAPI.getSkullItem("8d6a60e5799f4edb1bf52df49474d7b428c964e4a941328e241a1eab96025869",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+GPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ GPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c一级神(自选)*1"),
                        ColorParser.parse("&r          &c幸运方块*768"),
                        ColorParser.parse("&r          &c大师球*96"),
                        ColorParser.parse("&r          &c糖果*384"),
                        ColorParser.parse("&r          &c卡洛币*768000"),
                        ColorParser.parse("&r          &c时装碎片*500")
                ),
                type -> {
                    if (RMB>=GPrice){
                        if (!player.hasPermission("kim.grandtotal."+GPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+GPermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),768));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),96));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),384));
                            player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.MaxLegend,"MaxLegend"));
                            Main.getEcon().depositPlayer(player,768000);
                            Armourers.setAmount(500);
                            player.getInventory().addItem(Armourers);

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
                });
        this.setButton(6, G);




        double HPrice = 2180;
        String HPermission = "H";
        Button H = new Button(
                SkullAPI.getSkullItem("c285dd77c64e2368fe77c31ff7c3d42b700fb7b74f2b463e696916c90f5d27ab",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+HPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ HPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c一级神(自选)*2"),
                        ColorParser.parse("&r          &c幸运方块*1024"),
                        ColorParser.parse("&r          &c大师球*128"),
                        ColorParser.parse("&r          &c糖果*512"),
                        ColorParser.parse("&r          &c卡洛币*1024000"),
                        ColorParser.parse("&r          &c水银灯时装*1")
                ),
                type -> {
                    if (RMB>=HPrice){
                        if (!player.hasPermission("kim.grandtotal."+HPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+HPermission+" true server="+ Main.getLuckPerms().getServerName());

                            //奖励内容
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),1024));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),128));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),512));
                            player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.MaxLegend,"MaxLegend"));
                            player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.MaxLegend,"MaxLegend"));
                            Main.getEcon().depositPlayer(player,1024000);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.armourers.水银灯 server="+ Main.getLuckPerms().getServerName());

                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
                            player.closeInventory();
                        }
                    }else {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
                        player.closeInventory();
                    }
                });
        this.setButton(7, H);









        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至主菜单."));
        Button CloseButton = new Button(Close, type -> {
            new KitsGUI(player).openInventory();
        });
        this.setButton(53, CloseButton);
    }

}
