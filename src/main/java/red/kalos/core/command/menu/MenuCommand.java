package red.kalos.core.command.menu;

import red.kalos.core.manager.menu.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

    public MenuCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        MainMenu mainMenu = new MainMenu(player);
        mainMenu.openInventory();
        return true;
    }
}