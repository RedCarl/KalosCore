package kim.pokemon.kimexpand.menu;

import cn.asougi.pokemonquest.gui.pokemonQuest.QuestGUI;
import cn.asougi.pokemonquest.quest.QuestType;
import com.Zrips.CMI.CMI;
import de.tr7zw.nbtapi.NBTItem;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.GlazedPayDataSQLReader;
import kim.pokemon.kimexpand.crazyauctions.api.Category;
import kim.pokemon.kimexpand.crazyauctions.api.ShopType;
import kim.pokemon.kimexpand.crazyauctions.controllers.GUI;
import kim.pokemon.kimexpand.homes.HomeMenu;
import kim.pokemon.kimexpand.mysteriousstore.EeveeShop;
import kim.pokemon.kimexpand.playerinfo.PlayerInfo;
import kim.pokemon.kimexpand.plotadmin.gui.PlotMenu;
import kim.pokemon.kimexpand.pokeban.gui.BanList;
import kim.pokemon.kimexpand.pokeinfo.gui.PokemonInfoMenu;
import kim.pokemon.kimexpand.pokespawn.SpawnTime;
import kim.pokemon.kimexpand.recharge.recharge.RechargeMenu;
import kim.pokemon.kimexpand.warps.WorldWarpMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.kalos.ui.Menu;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainMenu extends InventoryGUI {
    public static HashMap<Player,Long> HealSleep = new HashMap<>();
    public static HashMap<Player,Long> CheckSpawnsSleep = new HashMap<>();

    public MainMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 菜单"), player, 6);
        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 54; i++) {
            this.setButton(i, new Button(Line));
        }

        ItemStack PlayerInfo = null;
        try {
            PlayerInfo = SkullAPI.getPlayerSkull(
                    ColorParser.parse("&b&l"+Data.SERVER_NAME_CN+"の宝可梦 &c(v"+Main.getInstance().getDescription().getVersion()+")"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&3玩家信息:"),
                    ColorParser.parse("&r  &b■ &7账号(用于登录游戏):"),
                    ColorParser.parse("&r      &f" + player.getName()),
                    ColorParser.parse("&r  &9■ &7昵称(用于游戏内显示):"),
                    ColorParser.parse("&r      &f" + player.getDisplayName()),
                    ColorParser.parse("&r  &c■ &7UUID(用于存储玩家数据):"),
                    ColorParser.parse("&r      &f" + player.getUniqueId()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7游戏余额:"),
                    ColorParser.parse("&r      &3" + CMI.getInstance().getPlayerManager().getUser(player).getBalance() + " &7"+Data.SERVER_VAULT),
                    ColorParser.parse("&r      &3" + Main.ppAPI.lookAsync(player.getUniqueId()).get() + ".0 &7"+Data.SERVER_POINTS),
                    ColorParser.parse("&r  &6■ &7累计充值: &3"),
                    ColorParser.parse("&r      &3" + Main.getInstance().getGlazedPayDataSQLReader().getPlayer(player.getName()).getAmount() + " &7RMB"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o您可以查看一些与您相关的信息.")
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Button PlayerInfoButton = new Button(PlayerInfo, type -> {
            if (type.isLeftClick()) {
                PlayerInfo playerInfo = new PlayerInfo(player);
                playerInfo.openInventory();
            }
        });
        this.setButton(10, PlayerInfoButton);

        ItemStack Recharge = ItemFactoryAPI.getItemStack(Material.EMERALD,
                ColorParser.parse("&f系统商城"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o在这里可以对服务器进行赞助,并获得回馈.")
        );
        Button RechargeButton = new Button(Recharge, type -> {
            if (type.isLeftClick()) {
                RechargeMenu rechargeMenu = new RechargeMenu(player);
                rechargeMenu.openInventory();
            }
        });
        this.setButton(19, RechargeButton);

        ItemStack Teleportation = ItemFactoryAPI.getItemStack(Material.LEATHER_BOOTS,
                ColorParser.parse("&f地标传送"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o更方便的将您传送至您需要前往的地方.")
        );
        Button TeleportationButton = new Button(Teleportation, type -> {
            if (type.isLeftClick()) {
                WorldWarpMenu warpMenu = new WorldWarpMenu(player);
                warpMenu.openInventory();
            }
        });
        this.setButton(28, TeleportationButton);

        ItemStack Clock = ItemFactoryAPI.getItemStack(Material.FIREWORK,
                ColorParser.parse("&f娱乐活动"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您可以看到本服务器所有的娱乐活动详情.")
        );
        Button ClockButton = new Button(Clock, type -> {
            if (type.isLeftClick()) {
                if (Main.getInstance().getServer().getPluginManager().isPluginEnabled("GameCore")){
                    Menu menu = new Menu(player);
                    menu.openInventory();
                }
            }
        });
        this.setButton(37, ClockButton);


        ItemStack Spyglass = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_CASH_REGISTER"),
                ColorParser.parse("&f全球市场"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o在这里与全服的玩家更方便的进行 &a出售 &7&o或 &a拍卖 &7&o物品."),
                ColorParser.parse("&7&o左键点击进入 &a出售 &7&o市场,右键点击进入 &a拍卖 &7&o市场.")
        );
        Button SpyglassButton = new Button(Spyglass, type -> {
            if (type.isLeftClick()) {
                GUI.openShop(player, ShopType.SELL, Category.NONE, 1);
            }
            if (type.isRightClick()) {
                GUI.openShop(player, ShopType.BID, Category.NONE, 1);
            }
        });
        this.setButton(12, SpyglassButton);


        ItemStack Bed = ItemFactoryAPI.getItemStack(Material.BED,
                ColorParser.parse("&f家园系统"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o查看您所有的 &a家园坐标 &7&o并对其进行管理.")
        );
        Button BedButton = new Button(Bed, type -> {
            if (type.isLeftClick()) {
                HomeMenu homeMenu = new HomeMenu(player);
                homeMenu.openInventory();
            }
        });
        this.setButton(13, BedButton);


        ItemStack Residence = ItemFactoryAPI.getItemStack(Material.WOOD_HOE,
                ColorParser.parse("&f地皮管理"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o查看您所有 &a地皮 &7&o并对其进行管理.")
        );
        Button ResidenceButton = new Button(Residence, type -> {
            if (type.isLeftClick()) {
                PlotMenu plotMenu = new PlotMenu(player);
                plotMenu.openInventory();
            }
        });
        this.setButton(14, ResidenceButton);


        ItemStack RandomTeleportation = ItemFactoryAPI.getItemStack(Material.COMPASS,
                ColorParser.parse("&f随机传送"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o将您随机传送至主世界的一个地点."),
                ColorParser.parse("&8当前范围: ( 3000 - 20000 )")
        );
        Button RandomTeleportationButton = new Button(RandomTeleportation, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                Bukkit.dispatchCommand(player,"cmi rt world");
            }
        });
        this.setButton(15, RandomTeleportationButton);

        ItemStack Workbench = ItemFactoryAPI.getItemStack(Material.WORKBENCH,
                ColorParser.parse("&f工作台"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您可以非常方便的打开工作台使用.")
        );
        Button WorkbenchButton = new Button(Workbench, type -> {
            if (type.isLeftClick()) {
                player.openWorkbench(player.getLocation(),true);
            }
        });
        this.setButton(16, WorkbenchButton);

        ItemStack EnderChest = ItemFactoryAPI.getItemStack(Material.ENDER_CHEST,
                ColorParser.parse("&f末影箱"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o随时随地打开末影箱进行存储物品.")
        );
        Button EnderChestButton = new Button(EnderChest, type -> {
            if (type.isLeftClick()) {
                player.openInventory(player.getEnderChest());
            }
        });
        this.setButton(21, EnderChestButton);

        ItemStack Heal = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HEALER"),
                ColorParser.parse("&f治疗宝可梦"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &a■ &7价 格:"),
                ColorParser.parse("&r      &7   普通&f|&7玩家: &f"+50+" &7"+Data.SERVER_VAULT+"/次 (冷却10秒)"),
                ColorParser.parse("&r      &e   皮卡&f|&7玩家: &f"+24+" &7"+Data.SERVER_VAULT+"/次 (冷却10秒)"),
                ColorParser.parse("&r      &6   伊布&f|&7玩家: &f"+0+" &7"+Data.SERVER_VAULT+"/次 (冷却10秒)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您可以就地对背包的 &a宝可梦 &7&o进行治疗.")
        );
        Button HealButton = new Button(Heal, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                if (HealSleep.containsKey(player)){
                    if (System.currentTimeMillis()-HealSleep.get(player)>=10000){
                        if (player.hasPermission("group.eevee")){
                            HealSleep.put(player,System.currentTimeMillis());
                            PokemonAPI.setPokemonStater(player);
                        }else if (player.hasPermission("group.pikanium")){
                            if (Main.econ.getBalance(player)>=24){
                                Main.econ.withdrawPlayer(player,24);
                                HealSleep.put(player,System.currentTimeMillis());
                                PokemonAPI.setPokemonStater(player);
                            }else {
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7抱歉，您需要花费 &c24 &7卡洛币来治疗宝可梦们."));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            }
                        }else {
                            if (Main.econ.getBalance(player)>=50){
                                Main.econ.withdrawPlayer(player,50);
                                HealSleep.put(player,System.currentTimeMillis());
                                PokemonAPI.setPokemonStater(player);
                            }else {
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7抱歉，您需要花费 &c50 &7卡洛币来治疗宝可梦们."));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            }
                        }
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7抱歉，您必须等待 &c10 &7秒才能使用该功能."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }

                }else {
                    HealSleep.put(player,System.currentTimeMillis());
                    PokemonAPI.setPokemonStater(player);
                }
            }
        });
        this.setButton(22, HealButton);

        ItemStack PC = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PC"),
                ColorParser.parse("&f宝可梦电脑"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &a■ &7精灵回收:"),
                ColorParser.parse("&r      &7   您可以在PC内将宝可梦拖入垃"),
                ColorParser.parse("&r      &7圾桶,将会给你 &a"+Data.SERVER_VAULT+" &7作为报酬."),
                ColorParser.parse("&r      &7   如果是传说宝可梦那么我们将"),
                ColorParser.parse("&r      &7会给你 &6"+Data.SERVER_POINTS+" &7作为报酬."),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o快捷的远程打开 &a宝可梦电脑 &7&o对宝可梦进行管理.")
        );
        Button PCButton = new Button(PC, type -> {
            if (type.isLeftClick()) {
                PokemonAPI.openPlayerPC(player);
            }
        });
        this.setButton(23, PCButton);

        ItemStack PokemonInfo = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                ColorParser.parse("&f宝可梦信息"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o查看背包内 &a宝可梦 &7&o们的详细属性.")
        );
        NBTItem nbtItem = new NBTItem(PokemonInfo);
        nbtItem.setShort("ndex", (short)4);
        Button PokemonInfoButton = new Button(nbtItem.getItem(), type -> {
            if (type.isLeftClick()) {
                PokemonInfoMenu pokemonInfoMenu = new PokemonInfoMenu(player);
                pokemonInfoMenu.openInventory();
            }
        });
        this.setButton(24, PokemonInfoButton);

        ItemStack VIPFly = ItemFactoryAPI.getItemStack(Material.ELYTRA,
                ColorParser.parse("&f飞行 &7/ &e会员专属"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以随时随地使用飞行权限.")
        );
        Button VIPFlyButton = new Button(VIPFly, type -> {
            if (type.isLeftClick()) {
                if (player.hasPermission("cmi.command.fly")&&(!player.getLocation().getWorld().getName().equals("pvp"))){
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    Bukkit.dispatchCommand(player,"cmi fly");
                    player.closeInventory();
                }else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.closeInventory();
                }
            }
        });
        this.setButton(25, VIPFlyButton);

        ItemStack VIPCheckSpawns = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_POKEMON_EDITOR"),
                ColorParser.parse("&f传说查询 &7(不能100%准确)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7使用收费:"),
                ColorParser.parse("&r      &7   普通&f|&7玩家: &f"+500+" &7"+Data.SERVER_VAULT+"/次 (冷却2分钟)"),
                ColorParser.parse("&r      &e   皮卡&f|&7玩家: &f"+240+" &7"+Data.SERVER_VAULT+"/次 (冷却1分钟)"),
                ColorParser.parse("&r      &6   伊布&f|&7玩家: &f"+0+" &7"+Data.SERVER_VAULT+"/次 (无冷却)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7概率信息:"),
                ColorParser.parse("&r      &7   刷新周期: &c"+Data.LEGENDARY_SPAWN+" &7秒"),
                ColorParser.parse("&r      &7   刷新概率: &c"+(10+Bukkit.getOnlinePlayers().size())+"% &7(随在线人数增加)"),
                ColorParser.parse("&r      &7   下次刷新: "+PokemonAPI.getDate(SpawnTime.second)),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o只能查询该区域的传说宝可梦,并不能代表您的脚下.")
        );
        Button VIPCheckSpawnsButton = new Button(VIPCheckSpawns, type -> {
            player.closeInventory();
            long s = 120000;
            if (player.hasPermission("group.eevee")){
                s = 0;
            }else if (player.hasPermission("group.pikanium")){
                s = 60000;
            }
            if (CheckSpawnsSleep.containsKey(player)){
                if (System.currentTimeMillis()-CheckSpawnsSleep.get(player)>=s){
                    CheckSpawnsSleep.put(player,System.currentTimeMillis());
                    PokemonAPI.check(player);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7抱歉，您必须等待 &c"+(s/1000 - (System.currentTimeMillis()-CheckSpawnsSleep.get(player))/1000)+" &7秒才能使用该功能."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }else {
                CheckSpawnsSleep.put(player,System.currentTimeMillis());
                PokemonAPI.check(player);
            }
        });
        this.setButton(30, VIPCheckSpawnsButton);

        ItemStack PokeRank = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_TRAINER_EDITOR"),
                ColorParser.parse("&f排位系统 &c(Bate1.0)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &a■ &7使用教程:"),
                ColorParser.parse("&r      &7   鼠标 &c左键 &7加入排位队伍 &c右键 &7取消匹配。"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o宝可梦竞标赛，来这里展示你的队伍.")
        );
        Button PokeRankButton = new Button(PokeRank, type -> {
            if (type.isLeftClick()) {
                Bukkit.dispatchCommand(player,"pokemonrank:rank");
                player.closeInventory();
            }
            if (type.isRightClick()) {
                Bukkit.dispatchCommand(player,"pokemonrank:rank cancel");
                player.closeInventory();
            }
        });
        this.setButton(49, PokeRankButton);

        ItemStack Mission = ItemFactoryAPI.getItemStack(Material.LEASH,
                ColorParser.parse("&f任务系统 &c(Bate1.0)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o丰富的任务系统，完成并获得奖励.")
        );
        Button MissionButton = new Button(Mission, type -> {
            if (type.isLeftClick()) {
                new QuestGUI(player, QuestType.DAILY).openInventory();
            }
        });
        this.setButton(50, MissionButton);

        ItemStack Mysterious = ItemFactoryAPI.getItemStack(Material.CHEST,
                ColorParser.parse("&f神秘商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o各种丰富的商品，意想不到.")
        );
        Button MysteriousButton = new Button(Mysterious, type -> {
            if (type.isLeftClick()) {
                EeveeShop eeveeShop = new EeveeShop(player);
                eeveeShop.openInventory();
            }
        });
        this.setButton(51, MysteriousButton);


        ItemStack LiteSignIn = ItemFactoryAPI.getItemStack(Material.SIGN,
                ColorParser.parse("&f签到系统"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每日的打卡可以获得丰厚的奖励.")
        );
        Button LiteSignInButton = new Button(LiteSignIn, type -> {
            if (type.isLeftClick()) {
                Bukkit.dispatchCommand(player,"LiteSignIn gui");
            }
        });
        this.setButton(52, LiteSignInButton);

        ItemStack BanList = ItemFactoryAPI.getItemStack(Material.BARRIER,
                ColorParser.parse("&f封禁列表"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o查看服务器封禁了那些物品.")
        );
        Button BanListButton = new Button(BanList, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.pokeban.gui.BanList banMenu = new BanList(player,0);
                banMenu.openInventory();
            }
        });
        this.setButton(53, BanListButton);
    }

}
