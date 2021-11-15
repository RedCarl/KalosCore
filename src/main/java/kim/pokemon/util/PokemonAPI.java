package kim.pokemon.util;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PCStorage;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.comm.packetHandlers.OpenScreen;
import com.pixelmonmod.pixelmon.comm.packetHandlers.clientStorage.newStorage.pc.ClientChangeOpenPC;
import com.pixelmonmod.pixelmon.comm.packetHandlers.clientStorage.newStorage.pc.ClientInitializePC;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Moveset;
import com.pixelmonmod.pixelmon.enums.EnumGuiScreen;
import kim.pokemon.Main;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PokemonAPI {
    /**
     * 获取宝可梦是不是闪光
     * @param pokemon 宝可梦
     * @return 自定义内容
     */
    public static String getPokemonName(Pokemon pokemon){

        String pokemonName = pokemon.getLocalizedName();

        if (pokemon.isLegendary()){
            pokemonName=pokemonName+" &c[传奇]";
        }

        if (pokemon.isShiny()){
            pokemonName="&e"+pokemonName;
        }

        return ColorParser.parse("&f"+pokemonName);
    }

    /**
     * 获取宝可梦是不是闪光
     * @param pokemon 宝可梦
     * @return 自定义内容
     */
    public static String isShiny(Pokemon pokemon){
        if (pokemon.isShiny()){
            return ColorParser.parse("&a✔");
        }
        return ColorParser.parse("&c✘");
    }

    /**
     * 获取宝可梦是不是闪光
     * @param attack 招式内容
     * @return 招式名 或者 无招式
     */
    public static String getMove(Attack attack){
        if (attack != null){
            return attack.getMove().getLocalizedName();
        }
        return "&c无招式";
    }


    public static void openPlayerPC(Player player){
        Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
            EntityPlayerMP entityPlayerMP = Pixelmon.storageManager.getParty(player.getUniqueId()).getPlayer();
            PCStorage pc = Pixelmon.storageManager.getPC(entityPlayerMP, null);
            Pixelmon.network.sendTo(new ClientInitializePC(pc), entityPlayerMP);
            Pixelmon.network.sendTo(new ClientChangeOpenPC(pc.uuid), entityPlayerMP);
            pc.sendContents(entityPlayerMP);
            OpenScreen.open(entityPlayerMP, EnumGuiScreen.PC);
        });
    }
}
