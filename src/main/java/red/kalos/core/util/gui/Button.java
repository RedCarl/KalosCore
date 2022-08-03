package red.kalos.core.util.gui;

import org.bukkit.inventory.ItemStack;

public class Button {
   private final ItemStack icon;
   private final ButtonClick buttonClick;

   public Button(ItemStack ic) {
      this.buttonClick = (type) -> {
      };
      this.icon = ic;
   }

   public Button(ItemStack ic, ButtonClick bc) {
      this.icon = ic;
      this.buttonClick = bc;
   }

   public boolean equalsIcon(ItemStack object) {
      return this.icon.equals(object);
   }

   public ItemStack getItemStack() {
      return this.icon.clone();
   }

   public ButtonClick getButtonClick() {
      return this.buttonClick;
   }
}
