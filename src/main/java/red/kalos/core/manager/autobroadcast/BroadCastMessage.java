package red.kalos.core.manager.autobroadcast;

import red.kalos.core.manager.ranking.RankingManager;
import red.kalos.core.util.ColorParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;

public class BroadCastMessage {
    String[] Message = new String[]{
            "&8[&c&l!&8] &7请不要相信RMB交易,请不要找他人RMB购买 &c物品、宝可梦 &7谨防上当受骗!",
            "&8[&c&l!&8] &7不要与不认识的人交换宝可梦，请使用 &c全球市场 &7进行交易物品!",
            "&8[&c&l!&8] &7不要相信不劳而获之财，下载 &b[国家反诈中心APP] &7谨防诈骗!",
            "&8[&c&l!&8] &7遇到熊孩子怎么办?不要慌张，截图发给管理员立马制裁他!",
            "&8[&c&l!&8] &7有任何不懂的问题可以咨询群管理，他们会耐心为您解答疑惑!",
            "&8[&c&l!&8] &7如果您有建议,请立马将您的想法告诉我们，我们会参考玩家的意见改善服务器!",
            "&8[&c&l!&8] &7您发现了BUG和漏洞?联系QQ: &c2940966174 &7提交并领取奖励吧!",
            "&8[&c&l!&8] &7领取奖励的时候一定要先检查背包有没有多余的位置，否则会丢失物品哦!"
    };
    public BroadCastMessage(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ColorParser.parse("&r"));
                Bukkit.broadcastMessage(ColorParser.parse(Arrays.asList(Message).get(new Random().nextInt((Message.length-1)))));
                Bukkit.broadcastMessage(ColorParser.parse("&r"));
            }
        }.runTaskTimer(plugin,0,12000);

        new BukkitRunnable() {
            @Override
            public void run() {
                RankingManager.LoadAllPlayerData();
                RankingManager.LoadTimeRankingData();
                RankingManager.LoadMoneyRankingData();
            }
        }.runTaskTimer(plugin,0,36000);
    }
}
