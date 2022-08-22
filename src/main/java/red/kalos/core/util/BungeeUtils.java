package red.kalos.core.util;

import org.bukkit.entity.Player;
import red.kalos.core.Main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeUtils {


    public static void sendMessage(Player player, String message){

        ByteArrayOutputStream b = new ByteArrayOutputStream();

        DataOutputStream out= new DataOutputStream(b);

        try{

            out.writeUTF("Forward");

            out.writeUTF(message);

        } catch (Exception e) {

            e.printStackTrace();

        }

        player.sendPluginMessage(Main.getInstance(),"Waterfall",b.toByteArray());

    }

    public static void sendPlayerToServer(Player player, String server){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out= new DataOutputStream(b);
        try{
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(Main.getInstance(),"BungeeCord",b.toByteArray());

    }
}
