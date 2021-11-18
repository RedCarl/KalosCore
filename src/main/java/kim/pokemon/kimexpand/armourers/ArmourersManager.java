package kim.pokemon.kimexpand.armourers;

import com.google.common.collect.Lists;
import kim.pokemon.Main;
import kim.pokemon.configFile.GlazedPay.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

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
        String armourers = cfg.getString(name);
        List<String> list = getArmourers(armourers);
        if(p.hasPermission("kim.armourers."+armourers)){
            return list;
        }else{
            return Lists.newArrayList();
        }
    }

    public static void setPlayerArmourers(Player p,String skin){
        String name = p.getName();
        FileConfiguration cfg = Main.getInstance().getConfig();
        cfg.set(name,skin);
    }
}
