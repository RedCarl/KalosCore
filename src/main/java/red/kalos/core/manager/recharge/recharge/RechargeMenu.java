package red.kalos.core.manager.recharge.recharge;

import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import red.kalos.core.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class RechargeMenu extends InventoryGUI {
    DecimalFormat df = new DecimalFormat("#0.##");
    public RechargeMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 充值系统"), player, 6);

        for (int i = 1; i <= 7; i++) {
            ItemStack Money = SkullAPI.getSkullItem("bb6aef7b520e27f60a5127300b47965ab7537477e69640e6513956a1c8a469ca",
                    ColorParser.parse("&f充值 &a"+(double) (6*i)+" &f"+Data.SERVER_POINTS),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o您的赞助是对我们最大的支持."));
            Money.setAmount(i);
            int finalI = i;
            Button MoneyButton = new Button(Money, type -> {
                if (type.isLeftClick()) {
                    RechargeSelect rechargeSelect = new RechargeSelect(player, (double) (6* finalI));
                    rechargeSelect.openInventory();
                }
            });
            this.setButton(10+(i-1), MoneyButton);
        }

        int j = 0;
        for (int i = 1; i <= 6; i++) {
            ItemStack Money = SkullAPI.getSkullItem("7095d0b895bd8b6f252534214d3f819b351e1987b52cbfc0afa424df1f00fa7a",
                    ColorParser.parse("&f充值 &6"+(double) (68*i)+" &f"+ Data.SERVER_POINTS),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7&o您的赞助是对我们最大的支持."));
            Money.setAmount(i);
            int finalI = i;
            Button MoneyButton = new Button(Money, type -> {
                if (type.isLeftClick()) {
                    RechargeSelect rechargeSelect = new RechargeSelect(player, (double) (68* finalI));
                    rechargeSelect.openInventory();
                }
            });
            this.setButton(19+j, MoneyButton);
            j++;
        }

        ItemStack Money = SkullAPI.getSkullItem("631525274d2c6b9a5ad3e6a844a8319bca18bfbc9b78aecdfd84a39932135548",
                ColorParser.parse("&c自定义充值数额"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o您的赞助是对我们最大的支持."));
        Button MoneyButton = new Button(Money, type -> {
            if (type.isLeftClick()) {
                RechargeCustomUI.open(player);
            }
        });
        this.setButton(25, MoneyButton);



        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //"+Data.SERVER_POINTS+"充值
        ItemStack Recharge = SkullAPI.getSkullItem("76c9c0b2b1e74b70847e551be14c81b58fc6011017f8922b5fe6f66a6dc77066",ColorParser.parse("&c"+Data.SERVER_POINTS+"充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以在这里进行赞助服务器."));
        Button RechargeButton = new Button(Recharge, type -> {
            if (type.isLeftClick()) {
                RechargeMenu rechargeMenu = new RechargeMenu(player);
                rechargeMenu.openInventory();
            }
        });
        this.setButton(45, RechargeButton);

        //礼包商店
        ItemStack GiftPackShop = SkullAPI.getSkullItem("b99dc01dcf28445576f2268882a77706fcb9353ab0c954f96045561a79244c1e",ColorParser.parse("&a会员商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o在这里可以开通会员享受更多内容。"));
        Button GiftPackShopButton = new Button(GiftPackShop, type -> {
            if (type.isLeftClick()) {
                RankShop rankShop = new RankShop(player);
                rankShop.openInventory();
            }
        });
        this.setButton(46, GiftPackShopButton);


        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
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
