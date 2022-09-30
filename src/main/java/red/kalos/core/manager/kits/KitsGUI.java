package red.kalos.core.manager.kits;

import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.kits.list.OnlineKits;
import red.kalos.core.manager.kits.list.GrandTotalKits;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class KitsGUI extends InventoryGUI {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    DecimalFormat decimalFormat = new DecimalFormat("#0.##");
    public KitsGUI(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 礼包系统"), player, 6);
        //累计在线礼包
        this.setButton(0,new Button(
                SkullAPI.getSkullItem("9a98a325f0b37cd270f58bb09cb9d73e1bb087cac632df2a5f072325334c540",
                        ColorParser.parse("&a累计在线礼包"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7在线的时常足够后可以领取对应的礼包。")
//                        ColorParser.parse("&7注意 &c伊布 皮卡丘 &7会有额外的奖励。"),
//                        ColorParser.parse("&7如果没有开通 &c会员 &7之前领取后，即使开通了也无法再次领取")
                ),
                type -> new OnlineKits(player).openInventory()));

        //累计充值
        this.setButton(1,new Button(
                SkullAPI.getSkullItem("13b08f083df8306fa86817dd08dfa024377b80e92c0800e91f292c2aba44ad3e",
                        ColorParser.parse("&6累计充值礼包"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o累计赞助到一定数额的额外奖励。")
                ),
                type -> new GrandTotalKits(player).openInventory()));

        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c关闭"));
        Button CloseButton = new Button(Close, type -> {
            player.closeInventory();
        });
        this.setButton(53, CloseButton);
    }
}
