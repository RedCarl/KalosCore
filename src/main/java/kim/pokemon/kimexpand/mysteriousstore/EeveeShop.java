package kim.pokemon.kimexpand.mysteriousstore;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.kimexpand.pokeinfo.gui.PokeInfoUpdate;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.api.PokemonPhotoAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class EeveeShop extends InventoryGUI {
    public EeveeShop(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 伊布の黑金商店"), player, 6);

//        /*创建文件夹*/
//        PokemonPhotoAPI.getFolder("MysteriousStore");
//        PokemonPhotoAPI.getFolder("MysteriousStore/EeveeShop");
//
//        File file = new File(Main.getInstance().getDataFolder(), "MysteriousStore/EeveeShop/"+player.getName() + ".yml");
//        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
//
//        try {
//            if (config.get("ShopList")==null){
//                List<String> pokemon = new ArrayList<>();
//                for (int i = 0; i < 7; i++) {
//                    String p=PokemonAPI.getRandomLegendaryPokemon().getLocalizedName();
//                    if (pokemon.contains(p)){//|| Objects.equals(p, "阿尔宙斯") || Objects.equals(p, "基格尔德")
//                        i--;
//                        continue;
//                    }
//                    pokemon.add(p);
//                }
//                config.set("ShopList",pokemon);
//                config.set("Buy",1);
//                config.save(file);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //获取今天的日期
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date;
//        long time = 0;
//        try {
//            date = format.parse(format.format(System.currentTimeMillis()));
//            time = date.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        //制作GUI
//        for (int i = 0; i < config.getStringList("ShopList").size(); i++) {
//            ItemStack PokemonPhoto =  PokemonPhotoAPI.getPhotoItemC(PokemonAPI.SpawnPokemon(config.getStringList("ShopList").get(i)));
//
//            int finalI = i;
//            long finalTime = time;
//            Button pokemonButton = new Button(PokemonPhoto, type -> {
//                if (type.isLeftClick()){
//                    try {
//                        if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=79){
//                            if (player.getFirstPlayed()<=finalTime){
//                                if (config.getInt("Buy")==1){
//                                    try {
//                                        Main.ppAPI.takeAsync(player.getUniqueId(),79);
//                                        PokemonAPI.GivePokemon(player,false,3,0,false,PokemonAPI.SpawnPokemon(config.getStringList("ShopList").get(finalI)));
//                                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功购买了这个商品，已经发往您的仓库."));
//                                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
//                                        player.closeInventory();
//                                        config.set("Buy",0);
//                                        config.save(file);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }else {
//                                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只能购买一次，无法再次购买."));
//                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                    player.closeInventory();
//                                }
//                            }else {
//                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，刚注册的玩家无法参与活动."));
//                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
//                                player.closeInventory();
//                            }
//                        }else {
//                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只有 &c"+Main.ppAPI.lookAsync(player.getUniqueId()).get()+" &7"+Data.SERVER_POINTS+"，不足以支付."));
//                            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                        }
//                    } catch (InterruptedException | ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            this.setButton(10+i,pokemonButton);
//        }


        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //伊布の折扣店
        ItemStack A = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ce0752ba89de69834a47f65a17c88498d07c87e54d9926f4fb9be2ce4d94f96f",
                ColorParser.parse("&e伊布の折扣店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每日随机的折扣商品供您挑选."));
        Button AButton = new Button(A, type -> {
            if (type.isLeftClick()) {
                EeveeShop eeveeShop = new EeveeShop(player);
                eeveeShop.openInventory();
            }
        });
        this.setButton(45, AButton);

        //限时累计充值
        ItemStack B = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/137f05b86b1a7fd14f29c24e15db3636d8912276ca0d566658a95645b3339f23",
                ColorParser.parse("&6皮卡丘の宝藏"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o一些限时的礼包和商品."));
        Button BButton = new Button(B, type -> {
            if (type.isLeftClick()) {
                PikachuShop pikachuShop = new PikachuShop(player);
                pikachuShop.openInventory();
            }
        });
        this.setButton(46, BButton);

        //活动兑换
        ItemStack C = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/6eac4e2908e39750a9b97fdf8c272f8c647786d452fb26a73dc37d39f5a864b9",
                ColorParser.parse("&c小火龙の活动"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用于活动兑换物品."));
        Button CButton = new Button(C, type -> {
            if (type.isLeftClick()) {
                CharmanderShop charmanderShop = new CharmanderShop(player);
                charmanderShop.openInventory();
            }
        });
        this.setButton(47, CButton);

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
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
