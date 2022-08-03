package kim.pokemon.manager.questmanager.quest.requirement.handler;

import org.bukkit.inventory.ItemStack;

public interface ItemRequirementHandler {
    boolean handle(ItemStack itemStack);
}
