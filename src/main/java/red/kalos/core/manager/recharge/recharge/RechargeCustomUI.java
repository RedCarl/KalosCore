package red.kalos.core.manager.recharge.recharge;

import eos.moe.dragoncore.api.gui.event.CustomPacketEvent;
import eos.moe.dragoncore.network.PacketSender;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import red.kalos.core.Main;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.KalosUtil;

import java.io.File;

/**
 * @Author: carl0
 * @DATE: 2022/8/11 20:05
 */
public class RechargeCustomUI implements Listener {

    static YamlConfiguration DragonGUI = YamlConfiguration.loadConfiguration(new File(Main.getInstance().getDataFolder(),"Recharge/gui.yml"));

    public static void open(Player player){
        DragonGUI.set("value.text","请输入要充值的金额。");
        PacketSender.sendYaml(player,"gui/RechargeGUI.yml", DragonGUI);
        PacketSender.sendOpenGui(player, "RechargeGUI");
    }

    @EventHandler
    public void CustomPacketEvent(final CustomPacketEvent event) {
        Player player = event.getPlayer();
        switch (event.getIdentifier()){
            case "Dragon_Recharge_OK":
                String value = event.getData().get(0);

                if (KalosUtil.isNumeric(value)){
                    double money = Double.parseDouble(value);

                    if (money==15 || money==45){
                        player.closeInventory();
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，请不要充值 &c45 &7或者 &c15 &7的金额。"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        return;
                    }

                    player.closeInventory();
                    RechargeSelect rechargeSelect = new RechargeSelect(player, Double.parseDouble(value));
                    rechargeSelect.openInventory();
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您输入的 &c数 &7必须是 &c正整数 &7否则无法使用。"));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.closeInventory();
                }
                break;
            case "Dragon_Recharge_Cancel":
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您取消了本次自定义充值操作。"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                player.closeInventory();
                break;
        }
    }
}
