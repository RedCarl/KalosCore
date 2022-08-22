package red.kalos.core.manager.shop;

import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.item.CustomItem;
import red.kalos.core.manager.shop.entity.ItemInfo;
import red.kalos.core.util.gui.inventory.SkullAPI;
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
        ItemList.add(new ItemInfo("&f银色沙漏",Material.getMaterial("PIXELMON_HOURGLASS_SILVER"),2*5000,2));
        ItemList.add(new ItemInfo("&f红线",Material.getMaterial("PIXELMON_DESTINY_KNOT"),29*5000,29));
        ItemList.add(new ItemInfo("&f纯净珠",Material.getMaterial("PIXELMON_ORB"),29*5000,29));
        ItemList.add(new ItemInfo("&f大师球",Material.getMaterial("PIXELMON_MASTER_BALL"),5*5000,5));
        ItemList.add(new ItemInfo("&f蛋糕",Material.CAKE,64,1));
        ItemList.add(new ItemInfo("&d超梦进化石-X",Material.getMaterial("PIXELMON_MEWTWONITE_X"),48*5000,48));
        ItemList.add(new ItemInfo("&d超梦进化石-Y",Material.getMaterial("PIXELMON_MEWTWONITE_Y"),48*5000,48));
        ItemList.add(new ItemInfo("&d拉帝亚斯",Material.getMaterial("PIXELMON_LATIASITE"),48*5000,48));
        ItemList.add(new ItemInfo("&d拉帝欧斯",Material.getMaterial("PIXELMON_LATIOSITE"),48*5000,48));
        ItemList.add(new ItemInfo("&d蒂安希",Material.getMaterial("PIXELMON_DIANCITE"),48*5000,48));
        ItemList.add(new ItemInfo("&f班基拉斯",Material.getMaterial("PIXELMON_TYRANITARITE"),18*5000,18));
        ItemList.add(new ItemInfo("&f路卡利欧",Material.getMaterial("PIXELMON_LUCARIONITE"),18*5000,18));
        ItemList.add(new ItemInfo("&f烈咬陆鲨",Material.getMaterial("PIXELMON_GARCHOMPITE"),18*5000,18));
        ItemList.add(new ItemInfo("&f喷火龙-X",Material.getMaterial("PIXELMON_CHARIZARDITE_X"),18*5000,18));
        ItemList.add(new ItemInfo("&f喷火龙-Y",Material.getMaterial("PIXELMON_CHARIZARDITE_Y"),18*5000,18));
        ItemList.add(new ItemInfo("&f巨金怪",Material.getMaterial("PIXELMON_METAGROSSITE"),18*5000,18));
        ItemList.add(new ItemInfo("&f暴飞龙",Material.getMaterial("PIXELMON_SALAMENCITE"),18*5000,18));
        ItemList.add(new ItemInfo("&c朱红色宝珠",Material.getMaterial("PIXELMON_RED_ORB"),38*5000,38));
        ItemList.add(new ItemInfo("&b湛蓝色宝珠",Material.getMaterial("PIXELMON_BLUE_ORB"),38*5000,38));
        ItemList.add(new ItemInfo("&c腐朽的剑",Material.getMaterial("PIXELMON_RUSTED_SWORD"),32*5000,32));
        ItemList.add(new ItemInfo("&c腐朽的盾",Material.getMaterial("PIXELMON_RUSTED_SHIELD"),32*5000,32));
        ItemList.add(new ItemInfo("&b奈克洛露奈合体器",Material.getMaterial("PIXELMON_N_LUNARIZER"),32*5000,32));
        ItemList.add(new ItemInfo("&6奈克洛索尔合体器",Material.getMaterial("PIXELMON_N_SOLARIZER"),32*5000,32));
        ItemList.add(new ItemInfo("&f基因之楔",Material.getMaterial("PIXELMON_DNA_SPLICERS"),32*5000,32));
        ItemList.add(new ItemInfo("&f牵绊缰绳",Material.getMaterial("PIXELMON_REINS_OF_UNITY"),32*5000,32));
        ItemList.add(new ItemInfo("&f金刚宝珠",Material.getMaterial("PIXELMON_ADAMANT_ORB"),32*5000,32));
//        ItemList.add(new ItemInfo("&b音速自行车",Material.getMaterial("PIXELMON_MACH_BIKE"),1*5000,1));
//        ItemList.add(new ItemInfo("&c越野自行车",Material.getMaterial("PIXELMON_ACRO_BIKE"),1*5000,1));

        ItemList.add(new ItemInfo("&6传奇宝可梦&7[宝箱:随机(禁 三鸟)]",Material.getMaterial("PIXELMON_ULTRA_RUIN_KEY"),68*10000,68));
        ItemList.add(new ItemInfo("&e宝可梦进化石&7[宝箱:随机]",Material.getMaterial("PIXELMON_AERODACTYLITE"),9*5000,9));
        ItemList.add(new ItemInfo("&b个体卷轴",Material.getMaterial("PIXELMON_HP_UP"),-1,18));
        ItemList.add(new ItemInfo("&e地皮扩充",Material.DIAMOND_HOE,10*5000,10));
        int a = 0;
        for (ItemInfo itemInfo:ItemList) {
            Material ItemMaterial = itemInfo.getMaterial();
            double ItemMoney=itemInfo.getMoney();
            int ItemPoints=itemInfo.getPoints();
            ItemStack Item = ItemFactoryAPI.getItemStack(ItemMaterial,
                    ColorParser.parse(itemInfo.getName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(左键) &c" + ItemMoney + " &7"+Data.SERVER_VAULT),
                    ColorParser.parse("&r      &7(右键) &c" + ItemPoints + " &7"+Data.SERVER_POINTS)
            );

            if (itemInfo.getName().equals("&6传奇宝可梦&7[宝箱:随机(禁 三鸟)]")){
                Item = SkullAPI.getSkullItem("6f9020c07d875bad1440337adb55a08c15db06b994646a691795f4cd293fe3de",
                        ColorParser.parse(itemInfo.getName()),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7收 购:"),
                        ColorParser.parse("&r      &7(左键) &c" + ItemMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键) &c" + ItemPoints + " &7"+Data.SERVER_POINTS)
                );
            }
            if (itemInfo.getName().equals("&e宝可梦进化石&7[宝箱:随机]")){
                Item = SkullAPI.getSkullItem("92defbe3cde326d4511bb53339d777afa703f3ec4daa697d61a4402744cbb0cd",
                        ColorParser.parse(itemInfo.getName()),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7收 购:"),
                        ColorParser.parse("&r      &7(左键) &c" + ItemMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键) &c" + ItemPoints + " &7"+Data.SERVER_POINTS)
                );
            }


            ItemStack finalItem = Item;
            Button ItemButton = new Button(Item, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
                if (type.isLeftClick()) {
                    if (ItemMoney<=0){
                        return;
                    }
                    if (economy.getBalance(player)>=ItemMoney){

                        //给予物品
                        switch (itemInfo.getName()) {
                            case "&6传奇宝可梦&7[宝箱:随机(禁 三鸟)]":
                                player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.RandomLegend, "RandomLegend"));
                                break;
                            case "&e宝可梦进化石&7[宝箱:随机]":
                                player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.RandomEvolution, "RandomEvolution"));
                                break;
                            case "&b个体卷轴":
                                player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.IVsScroll, "ivs"));
                                break;
                            case "&e地皮扩充":
                                player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.PlotAdder, "RandomPlot"));
                                break;
                            default:
                                player.getInventory().addItem(ItemFactoryAPI.getItemStack(ItemMaterial));
                                break;
                        }

                        //扣除金额
                        economy.withdrawPlayer(player,ItemMoney);

                        //成功提示
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+ finalItem.getItemMeta().getDisplayName()+" &7并扣除了 &c"+ItemMoney+" &7"+Data.SERVER_VAULT+"请注意查看。"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                        ItemBuy itemBuy = new ItemBuy(player);
                        itemBuy.openInventory();
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"不足以购买。"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                }
                if (type.isRightClick()) {
                    try {
                        if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=ItemPoints){

                            //给予物品
                            switch (itemInfo.getName()) {
                                case "&6传奇宝可梦&7[宝箱:随机(禁 三鸟)]":
                                    player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.RandomLegend, "RandomLegend"));
                                    break;
                                case "&e宝可梦进化石&7[宝箱:随机]":
                                    player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.RandomEvolution, "RandomEvolution"));
                                    break;
                                case "&b个体卷轴":
                                    player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.IVsScroll, "ivs"));
                                    break;
                                case "&e地皮扩充":
                                    player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.PlotAdder, "RandomPlot"));
                                    break;
                                default:
                                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(ItemMaterial));
                                    break;
                            }

                            //扣除金额
                            playerPointsAPI.takeAsync(player.getUniqueId(),ItemPoints);

                            //成功提示
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+ finalItem.getItemMeta().getDisplayName()+" &7并扣除了 &c"+ItemPoints+" &7"+Data.SERVER_POINTS+"请注意查看。"));
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
