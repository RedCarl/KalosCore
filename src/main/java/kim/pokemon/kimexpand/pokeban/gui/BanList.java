package kim.pokemon.kimexpand.pokeban.gui;

import kim.pokemon.configFile.Data;
import kim.pokemon.database.PokemonBanDataSQLReader;
import kim.pokemon.kimexpand.pokeban.PokemonBan;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.ui.module.ConfirmUI;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BanList extends InventoryGUI {
    public BanList(Player player, int slot) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 物品封禁 &7("+slot+")"), player, 6);

        List<ItemStack> itemStacks = new ArrayList<>();

        for (String item: PokemonBan.CraftBlackList) {
            ItemStack itemStack = ItemFactoryAPI.getItemStack(Material.getMaterial(item));
            itemStacks.add(itemStack);
        }

        for (String item : PokemonBanDataSQLReader.getBanDrops()) {
            ItemStack itemStack = ItemFactoryAPI.getItemStack(Material.getMaterial(item));
            itemStacks.add(itemStack);
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
            ItemStack A = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530",ColorParser.parse("&a第"+(i+1)+"页"));
            int finalI = i;
            Button AButton = new Button(A, type -> {
                if (type.isLeftClick()) {
                    BanList banList = new BanList(player, finalI);
                    banList.openInventory();
                }
            });
            this.setButton(45+i,AButton);
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

    public static void main(String[] args) {
        System.out.println(73/36);
        System.out.println(73%36);
    }

}
