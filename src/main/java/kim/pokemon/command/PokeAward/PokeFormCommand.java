package kim.pokemon.command.PokeAward;

import kim.pokemon.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

public class PokeFormCommand implements CommandExecutor {

	public PokeFormCommand(){}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String aliase, String[] args) {
		if(args.length==4){
			String playerName = args[0];
			String pokemon = args[1];
			String form = args[2];
			int addTime = Integer.parseInt(args[3]);
			Configuration configuration = Main.getInstance().getConfig();
			int time = configuration.getInt("PokeAward."+playerName+"."+pokemon+"."+form);
			time += addTime;
			configuration.set("PokeAward."+playerName+"."+pokemon+"."+form,time);
			Main.getInstance().saveConfig();
			sender.sendMessage("玩家 "+playerName+" 为 "+pokemon+" 的 "+form+" 形态增加了 "+addTime+" 次次数");
		}
		return true;
	}

	public static void addPokemonForm(String playerName,String pokemon,String form,int addTime){
		Configuration configuration = Main.getInstance().getConfig();
		int time = configuration.getInt("PokeAward."+playerName+"."+pokemon+"."+form);
		time += addTime;
		configuration.set("PokeAward."+playerName+"."+pokemon+"."+form,time);
		Main.getInstance().saveConfig();
	}
}
