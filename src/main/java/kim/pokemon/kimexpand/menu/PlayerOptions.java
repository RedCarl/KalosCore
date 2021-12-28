package kim.pokemon.kimexpand.menu;

import kim.pokemon.configFile.Data;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.InventoryGUI;
import org.bukkit.entity.Player;

public class PlayerOptions extends InventoryGUI {
    public PlayerOptions(Player player) {
        super(ColorParser.parse("&0"+Data.SERVER_NAME+" / 玩家信息"), player, 6);
    }
}
