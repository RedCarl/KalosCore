package red.kalos.core.entity;

import com.google.common.collect.Iterables;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public interface NPCs {
    NPCRegistry REGISTRY = CitizensAPI.createNamedNPCRegistry("Kalos", new MemoryNPCDataStore());
    String PLAYER_SKIN = "kalos:player_skin";

    static NPC createNPC(Location location, List<String> texts, double height) {
        return createNPC(location, texts, height, npc -> {
        });
    }

    static NPC createNPC(Location location, List<String> texts, double height, Consumer<NPC> consumer) {
        if (texts == null) {
            texts = new ArrayList<>();
        }
        //temp name
        String name = Iterables.getFirst(texts, "§8[NPC] " + UUID.randomUUID().toString().split("-")[0]);
        NPC npc = REGISTRY.createNPC(EntityType.PLAYER, name);
        consumer.accept(npc);
        // real name
        npc.setName("§8[NPC] " + npc.getUniqueId().toString().split("-")[0]);
        if (!texts.isEmpty()) {
            HologramTrait hologramTrait = npc.getOrAddTrait(HologramTrait.class);
            hologramTrait.setLineHeight(height);
            hologramTrait.clear();
            for (String text : texts) {
                hologramTrait.addLine(text);
            }
        }
        npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);
        npc.spawn(location);
        return npc;
    }

    /**
     * 意为玩家看的这个npc皮肤是玩家自己的皮肤。
     */
    static NPC setSkinByPlayerSelf(NPC npc) {
        npc.data().set(PLAYER_SKIN, true);
        npc.getOrAddTrait(SkinTrait.class).setSkinName("Kalos", true);
        return npc;
    }

    /**
     * 调用后请再次刷新皮肤
     */
    static NPC removeSkinByPlayerSelf(NPC npc) {
        npc.data().remove(PLAYER_SKIN);
        npc.getOrAddTrait(SkinTrait.class).setSkinName("Kalos", true);
        return npc;
    }

    static NPC createNPC(Location location) {
        return createNPC(location, null, 0);
    }

    static NPC createNPC(Location location, List<String> texts) {
        return createNPC(location, texts, 0.25);
    }
}
