package red.kalos.core.manager.kits;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import red.kalos.core.Main;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class KitsManager implements Listener {

    public static boolean isGetKits(String kit,Player player){
        //读取配置文件
        File file = new File(Main.getInstance().getDataFolder(), "Kits/"+player.getUniqueId()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);

        return data.get(kit + ".领取日期") == null || data.getInt(kit + ".领取日期") == 0;

//        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7非常抱歉，您暂时不能领取该礼包！"));
//        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//        player.closeInventory();
//        return false;
    }

    public static void getKits(Player player,String id){
        //读取配置文件
        File file = new File(Main.getInstance().getDataFolder(), "Kits/"+player.getUniqueId()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set(id+".领取日期",System.currentTimeMillis());
        try {
            data.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
