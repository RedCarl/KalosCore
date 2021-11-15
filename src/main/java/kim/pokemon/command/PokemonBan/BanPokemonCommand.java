package kim.pokemon.command.PokemonBan;

import kim.pokemon.database.PokemonBanDataSQLReader;
import kim.pokemon.util.ColorParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanPokemonCommand implements CommandExecutor {
    public BanPokemonCommand() {}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String aliase, String[] args) {
        if (commandSender.hasPermission("kim.admin")){
            if (args.length == 2) {
                if (args[0].equals("add")) {
                    PokemonBanDataSQLReader.addPokemons(args[1]);
                    commandSender.sendMessage("封禁了宝可梦 " + args[1]);
                }

                if (args[0].equals("remove")) {
                    PokemonBanDataSQLReader.removePokemons(args[1]);
                    commandSender.sendMessage("解封了宝可梦 " + args[1]);
                }
            }
        }else {
            commandSender.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，只有管理员才可以进行操作。"));
        }
        return true;
    }
}
