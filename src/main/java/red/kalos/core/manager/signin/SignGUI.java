//package red.kalos.core.manager.signin;
//
//import red.kalos.core.Main;
//import red.kalos.core.configFile.Data;
//import red.kalos.core.util.ColorParser;
//import red.kalos.core.util.PokemonAPI;
//import red.kalos.core.util.gui.Button;
//import red.kalos.core.util.gui.InventoryGUI;
//import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
//import red.kalos.core.util.gui.inventory.SkullAPI;
//import org.bukkit.Material;
//import org.bukkit.Sound;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//
//import java.util.List;
//
//public class SignGUI extends InventoryGUI {
//    public SignGUI(Player player, List<Long> signDate, long daytime) {
//        super(ColorParser.parse("&0" + Data.SERVER_NAME + " / 新手签到"), player, 1);
//
//        Button A_Kit = new Button(
//                SkullAPI.getSkullItem("a3e58ea7f3113caecd2b3a6f27af53b9cc9cfed7b043ba334b5168f1391d9",
//                        ColorParser.parse("&c#1 &f第一天"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c由克希*1"),
//                        ColorParser.parse("&r          &c猫鼬斩3v*1")
//                ),
//                type -> {
//                    if (signDate.size()!=0&&signDate.get(0)==daytime){
//                        PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Uxie"));
//                        PokemonAPI.GivePokemon(player,false,3,0,false,PokemonAPI.SpawnPokemon("Zangoose"));
//
//
//
//                        Newbie.sign(player,signDate.get(0));
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功领取了礼包，请注意查看."));
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
//                    }
//                });
//        this.setButton(0, A_Kit);
//
//
//        Button B_Kit = new Button(
//                SkullAPI.getSkullItem("ee3a8fd0852977444d9fd7797cac07b8d3948addc43f0bb5ce25ae72d95dc",
//                        ColorParser.parse("&c#2 &f第二天"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c糖果*20"),
//                        ColorParser.parse("&r          &c超级球*32")
//                ),
//                type -> {
//                    if (signDate.size()!=0&&signDate.get(1)==daytime){
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),20));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),32));
//
//
//                        Newbie.sign(player,signDate.get(1));
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功领取了礼包，请注意查看."));
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
//                    }
//                });
//        this.setButton(1, B_Kit);
//
//
//        Button C_Kit = new Button(
//                SkullAPI.getSkullItem("75419fce506a495343a1d368a71d22413f08c6d67cb951d656cd03f80b4d3d3",
//                        ColorParser.parse("&c#3 &f第三天"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c卡洛币*300"),
//                        ColorParser.parse("&r          &c高级球*24"),
//                        ColorParser.parse("&r          &c强制锻炼器*1")
//                ),
//                type -> {
//                    if (signDate.size()!=0&&signDate.get(2)==daytime){
//                        Main.getEcon().depositPlayer(player,300);
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),22));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MACHO_BRACE"),1));
//
//
//                        Newbie.sign(player,signDate.get(2));
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功领取了礼包，请注意查看."));
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
//                    }
//                });
//        this.setButton(2, C_Kit);
//
//
//        Button D_Kit = new Button(
//                SkullAPI.getSkullItem("10c75a05b344ea043863974c180ba817aea68678cbea5e4ba395f74d4803d1d",
//                        ColorParser.parse("&c#4 &f第四天"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c糖果*36"),
//                        ColorParser.parse("&r          &c卡洛币*500"),
//                        ColorParser.parse("&r          &c大师球*1")
//                ),
//                type -> {
//                    if (signDate.size()!=0&&signDate.get(3)==daytime){
//                        Main.getEcon().depositPlayer(player,500);
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),36));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),1));
//
//
//                        Newbie.sign(player,signDate.get(3));
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功领取了礼包，请注意查看."));
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
//                    }
//                });
//        this.setButton(3, D_Kit);
//
//
//        Button E_Kit = new Button(
//                SkullAPI.getSkullItem("ac3821d4f61b17f82f0d7a8e5312608ff50ede29b1b4dc89847be9427d36",
//                        ColorParser.parse("&c#5 &f第五天"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c糖果*38"),
//                        ColorParser.parse("&r          &c高级球*32"),
//                        ColorParser.parse("&r          &c卡洛币*600"),
//                        ColorParser.parse("&r          &c学习装置*1")
//                ),
//                type -> {
//                    if (signDate.size()!=0&&signDate.get(4)==daytime){
//                        Main.getEcon().depositPlayer(player,600);
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),38));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),32));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_EXP_SHARE"),1));
//
//
//                        Newbie.sign(player,signDate.get(4));
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功领取了礼包，请注意查看."));
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
//                    }
//                });
//        this.setButton(4, E_Kit);
//
//
//        Button F_Kit = new Button(
//                SkullAPI.getSkullItem("59f0743576bba4a2622480548970b721543d2c457955e8dd5c4f9ddb6a56b95c",
//                        ColorParser.parse("&c#6 &f第六天"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c糖果*40"),
//                        ColorParser.parse("&r          &c卡洛币*700"),
//                        ColorParser.parse("&r          &c高级球*64"),
//                        ColorParser.parse("&r          &c卡比兽Z*1"),
//                        ColorParser.parse("&r          &c卡比兽*1")
//                ),
//                type -> {
//                    if (signDate.size()!=0&&signDate.get(5)==daytime){
//                        Main.getEcon().depositPlayer(player,700);
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"),40));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64));
//                        PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Snorlax"));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_SNORLIUM_Z"),1));
//
//
//                        Newbie.sign(player,signDate.get(5));
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功领取了礼包，请注意查看."));
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
//                    }
//                });
//        this.setButton(5, F_Kit);
//
//
//        Button G_Kit = new Button(
//                SkullAPI.getSkullItem("ed97f4f44e796f79ca43097faa7b4fe91c445c76e5c26a5ad794f5e479837",
//                        ColorParser.parse("&c#7 &f第七天"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &a■ &7礼包内容:"),
//                        ColorParser.parse("&r          &c高级球*64"),
//                        ColorParser.parse("&r          &c大师球*2"),
//                        ColorParser.parse("&r          &c幸运方块*16"),
//                        ColorParser.parse("&r          &c艾姆利多*1"),
//                        ColorParser.parse("&r          &c费洛美螂2v*1"),
//                        ColorParser.parse("&r          &c梦幻5v*1")
//                ),
//                type -> {
//                    if (signDate.size()!=0&&signDate.get(6)==daytime){
//                        PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Mesprit"));
//                        PokemonAPI.GivePokemon(player,false,2,0,false,PokemonAPI.SpawnPokemon("Pheromosa"));
//                        PokemonAPI.GivePokemon(player,false,5,0,false,PokemonAPI.SpawnPokemon("Mew"));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),2));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64));
//                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),16));
//
//
//                        Newbie.sign(player,signDate.get(6));
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您，成功领取了礼包，请注意查看."));
//                    }else {
//                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您尚未连续签到指定天数，暂时无法领取."));
//                    }
//                });
//        this.setButton(6, G_Kit);
//
//
//        ItemStack error = ItemFactoryAPI.getItemStackWithDurability(Material.WOOL, (short) 14,ColorParser.parse("&c暂时无法领取该奖励!"));
//        for (int i = 0; i < 7; i++) {
//            if (signDate.get(i)!=daytime){
//                this.setButton(i,new Button(error));
//            }
//        }
//
//        //返回主菜单
//        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c关闭"),
//                ColorParser.parse("&r"),
//                ColorParser.parse("&7&o关闭界面."));
//        Button CloseButton = new Button(Close, type -> {
//            if (type.isLeftClick()) {
//                player.closeInventory();
//            }
//        });
//        this.setButton(8, CloseButton);
//    }
//}
