package kim.pokemon.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class LocationUtils
{
    public static Location getFixedLocation(final Location location, final BlockFace blockFace) {
        for (int i = 0; i < location.getWorld().getMaxHeight() && location.getBlock().isEmpty(); ++i) {
            location.add(0.0, (blockFace == BlockFace.UP) ? 1.0 : -1.0, 0.0);
        }
        return location.add(0.0, (blockFace == BlockFace.UP) ? -1.0 : 1.0, 0.0);
    }
    
    public static Location readLongFromConfig(final FileConfiguration fileConfiguration, String path) {
        if (path.charAt(path.length() - 1) == '.') {
            path = path.substring(0, path.length() - 1);
        }
        final World world = Bukkit.getWorld(fileConfiguration.getString(path + ".world"));
        if (world == null) {
            return null;
        }
        final double x = fileConfiguration.getDouble(path + ".x");
        final double y = fileConfiguration.getDouble(path + ".y");
        final double z = fileConfiguration.getDouble(path + ".z");
        final float yaw = (float)fileConfiguration.getDouble(path + ".yaw");
        final float pitch = (float)fileConfiguration.getDouble(path + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }
    
    public static void writeLongToConfig(final FileConfiguration fileConfiguration, String path, final Location location) {
        if (path.charAt(path.length() - 1) == '.') {
            path = path.substring(0, path.length() - 1);
        }
        fileConfiguration.set(path + ".world", (Object)location.getWorld().getName());
        fileConfiguration.set(path + ".x", (Object)location.getX());
        fileConfiguration.set(path + ".y", (Object)location.getY());
        fileConfiguration.set(path + ".z", (Object)location.getZ());
        fileConfiguration.set(path + ".yaw", (Object)location.getYaw());
        fileConfiguration.set(path + ".pitch", (Object)location.getPitch());
    }
    
    public static Location readShortFromConfig(final FileConfiguration fileConfiguration, final String path) {
        final String[] strings = fileConfiguration.getString(path).split(";");
        return new Location(Bukkit.getWorld(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]), Double.parseDouble(strings[3]), Float.parseFloat(strings[4]), Float.parseFloat(strings[5]));
    }
    
    public static void writeShortToConfig(final FileConfiguration fileConfiguration, final String path, final Location location) {
        fileConfiguration.set(path, (Object)(location.getWorld() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getPitch()));
    }
    
    public static List<Location> getCircle(final Location location, final double radius, final int points) {
        final List<Location> locations = new ArrayList<Location>();
        final double increment = 6.283185307179586 / points;
        for (int i = 0; i < points; ++i) {
            final double angle = i * increment;
            final double x = location.getX() + Math.cos(angle) * radius;
            final double z = location.getZ() + Math.sin(angle) * radius;
            locations.add(new Location(location.getWorld(), x, location.getY(), z));
        }
        return locations;
    }
    
    public static List<Block> getSphere(final Location location, final int radius) {
        final List<Block> blocks = new ArrayList<Block>();
        final int X = location.getBlockX();
        final int Y = location.getBlockY();
        final int Z = location.getBlockZ();
        final int radiusSquared = radius * radius;
        for (int x = X - radius; x <= X + radius; ++x) {
            for (int y = Y - radius; y <= Y + radius; ++y) {
                for (int z = Z - radius; z <= Z + radius; ++z) {
                    if ((X - x) * (X - x) + (Z - z) * (Z - z) <= radiusSquared) {
                        blocks.add(location.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }
    
    public static List<Block> getCube(final Location location, final int radius) {
        final List<Block> blocks = new ArrayList<Block>();
        final int X = location.getBlockX() - radius / 2;
        final int Y = location.getBlockY() - radius / 2;
        final int Z = location.getBlockZ() - radius / 2;
        for (int x = X; x < X + radius; ++x) {
            for (int y = Y; y < Y + radius; ++y) {
                for (int z = Z; z < Z + radius; ++z) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}
