package red.kalos.core.manager.nick;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.PlayerManager;
import eos.moe.dragoncore.api.gui.event.CustomPacketEvent;
import eos.moe.dragoncore.network.PacketSender;
import red.kalos.core.Main;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class Nick implements Listener {
    YamlConfiguration DragonGUI = YamlConfiguration.loadConfiguration(new File(Main.getInstance().getDataFolder(),"Nick/gui.yml"));
    YamlConfiguration NickData;



    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player player = event.getPlayer();
            ItemStack i = event.getItem();


                if (i!=null&&i.getItemMeta().getDisplayName()!=null&&i.getItemMeta().getDisplayName().equals(CustomItem.NickCard.getItemMeta().getDisplayName())){
                    if (player.getName().equals("Red_Carl")){
                        PacketSender.sendYaml(player,"gui/NickGUI.yml", DragonGUI);
                        PacketSender.sendOpenGui(player, "NickGUI");
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该功能还在测试中，暂时无法使用."));
                        player.getInventory().removeItem(i);
                    }
                    event.setCancelled(true);
                }

        }

    }

    @EventHandler
    public void CustomPacketEvent(final CustomPacketEvent event) {

        Player player = event.getPlayer();

        switch (event.getIdentifier()){
            case "Dragon_Nick_Change":
                PlayerManager playerManager = CMI.getInstance().getPlayerManager();
                String Name = event.getData().get(0);

                if (Name.equals("")){
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7很抱歉，请输入修改的昵称再点击修改按钮."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.closeInventory();
                    break;
                }
                if (CMI.getInstance().getNickNameManager().getNickNameBlackList().contains(Name)){
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7很抱歉，您的昵称不合法，请重新输入."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.closeInventory();
                    break;
                }
                playerManager.getUser(player).setNickName(Name,true);
                try {
                    File Data = new File(Main.getInstance().getDataFolder(),"Nick/data.yml");
                    NickData = YamlConfiguration.loadConfiguration(Data);
                    NickData.set(player.getName(),Name);
                    NickData.save(Data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7成功将您的游戏名称更改为 &a"+event.getData().get(0)+" &7请注意查看."));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);

                /*player.getInventory().removeItem(nickItem);*/
                player.closeInventory();
                break;
            case "Dragon_Nick_Cancel":
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7成功取消掉本次的名称修改行为."));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                player.closeInventory();
                break;
        }
    }
}
