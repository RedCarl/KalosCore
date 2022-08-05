package red.kalos.core.manager.recharge;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import de.tr7zw.nbtapi.NBTItem;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.premium.VIPBuy;
import red.kalos.core.manager.premium.entity.PlayerVIP;
import red.kalos.core.manager.recharge.recharge.RechargeMenu;
import red.kalos.core.manager.recharge.recharge.RechargeSelect;
import red.kalos.core.manager.shop.ItemBuy;
import red.kalos.core.manager.shop.ItemSell;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ExecutionException;

public class GiftPackShop extends InventoryGUI {
    PlayerPointsAPI playerPointsAPI = Main.ppAPI;
    public GiftPackShop(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 礼包商店"), player, 6);
//        //传说宝可梦
//        long RandomLegendaryMoney=480000;
//        int RandomLegendaryPrice=48;
//        ItemStack RandomLegendary = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e18af14cb4046c32678e9a97bd78461b97524b41022335a12bbc45bc6985a5b4",
//                ColorParser.parse("&b传说宝可梦 &7(即将下架)"),
//                ColorParser.parse("&f概率: &a普通(0%) &6传奇(100%)"),
//                ColorParser.parse("&r"),
//                ColorParser.parse("&r  &e■ &7售 价:"),
//                ColorParser.parse("&r      &7(左键) &c" + RandomLegendaryMoney + " &7"+Data.SERVER_VAULT),
//                ColorParser.parse("&r      &7(右键) &c" + RandomLegendaryPrice + " &7"+Data.SERVER_POINTS),
//                ColorParser.parse("&r"),
//                ColorParser.parse("&7&o随机获取一只传说宝可梦，属性也是随机."));
//        Button RandomLegendaryButton = new Button(RandomLegendary, type -> {
//            if (type.isLeftClick()) {
//                if (Main.econ.getBalance(player)>=RandomLegendaryMoney){
//                    Main.econ.withdrawPlayer(player,RandomLegendaryMoney);
//                    player.closeInventory();
//                    new Thread(()->{
//                        for (int i = 0; i <= 50; i++) {
//                            try {
//                                player.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse(EnumSpecies.randomLegendary().getLocalizedName()),0,20,0);
//                                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
//                                if (i<=10){
//                                    Thread.sleep(30);
//                                }else if (i<=20){
//                                    Thread.sleep(50);
//                                }else if (i<=30){
//                                    Thread.sleep(100);
//                                }else if (i<=40){
//                                    Thread.sleep(300);
//                                }else if (i<=45){
//                                    Thread.sleep(500);
//                                }else if (i<=49){
//                                    Thread.sleep(800);
//                                }else {
//                                    Pokemon pokemon = PokemonAPI.getRandomLegendaryPokemon();
//                                    PokemonAPI.GivePokemon(player,false,3,0,false,pokemon);
//                                    player.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse("&f恭喜您,获得了 &6"+pokemon.getLocalizedName()+" &f传说宝可梦."),0,60,0);
//                                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//                                    if (PokemonAPI.isLegendaryMaxPokemon(pokemon)){
//                                        for (Player p: Bukkit.getOnlinePlayers()) {
//                                            if (p!=player){
//                                                p.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse("&f恭喜 &c"+player.getName()+" &f,获得了 &6"+pokemon.getLocalizedName()+" &f传说宝可梦."),0,60,0);
//                                                p.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//                                            }
//                                        }
//                                    }
//                                }
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//                }else {
//                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+ Main.econ.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
//                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                }
//            }
//            if (type.isRightClick()) {
//                try {
//                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=RandomLegendaryPrice){
//                        playerPointsAPI.takeAsync(player.getUniqueId(),RandomLegendaryPrice);
//                        player.closeInventory();
//                        new Thread(()->{
//                            for (int i = 0; i <= 50; i++) {
//                                try {
//                                    player.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse(EnumSpecies.randomLegendary().getLocalizedName()),0,20,0);
//                                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
//                                    if (i<=10){
//                                        Thread.sleep(30);
//                                    }else if (i<=20){
//                                        Thread.sleep(50);
//                                    }else if (i<=30){
//                                        Thread.sleep(100);
//                                    }else if (i<=40){
//                                        Thread.sleep(300);
//                                    }else if (i<=45){
//                                        Thread.sleep(500);
//                                    }else if (i<=49){
//                                        Thread.sleep(800);
//                                    }else {
//                                        Pokemon pokemon = PokemonAPI.getRandomLegendaryPokemon();
//                                        PokemonAPI.GivePokemon(player,false,3,0,false,pokemon);
//                                        player.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse("&f恭喜您,获得了 &6"+pokemon.getLocalizedName()+" &f传说宝可梦."),0,60,0);
//                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//                                        if (PokemonAPI.isLegendaryMaxPokemon(pokemon)){
//                                            for (Player p: Bukkit.getOnlinePlayers()) {
//                                                if (p!=player){
//                                                    p.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse("&f恭喜 &c"+player.getName()+" &f,获得了 &6"+pokemon.getLocalizedName()+" &f传说宝可梦."),0,60,0);
//                                                    p.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//                                                }
//                                            }
//                                        }
//                                    }
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }).start();
//                    }else {
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
//                        player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                    }
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        this.setButton(0, RandomLegendaryButton);
//
//        //进化石宝箱
//        long RandomEvolutionMoney=80000;
//        int RandomEvolutionPrice=19;
//        this.setButton(1, new Button(SkullAPI.getSkullItem(
//                "http://textures.minecraft.net/texture/a5c6944593820d13d7d47db2abcfcbf683bb74a07e1a982db9f32e0a8b5dc466",
//                ColorParser.parse("&6宝可梦进化石 &7(即将下架)"),
//                ColorParser.parse("&f概率: &6超进化石(100%)"),
//                ColorParser.parse("&r"),
//                ColorParser.parse("&r  &e■ &7售 价:"),
//                ColorParser.parse("&r      &7(左键) &c" + RandomEvolutionMoney + " &7"+Data.SERVER_VAULT),
//                ColorParser.parse("&r      &7(右键) &c" + RandomEvolutionPrice + " &7"+Data.SERVER_POINTS),
//                ColorParser.parse("&r"),
//                ColorParser.parse("&7&o随机获取一只宝可梦超进化石，完全随机哦.")
//        ), type -> {
//            boolean varOn = true;
//            for (int i = 0; i < 36; i++) {
//                if (player.getInventory().getItem(i) == null) {
//                    varOn = false;
//                    break;
//                }
//            }
//            if (varOn){
//                player.closeInventory();
//                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
//                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                return;
//            }
//            if (type.isLeftClick()) {
//                if (Main.econ.getBalance(player)>=RandomEvolutionMoney){
//                    Main.econ.withdrawPlayer(player,RandomEvolutionMoney);
//                    String name = PokemonAPI.getRandomEvolution(player);
//                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您通过购买宝可梦进化石获得了一个 &c"+name+" &7超进化石."));
//                }else {
//                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+ Main.econ.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
//                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                }
//            }
//            if (type.isRightClick()) {
//                try {
//                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=RandomEvolutionPrice){
//                        playerPointsAPI.takeAsync(player.getUniqueId(),RandomEvolutionPrice);
//                        String name = PokemonAPI.getRandomEvolution(player);
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您通过购买宝可梦进化石获得了一个 &c"+name+" &7超进化石."));
//                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//                    }else {
//                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
//                        player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                    }
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }));

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        //皮卡丘会员
        int PikaniumPrice=15;
        String PikaniumRanks="pikanium";
        String PikaniumRankName="&e"+Data.SERVER_NAME_CN+"の皮卡丘";
        PlayerVIP PikaniumVIP = VIPBuy.checkRank(player,PikaniumRanks, Main.luckPerms.getServerName());
        if (PikaniumVIP!=null){
            ItemStack Pikanium = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(PikaniumRankName+" &f// &aVIP头衔"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&f到期时间&c(续费)&f:"),
                    ColorParser.parse("&a"+PikaniumVIP.getTime()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(续费) &c" + PikaniumPrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(使用: /fly)"),
                    ColorParser.parse("      &f每月礼包 &7(礼包系统查看)"),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a5%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a7&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a2&7)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即续费,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Pikanium);
            nbtItem.setShort("ndex", (short)25);
            Button PikaniumButton = new Button(nbtItem.getItem(), type -> {
                player.closeInventory();
                RechargeSelect rechargeSelect = new RechargeSelect(player, (double) PikaniumPrice);
                rechargeSelect.openInventory();
            });
            this.setButton(0, PikaniumButton);
        }else {
            ItemStack Pikanium = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(PikaniumRankName+" &f// &aVIP头衔"),
                    ColorParser.parse("&f时效: &a30 &f天"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(开通) &c" + PikaniumPrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(使用: /fly)"),
                    ColorParser.parse("      &f每月礼包 &7(礼包系统查看)"),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a5%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a7&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a2&7)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即购买,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Pikanium);
            nbtItem.setShort("ndex", (short)25);
            Button PikaniumButton = new Button(nbtItem.getItem(), type -> {
                if (VIPBuy.checkRank(player,null, Main.luckPerms.getServerName())==null){
                    player.closeInventory();
                    RechargeSelect rechargeSelect = new RechargeSelect(player, (double) PikaniumPrice);
                    rechargeSelect.openInventory();
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您还有其它的服务并未过期，请等待过期后再进行开通."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            });
            this.setButton(0, PikaniumButton);
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        //伊布会员
        int EeveePrice=45;
        String EeveeRanks="eevee";
        String EeveeRankName="&6"+Data.SERVER_NAME_CN+"の伊布";
        PlayerVIP EeveeVIP = VIPBuy.checkRank(player,EeveeRanks, Main.luckPerms.getServerName());
        if (EeveeVIP!=null){
            ItemStack Eevee = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(EeveeRankName+" &f// &6MVP头衔"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&f到期时间&c(续费)&f:"),
                    ColorParser.parse("&a"+EeveeVIP.getTime()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(续费) &c" + EeveePrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(菜单)"),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f月卡礼包 &7(每天签到领取 &c1 &7卡点)"),
                    ColorParser.parse("      &f每月礼包 &7(礼包系统查看)"),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a15%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a21&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a4&7)"),
                    ColorParser.parse("      &f传说宝可梦查询 &7(菜单)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即续费,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Eevee);
            nbtItem.setShort("ndex", (short)133);
            Button EeveeButton = new Button(nbtItem.getItem(), type -> {
                player.closeInventory();
                RechargeSelect rechargeSelect = new RechargeSelect(player, (double) EeveePrice);
                rechargeSelect.openInventory();
            });
            this.setButton(1, EeveeButton);
        }else {
            ItemStack Eevee = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(EeveeRankName+" &f// &6MVP头衔"),
                    ColorParser.parse("&f时效: &a30 &f天"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(开通) &c" + EeveePrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(菜单)"),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f月卡礼包 &7(每天签到领取 &c1 &7卡点)"),
                    ColorParser.parse("      &f每月礼包 &7(礼包系统查看)"),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a15%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a21&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a4&7)"),
                    ColorParser.parse("      &f传说宝可梦查询 &7(菜单)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即购买,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Eevee);
            nbtItem.setShort("ndex", (short)133);
            Button EeveeButton = new Button(nbtItem.getItem(), type -> {
                player.closeInventory();
                RechargeSelect rechargeSelect = new RechargeSelect(player, (double) EeveePrice);
                rechargeSelect.openInventory();
            });
            this.setButton(1, EeveeButton);
        }

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        //"+Data.SERVER_POINTS+"充值
        ItemStack Recharge = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/76c9c0b2b1e74b70847e551be14c81b58fc6011017f8922b5fe6f66a6dc77066",ColorParser.parse("&c"+Data.SERVER_POINTS+"充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以在这里进行赞助服务器."));
        Button RechargeButton = new Button(Recharge, type -> {
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
        Button GiftPackShopButton = new Button(GiftPackShop, type -> {
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
        Button ItemBuyButton = new Button(ItemBuy, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.shop.ItemBuy itemBuy = new ItemBuy(player);
                itemBuy.openInventory();
            }
        });
        this.setButton(47, ItemBuyButton);

        //道具回收
        ItemStack ItemSell = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/45a654f38302d245e59ec5f9f6cb46748c8342cb552d79653f1198a5faa0a468",ColorParser.parse("&9道具回收"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里可以回收一些不常用的物品道具."));
        Button ItemSellButton = new Button(ItemSell, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.shop.ItemSell itemSell = new ItemSell(player);
                itemSell.openInventory();
            }
        });
        this.setButton(48, ItemSellButton);

        //累计充值
        ItemStack GrandTotal = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/13b08f083df8306fa86817dd08dfa024377b80e92c0800e91f292c2aba44ad3e",ColorParser.parse("&6累计充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o累计赞助到一定数额的额外奖励."));
        Button GrandTotalButton = new Button(GrandTotal, type -> {
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
        Button ArmourersShopButton = new Button(ArmourersShop, type -> {
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
        Button CloseButton = new Button(Close, type -> {
            if (type.isLeftClick()) {
                MainMenu mainMenu = new MainMenu(player);
                mainMenu.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }
}
