package red.kalos.core.manager.pokespawn;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.spawning.LegendarySpawner;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import red.kalos.core.Main;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.KalosUtil;

public class SpawnTime {


    private static final SpawnTime instance = new SpawnTime();

    public static SpawnTime getInstance() {
        return instance;
    }

    //循环更新事件
    boolean isSpawner = false;

    public void setSpawner(Player player, Location location, Pokemon pokemon){
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
        Bukkit.broadcastMessage(ColorParser.parse("&r"));
        Bukkit.broadcastMessage(ColorParser.parse("&r"));
        Bukkit.broadcastMessage(ColorParser.parse("&7------ [ &6传奇宝可梦 &7] ------"));
        Bukkit.broadcastMessage(ColorParser.parse("&7宝可梦名称: &c"+pokemon.getLocalizedName()));
        Bukkit.broadcastMessage(ColorParser.parse("&7附近的玩家: &a"+player.getName()));
        Bukkit.broadcastMessage(ColorParser.parse("&7宝可梦坐标: &f"+location.getBlockX()+"&7,&f"+location.getBlockY()+"&7,&f"+location.getBlockZ()));
        Bukkit.broadcastMessage(ColorParser.parse("&r"));
        Bukkit.broadcastMessage(ColorParser.parse("&r"));
        isSpawner=true;
    }

    public void init() {
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
                    }.runTaskLaterAsynchronously(Main.getInstance(),80);
                }

            }

        }.runTaskTimerAsynchronously(Main.getInstance(),20,20);
    }



}
