package kim.pokemon.ui.banlist;

import kim.pokemon.database.PokemonBanDataSQLReader;
import kim.pokemon.kimexpand.PokemonBan;
import kim.pokemon.ui.MainMenu;
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

public class BanList_A extends InventoryGUI {
    public BanList_A(Player player) {
        super(ColorParser.parse("&0Kim / 物品封禁 &7(1)"), player, 6);

        List<ItemStack> itemStacks = new ArrayList<>();

        for (String item: PokemonBan.CraftBlackList) {
            ItemStack itemStack = ItemFactoryAPI.getItemStack(Material.getMaterial(item),ColorParser.parse("&e[普通物品] / 禁止合成"),ColorParser.parse("&7&o"+Material.getMaterial(item).name()));
            itemStacks.add(itemStack);
        }
        for (String item : PokemonBanDataSQLReader.getBanDrops()) {
            ItemStack itemStack = ItemFactoryAPI.getItemStack(Material.getMaterial(item),ColorParser.parse("&c[普通物品] / 禁止获取"),ColorParser.parse("&7&o"+Material.getMaterial(item).name()));
            itemStacks.add(itemStack);
        }


        for (int j = 0; j < 36; j++) {
            Button i = new Button(itemStacks.get(j), type -> {});
            this.setButton(j,i);
        }


        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }

        ItemStack A = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530",ColorParser.parse("&a第一页"));
        Button AButton = new Button(A, type -> {
            if (type.isLeftClick()) {
                BanList_A banList_a = new BanList_A(player);
                banList_a.openInventory();
            }
        });
        this.setButton(45,AButton);
        ItemStack B = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847",ColorParser.parse("&a第二页"));
        Button BButton = new Button(B, type -> {
            if (type.isLeftClick()) {
                BanList_B banList_b = new BanList_B(player);
                banList_b.openInventory();
            }
        });
        this.setButton(46,BButton);


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
