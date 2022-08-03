
package red.kalos.core.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.Random;

public class UnexpectedSpawn{
    Material[] b = {
            Material.AIR,
            Material.WATER,
            Material.STATIONARY_WATER,
            Material.MAGMA,
            Material.STATIONARY_LAVA,
            Material.LAVA,
            Material.BEDROCK
    };
    public Location randomLocation(World w) {
        int a = 0;
        do {

            Random r = new Random();
            int x = -9500 + r.nextInt(9500 - -9500 + 1);
            int z = -9500 + r.nextInt(9500 - -9500 + 1);
            int y = w.getHighestBlockYAt(x, z);

            Block block = w.getHighestBlockAt(x,z);

            if (w.getName().equals("DIM-1")){
                y = w.getMaxHeight()/2;
                return new Location(w, x, y, z);
            }


            if (w.getName().equals("DIM1")){
                x=0;
                z=0;
                y=70;
                return new Location(w, x, y, z);
            }

            if (Arrays.asList(b).contains(block.getType())){
                a++;
            }else {
                return new Location(w, x, y, z);
            }
        }while (a<=128);

        return null;
    }
}
