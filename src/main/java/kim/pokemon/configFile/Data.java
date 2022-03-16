package kim.pokemon.configFile;

import kim.pokemon.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Data {
    static ConfigurationSection mysqlConfig = Main.getInstance().getConfig().getConfigurationSection("mysql");
    static ConfigurationSection kimConfig = Main.getInstance().getConfig().getConfigurationSection("kimcore");

    //MYSQL Info
    public static final String DRIVER = mysqlConfig.getString("DRIVER");
    public static final String URL = mysqlConfig.getString("URL");
    public static final String PORT  = mysqlConfig.getString("PORT");
    public static final String DATABASE  = mysqlConfig.getString("DATABASE");
    public static final String USER  = mysqlConfig.getString("USER");
    public static final String PASS  = mysqlConfig.getString("PASS");

    public static final String GLAZED_PAY_DATA  = mysqlConfig.getString("GLAZED_PAY_DATA");
    public static final String POKEMON_BAN_DATA  = mysqlConfig.getString("POKEMON_BAN_DATA");
    public static final String PREMIUM_DATA  = mysqlConfig.getString("PREMIUM_DATA");
    public static final String PLAYER_EVENT_DATA  = mysqlConfig.getString("PLAYER_EVENT_DATA");

    //ARMOURERS_PATH
    public static final String ARMOURERS_PATH = kimConfig.getString("ARMOURERS_PATH");
    public static final String SERVER_NAME = kimConfig.getString("SERVER_NAME");
    public static final String SERVER_NAME_CN = kimConfig.getString("SERVER_NAME_CN");
    public static final String SERVER_VAULT = kimConfig.getString("SERVER_VAULT");
    public static final String SERVER_POINTS = kimConfig.getString("SERVER_POINTS");
    public static final String SERVER_RMB = kimConfig.getString("SERVER_RMB");
    public static final int LEGENDARY_SPAWN = Main.getInstance().getConfig().getInt("kimcore.LEGENDARY_SPAWN");

    //首次充值翻倍活动
    public static final boolean POINTS_ACTIVITY = false;

    //自定义皮肤系统
    public static List<String> CUSTOM_SKIN = new ArrayList<>();

}
