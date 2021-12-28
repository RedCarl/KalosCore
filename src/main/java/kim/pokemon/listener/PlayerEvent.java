package kim.pokemon.listener;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.GlazedPayDataSQLReader;
import kim.pokemon.database.PlayerEventDataSQLReader;
import kim.pokemon.entity.PlayerEventData;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.npc.NpcEntityEvent;
import kim.pokemon.util.ColorParser;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.sql.Timestamp;
import java.util.HashMap;

public class PlayerEvent implements Listener {
    HashMap<Player, Location> playerLocationHashMap = new HashMap<>();
    HashMap<Player, String> onlinePlayer = new HashMap<>();
    /**
     * 玩家进入游戏事件
     * @param event
     */
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        Main.getAllPlayerInfo();
        Player player = event.getPlayer();

        if (player.hasPlayedBefore()){
            //老玩家
        }else {
            //新玩家

        }

        //记录玩家进入服务器
        if (Bukkit.getOnlinePlayers().size()==0){
            NpcEntityEvent.NpcHologram(true);
        }
        onlinePlayer.put(event.getPlayer(),event.getPlayer().getName());

        //设置管理员隐身状态
        if (player.hasPermission("group.admin")){
            CMIUser cmiUser = CMI.getInstance().getPlayerManager().getUser(player);
            cmiUser.setVanished(true);
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7管理员自动启动隐藏模式,请不要尝试现身,不要打扰玩家游戏."));
        }
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event){
        onlinePlayer.remove(event.getPlayer());
        if (onlinePlayer.size()==0){
            NpcEntityEvent.NpcHologram(false);
        }
    }

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

        //禁止在地皮种植树果
        if(event.getClickedBlock() != null&&event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (event.getPlayer().getItemInHand().getType().name().contains("_APRICORN")){
                if (event.getPlayer().getLocation().getWorld().getName().equals("plot")){
                    event.getPlayer().sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，为了服务器的流畅度，您无法在地皮种植树果."));
                    event.setCancelled(true);
                }
            }
        }
    }

    /**
     * 库存被打开的时候
     * @param event
     */
    @EventHandler
    public void InventoryOpenEvent(InventoryOpenEvent event){
        //修复大部分需要两人配合的刷物品BUG
        //不检测的容器
        Player player = Bukkit.getPlayer(event.getPlayer().getUniqueId());
        if ((!event.getView().getType().equals(InventoryType.ENDER_CHEST))&&event.getInventory().getLocation()!=null){
            if (!(player.isSneaking()&&event.getPlayer().getItemInHand() != null)){
                Location location = event.getInventory().getLocation();
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

    /**
     * 玩家关闭库存的操作
     * @param event 事件
     */
    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent event){
        playerLocationHashMap.remove(Bukkit.getPlayer(event.getPlayer().getName()));

//        if (event.getPlayer().getName().equals("Red_Carl")){
//            for (ItemStack itemStack:event.getInventory()) {
//                if (itemStack!=null){
//                    System.out.println(itemStack.getType().name()+","+ I);;
//                }
//            }
//        }
    }

    /**
     * 玩家点击余额变更事件
     * @param event
     */
    @EventHandler
    public void PlayerPoints(PlayerPointsChangeEvent event){
        //充值奖励翻倍
        if (Data.POINTS_ACTIVITY){
            Player player = Bukkit.getPlayer(event.getPlayerId());
            if (player!=null){
                int money = event.getChange();
                if (money>0){
                    if (GlazedPayDataSQLReader.getPlayer(player.getName()).getAmount()<1){
                        event.setChange(money*2);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7感谢您的支持,由于您是第一次赞助,本次充值到账的 &c"+Data.SERVER_POINTS+"x2 &7请注意查收."));
                    }
                }
            }
        }


        //数据记录
        PlayerEventDataSQLReader.addPlayerEvent(PlayerEventData.setPlayerEventData(Bukkit.getPlayer(event.getPlayerId()).getName(),event.getEventName(),String.valueOf(event.getChange()),new Timestamp(System.currentTimeMillis())));
    }


    //方块破坏事件
    @EventHandler
    public void PlayerInteractEvent(BlockBreakEvent event){
        //开启幸运方块
        if (event.getBlock().getType().toString().equals("POKELUCKY_POKE_LUCKY")){
            //数据记录
            PlayerEventDataSQLReader.addPlayerEvent(PlayerEventData.setPlayerEventData(event.getPlayer().getName(),event.getEventName(),"1",new Timestamp(System.currentTimeMillis())));
        }
    }

}
