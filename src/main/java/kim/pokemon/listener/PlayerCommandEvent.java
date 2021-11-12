package kim.pokemon.listener;

import kim.pokemon.Main;
import kim.pokemon.util.ColorParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class PlayerCommandEvent implements Listener {

    @EventHandler
    public void PlayerOnCommand(PlayerCommandPreprocessEvent event){
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
            if (plugin.getDescription().getAuthors().contains("Red_Carl")){
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
}
