package kim.pokemon.ui;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import de.tr7zw.nbtapi.NBTItem;
import kim.pokemon.Main;
import kim.pokemon.database.GlazedPayDataSQLReader;
import kim.pokemon.ui.banlist.BanList_A;
import kim.pokemon.ui.plotadmin.PlotMenu;
import kim.pokemon.ui.pokemoninfo.PokemonInfoMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ExecutionException;

public class MainMenu extends InventoryGUI {
    public MainMenu(Player player) {
        super(ColorParser.parse("&0Kim / 菜单"), player, 6);
        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 54; i++) {
            this.setButton(i, new Button(Line));
        }

        ItemStack PlayerInfo = null;
        try {
            PlayerInfo = SkullAPI.getPlayerSkull(player,
                    ColorParser.parse("&f玩家信息"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&f信  息:"),
                    ColorParser.parse("&r  &e■ &7余额:"),
                    ColorParser.parse("&r      &3" + Main.econ.getBalance(player) + " &7萌币"),
                    ColorParser.parse("&r      &3" + Main.ppAPI.lookAsync(player.getUniqueId()).get() + ".0 &7萌点"),
                    ColorParser.parse("&r  &e■ &7充值: &3" + GlazedPayDataSQLReader.getPlayer(player.getName()).getAmount() + " &7RMB")
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        this.setButton(10, new Button(PlayerInfo));

        ItemStack Recharge = ItemFactoryAPI.getItemStack(Material.EMERALD,
                ColorParser.parse("&f充值中心"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o在这里可以对服务器进行赞助,并获得回馈.")
        );
        Button RechargeButton = new Button(Recharge, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该功能还未启用."));
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
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该功能还未启用."));
            }
        });
        this.setButton(28, TeleportationButton);

        ItemStack Clock = ItemFactoryAPI.getItemStack(Material.COOKIE,
                ColorParser.parse("&f公共区域"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您可以将您的位置设置作为公共区域,其他玩家可以传送至此.")
        );
        Button ClockButton = new Button(Clock, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该功能还未启用."));
            }
        });
        this.setButton(37, ClockButton);


        ItemStack Spyglass = ItemFactoryAPI.getItemStack(Material.PAINTING,
                ColorParser.parse("&f全球市场"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o在这里与全服的玩家进行交易.")
        );
        Button SpyglassButton = new Button(Spyglass, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该功能还未启用."));
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
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该功能还未启用."));
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
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该功能还未启用."));
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
                PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(player.getUniqueId());

                boolean isNull=true;
                for (Pokemon p:playerPartyStorage.getAll()) {
                    if (p!=null){
                        if (p.getHealth() != p.getMaxHealth()){
                            p.setHealth(p.getMaxHealth());
                            isNull=false;
                        }
                    }
                }

                if (isNull){
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包里面没有宝可梦需要治疗."));
                }else {
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7背包里的宝可梦们都恢复健康了!"));
                }

            }
        });
        this.setButton(22, HealButton);

        ItemStack PC = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PC"),
                ColorParser.parse("&f宝可梦电脑"),
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
