package kim.pokemon.kimexpand.crazylottery.action;

import com.pixelmonmod.pixelmon.Pixelmon;
import kim.pokemon.util.ColorParser;
import org.bukkit.entity.Player;

public class ActionMsg {


    public void sendLottery(Player player){
        player.sendTitle(ColorParser.parse(""),ColorParser.parse(""),20,40,20);


    }
}
