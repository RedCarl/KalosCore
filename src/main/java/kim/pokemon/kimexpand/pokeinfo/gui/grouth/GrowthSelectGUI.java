package kim.pokemon.kimexpand.pokeinfo.gui.grouth;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.pokeinfo.gui.PokeInfoUpdate;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ExecutionException;

public class GrowthSelectGUI extends InventoryGUI {

    public GrowthSelectGUI(Player player, Pokemon pokemon) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 选择性格 &7("+pokemon.getLocalizedName()+"/"+pokemon.getGrowth().getLocalizedName()+")"), player, 6);

        int GrowthPoints = 5;

        int j = 0;
        for (EnumGrowth enumGrowth:EnumGrowth.values()) {
            ItemStack Growth = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_N_LUNARIZER") , ColorParser.parse("&3"+enumGrowth.getLocalizedName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o培养您宝可梦的体型,将 100% 培养该体型."),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7价格: &f"+GrowthPoints+" &7"+Data.SERVER_POINTS+""),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&c左键点击立即培养"));
            Button GrowthButton = new Button(Growth, type -> {
                if (type.isLeftClick()){
                    try {
                        if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=GrowthPoints){
                            Main.ppAPI.takeAsync(player.getUniqueId(), GrowthPoints);
                            pokemon.setGrowth(enumGrowth);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,pokemon);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,pokemon);
                            pokeInfoUpdate.openInventory();
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
            this.setButton(j, GrowthButton);
            j++;
        }

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回上级菜单."));
        Button CloseButton = new Button(Close, type -> {
            if (type.isLeftClick()) {
                PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,pokemon);
                pokeInfoUpdate.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }
}
