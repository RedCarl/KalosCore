package red.kalos.core.manager.plotadmin.gui;

import com.intellectualcrafters.plot.object.Plot;
import red.kalos.core.configFile.Data;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class AdminGui extends InventoryGUI {
    public AdminGui(Player player, Plot plot) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 地皮管理 &7("+plot.getId()+")"), player, 6);

        int i=0;
        for (UUID uuid:plot.getTrusted()) {
            getPlayerSkull(uuid,i," &a居民");
            i++;
        }
        for (UUID uuid:plot.getMembers()) {
            getPlayerSkull(uuid,i," &2成员");
            i++;
        }

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int j = 0; j < 9; j++) {
            this.setButton(36+j, new Button(Line));
        }


        ItemStack Apply = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&a邀请玩家"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o邀请其它玩家进入您的地皮中."));
        Button ApplyButton = new Button(Apply, type -> {
        });
        this.setButton(45, ApplyButton);

        //删除地皮
        ItemStack Delete = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c删除地皮"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o删除这个地皮,这将会让地皮回到初始状态. &c(谨慎操作,不可逆转)"));
        Button DeleteButton = new Button(Delete, type -> {
            if (type.isLeftClick()){
                player.performCommand("plot tp "+plot.getId());
                Bukkit.dispatchCommand(player,"plot "+plot.getId()+" delete");
            }
        });
        this.setButton(46, DeleteButton);

        //返回地皮管理
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至地皮管理."));
        Button CloseButton = new Button(Close, type -> {
            if (type.isLeftClick()) {
                PlotMenu plotMenu = new PlotMenu(player,0);
                plotMenu.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }

    private void getPlayerSkull(UUID uuid, int i,String identity){
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
        ItemStack Member = SkullAPI.getPlayerSkull(
                ColorParser.parse("&f"+ p.getName() + identity)
        );
        Button MemberButton = new Button(Member, type -> {
            if (type.isLeftClick()) {
            }
            if (type.isRightClick()){
            }
        });
        this.setButton(i, MemberButton);
    }
}