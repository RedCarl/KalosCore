package red.kalos.core.manager.worldtime;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import red.kalos.core.Main;

/**
 * @Author: carl0
 * @DATE: 2022/8/4 20:44
 */
public class WorldTimeSynchronize {
    private static final WorldTimeSynchronize instance = new WorldTimeSynchronize();

    public static WorldTimeSynchronize getInstance() {
        return instance;
    }

    public void init(){
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world");
                for (World w:Main.getInstance().getServer().getWorlds()) {
                    if (!w.getName().equals("world")){
                        w.setTime(world.getTime());
                        w.setStorm(world.hasStorm());
                        w.setWeatherDuration(world.getWeatherDuration());
                        w.setThundering(world.isThundering());
                        w.setThunderDuration(world.getThunderDuration());
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(),20,20);

        for (World w:Main.getInstance().getServer().getWorlds()) {
            w.getWorldBorder().setCenter(new Location(w,0,0,0));
            w.getWorldBorder().setSize(20000);
        }
    }
}
