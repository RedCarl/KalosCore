package red.kalos.core.entity;

import red.kalos.core.database.PlayerDataManager;
import org.bukkit.entity.Player;

public class PlayerData {

    private String uuid;
    private String name;
    private long recharge;
    private long playTime;
    private RankData rankData;
    private String level;

    public PlayerData(){};

    public PlayerData(String uuid, String name, long recharge, long playTime, RankData rankData, String level) {
        this.uuid = uuid;
        this.name = name;
        this.recharge = recharge;
        this.playTime = playTime;
        this.rankData = rankData;
        this.level = level;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRecharge() {
        return recharge;
    }

    public void setRecharge(long recharge) {
        this.recharge = recharge;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public RankData getRankData() {
        return rankData;
    }

    public void setRankData(RankData rankData) {
        this.rankData = rankData;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public PlayerData sendPlayer(Player player) {
        return PlayerDataManager.getPlayerData(player.getUniqueId());
    }
}
