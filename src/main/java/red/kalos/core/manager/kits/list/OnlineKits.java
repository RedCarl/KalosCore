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
import red.kalos.core.manager.kits.KitsGUI;
import red.kalos.core.manager.kits.KitsManager;
import red.kalos.core.manager.kits.entity.OnlineKitsEntity;
import red.kalos.core.util.ColorParser;
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
                "w",
                ColorParser.parse("w"),
                new String[]{
                        ColorParser.parse("wtest")
                },
                1L,
                new ItemStack[]{
                        ItemFactoryAPI.getItemStack(Material.STONE,10),
                        ItemFactoryAPI.getItemStack(Material.STONE,10)
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
