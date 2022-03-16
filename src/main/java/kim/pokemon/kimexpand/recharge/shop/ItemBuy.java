package kim.pokemon.kimexpand.recharge.shop;

import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.recharge.ArmourersShop;
import kim.pokemon.kimexpand.recharge.GiftPackShop;
import kim.pokemon.kimexpand.recharge.GrandTotal;
import kim.pokemon.kimexpand.recharge.recharge.RechargeMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ExecutionException;

public class ItemBuy extends InventoryGUI {
    PlayerPointsAPI playerPointsAPI = Main.ppAPI;
    Economy economy = Main.econ;
    
    public ItemBuy(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 道具出售"), player, 6);

        //牧场
        long RanchMoney=21000;
        int RanchPrice=7;
        ItemStack Ranch = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH"),
                ColorParser.parse("&c"+Data.SERVER_NAME_CN+"の牧场"),
                ColorParser.parse("&f范围: &a1~1 &f个"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + RanchMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + RanchPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用于对宝可梦进行繁殖,无法繁殖 &c3v &7&o以上宝可梦!"));
        Button RanchButton = new Button(Ranch, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>=RanchMoney){
                    economy.withdrawPlayer(player,RanchMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Ranch.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=RanchPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),RanchPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RANCH")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Ranch.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(0, RanchButton);


        //幸运方块
        long PokeLuckyMoney = 6000;
        int PokeLuckyPrice=2;
        ItemStack PokeLucky = ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY"),
                ColorParser.parse("&c"+Data.SERVER_NAME_CN+"の幸运方块"),
                ColorParser.parse("&f概率: &a普通(95%) &6传奇(5%)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + PokeLuckyMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + PokeLuckyPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o来试试你是否真的幸运."));
        Button PokeLuckyButton = new Button(PokeLucky, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>=PokeLuckyMoney){
                    economy.withdrawPlayer(player,PokeLuckyMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+PokeLucky.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=PokeLuckyPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),PokeLuckyPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("POKELUCKY_POKE_LUCKY")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+PokeLucky.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(1, PokeLuckyButton);


        //奈克洛索尔合体器
        long N_SolarizerMoney = -1;
        int N_SolarizerPrice=30;
        ItemStack N_Solarizer = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_N_SOLARIZER"),
                ColorParser.parse("&6奈克洛索尔合体器"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + N_SolarizerMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + N_SolarizerPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用来让需求光的奈克洛兹玛和索尔迦雷欧合体的机器."));
        Button N_SolarizerButton = new Button(N_Solarizer, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=N_SolarizerPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),N_SolarizerPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_N_SOLARIZER")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+N_Solarizer.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(2, N_SolarizerButton);


        //奈克洛索尔合体器
        long N_LunarizerMoney = -1;
        int N_LunarizerPrice=30;
        ItemStack N_Lunarizer = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_N_LUNARIZER"),
                ColorParser.parse("&b奈克洛露奈合体器"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + N_LunarizerMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + N_LunarizerPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用来让需求光的奈克洛兹玛和露奈雅拉合体的机器."));
        Button N_LunarizerButton = new Button(N_Lunarizer, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=N_LunarizerPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),N_LunarizerPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_N_LUNARIZER")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+N_Lunarizer.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(3, N_LunarizerButton);


        //牵绊缰绳
        long Reins_Of_UnityMoney = -1;
        int Reins_Of_UnityPrice=30;
        ItemStack Reins_Of_Unity = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_REINS_OF_UNITY"),
                ColorParser.parse("&9牵绊缰绳"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Reins_Of_UnityMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Reins_Of_UnityPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o拿到光下即可生辉的布。人们曾经将其进献给丰饶之王以表感谢之意。"));
        Button Reins_Of_UnityButton = new Button(Reins_Of_Unity, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Reins_Of_UnityPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Reins_Of_UnityPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_REINS_OF_UNITY")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Reins_Of_Unity.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(4, Reins_Of_UnityButton);


        //朱红色宝珠
        long Red_OrbMoney = -1;
        int Red_OrbPrice=19;
        ItemStack Red_Orb = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RED_ORB"),
                ColorParser.parse("&c朱红色宝珠"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Red_OrbMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Red_OrbPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o散发着红色光辉的宝珠。据说和丰缘地区的传说渊源颇深。"));
        Button Red_OrbButton = new Button(Red_Orb, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Red_OrbPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Red_OrbPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RED_ORB")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Red_Orb.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(5, Red_OrbButton);


        //朱红色宝珠
        long Blue_OrbMoney = -1;
        int Blue_OrbPrice=19;
        ItemStack Blue_Orb = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_BLUE_ORB"),
                ColorParser.parse("&9靛蓝色宝珠"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Blue_OrbMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Blue_OrbPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o散发着蓝色光辉的宝珠。据说和丰缘地区的传说渊源颇深。"));
        Button Blue_OrbButton = new Button(Blue_Orb, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Blue_OrbPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Blue_OrbPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_BLUE_ORB")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Blue_Orb.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(6, Blue_OrbButton);


        //高级球
        long Ultra_BallMoney = 70;
        int Ultra_BallPrice=-1;
        ItemStack Ultra_Ball = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),
                ColorParser.parse("&6高级球"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Ultra_BallMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Ultra_BallPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o比起超级球来更容易捉到宝可梦的，性能非常不错的球。"));
        Button Ultra_BallButton = new Button(Ultra_Ball, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Ultra_BallMoney){
                    economy.withdrawPlayer(player,Ultra_BallMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Ultra_Ball.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(7, Ultra_BallButton);


        //大师球
        long Master_BallMoney = 50000;
        int Master_BallPrice = 5;
        ItemStack Master_Ball = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                ColorParser.parse("&6高级球"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Master_BallMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Master_BallPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o比起超级球来更容易捉到宝可梦的，性能非常不错的球。"));
        Button Master_BallButton = new Button(Master_Ball, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Master_BallMoney){
                    economy.withdrawPlayer(player,Master_BallMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Master_Ball.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Master_BallPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Master_BallPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Master_Ball.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(8, Master_BallButton);


        //纯净珠
        long OrbMoney = 190000;
        int OrbPrice = 19;
        ItemStack Orb = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ORB"),
                ColorParser.parse("&f纯净珠"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + OrbMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + OrbPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用于召唤传说钟的闪电鸟。"));
        Button OrbButton = new Button(Orb, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>OrbMoney){
                    economy.withdrawPlayer(player,OrbMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ORB")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Orb.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=OrbPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),OrbPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ORB")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Orb.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(9, OrbButton);

        //红线
        long Destiny_KnotMoney = 200000;
        int Destiny_KnotPrice = 20;
        ItemStack Destiny_Knot = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DESTINY_KNOT"),
                ColorParser.parse("&c红线"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Destiny_KnotMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Destiny_KnotPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o长长的鲜红色细线。携带后，在自己着迷时能让对手也着迷。"));
        Button Destiny_KnotButton = new Button(Destiny_Knot, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Destiny_KnotMoney){
                    economy.withdrawPlayer(player,Destiny_KnotMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DESTINY_KNOT")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Destiny_Knot.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Destiny_KnotPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Destiny_KnotPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_DESTINY_KNOT")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Destiny_Knot.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(10, Destiny_KnotButton);


        //金色沙漏
        long Hourglass_GoldMoney = 20000;
        int Hourglass_GoldPrice = 2;
        ItemStack Hourglass_Gold = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HOURGLASS_GOLD"),
                ColorParser.parse("&e金色沙漏"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Hourglass_GoldMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Hourglass_GoldPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用于增加牧场宝可梦繁殖速度。"));
        Button Hourglass_GoldButton = new Button(Hourglass_Gold, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Hourglass_GoldMoney){
                    economy.withdrawPlayer(player,Hourglass_GoldMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HOURGLASS_GOLD")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Hourglass_Gold.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Hourglass_GoldPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Hourglass_GoldPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HOURGLASS_GOLD")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Hourglass_Gold.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(11, Hourglass_GoldButton);


        //银色沙漏
        long Hourglass_SilverMoney = 10000;
        int Hourglass_SilverPrice = 1;
        ItemStack Hourglass_Silver = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HOURGLASS_SILVER"),
                ColorParser.parse("&f银色沙漏"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Hourglass_SilverMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Hourglass_SilverPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用于增加牧场宝可梦繁殖速度。"));
        Hourglass_Silver.setAmount(2);
        Button Hourglass_SilverButton = new Button(Hourglass_Silver, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }

            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Hourglass_SilverMoney){
                    economy.withdrawPlayer(player,Hourglass_SilverMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HOURGLASS_SILVER")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Hourglass_Silver.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Hourglass_SilverPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Hourglass_SilverPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HOURGLASS_SILVER")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Hourglass_Silver.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(12, Hourglass_SilverButton);


        //超梦进化石X
        long Mewtwonite_XMoney = 490000;
        int Mewtwonite_XPrice = 49;
        ItemStack Mewtwonite_X = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MEWTWONITE_X"),
                ColorParser.parse("&3超梦进化石 X"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Mewtwonite_XMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Mewtwonite_XPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o让超梦携带后，在战斗时就能进行超级进化的一种神奇超级石。"));
        Button Mewtwonite_XButton = new Button(Mewtwonite_X, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Mewtwonite_XMoney){
                    economy.withdrawPlayer(player,Mewtwonite_XMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MEWTWONITE_X")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Mewtwonite_X.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Mewtwonite_XPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Mewtwonite_XPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MEWTWONITE_X")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Mewtwonite_X.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(13, Mewtwonite_XButton);


        //超梦进化石X
        long Mewtwonite_YMoney = 490000;
        int Mewtwonite_YPrice = 49;
        ItemStack Mewtwonite_Y = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MEWTWONITE_Y"),
                ColorParser.parse("&d超梦进化石 Y"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Mewtwonite_YMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Mewtwonite_YPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o让超梦携带后，在战斗时就能进行超级进化的一种神奇超级石。"));
        Button Mewtwonite_YButton = new Button(Mewtwonite_Y, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Mewtwonite_YMoney){
                    economy.withdrawPlayer(player,Mewtwonite_YMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MEWTWONITE_Y")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Mewtwonite_Y.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Mewtwonite_YPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Mewtwonite_YPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MEWTWONITE_Y")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Mewtwonite_Y.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(14, Mewtwonite_YButton);


        //腐朽的剑
        long Rusted_SwordMoney = 190000;
        int Rusted_SwordPrice = 19;
        ItemStack Rusted_Sword = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RUSTED_SWORD"),
                ColorParser.parse("&c腐朽的剑"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Rusted_SwordMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Rusted_SwordPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o据说很久以前，英雄就是拿着这把剑驱走了灾厄。而现在早已变得锈迹斑斑。"));
        Button Rusted_SwordButton = new Button(Rusted_Sword, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Rusted_SwordMoney){
                    economy.withdrawPlayer(player,Rusted_SwordMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RUSTED_SWORD")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Rusted_Sword.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Rusted_SwordPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Rusted_SwordPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RUSTED_SWORD")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Rusted_Sword.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(15, Rusted_SwordButton);


        //腐朽的盾
        long Rusted_ShieldMoney = 190000;
        int Rusted_ShieldPrice = 19;
        ItemStack Rusted_Shield = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RUSTED_SHIELD"),
                ColorParser.parse("&c腐朽的盾"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Rusted_ShieldMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Rusted_ShieldPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o据说很久以前，英雄就是拿着这面盾驱走了灾厄。而现在早已变得锈迹斑斑。"));
        Button Rusted_ShieldButton = new Button(Rusted_Shield, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Rusted_ShieldMoney){
                    economy.withdrawPlayer(player,Rusted_ShieldMoney);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RUSTED_SHIELD")));
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Rusted_Shield.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Rusted_ShieldPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Rusted_ShieldPrice);
                        player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RUSTED_SHIELD")));
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Rusted_Shield.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(16, Rusted_ShieldButton);

        ///限时夜视药水
        long Night_VisionMoney = 1000;
        int Night_VisionPrice = 1;
        ItemStack Night_Vision = ItemFactoryAPI.getItemStack(Material.POTION,
                ColorParser.parse("&9夜视药水 &c(+60秒)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + Night_VisionMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + Night_VisionPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o能让你在漆黑的夜晚也能看见光明。"));
        Button Night_VisionButton = new Button(Night_Vision, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>Night_VisionMoney){
                    economy.withdrawPlayer(player,Night_VisionMoney);

                    if (player.getPotionEffect(PotionEffectType.NIGHT_VISION)!=null){
                        int i = (player.getPotionEffect(PotionEffectType.NIGHT_VISION).getDuration()+(20*60));
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,i,2));
                    }else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,(20*60),2));
                    }

                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Night_Vision.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=Night_VisionPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),Night_VisionPrice);

                        if (player.getPotionEffect(PotionEffectType.NIGHT_VISION)!=null){
                            int i = (player.getPotionEffect(PotionEffectType.NIGHT_VISION).getDuration()+(20*60));
                            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,i,2));
                        }else {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,(20*60),2));
                        }

                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Night_Vision.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(17, Night_VisionButton);


        ///限时夜视药水
        long NickMoney = 1000;
        int NickPrice = 1;
        ItemStack nickItem = ItemFactoryAPI.getItemStack(Material.NAME_TAG,
                ColorParser.parse("&6Kalos &f// &6改名卡"),
                ColorParser.parse("&8消耗品 (右键使用)"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7可以更改自己的名字，但无法更改登录的ID。"),
                ColorParser.parse("&7注意，登录和显示的名字不一样。")
        );
        ItemStack Nick = ItemFactoryAPI.getItemStack(Material.NAME_TAG,
                ColorParser.parse("&6Kalos &f// &6改名卡"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c" + NickMoney + " &7"+Data.SERVER_VAULT),
                ColorParser.parse("&r      &7(右键) &c" + NickPrice + " &7"+Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7可以更改自己的名字，但无法更改登录的ID。"),
                ColorParser.parse("&7注意，登录和显示的名字不一样。"));
        Button NickButton = new Button(Nick, type -> {
            boolean varOn = true;
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i) == null) {
                    varOn = false;
                    break;
                }
            }
            if (varOn){
                player.closeInventory();
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理空位后再试!"));
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                return;
            }
            if (type.isLeftClick()) {
                if (economy.getBalance(player)>NickMoney){
                    economy.withdrawPlayer(player,NickMoney);

                    player.getInventory().addItem(nickItem);

                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Nick.getItemMeta().getDisplayName()+" &7请注意查收."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+economy.getBalance(player)+" &7"+Data.SERVER_VAULT+"，不足以支付."));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
            if (type.isRightClick()) {
                try {
                    if (playerPointsAPI.lookAsync(player.getUniqueId()).get()>=NickPrice){
                        playerPointsAPI.takeAsync(player.getUniqueId(),NickPrice);

                        player.getInventory().addItem(nickItem);

                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了 "+Nick.getItemMeta().getDisplayName()+" &7请注意查收."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+playerPointsAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setButton(18, NickButton);

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
                ItemBuy itemBuy = new ItemBuy(player);
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
                ItemSell itemSell = new ItemSell(player);
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
                ColorParser.parse("&7&o可以购买时装来进行穿戴."));
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
