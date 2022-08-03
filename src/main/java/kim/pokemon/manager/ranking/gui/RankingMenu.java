package kim.pokemon.manager.ranking.gui;

import kim.pokemon.configFile.Data;
import kim.pokemon.manager.menu.MainMenu;
import kim.pokemon.manager.ranking.RankingManager;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class RankingMenu extends InventoryGUI {
    public RankingMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 排行系统"), player, 6);

        ItemStack TimeRanking = ItemFactoryAPI.getItemStack(Material.WATCH,
                ColorParser.parse("&c累计游玩时间排行 &7(Top10)"),
                ColorParser.parse("&r"));
        ItemMeta TimeRankingMeta = TimeRanking.getItemMeta();
        List<String> TimeLore = TimeRankingMeta.getLore();
        for (int i = 0; i < 10; i++) {
            TimeLore.add(ColorParser.parse("&7#"+(i+1)+" &c"+RankingManager.timeEntities.get(i).getName()+" &7- &c"+ RankingManager.timeEntities.get(i).getDate()));
        }
        TimeLore.add(ColorParser.parse("&r"));
        for (int i = 0; i < RankingManager.timeEntities.size(); i++) {
            if (RankingManager.timeEntities.get(i).getName().equals(player.getName())){
                TimeLore.add(ColorParser.parse("&7&o您当前在第 &c"+(i+1)+" &7&o名，总共游玩了 "+RankingManager.timeEntities.get(i).getDate()+" &7&o。"));
            }
        }
        TimeLore.add(ColorParser.parse("&r"));
        TimeLore.add(ColorParser.parse("&7&o( Update 30 min )"));
        TimeRankingMeta.setLore(TimeLore);
        TimeRanking.setItemMeta(TimeRankingMeta);
        this.setButton(0, new Button(TimeRanking));


        ItemStack MoneyRanking = ItemFactoryAPI.getItemStack(Material.DIAMOND,
                ColorParser.parse("&c累计充值排行 &7(Top10)"),
                ColorParser.parse("&r"));
        ItemMeta MoneyRankingMeta = MoneyRanking.getItemMeta();
        List<String> MoneyLore = MoneyRankingMeta.getLore();
        for (int i = 0; i < 10; i++) {
            MoneyLore.add(ColorParser.parse("&7#"+(i+1)+" &c"+RankingManager.moneyEntities.get(i).getName()+" &7- &c"+ RankingManager.moneyEntities.get(i).getMoney()+" &7元"));
        }
        MoneyLore.add(ColorParser.parse("&r"));
        for (int i = 0; i < RankingManager.moneyEntities.size(); i++) {
            if (RankingManager.moneyEntities.get(i).getName().equals(player.getName())){
                MoneyLore.add(ColorParser.parse("&7&o您当前在第 &c"+(i+1)+" &7&o名，总共充值了 &c"+RankingManager.moneyEntities.get(i).getMoney()+" &7&o元。"));
            }
        }
        MoneyLore.add(ColorParser.parse("&r"));
        MoneyLore.add(ColorParser.parse("&7&o( Update 30 min )"));
        MoneyRankingMeta.setLore(MoneyLore);
        MoneyRanking.setItemMeta(MoneyRankingMeta);
        this.setButton(1, new Button(MoneyRanking));



        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回上级菜单."));
        Button CloseButton = new Button(Close, type -> {
            MainMenu menu = new MainMenu(player);
            menu.openInventory();
        });
        this.setButton(53, CloseButton);
    }
}
