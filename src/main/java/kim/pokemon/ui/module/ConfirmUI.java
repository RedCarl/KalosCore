package kim.pokemon.ui.module;

import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ConfirmUI extends InventoryGUI {
    public ConfirmUI(Player player,String command) {
        super(ColorParser.parse("&0危险操作!"), player, 1);

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(i, new Button(Line));
        }

        ItemStack YES = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)5,ColorParser.parse("&a确 认"));
        Button YESButton = new Button(YES, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                Bukkit.dispatchCommand(player,command);
            }
        });
        this.setButton(2, YESButton);

        ItemStack NO = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short) 14,ColorParser.parse("&c取 消"));
        Button NOButton = new Button(NO, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
            }
        });
        this.setButton(6, NOButton);
    }
}
