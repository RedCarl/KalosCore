package kim.pokemon.manager.recharge.gui;

import kim.pokemon.configFile.Data;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.InventoryGUI;
import org.bukkit.entity.Player;

public class PokemonSelect extends InventoryGUI {
    public PokemonSelect(Player player,String...pokemon) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 宝可梦自选"), player, 6);

    }
}
