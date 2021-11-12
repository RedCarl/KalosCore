package kim.pokemon.ui;

import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainMenu extends InventoryGUI {
    public MainMenu(Player player) {
        super(ColorParser.parse("&0Kim / 任务"), player, 6);

        //主菜单 - 显示一些任务信息，比如 每日任务还剩多少没完成等等。

        //任务举例//
        //自定义显示物品  如果任务完成则不显示该任务
        //提供变量: 任务目标//奖励内容//任务进度

        //任务目标  击败  抓捕  互动NPC
        //借鉴: https://bbs.mc9y.com/resources/11/
        ItemStack test = ItemFactoryAPI.getItemStack(Material.BREAD,
                ColorParser.parse("&a任务名称"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每天都会有不同的任务等待着你去完成."));


        //分割线
        ItemStack Line = ItemFactoryAPI.getItemStack(Material.STAINED_GLASS_PANE,ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i,new Button(Line));
        }



        //名称后面显示完成了几个任务，还剩几个任务
        //每日任务
        ItemStack Daily = ItemFactoryAPI.getItemStack(Material.COMPASS,ColorParser.parse("&a每日任务 &7(1/3)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每天都会有不同的任务等待着你去完成."));
        this.setButton(45,new Button(Daily));

        //每周任务
        ItemStack Weekly = ItemFactoryAPI.getItemStack(Material.WATCH,ColorParser.parse("&e每周任务 &7(1/3)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每周都会有不同的任务等待着你去完成."));
        this.setButton(46,new Button(Weekly));

        //成就任务
        ItemStack Achievement = ItemFactoryAPI.getItemStack(Material.BOOK,ColorParser.parse("&6成就任务 &7(1/3)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o获得指定的成就完成任务."));
        this.setButton(47,new Button(Achievement));

        //关闭菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c关闭"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o关闭任务菜单."));
        this.setButton(53,new Button(Close));
    }
}
