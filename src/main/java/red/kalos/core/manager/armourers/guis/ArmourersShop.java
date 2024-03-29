package red.kalos.core.manager.armourers.guis;

import com.google.common.collect.Lists;
import eos.moe.armourers.api.DragonAPI;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.armourers.ArmourersManager;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.DragonItemAPI;
import red.kalos.core.util.api.ItemFacAPI;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ArmourersShop extends InventoryGUI {
    HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
    public static HashMap<Player, Long> playerLongHashMap = new HashMap<>();
    
    public ArmourersShop(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 时装商店"), player, 6);

        //时装碎片
        long ArmourersMoney=-1;
        int ArmourersPrice=3;
        ItemStack Armourers = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&c"+Data.SERVER_NAME_CN+"の时装碎片"),
                ColorParser.parse("&f范围: &a1~10 &f个"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + ArmourersMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + ArmourersPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o时装碎片可以去兑换时装，需要非常多哦!"));

        //初始化时装价格
        stringIntegerHashMap.put("楪祈",98);
        stringIntegerHashMap.put("电磁力",12);
        stringIntegerHashMap.put("觉醒者",30);
        stringIntegerHashMap.put("狂三",79);
        stringIntegerHashMap.put("水银灯",9999);
        stringIntegerHashMap.put("凝光",128);
        stringIntegerHashMap.put("莫娜",158);
        stringIntegerHashMap.put("谢菲尔",98);
        stringIntegerHashMap.put("迪卢克",168);
        stringIntegerHashMap.put("钟离",35);
        stringIntegerHashMap.put("温迪",35);
        stringIntegerHashMap.put("亚丝娜",79);
        stringIntegerHashMap.put("伊蕾娜",79);
        stringIntegerHashMap.put("胡桃",89);

        File f = new File(Data.ARMOURERS_PATH);
        if(f.exists()) {
            List<String> name_list = Arrays
                    .stream(Objects.requireNonNull(f.listFiles()))
                    .filter(File::isDirectory)
                    .map(File::getName)
                    .filter(name -> !player.hasPermission("kim.armourers." + name)).collect(Collectors.toList());
            List<ItemStack> is_list = Lists.newArrayList();

            name_list.removeIf(value -> value.equals("隐形时装不要删除") || value.equals("活动"));

            for (String s : name_list) {
                is_list.add(ItemFacAPI.getItemStack(Material.ENDER_CHEST, "§r" + s));
            }

            for (int i = 0; i < name_list.size(); i++) {
                String name = name_list.get(i);
                File dic = new File(Data.ARMOURERS_PATH, name);
                List<String> armourers = Arrays.stream(Objects.requireNonNull(dic.list())).filter(n -> n.endsWith("armour")).map(armourer -> armourer.split("\\.")[0]).collect(Collectors.toList());
                for (String armourer : armourers) {
                    String type = DragonAPI.getSkinType(armourer);
                    if (type.equals("Head")) {
                        is_list.set(i, DragonItemAPI.getItemStack(armourer,
                                ColorParser.parse("&b" + name_list.get(i)),
                                ColorParser.parse("&r"),
                                ColorParser.parse("&r  &e■ &7售 价:"),
                                ColorParser.parse("&r      &7(左键) &c试穿 30 秒"),
                                ColorParser.parse("&r      &7(右键) &c" + stringIntegerHashMap.get(name_list.get(i)) + " &7时装碎片")
                                ));
                    }
                }
            }

            for (int i = 0; i < name_list.size(); i++) {
                String name = name_list.get(i);
                Button b = new Button(is_list.get(i), (type) -> {
                    if (type.isLeftClick()){
                        if (!playerLongHashMap.containsKey(player)){
                            ArmourersManager.setPlayerTempArmourers(player,name,30);
                            DragonAPI.updatePlayerSkin(player);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7已临时展示时装，30秒后将复原, 按F5可切换视角."));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                            player.closeInventory();
                        }else {
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您已经在展示时装了,请按F5可切换视角查看."));
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.closeInventory();
                        }

                    }
                    if (type.isRightClick()){
                        if (player.getInventory().containsAtLeast(Armourers, stringIntegerHashMap.get(name))){
                            player.closeInventory();
                            Armourers.setAmount(stringIntegerHashMap.get(name));
                            player.getInventory().removeItem(Armourers);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set kim.armourers."+name+" server="+ Main.getLuckPerms().getServerName());
                            player.sendTitle(ColorParser.parse("&b卡洛斯の时装系统"), ColorParser.parse("&f恭喜您,获得了 &b"+name+" &f时装,请前往个人中心查看."),0,60,0);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                        }else {
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c时装碎片 &7来兑换这个时装."));
                            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                        }
                    }
                });
                this.setButton(i, b);
            }
        }








        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至主菜单."));
        Button CloseButton = new Button(Close, type -> {
            new ArmourersGUI(player).openInventory();
        });
        this.setButton(53, CloseButton);
    }

}
