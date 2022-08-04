package red.kalos.core.manager.ranking;

import red.kalos.core.database.PlayerDataManager;
import red.kalos.core.entity.PlayerData;
import red.kalos.core.manager.ranking.entity.MoneyEntity;
import red.kalos.core.manager.ranking.entity.TimeEntity;
import red.kalos.core.util.PokemonAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RankingManager {
    public static List<TimeEntity> timeEntities = new ArrayList<>();
    public static List<MoneyEntity> moneyEntities = new ArrayList<>();
    public static List<PlayerData> playerDataList = new ArrayList<>();
    public static List<UUID> complexList = new ArrayList<>();
    //获取所有玩家数据
    public static void LoadAllPlayerData(){
        playerDataList.clear();
        playerDataList=PlayerDataManager.getInstance().getAllPlayerData();
    }

    //加载玩家在线时间排行
    public static void LoadTimeRankingData(){
        timeEntities.clear();
        for (PlayerData p:playerDataList) {
            TimeEntity timeEntity = new TimeEntity();
            timeEntity.setName(p.getName());
            timeEntity.setTime(p.getPlayTime());
            timeEntity.setDate(PokemonAPI.getDate(p.getPlayTime()));
            timeEntities.add(timeEntity);
        }
        timeEntities.sort(null);
        Collections.reverse(timeEntities);
    }

    //加载玩家充值排行
    public static void LoadMoneyRankingData(){
        moneyEntities.clear();
        for (PlayerData p:playerDataList) {
            MoneyEntity moneyEntity = new MoneyEntity();
            moneyEntity.setName(p.getName());
            moneyEntity.setMoney(p.getRecharge());
            moneyEntities.add(moneyEntity);
        }
        moneyEntities.sort(null);
        Collections.reverse(moneyEntities);
    }

    //加载玩家综合排行榜
    public static void complexRankingData(){

    }
}
