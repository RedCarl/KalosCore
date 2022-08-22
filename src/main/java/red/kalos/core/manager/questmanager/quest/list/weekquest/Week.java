package red.kalos.core.manager.questmanager.quest.list.weekquest;

import org.bukkit.Material;
import red.kalos.core.Main;
import red.kalos.core.manager.questmanager.manager.QuestManager;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.quest.WeekQuest;
import red.kalos.core.manager.questmanager.quest.requirement.CatchRequirement;
import red.kalos.core.manager.questmanager.quest.requirement.WinRequirement;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.ItemFacAPI;

/**
 * @Author: carl0
 * @DATE: 2022/8/20 15:22
 */
public class Week {
    public void initialize() {
        quest();
    }
    public void quest() {
        WeekQuest week_a = new WeekQuest();
        WinRequirement week_catch_a = week_a.addWinRequirement(
                "week_catch_a",
                8, pokemon -> pokemon.isBossPokemon() && pokemon.getBossMode().getExtraLevels()==50 && pokemon.getPokemonData().isShiny()
        );
        week_a.setItemCreator((player) -> {
            int state = week_a.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&f战胜终极 BOSS 宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f战胜 &a8 &f只 &a终极异色 &f的 &aBOSS &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*64"),
                        ColorParser.parse("&r          &c大师球*1"),
                        ColorParser.parse("&r          &c先机球*64"),
                        ColorParser.parse("&r          &c高级球*64"),
                        ColorParser.parse("&r          &c卡洛币*30000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&f战胜终极 BOSS 宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f战胜 &a8 &f只 &a终极异色 &f的 &aBOSS &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ week_catch_a.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*64"),
                        ColorParser.parse("&r          &c大师球*1"),
                        ColorParser.parse("&r          &c先机球*64"),
                        ColorParser.parse("&r          &c高级球*64"),
                        ColorParser.parse("&r          &c卡洛币*30000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击完成任务,并领取奖励")
                );
            }
            if (state == QuestState.CLOSE_STATE) {
                return ItemFacAPI.getItemStack(Material.BARRIER,
                        ColorParser.parse("&c任务已经完成!")
                );

            }
            return null;
        });
        week_a.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 64));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_MASTER_BALL"), 1));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_QUICK_BALL"), 64));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 64));
            Main.getEcon().depositPlayer(player,30000);
        });
        QuestManager.registerQuest("week_a", week_a);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        WeekQuest week_b = new WeekQuest();
        CatchRequirement week_catch_b = week_b.addCatchRequirement(
                "week_catch_b",
                8, pokemon -> pokemon.getPokemonData().isLegendary()
        );
        week_b.setItemCreator((player) -> {
            int state = week_b.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&c它是传奇宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f捕捉 &a8 &f只 &6传奇宝可梦 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*64"),
                        ColorParser.parse("&r          &c大师球*1"),
                        ColorParser.parse("&r          &c先机球*64"),
                        ColorParser.parse("&r          &c高级球*64"),
                        ColorParser.parse("&r          &c卡洛币*30000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&c它是传奇宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f捕捉 &a8 &f只 &6传奇宝可梦 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ week_catch_b.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*64"),
                        ColorParser.parse("&r          &c大师球*2"),
                        ColorParser.parse("&r          &c先机球*64"),
                        ColorParser.parse("&r          &c高级球*64"),
                        ColorParser.parse("&r          &c卡洛币*30000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击完成任务,并领取奖励")
                );
            }
            if (state == QuestState.CLOSE_STATE) {
                return ItemFacAPI.getItemStack(Material.BARRIER,
                        ColorParser.parse("&c任务已经完成!")
                );

            }
            return null;
        });
        week_b.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 64));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_MASTER_BALL"), 2));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_QUICK_BALL"), 64));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 64));
            Main.getEcon().depositPlayer(player,30000);
        });
        QuestManager.registerQuest("week_b", week_b);
    }
}
