package kim.pokemon.database;


import kim.pokemon.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class BanItemManager {

    private static FileConfiguration data = Main.getInstance().getConfig();

    public static List<String> getBanDrops() {
        return data.getStringList("ItemBanList");
    }
}
