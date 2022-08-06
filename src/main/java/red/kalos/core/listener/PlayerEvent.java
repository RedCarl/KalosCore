package red.kalos.core.listener;

import com.glazed7.glazedpay.bukkit.event.OrderShipEvent;
import eos.moe.dragoncore.api.KeyPressEvent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.Main;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.entity.PlayerData;
import red.kalos.core.entity.RankData;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.premium.VIPBuy;
import red.kalos.core.manager.premium.entity.PlayerVIP;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.PokemonPhotoAPI;
import studio.trc.bukkit.litesignin.event.custom.PlayerSignInEvent;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class PlayerEvent implements Listener {
    HashMap<Player, Location> playerLocationHashMap = new HashMap<>();

    //玩家进入游戏事件
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){

        Player player = event.getPlayer();

        //修改玩家默认游戏模式
        player.setGameMode(GameMode.SURVIVAL);

        //玩家进入游戏提示
        if (PlayerDataManager.getInstance().getPlayerData(player.getUniqueId()).getUuid()==null){
            PlayerDataManager.getInstance().setPlayerData(new PlayerData(player.getUniqueId().toString(),player.getName(),0,0,new RankData("default",0),"0"));
            /*event.setJoinMessage(ColorParser.parse("&8[&a&l!&8] &7欢迎新玩家 &f"+player.getName()+" &7加入卡洛斯！"));*/
        }
//        else {
//            if (player.hasPermission("kim.grandtotal.L")&&!(player.hasPermission("group.admin"))){
//                //通知全服玩家
//                event.setJoinMessage(ColorParser.parse("&8[&c&l!&8] &7欢迎贵族玩家 &6"+player.getName()+" &7回到卡洛斯！"));
//                for (Player p: Bukkit.getOnlinePlayers()) {
//                    p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
//                }
//            }
//        }


        //进入提示
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                //新手签到
//                Newbie.loadPlayerSignData(player);
//
//                //签到系统
//                if (!Storage.getPlayer(player).alreadySignIn()){
//                    if (player.hasPermission("group.eevee")){
//                        Storage.getPlayer(player).signIn();
//                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您是尊贵的 &6[伊布] &7会员玩家，已经自动帮您签到."));
//                        player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
//                    }
//                }
//            }
//        }.runTaskLater(Main.getInstance(),120);

        //设置管理员隐身状态
//        if (player.hasPermission("group.admin")){
//            CMIUser cmiUser = CMI.getInstance().getPlayerManager().getUser(player);
//            cmiUser.setVanished(true);
//            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7管理员自动启动隐藏模式,请不要尝试现身,不要打扰玩家游戏."));
//        }

        //检查会员状态
        VIPBuy.checkRank(player,"eevee",Main.luckPerms.getServerName());
        VIPBuy.checkRank(player,"pikanium",Main.luckPerms.getServerName());
    }

    //蹲下+F 打开菜单
    @EventHandler
    public void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event){
        if (event.getPlayer().isSneaking()){
            MainMenu mainMenu = new MainMenu(event.getPlayer());
            mainMenu.openInventory();
            event.setCancelled(true);
        }
    }

    //玩家交互时的操作
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        //禁止在主城使用 锅 和 钓鱼竿
        if (itemStack!=null&&!player.hasPermission("group.admin")){
            if (player.getLocation().getWorld().getName().equals("spawn")||player.getLocation().getWorld().getName().equals("training")){
                if (
                        itemStack.getType().name().contains("_ROD")||
                                itemStack.getType().name().contains("FRYPAN")||
                                itemStack.getType().name().contains("SAKURA")||
                                itemStack.getType().name().contains("TCONSTRUCT")||
                                itemStack.getType().name().contains("EGG")
                ){
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，为了服务器的秩序，您无法在主城使用该物品."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                    event.setCancelled(true);
                }
            }
        }

//        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)||event.getAction().equals(Action.RIGHT_CLICK_AIR)){
//            if (itemStack!=null&&player.hasPermission("group.eevee")){
//                if (itemStack.getType().equals(Material.WOOD_AXE)){
//                    BuildGUI buildGUI = new BuildGUI(player,0);
//                    buildGUI.openInventory();
//                }
//            }
//        }

    }
    //库存被打开的时候
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

    //玩家关闭库存的操作
    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent event){
        playerLocationHashMap.remove(Bukkit.getPlayer(event.getPlayer().getName()));
//        if (event.getPlayer().getName().equals("Red_Carl")){
//            for (ItemStack i: event.getInventory()) {
//                System.out.println(i.getType());
//            }
//        }
    }

    //玩家移动事件
    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
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

        //累计充值记录
        PlayerData playerData = PlayerDataManager.getInstance().getPlayerData(player.getUniqueId());
        double m = playerData.getRecharge()+Double.parseDouble(money);
        playerData.setRecharge((long)m);
        PlayerDataManager.getInstance().setPlayerData(playerData);

        //会员 充值/续费 取消发货
        if (Objects.equals(money, "15.0") || Objects.equals(money, "45.0")){
            PlayerVIP rank = new PlayerVIP();
            switch (money){
                case "15.0":
                    PlayerVIP PikaniumVIP = VIPBuy.checkRank(player,"pikanium", Main.luckPerms.getServerName());
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
                    PlayerVIP EeveeVIP = VIPBuy.checkRank(player,"eevee", Main.luckPerms.getServerName());
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
        if ("G".equals(key)) {
            if (!config.getBoolean("G")) {
                MainMenu mainMenu = new MainMenu(player);
                mainMenu.openInventory();
            }
        }
    }

    //玩家签到事件
    @EventHandler
    public void PlayerSignInEvent(PlayerSignInEvent event){
        Player player = event.getPlayer();

        //月卡礼包
        if (player.hasPermission("group.eevee")){
            Main.ppAPI.giveAsync(player.getUniqueId(),1);
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7尊贵的 伊布月卡会员 成功领取今天的月卡奖励。 &a(卡点*1)"));
        }
    }

    //玩家死亡事件
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity();
        event.setKeepInventory(true);
        int money = new Random().nextInt(45);
        Main.econ.withdrawPlayer(player,money);
        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很遗憾您在探险的过程中失败了，您丢失了 &c"+money+" &7卡洛币，不要灰心。"));
    }

    //玩家更换世界事件
    @EventHandler
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        switch (event.getPlayer().getWorld().getName()){
            case "world":
                player.sendTitle(ColorParser.parse("&aKalos &f// &a主世界"),ColorParser.parse("&f尽情的享受这个世界吧!"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            case "DIM-1":
                player.sendTitle(ColorParser.parse("&cKalos &f// &c下届"),ColorParser.parse("&f这是一个可怕的世界."),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            case "DIM1":
                player.sendTitle(ColorParser.parse("&dKalos &f// &d末地"),ColorParser.parse("&f末影龙?"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            case "DIM72":
                player.sendTitle(ColorParser.parse("&6Kalos &f// &6究极世界"),ColorParser.parse("&f天空的颜色好像在变化着?"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            case "DIM73":
                player.sendTitle(ColorParser.parse("&3Kalos &f// &3溺水世界"),ColorParser.parse("&f全世界都是海水，无比压抑!"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            case "plot":
                player.sendTitle(ColorParser.parse("&bKalos &f// &b地皮世界"),ColorParser.parse("&f供训练家居住的地方，萌新专属哦！"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            case "spawn":
                player.sendTitle(ColorParser.parse("&3Kalos &f// &3主城"),ColorParser.parse("&f在这里交易、培养宝可梦。"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            case "training":
                player.sendTitle(ColorParser.parse("&cKalos &f// &c宝可梦训练场"),ColorParser.parse("&f尽情的训练您的宝可梦去吧."),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
        }
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event){
        if (event.getEntity().getType().equals(EntityType.PLAYER)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageByBlockEvent event){
        if (event.getEntity().getType().equals(EntityType.PLAYER)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageByEntityEvent event){
        if (event.getEntity().getType().equals(EntityType.PLAYER)){
            event.setCancelled(true);
        }
    }
}
