package red.kalos.core.manager.plotadmin.gui;

import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.Sound;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InvitePlayerGui extends InventoryGUI {

    List<Button> Buttons = new ArrayList<>();

    public InvitePlayerGui(Player player, Plot plot,int slot) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 邀请玩家 &7("+plot.getId()+")"), player, 6);

        Buttons.clear();



        int a = 0;
        for (Player p:Bukkit.getOnlinePlayers()){
            getPlayerSkull(player,a);
            a++;
        }


        //设置当前页面的按钮
        for (int j = 0; j < 36; j++) {
            if(Buttons.size()==(36*slot)+j){
                break;
            }
            this.setButton(j,Buttons.get((36*slot)+j));
        }

        //翻页系统
        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }

        int amountListSize = Buttons.size()/36;
        if (Buttons.size()%36!=0){
            amountListSize++;
        }

        for (int i = 0; i < amountListSize; i++) {
            ItemStack A = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)5,ColorParser.parse("&a第"+(i+1)+"页"));
            if (i==slot){
                A = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)14,ColorParser.parse("&a第"+(i+1)+"页"));
            }
            int finalI = i;
            Button AButton = new Button(A, type -> {
                if (type.isLeftClick()) {
                    PlotMenu plotMenu = new PlotMenu(player, finalI);
                    plotMenu.openInventory();
                }
            });
            this.setButton(36+i,AButton);
        }



        //返回地皮管理
        ItemStack Close = SkullAPI.getSkullItem("865426a33df58b465f0601dd8b9bec3690b2193d1f9503c2caab78f6c2438",
                ColorParser.parse("&c返回"),
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

    private void getPlayerSkull(Player p, int i){
        ItemStack Member = SkullAPI.getPlayerSkull(
                ColorParser.parse("&f"+ p.getName())
        );
        Button MemberButton = new Button(Member, type -> {
            if (p.isOnline()){
                if (type.isLeftClick()) {
                }
                if (type.isRightClick()){
                }
            }else {
                p.closeInventory();
                p.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，这个玩家已经离线了，暂时无法邀请他。"));
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
            }

        });
        this.setButton(i, MemberButton);
    }

    private Button getPlot(Plot plot,Player player,String lore,String url){
        ItemStack Plot = SkullAPI.getSkullItem(""+url,
                ColorParser.parse("&7"+player.getName()),
                ColorParser.parse(lore),
                ColorParser.parse("&r"),
                ColorParser.parse("&c左键添加 &7| &c右键管理")
        );
        return new Button(Plot, type -> {
            if (player.isOnline()){
                if (type.isLeftClick()) {
                    player.performCommand("plot tp "+plot.getId());
                }
                if (type.isRightClick()){
                    player.performCommand("plot tp "+plot.getId());
                }
            }else {
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，这个玩家已经离线了，暂时无法邀请他。"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
            }
        });
    }
}
