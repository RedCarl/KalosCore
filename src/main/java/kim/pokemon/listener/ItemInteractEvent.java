package kim.pokemon.listener;

import kim.pokemon.manager.optional.MaxLegendGUI;
import kim.pokemon.util.api.CustomItem;
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

            if (i!=null&&i.getItemMeta().getDisplayName()!=null&&i.getItemMeta().getDisplayName().equals(CustomItem.MaxLegend.getItemMeta().getDisplayName())){
                MaxLegendGUI maxLegendGUI = new MaxLegendGUI(player);
                maxLegendGUI.openInventory();
                event.setCancelled(true);
            }
        }
    }
}
