package red.kalos.core.manager.questmanager.quest.list.dayquest;

import com.pixelmonmod.pixelmon.entities.pixelmon.Entity1Base;
import org.bukkit.Material;
import red.kalos.core.Main;
import red.kalos.core.manager.questmanager.manager.QuestManager;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.quest.DailyQuest;
import red.kalos.core.manager.questmanager.quest.requirement.CatchRequirement;
import red.kalos.core.manager.questmanager.quest.requirement.HatchRequirement;
import red.kalos.core.manager.questmanager.quest.requirement.WinRequirement;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.ItemFacAPI;

public class Day {

    public void initialize() {
        quest();
    }
    public void quest() {

        DailyQuest daily_a = new DailyQuest();
        CatchRequirement daily_catch_a = daily_a.addCatchRequirement(
                "daily_catch_a",
                15, pokemon -> pokemon.getPokemonData().getLevel() > 5
        );
        daily_a.setItemCreator((player) -> {
            int state = daily_a.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&a抓捕野外的口袋妖怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a15 &f只等级大于 &a5 &f的宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c精灵球*30"),
                        ColorParser.parse("&r          &c卡洛币*500"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&a寻找野外的口袋妖怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a15 &f只等级大于 &a5 &f的宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_a.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c精灵球*30"),
                        ColorParser.parse("&r          &c卡洛币*500"),
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
        daily_a.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_POKE_BALL"), 30));
            Main.getEcon().depositPlayer(player,500);
        });
        QuestManager.registerQuest("daily_a", daily_a);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_b = new DailyQuest();
        WinRequirement daily_catch_b = daily_b.addWinRequirement(
                "daily_catch_b",
                25, pokemon -> pokemon.getPokemonData().getLevel() > 10
        );
        daily_b.setItemCreator((player) -> {
            int state = daily_b.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&b战胜野外的口袋妖怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f击杀 &a25 &f只等级大于 &a10 &f的宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c高级球*10"),
                        ColorParser.parse("&r          &c卡洛币*888"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&b战胜野外的口袋妖怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f击杀 &a25 &f只等级大于 &a10 &f的宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_b.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c高级球*10"),
                        ColorParser.parse("&r          &c卡洛币*888"),
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
        daily_b.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 10));
            Main.getEcon().depositPlayer(player,888);
        });
        QuestManager.registerQuest("daily_b", daily_b);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_c = new DailyQuest();
        CatchRequirement daily_catch_c = daily_c.addCatchRequirement(
                "daily_catch_c",
                1, pokemon -> pokemon.getPokemonData().isLegendary()
        );
        daily_c.setItemCreator((player) -> {
            int state = daily_c.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&c它是传奇宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f捕捉 &a1 &f只 &6传奇宝可梦 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*30"),
                        ColorParser.parse("&r          &c公园球*1"),
                        ColorParser.parse("&r          &c卡洛币*5000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&c它是传奇宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f捕捉 &a1 &f只 &6传奇宝可梦 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_c.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*30"),
                        ColorParser.parse("&r          &c公园球*1"),
                        ColorParser.parse("&r          &c卡洛币*5000"),
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
        daily_c.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 30));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_PARK_BALL"), 1));
            Main.getEcon().depositPlayer(player,5000);
        });
        QuestManager.registerQuest("daily_c", daily_c);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_d = new DailyQuest();
        CatchRequirement daily_catch_d = daily_d.addCatchRequirement(
                "daily_catch_d",
                1, pokemon -> pokemon.getPokemonData().isShiny()
        );
        daily_d.setItemCreator((player) -> {
            int state = daily_d.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&d这是闪光宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只 &a闪光宝可梦 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*5"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c卡洛币*2000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&d这是闪光宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只 &a闪光宝可梦 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_d.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*5"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c卡洛币*2000"),
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
        daily_d.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 5));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 32));
            Main.getEcon().depositPlayer(player,2000);
        });
        QuestManager.registerQuest("daily_d", daily_d);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_e = new DailyQuest();
        WinRequirement daily_catch_e = daily_e.addWinRequirement(
                "daily_catch_e",
                1, pokemon -> pokemon.isBossPokemon() && pokemon.getBossMode().getExtraLevels()==40
        );
        daily_e.setItemCreator((player) -> {
            int state = daily_e.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&6发现传说BOSS!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f击杀 &a1 &f个 &a传说BOSS &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c卡洛币*3000"),
                        ColorParser.parse("&r          &c糖果*10"),
                        ColorParser.parse("&r          &c先机球*32"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&6发现传说BOSS!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f击杀 &a1 &f个 &a传说BOSS &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_e.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c卡洛币*3000"),
                        ColorParser.parse("&r          &c糖果*10"),
                        ColorParser.parse("&r          &c先机球*32"),
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
        daily_e.setReward((player) -> {
            Main.getEcon().depositPlayer(player,3000);
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 10));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_QUICK_BALL"), 32));
        });
        QuestManager.registerQuest("daily_e", daily_e);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_f = new DailyQuest();
        WinRequirement daily_catch_f = daily_f.addWinRequirement(
                "daily_catch_f",
                3, Entity1Base::isBossPokemon
        );
        daily_f.setItemCreator((player) -> {
            int state = daily_f.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&f战胜野外 BOSS 宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f战胜 &a3 &f只 &aBOSS &f级别的宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c卡洛币*1000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&f战胜野外 BOSS 宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f战胜 &a3 &f只 &aBOSS &f级别的宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_f.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c卡洛币*1000"),
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
        daily_f.setReward((player) -> {
            Main.getEcon().depositPlayer(player,1000);
        });
        QuestManager.registerQuest("daily_f", daily_f);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_g = new DailyQuest();
        WinRequirement daily_catch_g = daily_g.addWinRequirement(
                "daily_catch_g",
                1, pokemon -> pokemon.isBossPokemon() && pokemon.getBossMode().getExtraLevels()==50 && pokemon.getPokemonData().isShiny()
        );
        daily_g.setItemCreator((player) -> {
            int state = daily_g.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&f战胜终极 BOSS 宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f战胜 &a1 &f只 &a终极异色 &f的 &aBOSS &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*5"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c卡洛币*3000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&f战胜终极 BOSS 宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f战胜 &a1 &f只 &a终极异色 &f的 &aBOSS &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_g.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*5"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c卡洛币*3000"),
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
        daily_g.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 5));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 32));
            Main.getEcon().depositPlayer(player,3000);
        });
        QuestManager.registerQuest("daily_g", daily_g);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_h = new DailyQuest();
        CatchRequirement daily_catch_h = daily_h.addCatchRequirement(
                "daily_catch_h",
                1, pokemon -> pokemon.getPokemonData().getLocalizedName().equals("百变怪")
        );
        daily_h.setItemCreator((player) -> {
            int state = daily_h.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&d它是谁? 百变怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只 &a百变怪 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*10"),
                        ColorParser.parse("&r          &c公园球*1"),
                        ColorParser.parse("&r          &c卡洛币*10000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&d它是谁? 百变怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只 &a百变怪 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_h.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*10"),
                        ColorParser.parse("&r          &c公园球*1"),
                        ColorParser.parse("&r          &c卡洛币*10000"),
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
        daily_h.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 10));
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_PARK_BALL"), 1));
            Main.getEcon().depositPlayer(player,10000);
        });
        QuestManager.registerQuest("daily_h", daily_h);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_i = new DailyQuest();
        CatchRequirement daily_catch_i = daily_i.addCatchRequirement(
                "daily_catch_i",
                1, pokemon -> pokemon.getPokemonData().getMaxHealth()>=120
        );
        daily_i.setItemCreator((player) -> {
            int state = daily_i.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&d血量越多皮越厚?"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只血量超过 &a120 &f的宝可梦完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c卡洛币*2500"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&d血量越多皮越厚?"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只血量超过 &a120 &f的宝可梦完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_i.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c高级球*32"),
                        ColorParser.parse("&r          &c卡洛币*2500"),
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
        daily_i.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 32));
            Main.getEcon().depositPlayer(player,2500);
        });
        QuestManager.registerQuest("daily_i", daily_i);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_j = new DailyQuest();
        WinRequirement daily_catch_j = daily_j.addWinRequirement(
                "daily_catch_j",
                1, pokemon -> pokemon.isBossPokemon() && pokemon.getBossMode().getExtraLevels()==30
        );
        daily_j.setItemCreator((player) -> {
            int state = daily_j.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&d发现史诗BOSS!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f击杀 &a1 &f个 &a史诗BOSS &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c卡洛币*1500"),
                        ColorParser.parse("&r          &c高级球*20"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&d发现史诗BOSS!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f击杀 &a1 &f个 &a史诗BOSS &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_j.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c卡洛币*1500"),
                        ColorParser.parse("&r          &c高级球*20"),
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
        daily_j.setReward((player) -> {
            Main.getEcon().depositPlayer(player,1500);
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 20));
        });
        QuestManager.registerQuest("daily_j", daily_j);
}
}
