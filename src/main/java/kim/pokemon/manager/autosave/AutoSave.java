package kim.pokemon.manager.autosave;

import kim.pokemon.Main;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public class AutoSave implements Listener {
    private final HashMap<String, Integer> playerMap = new HashMap<>();


    public static void onEnable() {
        int delay = 1200;
        new WorldThread().runTaskTimer(Main.getInstance(), delay, delay);
    }

    public static void onDisable() {
        List<World> worlds = Main.getInstance().getServer().getWorlds();
        for (World world : worlds){
            world.save();
        }
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String key = player.getUniqueId().toString();
        Integer object = new PlayerThread(player).runTaskTimer(Main.getInstance(), 6000L, 6000).getTaskId();
        this.playerMap.put(key, object);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String key = player.getUniqueId().toString();
        boolean containsKey = this.playerMap.containsKey(key);
        if (containsKey) {
            Integer id = this.playerMap.get(key);
            this.playerMap.remove(key);
            Main.getInstance().getServer().getScheduler().cancelTask(id);
        }
    }

    //玩家数据保存线程
    private static class PlayerThread extends BukkitRunnable {
        private final Player player;
        public PlayerThread(Player player) {
            this.player = player;
        }
        public void run() {
            this.player.saveData();
        }
    }

    //地图数据保存线程
    private static class WorldThread extends BukkitRunnable {
        private int id = 0;
        public void run() {
            List<World> worlds = Main.getInstance().getServer().getWorlds();
            if (this.id < worlds.size()) {
                worlds.get(this.id).save();
                for (Chunk chunk : worlds.get(this.id).getLoadedChunks())
                    chunk.unload(false, true);
                this.id++;
            } else {
                this.id = 0;
                run();
            }
        }
    }

}
