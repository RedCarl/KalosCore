package red.kalos.core.manager.questmanager;

import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.questmanager.manager.QuestManager;
import red.kalos.core.manager.questmanager.quest.QuestState;
import red.kalos.core.manager.questmanager.quest.QuestType;
import red.kalos.core.manager.questmanager.quest.quest.Quest;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class QuestGUI extends InventoryGUI {


    public QuestGUI(Player player, QuestType questType) {//为指定玩家创建箱子页面

        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 任务系统"), player, 6);

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }


        List<Quest> quests = QuestManager.filter(questType, player);

        if(questType.equals(QuestType.ACHIEVEMENT)){
            quests = QuestManager.filterAchievementQuests(player);
        }

        for (int i = 0; i < quests.size(); i++) {
            Quest quest = quests.get(i);
            Button b = new Button(quest.getItemStack(player), (type) -> {
                if(quest.getState(player.getName())== QuestState.UNACCEPTED_STATE){
                    quest.accept(player);
                    new QuestGUI(player,questType).openInventory();
                }
                if(quest.getState(player.getName())== QuestState.ACCEPTED_STATE){
                    quest.tryFinish(player);
                    new QuestGUI(player,questType).openInventory();
                }
            });
            setButton(i,b);
        }

        //每日任务
        ItemStack DayMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/f8e5a3a85f82f772b10ddda1e216576ff1b9a98344e0753b68dde06f7f2157f4",ColorParser.parse("&a每日任务"),
                ColorParser.parse("&7任务时间: &f8:00 &7- &f8:00&c(次日)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每天都可以完成的任务,每日随机分配."));
        Button DayMissionButton = new Button(DayMission, type -> {
            new QuestGUI(player,QuestType.DAILY).openInventory();
        });
        this.setButton(45, DayMissionButton);

        //每周任务
        ItemStack WeekMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/fcae5d0379fe73b94042178d87d98298d3cde7da126b18dcce4a3a6c663fafb7",ColorParser.parse("&b每周任务"),
                ColorParser.parse("&7任务时间: &f周一00:00 &7- &f周日00:00"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每周都可以完成的任务,每周随机分配."));
        Button WeekMissionButton = new Button(WeekMission, type -> {
            new QuestGUI(player,QuestType.WEEK).openInventory();
        });
        this.setButton(46, WeekMissionButton);

        //挑战任务
        ItemStack ChallengeMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/22938b36057d883868638fe703473c3ee70ba2798aba54514cb2b2f912822ed1",ColorParser.parse("&c挑战任务"),
                ColorParser.parse("&7任务时间: &f不限制,一次性任务"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o挑战不可能,按任务要求完成任务获得奖励."));
        Button ChallengeMissionButton = new Button(ChallengeMission, type -> {
            new QuestGUI(player,QuestType.CHALLENGE).openInventory();
        });
        this.setButton(47, ChallengeMissionButton);

        //成就任务
        ItemStack AchievementMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/4a3890aa8c76f177cbae7dc0966149a2e0fb1c1efeb283deb7deaa09fd0fb6",ColorParser.parse("&6成就任务"),
                ColorParser.parse("&7任务时间: &f不限制,一次性任务"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o完成成就,获得成就奖励,会不断更新."));
        Button AchievementMissionButton = new Button(AchievementMission, type -> {
            new QuestGUI(player,QuestType.ACHIEVEMENT).openInventory();
        });
        this.setButton(48, AchievementMissionButton);

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至主菜单."));
        Button CloseButton = new Button(Close, type -> {
            new MainMenu(player).openInventory();
        });
        this.setButton(53, CloseButton);
    }
}
