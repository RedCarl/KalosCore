package kim.pokemon.kimexpand.crazyauctions;

import kim.pokemon.Main;
import kim.pokemon.kimexpand.crazyauctions.api.FileManager;
import kim.pokemon.kimexpand.crazyauctions.api.FileManager.Files;
import kim.pokemon.kimexpand.crazyauctions.api.Messages;
import kim.pokemon.kimexpand.crazyauctions.controllers.GUI;
import kim.pokemon.kimexpand.crazyauctions.controllers.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CrazyAuctions implements Listener {

    public static FileManager fileManager = FileManager.getInstance();
    public static kim.pokemon.kimexpand.crazyauctions.api.CrazyAuctions crazyAuctions = kim.pokemon.kimexpand.crazyauctions.api.CrazyAuctions.getInstance();

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
    

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        if (player.getName().equalsIgnoreCase("BadBones69")) {
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                player.sendMessage(Methods.getPrefix() + Methods.color("&7Main.getInstance() server is running your Crazy Auctions Plugin. " + "&7It is running version &av" + Main.getInstance().getServer().getVersion() + "&7."));
            }, 40);
        }
    }
    
    private void startCheck() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), Methods::updateAuction, 20, 5 * 20);
    }
    

    
}