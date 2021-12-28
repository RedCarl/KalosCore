package kim.pokemon.kimexpand.onlinereward;

import kim.pokemon.Main;
import kim.pokemon.util.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Timestamp;
import java.util.HashMap;

public class PlayerOnlineReward {
    HashMap<Player, Timestamp> timestampHashMap = new HashMap<>();

    public PlayerOnlineReward(Plugin plugin){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p: Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("group.eevee")){
                        Main.econ.depositPlayer(p,200);
                        p.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您已经游玩了 &c10 &7分钟,获得了 &a100&f+&6100(伊布加成) &7卡洛币奖励."));
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else if (p.hasPermission("group.pikanium")){
                        Main.econ.depositPlayer(p,150);
                        p.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您已经游玩了 &c10 &7分钟,获得了 &a100&f+&e50(皮卡丘加成) &7卡洛币奖励."));
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        Main.econ.depositPlayer(p,100);
                        p.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您已经游玩了 &c10 &7分钟,获得了 &c100 &7卡洛币奖励."));
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }
                }
            }
        }.runTaskTimer(plugin,20*60*10,20*60*10);
    }
}
