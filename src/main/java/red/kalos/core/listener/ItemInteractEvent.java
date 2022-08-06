package red.kalos.core.listener;

import de.tr7zw.nbtapi.NBTItem;
import red.kalos.core.manager.optional.OptionalGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemInteractEvent implements Listener {


    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player player = event.getPlayer();
            ItemStack i = event.getItem();
            NBTItem nbtItem = new NBTItem(i);

            if (nbtItem.getString("type") != null){
                OptionalGUI optionalGUI = new OptionalGUI(player,nbtItem.getString("type"));
                optionalGUI.openInventory();
                event.setCancelled(true);
            }
        }
    }
}
