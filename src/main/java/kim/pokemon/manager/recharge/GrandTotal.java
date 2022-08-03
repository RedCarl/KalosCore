package kim.pokemon.manager.recharge;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import kim.pokemon.Main;
import kim.pokemon.database.PlayerDataManager;
import kim.pokemon.configFile.Data;
import kim.pokemon.manager.menu.MainMenu;
import kim.pokemon.manager.recharge.recharge.RechargeMenu;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.api.CustomItem;
import kim.pokemon.util.gui.inventory.SkullAPI;
import kim.pokemon.command.pokeaward.PokeFormCommand;
import kim.pokemon.manager.recharge.shop.ItemBuy;
import kim.pokemon.manager.recharge.shop.ItemSell;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GrandTotal extends InventoryGUI {

    double RMB;

    public GrandTotal(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 累计充值"), player, 6);

        RMB = PlayerDataManager.getPlayerData(player.getUniqueId()).getRecharge();

        double APrice = 6;
        String APermission = "A";
        Button A = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/c9e89600ba75dc346b73762024ef25b0dd9dfc6fc4f26a684f8812a86ae3dbdc",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+APrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ APrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c大师球*2"),
                        ColorParser.parse("&r          &c幸运方块*12"),
                        ColorParser.parse("&r          &c爆肌蚊*1"),
                        ColorParser.parse("&r          &c玛娜菲*1")
                ),
                type -> {
                    if (RMB>=APrice){
                        if (!player.hasPermission("kim.grandtotal."+APermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+APermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Manaphy"));
                            PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Buzzwole"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),2));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),12));

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





        double BPrice = 28;
        String BPermission = "B";
        Button B = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/db80706f882e7762c11d5ab3cf190f828145dc30a64cb453c7f18842f0aa74d9",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+BPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ BPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c甲贺忍蛙(一般2 / 皮肤)*1"),
                        ColorParser.parse("&r          &c甲贺忍蛙*1"),
                        ColorParser.parse("&r          &c幸运方块*6")
                ),
                type -> {
                    if (RMB>=BPrice){
                        if (!player.hasPermission("kim.grandtotal."+BPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+BPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Greninja"));
                            PokeFormCommand.addPokemonForm(player.getName(), "甲贺忍蛙","一般2",1);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),6));

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





        double CPrice = 68;
        String CPermission = "C";
        Button C = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e18af14cb4046c32678e9a97bd78461b97524b41022335a12bbc45bc6985a5b4",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+CPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ CPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c伪传说宝可梦(抽奖)*1"),
                        ColorParser.parse("&r          &c宝可梦进化石(抽奖)*1"),
                        ColorParser.parse("&r          &c大师球*5"),
                        ColorParser.parse("&r          &c高级球*88"),
                        ColorParser.parse("&r          &c牛排*64")
                ),
                type -> {
                    if (RMB>=CPrice){
                        if (!player.hasPermission("kim.grandtotal."+CPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+CPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            Pokemon pokemon = PokemonAPI.getRandomPseudoLegendaryPokemon();
                            PokemonAPI.GivePokemon(player,false,2,0,false,pokemon);
                            PokemonAPI.getRandomEvolution(player);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),5));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),88));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.COOKED_BEEF,64));

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



        double DPrice = 198;
        String DPermission = "D";
        Button D = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/5e8d16836e13786469a77bd17983b909ef59a33b4d4dd67d7a9d76ae810a09f5",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+DPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ DPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c伪传说宝可梦(抽奖)*1"),
                        ColorParser.parse("&r          &c藏玛然特(5v)*1"),
                        ColorParser.parse("&r          &c幸运方块*18"),
                        ColorParser.parse("&r          &c创建公会(永久)*1"),
                        ColorParser.parse("&r          &c牧场*2"),
                        ColorParser.parse("&r          &c红线*1")
                ),
                type -> {
                    if (RMB>=DPrice){
                        if (!player.hasPermission("kim.grandtotal."+DPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+DPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            PokemonAPI.GivePokemon(player,false,2,0,false,PokemonAPI.getRandomPseudoLegendaryPokemon());
                            PokemonAPI.GivePokemon(player,false,5,0,false,PokemonAPI.SpawnPokemon("Zamazenta"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),18));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH"),2));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DESTINY_KNOT"),1));

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



        double EPrice = 398;
        String EPermission = "E";
        Button E = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/aeae6fe18f27bbb3b62a03e7c0da2a3d88d30b655098feb377a32f672e33b7f4",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+EPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ EPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c纸御剑(6v)*1"),
                        ColorParser.parse("&r          &c时装碎片*32")
                ),
                type -> {
                    if (RMB>=EPrice){
                        if (!player.hasPermission("kim.grandtotal."+EPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+EPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            PokemonAPI.GivePokemon(player,false,6,0,false,PokemonAPI.SpawnPokemon("Kartana"));
                            long ArmourersMoney=-1;
                            int ArmourersPrice=3;
                            ItemStack Armourers = ItemFactoryAPI.getItemStack(Material.PAPER,
                                    ColorParser.parse("&c"+Data.SERVER_NAME_CN+"の时装碎片"),
                                    ColorParser.parse("&f范围: &a1~10 &f个"),
                                    ColorParser.parse("&r"),
                                    ColorParser.parse("&r  &e■ &7售 价:"),
                                    ColorParser.parse("&r      &7(左键) &c" + ArmourersMoney + " &7"+Data.SERVER_VAULT),
                                    ColorParser.parse("&r      &7(右键) &c" + ArmourersPrice + " &7"+Data.SERVER_POINTS),
                                    ColorParser.parse("&r"),
                                    ColorParser.parse("&7&o时装碎片可以去兑换时装，需要非常多哦!"));
                            Armourers.setAmount(32);
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
        this.setButton(4, E);



        double FPrice = 598;
        String FPermission = "F";
        Button F = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/7984814a53275ddc5a21d2415b7ca50c05e3d2edceb001ca384878bf3171aaf6",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+FPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ FPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c初始圣杯(装饰品)*1"),
                        ColorParser.parse("&r          &c卡点*50")
                ),
                type -> {
                    if (RMB>=FPrice){
                        if (!player.hasPermission("kim.grandtotal."+FPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+FPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PLATEHOLDER"),1));
                            Main.ppAPI.giveAsync(player.getUniqueId(),50);

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



        double GPrice = 999;
        String GPermission = "G";
        Button G = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/b38ecd20aec9f9c95f5f97dd3d25bc419e2a721456a50b38167930cda60c948f",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+GPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ GPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c铁火辉夜(闪光 6v)*1"),
                        ColorParser.parse("&r          &c极具化糖果*64"),
                        ColorParser.parse("&r          &c极具化汤*5"),
                        ColorParser.parse("&r          &c牧场*3"),
                        ColorParser.parse("&r          &c卡点*50")
                ),
                type -> {
                    if (RMB>=GPrice){
                        if (!player.hasPermission("kim.grandtotal."+GPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+GPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            PokemonAPI.GivePokemon(player,true,6,0,false,PokemonAPI.SpawnPokemon("Celesteela"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DYNAMAX_CANDY"),64));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MAX_SOUP"),5));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH"),3));
                            Main.ppAPI.giveAsync(player.getUniqueId(),50);

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



        double HPrice = 1388;
        String HPermission = "H";
        Button H = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e097014ae1420ac74b3c7f8d5d1d175e49646b59f756cdcd30d1aa880b75238e",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+HPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ HPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c哲尔尼亚斯(创造者 / 皮肤)*1"),
                        ColorParser.parse("&r          &c皮卡丘(摇滚皮卡丘 / 皮肤)*1"),
                        ColorParser.parse("&r          &c卡点*50")
                ),
                type -> {
                    if (RMB>=HPrice){
                        if (!player.hasPermission("kim.grandtotal."+HPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+HPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            PokeFormCommand.addPokemonForm(player.getName(), "哲尔尼亚斯","创造者",1);
                            PokeFormCommand.addPokemonForm(player.getName(), "皮卡丘","摇滚皮卡丘",1);
                            Main.ppAPI.giveAsync(player.getUniqueId(),50);

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



        double IPrice = 1888;
        String IPermission = "I";
        Button I = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/df5f088abbefc244e35924c80023c25055af10257656bac4e84e74855ec12f2b",
                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+IPrice+" &f元"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7当前进度:"),
                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ IPrice +" &f元"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c一级神自选包*1"),
                        ColorParser.parse("&r          &c烈空坐(诅咒 / 皮肤)*1"),
                        ColorParser.parse("&r          &c百变怪(6v)*1"),
                        ColorParser.parse("&r          &c幸运方块*50"),
                        ColorParser.parse("&r          &c红线*4")
                ),
                type -> {
                    if (RMB>=IPrice){
                        if (!player.hasPermission("kim.grandtotal."+IPermission)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+IPermission+" true server="+ Main.luckPerms.getServerName());

                            //奖励内容
                            PokeFormCommand.addPokemonForm(player.getName(), "烈空坐","诅咒",1);
                            PokemonAPI.GivePokemon(player,true,6,0,false,PokemonAPI.SpawnPokemon("Ditto"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),50));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DESTINY_KNOT"),4));
                            player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.MaxLegend));

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
        this.setButton(8, I);


//
//        double JPrice = 2388;
//        String JPermission = "J";
//        Button J = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/5726d9d0632e40bda5bcf65839ba2cc98a87bd619c53adf00310d6fc71f042b5",
//                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+JPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ JPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c固拉多(地之魔物 皮肤)*1"),
//                        ColorParser.parse("&r          &c水银灯(时装)*1"),
//                        ColorParser.parse("&r          &c卡点*288")
//                ),
//                type -> {
//                    if (RMB>=JPrice){
//                        if (!player.hasPermission("kim.grandtotal."+JPermission)){
//                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+JPermission+" true server="+ Main.luckPerms.getServerName());
//
//                            //奖励内容
//                            PokeFormCommand.addPokemonForm(player.getName(), "固拉多","地之魔物",1);
//                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+"kim.armourers.水银灯"+" true server="+ Main.luckPerms.getServerName());
//                            Main.ppAPI.giveAsync(player.getUniqueId(),288);
//
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                            player.closeInventory();
//                        }
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                        player.closeInventory();
//                    }
//                });
//        this.setButton(9, J);
//
//
//
//        double KPrice = 3888;
//        String KPermission = "K";
//        Button K = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/1375061d08f1d7b317675aa7fa8800d6f2066e018d9f91ecddf9caf304e97e92",
//                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+KPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ KPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c一级传说宝可梦(人工自选 6v)*1"),
//                        ColorParser.parse("&r          &c无极汰那(真实无极巨化 / 皮肤)*1"),
//                        ColorParser.parse("&r          &c卡点*398")
//                ),
//                type -> {
//                    if (RMB>=KPrice){
//                        if (!player.hasPermission("kim.grandtotal."+KPermission)){
//                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+KPermission+" true server="+ Main.luckPerms.getServerName());
//
//                            //奖励内容
//                            //自选一级传说宝可梦
//                            PokeFormCommand.addPokemonForm(player.getName(), "无极汰那","真实无极巨化",1);
//                            Main.ppAPI.giveAsync(player.getUniqueId(),398);
//
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                            player.closeInventory();
//                        }
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                        player.closeInventory();
//                    }
//                });
//        this.setButton(10, K);
//
//
//
//        double LPrice = 4999;
//        String LPermission = "L";
//        Button L = new Button(
//                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/81e42e3725c2b4ae6900580c4e2a6b830f6eca0211f7a3641433fc67fbc43d3f",
//                        ColorParser.parse("&a充值礼包 &7// &f累计 &c"+LPrice+" &f元"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7当前进度:"),
//                        ColorParser.parse("&r          &c"+ RMB +"&7/&f"+ LPrice +" &f元"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c一级传说宝可梦(6v / 抽奖)*1"),
//                        ColorParser.parse("&r          &c传说宝可梦(6v / 抽奖)*1"),
//                        ColorParser.parse("&r          &c百变怪(MT 6v)*1"),
//                        ColorParser.parse("&r          &c专属特权(登录/特效/客服/特权)*1"),
//                        ColorParser.parse("&r          &c卡点*398")
//                ),
//                type -> {
//                    if (RMB>=LPrice){
//                        if (!player.hasPermission("kim.grandtotal."+LPermission)){
//                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.grandtotal."+LPermission+" true server="+ Main.luckPerms.getServerName());
//
//                            //奖励内容
//                            Pokemon pokemon = PokemonAPI.getRandomLegendaryPokemon();
//                            PokemonAPI.GivePokemon(player,false,6,0,false,pokemon);
//                            //-//
//                            Pokemon pokemons = PokemonAPI.getRandomLegendaryMaxPokemon();
//                            PokemonAPI.GivePokemon(player,false,6,0,false,pokemons);
//                            //-//
//                            PokemonAPI.GivePokemon(player,false,6,0,true,PokemonAPI.SpawnPokemon("Ditto"));
//                            Main.ppAPI.giveAsync(player.getUniqueId(),398);
//
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7礼包内容已经进入您的背包了，请注意查收!"));
//                        }else {
//                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您已经领取过一遍了，无法再次领取奖励."));
//                            player.closeInventory();
//                        }
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您的充值额度还未达到要求，无法领取奖励."));
//                        player.closeInventory();
//                    }
//                });
//        this.setButton(11, L);


        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //"+Data.SERVER_POINTS+"充值
        ItemStack Recharge = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/76c9c0b2b1e74b70847e551be14c81b58fc6011017f8922b5fe6f66a6dc77066",ColorParser.parse("&c"+Data.SERVER_POINTS+"充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以在这里进行赞助服务器."));
        Button RechargeButton = new Button(Recharge,type -> {
            if (type.isLeftClick()) {
                RechargeMenu rechargeMenu = new RechargeMenu(player);
                rechargeMenu.openInventory();
            }
        });
        this.setButton(45, RechargeButton);

        //礼包商店
        ItemStack GiftPackShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/b99dc01dcf28445576f2268882a77706fcb9353ab0c954f96045561a79244c1e",ColorParser.parse("&a礼包商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以购买一些商品，作为赞助的回报."));
        Button GiftPackShopButton = new Button(GiftPackShop,type -> {
            if (type.isLeftClick()) {
                GiftPackShop giftPackShop = new GiftPackShop(player);
                giftPackShop.openInventory();
            }
        });
        this.setButton(46, GiftPackShopButton);

        //道具出售
        ItemStack ItemBuy = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/786f6feb285b53e7a85f924dc032d2e5816f5042a4530eecc5c034bee17b1bd0",ColorParser.parse("&e道具出售"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里可以购买一些常用的物品道具."));
        Button ItemBuyButton = new Button(ItemBuy,type -> {
            if (type.isLeftClick()) {
                kim.pokemon.manager.recharge.shop.ItemBuy itemBuy = new ItemBuy(player);
                itemBuy.openInventory();
            }
        });
        this.setButton(47, ItemBuyButton);

        //道具回收
        ItemStack ItemSell = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/45a654f38302d245e59ec5f9f6cb46748c8342cb552d79653f1198a5faa0a468",ColorParser.parse("&9道具回收"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里可以回收一些不常用的物品道具."));
        Button ItemSellButton = new Button(ItemSell,type -> {
            if (type.isLeftClick()) {
                kim.pokemon.manager.recharge.shop.ItemSell itemSell = new ItemSell(player);
                itemSell.openInventory();
            }
        });
        this.setButton(48, ItemSellButton);

        //累计充值
        ItemStack GrandTotal = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/13b08f083df8306fa86817dd08dfa024377b80e92c0800e91f292c2aba44ad3e",ColorParser.parse("&6累计充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o累计赞助到一定数额的额外奖励."));
        Button GrandTotalButton = new Button(GrandTotal,type -> {
            if (type.isLeftClick()) {
                GrandTotal grandTotal = new GrandTotal(player);
                grandTotal.openInventory();
            }
        });
        this.setButton(49, GrandTotalButton);

        //累计充值
        ItemStack ArmourersShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/66e52b0ac7b34398ff200c48d9c4fdc6bb865aad6a1d5fcf02c8266358fbaf3",ColorParser.parse("&b时装商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以购买时装来进行穿戴."));
        Button ArmourersShopButton = new Button(ArmourersShop,type -> {
            if (type.isLeftClick()) {
                ArmourersShop armourersShop = new ArmourersShop(player);
                armourersShop.openInventory();
            }
        });
        this.setButton(50, ArmourersShopButton);

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至主菜单."));
        Button CloseButton = new Button(Close,type -> {
            if (type.isLeftClick()) {
                MainMenu mainMenu = new MainMenu(player);
                mainMenu.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }

}
