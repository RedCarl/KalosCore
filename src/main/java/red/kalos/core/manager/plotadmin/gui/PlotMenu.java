package red.kalos.core.manager.plotadmin.gui;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.plotsquared.bukkit.object.BukkitPlayer;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.util.gui.inventory.SkullAPI;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlotMenu extends InventoryGUI {
    List<Button> Buttons = new ArrayList<>();
    public PlotMenu(Player player,int slot) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 地皮管理"), player, 6);

        Buttons.clear();

        List<Button> Owners = new ArrayList<>();
        List<Button> Trusted = new ArrayList<>();
        List<Button> Members = new ArrayList<>();

        //该玩家的信息
        PlotPlayer plotPlayer = new BukkitPlayer(player);

        //遍历所有地皮
        PlotAPI plotAPI = new PlotAPI();
        int a=0;

        for (Plot plot:plotAPI.getAllPlots()){
            if (plot.getOwners().contains(player.getUniqueId())){
                Owners.add(getPlot(plot,player,a," &c[主人]","&7&o这是您的地皮,您可以对其进行操作.","c5a35b5ca15268685c4660535e5883d21a5ec57c55d397234269acb5dc2954f"));
            }else if (plot.getTrusted().contains(player.getUniqueId())){
                Trusted.add(getPlot(plot,player,a," &a[居民]","&7&o您是这个地皮的 &a居民 &7&o可以自由的建造.","de678e3a96bb322ff7d99b287e6903ac018283a8c68d7f6cad3d8f1c22b60"));
            }else if (plot.getMembers().contains(player.getUniqueId())){
                Members.add(getPlot(plot,player,a," &2[成员]","&7&o您是这个地皮的 &2成员 &7&o您只能在 &c主人 &7&o在线时进行建造.","2fb3a0e5dea915a224f86c528d414ec0f1db6f47cdb9718775ad3a93418fe24"));
            }
            a++;
        }

        //创建地皮按钮
        ItemStack CreatePlot = SkullAPI.getSkullItem("ac4970ea91ab06ece59d45fce7604d255431f2e03a737b226082c4cce1aca1c4",
                ColorParser.parse("&a创建地皮 &7("+plotPlayer.getPlots().size()+"/"+plotPlayer.getAllowedPlots()+")"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您可以站在您喜欢的地皮上输入 &a/plot claim &7&o来领取它.")
        );
        Button CreatePlotButton = new Button(CreatePlot, type -> {
            if (type.isLeftClick()) {
                Bukkit.dispatchCommand(player,"plot auto");
                player.closeInventory();
            }
        });

        Buttons.addAll(Owners);
        Buttons.addAll(Trusted);
        Buttons.addAll(Members);
        Buttons.add(CreatePlotButton);

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

    private Button getPlot(Plot plot,Player player,int i,String identity,String lore,String url){
        ItemStack Plot = SkullAPI.getSkullItem(""+url,
                ColorParser.parse("&fID: "+plot.getId()+identity),
                ColorParser.parse(lore),
                ColorParser.parse("&r"),
                ColorParser.parse("&c左键传送 &7| &c右键管理")
        );
        return new Button(Plot, type -> {
            if (type.isLeftClick()) {
                player.performCommand("plot tp "+plot.getId());
                player.closeInventory();
            }
            if (type.isRightClick()){
                AdminGui adminGui = new AdminGui(player,plot);
                adminGui.openInventory();
            }
        });
    }

}
