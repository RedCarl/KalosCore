package red.kalos.core.manager.questmanager.quest.list.dayquest;

import red.kalos.core.Main;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.ItemFacAPI;
import org.bukkit.Material;
import red.kalos.core.manager.questmanager.manager.QuestManager;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.quest.DailyQuest;
import red.kalos.core.manager.questmanager.quest.requirement.CatchRequirement;
import red.kalos.core.manager.questmanager.quest.requirement.CraftRequirement;
import red.kalos.core.manager.questmanager.quest.requirement.HatchRequirement;
import red.kalos.core.manager.questmanager.quest.requirement.WinRequirement;

public class Day {

    public void initialize() {
        quest();
    }
    public void quest() {

        DailyQuest daily_a = new DailyQuest();
        CatchRequirement daily_catch_a = daily_a.addCatchRequirement(
                "daily_catch_a",
                15, pokemon -> pokemon.getLevel() > 5
        );
        daily_a.setItemCreator((player) -> {
            int state = daily_a.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&a寻找野外的口袋妖怪!"),
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
            Main.econ.depositPlayer(player,500);
        });
        QuestManager.registerQuest("daily_a", daily_a);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_b = new DailyQuest();
        WinRequirement daily_catch_b = daily_b.addWinRequirement(
                "daily_catch_b",
                25, pokemon -> pokemon.getLevel() > 10
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
            Main.econ.depositPlayer(player,888);
        });
        QuestManager.registerQuest("daily_b", daily_b);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_c = new DailyQuest();
        CraftRequirement daily_catch_c = daily_c.addCraftRequirement(
                "daily_catch_c",
                70, itemStack -> itemStack.getType().equals(Material.getMaterial("PIXELMON_ULTRA_BALL"))

        );
        daily_c.setItemCreator((player) -> {
            int state = daily_c.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&c一起制作高级球!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f制作 &a70 &f个 &a高级球 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*30"),
                        ColorParser.parse("&r          &c公园球*1"),
                        ColorParser.parse("&r          &c卡洛币*666"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&c一起制作高级球!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f制作 &a70 &f个 &a高级球 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_c.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c糖果*30"),
                        ColorParser.parse("&r          &c公园球*1"),
                        ColorParser.parse("&r          &c卡洛币*666"),
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
            Main.econ.depositPlayer(player,666);
        });
        QuestManager.registerQuest("daily_c", daily_c);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    DailyQuest daily_d = new DailyQuest();
    CraftRequirement daily_catch_d = daily_d.addCraftRequirement(
            "daily_catch_d",
            80, itemStack -> itemStack.getType().equals(Material.getMaterial("PIXELMON_POKE_BALL"))

    );
        daily_d.setItemCreator((player) -> {
        int state = daily_d.getState(player.getName());
        if (state == QuestState.UNACCEPTED_STATE) {
            return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                    ColorParser.parse("&d一起制作精灵球!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7任务内容: "),
                    ColorParser.parse("&r  &f制作 &a80 &f个 &a精灵球 &f完成任务!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &a■ &7任务奖励:"),
                    ColorParser.parse("&r          &c高级球*30"),
                    ColorParser.parse("&r          &c卡洛币*239"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&c点击接受任务,并开始任务")
            );
        }

        if (state == QuestState.ACCEPTED_STATE) {
            return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                    ColorParser.parse("&d一起制作精灵球!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7任务内容: "),
                    ColorParser.parse("&r  &f制作 &a80 &f个 &a精灵球 &f完成任务!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7任务进度:"),
                    ColorParser.parse("&r          &c"+ daily_catch_d.getText(player.getName())),
                    ColorParser.parse("&r  &a■ &7任务奖励:"),
                    ColorParser.parse("&r          &c高级球*30"),
                    ColorParser.parse("&r          &c卡洛币*239"),
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
        player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_RARE_CANDY"), 10));
        Main.econ.depositPlayer(player,666);
    });
        QuestManager.registerQuest("daily_d", daily_d);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_e = new DailyQuest();
        HatchRequirement daily_catch_e = daily_e.addHatchRequirement(
                "daily_catch_e",
                2, pokemon -> pokemon.getLevel() > 0
        );
        daily_e.setItemCreator((player) -> {
            int state = daily_e.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&e孵化一个宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f孵化 &a2 &f个 &a蛋 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c银沙漏*2"),
                        ColorParser.parse("&r          &c卡洛币*123"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&e孵化一个宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f孵化 &a2 &f个 &a蛋 &f完成任务!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_e.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c银沙漏*2"),
                        ColorParser.parse("&r          &c卡洛币*123"),
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
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_HOURGLASS_SILVER"), 2));
            Main.econ.depositPlayer(player,123);
        });
        QuestManager.registerQuest("daily_e", daily_e);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_f = new DailyQuest();
        CatchRequirement daily_catch_f = daily_f.addCatchRequirement(
                "daily_catch_f",
                2, pokemon -> pokemon.getLevel() > 50&&pokemon.getLocalizedName().equals("巨金怪")
        );
        daily_f.setItemCreator((player) -> {
            int state = daily_f.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&f寻找野外的巨金怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a2 &f只等级大于 &a50 &f的 &a巨金怪 &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c大师球*1"),
                        ColorParser.parse("&r          &c卡洛币*3000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&f寻找野外的巨金怪!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a2 &f只等级大于 &a50 &f的 &a巨金怪 &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_f.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c大师球*1"),
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
        daily_f.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_MASTER_BALL"), 1));
            Main.econ.depositPlayer(player,3000);
        });
        QuestManager.registerQuest("daily_f", daily_f);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        DailyQuest daily_g = new DailyQuest();
        CatchRequirement daily_catch_g = daily_g.addCatchRequirement(
                "daily_catch_g",
                1, pokemon -> pokemon.getLevel() > 1&&pokemon.getLocalizedName().equals("多边兽")
        );
        daily_g.setItemCreator((player) -> {
            int state = daily_g.getState(player.getName());
            if (state == QuestState.UNACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                        ColorParser.parse("&f寻找野外的多边兽!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只等级大于 &a1 &f的 &a多边兽 &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c高级球*15"),
                        ColorParser.parse("&r          &c卡洛币*2000"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&c点击接受任务,并开始任务")
                );
            }

            if (state == QuestState.ACCEPTED_STATE) {
                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                        ColorParser.parse("&f寻找野外的多边兽!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7任务内容: "),
                        ColorParser.parse("&r  &f抓捕 &a1 &f只等级大于 &a1 &f的 &a多边兽 &f宝可梦!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7任务进度:"),
                        ColorParser.parse("&r          &c"+ daily_catch_g.getText(player.getName())),
                        ColorParser.parse("&r  &a■ &7任务奖励:"),
                        ColorParser.parse("&r          &c高级球*15"),
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
        daily_g.setReward((player) -> {
            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_ULTRA_BALL"), 15));
            Main.econ.depositPlayer(player,2000);
        });
        QuestManager.registerQuest("daily_g", daily_g);





}
}
