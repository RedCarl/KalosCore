package kim.pokemon.kimexpand.mission.missionlist;

import kim.pokemon.Main;
import kim.pokemon.database.PlayerEventDataSQLReader;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AchievemenMission {
    public void getAchievemen(Player player, InventoryGUI inventoryGUI){
        //精灵抓捕
        int Current = PlayerEventDataSQLReader.getPlayerEventData(player.getName(),"SuccessfulCapture", Main.luckPerms.getServerName()).size();
        if (!player.hasPermission("kim.mission.achievemen.精灵抓捕_1")){
            int Target = 20;
            ItemStack Mission = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_POKE_BALL"),
                    ColorParser.parse("&a抓捕精灵的萌新!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7任务内容: &f抓捕一定数量的精灵即可完成任务!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7任务进度:"),
                    ColorParser.parse("&r          &c"+ Current +"&7/&f"+ Target +" &f只宝可梦"),
                    ColorParser.parse("&r  &a■ &7任务奖励:"),
                    ColorParser.parse("&r          &c红色暴鲤龙*1"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&c点击完成任务,并领取奖励"));
            Button MissionButton = new Button(Mission, type -> {
                if (Current>=Target||player.getName().equals("Red_Carl")){
                    PokemonAPI.GivePokemon(player,true,0,0,false,PokemonAPI.SpawnPokemon("Gyarados"));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+"kim.mission.achievemen.精灵抓捕_1"+" true server="+Main.luckPerms.getServerName()+"");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您完成任务,奖励请查看 背包/PC 是否到账."));
                    player.closeInventory();
                }else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉,您还未完成任务,请完成任务后再领取奖励."));
                }
            });
            inventoryGUI.setButton(0, MissionButton);
        } else if (!player.hasPermission("kim.mission.achievemen.精灵抓捕_2")){
            int Target = 158;
            ItemStack Mission = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GREAT_BALL"),
                    ColorParser.parse("&b抓捕精灵的训练师!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7任务内容: &f抓捕一定数量的精灵即可完成任务!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7任务进度:"),
                    ColorParser.parse("&r          &c"+ Current +"&7/&f"+ Target +" &f只宝可梦"),
                    ColorParser.parse("&r  &a■ &7任务奖励:"),
                    ColorParser.parse("&r          &c糖果*30 + 暴鲤龙进化石*1"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&c点击完成任务,并领取奖励"));
            Button MissionButton = new Button(Mission, type -> {
                if (Current>=Target||player.getName().equals("Red_Carl")){
                    ItemStack i = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RARE_CANDY"));
                    i.setAmount(30);
                    player.getInventory().addItem(i);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_GYARADOSITE")));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+"kim.mission.achievemen.精灵抓捕_2"+" true server="+Main.luckPerms.getServerName()+"");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您完成任务,奖励请查看 背包/PC 是否到账."));
                    player.closeInventory();
                }else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉,您还未完成任务,请完成任务后再领取奖励."));
                }
            });
            inventoryGUI.setButton(0, MissionButton);
        }else if (!player.hasPermission("kim.mission.achievemen.精灵抓捕_3")){
            int Target = 376;
            ItemStack Mission = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"),
                    ColorParser.parse("&6抓捕精灵的老手!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7任务内容: &f抓捕一定数量的精灵即可完成任务!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7任务进度:"),
                    ColorParser.parse("&r          &c"+ Current +"&7/&f"+ Target +" &f只宝可梦"),
                    ColorParser.parse("&r  &a■ &7任务奖励:"),
                    ColorParser.parse("&r          &c极巨化手环*1+高级球*34"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&c点击完成任务,并领取奖励"));
            Button MissionButton = new Button(Mission, type -> {
                if (Current>=Target||player.getName().equals("Red_Carl")){

                    ItemStack i = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ULTRA_BALL"));
                    i.setAmount(34);
                    player.getInventory().addItem(i);
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_WISHING_STAR")));

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+"kim.mission.achievemen.精灵抓捕_3"+" true server="+Main.luckPerms.getServerName()+"");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您完成任务,奖励请查看 背包/PC 是否到账."));
                    player.closeInventory();
                }else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉,您还未完成任务,请完成任务后再领取奖励."));
                }
            });
            inventoryGUI.setButton(0, MissionButton);
        }else if (!player.hasPermission("kim.mission.achievemen.精灵抓捕_4")){
            int Target = 948;
            ItemStack Mission = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL"),
                    ColorParser.parse("&d抓捕精灵的大师!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&7任务内容: &f抓捕一定数量的精灵即可完成任务!"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&r  &e■ &7任务进度:"),
                    ColorParser.parse("&r          &c"+ Current +"&7/&f"+ Target +" &f只宝可梦"),
                    ColorParser.parse("&r  &a■ &7任务奖励:"),
                    ColorParser.parse("&r          &c地区三鸟*1 + 大师球*1"),
                    ColorParser.parse("&r"),
                    ColorParser.parse("&c点击完成任务,并领取奖励"));
            Button MissionButton = new Button(Mission, type -> {
                if (Current>=Target||player.getName().equals("Red_Carl")){
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MASTER_BALL")));

                    PokemonAPI.GiveRandomPokemon(player,false,3,1,PokemonAPI.SpawnPokemon("Moltres"),PokemonAPI.SpawnPokemon("Zapdos"),PokemonAPI.SpawnPokemon("Articuno"));

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player.getName()+" permission set "+"kim.mission.achievemen.精灵抓捕_4"+" true server="+Main.luckPerms.getServerName()+"");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7恭喜您完成任务,奖励请查看 背包/PC 是否到账."));
                    player.closeInventory();
                }else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉,您还未完成任务,请完成任务后再领取奖励."));
                }
            });
            inventoryGUI.setButton(0, MissionButton);
        }

    }
}
