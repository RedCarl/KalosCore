package kim.pokemon;

import kim.pokemon.listener.PlayerCommandEvent;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.listener.ButtonClickListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    public static Plugin getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        log(getName() + " " + getDescription().getVersion() + " &7开始加载...");

        long startTime = System.currentTimeMillis();

        log("正在注册监听器...");
        regListener(new ButtonClickListener());
        regListener(new PlayerCommandEvent());

        log("加载完成 ，共耗时 " + (System.currentTimeMillis() - startTime) + " ms 。");

        showAD();
    }

    @Override
    public void onDisable() {
        log(getName() + " " + getDescription().getVersion() + " 开始卸载...");
        long startTime = System.currentTimeMillis();

        log("卸载监听器...");
        Bukkit.getServicesManager().unregisterAll(this);

        log("卸载完成 ，共耗时 " + (System.currentTimeMillis() - startTime) + " ms 。");

        showAD();
    }
    /**
     * 注册监听器
     *
     * @param listener 监听器
     */
    public static void regListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getInstance());
    }

    /**
     * 日志
     * @param message 日志消息
     */
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ColorParser.parse("[" + getInstance().getName() + "] " + message));
    }

    /**
     * 作者信息
     */
    private void showAD() {
        log("&7感谢您使用 &c&lKimMissionCore v" + getDescription().getVersion());
        log("&7本插件由 &c&lKim Studios &7提供长期支持与维护。");
    }
}
