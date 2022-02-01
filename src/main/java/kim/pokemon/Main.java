package kim.pokemon;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.TabList.TabListManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import kim.pokemon.command.CrazyAuctions.CrazyAuctionsCommand;
import kim.pokemon.command.MenuCommand;
import kim.pokemon.command.PokeAward.PokeFormCommand;
import kim.pokemon.command.PokemonBan.BanItemCommand;
import kim.pokemon.command.PokemonBan.BanPokemonCommand;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.*;
import kim.pokemon.kimexpand.armourers.listener.ArmourersUpdateListener;
import kim.pokemon.kimexpand.autobroadcast.BroadCastMessage;
import kim.pokemon.kimexpand.crazyauctions.CrazyAuctions;
import kim.pokemon.kimexpand.kitpvp.PVPEvent;
import kim.pokemon.kimexpand.npc.NpcEntityEvent;
import kim.pokemon.kimexpand.onlinereward.PlayerOnlineReward;
import kim.pokemon.kimexpand.pokeban.PokemonBan;
import kim.pokemon.kimexpand.pokespawn.SpawnTime;
import kim.pokemon.listener.CommandEvent;
import kim.pokemon.listener.PlayerEvent;
import kim.pokemon.listener.PokemonEvent;
import kim.pokemon.listener.WorldEvent;
import kim.pokemon.packetlistener.AdvanceAdapter;
import kim.pokemon.packetlistener.MessageAdapter;
import kim.pokemon.placeholder.TitlePlaceholderAPI;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.api.PokemonPhotoAPI;
import kim.pokemon.util.gui.listener.ButtonClickListener;
import kim.pokemon.util.mojang.Mojang;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Main extends JavaPlugin {
    private static Main instance;
    public static Economy econ = null;
    public static PlayerPointsAPI ppAPI;
    public static LuckPerms luckPerms;
    public static Plugin getInstance(){
        return instance;
    }
    //玩家皮肤ID数据
    public static HashMap<Player,String> players = new HashMap<>();
    //玩家累计充值数量数据
    public static HashMap<Player, Double> GlazedPayData = new HashMap<>();

    @Override
    public void onEnable() {

        instance = this;
        this.saveDefaultConfig();
        this.getConfig();

        log(getName() + " " + getDescription().getVersion() + " &7开始加载...");

        long startTime = System.currentTimeMillis();

        //Vault
        if (!setupEconomy() ) {
            log("未找到 Vault 依赖...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }else {
            log("正在加载 Vault 依赖...");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            log("正在加载 PlayerPoints 依赖...");
            ppAPI = PlayerPoints.getInstance().getAPI();
        }else {
            log("未找到 PlayerPoints 依赖...");
        }

        //PlaceholderAPI
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            log("注册变量...");
            new TitlePlaceholderAPI(Main.instance).register();
        }else {
            log("未安装 PlaceholderAPI 不进行变量注册...");
            log("若您想使用变量显示，请安装PlaceholderAPI！");
        }

        //NBTAPI
        if(Bukkit.getPluginManager().getPlugin("NBTAPI") != null) {
            log("加载 NBTAPI 依赖...");
        }else {
            log("未安装 NBTAPI 部分功能将无法正常运行...");
        }

        //ProtocolLib
        if(Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            log("正在注册数据包...");
            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            manager.addPacketListener(new MessageAdapter());
            manager.addPacketListener(new AdvanceAdapter());
        }else {
            log("未安装 ProtocolLib 不进行数据包注册...");
            log("若您想使用全部功能，请安装ProtocolLib！");
        }

        //LuckPerms
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            log("正在注册 LuckPerms 权限管理...");
            luckPerms = provider.getProvider();
        }else {
            log("未安装 LuckPerms 不进行数据包注册...");
            log("若您想使用全部功能，请安装LuckPerms！");
        }


        log("正在注册监听器...");
        regListener(new ButtonClickListener());
        regListener(new CommandEvent());
        regListener(new PlayerEvent());
        regListener(new PokemonEvent());
        regListener(new SpawnTime());
        regListener(new PokemonBan());
        regListener(new ArmourersUpdateListener());
        regListener(new NpcEntityEvent());
        regListener(new WorldEvent());
        regListener(new PVPEvent());

        log("启动传奇宝可梦监控系统...");
        SpawnTime.start();

        log("启动宝可梦封禁系统...");
        PokemonBan.rua();

        log("正在注册指令...");
        regCommand("Menu",new MenuCommand());
        regCommand("BanPokemon",new BanPokemonCommand());
        regCommand("BanItem",new BanItemCommand());
        regCommand("Kim",new CrazyAuctionsCommand());
        regCommand("PokeAward",new PokeFormCommand());

        log("正在启动数据库...");
        SQLConnection.initialize();
        GlazedPayDataSQLReader.selectTable();
        PokemonBanDataSQLReader.selectTable();
        PremiumPlayerDataSQLReader.selectTable();
        PlayerEventDataSQLReader.selectTable();

        log("获取在线玩家信息...");
        getAllPlayerInfo();
        getAllOnlinePlayerData();

        log("关闭所有玩家的GUI界面...");
        for (Player player:Bukkit.getOnlinePlayers()) {
            if (player.getOpenInventory().getTitle().contains(Data.SERVER_NAME)){
                player.closeInventory();
            }
        }

        log("加载内置插件 CrazyAuctions 信息...");
        Bukkit.getConsoleSender().sendMessage(ColorParser.parse("&c-------[ CrazyAuctions ]-------"));
        PokemonPhotoAPI.getFolder("CrazyAuctions");
        CrazyAuctions crazyAuctions = new CrazyAuctions();
        crazyAuctions.onEnable();
        Bukkit.getConsoleSender().sendMessage(ColorParser.parse("&c-------------------------------"));

        log("加载公告组件....");
        new BroadCastMessage(this);

        log("加载在线奖励组件...");
        new PlayerOnlineReward(this);

        log("加载完成 ，共耗时 " + (System.currentTimeMillis() - startTime) + " ms 。");

        showAD();
    }

    @Override
    public void onDisable() {
        log(getName() + " " + getDescription().getVersion() + " 开始卸载...");
        long startTime = System.currentTimeMillis();

        log("卸载监听器...");
        Bukkit.getServicesManager().unregisterAll(this);

        log("正在关闭数据库...");
        SQLConnection.close();

        log("卸载内置插件 CrazyAuctions 信息...");
        CrazyAuctions crazyAuctions = new CrazyAuctions();
        crazyAuctions.onDisable();

        log("卸载完成 ，共耗时 " + (System.currentTimeMillis() - startTime) + " ms 。");

        showAD();
    }
    /**
     * 注册监听器
     *
     * @param listener 监听器
     */
    public static void regListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getInstance());
    }

    /**
     * 注册指令
     *
     * @param name 指令名字
     * @param command 指令
     */
    public static void regCommand(String name, CommandExecutor command) {
        Bukkit.getPluginCommand(name).setExecutor(command);
    }

    /**
     * 日志
     * @param message 日志消息
     */
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ColorParser.parse("[" + getInstance().getName() + "] " + message));
    }

    /**
     * 作者信息
     */
    private void showAD() {
        log("&7感谢您使用 &c&lKalosCore v" + getDescription().getVersion());
        log("&7本插件由 &c&lKalos Studios &7提供长期支持与维护。");
    }

    /**
     * 经济前置检测
     * @return 是否安装
     */
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    /**
     * 获取所有在线玩家的正版UUID
     */
    public static void getAllPlayerInfo(){
        new Thread(()->{
            try {
                for (Player player:Bukkit.getOnlinePlayers()) {
                    if (!players.containsKey(player)){
                        players.put(player,Mojang.getSkinUrl(new Mojang().getUUIDOfUsername(player.getName())));
                    }
                }
            }catch (NullPointerException ignored){}
        }).start();
    }

    /**
     * 获取玩家的累计充值数量
     */
    public static void getAllOnlinePlayerData(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player:Bukkit.getOnlinePlayers()) {
                    GlazedPayData.put(player,GlazedPayDataSQLReader.getPlayer(player.getName()).getAmount());
                }
            }
        }.runTaskTimer(Main.getInstance(),0,600);
    }
}
