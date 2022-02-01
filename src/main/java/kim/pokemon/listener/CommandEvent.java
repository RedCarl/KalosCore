package kim.pokemon.listener;

import kim.pokemon.Main;
import kim.pokemon.database.PlayerEventDataSQLReader;
import kim.pokemon.entity.PlayerEventData;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CommandEvent implements Listener {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @EventHandler
    public void PlayerMessage(AsyncPlayerChatEvent asyncPlayerChatEvent){

    }

    @EventHandler
    public void PlayerOnCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        switch (event.getMessage().toLowerCase()){
            case "/version":
            case "/ver":
                getVersion(event.getPlayer());
                event.setCancelled(true);
                break;
            case "/plugins":
            case "/pl":
                getPlugins(event.getPlayer());
                event.setCancelled(true);
                break;
            case "/cd":
                MainMenu mainMenu = new MainMenu(event.getPlayer());
                mainMenu.openInventory();
                event.setCancelled(true);
                break;
            case "/gpay income":
                List<PlayerEventData> today = PlayerEventDataSQLReader.getPlayerEventDataTime("OrderShipEvent", Main.luckPerms.getServerName(),simpleDateFormat.format(System.currentTimeMillis()),simpleDateFormat.format(System.currentTimeMillis()+86400000L));
                List<PlayerEventData> yesterday = PlayerEventDataSQLReader.getPlayerEventDataTime("OrderShipEvent", Main.luckPerms.getServerName(),simpleDateFormat.format(System.currentTimeMillis()-86400000L),simpleDateFormat.format(System.currentTimeMillis()));
                List<PlayerEventData> this_month = PlayerEventDataSQLReader.getPlayerEventDataTime("OrderShipEvent", Main.luckPerms.getServerName(),this_month_head(),this_month_bottom());
                List<PlayerEventData> last_month = PlayerEventDataSQLReader.getPlayerEventDataTime("OrderShipEvent", Main.luckPerms.getServerName(),last_month_head(),last_month_bottom());

                player.sendMessage(ColorParser.parse("&r"));
                player.sendMessage(ColorParser.parse("&bKalos &f// &aRechargeSystem &7(1.0) "));
                player.sendMessage(ColorParser.parse("&r"));
                player.sendMessage(ColorParser.parse("&f# &c今日收益: &a"+getValue(today)+" &d/ &a"+getValue(today)*0.06+" &7[扣税]"));
                player.sendMessage(ColorParser.parse("&f# &c本月收益: &6"+getValue(this_month)+" &d/ &6"+getValue(this_month)*0.06+" &7[扣税]"));
                player.sendMessage(ColorParser.parse("&r"));
                player.sendMessage(ColorParser.parse("&f# &4昨日收益: &b"+getValue(yesterday)+" &d/ &b"+getValue(yesterday)*0.06+" &7[扣税]"));
                player.sendMessage(ColorParser.parse("&f# &4上月收益: &e"+getValue(last_month)+" &d/ &e"+getValue(last_month)*0.06+" &7[扣税]"));
                player.sendMessage(ColorParser.parse("&r"));
                event.setCancelled(true);
                break;
        }

        if (event.getMessage().toLowerCase().contains("sethome")){
            if (event.getPlayer().getLocation().getWorld().getName().equals("spawn")){
                event.setCancelled(true);
                event.getPlayer().sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您不能在这个世界设置家."));
            }
        }

        if ("/eb".equals(event.getMessage())) {
            event.setCancelled(true);
            PokemonAPI.endBattle(event.getPlayer());
        }
    }

    /**
     * 获取服务器版本信息
     * @param player 玩家
     */
    public void getVersion(Player player){
        player.sendMessage(ColorParser.parse("&r"));
        player.sendMessage(ColorParser.parse("&3&lKimCore&8(forked Paper & Forge) &7服务端相关信息"));
        player.sendMessage(ColorParser.parse("&r &b| &7运行版本 &bgit-KimServer-1.12.2 (MC: 1.12.2)"));
        player.sendMessage(ColorParser.parse("&r &b| &7接口版本 &b") + Main.getInstance().getServer().getBukkitVersion());
        player.sendMessage(ColorParser.parse("&r &b| &f相关服务端更新请查询开发者内部构建服务器。"));
        player.sendMessage(ColorParser.parse("&r &b| &f相关插件状态查询请参考 &b/Plugins &f指令。"));
    }

    /**
     * 获取服务器插件列表
     * @param player 玩家
     */
    public void getPlugins(Player player){
        Plugin[] plugins = Main.getInstance().getServer().getPluginManager().getPlugins();
        player.sendMessage(ColorParser.parse("&r"));
        player.sendMessage(ColorParser.parse("&7您好,本服有 &f"+plugins.length+"&7 插件 &b(标有&fⒸ&b为原创)&7:"));
        StringBuilder pluginName = new StringBuilder();
        for (Plugin plugin:plugins) {
            if (plugin.getDescription().getAuthors().contains("Red_Carl")||plugin.getDescription().getAuthors().contains("asougi85")){
                pluginName.append("&b").append(plugin.getName()).append("&fⒸ&8, ");
            }
        }
        for (Plugin plugin:plugins) {
            if (!plugin.getDescription().getAuthors().contains("Red_Carl")){
                pluginName.append("&3").append(plugin.getName()).append("&7, ");
            }
        }
        player.sendMessage(ColorParser.parse(pluginName.substring(0,pluginName.length()-2)));
    }


    //计算综合
    public static double getValue(List<PlayerEventData> list){
        double i = 0.0;
        for (PlayerEventData p:list) {
            i+=Double.parseDouble(p.getValue());
        }
        return i;
    }


    //获取当前月的第一天
    public static String this_month_head() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);

        return simpleDateFormat.format(c.getTime());
    }
    //获取当前月最后一天
    public static String this_month_bottom(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return simpleDateFormat.format(c.getTime());

    }
    //获取上个月的第一天
    public static String last_month_head(){
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);

        return simpleDateFormat.format(c.getTime());
    }
    //获取上个月的最后一天
    public static String last_month_bottom(){
        Calendar c=Calendar.getInstance();
        int month=c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return simpleDateFormat.format(c.getTime());

    }
}
