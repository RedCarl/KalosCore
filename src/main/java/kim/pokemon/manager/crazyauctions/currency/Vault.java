package kim.pokemon.manager.crazyauctions.currency;

import kim.pokemon.Main;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Vault {
    
    public static Economy econ = Main.econ;
    public static EconomyResponse r;
    
    public static Long getMoney(Player player) {
        if (player != null) {
            try {
                return (long) econ.getBalance(player);
            } catch (NullPointerException ignore) {
            }
        }
        return 0L;
    }
    
    public static void removeMoney(Player player, Long amount) {
        econ.withdrawPlayer(player, amount);
    }
    
    public static void removeMoney(OfflinePlayer player, Long amount) {
        econ.withdrawPlayer(player, amount);
    }
    
    public static void addMoney(Player player, Long amount) {
        econ.depositPlayer(player, amount);
    }
    
    public static void addMoney(OfflinePlayer player, Long amount) {
        econ.depositPlayer(player, amount);
    }
    
}