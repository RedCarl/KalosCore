package red.kalos.core.packetlistener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import red.kalos.core.Main;

public class MessageAdapter extends PacketAdapter {
    public MessageAdapter() {
        super(Main.getInstance(), ListenerPriority.HIGHEST, PacketType.Play.Server.CHAT);
    }

    public void onPacketSending(PacketEvent event) {
        if (event.getPacket().getChatComponents().size() != 0) {
            if (event.getPacket().getChatComponents().read(0) != null) {
                if (event.getPacket().getChatComponents().read(0).getJson() != null) {
                    if (event.getPacket().getChatComponents().read(0).getJson().contains("chat.type.advancement.task")) {
                        event.setCancelled(true);
                    }

                }
            }
        }
    }
}