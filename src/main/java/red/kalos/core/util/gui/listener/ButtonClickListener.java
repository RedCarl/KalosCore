package red.kalos.core.util.gui.listener;

import red.kalos.core.Main;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.Slot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ButtonClickListener implements Listener {

   @EventHandler
   public void onInventoryClose(InventoryCloseEvent evt){
      Inventory inv = evt.getInventory();
      if(InventoryGUI.guiContains(inv)){
         InventoryGUI.getGUI(inv).onInventoryClose(inv, (Player) evt.getPlayer());
      }
   }

   @EventHandler
   public void onPlayerDragInventory(InventoryDragEvent evt){
      InventoryView v = evt.getView();
      if(InventoryGUI.guiContains(v.getBottomInventory())||InventoryGUI.guiContains(v.getTopInventory())){
         if(evt.getRawSlots().size()!=1){
            evt.setCancelled(true);
            return;
         }
         int i = evt.getRawSlots().iterator().next();
         Inventory inv = null;
         if(InventoryGUI.guiContains(v.getBottomInventory())){
            inv = v.getBottomInventory();
         }
         if(InventoryGUI.guiContains(v.getTopInventory())){
            inv = v.getTopInventory();
         }
         assert inv != null;
         if(i<inv.getSize()){
            Slot s = InventoryGUI.getGUI(inv).getSlot(i);
            if(s==null){
               evt.setCancelled(true);
            }
         }
      }
   }

   @EventHandler
   public void onPlayerClickInventory(InventoryClickEvent evt) {
      Inventory inv = evt.getInventory();

      InventoryView v = evt.getView();

      if(InventoryGUI.guiContains(v.getBottomInventory())||InventoryGUI.guiContains(v.getTopInventory())){
         if(evt.getInventory()==null){
            return;
         }
         if(evt.getInventory().getType().equals(InventoryType.PLAYER)){
            if(evt.getClick().isShiftClick()){
               evt.setCancelled(true);
            }
         }
         if(evt.getAction().equals(InventoryAction.COLLECT_TO_CURSOR)){
            evt.setCancelled(true);
         }
      }

      if (InventoryGUI.guiContains(inv)) {
         InventoryGUI gui = InventoryGUI.getGUI(inv);

         Button button = gui.getButton(evt.getRawSlot());
         Slot slot = gui.getSlot(evt.getRawSlot());

         if(slot==null){
            evt.setCancelled(true);
         }

         if(button!=null){
            button.getButtonClick().onButtonClicked(evt.getClick());
            gui.refreshInventory();
         }

         if(slot!=null){
            if(evt.getClick().isShiftClick()&&!evt.getInventory().getTitle().contains("[EDIT]")){
               evt.setCancelled(true);
            }else {
               Bukkit.getScheduler().runTaskLater(Main.getInstance(),()->{
                  ItemStack before = slot.getItemStack();
                  ItemStack after = inv.getItem(evt.getRawSlot());
                  slot.getSlotClick().onSlotChange(before,after);
               },0);
            }

         }
      }

   }
}
