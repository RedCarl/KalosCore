package kim.pokemon.kimexpand.pokeinfo.gui.nature;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.pokeinfo.gui.PokeInfoUpdate;
import kim.pokemon.kimexpand.pokeinfo.gui.PokemonInfoMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ExecutionException;

public class NatureSelectGUI extends InventoryGUI {
    public NatureSelectGUI(Player player, Pokemon pokemon) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 选择性格 &7("+pokemon.getLocalizedName()+"/"+pokemon.getNature().getLocalizedName()+")"), player, 6);

        int NaturePoints = 25;

        int j = 0;
        for (EnumNature enumNature:EnumNature.values()) {
            ItemStack Nature = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RAGE_CANDY_BAR") , ColorParser.parse("&3"+enumNature.getLocalizedName()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(右键 更改) &c" + NaturePoints + " &7"+Data.SERVER_POINTS),
                    ColorParser.parse("&r")
            );
            Button NatureButton = new Button(Nature, type -> {
                if (type.isLeftClick()){
                    try {
                        if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=NaturePoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(), NaturePoints);
                                pokemon.setNature(enumNature);
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
            this.setButton(j, NatureButton);
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
