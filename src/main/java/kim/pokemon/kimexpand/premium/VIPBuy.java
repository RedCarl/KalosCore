package kim.pokemon.kimexpand.premium;

import kim.pokemon.Main;
import kim.pokemon.database.PremiumPlayerDataSQLReader;
import kim.pokemon.kimexpand.premium.entity.PlayerVIP;
import kim.pokemon.util.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class VIPBuy {

    /**
     * 开通Rank
     * @param playerVIP 开通玩家
     */
    public static void addRank(PlayerVIP playerVIP){
        Main.getInstance().getPremiumPlayerDataSQLReader().addRank(playerVIP);
    }

    /**
     * 续费Rank
     * @param playerVIP 续费玩家
     */
    public static void updateRank(PlayerVIP playerVIP){
        Main.getInstance().getPremiumPlayerDataSQLReader().updateRank(playerVIP);
    }

    /**
     * 查询Rank
     */
    public static PlayerVIP checkRank(Player player,String rank,String server){
        PlayerVIP vip = Main.getInstance().getPremiumPlayerDataSQLReader().getRank(player.getName(),rank,server);
        if (vip!=null){
            if (Objects.equals(server, vip.getServer())){
                if (Objects.equals(rank, vip.getRank())){
                    if (vip.getTime().getTime()>=System.currentTimeMillis()){
                        if (!player.hasPermission("group."+vip.getRank())){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" parent set "+vip.getRank()+" server="+ Main.luckPerms.getServerName());
                        }
                        return vip;
                    }else {
                        deleteRank(player);
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7训练师，你的头衔过期了，想保留特权请前往商城再次购买哦!"));
                        return null;
                    }
                }else {
                    return vip;
                }

            }
        }
        return null;
    }

    /**
     * 删除Rank
     */
    public static void deleteRank(Player player){
        Main.getInstance().getPremiumPlayerDataSQLReader().deleteRank(player);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" parent clear server="+ Main.luckPerms.getServerName());

    }
}
