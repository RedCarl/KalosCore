package kim.pokemon.manager.recharge.recharge;

import com.glazed7.glazedpay.bukkit.pay.PayManager;
import com.glazed7.glazedpay.bukkit.pay.PaywayType;
import kim.pokemon.configFile.Data;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import kim.pokemon.util.mojang.Mojang;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RechargeSelect extends InventoryGUI {
    public RechargeSelect(Player player,Double money) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 支付方式 &7("+money+"元)"), player, 1);
        //QQ
        ItemStack QQ = SkullAPI.getSkullItem(Mojang.getBaseHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzk5MjQ2NjMyOGFlYTE4MjVhMmEzMzZiMTg5NGI3N2QxNmQ3NjE4NjI0YWFhNWZiNjRlODFkYTMxZmUyZjAifX19"),ColorParser.parse("&cQQ支付"));
        Button QQButton = new Button(QQ, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                PayManager.initiatePay(player, PaywayType.TENPAY,money);
            }
        });
        this.setButton(2, QQButton);
        //微信
        ItemStack WECHAT_JS = SkullAPI.getSkullItem(Mojang.getBaseHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY5YTQwMWMzNzRhZDcwODNmMjVhZGNkZDUyYjQ2YTc0NzQyYzQ4YmEyZTM5ZGQ2YmMzZTAwMTAzZjJmOThkOSJ9fX0="),ColorParser.parse("&c微信"));
        Button WECHAT_JSButton = new Button(WECHAT_JS, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                PayManager.initiatePay(player, PaywayType.WECHAT_JS,money);
            }
        });
        this.setButton(4, WECHAT_JSButton);
        //支付宝
        ItemStack ALIPAY = SkullAPI.getSkullItem(Mojang.getBaseHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDA5YWRkNDZlMWMxZjNkMzBkOThkYjQ1NjNkNTMyNjE3MGUyZjk0ZjRlYTY5YWY2ZDJmNzc1NDk5ZTM3MGVmNCJ9fX0="),ColorParser.parse("&c支付宝"));
        Button ALIPAYButton = new Button(ALIPAY, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                PayManager.initiatePay(player, PaywayType.ALIPAY,money);
            }
        });
        this.setButton(6, ALIPAYButton);
    }
}
