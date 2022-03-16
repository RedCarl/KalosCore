package kim.pokemon.kimexpand.pokespawn;

import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.util.ColorParser;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SpawnTime implements Listener {
    public static int second = Data.LEGENDARY_SPAWN;
    public static HashMap<Player,String> playerStringHashMap = new HashMap<>();
    //循环更新事件
    public static void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size()>0){
                    if (second==12000){
                        Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+second/60+" &7分钟后尝试出现."));
                    }
                    if (second==8000){
                        Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+second/60+" &7分钟后尝试出现."));
                    }
                    if (second==6000){
                        Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+second/60+" &7分钟后尝试出现."));
                    }
                    if (second==3000){
                        Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+second/60+" &7分钟后尝试出现."));
                    }
                    if (second==600){
                        Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+second/60+" &7分钟后尝试出现."));
                    }
                    if (second==60){
                        Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+second/60+" &7分钟后尝试出现."));
                    }
                    if (second<=10&&second>0){
                        Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7传说宝可梦将在 &c"+second+" &7秒后尝试出现."));
                    }

                    if (second==0){
                        double randomInt =  RandomUtils.nextDouble(0.000000000000000,100.000000000000000);

                        if(randomInt <= Bukkit.getOnlinePlayers().size() + 10.243365596046448){
                            WorldSpawnLegendary();
                        }else {
                            Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7很遗憾,大家与传说宝可梦擦肩而过~"));
                        }
                        second = Main.getInstance().getConfig().getInt("kimcore.LEGENDARY_SPAWN");
                    }else {
                        second--;
                    }
                }
            }

        }.runTaskTimer(Main.getInstance(),0,20);
    }

    /**
     * 神兽刷新模块
     */
    public static void WorldSpawnLegendary(){
        List<Player> players = new ArrayList<>();
        for (Player p:Bukkit.getOnlinePlayers()) {
            if (
                    p.getLocation().getWorld().getName().equals("world")||
                            p.getLocation().getWorld().getName().equals("DIM1")||
                            p.getLocation().getWorld().getName().equals("DIM-1")||
                            p.getLocation().getWorld().getName().equals("DIM72")||
                            p.getLocation().getWorld().getName().equals("DIM73")
            ){
                players.add(p);
            }
        }
        int i = new Random().nextInt(players.size());
        Player player = players.get(i);

        playerStringHashMap.put(player,null);

        Bukkit.broadcastMessage(ColorParser.parse("&8[&6&l!&8] &7正在尝试在玩家 &c"+players.get(i).getDisplayName()+" &7周围生成传说宝可梦..."));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"spawnlegendary "+players.get(i).getName());

        new BukkitRunnable() {
            @Override
            public void run() {
                if (playerStringHashMap.get(player)==null){
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    Bukkit.broadcastMessage(ColorParser.parse("&8[&c&l!&8] &7真遗憾,该玩家的地形不适合传说宝可梦的生成..."));
                }
                playerStringHashMap.remove(player);
            }
        }.runTaskLater(Main.getInstance(),20);
    }

}
