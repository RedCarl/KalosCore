package red.kalos.core.database;

import red.kalos.core.Main;
import red.kalos.core.entity.PlayerData;
import red.kalos.core.entity.RankData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PlayerDataManager {

    private static final PlayerDataManager instance = new PlayerDataManager();

    public static PlayerDataManager getInstance() {
        return instance;
    }

    /**
     * 获取所有玩家数据
     * @return 数据
     */
    public List<PlayerData> getAllPlayerData(){
        List<PlayerData> playerData = new ArrayList<>();
        File file = new File(Main.getInstance().getDataFolder(), "PlayerData");
        File[] files = file.listFiles();
        if (files!=null){
            for (File f: files) {
                playerData.add(getPlayerData(UUID.fromString(f.getName().replace(".yml",""))));
            }
        }
        return playerData;
    }

    /**
     * 添加玩家数据
     */
    public void setPlayerData(PlayerData playerData){
        try {
            File file = new File(Main.getInstance().getDataFolder(), "PlayerData/"+playerData.getUuid()+".yml");
            FileConfiguration data = YamlConfiguration.loadConfiguration(file);
            data.set("UUID",playerData.getUuid());
            data.set("Name",playerData.getName());
            data.set("Level",playerData.getLevel());
            data.set("Recharge",playerData.getRecharge());
            data.set("PlayerTime",playerData.getPlayTime());
            data.set("RankData",playerData.getRankData().toString());
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取玩家数据
     * @return 数据
     */
    public PlayerData getPlayerData(UUID uuid){
        File file = new File(Main.getInstance().getDataFolder(), "PlayerData/"+uuid+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
        PlayerData playerData = new PlayerData();
        playerData.setUuid(data.getString("UUID"));

        if (data.getString("Name") == null){
            playerData.setName(Bukkit.getOfflinePlayer(uuid).getName());
        }else {
            playerData.setName(data.getString("Name"));
        }

        playerData.setLevel(data.getString("Level"));

        playerData.setRecharge(data.getInt("Recharge"));
        playerData.setPlayTime(data.getLong("PlayerTime"));
        if (playerData.getUuid()!=null){
            playerData.setRankData(new RankData(data.getString("RankData")));
        }
        return playerData;
    }



}
