package kim.pokemon.util.api;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import de.tr7zw.nbtapi.NBTItem;
import kim.pokemon.Main;
import kim.pokemon.kimexpand.pokemonEgg.PokeEgg;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.text.DecimalFormat;
import java.util.UUID;

public class PokemonPhotoAPI {

    public static ItemStack getPokeEggItem(Pokemon pokemon, boolean remove, int slot, PlayerPartyStorage pps) {
        ItemStack item = CraftItemStack.asBukkitCopy(ItemPixelmonSprite.getPhoto(pokemon));
        String localizedName = pokemon.getSpecies().getLocalizedName();
        ItemStack lores = getPhotoItem(pokemon,pps,slot);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lores.getItemMeta().getLore());
        itemMeta.setDisplayName("§e" + localizedName);
                item.setItemMeta(itemMeta);
        if (remove) {
            pps.retrieveAll();
            pps.set(slot, null);
        }
        item.setItemMeta(itemMeta);
        UUID uuid = UUID.randomUUID();
        File f = new File(Main.getInstance().getDataFolder() + "/PokeEggs/", uuid + ".pke");
        PokeEgg pe = new PokeEgg(f, pokemon);
        net.minecraft.item.ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsitem.func_77942_o() ? nmsitem.func_77978_p() : new NBTTagCompound();
        assert compound != null;
        compound.func_74782_a("PokeEggUUID", (NBTBase)new NBTTagString(uuid.toString()));
        nmsitem.func_77982_d(compound);
        item = CraftItemStack.asBukkitCopy(nmsitem);
        return item;
    }


    public static ItemStack getPhotoItem(Pokemon pokemon,PlayerPartyStorage playerPartyStorage,int i){
        DecimalFormat df = new DecimalFormat("#0.##");
        int ivSum = pokemon.getIVs().getStat(StatsType.HP) + pokemon.getIVs().getStat(StatsType.Attack) + pokemon.getIVs().getStat(StatsType.Defence) +
                pokemon.getIVs().getStat(StatsType.SpecialAttack) + pokemon.getIVs().getStat(StatsType.SpecialDefence) + pokemon.getIVs().getStat(StatsType.Speed);
        int evSum = pokemon.getEVs().getStat(StatsType.HP) + pokemon.getEVs().getStat(StatsType.Attack) + pokemon.getEVs().getStat(StatsType.Defence) +
                pokemon.getEVs().getStat(StatsType.SpecialAttack) + pokemon.getEVs().getStat(StatsType.SpecialDefence) + pokemon.getEVs().getStat(StatsType.Speed);
        String totalEVs = df.format((int) (evSum / 510.0 * 100.0)) + "%";
        String totalIVs = df.format((int) (ivSum / 186.0 * 100.0)) + "%";

        ItemStack PokemonInfo = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                ColorParser.parse(PokemonAPI.getPokemonName(pokemon)+" &7(点击培养宝可梦)"),
                ColorParser.parse("&7等级: &b"+pokemon.getLevel()),
                ColorParser.parse("&7闪光: &f"+PokemonAPI.isShiny(pokemon)),
                ColorParser.parse("&7特性: &f"+pokemon.getAbility().getLocalizedName()),
                ColorParser.parse("&7性格: &f"+pokemon.getNature().getLocalizedName()),
                ColorParser.parse("&7体型: &f"+pokemon.getGrowth().getLocalizedName()),
                ColorParser.parse("&7性别: &f"+pokemon.getGender().getLocalizedName()),
                ColorParser.parse("&7昵称: &f"+(pokemon.getNickname()==null ? "&c暂无昵称" : pokemon.getNickname())),
                ColorParser.parse("&r"),
                ColorParser.parse("&7个体值: &7(&a"+totalIVs+"&7)"),
                ColorParser.parse("&c"+pokemon.getIVs().getStat(StatsType.HP)+" 血量 &6"+pokemon.getIVs().getStat(StatsType.Attack)+" 攻击 &e"+pokemon.getIVs().getStat(StatsType.Defence)+" 防御"),
                ColorParser.parse("&9"+pokemon.getIVs().getStat(StatsType.SpecialAttack)+" 特攻 &a"+pokemon.getIVs().getStat(StatsType.SpecialDefence)+" 特防 &d"+pokemon.getIVs().getStat(StatsType.Speed)+" 速度"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7努力值: &7(&a"+totalEVs+"&7)"),
                ColorParser.parse("&c"+pokemon.getEVs().getStat(StatsType.HP)+" 血量 &6"+pokemon.getEVs().getStat(StatsType.Attack)+" 攻击 &e"+pokemon.getEVs().getStat(StatsType.Defence)+" 防御"),
                ColorParser.parse("&9"+pokemon.getEVs().getStat(StatsType.SpecialAttack)+" 特攻 &a"+pokemon.getEVs().getStat(StatsType.SpecialDefence)+" 特防 &d"+pokemon.getEVs().getStat(StatsType.Speed)+" 速度"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7技能:"),
                ColorParser.parse("&b"+PokemonAPI.getMove(pokemon.getMoveset().get(0))+" &7- &b"+PokemonAPI.getMove(pokemon.getMoveset().get(1))+" &7- &b"+PokemonAPI.getMove(pokemon.getMoveset().get(2))+" &7- &b"+PokemonAPI.getMove(pokemon.getMoveset().get(3)))
        );


        NBTItem nbtItem = new NBTItem(PokemonInfo);
        nbtItem.setShort("ndex", (short) playerPartyStorage.get(i).getSpecies().getNationalPokedexInteger());
        if (playerPartyStorage.get(i).isShiny()){
            nbtItem.setByte("Shiny", (byte) 1);
        }else {
            nbtItem.setByte("Shiny", (byte) 0);
        }

        nbtItem.setByte("form", playerPartyStorage.get(i).getFormEnum().getForm());
        return nbtItem.getItem();
    }



    public static void addPokemon(Player player,ItemStack item){
        net.minecraft.item.ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        if(!nmsitem.func_77942_o()){  //如果没有ItemTag
            return;
        }
        NBTTagCompound compound = nmsitem.func_77978_p();
        assert compound != null;
        if (compound.func_74764_b("PokeEggUUID")) {
            String uuid = compound.func_74779_i("PokeEggUUID");
            File f = new File(Main.getInstance().getDataFolder() + "/PokeEggs/", uuid + ".pke");
            PokeEgg pokeEgg = new PokeEgg(f);
            PlayerPartyStorage pps = Pixelmon.storageManager.getParty(player.getUniqueId());
            pps.add(pokeEgg.getPokemon());
            f.delete();
        }
    }

}
