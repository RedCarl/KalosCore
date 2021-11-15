package kim.pokemon.kim;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.DropEvent;
import com.pixelmonmod.pixelmon.api.events.storage.ChangeStorageEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PCStorage;
import com.pixelmonmod.pixelmon.entities.npcs.registry.RaidSpawningRegistry;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.forms.IEnumForm;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import kim.pokemon.Main;
import kim.pokemon.database.PokemonBanDataSQLReader;
import kim.pokemon.util.ColorParser;
import net.minecraft.util.Tuple;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public class PokemonBan implements Listener {
    //极具巢穴黑名单
    public static String[] RaidBlackList = new String[]{"MissingNo", "Ditto", "Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle", "Blastoise", "Chikorita", "Bayleef", "Meganium", "Cyndaquil", "Quilava", "Typhlosion", "Totodile", "Croconaw", "Feraligat", "Treecko", "Grovyle", "Sceptile", "Torchic", "Combusken", "Blaziken", "Mudkip", "Marshtomp", "Swampert", "Turtwig", "Grotle", "Torterra", "Chimchar", "Monferno", "Infernape", "Piplup", "Prinplup", "Empoleon", "Snivy", "Servine", "Serperior", "Tepig", "Pignite", "Emboar", "Oshawott", "Dewott", "Samurott", "Chespin", "Quilladin", "Chesnaught", "Fennekin", "Braixen", "Delphox", "Froakie", "Frogadier", "Greninja", "Rowlet", "Dartrix", "Decidueye", "Litten", "Torracat", "Incineroar", "Popplio", "Brionne", "Primarina", "Grookey", "Thwackey", "Rillaboom", "Scorbunny", "Raboot", "Cinderace", "Sobble", "Drizzile", "Inteleon"};
    public static String[] CraftBlackList = new String[]{"POKELUCKY_POKE_LUCKY","PIXELMON_MECHANICAL_ANVIL","PIXELMON_RANCH"};
    

    public static void rua(){
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), PokemonBanDataSQLReader::refreshBan, 0);

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
    public void onPixelmonSendOut(ForgeEvent e) {
        if (e.getForgeEvent() instanceof ChangeStorageEvent) {
            ChangeStorageEvent event = (ChangeStorageEvent)e.getForgeEvent();
            Player player = Bukkit.getPlayer(event.pokemon.getOwnerPlayerUUID());
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> this.onPlayerPartyCheck(player), 1L);
        }
    }

    @EventHandler
    public void onBattleGetSpoil(ForgeEvent e) {
        if (e.getForgeEvent() instanceof DropEvent) {
            DropEvent event = (DropEvent)e.getForgeEvent();
            (new ArrayList<>(event.getDrops())).forEach((item) -> {
                ItemStack is = CraftItemStack.asBukkitCopy(item.itemStack);
                Material material = is.getType();
                String materialName = material.name().toUpperCase();
                if (PokemonBanDataSQLReader.getBanDrops().contains(materialName)) {
                    event.removeDrop(item);
                }
            });
        }
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        onItemCheck(event.getPlayer(),event.getPlayer().getInventory().getItem(event.getNewSlot()));
        onItemCheck(event.getPlayer(),event.getPlayer().getInventory().getItem(event.getPreviousSlot()));
    }

    @EventHandler
    public void onPlayerItemHel(PlayerPickupItemEvent event) {
        this.onItemCheck(event.getPlayer(),event.getItem().getItemStack());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.onPlayerPartyCheck(event.getPlayer());
        this.onPlayerPCCheck(event.getPlayer());
        this.onPlayerInventoryCheck(event.getPlayer());
        this.onPlayerEnderChestCheck(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.onPlayerPartyCheck(event.getPlayer());
        this.onPlayerPCCheck(event.getPlayer());
        this.onPlayerInventoryCheck(event.getPlayer());
        this.onPlayerEnderChestCheck(event.getPlayer());
    }

    @EventHandler
    public void onChest(InventoryOpenEvent event){
        if (!event.getInventory().getTitle().contains("Kim")){
            this.onInventoryCheck(event.getInventory());
        }
    }

    public void onPlayerPartyCheck(Player player) {
        PlayerPartyStorage pps = Pixelmon.storageManager.getParty(player.getUniqueId());

        for (Pokemon o : new ArrayList<>(pps.getTeam())) {
            if (o != null && PokemonBanDataSQLReader.getBanPokemons().contains(o.getLocalizedName())) {
                pps.set(o.getPosition(), null);
            }
        }
    }

    public void onPlayerPCCheck(Player player) {
        PCStorage pcStorage = Pixelmon.storageManager.getPCForPlayer(player.getUniqueId());
        Pokemon[] var3 = pcStorage.getAll();
        for (Pokemon p : var3) {
            if (p != null && PokemonBanDataSQLReader.getBanPokemons().contains(p.getLocalizedName())) {
                pcStorage.set(p.getPosition(), null);
            }
        }
    }

    public void onPlayerInventoryCheck(Player player) {
        Inventory inventory = player.getInventory();
        ItemStack[] var3 = inventory.getContents();
        for (ItemStack i : var3) {
            if (i != null && PokemonBanDataSQLReader.getBanDrops().contains(i.getType().name())) {
                player.getInventory().removeItem(i);
            }
        }
    }

    public void onPlayerEnderChestCheck(Player player) {
        Inventory inventory = player.getEnderChest();
        ItemStack[] var3 = inventory.getContents();
        for (ItemStack i : var3) {
            if (i != null && PokemonBanDataSQLReader.getBanDrops().contains(i.getType().name())) {
                player.getEnderChest().removeItem(i);
            }
        }
    }

    public void onItemCheck(Player player,ItemStack itemStack){
        if (itemStack != null && PokemonBanDataSQLReader.getBanDrops().contains(itemStack.getType().name())) {
            player.getInventory().removeItem(itemStack);
            this.onPlayerInventoryCheck(player);
            this.onPlayerEnderChestCheck(player);
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该物品已经被服务器禁止使用。"));
        }
    }

    public void onInventoryCheck(Inventory inventory){
        for (ItemStack i:inventory) {
            if (i != null && PokemonBanDataSQLReader.getBanDrops().contains(i.getType().name())) {
                inventory.removeItem(i);
            }
        }
    }
}
