package red.kalos.core.manager.mysteriousstore;

import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import red.kalos.core.util.ColorParser;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PikachuShop extends InventoryGUI {
    public PikachuShop(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 皮卡丘の宝藏"), player, 6);


//        double RMB = Main.getInstance().getGlazedPayDataSQLReader().getPlayerTime(player.getName(),"2022-01-30","2022-02-04").getAmount();
//
//
//        double APrice = 6;
//        String APermission = "A";
//        Button A = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/c9e89600ba75dc346b73762024ef25b0dd9dfc6fc4f26a684f8812a86ae3dbdc",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+APrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ APrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c幸运方块*2"),
//                        ColorParser.parse("&r          &c卡点*4")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=APrice){
//                            if (!player.hasPermission("kim.pikachushop."+APermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+APermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),2));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),4);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(0, A);
//
//
//
//        double BPrice = 18;
//        String BPermission = "B";
//        Button B = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/db80706f882e7762c11d5ab3cf190f828145dc30a64cb453c7f18842f0aa74d9",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+BPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ BPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c幸运方块*2"),
//                        ColorParser.parse("&r          &c卡点*8")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=BPrice){
//                            if (!player.hasPermission("kim.pikachushop."+BPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+BPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),2));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),8);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(1, B);
//
//
//
//        double CPrice = 32;
//        String CPermission = "C";
//        Button C = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e18af14cb4046c32678e9a97bd78461b97524b41022335a12bbc45bc6985a5b4",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+CPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ CPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c快龙(创造者 皮肤)*1"),
//                        ColorParser.parse("&r          &c大师球*1")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=CPrice){
//                            if (!player.hasPermission("kim.pikachushop."+CPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+CPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                PokeFormCommand.addPokemonForm(player.getName(), "快龙","创造者",1);
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),1));
//
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(2, C);
//
//
//
//        double DPrice = 98;
//        String DPermission = "D";
//        Button D = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/5e8d16836e13786469a77bd17983b909ef59a33b4d4dd67d7a9d76ae810a09f5",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+DPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ DPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c公园球*1"),
//                        ColorParser.parse("&r          &c大师球*1"),
//                        ColorParser.parse("&r          &c卡点*30")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=DPrice){
//                            if (!player.hasPermission("kim.pikachushop."+DPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+DPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PARK_BALL"),1));
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),1));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),30);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(3, D);
//
//
//
//        double EPrice = 168;
//        String EPermission = "E";
//        Button E = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/aeae6fe18f27bbb3b62a03e7c0da2a3d88d30b655098feb377a32f672e33b7f4",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+EPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ EPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c高级球*128"),
//                        ColorParser.parse("&r          &c卡洛币*10000"),
//                        ColorParser.parse("&r          &c卡点*39")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=EPrice){
//                            if (!player.hasPermission("kim.pikachushop."+EPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+EPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                Main.econ.depositPlayer(player,10000);
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),128));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),39);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(4, E);
//
//
//
//        double FPrice = 348;
//        String FPermission = "F";
//        Button F = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/7984814a53275ddc5a21d2415b7ca50c05e3d2edceb001ca384878bf3171aaf6",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+FPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ FPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c卡洛币*30000"),
//                        ColorParser.parse("&r          &c牧场*1"),
//                        ColorParser.parse("&r          &c卡点*88")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=FPrice){
//                            if (!player.hasPermission("kim.pikachushop."+FPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+FPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                Main.econ.depositPlayer(player,30000);
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH"),1));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),88);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(5, F);
//
//
//
//        double GPrice = 499;
//        String GPermission = "G";
//        Button G = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/b38ecd20aec9f9c95f5f97dd3d25bc419e2a721456a50b38167930cda60c948f",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+GPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ GPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c基拉祈(闪光)*1"),
//                        ColorParser.parse("&r          &c卡点*68")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=GPrice){
//                            if (!player.hasPermission("kim.pikachushop."+GPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+GPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                PokemonAPI.GivePokemon(player,true,0,0,false,PokemonAPI.SpawnPokemon("Jirachi"));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),68);
//
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(6, G);
//
//
//
//        double HPrice = 648;
//        String HPermission = "H";
//        Button H = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e097014ae1420ac74b3c7f8d5d1d175e49646b59f756cdcd30d1aa880b75238e",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+HPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ HPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c卡点*300")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=HPrice){
//                            if (!player.hasPermission("kim.pikachushop."+HPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+HPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                Main.ppAPI.giveAsync(player.getUniqueId(),300);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(7, H);
//
//
//
//        double IPrice = 798;
//        String IPermission = "I";
//        Button I = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/df5f088abbefc244e35924c80023c25055af10257656bac4e84e74855ec12f2b",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+IPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ IPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c快龙(6V MT)*1")
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=IPrice){
//                            if (!player.hasPermission("kim.pikachushop."+IPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+IPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                PokemonAPI.GivePokemon(player,false,6,0,true,PokemonAPI.SpawnPokemon("Dragonite"));
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(8, I);
//
//        double JPrice = 888;
//        String JPermission = "J";
//        Button J = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/df5f088abbefc244e35924c80023c25055af10257656bac4e84e74855ec12f2b",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+JPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ JPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c幸运方块*40"),
//                        ColorParser.parse("&r          &c卡点*251")
//
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=JPrice){
//                            if (!player.hasPermission("kim.pikachushop."+JPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+JPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),40));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),251);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(9, J);
//
//        double KPrice = 999;
//        String KPermission = "K";
//        Button K = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/df5f088abbefc244e35924c80023c25055af10257656bac4e84e74855ec12f2b",
//                        ColorParser.parse("&a充值礼包 &7// &f限时累计 &c"+KPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ KPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c玛夏多(闪光)*1"),
//                        ColorParser.parse("&r          &c卡点*100")
//
//                ),
//                type -> {
//                    if (type.isLeftClick()) {
//                        if (RMB>=KPrice){
//                            if (!player.hasPermission("kim.pikachushop."+KPermission)){
//                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.pikachushop."+KPermission+" true server="+ Main.luckPerms.getServerName());
//
//                                //奖励内容
//                                PokemonAPI.GivePokemon(player,true,0,0,false,PokemonAPI.SpawnPokemon("Marshadow"));
//                                Main.ppAPI.giveAsync(player.getUniqueId(),100);
//
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                            }else {
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                            player.closeInventory();
//                        }
//                    }
//                });
//        this.setButton(10, K);

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //伊布の折扣店
        ItemStack A = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ce0752ba89de69834a47f65a17c88498d07c87e54d9926f4fb9be2ce4d94f96f",
                ColorParser.parse("&e伊布の折扣店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每日随机的折扣商品供您挑选."));
        Button AButton = new Button(A, type -> {
            if (type.isLeftClick()) {
                EeveeShop eeveeShop = new EeveeShop(player);
                eeveeShop.openInventory();
            }
        });
        this.setButton(45, AButton);

        //限时累计充值
        ItemStack B = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/137f05b86b1a7fd14f29c24e15db3636d8912276ca0d566658a95645b3339f23",
                ColorParser.parse("&6皮卡丘の宝藏"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o一些限时的礼包和商品."));
        Button BButton = new Button(B, type -> {
            if (type.isLeftClick()) {
                PikachuShop pikachuShop = new PikachuShop(player);
                pikachuShop.openInventory();
            }
        });
        this.setButton(46, BButton);

        //活动兑换
        ItemStack C = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/6eac4e2908e39750a9b97fdf8c272f8c647786d452fb26a73dc37d39f5a864b9",
                ColorParser.parse("&c小火龙の活动"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用于活动兑换物品."));
        Button CButton = new Button(C, type -> {
            if (type.isLeftClick()) {
                CharmanderShop charmanderShop = new CharmanderShop(player);
                charmanderShop.openInventory();
            }
        });
        this.setButton(47, CButton);

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
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
