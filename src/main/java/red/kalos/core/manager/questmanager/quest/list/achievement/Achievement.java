package red.kalos.core.manager.questmanager.quest.list.achievement;

public class Achievement {
    public void initialize() {
//        quest0();
//        quest1();
    }
//    public void quest0(){
//        AchievementQuest achievement_a = new AchievementQuest();
//        WinRequirement win_a = achievement_a.addWinRequirement("win_a", 35, pokemon -> pokemon.getLevel()>=30);
//        achievement_a.setItemCreator(player -> {
//            int state = achievement_a.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&a我是宝可梦捕猎萌新!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f击杀 &a35 &f只等级大于 &a30 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_a.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &cMega手环*1"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_a.setReward((player) -> {
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_KEY_STONE"), 1));
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_a", achievement_a);
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        AchievementQuest achievement_b = new AchievementQuest();
//        achievement_b.addDepend("achievement_a");
//        WinRequirement win_b = achievement_b.addWinRequirement("win_b", 198, pokemon -> pokemon.getLevel()>=40);
//        achievement_b.setItemCreator(player -> {
//            int state = achievement_b.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&b我是宝可梦捕猎实习者!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f击杀 &a198 &f只等级大于 &a40 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_b.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &c极具化手环*1"),
//                        ColorParser.parse("&r          &c幸运方块*6"),
//                        ColorParser.parse("&r          &c卡洛币*1390"),
//                        ColorParser.parse("&r          &c卡点*2"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_b.setReward((player) -> {
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_WISHING_STAR"), 1));
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("POKELUCKY_POKE_LUCKY"), 6));
//            Main.getEcon().depositPlayer(player,1390);
//            Main.getPpAPI().giveAsync(player.getUniqueId(),2);
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_b", achievement_b);
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        AchievementQuest achievement_c = new AchievementQuest();
//        achievement_c.addDepend("achievement_b");
//        WinRequirement win_c = achievement_c.addWinRequirement("win_c", 438, pokemon -> pokemon.getLevel()>=45);
//        achievement_c.setItemCreator(player -> {
//            int state = achievement_c.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&e我是宝可梦捕猎高手!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f击杀 &a438 &f只等级大于 &a45 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_c.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &c幸运方块*20"),
//                        ColorParser.parse("&r          &c卡洛币*4390"),
//                        ColorParser.parse("&r          &c卡点*10"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_c.setReward((player) -> {
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("POKELUCKY_POKE_LUCKY"), 20));
//            Main.getEcon().depositPlayer(player,4390);
//            Main.getPpAPI().giveAsync(player.getUniqueId(),10);
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_c", achievement_c);
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        AchievementQuest achievement_d = new AchievementQuest();
//        achievement_d.addDepend("achievement_c");
//        WinRequirement win_d = achievement_d.addWinRequirement("win_d", 1280, pokemon -> pokemon.getLevel()>=60);
//        achievement_d.setItemCreator(player -> {
//            int state = achievement_d.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&6我是宝可梦捕猎大师!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f击杀 &a1280 &f只等级大于 &a60 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_d.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &c幸运方块*64"),
//                        ColorParser.parse("&r          &c卡洛币*8390"),
//                        ColorParser.parse("&r          &c卡点*50"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_d.setReward((player) -> {
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("POKELUCKY_POKE_LUCKY"), 64));
//            Main.getEcon().depositPlayer(player,8390);
//            Main.getPpAPI().giveAsync(player.getUniqueId(),50);
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_d", achievement_d);
//    }
//
//    public void quest1(){
//        AchievementQuest achievement_a1 = new AchievementQuest();
//        CatchRequirement win_a1 = achievement_a1.addCatchRequirement("win_a1", 98, pokemon -> pokemon.getLevel()>=15);
//        achievement_a1.setItemCreator(player -> {
//            int state = achievement_a1.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&a我是宝可梦训练萌新!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f抓捕 &a98 &f只等级大于 &a15 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_a1.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &c暴鲤龙(闪光)*1"),
//                        ColorParser.parse("&r          &c暴鲤龙进化石*1"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_a1.setReward((player) -> {
//            PokemonAPI.GivePokemon(player,true,0,0,false,PokemonAPI.SpawnPokemon("Gyarados"));
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_GYARADOSITE"), 1));
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_a1", achievement_a1);
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        AchievementQuest achievement_b1 = new AchievementQuest();
//        achievement_b1.addDepend("achievement_a1");
//        CatchRequirement win_b1 = achievement_b1.addCatchRequirement("win_b1", 398, pokemon -> pokemon.getLevel()>=15);
//        achievement_b1.setItemCreator(player -> {
//            int state = achievement_b1.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&b我是宝可梦训练高手!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f抓捕 &a398 &f只等级大于 &a15 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_b1.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &c公园球*3"),
//                        ColorParser.parse("&r          &c大师球*2"),
//                        ColorParser.parse("&r          &c幸运方块*6"),
//                        ColorParser.parse("&r          &c卡洛币*1390"),
//                        ColorParser.parse("&r          &c卡点*2"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_b1.setReward((player) -> {
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_PARK_BALL"), 3));
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_MASTER_BALL"), 2));
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("POKELUCKY_POKE_LUCKY"), 6));
//            Main.getEcon().depositPlayer(player,1390);
//            Main.getPpAPI().giveAsync(player.getUniqueId(),2);
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_b1", achievement_b1);
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        AchievementQuest achievement_c1 = new AchievementQuest();
//        achievement_c1.addDepend("achievement_b1");
//        CatchRequirement win_c1 = achievement_c1.addCatchRequirement("win_c1", 999, pokemon -> pokemon.getLevel()>=15);
//        achievement_c1.setItemCreator(player -> {
//            int state = achievement_c1.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&e我是宝可梦训练大师!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f抓捕 &a999 &f只等级大于 &a15 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_c1.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &c地区三鸟*1"),
//                        ColorParser.parse("&r          &c幸运方块*20"),
//                        ColorParser.parse("&r          &c卡洛币*4390"),
//                        ColorParser.parse("&r          &c卡点*10"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_c1.setReward((player) -> {
//            PokemonAPI.GivePokemon(player,false,3,2,false,PokemonAPI.SpawnPokemon("Zapdos"));
//            PokemonAPI.GivePokemon(player,false,3,2,false,PokemonAPI.SpawnPokemon("Moltres"));
//            PokemonAPI.GivePokemon(player,false,3,2,false,PokemonAPI.SpawnPokemon("Articuno"));
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("POKELUCKY_POKE_LUCKY"), 20));
//            Main.getEcon().depositPlayer(player,4390);
//            Main.getPpAPI().giveAsync(player.getUniqueId(),10);
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_c1", achievement_c1);
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        AchievementQuest achievement_d1 = new AchievementQuest();
//        achievement_d1.addDepend("achievement_c1");
//        CatchRequirement win_d1 = achievement_d1.addCatchRequirement("win_d1", 1999, pokemon -> pokemon.getLevel()>=15);
//        achievement_d1.setItemCreator(player -> {
//            int state = achievement_d1.getState(player.getName());
//
//            if (state == QuestState.ACCEPTED_STATE) {
//                return ItemFacAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
//                        ColorParser.parse("&6我是宝可梦训练骨灰级大师!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&7任务内容: "),
//                        ColorParser.parse("&r  &f抓捕 &a1999 &f只等级大于 &a15 &f的宝可梦!"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&r  &e■ &7任务进度:"),
//                        ColorParser.parse("&r          &c"+ win_d1.getText(player.getName())),
//                        ColorParser.parse("&r  &a■ &7任务奖励:"),
//                        ColorParser.parse("&r          &c路卡利欧(4v)*1"),
//                        ColorParser.parse("&r          &c路卡利欧进化石*1"),
//                        ColorParser.parse("&r          &c幸运方块*64"),
//                        ColorParser.parse("&r          &c卡洛币*8390"),
//                        ColorParser.parse("&r          &c卡点*50"),
//                        ColorParser.parse("&r"),
//                        ColorParser.parse("&c点击完成任务,并领取奖励")
//                );
//            }
//            return null;
//        });
//        achievement_d1.setReward((player) -> {
//            PokemonAPI.GivePokemon(player,false,4,0,false,PokemonAPI.SpawnPokemon("Lucario"));
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("PIXELMON_LUCARIONITE"), 1));
//            player.getInventory().addItem(ItemFacAPI.getItemStackWithDurability(Material.getMaterial("POKELUCKY_POKE_LUCKY"), 64));
//            Main.getEcon().depositPlayer(player,8390);
//            Main.getPpAPI().giveAsync(player.getUniqueId(),50);
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
//        });
//        QuestManager.registerQuest("achievement_d1", achievement_d1);
//    }

}
