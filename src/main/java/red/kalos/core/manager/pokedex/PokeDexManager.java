package red.kalos.core.manager.pokedex;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import red.kalos.core.Main;
import red.kalos.core.util.ColorParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: carl0
 * @DATE: 2022/8/17 19:22
 */
public class PokeDexManager {
    private static final PokeDexManager instance = new PokeDexManager();

    public static PokeDexManager getInstance() {
        return instance;
    }

    /**
     * 试图增加新图鉴
     * @param pokemon 宝可梦
     * @param player 玩家
     * @return 是否成功
     */
    public boolean addPokeDex(Pokemon pokemon, Player player){
        File file = new File(Main.getInstance().getDataFolder(), "PokeDex/"+player.getUniqueId()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        List<String> list = data.getStringList("list");
        if (!list.contains(pokemon.getUnlocalizedName())){
            list.add(pokemon.getUnlocalizedName());
            data.set("list",list);
            try {
                data.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功找到了 &a"+pokemon.getLocalizedName()+" &7宝可梦，并将它记录进入宝可梦图鉴。"));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            return true;
        }
        return false;
    }

    /**
     * 获取该玩家的图鉴内容
     * @param player 玩家
     * @return 图鉴集合
     */
    public List<String> getPokeDex(Player player){
        File file = new File(Main.getInstance().getDataFolder(), "PokeDex/"+player.getUniqueId()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getStringList("list");
    }
}
