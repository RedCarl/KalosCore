package red.kalos.core.listener;

import red.kalos.core.Main;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.api.PokemonPhotoAPI;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class CommandEvent implements Listener {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @EventHandler
    public void PlayerOnCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        MainMenu mainMenu = new MainMenu(event.getPlayer());
        switch (event.getMessage().toLowerCase()){
            case "/cd":
                mainMenu.openInventory();
                event.setCancelled(true);
                break;
            case "/g":
                PokemonPhotoAPI.getFolder("KeyPressEvent/");
                File file = new File(Main.getInstance().getDataFolder(), "KeyPressEvent/"+player.getName() + ".yml");
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);

                if (config.getBoolean("G")){
                    config.set("G",false);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您已经 &a开启 &7了 &cG &7键菜单功能."));
                }else {
                    config.set("G",true);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您已经 &c关闭 &7了 &cG &7键菜单功能."));
                }
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);

                try {
                    config.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.setCancelled(true);
                break;
        }

        if (event.getMessage().contains("sethome")){
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


}
