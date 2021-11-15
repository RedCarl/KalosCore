package kim.pokemon.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.plugin.java.JavaPlugin;

public class TitlePlaceholderAPI extends PlaceholderExpansion {

	JavaPlugin javaPlugin;

	public TitlePlaceholderAPI(JavaPlugin javaPlugin){
		this.javaPlugin=javaPlugin;
	}

	@Override
	public @org.jetbrains.annotations.NotNull String getIdentifier() {
		return "KimCore";
	}

	@Override
	public @org.jetbrains.annotations.NotNull String getAuthor() {
		return "Red_Carl";
	}

	@Override
	public @org.jetbrains.annotations.NotNull String getVersion() {
		return "1.0";
	}
}
