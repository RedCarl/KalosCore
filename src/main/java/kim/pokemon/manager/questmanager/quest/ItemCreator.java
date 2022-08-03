package kim.pokemon.manager.questmanager.quest;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemCreator {
    public ItemStack getItemStack(Player player);
}
