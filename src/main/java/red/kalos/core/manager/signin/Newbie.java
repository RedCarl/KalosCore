//package red.kalos.core.manager.signin;
//
//import red.kalos.core.Main;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class Newbie {
//    static long daytime = 0;
//    static List<Long> signDate = new ArrayList<>();
//
//    public static void loadPlayerSignData(Player player){
//        //当天日期
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String today = simpleDateFormat.format(date.getTime());
//        try {
//            daytime = simpleDateFormat.parse(today).getTime();
//        } catch (ParseException ignored) {}
//
//        //读取配置文件
//        File file = new File(Main.getInstance().getDataFolder(), "Newbie/"+player.getUniqueId()+".yml");
//        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
//
//        signDate = data.getLongList("signDate");
//
//        //判断玩家是否已经有数据
//        if (signDate.size()==0){
//            //预测签到日期
//            for (int i = 0; i < 7; i++) {
//                signDate.add(daytime+(i*86400000));
//            }
//
//            //保存文件
//            try {
//                data.set("signDate",signDate);
//                data.save(file);
//            } catch (IOException ignored) {}
//        }
//
//        //判断玩家是否有资格领取新手礼包
//        if (signDate.contains(daytime)){
//            SignGUI signGUI = new SignGUI(player,signDate,daytime);
//            signGUI.openInventory();
//        }
//    }
//
//    public static void sign(Player player,long daytime){
//        //读取配置文件
//        File file = new File(Main.getInstance().getDataFolder(), "Newbie/"+player.getUniqueId()+".yml");
//        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
//
//        signDate = data.getLongList("signDate");
//
//        for (int i = 0; i < signDate.size(); i++) {
//            if (signDate.get(i)==daytime){
//                signDate.set(i, 0L);
//            }
//        }
//
//        try {
//            data.set("signDate",signDate);
//            data.save(file);
//        } catch (IOException ignored) {}
//
//        SignGUI signGUI = new SignGUI(player,signDate,daytime);
//        signGUI.openInventory();
//    }
//}
