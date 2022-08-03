package red.kalos.core.util;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

public class ParticleUtils
{
    public static void displayBreakingBlocks(final Location location, final Material material, final int amount) {
        for (int i = 0; i < amount; ++i) {
            location.getWorld().playEffect(location, Effect.STEP_SOUND, (Object)material);
        }
    }
}
