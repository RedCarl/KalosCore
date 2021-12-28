package kim.pokemon.kimexpand.npc;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import kim.pokemon.kimexpand.crazyauctions.api.Category;
import kim.pokemon.kimexpand.crazyauctions.api.ShopType;
import kim.pokemon.kimexpand.crazyauctions.controllers.GUI;
import kim.pokemon.kimexpand.mission.MissionGUI;
import kim.pokemon.kimexpand.mysteriousstore.StoreGUI;
import kim.pokemon.kimexpand.pokeinfo.gui.PokemonInfoMenu;
import kim.pokemon.kimexpand.recharge.recharge.RechargeMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Arrays;
import java.util.HashMap;

public class NpcEntityEvent implements Listener {

    @EventHandler
    public void npc(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (player.getLocation().getWorld().getName().equals("spawn")){
            if (player.hasPermission("kim.admin")){
                System.out.println(entity.getUniqueId());
            }
            switch (entity.getUniqueId().toString()){
                //花丝小姐 (商店)
                case "e7c30e6a-34be-4ac5-be2e-20528e29a16f":
                    RechargeMenu rechargeMenu = new RechargeMenu(player);
                    rechargeMenu.openInventory();
                    event.setCancelled(true);
                    break;
                //玛丽亚 (任务)
                case "f632b0aa-0064-40e9-a8be-27b25df1a68b":
                    event.setCancelled(true);
                    MissionGUI missionGUI = new MissionGUI(player,"DAY");
                    missionGUI.openInventory();
                    break;
                //蒂莉 (签到)
                case "f6a4f7fd-db7f-4276-a63c-f01f30a78f9e":
                    Bukkit.dispatchCommand(player,"litesignin gui");
                    event.setCancelled(true);
                    break;
                //土豆先生 (市场)
                case "c6d366e4-ebe1-4afa-82f4-50fb5367e820":
                    GUI.openShop(player, ShopType.SELL, Category.NONE, 1);
                    event.setCancelled(true);
                    break;
                //萨曼莎 (萨曼莎的神秘商店)
                case "2dcca6a8-96ef-474e-9292-e46aa4afa420":
                    StoreGUI storeGUI = new StoreGUI(player);
                    storeGUI.openInventory();
                    event.setCancelled(true);
                    break;
                //史密斯博士 (精灵培育)
                case "a06ca45a-569f-4fed-af38-30812fad77a7":
                    PokemonInfoMenu pokemonInfoMenu = new PokemonInfoMenu(player);
                    pokemonInfoMenu.openInventory();
                    event.setCancelled(true);
                    break;
            }
        }

    }

    static HashMap<Location,CMIHologram> cmiHologramHashMap = new HashMap<>();

    //NPC全息字
    public static void NpcHologram(boolean b){
        for (Entity entity:Bukkit.getWorld("spawn").getEntities()) {
            Location location = entity.getLocation();
            location.setY(location.getY()+2.5);
            CMIHologram holo;
            switch (entity.getUniqueId().toString()){
                //花丝小姐 (商店)
                case "e7c30e6a-34be-4ac5-be2e-20528e29a16f":
                    holo = new CMIHologram("花丝小姐", location);
                    holo.setLines(Arrays.asList("&d花丝小姐","&7&o有什么可以为您服务的吗?"));
                    if (b){
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        cmiHologramHashMap.put(location,holo);
                    }else {
                        CMI.getInstance().getHologramManager().removeHolo(cmiHologramHashMap.get(location));
                        cmiHologramHashMap.remove(location);
                    }
                    holo.update();
                    break;
                //玛丽亚 (任务)
                case "f632b0aa-0064-40e9-a8be-27b25df1a68b":
                    holo = new CMIHologram("玛丽亚", location);
                    holo.setLines(Arrays.asList("&d玛丽亚","&7&o今天又是元气满满的一天!"));
                    if (b){
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        cmiHologramHashMap.put(location,holo);
                    }else {
                        CMI.getInstance().getHologramManager().removeHolo(cmiHologramHashMap.get(location));
                        cmiHologramHashMap.remove(location);
                    }
                    holo.update();
                    break;
                //蒂莉 (签到)
                case "f6a4f7fd-db7f-4276-a63c-f01f30a78f9e":
                    holo = new CMIHologram("蒂莉", location);
                    holo.setLines(Arrays.asList("&d蒂莉","&7&o又到了每日的打卡时间!"));
                    if (b){
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        cmiHologramHashMap.put(location,holo);
                    }else {
                        CMI.getInstance().getHologramManager().removeHolo(cmiHologramHashMap.get(location));
                        cmiHologramHashMap.remove(location);
                    }
                    holo.update();
                    break;
                //土豆先生 (市场)
                case "c6d366e4-ebe1-4afa-82f4-50fb5367e820":
                    holo = new CMIHologram("土豆先生", location);
                    holo.setLines(Arrays.asList("&6土豆先生","&7&o你有东西要出售吗?我可以帮你!"));
                    if (b){
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        cmiHologramHashMap.put(location,holo);
                    }else {
                        CMI.getInstance().getHologramManager().removeHolo(cmiHologramHashMap.get(location));
                        cmiHologramHashMap.remove(location);
                    }
                    holo.update();
                    break;
                //萨曼莎 (萨曼莎的神秘商店)
                case "2dcca6a8-96ef-474e-9292-e46aa4afa420":
                    holo = new CMIHologram("萨曼莎", location);
                    holo.setLines(Arrays.asList("&c萨曼莎的神秘商店","&7&o每周都有好东西,记得来看看!"));
                    if (b){
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        cmiHologramHashMap.put(location,holo);
                    }else {
                        CMI.getInstance().getHologramManager().removeHolo(cmiHologramHashMap.get(location));
                        cmiHologramHashMap.remove(location);
                    }
                    holo.update();
                    break;
                //史密斯博士 (精灵培育)
                case "a06ca45a-569f-4fed-af38-30812fad77a7":
                    holo = new CMIHologram("史密斯博士", location);
                    holo.setLines(Arrays.asList("&f史密斯博士","&7&o我是最强的宝可梦医生!"));
                    if (b){
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        cmiHologramHashMap.put(location,holo);
                    }else {
                        CMI.getInstance().getHologramManager().removeHolo(cmiHologramHashMap.get(location));
                        cmiHologramHashMap.remove(location);
                    }
                    holo.update();
                    break;
            }
        }
    }
}
