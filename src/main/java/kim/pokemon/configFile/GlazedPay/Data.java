package kim.pokemon.configFile.GlazedPay;

import kim.pokemon.Main;
import org.bukkit.configuration.ConfigurationSection;

public class Data {
    static ConfigurationSection mysqlConfig = Main.getInstance().getConfig().getConfigurationSection("mysql");

    //MYSQL Info
    public static final String DRIVER = mysqlConfig.getString("DRIVER");
    public static final String URL = mysqlConfig.getString("URL");
    public static final String PORT  = mysqlConfig.getString("PORT");
    public static final String DATABASE  = mysqlConfig.getString("DATABASE");
    public static final String USER  = mysqlConfig.getString("USER");
    public static final String PASS  = mysqlConfig.getString("PASS");

    public static final String GLAZED_PAY_DATA  = mysqlConfig.getString("GLAZED_PAY_DATA");
    public static final String POKEMON_BAN_DATA  = mysqlConfig.getString("POKEMON_BAN_DATA");
}
