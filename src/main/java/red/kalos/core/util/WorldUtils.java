package red.kalos.core.util;

import red.kalos.core.Main;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class WorldUtils {
    public static void spawnFirework(final Location location, final FireworkEffect.Type type, final boolean flicker, final boolean trail, final int power, final List<Color> colors, final List<Color> fadeColors) {
        final Firework firework = (Firework)location.getWorld().spawn(location, (Class)Firework.class);
        firework.setMetadata(Main.getInstance().getDescription().getName(), new FixedMetadataValue(Main.getInstance(), true));
        final FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder().with(type).flicker(flicker).trail(trail).withColor(colors).withFade(fadeColors).build());
        fireworkMeta.setPower((power == 0) ? 1 : power);
        firework.setFireworkMeta(fireworkMeta);
        new BukkitRunnable() {
            public void run() {
                firework.detonate();
            }
        }.runTaskLater(Main.getInstance(), 2L);
    }
}
