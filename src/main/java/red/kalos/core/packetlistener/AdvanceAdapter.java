package red.kalos.core.packetlistener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import red.kalos.core.Main;

public class AdvanceAdapter extends PacketAdapter {
    public AdvanceAdapter() {
        super(Main.getInstance(), ListenerPriority.HIGHEST, PacketType.Play.Server.ADVANCEMENTS);
    }

    public void onPacketSending(PacketEvent event) {
        event.setCancelled(true);
    }

    public void onPacketReceiving(PacketEvent event) {
    }
}