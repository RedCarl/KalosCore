package kim.pokemon.kimexpand.armourers;

import com.google.common.collect.Lists;
import eos.moe.armourers.api.DragonAPI;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.armourers.guis.ArmourersGUI;
import kim.pokemon.kimexpand.recharge.ArmourersShop;
import kim.pokemon.util.ColorParser;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArmourersManager {

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
        FileConfiguration cfg = Main.getInstance().getConfig();
        String armourers = cfg.getString("KimArmourers."+name);
        List<String> list = getArmourers(armourers);
        if(p.hasPermission("kim.armourers."+armourers)){
            return list;
        }else{
            return list;
        }
    }

    public static void setPlayerArmourers(Player p,String skin){
        String name = p.getName();
        FileConfiguration cfg = Main.getInstance().getConfig();
        cfg.set("KimArmourers."+name,skin);
        Main.getInstance().saveConfig();
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
                FileConfiguration cfg = Main.getInstance().getConfig();
                cfg.set("KimArmourers."+name,null);
                Main.getInstance().saveConfig();
                DragonAPI.updatePlayerSkin(p);
                ArmourersShop.playerLongHashMap.remove(p);
                p.sendMessage(ColorParser.parse("&8[&c&l!&8] &7试穿已经结束,恢复默认状态."));
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        }.runTaskLater(Main.getInstance(), 20L *temp);
    }
}
