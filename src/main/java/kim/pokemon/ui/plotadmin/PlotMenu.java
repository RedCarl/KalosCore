package kim.pokemon.ui.plotadmin;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.plotsquared.bukkit.object.BukkitPlayer;
import kim.pokemon.ui.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlotMenu extends InventoryGUI {
    public PlotMenu(Player player) {
        super(ColorParser.parse("&0Kim / 地皮管理"), player, 6);

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        PlotPlayer plotPlayer = new BukkitPlayer(player);

        if (plotPlayer.getPlots().size()==0&&plotPlayer.getAllowedPlots()==0){
            ItemStack CreatePlot = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ac4970ea91ab06ece59d45fce7604d255431f2e03a737b226082c4cce1aca1c4",
                    ColorParser.parse("&a创建地皮"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o您当前没有地皮,您可以创建一个地皮使用."),
                    ColorParser.parse("&7&o也可以站在您喜欢的地皮上输入 &a/plot claim &7&o来领取它.")
            );
            Button CreatePlotButton = new Button(CreatePlot, type -> {
                if (type.isLeftClick()) {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player,"plot auto");
                }
            });
            this.setButton(13, CreatePlotButton);
        }else {
            int i=0;
            PlotAPI plotAPI = new PlotAPI();

            for (Plot plot: plotPlayer.getPlots("plot")) {
                getPlot(plot,player,i," &c[主人]","&7&o这是您的地皮,您可以对其进行操作.","c5a35b5ca15268685c4660535e5883d21a5ec57c55d397234269acb5dc2954f");
                i++;
            }

            for (Plot plot:plotAPI.getAllPlots()){
                if (plot.getTrusted().contains(player.getUniqueId())){
                    getPlot(plot,player,i," &a[居民]","&7&o您是这个地皮的 &a居民 &7&o可以自由的建造.","de678e3a96bb322ff7d99b287e6903ac018283a8c68d7f6cad3d8f1c22b60");
                    i++;
                }

            }
            for (Plot plot:plotAPI.getAllPlots()){
                if (plot.getMembers().contains(player.getUniqueId())){
                    getPlot(plot,player,i," &2[成员]","&7&o您是这个地皮的 &2成员 &7&o您只能在 &c主人 &7&o在线时进行建造.","2fb3a0e5dea915a224f86c528d414ec0f1db6f47cdb9718775ad3a93418fe24");
                    i++;
                }
            }



            ItemStack CreatePlot = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ac4970ea91ab06ece59d45fce7604d255431f2e03a737b226082c4cce1aca1c4",
                    ColorParser.parse("&a创建地皮 &7("+plotPlayer.getPlots().size()+"/"+plotPlayer.getAllowedPlots()+")"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o您可以站在您喜欢的地皮上输入 &a/plot claim &7&o来领取它.")
            );
            Button CreatePlotButton = new Button(CreatePlot, type -> {
                if (type.isLeftClick()) {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player,"plot auto");
                }
            });
            this.setButton(i, CreatePlotButton);
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

    private void getPlot(Plot plot,Player player,int i,String identity,String lore,String url){
        ItemStack Plot = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/"+url,
                ColorParser.parse("&fID: "+plot.getId()+identity),
                ColorParser.parse(lore),
                ColorParser.parse("&r"),
                ColorParser.parse("&c左键传送 &7| &c右键管理")
        );
        Button PlotPlotButton = new Button(Plot, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.performCommand("plot tp "+plot.getId());
            }
            if (type.isRightClick()){
                PlotAdmin plotAdmin = new PlotAdmin(player,plot);
                plotAdmin.openInventory();
            }
        });
        this.setButton(i, PlotPlotButton);
    }
}
