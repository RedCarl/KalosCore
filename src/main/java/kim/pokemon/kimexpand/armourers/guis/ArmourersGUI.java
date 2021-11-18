package kim.pokemon.kimexpand.armourers.guis;

import com.google.common.collect.Lists;
import eos.moe.armourers.api.DragonAPI;
import kim.pokemon.configFile.GlazedPay.Data;
import kim.pokemon.kimexpand.armourers.ArmourersManager;
import kim.pokemon.ui.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.api.DragonItemAPI;
import kim.pokemon.util.api.ItemFacAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
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

        super(ColorParser.parse("&0Kim / 时装系统"), player, 6);

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
}
