package red.kalos.core.manager.recharge.gui;

import red.kalos.core.configFile.Data;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.InventoryGUI;
import org.bukkit.entity.Player;

public class PokemonSelect extends InventoryGUI {
    public PokemonSelect(Player player,String...pokemon) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 宝可梦自选"), player, 6);

    }
}
