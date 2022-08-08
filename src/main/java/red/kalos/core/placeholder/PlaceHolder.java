package red.kalos.core.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import red.kalos.core.manager.ranking.RankingManager;
import red.kalos.core.util.ColorParser;

public class PlaceHolder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "kalos";
    }

    @Override
    public @NotNull String getAuthor() {
        return "redcarl";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        //累计充值排行榜
        if (params.contains("money")){
            for (int i = 1; i <= 10; i++) {
                String top;
                if (i!=10){
                    top = "0"+i;
                }else {
                    top = String.valueOf(i);
                }
                if (params.contains(top)){
                    return ColorParser.parse("&f#"+i+" &a"+RankingManager.moneyEntities.get(i-1).getName()+" - "+RankingManager.moneyEntities.get(i-1).getMoney()+" 卡点");
                }
            }
        }

        //累计在线排行榜
        if (params.contains("time")){
            for (int i = 1; i <= 10; i++) {
                String top;
                if (i!=10){
                    top = "0"+i;
                }else {
                    top = String.valueOf(i);
                }
                if (params.contains(top)){
                    return ColorParser.parse("&f#"+i+" &a"+RankingManager.timeEntities.get(i-1).getName()+" - "+RankingManager.timeEntities.get(i-1).getDate());
                }
            }
        }
        return null;
    }

}
