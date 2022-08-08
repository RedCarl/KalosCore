package red.kalos.core.manager.homes;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import com.Zrips.CMI.Modules.Homes.CmiHome;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class HomeMenu extends InventoryGUI {
    CMIUser user;
    public HomeMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 家园系统 &7("+CMI.getInstance().getPlayerManager().getUser(player).getHomes().size()+"/"+CMI.getInstance().getHomeManager().getMaxHomes(player)+")"), player, 6);

        user = CMI.getInstance().getPlayerManager().getUser(player);

        int j=0;
        for (CmiHome cmiHome:user.getHomes().values()) {

            ItemStack Home = ItemFactoryAPI.getItemStack(cmiHome.getIconMaterial().getMaterial(),ColorParser.parse("&f"+cmiHome.getName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&a左键传送 &7/ &c右键删除."));
            Button HomeButton = new Button(Home, type -> {
                if (type.isLeftClick()) {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player,"home "+cmiHome.getName());
                }
                if (type.isRightClick()){
                    Bukkit.dispatchCommand(player,"removehome "+cmiHome.getName());
                    new HomeMenu(player).openInventory();
                }
            });
            this.setButton(j, HomeButton);

            j++;
        }
        ItemStack CreateHome = SkullAPI.getSkullItem("ac4970ea91ab06ece59d45fce7604d255431f2e03a737b226082c4cce1aca1c4",
                ColorParser.parse("&a创建家园 &7(随机名称)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您可以输入 &c/sethome 家园名称 &7&o创建它.")
        );

        String finalJ = PokemonAPI.getRandomString(4)+new Random().nextInt(9);
        Button CreateHomeButton = new Button(CreateHome, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                Bukkit.dispatchCommand(player,("sethome "+ finalJ));
                new HomeMenu(player).openInventory();
            }
        });
        this.setButton(j, CreateHomeButton);

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
