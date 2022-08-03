package kim.pokemon.manager.questmanager.manager;

import kim.pokemon.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class ConfigManager {



    public static void initialize() {
        Main.getInstance().getDataFolder().mkdirs();
        new File(Main.getInstance().getDataFolder(),"quest").mkdirs();
    }

    public static FileConfiguration getConfig() {
        return Main.getInstance().getConfig();
    }

    public static YamlConfiguration getQuest(String name) {
        File f = new File(Main.getInstance().getDataFolder(), "quest/"+name+".yml");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(f);
        return configuration;
    }

    public static void saveQuest(YamlConfiguration yaml,String name){
        File f = new File(Main.getInstance().getDataFolder(), "quest/"+name+".yml");
        try {
            yaml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQuest(String name){
        File f = new File(Main.getInstance().getDataFolder(), "quest/"+name+".yml");
        f.delete();
    }

}
