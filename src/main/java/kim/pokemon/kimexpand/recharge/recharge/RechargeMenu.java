package kim.pokemon.kimexpand.recharge.recharge;

import kim.pokemon.configFile.Data;
import kim.pokemon.database.GlazedPayDataSQLReader;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.recharge.*;
import kim.pokemon.kimexpand.recharge.entity.PlayerRecharge;
import kim.pokemon.kimexpand.recharge.shop.ItemBuy;
import kim.pokemon.kimexpand.recharge.shop.ItemSell;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class RechargeMenu extends InventoryGUI {
    DecimalFormat df = new DecimalFormat("#0.##");
    public RechargeMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 充值系统"), player, 6);

        List<PlayerRecharge> rechargeList = GlazedPayDataSQLReader.getTopPlayer();
        double total =  GlazedPayDataSQLReader.getTotal();

//        HashMap<String,Double> rankTop = new HashMap<>();
//        if (rechargeList != null){
//            for (int i = 0; i < 4; i++) {
//                rankTop.put(rechargeList.get(i).getPlayer(), rechargeList.get(i).getAmount());
//            }
//
//        }
        ItemStack Top = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/3821987b682663c26b0315cfd796898ce75ed3f0a8de6807599fe3f0173c9f63",
                ColorParser.parse("&bKalosの玩家贡献排行榜"),
                ColorParser.parse("&r"),
                ColorParser.parse("&f#1 &c"+rechargeList.get(0).getPlayer()+" ( "+df.format((rechargeList.get(0).getAmount()/total)*100)+"% )"),
                ColorParser.parse("&f#2 &6"+rechargeList.get(1).getPlayer()+" ( "+df.format((rechargeList.get(1).getAmount()/total)*100)+"% )"),
                ColorParser.parse("&f#3 &e"+rechargeList.get(2).getPlayer()+" ( "+df.format((rechargeList.get(2).getAmount()/total)*100)+"% )"),
                ColorParser.parse("&f#4 &a"+rechargeList.get(3).getPlayer()+" ( "+df.format((rechargeList.get(3).getAmount()/total)*100)+"% )"),
                ColorParser.parse("&f#5 &a"+rechargeList.get(4).getPlayer()+" ( "+df.format((rechargeList.get(4).getAmount()/total)*100)+"% )"),
                ColorParser.parse("&r"),
                ColorParser.parse("&f#? &9"+player.getName()+" ( "+df.format((GlazedPayDataSQLReader.getPlayer(player.getName()).getAmount()/total)*100)+"% )"),
                ColorParser.parse("&r"),
                ColorParser.parse("&c感谢所有充值的玩家,我们会努力做到更好!")
        );
        Button TopButton = new Button(Top, type -> {
        });
        this.setButton(8, TopButton);

        for (int i = 1; i <= 7; i++) {
            ItemStack Money = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/bb6aef7b520e27f60a5127300b47965ab7537477e69640e6513956a1c8a469ca",
                    ColorParser.parse("&f充值 &a"+(double) (6*i)+" &f"+Data.SERVER_POINTS+""),
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
                    ColorParser.parse("&f充值 &6"+(double) (6*i)+" &f"+ Data.SERVER_POINTS+""),
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
        ItemStack GiftPackShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/b99dc01dcf28445576f2268882a77706fcb9353ab0c954f96045561a79244c1e",ColorParser.parse("&a礼包商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以购买一些商品，作为赞助的回报."));
        Button GiftPackShopButton = new Button(GiftPackShop, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.recharge.GiftPackShop giftPackShop = new GiftPackShop(player);
                giftPackShop.openInventory();
            }
        });
        this.setButton(46, GiftPackShopButton);

        //道具出售
        ItemStack ItemBuy = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/786f6feb285b53e7a85f924dc032d2e5816f5042a4530eecc5c034bee17b1bd0",ColorParser.parse("&e道具出售"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里可以购买一些常用的物品道具."));
        Button ItemBuyButton = new Button(ItemBuy, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.recharge.shop.ItemBuy itemBuy = new ItemBuy(player);
                itemBuy.openInventory();
            }
        });
        this.setButton(47, ItemBuyButton);

        //道具回收
        ItemStack ItemSell = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/45a654f38302d245e59ec5f9f6cb46748c8342cb552d79653f1198a5faa0a468",ColorParser.parse("&9道具回收"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里可以回收一些不常用的物品道具."));
        Button ItemSellButton = new Button(ItemSell, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.recharge.shop.ItemSell itemSell = new ItemSell(player);
                itemSell.openInventory();
            }
        });
        this.setButton(48, ItemSellButton);

        //累计充值
        ItemStack GrandTotal = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/13b08f083df8306fa86817dd08dfa024377b80e92c0800e91f292c2aba44ad3e",ColorParser.parse("&6累计充值"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o累计赞助到一定数额的额外奖励."));
        Button GrandTotalButton = new Button(GrandTotal, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.recharge.GrandTotal grandTotal = new GrandTotal(player);
                grandTotal.openInventory();
            }
        });
        this.setButton(49, GrandTotalButton);

        //累计充值
        ItemStack ArmourersShop = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/66e52b0ac7b34398ff200c48d9c4fdc6bb865aad6a1d5fcf02c8266358fbaf3",ColorParser.parse("&b时装商店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o累计赞助到一定数额的额外奖励."));
        Button ArmourersShopButton = new Button(ArmourersShop, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.recharge.ArmourersShop armourersShop = new ArmourersShop(player);
                armourersShop.openInventory();
            }
        });
        this.setButton(50, ArmourersShopButton);

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
