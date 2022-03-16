package kim.pokemon.kimexpand.nick;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.PlayerManager;
import eos.moe.dragoncore.api.gui.event.CustomPacketEvent;
import eos.moe.dragoncore.network.PacketSender;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    YamlConfiguration DragonGUI = YamlConfiguration.loadConfiguration(new File(Bukkit.getPluginManager().getPlugin("KimCore").getDataFolder(),"Nick/gui.yml"));
    YamlConfiguration NickData;
    ItemStack nickItem = ItemFactoryAPI.getItemStack(Material.NAME_TAG,
            ColorParser.parse("&6Kalos &f// &6改名卡"),
            ColorParser.parse("&8消耗品 (右键使用)"),
            ColorParser.parse("&r"),
            ColorParser.parse("&7可以更改自己的名字，但无法更改登录的ID。"),
            ColorParser.parse("&7注意，登录和显示的名字不一样。")
    );

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player player = event.getPlayer();
            ItemStack i = event.getItem();

            if (i!=null&&i.getItemMeta().equals(nickItem.getItemMeta())){
                PacketSender.sendYaml(player,"gui/NickGUI.yml", DragonGUI);
                PacketSender.sendOpenGui(player, "NickGUI");
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
                    File Data = new File(Bukkit.getPluginManager().getPlugin("KimCore").getDataFolder(),"Nick/data.yml");
                    NickData = YamlConfiguration.loadConfiguration(Data);
                    NickData.set(player.getName(),Name);
                    NickData.save(Data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7成功将您的游戏名称更改为 &a"+event.getData().get(0)+" &7请注意查看."));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);

                player.getInventory().removeItem(nickItem);
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
