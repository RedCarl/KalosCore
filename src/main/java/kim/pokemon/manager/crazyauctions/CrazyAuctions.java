package kim.pokemon.manager.crazyauctions;

import kim.pokemon.Main;
import kim.pokemon.manager.crazyauctions.api.FileManager;
import kim.pokemon.manager.crazyauctions.api.FileManager.Files;
import kim.pokemon.manager.crazyauctions.api.Messages;
import kim.pokemon.manager.crazyauctions.controllers.GUI;
import kim.pokemon.manager.crazyauctions.controllers.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class CrazyAuctions implements Listener {

    public static FileManager fileManager = FileManager.getInstance();
    public static kim.pokemon.manager.crazyauctions.api.CrazyAuctions crazyAuctions = kim.pokemon.manager.crazyauctions.api.CrazyAuctions.getInstance();

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
        Files.DATA.saveFile();
    }
    
    private void startCheck() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), Methods::updateAuction, 20, 5 * 20);
    }
    

    
}