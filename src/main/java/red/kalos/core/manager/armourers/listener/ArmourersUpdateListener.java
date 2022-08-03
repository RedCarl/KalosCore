package red.kalos.core.manager.armourers.listener;

import com.google.common.collect.Lists;
import eos.moe.armourers.api.PlayerSkinUpdateEvent;
import red.kalos.core.manager.armourers.ArmourersManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class ArmourersUpdateListener implements  Listener{
    @EventHandler
    public void onPlayerUpdateSkin(PlayerSkinUpdateEvent evt){
        List<String> list = Lists.newArrayList();
        list.addAll(ArmourersManager.getPlayerArmourers(evt.getPlayer()));
        evt.setSkinList(list);
    }
}
