package red.kalos.core.manager.pokespawn;

import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.spawning.LegendarySpawner;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import red.kalos.core.Main;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.KalosUtil;

public class SpawnTime {
    //循环更新事件
    public static boolean isSpawner = false;
    public static void start() {
        new BukkitRunnable() {
            @Override
            public void run() {

                if (KalosUtil.getLegendarySpawnerTime()==12000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+ KalosUtil.getLegendarySpawnerTime()/60+" &7分钟后尝试刷新。"));
                }else if (KalosUtil.getLegendarySpawnerTime()==8000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+KalosUtil.getLegendarySpawnerTime()/60+" &7分钟后尝试刷新。"));
                }else if (KalosUtil.getLegendarySpawnerTime()==6000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+KalosUtil.getLegendarySpawnerTime()/60+" &7分钟后尝试刷新。"));
                }else if (KalosUtil.getLegendarySpawnerTime()==3000){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+KalosUtil.getLegendarySpawnerTime()/60+" &7分钟后尝试刷新。"));
                }else if (KalosUtil.getLegendarySpawnerTime()==600){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+KalosUtil.getLegendarySpawnerTime()/60+" &7分钟后尝试刷新。"));
                }else if (KalosUtil.getLegendarySpawnerTime()==60){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+KalosUtil.getLegendarySpawnerTime()/60+" &7分钟后尝试刷新。"));
                }else if (KalosUtil.getLegendarySpawnerTime()<=10&&KalosUtil.getLegendarySpawnerTime()>1){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+KalosUtil.getLegendarySpawnerTime()+" &7秒后尝试刷新。"));
                }else if (KalosUtil.getLegendarySpawnerTime()==1){
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传奇宝可梦将在 &c"+KalosUtil.getLegendarySpawnerTime()+" &7秒后尝试刷新。"));
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!isSpawner){
                                Bukkit.broadcastMessage(ColorParser.parse("&8[&c&l!&8] &7很遗憾，并没有传奇宝可梦出现在世界上。"));
                            }
                        }
                    }.runTaskLaterAsynchronously(Main.getInstance(),120);
                }

            }

        }.runTaskTimerAsynchronously(Main.getInstance(),0,20);
    }



}
