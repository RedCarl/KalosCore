package kim.pokemon.manager.pokespawn;

import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.util.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SpawnTime {
    //循环更新事件
    public static void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long nextSpawnTime = PixelmonSpawning.legendarySpawner.nextSpawnTime;
                nextSpawnTime=nextSpawnTime/1000;
                System.out.println(nextSpawnTime);
                if (nextSpawnTime==12000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+nextSpawnTime/60+" &7分钟后尝试出现."));
                }
                if (nextSpawnTime==8000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+nextSpawnTime/60+" &7分钟后尝试出现."));
                }
                if (nextSpawnTime==6000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+nextSpawnTime/60+" &7分钟后尝试出现."));
                }
                if (nextSpawnTime==3000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+nextSpawnTime/60+" &7分钟后尝试出现."));
                }
                if (nextSpawnTime==600){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+nextSpawnTime/60+" &7分钟后尝试出现."));
                }
                if (nextSpawnTime==60){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+nextSpawnTime/60+" &7分钟后尝试出现."));
                }
                if (nextSpawnTime<=10&&nextSpawnTime>0){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+nextSpawnTime+" &7秒后尝试出现."));
                }
            }

        }.runTaskTimer(Main.getInstance(),0,20);
    }

}
