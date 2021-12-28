package kim.pokemon.kimexpand.warps;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Warps.CmiWarp;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.ui.module.ConfirmUI;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class WarpMenu extends InventoryGUI {
    public WarpMenu(Player player) {
        super(ColorParser.parse("&0" + Data.SERVER_NAME + " / 公共坐标"), player, 6);

        HashMap<String, CmiWarp> hashMap = CMI.getInstance().getWarpManager().getWarps();


        int j=0;
        for (CmiWarp cmiWarp:hashMap.values()) {

            ItemStack Warp = ItemFactoryAPI.getItemStack(cmiWarp.getIcon().getType(),ColorParser.parse("&f"+cmiWarp.getName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o更方便的传送至该坐标点."));
            Button WarpButton = new Button(Warp, type -> {
                if (type.isLeftClick()) {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player,"warp "+cmiWarp.getName());
                }
                if (player.hasPermission("kim.admin")){
                    if (type.isRightClick()){
                        ConfirmUI confirmUI = new ConfirmUI(player,"removewarp "+cmiWarp.getName());
                        confirmUI.openInventory();
                    }
                }
            });
            this.setButton(j, WarpButton);

            j++;
        }

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

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
