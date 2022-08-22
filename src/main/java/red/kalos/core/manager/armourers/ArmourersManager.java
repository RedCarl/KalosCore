package red.kalos.core.manager.armourers;

import com.google.common.collect.Lists;
import eos.moe.armourers.api.DragonAPI;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.util.ColorParser;
import red.kalos.core.manager.armourers.guis.ArmourersShop;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArmourersManager {
    static File file = new File(Main.getInstance().getDataFolder(), "KimArmourers/config.yml");

    public static List<String> getArmourers(String path){ //获取对应文件夹下所有皮肤
        if(path == null){
            return Lists.newArrayList();
        }
        File f = new File(Data.ARMOURERS_PATH,path);
        if(f.exists()&&f.isDirectory()){
            return Arrays.stream(Objects.requireNonNull(f.list())).filter(s->s.endsWith("armour")).map(s->s.split("\\.")[0]).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    public static List<String> getPlayerArmourers(Player p){
        String name = p.getName();

        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        String armourers = cfg.getString("KimArmourers."+name);
        return getArmourers(armourers);
    }

    public static void setPlayerArmourers(Player p,String skin){
        String name = p.getName();
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("KimArmourers."+name,skin);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerTempArmourers(Player p,String skin,int temp){
        //穿上时装
        ArmourersShop.playerLongHashMap.put(p,System.currentTimeMillis());
        setPlayerArmourers(p,skin);
        DragonAPI.updatePlayerSkin(p);

        //记录玩家ID
        String name = p.getName();
        //30秒后执行
        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                cfg.set("KimArmourers."+name,null);
                try {
                    cfg.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DragonAPI.updatePlayerSkin(p);
                ArmourersShop.playerLongHashMap.remove(p);
                p.sendMessage(ColorParser.parse("&8[&c&l!&8] &7试穿已经结束,恢复默认状态."));
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        }.runTaskLaterAsynchronously(Main.getInstance(), 20L *temp);
    }
}
