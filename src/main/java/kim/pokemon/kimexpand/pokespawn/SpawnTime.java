package kim.pokemon.kimexpand.pokespawn;

import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.spawning.LegendarySpawner;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import kim.pokemon.Main;
import kim.pokemon.util.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnTime implements Listener {

    //循环更新事件
    public static void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getTime()==12000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+getTime()/60+" &7分钟后尝试出现."));
                }
                if (getTime()==8000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+getTime()/60+" &7分钟后尝试出现."));
                }
                if (getTime()==6000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+getTime()/60+" &7分钟后尝试出现."));
                }
                if (getTime()==3000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+getTime()/60+" &7分钟后尝试出现."));
                }
                if (getTime()==600){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+getTime()/60+" &7分钟后尝试出现."));
                }
                if (getTime()==60){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+getTime()/60+" &7分钟后尝试出现."));
                }
                if (getTime()<=10){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+getTime()+" &7秒后尝试出现."));
                }
            }
        }.runTaskTimer(Main.getInstance(),0,20);
    }

    public static AbstractSpawner abstractSpawner() {
        return PixelmonSpawning.coordinator.getSpawner("legendary");
    }

    public static int getTime() {
        try {
            if (abstractSpawner() instanceof LegendarySpawner) {
                long l1 = ((LegendarySpawner)abstractSpawner()).nextSpawnTime;
                long l2 = System.currentTimeMillis();
                return Double.valueOf((double)(l1 - l2) / 1000.0D).intValue();
            }
        }catch (NullPointerException ignored){}
        return -1;
    }
}
