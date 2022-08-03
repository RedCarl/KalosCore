package red.kalos.core.manager.questmanager.quest.requirement.handler;

import org.bukkit.inventory.ItemStack;

public interface ItemRequirementHandler {
    boolean handle(ItemStack itemStack);
}
