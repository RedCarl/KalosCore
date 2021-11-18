package kim.pokemon.kimexpand;

import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.spawning.LegendarySpawner;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import kim.pokemon.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PokemonSpawnTime implements Listener {
    static BossBar bossBar;

    public PokemonSpawnTime() {
    }



    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        bossBar.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event) {
        bossBar.removePlayer(event.getPlayer());
    }

    //循环更新事件
    public static void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                PokemonSpawnTime.bossBar = Bukkit.createBossBar("§fKim §c传说宝可梦 §f将在 §c" + getTime() + " §f分钟后尝试刷新.", BarColor.BLUE, BarStyle.SOLID);
            }
        }.runTaskTimer(Main.getInstance(),0,60);
    }

    public static AbstractSpawner abstractSpawner() {
        return PixelmonSpawning.coordinator.getSpawner("legendary");
    }

    public static int getTime() {
        if (abstractSpawner() instanceof LegendarySpawner) {
            long l1 = ((LegendarySpawner)abstractSpawner()).nextSpawnTime;
            long l2 = System.currentTimeMillis();
            return Double.valueOf((double)(l1 - l2) / 1000.0D).intValue() / 60;
        } else {
            return -1;
        }
    }
}
