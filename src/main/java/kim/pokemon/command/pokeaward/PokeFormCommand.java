package kim.pokemon.command.pokeaward;

import kim.pokemon.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PokeFormCommand implements CommandExecutor {

	static File file = new File(Main.getInstance().getDataFolder(), "CustomSkin/data.yml");
	static FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

	public PokeFormCommand(){}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String aliase, String[] args) {
		if (sender.hasPermission("group.admin")){
			try {
				if(args.length==4){
					String playerName = args[0];
					String pokemon = args[1];
					String form = args[2];
					int addTime = Integer.parseInt(args[3]);
					int time = configuration.getInt("PokeAward."+playerName+"."+pokemon+"."+form);
					time += addTime;
					configuration.set("PokeAward."+playerName+"."+pokemon+"."+form,time);
					sender.sendMessage("玩家 "+playerName+" 为 "+pokemon+" 的 "+form+" 形态增加了 "+addTime+" 次次数");

					configuration.save(file);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public static void addPokemonForm(String playerName,String pokemon,String form,int addTime){
		try {
			int time = configuration.getInt("PokeAward."+playerName+"."+pokemon+"."+form);
			time += addTime;
			configuration.set("PokeAward."+playerName+"."+pokemon+"."+form,time);
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
