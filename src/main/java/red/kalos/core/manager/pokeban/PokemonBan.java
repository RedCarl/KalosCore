package red.kalos.core.manager.pokeban;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.api.events.DropEvent;
import com.pixelmonmod.pixelmon.api.events.raids.RaidDropsEvent;
import com.pixelmonmod.pixelmon.entities.npcs.registry.RaidSpawningRegistry;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.forms.IEnumForm;
import red.kalos.core.database.BanItemManager;
import red.kalos.core.configFile.Data;
import red.kalos.core.util.ColorParser;
import net.minecraft.util.Tuple;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PokemonBan implements Listener {
    //极具巢穴黑名单
    public static String[] RaidBlackList = new String[]{"MissingNo", "Ditto", "Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle", "Blastoise", "Chikorita", "Bayleef", "Meganium", "Cyndaquil", "Quilava", "Typhlosion", "Totodile", "Croconaw", "Feraligat", "Treecko", "Grovyle", "Sceptile", "Torchic", "Combusken", "Blaziken", "Mudkip", "Marshtomp", "Swampert", "Turtwig", "Grotle", "Torterra", "Chimchar", "Monferno", "Infernape", "Piplup", "Prinplup", "Empoleon", "Snivy", "Servine", "Serperior", "Tepig", "Pignite", "Emboar", "Oshawott", "Dewott", "Samurott", "Chespin", "Quilladin", "Chesnaught", "Fennekin", "Braixen", "Delphox", "Froakie", "Frogadier", "Greninja", "Rowlet", "Dartrix", "Decidueye", "Litten", "Torracat", "Incineroar", "Popplio", "Brionne", "Primarina", "Grookey", "Thwackey", "Rillaboom", "Scorbunny", "Raboot", "Cinderace", "Sobble", "Drizzile", "Inteleon"};


    public static void rua(){

        //删除极具巢穴内特定的宝可梦
        RaidSpawningRegistry.map.values().forEach((map) -> map.values().forEach((list) -> {
            Iterator<Tuple<EnumSpecies, Optional<IEnumForm>>> iter = list.iterator();
            while(iter.hasNext()) {
                Tuple<EnumSpecies, Optional<IEnumForm>> tuple = iter.next();
                EnumSpecies species = tuple.func_76341_a();
                if (Arrays.asList(RaidBlackList).contains(species.name())) {
                    iter.remove();
                }
            }
        }));

    }

    @EventHandler
    public void onBattleGetSpoil(ForgeEvent e) {
        if (e.getForgeEvent() instanceof DropEvent) {
            DropEvent event = (DropEvent)e.getForgeEvent();
            (new ArrayList<>(event.getDrops())).forEach((item) -> {
                ItemStack is = CraftItemStack.asBukkitCopy(item.itemStack);
                Material material = is.getType();
                String materialName = material.name().toUpperCase();
                if (BanItemManager.getBanDrops().contains(materialName)) {
                    event.removeDrop(item);
                }
            });
        }
        if (e.getForgeEvent() instanceof RaidDropsEvent) {
            RaidDropsEvent event = (RaidDropsEvent)e.getForgeEvent();
            ArrayList<net.minecraft.item.ItemStack> arrayList = event.getDrops();
            ArrayList<net.minecraft.item.ItemStack> itemStacks = event.getDrops();

            try {
                for (net.minecraft.item.ItemStack item: arrayList) {
                    ItemStack is = CraftItemStack.asBukkitCopy(item);
                    Material material = is.getType();
                    String materialName = material.name().toUpperCase();
                    if (BanItemManager.getBanDrops().contains(materialName)) {
                        itemStacks.remove(item);
                    }
                }
            }catch (ConcurrentModificationException ignored){}
            event.setDrops(itemStacks);
        }
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        if (event.getPlayer().hasPermission("group.admin")){return;}
        onItemCheck(event.getPlayer(),event.getPlayer().getInventory().getItem(event.getNewSlot()));
        onItemCheck(event.getPlayer(),event.getPlayer().getInventory().getItem(event.getPreviousSlot()));
    }

    @EventHandler
    public void onPlayerItemHel(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            ItemStack itemStack = event.getItem().getItemStack();
            if (player.hasPermission("group.admin")){return;}
            if (itemStack != null && BanItemManager.getBanDrops().contains(itemStack.getType().name())) {
                event.setCancelled(true);
                event.getItem().remove();
                showInfo(player);
            }
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission("group.admin")){return;}
        this.onPlayerInventoryCheck(event.getPlayer());
        this.onPlayerEnderChestCheck(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().hasPermission("group.admin")){return;}
        this.onPlayerInventoryCheck(event.getPlayer());
        this.onPlayerEnderChestCheck(event.getPlayer());
    }

    @EventHandler
    public void onChest(InventoryOpenEvent event){
        if (event.getPlayer().hasPermission("group.admin")){return;}
        if (!event.getInventory().getTitle().contains(Data.SERVER_NAME)){
            this.onInventoryCheck(event.getInventory());
        }
    }

    @EventHandler
    public void CraftItemEvent(CraftItemEvent event){
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack != null && BanItemManager.getBanDrops().contains(itemStack.getType().name())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if (event.getPlayer().hasPermission("group.admin")){return;}
        if (event.getItem()!=null&&(event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK)||(event.getAction().equals(Action.LEFT_CLICK_AIR)||event.getAction().equals(Action.LEFT_CLICK_BLOCK)))){
            if (BanItemManager.getBanDrops().contains(event.getItem().getType().name())){
                event.setCancelled(true);
                event.getPlayer().getInventory().removeItem(event.getItem());
                showInfo(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event){
        if (event.getPlayer().hasPermission("group.admin")){return;}
        onPlayerInventoryCheck(event.getPlayer());
    }

    public void onPlayerInventoryCheck(Player player) {
        if (player.hasPermission("group.admin")){return;}
        Inventory inventory = player.getInventory();
        ItemStack[] var3 = inventory.getContents();
        for (ItemStack i : var3) {
            if (i != null && BanItemManager.getBanDrops().contains(i.getType().name())) {
                player.getInventory().removeItem(i);
                showInfo(player);
            }
        }
    }

    public void onPlayerEnderChestCheck(Player player) {
        if (player.hasPermission("group.admin")){return;}
        Inventory inventory = player.getEnderChest();
        ItemStack[] var3 = inventory.getContents();
        for (ItemStack i : var3) {
            if (i != null && BanItemManager.getBanDrops().contains(i.getType().name())) {
                player.getEnderChest().removeItem(i);
                showInfo(player);
            }
        }
    }

    public void onItemCheck(Player player,ItemStack itemStack){
        if (player.hasPermission("group.admin")){return;}
        if (itemStack != null && BanItemManager.getBanDrops().contains(itemStack.getType().name())) {
            player.getInventory().removeItem(itemStack);
            this.onPlayerInventoryCheck(player);
            this.onPlayerEnderChestCheck(player);
            showInfo(player);
        }
    }

    public void onInventoryCheck(Inventory inventory){
        for (ItemStack i:inventory) {
            if (i != null && BanItemManager.getBanDrops().contains(i.getType().name())) {
                inventory.removeItem(i);
            }
        }
    }

    public void showInfo(Player player){
        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该物品已经被服务器禁止使用。"));
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
    }
}
