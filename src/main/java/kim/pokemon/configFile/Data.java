package kim.pokemon.configFile;

import kim.pokemon.Main;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class Data {
    static ConfigurationSection kimConfig = Main.getInstance().getConfig().getConfigurationSection("kimcore");

    //ARMOURERS_PATH
    public static final String ARMOURERS_PATH = kimConfig.getString("ARMOURERS_PATH");
    public static final String SERVER_NAME = kimConfig.getString("SERVER_NAME");
    public static final String SERVER_NAME_CN = kimConfig.getString("SERVER_NAME_CN");
    public static final String SERVER_VAULT = kimConfig.getString("SERVER_VAULT");
    public static final String SERVER_POINTS = kimConfig.getString("SERVER_POINTS");
    public static final String SERVER_RMB = kimConfig.getString("SERVER_RMB");
    public static final int LEGENDARY_SPAWN = kimConfig.getInt("LEGENDARY_SPAWN");

    //自定义皮肤系统
    public static List<String> CUSTOM_SKIN = new ArrayList<>();

}
