package red.kalos.core.listener;

import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import red.kalos.core.manager.buildmanager.BuildGUI;
import red.kalos.core.manager.crazyauctions.api.Category;
import red.kalos.core.manager.crazyauctions.api.ShopType;
import red.kalos.core.manager.crazyauctions.controllers.GUI;
import red.kalos.core.manager.help.Newbie;
import red.kalos.core.manager.kits.KitsGUI;
import red.kalos.core.manager.questmanager.QuestGUI;
import red.kalos.core.manager.questmanager.quest.QuestType;
import red.kalos.core.manager.recharge.recharge.RechargeMenu;
import red.kalos.core.manager.shop.ItemBuy;
import red.kalos.core.manager.shop.ItemSell;
import red.kalos.core.util.BungeeUtils;

/**
 * @Author: carl0
 * @DATE: 2022/8/3 20:24
 */
public class CitizensEvent implements Listener {

    @EventHandler
    public void NPCLeftClickEvent(NPCLeftClickEvent event){
        ClickNPCEvent(event.getNPC(),event.getClicker(),0);
    }

    @EventHandler
    public void NPCRightClickEvent(NPCRightClickEvent event){
        ClickNPCEvent(event.getNPC(),event.getClicker(),1);
    }

    public void ClickNPCEvent(NPC npc, Player player,int type){
        if (npc.data().get("kalos_spawn").equals("签到")){

            if (player.hasPermission("kim.newbie.G")){
                Bukkit.dispatchCommand(player,"LiteSignIn gui");
            }else {
                new Newbie(player).openInventory();
            }

        }

        if (npc.data().get("kalos_spawn").equals("礼物")){
            KitsGUI kitsGUI = new KitsGUI(player);
            kitsGUI.openInventory();
        }

        if (npc.data().get("kalos_spawn").equals("市场")){
            GUI.openShop(player, ShopType.SELL, Category.NONE, 1);
        }


        if (npc.data().get("kalos_spawn").equals("任务")){
            new QuestGUI(player, QuestType.DAILY).openInventory();
        }

        if (npc.data().get("kalos_spawn").equals("收购")){
            new ItemSell(player).openInventory();
        }

        if (npc.data().get("kalos_spawn").equals("出售")){
            new ItemBuy(player).openInventory();
        }

        if (npc.data().get("kalos_spawn").equals("支持")){
            new RechargeMenu(player).openInventory();
        }

        if (npc.data().get("kalos_spawn").equals("建筑")){
            new BuildGUI(player,0).openInventory();
        }

        if (npc.data().get("kalos_spawn").equals("锦标赛")){
            if (type==0) {
                Bukkit.dispatchCommand(player,"kalosrank:kalosrank rank");
            }
            if (type==1) {
                Bukkit.dispatchCommand(player,"kalosrank:kalosrank cancel");
            }
        }

        if (npc.data().get("kalos_spawn").equals("小游戏")){
            BungeeUtils.sendPlayerToServer(player,"arcade");
        }
    }

}
