package red.kalos.core.manager.crazyauctions;

import red.kalos.core.Main;
import red.kalos.core.manager.crazyauctions.api.FileManager;
import red.kalos.core.manager.crazyauctions.api.Messages;
import red.kalos.core.manager.crazyauctions.controllers.GUI;
import red.kalos.core.manager.crazyauctions.controllers.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class CrazyAuctions implements Listener {

    public static FileManager fileManager = FileManager.getInstance();
    public static red.kalos.core.manager.crazyauctions.api.CrazyAuctions crazyAuctions = red.kalos.core.manager.crazyauctions.api.CrazyAuctions.getInstance();

    public void onEnable() {
        fileManager.logInfo(true).setup(Main.getInstance());
        crazyAuctions.loadCrazyAuctions();
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new GUI(), Main.getInstance());
        Methods.updateAuction();
        startCheck();
        Messages.addMissingMessages();
        new Metrics(Main.getInstance(), 4624); //Starts up bStats
    }

    public void onDisable() {
        int file = 0;
        Bukkit.getScheduler().cancelTask(file);
        FileManager.Files.DATA.saveFile();
    }
    
    private void startCheck() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), Methods::updateAuction, 20, 5 * 20);
    }
    

    
}