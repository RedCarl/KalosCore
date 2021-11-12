package kim.pokemon.util.gui;

import org.bukkit.inventory.ItemStack;

public interface SlotClick {
    public void onSlotChange(ItemStack before,ItemStack after);
}
