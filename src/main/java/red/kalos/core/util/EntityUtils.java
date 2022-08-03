package red.kalos.core.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils
{
    public static List<Entity> getNearbyEntities(final Location location, final double range) {
        final List<Entity> entities = new ArrayList<Entity>();
        for (final Entity entity : location.getWorld().getEntities()) {
            if (entity.getLocation().distance(location) <= range) {
                entities.add(entity);
            }
        }
        return entities;
    }
}
