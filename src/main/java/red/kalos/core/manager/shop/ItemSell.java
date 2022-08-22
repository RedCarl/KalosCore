package red.kalos.core.manager.shop;

import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.shop.entity.ItemInfo;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemSell extends InventoryGUI {
    PlayerPointsAPI playerPointsAPI = Main.getPpAPI();
    Economy economy = Main.getEcon();
    DecimalFormat decimalFormat = new DecimalFormat("#0.##");
    ArrayList<ItemInfo> ItemList = new ArrayList<>();

    public ItemSell(Player player) {

        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 收购商店"), player, 6);


        ItemList.add(new ItemInfo("&a胡萝卜",Material.CARROT_ITEM,0.4,0));
        ItemList.add(new ItemInfo("&a马铃薯",Material.POTATO_ITEM,0.4,0));
        ItemList.add(new ItemInfo("&a小麦",Material.WHEAT,0.4,0));

        ItemList.add(new ItemInfo("&a甘蔗",Material.SUGAR_CANE,0.04,0));
        ItemList.add(new ItemInfo("&a仙人掌",Material.CACTUS,0.02,0));

        ItemList.add(new ItemInfo("&a南瓜",Material.PUMPKIN,0.7,0));
        ItemList.add(new ItemInfo("&a西瓜",Material.MELON_BLOCK,1.5,0));

        ItemList.add(new ItemInfo("&6煤矿石",Material.COAL_ORE,0.65,0));
        ItemList.add(new ItemInfo("&6铁矿石",Material.IRON_ORE,1.25,0));
        ItemList.add(new ItemInfo("&6金矿石",Material.GOLD_ORE,2.35,0));
        ItemList.add(new ItemInfo("&6钻石矿",Material.DIAMOND_ORE,2.75,0));
        ItemList.add(new ItemInfo("&6绿宝石矿",Material.EMERALD_ORE,4.3,0));

        ItemList.add(new ItemInfo("&f黑球果",Material.getMaterial("PIXELMON_BLACK_APRICORN"),0.9,0));
        ItemList.add(new ItemInfo("&f白球果",Material.getMaterial("PIXELMON_WHITE_APRICORN"),0.9,0));
        ItemList.add(new ItemInfo("&f粉球果",Material.getMaterial("PIXELMON_PINK_APRICORN"),0.9,0));
        ItemList.add(new ItemInfo("&f绿球果",Material.getMaterial("PIXELMON_GREEN_APRICORN"),0.9,0));
        ItemList.add(new ItemInfo("&f蓝球果",Material.getMaterial("PIXELMON_BLUE_APRICORN"),0.9,0));
        ItemList.add(new ItemInfo("&f黄球果",Material.getMaterial("PIXELMON_YELLOW_APRICORN"),0.9,0));
        ItemList.add(new ItemInfo("&f红球果",Material.getMaterial("PIXELMON_RED_APRICORN"),0.9,0));


        int a = 0;
        for (ItemInfo itemInfo:ItemList) {
            Material ItemMaterial = itemInfo.getMaterial();
            ItemStack ItemPlayer = PokemonAPI.getAllItem(player.getInventory(), ItemFactoryAPI.getItemStack(ItemMaterial));
            double ItemMoney=itemInfo.getMoney();
            ItemStack Item = ItemFactoryAPI.getItemStack(ItemMaterial,
                    ColorParser.parse(itemInfo.getName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7收 购:"),
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


        //关闭
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&7关闭"));
        Button CloseButton = new Button(Close, type -> {
            player.closeInventory();
        });
        this.setButton(53, CloseButton);
    }

}
