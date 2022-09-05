package red.kalos.core.command.menu;

import red.kalos.core.manager.item.CustomItem;
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

        if (player.hasPermission("kalos.admin") && strings.length==1){
            switch (strings[0]){
                case "eevee":
                    player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.EeveeKit, "RandomEeveeKit"));
                    break;
                case "pikanium":
                    player.getInventory().addItem(CustomItem.getEncryptionItem(CustomItem.PikachuKit, "RandomPikachuKit"));
                    break;
            }
        }

        return true;
    }
}
