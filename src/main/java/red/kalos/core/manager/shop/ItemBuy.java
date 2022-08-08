package red.kalos.core.manager.shop;

import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.recharge.GiftPackShop;
import red.kalos.core.manager.recharge.GrandTotal;
import red.kalos.core.manager.recharge.recharge.RechargeMenu;
import red.kalos.core.manager.shop.entity.ItemInfo;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import red.kalos.core.manager.recharge.ArmourersShop;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ItemBuy extends InventoryGUI {
    PlayerPointsAPI playerPointsAPI = Main.getPpAPI();
    Economy economy = Main.getEcon();
    DecimalFormat decimalFormat = new DecimalFormat("#0.##");
    ArrayList<ItemInfo> ItemList = new ArrayList<>();

    public ItemBuy(Player player) {

        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 出售商店"), player, 6);


        ItemList.add(new ItemInfo("&f牧场",Material.getMaterial("PIXELMON_RANCH"),7*5000,7));
        ItemList.add(new ItemInfo("&f幸运方块",Material.getMaterial("POKELUCKY_POKE_LUCKY"),2*5000,2));
        ItemList.add(new ItemInfo("&f许愿星",Material.getMaterial("PIXELMON_WISHING_STAR"),19*5000,19));
        ItemList.add(new ItemInfo("&f钥石",Material.getMaterial("PIXELMON_KEY_STONE"),19*5000,19));
        ItemList.add(new ItemInfo("&f金色沙漏",Material.getMaterial("PIXELMON_HOURGLASS_GOLD"),4*5000,4));
        ItemList.add(new ItemInfo("&f金色沙漏",Material.getMaterial("PIXELMON_HOURGLASS_SILVER"),2*5000,2));
        ItemList.add(new ItemInfo("&f红线",Material.getMaterial("PIXELMON_DESTINY_KNOT"),29*5000,29));
        ItemList.add(new ItemInfo("&f纯净珠",Material.getMaterial("PIXELMON_ORB"),29*5000,29));
        ItemList.add(new ItemInfo("&f大师球",Material.getMaterial("PIXELMON_MASTER_BALL"),5*5000,5));


        int a = 0;
        for (ItemInfo itemInfo:ItemList) {
            Material ItemMaterial = itemInfo.getMaterial();
            double ItemMoney=itemInfo.getMoney();
            int ItemPoints=itemInfo.getPoints();
            ItemStack Item = ItemFactoryAPI.getItemStack(ItemMaterial,
                    ColorParser.parse(itemInfo.getName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7收 购:"),
                    ColorParser.parse("&r      &7(左键) &c" + ItemMoney + " &7"+Data.SERVER_VAULT),
                    ColorParser.parse("&r      &7(右键) &c" + ItemPoints + " &7"+Data.SERVER_POINTS)
            );
            Button ItemButton = new Button(Item, type -> {
                if (type.isLeftClick()) {
                    if (economy.getBalance(player)>=ItemMoney){

                        //给予物品 扣除金额
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(ItemMaterial));
                        economy.withdrawPlayer(player,ItemMoney);

                        //成功提示
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Item.getItemMeta().getDisplayName()+" &7并扣除了 &c"+ItemMoney+" &7"+Data.SERVER_VAULT+"请注意查看。"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                        ItemBuy itemBuy = new ItemBuy(player);
                        itemBuy.openInventory();
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_POINTS+"不足以购买。"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                }
                if (type.isRightClick()) {
                    try {
                        if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=ItemPoints){

                            //给予物品 扣除金额
                            player.getInventory().addItem(ItemFactoryAPI.getItemStack(ItemMaterial));
                            playerPointsAPI.takeAsync(player.getUniqueId(),ItemPoints);

                            //成功提示
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Item.getItemMeta().getDisplayName()+" &7并扣除了 &c"+ItemPoints+" &7"+Data.SERVER_POINTS+"请注意查看。"));
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            ItemBuy itemBuy = new ItemBuy(player);
                            itemBuy.openInventory();
                        }else {
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"不足以购买。"));
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
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
