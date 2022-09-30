package red.kalos.core.manager.recharge.recharge;

import de.tr7zw.nbtapi.NBTItem;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.kits.KitsGUI;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.premium.VIPBuy;
import red.kalos.core.manager.premium.entity.PlayerVIP;
import red.kalos.core.manager.recharge.recharge.RechargeSelect;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.util.gui.inventory.SkullAPI;

public class RankShop extends InventoryGUI {
    PlayerPointsAPI playerPointsAPI = Main.getPpAPI();
    public RankShop(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 会员商店"), player, 6);
        //皮卡丘会员
        int PikaniumPrice=15;
        String PikaniumRanks="pikanium";
        String PikaniumRankName="&e"+Data.SERVER_NAME_CN+"の皮卡丘";
        PlayerVIP PikaniumVIP = VIPBuy.checkRank(player,PikaniumRanks, Main.getLuckPerms().getServerName());
        if (PikaniumVIP!=null){
            ItemStack Pikanium = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(PikaniumRankName+" &f// &aVIP头衔"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&f到期时间&c(续费)&f:"),
                    ColorParser.parse("&a"+PikaniumVIP.getTime()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(续费) &c" + PikaniumPrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(使用: /fly)"),
                    ColorParser.parse("      &f开通礼包 &7(开通即得,次数无上限)"),
                    ColorParser.parse("          &a糖果&7*&b"+32),
                    ColorParser.parse("          &a大师球&7*&b"+4),
                    ColorParser.parse("          &a先机球&7*&b"+32),
                    ColorParser.parse("          &a超级球&7*&b"+64),
                    ColorParser.parse("          &a高级球&7*&b"+32),
                    ColorParser.parse("          &a熟牛排&7*&b"+64*3),
                    ColorParser.parse("          &a卡洛币&7*&b"+15000),
                    ColorParser.parse("          &a幸运方块&7*&b"+10),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a5%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a7&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a2&7)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即续费,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Pikanium);
            nbtItem.setShort("ndex", (short)25);
            Button PikaniumButton = new Button(nbtItem.getItem(), type -> {
                player.closeInventory();
                RechargeSelect rechargeSelect = new RechargeSelect(player, (double) PikaniumPrice);
                rechargeSelect.openInventory();
            });
            this.setButton(0, PikaniumButton);
        }else {
            ItemStack Pikanium = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(PikaniumRankName+" &f// &aVIP头衔"),
                    ColorParser.parse("&f时效: &a30 &f天"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(开通) &c" + PikaniumPrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(使用: /fly)"),
                    ColorParser.parse("      &f开通礼包 &7(开通即得,次数无上限)"),
                    ColorParser.parse("          &a糖果&7*&b"+32),
                    ColorParser.parse("          &a大师球&7*&b"+4),
                    ColorParser.parse("          &a先机球&7*&b"+32),
                    ColorParser.parse("          &a超级球&7*&b"+64),
                    ColorParser.parse("          &a高级球&7*&b"+32),
                    ColorParser.parse("          &a熟牛排&7*&b"+64*3),
                    ColorParser.parse("          &a卡洛币&7*&b"+15000),
                    ColorParser.parse("          &a幸运方块&7*&b"+10),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a5%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a7&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a2&7)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即购买,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Pikanium);
            nbtItem.setShort("ndex", (short)25);
            Button PikaniumButton = new Button(nbtItem.getItem(), type -> {
                if (VIPBuy.checkRank(player,null, Main.getLuckPerms().getServerName())==null){
                    player.closeInventory();
                    RechargeSelect rechargeSelect = new RechargeSelect(player, (double) PikaniumPrice);
                    rechargeSelect.openInventory();
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您还有其它的服务并未过期，请等待过期后再进行开通."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            });
            this.setButton(0, PikaniumButton);
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        //伊布会员
        int EeveePrice=45;
        String EeveeRanks="eevee";
        String EeveeRankName="&6"+Data.SERVER_NAME_CN+"の伊布";
        PlayerVIP EeveeVIP = VIPBuy.checkRank(player,EeveeRanks, Main.getLuckPerms().getServerName());
        if (EeveeVIP!=null){
            ItemStack Eevee = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(EeveeRankName+" &f// &6MVP头衔"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&f到期时间&c(续费)&f:"),
                    ColorParser.parse("&a"+EeveeVIP.getTime()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(续费) &c" + EeveePrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(菜单)"),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f月卡礼包 &7(每天签到领取 &c1 &7卡点)"),
                    ColorParser.parse("      &f开通礼包 &7(开通即得,次数无上限)"),
                    ColorParser.parse("          &a糖果&7*&b"+64*2),
                    ColorParser.parse("          &a大师球&7*&b"+12),
                    ColorParser.parse("          &a先机球&7*&b"+64*2),
                    ColorParser.parse("          &a超级球&7*&b"+64*3),
                    ColorParser.parse("          &a高级球&7*&b"+64*2),
                    ColorParser.parse("          &a熟牛排&7*&b"+64*6),
                    ColorParser.parse("          &a卡洛币&7*&b"+50000),
                    ColorParser.parse("          &a幸运方块&7*&b"+32),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a15%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a21&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a4&7)"),
                    ColorParser.parse("      &f传说宝可梦查询 &7(菜单)"),
                    ColorParser.parse("      &f彩色字符 &7(& 聊天栏)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即续费,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Eevee);
            nbtItem.setShort("ndex", (short)133);
            Button EeveeButton = new Button(nbtItem.getItem(), type -> {
                player.closeInventory();
                RechargeSelect rechargeSelect = new RechargeSelect(player, (double) EeveePrice);
                rechargeSelect.openInventory();
            });
            this.setButton(1, EeveeButton);
        }else {
            ItemStack Eevee = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                    ColorParser.parse(EeveeRankName+" &f// &6MVP头衔"),
                    ColorParser.parse("&f时效: &a30 &f天"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7售 价:"),
                    ColorParser.parse("&r      &7(开通) &c" + EeveePrice + " &7"+Data.SERVER_RMB),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &c■ &7特 权:"),
                    ColorParser.parse("      &f飞行 &7(菜单)"),
                    ColorParser.parse("      &f签到 &7(额外的奖励 幸运方块)"),
                    ColorParser.parse("      &f月卡礼包 &7(每天签到领取 &c1 &7卡点)"),
                    ColorParser.parse("      &f开通礼包 &7(开通即得,次数无上限)"),
                    ColorParser.parse("          &a糖果&7*&b"+64*2),
                    ColorParser.parse("          &a大师球&7*&b"+12),
                    ColorParser.parse("          &a先机球&7*&b"+64*2),
                    ColorParser.parse("          &a超级球&7*&b"+64*3),
                    ColorParser.parse("          &a高级球&7*&b"+64*2),
                    ColorParser.parse("          &a熟牛排&7*&b"+64*6),
                    ColorParser.parse("          &a卡洛币&7*&b"+50000),
                    ColorParser.parse("          &a幸运方块&7*&b"+32),
                    ColorParser.parse("      &f宝可梦回收 &7(额外的收益 &a15%&7)"),
                    ColorParser.parse("      &f更多的家园 &7(3 -> &a21&7)"),
                    ColorParser.parse("      &f更多的地皮 &7(1 -> &a4&7)"),
                    ColorParser.parse("      &f传说宝可梦查询 &7(菜单)"),
                    ColorParser.parse("      &f彩色字符 &7(& 聊天栏)"),
                    ColorParser.parse("      &r"),
                    ColorParser.parse("&c点击立即购买,仅支持 RMB 支付")
            );
            NBTItem nbtItem = new NBTItem(Eevee);
            nbtItem.setShort("ndex", (short)133);
            Button EeveeButton = new Button(nbtItem.getItem(), type -> {
                player.closeInventory();
                RechargeSelect rechargeSelect = new RechargeSelect(player, (double) EeveePrice);
                rechargeSelect.openInventory();
            });
            this.setButton(1, EeveeButton);
        }


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
