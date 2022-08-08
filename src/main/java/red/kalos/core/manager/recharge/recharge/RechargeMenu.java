package red.kalos.core.manager.recharge.recharge;

import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.recharge.GiftPackShop;
import red.kalos.core.manager.recharge.ArmourersShop;
import red.kalos.core.manager.recharge.GrandTotal;
import red.kalos.core.manager.shop.ItemBuy;
import red.kalos.core.manager.shop.ItemSell;
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
            ItemStack Money = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/bb6aef7b520e27f60a5127300b47965ab7537477e69640e6513956a1c8a469ca",
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
        for (int i = 10; i <= 70; i=i+10) {
            ItemStack Money = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/7095d0b895bd8b6f252534214d3f819b351e1987b52cbfc0afa424df1f00fa7a",
                    ColorParser.parse("&f充值 &6"+(double) (6*i)+" &f"+ Data.SERVER_POINTS),
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
            this.setButton(19+j, MoneyButton);
            j++;
        }

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //"+Data.SERVER_POINTS+"充值
        ItemStack Recharge = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/76c9c0b2b1e74b70847e551be14c81b58fc6011017f8922b5fe6f66a6dc77066",ColorParser.parse("&c"+Data.SERVER_POINTS+"充值"),
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
        ItemStack GiftPackShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/b99dc01dcf28445576f2268882a77706fcb9353ab0c954f96045561a79244c1e",ColorParser.parse("&a会员商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o在这里可以开通会员享受更多内容。"));
        Button GiftPackShopButton = new Button(GiftPackShop, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.recharge.GiftPackShop giftPackShop = new GiftPackShop(player);
                giftPackShop.openInventory();
            }
        });
        this.setButton(46, GiftPackShopButton);

        //累计充值
        ItemStack GrandTotal = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/13b08f083df8306fa86817dd08dfa024377b80e92c0800e91f292c2aba44ad3e",ColorParser.parse("&6累计充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o累计赞助到一定数额的额外奖励。"));
        Button GrandTotalButton = new Button(GrandTotal, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.recharge.GrandTotal grandTotal = new GrandTotal(player);
                grandTotal.openInventory();
            }
        });
        this.setButton(47, GrandTotalButton);

        //时装商店
        ItemStack ArmourersShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/66e52b0ac7b34398ff200c48d9c4fdc6bb865aad6a1d5fcf02c8266358fbaf3",ColorParser.parse("&b时装商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以在这里购买时装。"));
        Button ArmourersShopButton = new Button(ArmourersShop, type -> {
            if (type.isLeftClick()) {
                red.kalos.core.manager.recharge.ArmourersShop armourersShop = new ArmourersShop(player);
                armourersShop.openInventory();
            }
        });
        this.setButton(48, ArmourersShopButton);

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
