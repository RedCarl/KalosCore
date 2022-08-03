package kim.pokemon.manager.premium;

import kim.pokemon.Main;
import kim.pokemon.database.PlayerDataManager;
import kim.pokemon.entity.PlayerData;
import kim.pokemon.entity.RankData;
import kim.pokemon.manager.premium.entity.PlayerVIP;
import kim.pokemon.util.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.Objects;

public class VIPBuy {

    /**
     * 开通Rank
     * @param playerVIP 开通玩家
     */
    public static void addRank(PlayerVIP playerVIP){
        RankData rankData = new RankData(playerVIP.getRank(),playerVIP.getTime().getTime());

        PlayerData playerData = PlayerDataManager.getPlayerData(Bukkit.getPlayer(playerVIP.getName()).getUniqueId());
        playerData.setRankData(rankData);

        PlayerDataManager.setPlayerData(playerData);
    }

    /**
     * 续费Rank
     * @param playerVIP 续费玩家
     */
    public static void updateRank(PlayerVIP playerVIP){
        RankData rankData = new RankData(playerVIP.getRank(),playerVIP.getTime().getTime());

        PlayerData playerData = PlayerDataManager.getPlayerData(Bukkit.getPlayer(playerVIP.getName()).getUniqueId());
        playerData.setRankData(rankData);

        PlayerDataManager.setPlayerData(playerData);
    }

    /**
     * 查询Rank
     */
    public static PlayerVIP checkRank(Player player,String rank,String server){
        RankData rankData = PlayerDataManager.getPlayerData(player.getUniqueId()).getRankData();

        PlayerVIP vip = new PlayerVIP();

        vip.setName(player.getName());
        vip.setRank(rankData.getRankName());
        vip.setServer(server);
        vip.setTime(new Timestamp(rankData.getExpireDate()));

        if (!vip.getRank().equals("default")){
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
        PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());
        playerData.setRankData(new RankData("default",0));
        PlayerDataManager.setPlayerData(playerData);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" parent clear server="+ Main.luckPerms.getServerName());

    }
}
