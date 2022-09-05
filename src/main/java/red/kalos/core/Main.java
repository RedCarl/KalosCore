package red.kalos.core;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import red.kalos.core.command.crazyauctions.CrazyAuctionsCommand;
import red.kalos.core.command.menu.MenuCommand;
import red.kalos.core.command.pokeaward.PokeFormCommand;
import red.kalos.core.configFile.Data;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.entity.PlayerData;
import red.kalos.core.listener.*;
import red.kalos.core.manager.armourers.listener.ArmourersUpdateListener;
import red.kalos.core.manager.citizens.CitizensManager;
import red.kalos.core.manager.crazyauctions.CrazyAuctions;
import red.kalos.core.manager.kitpvp.listener.KitPvpEvent;
import red.kalos.core.manager.kits.KitsManager;
import red.kalos.core.manager.nick.Nick;
import red.kalos.core.manager.pokeban.PokemonBan;
import red.kalos.core.manager.pokespawn.SpawnTime;
import red.kalos.core.manager.questmanager.listener.QuestListener;
import red.kalos.core.manager.questmanager.manager.ConfigManager;
import red.kalos.core.manager.questmanager.manager.QuestManager;
import red.kalos.core.manager.questmanager.quest.list.achievement.Achievement;
import red.kalos.core.manager.questmanager.quest.list.dayquest.Day;
import red.kalos.core.manager.questmanager.quest.list.weekquest.Week;
import red.kalos.core.manager.ranking.RankingManager;
import red.kalos.core.manager.recharge.recharge.RechargeCustomUI;
import red.kalos.core.manager.worldtime.WorldTimeSynchronize;
import red.kalos.core.packetlistener.AdvanceAdapter;
import red.kalos.core.packetlistener.MessageAdapter;
import red.kalos.core.placeholder.PlaceHolder;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.PokemonPhotoAPI;
import red.kalos.core.util.gui.listener.ButtonClickListener;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    private static Economy econ = null;
    public static Economy getEcon() {
        return econ;
    }
    private static PlayerPointsAPI ppAPI;
    public static PlayerPointsAPI getPpAPI() {
        return ppAPI;
    }
    private static LuckPerms luckPerms;
    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }
    @Override
    public void onEnable() {

        instance = this;
        this.saveDefaultConfig();
        this.getConfig();

        log(getName() + " " + getDescription().getVersion() + " &7开始加载...");

        long startTime = System.currentTimeMillis();

        log("正在注册跨服监听器...");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

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

        //PlaceholderAPI
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            log("注册变量...");
            new PlaceHolder().register();
        }else {
            log("未安装 PlaceholderAPI 不进行变量注册...");
            log("若您想使用变量显示，请安装PlaceholderAPI！");
        }

        log("正在注册监听器...");
        regListener(new ButtonClickListener());
        regListener(new CommandEvent());
        regListener(new PlayerEvent());
        regListener(new PokemonEvent());
        regListener(new PokemonBan());
        regListener(new ArmourersUpdateListener());
        regListener(new KitPvpEvent());
        regListener(new Nick());
        regListener(new KitsManager());
        regListener(new ItemInteractEvent());
        regListener(new CitizensEvent());
        regListener(new RechargeCustomUI());

        log("启动传奇宝可梦监控系统...");
        SpawnTime.getInstance().init();

        log("启动宝可梦封禁系统...");
        PokemonBan.rua();

        log("正在注册指令...");
        regCommand("Menu",new MenuCommand());
        regCommand("Kim",new CrazyAuctionsCommand());
        regCommand("PokeAward",new PokeFormCommand());

        log("获取在线玩家信息...");
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

//        log("加载公告组件....");
//        new BroadCastMessage(this);

//        log("加载宝可梦皮肤信息...");
//        saveYmlConfig("CustomSkin/config.yml");
//        File CustomFile = new File(Main.getInstance().getDataFolder(), "CustomSkin/config.yml");
//        FileConfiguration CustomConfig = YamlConfiguration.loadConfiguration(CustomFile);
//        Set<String> AllSkinList = CustomConfig.getConfigurationSection("skins").getKeys(false);
//        Data.CUSTOM_SKIN.addAll(AllSkinList);

        log("加载龙之核心组件...");
        saveYmlConfig("Nick/gui.yml");
        saveYmlConfig("Recharge/gui.yml");

        log("加载礼包系统...");
        KitsManager.onLoadKitsEvent();

        log("加载玩家Ranking系统...");
        RankingManager.init();

        log("加载 Quest 模块...");
        ConfigManager.initialize();
        new QuestListener().initialize();
        new Day().initialize();
        new Week().initialize();
        new Achievement().initialize();
        QuestManager.initialize();

        log("加载 NPC 模块...");
        CitizensManager.getInstance().init(Bukkit.getWorld("spawn"));

        log("加载 世界状态同步 模块...");
        WorldTimeSynchronize.getInstance().init();

        log("加载完成 ，共耗时 " + (System.currentTimeMillis() - startTime) + " ms 。");

        showAD();
    }

    @Override
    public void onDisable() {
        log(getName() + " " + getDescription().getVersion() + " 开始卸载...");
        long startTime = System.currentTimeMillis();

        log("保存 Quest 模块数据...");
        QuestManager.save();

        log("卸载监听器...");
        Bukkit.getServicesManager().unregisterAll(this);

        log("卸载内置插件 CrazyAuctions 信息...");
        CrazyAuctions crazyAuctions = new CrazyAuctions();
        crazyAuctions.onDisable();

        log("卸载 Citizens 模块...");
        CitizensManager.getInstance().clear();

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
     * 获取玩家的累计充值数量
     */
    public static void getAllOnlinePlayerData(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player:Bukkit.getOnlinePlayers()) {
                    PlayerData playerData = PlayerDataManager.getInstance().getPlayerData(player.getUniqueId());
                    playerData.setPlayTime(playerData.getPlayTime()+1);
                    PlayerDataManager.getInstance().setPlayerData(playerData);
                }
            }
        }.runTaskTimer(Main.getInstance(),0,20);
    }


    //文件保存至本地
    public void saveYmlConfig(String file) {
        File configFile = new File(Bukkit.getPluginManager().getPlugin(this.getName()).getDataFolder(), file);
        if (!configFile.exists()) {
            this.saveResource(file, false);
        }

    }

}
