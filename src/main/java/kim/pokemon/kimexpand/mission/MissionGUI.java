package kim.pokemon.kimexpand.mission;

import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.mission.missionlist.AchievemenMission;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MissionGUI extends InventoryGUI {
    public MissionGUI(Player player,String MissionType) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 任务系统"), player, 6);
        AchievemenMission achievemenMission = new AchievemenMission();

        switch (MissionType){
            case "DAY":
                break;
            case "WEEK":
                break;
            case "CHALLENGE":
                break;
            case "ACHIEVEMEN":
                achievemenMission.getAchievemen(player,this);
                break;
        }



        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //每日任务
        ItemStack DayMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/f8e5a3a85f82f772b10ddda1e216576ff1b9a98344e0753b68dde06f7f2157f4",ColorParser.parse("&a每日任务"),
                ColorParser.parse("&7任务时间: &f4:00 &7- &f4:00&c(次日)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每天都可以完成的任务,每日随机分配."));
        Button DayMissionButton = new Button(DayMission, type -> {
            MissionGUI missionGUI = new MissionGUI(player,"DAY");
            missionGUI.openInventory();
        });
        this.setButton(45, DayMissionButton);

        //每周任务
        ItemStack WeekMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/fcae5d0379fe73b94042178d87d98298d3cde7da126b18dcce4a3a6c663fafb7",ColorParser.parse("&b每周任务"),
                ColorParser.parse("&7任务时间: &f周一00:00 &7- &f周日00:00"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每周都可以完成的任务,每周随机分配."));
        Button WeekMissionButton = new Button(WeekMission, type -> {
            MissionGUI missionGUI = new MissionGUI(player,"WEEK");
            missionGUI.openInventory();
        });
        this.setButton(46, WeekMissionButton);

        //挑战任务
        ItemStack ChallengeMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/22938b36057d883868638fe703473c3ee70ba2798aba54514cb2b2f912822ed1",ColorParser.parse("&c挑战任务"),
                ColorParser.parse("&7任务时间: &f不限制,一次性任务"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o挑战不可能,按任务要求完成任务获得奖励."));
        Button ChallengeMissionButton = new Button(ChallengeMission, type -> {
            MissionGUI missionGUI = new MissionGUI(player,"CHALLENGE");
            missionGUI.openInventory();
        });
        this.setButton(47, ChallengeMissionButton);

        //成就任务
        ItemStack AchievementMission = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/4a3890aa8c76f177cbae7dc0966149a2e0fb1c1efeb283deb7deaa09fd0fb6",ColorParser.parse("&6成就任务"),
                ColorParser.parse("&7任务时间: &f不限制,一次性任务"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o完成成就,获得成就奖励,会不断更新."));
        Button AchievementMissionButton = new Button(AchievementMission, type -> {
            MissionGUI missionGUI = new MissionGUI(player,"ACHIEVEMEN");
            missionGUI.openInventory();
        });
        this.setButton(48, AchievementMissionButton);

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至主菜单."));
        Button CloseButton = new Button(Close, type -> {
            if (type.isLeftClick()) {
                MainMenu mainMenu = new MainMenu(player);
                mainMenu.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }
}
