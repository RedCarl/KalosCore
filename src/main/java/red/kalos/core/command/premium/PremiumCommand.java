package red.kalos.core.command.premium;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import red.kalos.core.Main;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.premium.VIPBuy;
import red.kalos.core.manager.premium.entity.PlayerVIP;

import java.sql.Timestamp;

/**
 * @Author: carl0
 * @DATE: 2022/10/15 22:17
 */
public class PremiumCommand implements CommandExecutor {

    public PremiumCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        MainMenu mainMenu = new MainMenu(player);
        mainMenu.openInventory();

        if (player.hasPermission("kalos.admin") && strings.length>=1){
            switch (strings[0]){
                case "add":
                    if (strings[1]!=null && strings[2]!=null){
                        Player p = Bukkit.getPlayer(strings[1]);
                        PlayerVIP rank = new PlayerVIP();
                        PlayerVIP PikaniumVIP = VIPBuy.checkRank(p,strings[2], Main.getLuckPerms().getServerName());
                        rank.setName(p.getName());
                        rank.setRank(strings[2]);
                        if (PikaniumVIP!=null){
                            rank.setTime(new Timestamp(PikaniumVIP.getTime().getTime()+ 2592000000L));
                            rank.setServer(Main.getLuckPerms().getServerName());
                            VIPBuy.updateRank(rank);
                        }else {
                            rank.setTime(new Timestamp(System.currentTimeMillis()+ 2592000000L));
                            rank.setServer(Main.getLuckPerms().getServerName());
                            VIPBuy.addRank(rank);
                        }
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                        VIPBuy.checkRank(player,strings[2], Main.getLuckPerms().getServerName());
                    }
                    break;
            }
        }

        return true;
    }
}
