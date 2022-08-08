package red.kalos.core.manager.buildmanager;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.shop.ItemBuy;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;

import java.util.ArrayList;
import java.util.List;

public class BuildGUI extends InventoryGUI {
    Economy economy = Main.getEcon();
    public BuildGUI(Player player,int slot) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 建筑材料"), player, 6);
        List<ItemStack> itemStacks = new ArrayList<>();

        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 4,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 5,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 6,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 7,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 8,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 9,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 10,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 11,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 12,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 13,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 15,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("WOOL"),(short) 14,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STONE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STONE"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STONE"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("GRASS"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("DIRT"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("DIRT"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("DIRT"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("GRASS_PATH"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("LOG"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("LOG"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("LOG"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("LOG"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("BRICK"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("BOOKSHELF"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.GLASS,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 4,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 5,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 6,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 7,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 8,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 9,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 10,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 11,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 12,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 13,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 14,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_GLASS"),(short) 15,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 4,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 5,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 6,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 7,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 8,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 9,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 10,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 11,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 12,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 13,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 14,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("STAINED_CLAY"),(short) 15,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("LOG_2"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("LOG_2"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 4,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 5,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 6,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 8,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 7,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 9,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 10,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 11,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 12,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 13,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 14,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE"),(short) 15,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 4,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 5,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 6,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 7,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 8,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 9,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 10,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 11,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 12,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 13,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 14,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("CONCRETE_POWDER"),(short) 15,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("SAPLING"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("SAPLING"),(short) 1,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("SAPLING"),(short) 2,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("SAPLING"),(short) 3,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("SAPLING"),(short) 4,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("SAPLING"),(short) 5,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("FLOWER_POT_ITEM"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("SPRUCE_FENCE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("BIRCH_FENCE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("JUNGLE_FENCE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("DARK_OAK_FENCE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("ACACIA_FENCE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.getMaterial("BONE"),(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.GLOWSTONE,(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.SEA_LANTERN,(short) 0,64));
        itemStacks.add(ItemFactoryAPI.getItemStack(Material.END_ROD,64));
        //设置当前页面的按钮
        for (int j = 0; j < 36; j++) {
            if(itemStacks.size()==(36*slot)+j){
                break;
            }

            ItemStack Item = ItemFactoryAPI.getItemStack(itemStacks.get((36*slot)+j).getType(),itemStacks.get((36*slot)+j).getDurability(),
                    ColorParser.parse("&f建筑方块"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &c 64&7"+Data.SERVER_VAULT)
            );
            int finalJ = j;
            Button ItemButton = new Button(Item, type -> {
                if (economy.getBalance(player)>=64){

                    //给予物品 扣除金额
                    player.getInventory().addItem(itemStacks.get((36*slot)+ finalJ));
                    economy.withdrawPlayer(player,64);

                    //成功提示
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Item.getItemMeta().getDisplayName()+" &7并扣除了 &c"+64+" &7"+Data.SERVER_VAULT+"请注意查看。"));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    BuildGUI buildGUI = new BuildGUI(player,slot);
                    buildGUI.openInventory();
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_POINTS+"不足以购买。"));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            });
            this.setButton(j, ItemButton);
        }

        //翻页系统
        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }

        int amountListSize = itemStacks.size()/36;
        if (itemStacks.size()%36!=0){
            amountListSize++;
        }

        for (int i = 0; i < amountListSize; i++) {
            ItemStack A = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)5,ColorParser.parse("&a第"+(i+1)+"页"));
            if (i==slot){
                A = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)14,ColorParser.parse("&a第"+(i+1)+"页"));
            }
            int finalI = i;
            Button AButton = new Button(A, type -> {
                if (type.isLeftClick()) {
                    BuildGUI buildGUI = new BuildGUI(player,finalI);
                    buildGUI.openInventory();
                }
            });
            this.setButton(36+i,AButton);
        }


        //关闭
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&7关闭"));
        Button CloseButton = new Button(Close, type -> {
            player.closeInventory();
        });
        this.setButton(53, CloseButton);
    }
}
