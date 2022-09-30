package red.kalos.core.manager.kits.list;

import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.entity.PlayerData;
import red.kalos.core.manager.item.CustomItem;
import red.kalos.core.manager.kits.KitsGUI;
import red.kalos.core.manager.kits.KitsManager;
import red.kalos.core.manager.kits.entity.OnlineKitsEntity;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.DateUtil;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: carl0
 * @DATE: 2022/8/17 17:27
 */
public class OnlineKits extends InventoryGUI {
    PlayerPointsAPI ppAPI = Main.getPpAPI();
    Economy economy = Main.getEcon();
    List<OnlineKitsEntity> list = new ArrayList<>();

    public OnlineKits(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 在线奖励"), player, 6);

        PlayerData playerData = PlayerDataManager.getInstance().getPlayerData(player.getUniqueId());

        list.add(new OnlineKitsEntity(
                "TIME_100",
                ColorParser.parse("&7累计在线 &a100 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   高级球*64"),
                        ColorParser.parse("&f   大师球*2"),
                        ColorParser.parse("&f   GS球*1"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                360000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),64),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),2),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GS_BALL"),1),
                },
                0.0,
                0,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_200",
                ColorParser.parse("&7累计在线 &a200 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   大师球*5"),
                        ColorParser.parse("&f   GS球*2"),
                        ColorParser.parse("&f   幸运方块*20"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                720000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GS_BALL"),2),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),20),
                },
                0.0,
                0,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_300",
                ColorParser.parse("&7累计在线 &a300 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   大师球*5"),
                        ColorParser.parse("&f   GS球*3"),
                        ColorParser.parse("&f   幸运方块*20"),
                        ColorParser.parse("&f   红线*1"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                1080000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GS_BALL"),3),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),20),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DESTINY_KNOT"),1),
                },
                0.0,
                0,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_400",
                ColorParser.parse("&7累计在线 &a400 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   大师球*5"),
                        ColorParser.parse("&f   GS球*5"),
                        ColorParser.parse("&f   幸运方块*20"),
                        ColorParser.parse("&f   纯净珠*2"),
                        ColorParser.parse("&f   卡洛币*5w"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                1440000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GS_BALL"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),20),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ORB"),2),
                },
                50000.0,
                0,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_500",
                ColorParser.parse("&7累计在线 &a500 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   大师球*5"),
                        ColorParser.parse("&f   幸运方块*64"),
                        ColorParser.parse("&f   卡洛币*10w"),
                        ColorParser.parse("&f   卡点*10"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                1800000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),64),
                },
                100000.0,
                10,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_600",
                ColorParser.parse("&7累计在线 &a600 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   大师球*8"),
                        ColorParser.parse("&f   纯净珠*5"),
                        ColorParser.parse("&f   GS球*5"),
                        ColorParser.parse("&f   红线*1"),
                        ColorParser.parse("&f   卡洛币*5w"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                2160000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),8),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ORB"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GS_BALL"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DESTINY_KNOT"),1),
                },
                50000.0,
                0,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_700",
                ColorParser.parse("&7累计在线 &a700 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   大师球*8"),
                        ColorParser.parse("&f   纯净珠*5"),
                        ColorParser.parse("&f   GS球*5"),
                        ColorParser.parse("&f   幸运方块*32"),
                        ColorParser.parse("&f   卡洛币*5w"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                2520000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),8),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ORB"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GS_BALL"),5),
                        ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),32),
                },
                50000.0,
                0,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_800",
                ColorParser.parse("&7累计在线 &a800 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   时装碎片*100"),
                        ColorParser.parse("&f   幸运方块*64"),
                        ColorParser.parse("&f   卡洛币*10w"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                2880000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),64),
                        ItemFactoryAPI.getItemStack(CustomItem.Armourers,100),
                },
                50000.0,
                0,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_900",
                ColorParser.parse("&7累计在线 &a900 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   卡点*20"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                3240000L,
                new ItemStack[]{
                },
                0.0,
                20,
                null
        ));

        list.add(new OnlineKitsEntity(
                "TIME_1000",
                ColorParser.parse("&7累计在线 &a1000 &7小时礼包"),
                new String[]{
                        ColorParser.parse("&r"),
                        ColorParser.parse("&8当前: "+ DateUtil.getDate(playerData.getPlayTime())),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7内容:"),
                        ColorParser.parse("&f   幸运方块*64"),
                        ColorParser.parse("&f   神兽宝箱*1"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7领取时请注意背包是否有空位。"),
                },
                3600000L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),64),
                        ItemFactoryAPI.getItemStack(CustomItem.getEncryptionItem(CustomItem.RandomLegend,"RandomLegend"),1),
                },
                0.0,
                0,
                null
        ));
















        int i = 0;
        for (OnlineKitsEntity o:list) {
            //能不能领取
            if (playerData.getPlayTime()>=o.getTime()){
                //是不是领取过了
                if (KitsManager.isGetKits(o.getId(),player)){
                    this.setButton(i,new Button(ItemFactoryAPI.getItemStack(
                            Material.STORAGE_MINECART,
                            o.getName(),
                            true,
                            o.getLores()
                    ),type -> {

                        if (o.getMoney()>0){
                            economy.depositPlayer(player,o.getMoney());
                        }
                        if (o.getPoints()>0){
                            ppAPI.giveAsync(player.getUniqueId(),o.getPoints());
                        }
                        if (o.getPermission()!=null){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+o.getPermission()+" server="+ Main.getLuckPerms().getServerName());
                        }
                        if (o.getItems().length!=0){
                            for (ItemStack item:o.getItems()) {
                                player.getInventory().addItem(item);
                            }
                        }

                        KitsManager.getKits(player,o.getId());
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您成功领取了 &c"+o.getName()+" &7礼包，请注意查看背包！"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                        new OnlineKits(player).openInventory();
                    }));
                }else {
                    this.setButton(i,new Button(ItemFactoryAPI.getItemStack(
                            Material.MINECART,
                            ColorParser.parse("&7这个奖励您已经领取过了。")
                    )));
                }
            }else {
                this.setButton(i,new Button(ItemFactoryAPI.getItemStack(
                        Material.STORAGE_MINECART,
                        ColorParser.parse(o.getName()+" &c[未满足要求]"),
                        o.getLores()
                )));
            }

            i++;
        }





        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至菜单。"));
        Button CloseButton = new Button(Close, type -> {
            new KitsGUI(player).openInventory();
        });
        this.setButton(53, CloseButton);
    }
}
