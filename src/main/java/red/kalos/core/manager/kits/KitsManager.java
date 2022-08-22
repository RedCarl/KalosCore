package red.kalos.core.manager.kits;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import red.kalos.core.Main;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class KitsManager implements Listener {

    public static boolean isGetKits(String kit,Player player){
        //读取配置文件
        File file = new File(Main.getInstance().getDataFolder(), "Kits/"+player.getUniqueId()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);

        switch (kit){
            case "皮卡丘月礼包":
                if (player.hasPermission("group.pikanium")){
                    if (data.get("皮卡丘月礼包.领取日期")==null||data.getInt("皮卡丘月礼包.领取日期")==0||data.getLong("皮卡丘月礼包.领取日期") < (PokemonAPI.getMonthDay()*1000)){
                        data.set("皮卡丘月礼包.领取日期",System.currentTimeMillis());
                        try {
                            data.save(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return true;
                    }
                }
                break;
            case "伊布月礼包":
                if (player.hasPermission("group.eevee")){
                    if (data.get("伊布月礼包.领取日期")==null||data.getInt("伊布月礼包.领取日期")==0||data.getLong("伊布月礼包.领取日期") < (PokemonAPI.getMonthDay()*1000)){
                        data.set("伊布月礼包.领取日期",System.currentTimeMillis());
                        try {
                            data.save(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return true;
                    }
                }
                break;
            default:
                return data.get(kit + ".领取日期") == null || data.getInt(kit + ".领取日期") == 0;
        }

        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7非常抱歉，您暂时不能领取该礼包！"));
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
        player.closeInventory();
        return false;
    }

    public static void onLoadKitsEvent(){
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                for (Player player: Bukkit.getOnlinePlayers()) {
//                    try {
//                        //读取配置文件
//                        File file = new File(Main.getInstance().getDataFolder(), "Kits/"+player.getUniqueId()+".yml");
//                        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
//                        data.set("清明节礼包.游戏时间",data.getInt("清明节礼包.游戏时间")+1);
//
//                        data.save(file);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }.runTaskTimer(Main.getInstance(),0,20);
    }

    public static void getKits(Player player,String id){
        //读取配置文件
        File file = new File(Main.getInstance().getDataFolder(), "Kits/"+player.getUniqueId()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set(id+".领取日期",System.currentTimeMillis());
        try {
            data.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onForgeEvent(final ForgeEvent forgeEvent) {
        //成功捕捉宝可梦
        if (forgeEvent.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture) {
            CaptureEvent.SuccessfulCapture event = (CaptureEvent.SuccessfulCapture) forgeEvent.getForgeEvent();
            Player player = Bukkit.getPlayer(event.player.getPersistentID());

            //读取配置文件
//            File file = new File(Main.getInstance().getDataFolder(), "Kits/"+player.getUniqueId()+".yml");
//            FileConfiguration data = YamlConfiguration.loadConfiguration(file);
//            data.set("清明节礼包.精灵捕捉",data.getInt("清明节礼包.精灵捕捉")+1);
//
//            try {
//                data.save(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
