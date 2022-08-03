package kim.pokemon.manager.ranking;

import kim.pokemon.database.PlayerDataManager;
import kim.pokemon.entity.PlayerData;
import kim.pokemon.manager.ranking.entity.MoneyEntity;
import kim.pokemon.manager.ranking.entity.TimeEntity;
import kim.pokemon.util.PokemonAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingManager {
    public static List<TimeEntity> timeEntities = new ArrayList<>();
    public static List<MoneyEntity> moneyEntities = new ArrayList<>();
    public static List<PlayerData> playerDataList = new ArrayList<>();

    //获取所有玩家数据
    public static void LoadAllPlayerData(){
        playerDataList.clear();
        playerDataList=PlayerDataManager.getPlayerData();
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
}
