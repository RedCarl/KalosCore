package kim.pokemon.listener;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PokemonEvent implements Listener {
    @EventHandler
    public void onForgeEvent(final ForgeEvent forgeEvent) {
        if ((forgeEvent.getForgeEvent() instanceof SpawnEvent)) {
            SpawnEvent event = (SpawnEvent)forgeEvent.getForgeEvent();
            String world = event.action.spawnLocation.location.world.field_72986_A.func_76065_j();
            switch (world){
                case "plot":
                case "lobby":
                    event.setCanceled(true);
                    break;
                default:

                    break;
            }
        }
    }
}
