package red.kalos.core.manager.optional;

import com.intellectualcrafters.plot.object.PlotPlayer;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.plotsquared.bukkit.object.BukkitPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.pokedex.PokeDexManager;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.manager.item.CustomItem;
import red.kalos.core.util.api.PokemonPhotoAPI;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OptionalGUI extends InventoryGUI {
    public OptionalGUI(Player player, String Type) {
        super(ColorParser.parse("&0" + Data.SERVER_NAME + " / 宝箱"), player, 6);
        String[] poke = new String[0];

        if (Type.equals("MaxLegend")){
            poke = new String[]{
                    "Mewtwo",
                    "Ho-Oh",
                    "Lugia",
                    "Kyogre",
                    "Groudon",
                    "Rayquaza",
                    "Dialga",
                    "Palkia",
                    "Giratina",
                    "Regigigas",
                    "Reshiram",
                    "Zekrom",
                    "Xerneas",
                    "Yveltal",
                    "Zygarde",
                    "Hoopa",
                    "Lunala",
                    "Solgaleo",
                    "Zacian",
                    "Zamazenta",
                    "Eternatus",
                    "Calyrex"
            };
        }
        if (Type.equals("累充128")){
            poke = new String[]{
                    "Tyranitar",
                    "Lucario",
                    "Salamence",
            };
        }
        if (Type.equals("累充328")){
            poke = new String[]{
                    "Kartana",
                    "Celesteela",
            };
        }


        if (Type.contains("累充") || Type.equals("MaxLegend")){
            int index = 0;
            for (String s:poke) {
                Pokemon pokemon = PokemonAPI.SpawnPokemon(s);
                this.setButton(index,new Button(PokemonPhotoAPI.getPhotoItemSelect(pokemon), type -> {
                    if (CustomItem.useEncryptionItem(player,player.getItemInHand())!=null){
                        PokemonAPI.GivePokemon(player,false,3,0,false,pokemon);
                        PokeDexManager.getInstance().addPokeDex(pokemon,player);
                    }
                    player.closeInventory();
                }));
                index++;
            }
        }

        if (Type.contains("Random")){
            this.setButton(20,new Button(ItemFactoryAPI.getItemStack(Material.STAINED_GLASS_PANE,(short) 5,
                    ColorParser.parse("&a确认"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7此操作会使用该物品，无法撤回。")

            ), type -> {
                if (player.getItemInHand()!=null){
                    if (Type.equals("RandomLegend")){
                        player.closeInventory();
                        new Thread(()->{
                            for (int i = 0; i <= 50; i++) {
                                try {
                                    player.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse(EnumSpecies.randomLegendary().getLocalizedName()),0,20,0);
                                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
                                    if (i<=10){
                                        Thread.sleep(30);
                                    }else if (i<=20){
                                        Thread.sleep(50);
                                    }else if (i<=30){
                                        Thread.sleep(100);
                                    }else if (i<=40){
                                        Thread.sleep(300);
                                    }else if (i<=45){
                                        Thread.sleep(500);
                                    }else if (i<=49){
                                        Thread.sleep(800);
                                    }else {
                                        if (CustomItem.useEncryptionItem(player,player.getItemInHand())!=null){
                                            Pokemon pokemon = PokemonAPI.getRandomLegendaryPokemon();
                                            PokemonAPI.GivePokemon(player,false,3,0,false,pokemon);
                                            player.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse("&f恭喜您,获得了 &6"+pokemon.getLocalizedName()+" &f传说宝可梦."),0,60,0);
                                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                                            PokeDexManager.getInstance().addPokeDex(pokemon,player);
                                            if (PokemonAPI.isLegendaryMaxPokemon(pokemon)){
                                                for (Player p: Bukkit.getOnlinePlayers()) {
                                                    if (p!=player){
                                                        p.sendTitle(ColorParser.parse("&b卡洛斯の传说宝可梦"),ColorParser.parse("&f恭喜 &c"+player.getName()+" &f,获得了 &6"+pokemon.getLocalizedName()+" &f传说宝可梦."),0,60,0);
                                                        p.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                                                    }
                                                }
                                            }
                                        }else {
                                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，请不要将 &c宝箱 &7从 &c主手 &7中移除。"));
                                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                        }

                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                    if (Type.equals("RandomEvolution")){
                        String name = PokemonAPI.getRandomEvolution(player);
                        player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您,您获得了一个 &c"+name+" &7超进化石."));
                        CustomItem.useEncryptionItem(player,player.getItemInHand());
                    }
                    if (Type.equals("RandomPlot")){
                        PlotPlayer plotPlayer = new BukkitPlayer(player);
                        if (plotPlayer.getAllowedPlots()>=4){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set plots.plot."+(plotPlayer.getAllowedPlots()+1)+" true server="+ Main.getLuckPerms().getServerName());
                            CustomItem.useEncryptionItem(player,player.getItemInHand());
                            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您的地皮拥有资格现在 &a+1 &7请查看。"));
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                        }else {
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您需要开通 &6伊布 &7会员才可以使用。"));
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        }

                    }
                }
                player.closeInventory();
            }));

            this.setButton(24,new Button(ItemFactoryAPI.getItemStack(Material.STAINED_GLASS_PANE,(short) 14,
                    ColorParser.parse("&c取消"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7此操作会取消使用该物品。")
            ), type -> {
                player.closeInventory();
            }));
        }


        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }


        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c取消"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o取消本次操作，不会消耗物品."));
        Button CloseButton = new Button(Close, type -> {
            player.closeInventory();
        });
        this.setButton(53, CloseButton);
    }
}
