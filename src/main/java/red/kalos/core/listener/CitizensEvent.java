package red.kalos.core.listener;

import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import red.kalos.core.manager.kits.PlayerKits;

/**
 * @Author: carl0
 * @DATE: 2022/8/3 20:24
 */
public class CitizensEvent implements Listener {

    @EventHandler
    public void NPCLeftClickEvent(NPCLeftClickEvent event){
        ClickNPCEvent(event.getNPC(),event.getClicker());
    }

    @EventHandler
    public void NPCRightClickEvent(NPCRightClickEvent event){
        ClickNPCEvent(event.getNPC(),event.getClicker());
    }

    public void ClickNPCEvent(NPC npc, Player player){
        if (npc.data().get("kalos_spawn").equals("签到")){
            Bukkit.dispatchCommand(player,"LiteSignIn gui");
        }

        if (npc.data().get("kalos_spawn").equals("礼物")){
            PlayerKits playerKits = new PlayerKits(player);
            playerKits.openInventory();
        }
    }
}