//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kim.pokemon.command.PokemonBan;

import kim.pokemon.database.PokemonBanDataSQLReader;
import kim.pokemon.kim.PokemonBan;
import kim.pokemon.util.ColorParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanItemCommand implements CommandExecutor {
    public BanItemCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String aliase, String[] args) {

        if (commandSender.hasPermission("kim.admin")){
            if (args.length == 1 && commandSender instanceof Player && ((Player)commandSender).getInventory().getItemInMainHand()!=null) {
                if (args[0].equals("add")) {
                    PokemonBanDataSQLReader.addDrops(((Player)commandSender).getInventory().getItemInMainHand().getType().name());
                    commandSender.sendMessage("封禁了宝可梦掉落物 " + ((Player)commandSender).getInventory().getItemInMainHand().getType().name());
                }

                if (args[0].equals("remove")) {
                    PokemonBanDataSQLReader.removeDrops(((Player)commandSender).getInventory().getItemInMainHand().getType().name());
                    commandSender.sendMessage("解封了宝可梦掉落物 " + ((Player)commandSender).getInventory().getItemInMainHand().getType().name());
                }
            }

            if (args.length == 2) {
                if (args[0].equals("add")) {
                    PokemonBanDataSQLReader.addDrops(args[1]);
                    commandSender.sendMessage("封禁了宝可梦掉落物 " + args[1]);
                }

                if (args[0].equals("remove")) {
                    PokemonBanDataSQLReader.removeDrops(args[1]);
                    commandSender.sendMessage("解封了宝可梦掉落物 " + args[1]);
                }
            }
        }else {
            commandSender.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，只有管理员才可以进行操作。"));
        }
        return true;
    }
}
