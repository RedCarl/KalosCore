package kim.pokemon.manager.pokeban.gui;

import kim.pokemon.database.BanItemManager;
import kim.pokemon.configFile.Data;
import kim.pokemon.manager.menu.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BanList extends InventoryGUI {
    public BanList(Player player, int slot) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 物品封禁 &7("+slot+")"), player, 6);

        List<ItemStack> itemStacks = new ArrayList<>();

        for (String item : BanItemManager.getBanDrops()) {
            try {
                if (Material.getMaterial(item)!=null){
                    ItemStack itemStack = ItemFactoryAPI.getItemStack(Material.getMaterial(item));
                    itemStacks.add(itemStack);
                }
            }catch (NullPointerException ignored){}

        }

        for (int j = 0; j < 36; j++) {
            if(itemStacks.size()==(36*slot)+j){
                break;
            }
            Button i = new Button(itemStacks.get((36*slot)+j), type -> {});
            this.setButton(j,i);

        }

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
                    BanList banList = new BanList(player, finalI);
                    banList.openInventory();
                }
            });
            this.setButton(36+i,AButton);
        }


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
