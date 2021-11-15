package kim.pokemon.listener;

import kim.pokemon.ui.MainMenu;
import kim.pokemon.util.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public class PlayerEvent implements Listener {

    HashMap<Player, Location> playerLocationHashMap = new HashMap<>();

    /**
     * 蹲下+F 打开菜单
     * @param event 事件
     */
    @EventHandler
    public void PlayerInteractEvent(PlayerSwapHandItemsEvent event){
        if (event.getPlayer().isSneaking()){
            MainMenu mainMenu = new MainMenu(event.getPlayer());
            mainMenu.openInventory();
            event.setCancelled(true);
        }
    }

    /**
     * 玩家交互时的操作
     * @param event 事件
     */
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){

        //修复大部分需要两人配合的刷物品BUG
        if(event.getClickedBlock() != null){
            if(event.getClickedBlock().getState() instanceof InventoryHolder){
                if (!(event.getPlayer().isSneaking()&&event.getPlayer().getItemInHand() != null)){
                    Player player = event.getPlayer();

                    Location location = event.getClickedBlock().getLocation();
                    String localLocation = location.getBlockX() +"\t"+ location.getBlockY() +"\t"+ location.getBlockZ();

                    for (Location l:playerLocationHashMap.values()) {
                        String localL = l.getBlockX() +"\t"+ l.getBlockY() +"\t"+ l.getBlockZ();
                        if (localLocation.equals(localL)){
                            event.setCancelled(true);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉该物品已经有人在使用,请耐心等待..."));
                            return;
                        }
                    }
                    playerLocationHashMap.put(player,location);
                }
            }
        }
    }

    /**
     * 玩家关闭库存的操作
     * @param event 事件
     */
    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent event){
        playerLocationHashMap.remove(Bukkit.getPlayer(event.getPlayer().getName()));
    }

}
