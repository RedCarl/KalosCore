package red.kalos.core.manager.nametag;

import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.menu.MainMenu;
import red.kalos.core.manager.nametag.entity.TagEntity;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

public class NameTag extends InventoryGUI {

    public NameTag(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 头衔系统"), player, 6);

        User user= Main.getLuckPerms().getUserManager().getUser(player.getUniqueId());

        ArrayList<TagEntity> tagEntities = new ArrayList<>();


        tagEntities.add(new TagEntity("先锋","kim.tag.先锋","&9[先锋]",null,100));


        int a = 0;
        for (TagEntity tagEntity:tagEntities) {
            Material ItemMaterial = Material.NAME_TAG;
            ItemStack Item = ItemFactoryAPI.getItemStack(ItemMaterial,
                    ColorParser.parse(tagEntity.getPrefix()),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &6■ &7介 绍:"),
                    ColorParser.parse("&r      &7敬请期待...")
            );
            Button ItemButton = new Button(Item, type -> {
                if (type.isLeftClick()) {
                    if (player.hasPermission(tagEntity.getPermission())){
                        if (user!=null&&!Objects.equals(user.getCachedData().getMetaData().getPrefix(), tagEntity.getPrefix())){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" meta addprefix "+tagEntity.getPriority()+" "+tagEntity.getPrefix()+" server="+ Main.getLuckPerms().getServerName());
                            player.sendTitle(ColorParser.parse("&b卡洛斯の头衔系统"),ColorParser.parse("&f您成功更换 "+tagEntity.getPrefix()+" &f头衔."),0,60,0);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            player.closeInventory();
                        }else {
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您正在佩戴 "+tagEntity.getPrefix()+" 头衔，不能进行操作."));
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.closeInventory();
                        }

                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有 "+tagEntity.getPrefix()+" 头衔，不能进行操作."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        player.closeInventory();
                    }
                }
            });
            this.setButton(a, ItemButton);
            a++;
        }



        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }

        //头衔重置
        ItemStack ClearTag = ItemFactoryAPI.getItemStack(Material.ANVIL,
                ColorParser.parse("&e头衔重置"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o将您的头衔恢复至默认状态."));
        Button ClearTagButton = new Button(ClearTag, type -> {
            if (type.isLeftClick()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" meta clear server="+ Main.getLuckPerms().getServerName());
                player.sendTitle(ColorParser.parse("&b卡洛斯の头衔系统"),ColorParser.parse("&f您成功将您的头衔重置为默认."),0,60,0);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                player.closeInventory();
            }
        });
        this.setButton(52, ClearTagButton);

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
