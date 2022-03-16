package kim.pokemon.kimexpand.nametag;

import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.armourers.guis.ArmourersGUI;
import kim.pokemon.kimexpand.kits.PlayerKits;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.nametag.entity.TagEntity;
import kim.pokemon.kimexpand.playerinfo.PlayerInfo;
import kim.pokemon.kimexpand.recharge.shop.ItemSell;
import kim.pokemon.kimexpand.recharge.shop.entity.ItemInfo;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class NameTag extends InventoryGUI {

    public NameTag(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 头衔系统"), player, 6);

        User user= Main.luckPerms.getUserManager().getUser(player.getUniqueId());

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
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" meta addprefix "+tagEntity.getPriority()+" "+tagEntity.getPrefix()+" server="+ Main.luckPerms.getServerName());
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

        //个人信息
        ItemStack PlayerInfo = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/f4885633f0c91a6fe8652fff2414198f69a943f38b648e4739880a1703b24ce1",
                ColorParser.parse("&e个人信息"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o查看您的各种数据统计信息."));
        Button PlayerInfoButton = new Button(PlayerInfo, type -> {
            if (type.isLeftClick()) {
                PlayerInfo playerInfo = new PlayerInfo(player);
                playerInfo.openInventory();
            }
        });
        this.setButton(45, PlayerInfoButton);

        //头衔系统
        ItemStack NameTag = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e333e741c7182f28f43e1804c2bb0f8d79a73d747c8265297f5ed0e5d24e35e8",
                ColorParser.parse("&6头衔仓库"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o管理您的头衔，装配或者取下."));
        Button NameTagButton = new Button(NameTag, type -> {
            if (type.isLeftClick()) {
                kim.pokemon.kimexpand.nametag.NameTag nameTag = new NameTag(player);
                nameTag.openInventory();
            }
        });
        this.setButton(46, NameTagButton);

        //礼包系统
        ItemStack Kits = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e0a65a188e23457fab9ae73427ab5dde73fcb79ac59257d3509ebd9c386a8eab",
                ColorParser.parse("&b礼包系统"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里有各种礼包可以供您领取."));
        Button KitsButton = new Button(Kits, type -> {
            if (type.isLeftClick()) {
                PlayerKits playerKits = new PlayerKits(player);
                playerKits.openInventory();
            }
        });
        this.setButton(47, KitsButton);

        //时装系统
        ItemStack Armourers = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/911db4b3eefb72e661c9a5388753a2cee5e6453582641a1f0b866eb4af5ab22c",
                ColorParser.parse("&d时装仓库"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o这里储存着您拥有的时装，随时都可以穿戴."));
        Button ArmourersButton = new Button(Armourers, type -> {
            if (type.isLeftClick()) {
                ArmourersGUI armourersGUI = new ArmourersGUI(player);
                armourersGUI.openInventory();
            }
        });
        this.setButton(48, ArmourersButton);

        //头衔重置
        ItemStack ClearTag = ItemFactoryAPI.getItemStack(Material.ANVIL,
                ColorParser.parse("&e头衔重置"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o将您的头衔恢复至默认状态."));
        Button ClearTagButton = new Button(ClearTag, type -> {
            if (type.isLeftClick()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" meta clear server="+ Main.luckPerms.getServerName());
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
