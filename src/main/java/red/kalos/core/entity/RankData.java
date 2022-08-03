package red.kalos.core.entity;

import red.kalos.core.util.PokemonAPI;

public class RankData {
    private String rankName;
    private long expireDate;

    public RankData(){};

    public RankData(String rankName, long expireDate) {
        this.rankName = rankName;
        this.expireDate = expireDate;
    }


    public RankData(String data) {
        this.rankName = PokemonAPI.subString(data,"rankName=",",");
        this.expireDate = Long.parseLong(PokemonAPI.subString(data,"expireDate=","}"));;
    }


    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }



    public String toString() {
        return "Ranks{rankName=" + this.rankName + ",expireDate=" + this.expireDate + '}';
    }

//    public static RankData deserialize(Map<String, Object> args) {
//        RankData rankData = new RankData();
//        rankData.setRankName((String) args.get("rankName"));
//        rankData.setExpireDate((Long) args.get("expireDate"));
//        return rankData;
//    }
}
