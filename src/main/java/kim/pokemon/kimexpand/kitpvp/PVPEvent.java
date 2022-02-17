package kim.pokemon.kimexpand.kitpvp;

import com.Zrips.CMI.events.CMIEventCommandEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PVPEvent implements Listener {

    //更改世界
    @EventHandler
    public void PlayerDeathEvent(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        if (event.getPlayer().getLocation().getWorld().getName().equals("pvp")){
            player.setFlying(false);
        }
    }

    //更改世界
    @EventHandler
    public void PlayerDeathEvent(CMIEventCommandEvent event){
        if (event.getCommands().contains("fly")){
            event.setCancelled(false);
        }
    }

    //玩家死亡
    @EventHandler
    public void PlayerDeathEvent(EntityDamageByEntityEvent event){
        if (event.getEntity().getType().equals(EntityType.PLAYER)){
            if (!event.getEntity().getLocation().getWorld().getName().equals("pvp")){
                event.setCancelled(true);
            }
        }
    }

    //玩家重生
    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        Location location = new Location(Bukkit.getWorld("pvp"), -120.5,88.5,-52.5);

        if (player.getLocation().getWorld().getName().equals("pvp")){
            event.setRespawnLocation(location);
        }
    }


}
