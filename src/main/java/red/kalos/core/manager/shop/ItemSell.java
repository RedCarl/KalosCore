package red.kalos.core.manager.shop;

import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.recharge.ArmourersShop;
import red.kalos.core.manager.recharge.GiftPackShop;
import red.kalos.core.manager.recharge.GrandTotal;
import red.kalos.core.manager.recharge.recharge.RechargeMenu;
import red.kalos.core.manager.shop.entity.ItemInfo;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemSell extends InventoryGUI {
    PlayerPointsAPI playerPointsAPI = Main.ppAPI;
    Economy economy = Main.econ;
    DecimalFormat decimalFormat = new DecimalFormat("#0.##");
    ArrayList<ItemInfo> ItemList = new ArrayList<>();

    public ItemSell(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 收购商店"), player, 6);

        ItemList.add(new ItemInfo("&6胡萝卜",Material.CARROT_ITEM,0.2,0));
        ItemList.add(new ItemInfo("&6马铃薯",Material.POTATO_ITEM,0.2,0));

//        ItemList.add(new ItemInfo("&a煤矿石",Material.COAL_ORE,1.3,0));
//        ItemList.add(new ItemInfo("&a铁矿石",Material.IRON_ORE,3.5,0));
//        ItemList.add(new ItemInfo("&a金矿石",Material.GOLD_ORE,6.7,0));
//        ItemList.add(new ItemInfo("&a钻石矿",Material.DIAMOND_ORE,9.5,0));
//        ItemList.add(new ItemInfo("&a绿宝石矿",Material.EMERALD_ORE,12.6,0));

        ItemList.add(new ItemInfo("&f黑球果",Material.getMaterial("PIXELMON_BLACK_APRICORN"),0.2,0));
        ItemList.add(new ItemInfo("&f白球果",Material.getMaterial("PIXELMON_WHITE_APRICORN"),0.2,0));
        ItemList.add(new ItemInfo("&f粉球果",Material.getMaterial("PIXELMON_PINK_APRICORN"),0.2,0));
        ItemList.add(new ItemInfo("&f绿球果",Material.getMaterial("PIXELMON_GREEN_APRICORN"),0.2,0));
        ItemList.add(new ItemInfo("&f蓝球果",Material.getMaterial("PIXELMON_BLUE_APRICORN"),0.2,0));
        ItemList.add(new ItemInfo("&f黄球果",Material.getMaterial("PIXELMON_YELLOW_APRICORN"),0.2,0));
        ItemList.add(new ItemInfo("&f红球果",Material.getMaterial("PIXELMON_RED_APRICORN"),0.2,0));


        int a = 0;
        for (ItemInfo itemInfo:ItemList) {
            Material ItemMaterial = itemInfo.getMaterial();
            ItemStack ItemPlayer = PokemonAPI.getAllItem(player.getInventory(), ItemFactoryAPI.getItemStack(ItemMaterial));
            double ItemMoney=itemInfo.getMoney();
            ItemStack Item = ItemFactoryAPI.getItemStack(ItemMaterial,
                    ColorParser.parse(itemInfo.getName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(左键单个) &c" + ItemMoney + " &7"+Data.SERVER_VAULT),
                    ColorParser.parse("&r      &7(右键全部) &c" + decimalFormat.format(ItemPlayer.getAmount()*ItemMoney) + " &7"+Data.SERVER_VAULT)
            );
            Button ItemButton = new Button(Item, type -> {
                if (type.isLeftClick()) {
                    if (player.getInventory().contains(ItemMaterial)){
                        player.getInventory().removeItem(ItemFactoryAPI.getItemStack(ItemMaterial));
                        economy.depositPlayer(player,ItemMoney);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功出售了 "+Item.getItemMeta().getDisplayName()+" &7获得了 &c"+ItemMoney+Data.SERVER_VAULT+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                        ItemSell itemSell = new ItemSell(player);
                        itemSell.openInventory();
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+ItemPlayer.getAmount()+" &7个 &c"+Item.getItemMeta().getDisplayName()+" &7不足以出售."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                }
                if (type.isRightClick()) {
                    if (player.getInventory().contains(ItemMaterial)){
                        player.getInventory().removeItem(ItemPlayer);
                        economy.depositPlayer(player,ItemPlayer.getAmount()*ItemMoney);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功出售了 "+Item.getItemMeta().getDisplayName()+" &7获得了 &c"+decimalFormat.format(ItemPlayer.getAmount()*ItemMoney)+Data.SERVER_VAULT+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                        ItemSell itemSell = new ItemSell(player);
                        itemSell.openInventory();
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+ItemPlayer.getAmount()+" &7个 &c"+Item.getItemMeta().getDisplayName()+" &7不足以出售."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                }
            });
            this.setButton(a, ItemButton);
            a++;
        }


        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //"+Data.SERVER_POINTS+"充值
        ItemStack Recharge = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/76c9c0b2b1e74b70847e551be14c81b58fc6011017f8922b5fe6f66a6dc77066", ColorParser.parse("&c"+Data.SERVER_POINTS+"充值"),
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
        ItemStack GiftPackShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/b99dc01dcf28445576f2268882a77706fcb9353ab0c954f96045561a79244c1e", ColorParser.parse("&a礼包商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以购买一些商品，作为赞助的回报."));
        Button GiftPackShopButton = new Button(GiftPackShop, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.recharge.GiftPackShop giftPackShop = new GiftPackShop(player);
                giftPackShop.openInventory();
            }
        });
        this.setButton(46, GiftPackShopButton);

        //道具出售
        ItemStack ItemBuy = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/786f6feb285b53e7a85f924dc032d2e5816f5042a4530eecc5c034bee17b1bd0", ColorParser.parse("&e道具出售"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里可以购买一些常用的物品道具."));
        Button ItemBuyButton = new Button(ItemBuy, type -> {
            if (type.isLeftClick()) {
                ItemBuy itemBuy = new ItemBuy(player);
                itemBuy.openInventory();
            }
        });
        this.setButton(47, ItemBuyButton);

        //道具回收
        ItemStack ItemSell = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/45a654f38302d245e59ec5f9f6cb46748c8342cb552d79653f1198a5faa0a468", ColorParser.parse("&9道具回收"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里可以回收一些不常用的物品道具."));
        Button ItemSellButton = new Button(ItemSell, type -> {
            if (type.isLeftClick()) {
                ItemSell itemSell = new ItemSell(player);
                itemSell.openInventory();
            }
        });
        this.setButton(48, ItemSellButton);

        //累计充值
        ItemStack GrandTotal = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/13b08f083df8306fa86817dd08dfa024377b80e92c0800e91f292c2aba44ad3e", ColorParser.parse("&6累计充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o累计赞助到一定数额的额外奖励."));
        Button GrandTotalButton = new Button(GrandTotal, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.recharge.GrandTotal grandTotal = new GrandTotal(player);
                grandTotal.openInventory();
            }
        });
        this.setButton(49, GrandTotalButton);

        //累计充值
        ItemStack ArmourersShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/66e52b0ac7b34398ff200c48d9c4fdc6bb865aad6a1d5fcf02c8266358fbaf3", ColorParser.parse("&b时装商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以购买时装来进行穿戴."));
        Button ArmourersShopButton = new Button(ArmourersShop, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.recharge.ArmourersShop armourersShop = new ArmourersShop(player);
                armourersShop.openInventory();
            }
        });
        this.setButton(50, ArmourersShopButton);

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
