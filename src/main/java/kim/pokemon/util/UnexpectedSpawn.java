//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kim.pokemon.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;

import java.util.Random;

public class UnexpectedSpawn implements Listener {
    public Location randomLocation(World w) {
        Random r = new Random();
        int x = -9500 + r.nextInt(9500 - -9500 + 1);
        int z = -9500 + r.nextInt(9500 - -9500 + 1);
        int y = w.getHighestBlockYAt(x, z);
        return new Location(w, x, y, z);
    }
}
