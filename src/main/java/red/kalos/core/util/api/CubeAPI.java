//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package red.kalos.core.util.api;

import java.util.ArrayList;
import java.util.Objects;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CubeAPI {
    private final int y1;
    private final int y2;
    private final World world;
    private int x1;
    private int x2;
    private int z1;
    private int z2;

    public CubeAPI(Location loc1, Location loc2) {
        this.world = loc1.getWorld();
        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();
        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();
        if (x1 > x2) {
            this.x1 = x1;
            this.x2 = x2;
        } else {
            this.x1 = x2;
            this.x2 = x1;
        }

        if (y1 > y2) {
            this.y1 = y1;
            this.y2 = y2;
        } else {
            this.y1 = y2;
            this.y2 = y1;
        }

        if (z1 > z2) {
            this.z1 = z1;
            this.z2 = z2;
        } else {
            this.z1 = z2;
            this.z2 = z1;
        }

    }

    public int getMaxX() {
        return this.x1;
    }

    public int getMinX() {
        return this.x2;
    }

    public int getMaxY() {
        return this.y1;
    }

    public int getMinY() {
        return this.y2;
    }

    public int getMaxZ() {
        return this.z1;
    }

    public int getMinZ() {
        return this.z2;
    }

    public ArrayList<Block> getBlocks() {
        if (this.x1 < 0) {
            --this.x1;
        }

        if (this.x2 < 0) {
            --this.x2;
        }

        if (this.z1 < 0) {
            --this.z1;
        }

        if (this.z2 < 0) {
            --this.z2;
        }

        ArrayList<Block> blocks = new ArrayList();

        for(int a = this.x2; a <= this.x1; ++a) {
            for(int b = this.y2; b <= this.y1; ++b) {
                for(int c = this.z2; c <= this.z1; ++c) {
                    Location location = new Location(this.world, (double)a, (double)b, (double)c);
                    if (location.getBlock().getType() != Material.AIR) {
                        blocks.add(location.getBlock());
                    }
                }
            }
        }

        if (this.x1 <= 0) {
            ++this.x1;
        }

        if (this.x2 <= 0) {
            ++this.x2;
        }

        if (this.z1 <= 0) {
            ++this.z1;
        }

        if (this.z2 <= 0) {
            ++this.z2;
        }

        return blocks;
    }

    public boolean isLocationInCube(Location loc) {
        int x = (int)loc.getX();
        int y = (int)loc.getY();
        int z = (int)loc.getZ();
        return this.x1 >= x && this.x2 <= x && this.y1 >= y && this.y2 <= y && this.z1 >= z && this.z2 <= z;
    }

    public Location getMaxLoc() {
        return new Location(this.world, (double)this.getMaxX(), (double)this.getMaxY(), (double)this.getMaxZ());
    }

    public Location getMinLoc() {
        return new Location(this.world, (double)this.getMinX(), (double)this.getMinY(), (double)this.getMinZ());
    }

    public boolean isPlayerInCube(Player p) {
        int x = (int)p.getLocation().getX();
        int y = (int)p.getLocation().getY();
        int z = (int)p.getLocation().getZ();
        return this.x1 >= x && this.x2 <= x && this.y1 >= y && this.y2 <= y && this.z1 >= z && this.z2 <= z;
    }

//    public static CubeAPI fromConfig(ConfigurationSection config) {
//        return new CubeAPI(CoreUtils.deSerializeLocation(config.getString("pos1")), CoreUtils.deSerializeLocation(config.getString("pos2")));
//    }
//
//    public static CubeAPI fromConfig(me.huanmeng.utils.configuration.ConfigurationSection config) {
//        return new CubeAPI(CoreUtils.deSerializeLocation(config.getString("pos1")), CoreUtils.deSerializeLocation(config.getString("pos2")));
//    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CubeAPI cube = (CubeAPI)o;
            return this.y1 == cube.y1 && this.y2 == cube.y2 && this.x1 == cube.x1 && this.x2 == cube.x2 && this.z1 == cube.z1 && this.z2 == cube.z2 && Objects.equals(this.world, cube.world);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.y1, this.y2, this.world, this.x1, this.x2, this.z1, this.z2});
    }

    public World getWorld() {
        return this.world;
    }
}
