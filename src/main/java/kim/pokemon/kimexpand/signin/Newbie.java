package kim.pokemon.kimexpand.signin;

import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import studio.trc.bukkit.litesignin.api.Storage;

public class Newbie extends InventoryGUI {
    public Newbie(Player player) {
        super(ColorParser.parse("&0" + Data.SERVER_NAME + " / 新手签到"), player, 1);

        int i = Storage.getPlayer(player).getContinuousSignIn();

        Button Signin = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/a92e711a1ddd2521314839f5f79af3679c982ad687460a5f7ea49fec90e9ce77",
                        ColorParser.parse("&a签到 &7(次数: "+i+")"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7活动介绍:"),
                        ColorParser.parse("&r          &c非常欢迎您来到Kalos游玩，我们为您"),
                        ColorParser.parse("&r          &c准备了丰富的新手签到礼品供您领取，需要连续签到！"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&e[点击可进行签到]")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (!Storage.getPlayer(player).alreadySignIn()){
                            Storage.getPlayer(player).signIn();
                            Newbie newbie = new Newbie(player);
                            newbie.openInventory();
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您今天已经签到过了."));
                        }
                    }
                });
        this.setButton(0, Signin);

        String A_Permission = "kim.newbie.A";
        String B_Permission = "kim.newbie.B";
        String C_Permission = "kim.newbie.C";
        String D_Permission = "kim.newbie.D";
        String E_Permission = "kim.newbie.E";
        String F_Permission = "kim.newbie.F";
        String G_Permission = "kim.newbie.G";


        Button A_Kit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/a3e58ea7f3113caecd2b3a6f27af53b9cc9cfed7b043ba334b5168f1391d9",
                        ColorParser.parse("&c#1 &f第一天"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c牙牙*1"),
                        ColorParser.parse("&r          &c糖果*18"),
                        ColorParser.parse("&r          &c超级球*22")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (i>=1){
                            player.closeInventory();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+A_Permission+" true");
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

                            PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Axew"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),18));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),22));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
                        }
                    }
                });
        this.setButton(2, A_Kit);


        Button B_Kit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ee3a8fd0852977444d9fd7797cac07b8d3948addc43f0bb5ce25ae72d95dc",
                        ColorParser.parse("&c#2 &f第二天"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c糖果*20"),
                        ColorParser.parse("&r          &c超级球*32")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (i>=2){
                            player.closeInventory();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+B_Permission+" true");
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),20));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),32));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
                        }
                    }
                });
        this.setButton(3, B_Kit);


        Button C_Kit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/75419fce506a495343a1d368a71d22413f08c6d67cb951d656cd03f80b4d3d3",
                        ColorParser.parse("&c#3 &f第三天"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c卡洛币*300"),
                        ColorParser.parse("&r          &c高级球*24")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (i>=3){
                            player.closeInventory();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+C_Permission+" true");
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

                            Main.econ.depositPlayer(player,300);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),22));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
                        }
                    }
                });
        this.setButton(4, C_Kit);


        Button D_Kit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/10c75a05b344ea043863974c180ba817aea68678cbea5e4ba395f74d4803d1d",
                        ColorParser.parse("&c#4 &f第四天"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c糖果*36"),
                        ColorParser.parse("&r          &c卡洛币*500")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (i>=4){
                            player.closeInventory();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+D_Permission+" true");
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

                            Main.econ.depositPlayer(player,500);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),36));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
                        }
                    }
                });
        this.setButton(5, D_Kit);


        Button E_Kit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ac3821d4f61b17f82f0d7a8e5312608ff50ede29b1b4dc89847be9427d36",
                        ColorParser.parse("&c#5 &f第五天"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c糖果*38"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c卡洛币*600")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (i>=5){
                            player.closeInventory();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+E_Permission+" true");
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

                            Main.econ.depositPlayer(player,600);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),38));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),32));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
                        }
                    }
                });
        this.setButton(6, E_Kit);


        Button F_Kit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/59f0743576bba4a2622480548970b721543d2c457955e8dd5c4f9ddb6a56b95c",
                        ColorParser.parse("&c#6 &f第六天"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c糖果*40"),
                        ColorParser.parse("&r          &c卡洛币*700"),
                        ColorParser.parse("&r          &c高级球*64")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (i>=6){
                            player.closeInventory();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+F_Permission+" true");
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

                            Main.econ.depositPlayer(player,700);
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),40));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
                        }
                    }
                });
        this.setButton(7, F_Kit);


        Button G_Kit = new Button(
                SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ed97f4f44e796f79ca43097faa7b4fe91c445c76e5c26a5ad794f5e479837",
                        ColorParser.parse("&c#7 &f第七天"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7礼包内容:"),
                        ColorParser.parse("&r          &c艾姆利多*1"),
                        ColorParser.parse("&r          &c高级球*64")
                ),
                type -> {
                    if (type.isLeftClick()) {
                        if (i>=7){
                            player.closeInventory();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+G_Permission+" true");
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

                            PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Mesprit"));
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64));
                        }else {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
                        }
                    }
                });
        this.setButton(8, G_Kit);


        //检查是否领取过
        if (player.hasPermission(A_Permission)){
            ItemStack A = ItemFactoryAPI.getItemStack(Material.BARRIER,
                    ColorParser.parse("&c已经领取")
            );
            this.setButton(2,new Button(A));
        }
        if (player.hasPermission(B_Permission)){
            ItemStack B = ItemFactoryAPI.getItemStack(Material.BARRIER,
                    ColorParser.parse("&c已经领取")
            );
            this.setButton(3,new Button(B));
        }
        if (player.hasPermission(C_Permission)){
            ItemStack C = ItemFactoryAPI.getItemStack(Material.BARRIER,
                    ColorParser.parse("&c已经领取")
            );
            this.setButton(4,new Button(C));
        }
        if (player.hasPermission(D_Permission)){
            ItemStack D = ItemFactoryAPI.getItemStack(Material.BARRIER,
                    ColorParser.parse("&c已经领取")
            );
            this.setButton(5,new Button(D));
        }
        if (player.hasPermission(E_Permission)){
            ItemStack E = ItemFactoryAPI.getItemStack(Material.BARRIER,
                    ColorParser.parse("&c已经领取")
            );
            this.setButton(6,new Button(E));
        }
        if (player.hasPermission(F_Permission)){
            ItemStack F = ItemFactoryAPI.getItemStack(Material.BARRIER,
                    ColorParser.parse("&c已经领取")
            );
            this.setButton(7,new Button(F));
        }
        if (player.hasPermission(G_Permission)){
            ItemStack G = ItemFactoryAPI.getItemStack(Material.BARRIER,
                    ColorParser.parse("&c已经领取")
            );
            this.setButton(8,new Button(G));
        }
    }
}
