package kim.pokemon.kimexpand.armourers.guis;

import com.google.common.collect.Lists;
import eos.moe.armourers.api.DragonAPI;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.armourers.ArmourersManager;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.api.DragonItemAPI;
import kim.pokemon.util.api.ItemFacAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArmourersGUI extends InventoryGUI {
    public ArmourersGUI(Player player) {

        super(ColorParser.parse("&0"+Data.SERVER_NAME+" / 时装系统"), player, 6);

        File f = new File(Data.ARMOURERS_PATH);
        if(f.exists()){
            List<String> name_list = Arrays
                    .stream(Objects.requireNonNull(f.listFiles()))
                    .filter(File::isDirectory)
                    .map(File::getName)
                    .filter(name->player.hasPermission("kim.armourers."+name)).collect(Collectors.toList());
            List<ItemStack> is_list = Lists.newArrayList();

            for (int i = 0; i < name_list.size(); i++) {
                if (name_list.get(i).equals("隐形时装不要删除")){
                    name_list.remove(i);
                    break;
                }
            }

            for (String s : name_list) {
                is_list.add(ItemFacAPI.getItemStack(Material.ENDER_CHEST, "§r" + s));
            }

            for(int i=0;i<name_list.size();i++){
                String name = name_list.get(i);
                File dic = new File(Data.ARMOURERS_PATH,name);
                List<String> armourers = Arrays.stream(Objects.requireNonNull(dic.list())).filter(n->n.endsWith("armour")).map(armourer->armourer.split("\\.")[0]).collect(Collectors.toList());
                for (String armourer : armourers) {
                    String type = DragonAPI.getSkinType(armourer);
                    if (type.equals("Head")) {
                        is_list.set(i, DragonItemAPI.getItemStack(armourer, ColorParser.parse("&c" + name_list.get(i)+" &6[永久]")));
                    }
                }
            }

            for(int i =0;i<name_list.size();i++){
                String name = name_list.get(i);
                Button b = new Button(is_list.get(i),(type)->{
                    ArmourersManager.setPlayerArmourers(player,name);
                    DragonAPI.updatePlayerSkin(player);
                    player.closeInventory();
                });
                this.setButton(i,b);
            }
        }


        ItemStack A = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530",ColorParser.parse("&a第一页"));
        Button AButton = new Button(A, type -> {
            if (type.isLeftClick()) {
            }
        });
        this.setButton(45,AButton);
        ItemStack B = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847",ColorParser.parse("&a第二页"));
        Button BButton = new Button(B, type -> {
            if (type.isLeftClick()) {
            }
        });
        this.setButton(46,BButton);


        ItemStack Reset = ItemFactoryAPI.getItemStack(Material.TOTEM, ColorParser.parse("&a恢复默认"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o恢复默认的时装，将身上的时装卸下."));
        Button ResetButton = new Button(Reset, type -> {
            if (type.isLeftClick()) {
                ArmourersManager.setPlayerArmourers(player,null);
                DragonAPI.updatePlayerSkin(player);
                player.closeInventory();
            }
        });
        this.setButton(47, ResetButton);

        for (int j = 0; j < 9; j++) {
            ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
            this.setButton(36+j, new Button(Line));
        }
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
