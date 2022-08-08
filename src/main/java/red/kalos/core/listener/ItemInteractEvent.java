package red.kalos.core.listener;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import red.kalos.core.Main;
import red.kalos.core.manager.optional.OptionalGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class ItemInteractEvent implements Listener {


    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player player = event.getPlayer();
            ItemStack i = event.getItem();
            if (i!=null){
                NBTItem nbtItem = new NBTItem(i);
                if (nbtItem.getString("id")!=null){
                    File file = new File(Main.getInstance().getDataFolder(), "EncryptionItems/"+nbtItem.getString("id")+".kalos");
                    FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                    if (data.getBoolean("kalos")){
                        OptionalGUI optionalGUI = new OptionalGUI(player,data.getString("type"));
                        optionalGUI.openInventory();
                        event.setCancelled(true);
                    }
                }



            }

        }
    }
}
