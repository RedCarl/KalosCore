package kim.pokemon.kimexpand.menu;

import com.Zrips.CMI.CMI;
import de.tr7zw.nbtapi.NBTItem;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.database.GlazedPayDataSQLReader;
import kim.pokemon.kimexpand.armourers.guis.ArmourersGUI;
import kim.pokemon.kimexpand.crazyauctions.api.Category;
import kim.pokemon.kimexpand.crazyauctions.api.ShopType;
import kim.pokemon.kimexpand.crazyauctions.controllers.GUI;
import kim.pokemon.kimexpand.homes.HomeMenu;
import kim.pokemon.kimexpand.plotadmin.gui.PlotMenu;
import kim.pokemon.kimexpand.pokeban.gui.BanList_A;
import kim.pokemon.kimexpand.pokeinfo.gui.PokemonInfoMenu;
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

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainMenu extends InventoryGUI {
    public static HashMap<Player,Long> HealSleep = new HashMap<>();

    public MainMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 菜单"), player, 6);
        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 54; i++) {
            this.setButton(i, new Button(Line));
        }

        ItemStack PlayerInfo = null;
        try {
            PlayerInfo = SkullAPI.getPlayerSkull(player,
                    ColorParser.parse("&b&l"+Data.SERVER_NAME_CN+"の宝可梦 &c(v"+Main.getInstance().getDescription().getVersion()+")"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&3玩家信息:"),
                    ColorParser.parse("&r  &a■ &7余额:"),
                    ColorParser.parse("&r      &3" + CMI.getInstance().getPlayerManager().getUser(player).getBalance() + " &7"+Data.SERVER_VAULT+""),
                    ColorParser.parse("&r      &3" + Main.ppAPI.lookAsync(player.getUniqueId()).get() + ".0 &7"+Data.SERVER_POINTS+""),
                    ColorParser.parse("&r  &b■ &7累计充值: &3" + GlazedPayDataSQLReader.getPlayer(player.getName()).getAmount() + " &7RMB"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&3世界信息:"),
                    ColorParser.parse("&r  &c■ &7传说宝可梦:"),
                    ColorParser.parse("&r      &315.3% &7概率"),
                    ColorParser.parse("&r      &3216000tick &7周期"),
                    ColorParser.parse("&r  &d■ &7BOSS可梦:"),
                    ColorParser.parse("&r      &315.0% &7概率"),
                    ColorParser.parse("&r      &310000tick &7周期"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o您可以查看一些与您相关的信息.")
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Button PlayerInfoButton = new Button(PlayerInfo, type -> {
            if (type.isLeftClick()) {
                ArmourersGUI armourersGUI = new ArmourersGUI(player);
                armourersGUI.openInventory();
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
                ColorParser.parse("&f活动事件"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您可以看到本服务器所有的活动详情.")
        );
        Button ClockButton = new Button(Clock, type -> {
            if (type.isLeftClick()) {
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
                ColorParser.parse("&7&o您可以就地对背包的 &a宝可梦 &7&o进行治疗.")
        );
        Button HealButton = new Button(Heal, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                PokemonAPI.setPokemonStater(player);
                if (HealSleep.containsKey(player)){
                    if (System.currentTimeMillis()-HealSleep.get(player)>=120000){
                        PokemonAPI.setPokemonStater(player);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7抱歉，您必须等待 &c2 &7分钟才能使用该功能."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                }else {
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
                ColorParser.parse("&r      &7圾桶,这样"+Data.SERVER_NAME_CN+"会给你报酬."),
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
                if (player.hasPermission("cmi.command.fly")){
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
                ColorParser.parse("&f传说查询 &7/ &6会员专属"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o查询该区域下会生成的宝可梦.")
        );
        Button VIPCheckSpawnsButton = new Button(VIPCheckSpawns, type -> {
            if (type.isLeftClick()) {
                if (player.hasPermission("group.eevee")){
                    player.sendMessage(ColorParser.parse("&r"));
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7正在为您查询这片区域的传说宝可梦..."));
                    Bukkit.dispatchCommand(player,"checkspawns legendary");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    player.closeInventory();
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您无法使用该功能."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.closeInventory();
                }
            }
        });
        this.setButton(30, VIPCheckSpawnsButton);


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
                BanList_A banMenu = new BanList_A(player);
                banMenu.openInventory();
            }
        });
        this.setButton(53, BanListButton);
    }
}
