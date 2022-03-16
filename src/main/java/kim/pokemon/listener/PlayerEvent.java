package kim.pokemon.listener;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import com.glazed7.glazedpay.bukkit.event.OrderShipEvent;
import eos.moe.dragoncore.api.KeyPressEvent;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.GlazedPayDataSQLReader;
import kim.pokemon.database.PlayerEventDataSQLReader;
import kim.pokemon.entity.PlayerEventData;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.premium.VIPBuy;
import kim.pokemon.kimexpand.premium.entity.PlayerVIP;
import kim.pokemon.kimexpand.signin.Newbie;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.api.PokemonPhotoAPI;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;
import studio.trc.bukkit.litesignin.api.Storage;
import studio.trc.bukkit.litesignin.event.custom.PlayerSignInEvent;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class PlayerEvent implements Listener {
    HashMap<Player, Location> playerLocationHashMap = new HashMap<>();
    /**
     * 玩家进入游戏事件
     * @param event
     */
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        //修改玩家默认游戏模式
        player.setGameMode(GameMode.SURVIVAL);

        //玩家进入游戏提示
        if (player.hasPlayedBefore()){
            if (player.hasPermission("kim.grandtotal.L")&&!(player.hasPermission("group.admin"))){
                event.setJoinMessage(ColorParser.parse("&8[&c&l!&8] &7欢迎贵族玩家 &6"+player.getDisplayName()+" &7回到卡洛斯！"));
                for (Player p: Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }
            }
        }else {
            event.setJoinMessage(ColorParser.parse("&8[&a&l!&8] &7欢迎新玩家 &f"+player.getName()+" &7加入卡洛斯！"));
        }

        //签到系统
        if (!player.hasPermission("kim.newbie.G")){
            Newbie newbie = new Newbie(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    newbie.openInventory();
                    player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }
            }.runTaskLater(Main.getInstance(),60);
        }else {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.hasPermission("group.eevee")){
                        if (!Storage.getPlayer(player).alreadySignIn()){
                            Storage.getPlayer(player).signIn();
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您是尊贵的 &6[伊布] &7会员玩家，已经自动帮您签到."));
                        }
                    }else {
                        Bukkit.dispatchCommand(player,"LiteSignIn gui");
                    }
                    player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }
            }.runTaskLater(Main.getInstance(),60);
        }

        //设置管理员隐身状态
        if (player.hasPermission("group.admin")){
            CMIUser cmiUser = CMI.getInstance().getPlayerManager().getUser(player);
            cmiUser.setVanished(true);
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7管理员自动启动隐藏模式,请不要尝试现身,不要打扰玩家游戏."));
        }
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event){

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
        Player player = event.getPlayer();

        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)||event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            //禁止在地皮种植树果
//            if (player.getLocation().getWorld().getName().equals("plot")){
//                if (player.getItemInHand().getType().name().contains("_APRICORN")){
//                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，为了服务器的流畅度，您无法在地皮种植树果."));
//                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                    event.setCancelled(true);
//                }
//            }
            //禁止在主城使用 锅 和 钓鱼竿
            if (!player.hasPermission("group.admin")){
                if (player.getLocation().getWorld().getName().equals("spawn")){
                    if (
                            player.getItemInHand().getType().name().contains("_ROD")||
                            player.getItemInHand().getType().name().contains("FRYPAN")||
                                    player.getItemInHand().getType().name().contains("SAKURA")||
                                    player.getItemInHand().getType().name().contains("TCONSTRUCT")
                    ){
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，为了服务器的秩序，您无法在主城使用该物品."));
                        player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                        event.setCancelled(true);
                    }
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
//            for (ItemStack i: event.getInventory()) {
//                System.out.println(i.getType());
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
                    if (Main.getInstance().getGlazedPayDataSQLReader().getPlayer(player.getName()).getAmount()<1){
                        event.setChange(money*2);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7感谢您的支持,由于您是第一次赞助,本次充值到账的 &c"+Data.SERVER_POINTS+"x2 &7请注意查收."));
                    }
                }
            }
        }


        //数据记录
        Main.getInstance().getPlayerEventDataSQLReader().addPlayerEvent(PlayerEventData.setPlayerEventData(Bukkit.getPlayer(event.getPlayerId()).getName(),event.getEventName(),String.valueOf(event.getChange()),new Timestamp(System.currentTimeMillis())));
    }


    //方块破坏事件
    @EventHandler
    public void PlayerInteractEvent(BlockBreakEvent event){
        //开启幸运方块
        if (event.getBlock().getType().toString().equals("POKELUCKY_POKE_LUCKY")){
            //数据记录
//            Main.getInstance().getPlayerEventDataSQLReader().addPlayerEvent(PlayerEventData.setPlayerEventData(event.getPlayer().getName(),event.getEventName(),"1",new Timestamp(System.currentTimeMillis())));
        }
    }

    //玩家移动事件
    @EventHandler
    public void Player(PlayerMoveEvent event){
        Player player = event.getPlayer();

        //掉入虚空拉回
        if (player.getLocation().getWorld().getName().equals("spawn")&&player.getLocation().getY()<=-1){
            Location location = new Location(Bukkit.getWorld("spawn"),-20.5,10,47.5);
            location.setYaw(90);
            player.teleport(location);
        }
    }

    @EventHandler
    public void OrderShipEvent(OrderShipEvent event){
        String name = String.valueOf(event.getOrderInfo().get("buyerName")).replace("\"","");
        String money = String.valueOf(event.getOrderInfo().get("totalFee"));
        Player player = Bukkit.getPlayer(name);


        Main.getInstance().getPlayerEventDataSQLReader().addPlayerEvent(PlayerEventData.setPlayerEventData(name,event.getEventName(),money,new Timestamp(System.currentTimeMillis())));

        //会员 充值/续费 取消发货
        if (Objects.equals(money, "25.0") || Objects.equals(money, "45.0")){
            PlayerVIP rank = new PlayerVIP();
            switch (money){
                case "25.0":
                    PlayerVIP PikaniumVIP = VIPBuy.checkRank(player,"pikanium",Main.luckPerms.getServerName());
                    rank.setName(player.getName());
                    rank.setRank("pikanium");
                    if (PikaniumVIP!=null){
                        rank.setTime(new Timestamp(PikaniumVIP.getTime().getTime()+ 2592000000L));
                        rank.setServer(Main.luckPerms.getServerName());
                        VIPBuy.updateRank(rank);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功续费了 &e卡洛斯の皮卡丘*30天 &7请注意查收."));
                    }else {
                        rank.setTime(new Timestamp(System.currentTimeMillis()+ 2592000000L));
                        rank.setServer(Main.luckPerms.getServerName());
                        VIPBuy.addRank(rank);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 &e卡洛斯の皮卡丘*30天 &7请注意查收."));
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    break;
                case "45.0":
                    PlayerVIP EeveeVIP = VIPBuy.checkRank(player,"eevee",Main.luckPerms.getServerName());
                    rank.setName(player.getName());
                    rank.setRank("eevee");
                    if (EeveeVIP!=null){
                        rank.setTime(new Timestamp(EeveeVIP.getTime().getTime()+ 2592000000L));
                        rank.setServer(Main.luckPerms.getServerName());
                        VIPBuy.updateRank(rank);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功续费了 &6卡洛斯の伊布*30天 &7请注意查收."));
                    }else {
                        VIPBuy.deleteRank(player);
                        rank.setTime(new Timestamp(System.currentTimeMillis()+ 2592000000L));
                        rank.setServer(Main.luckPerms.getServerName());
                        VIPBuy.addRank(rank);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 &6卡洛斯の伊布*30天 &7请注意查收."));
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    break;
            }
            event.setCancelled(true);
        }
    }

    //玩家案件事件(龙之核心)
    @EventHandler
    public void KeyPressEvent(KeyPressEvent event){
        Player player = event.getPlayer();
        String key = event.getKey();
        PokemonPhotoAPI.getFolder("KeyPressEvent/");
        File file = new File(Main.getInstance().getDataFolder(), "KeyPressEvent/"+player.getName() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        switch (key){
            case "G":
                if (!config.getBoolean("G")){
                    MainMenu mainMenu = new MainMenu(player);
                    mainMenu.openInventory();
                }
                break;
        }
    }

    //玩家签到事件
    @EventHandler
    public void PlayerSignInEvent(PlayerSignInEvent event){
        Player player = event.getPlayer();

        //月卡礼包
        if (player.hasPermission("group.pikanium")){
            Main.ppAPI.giveAsync(player.getUniqueId(),1);
        }
        if (player.hasPermission("group.eevee")){
            Main.ppAPI.giveAsync(player.getUniqueId(),5);
        }
    }

    //玩家死亡事件
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity();

        int money = new Random().nextInt(45);
        Main.econ.withdrawPlayer(player,money);
        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很遗憾您在探险的过程中失败了，您丢失了 &c"+money+" &7卡洛币，不要灰心。"));
    }
}
