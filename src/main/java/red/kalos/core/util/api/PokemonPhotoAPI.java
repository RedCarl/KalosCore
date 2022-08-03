package red.kalos.core.util.api;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import de.tr7zw.nbtapi.NBTItem;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.text.DecimalFormat;
import java.util.UUID;

public class PokemonPhotoAPI {


    /**
     * 把玩家某个位置的宝可梦制作成相片并把数据放进对应文件夹
     * @param pokemon 宝可梦
     * @param remove 读取数据后，是否删除玩家对应位置的宝可梦
     * @param slot 位置
     * @param pps 玩家宝可梦背包
     * @param path 储存该数据的path，比如PokeEggs
     * @return 返回制作完成的相片
     */
    public static ItemStack getPokeEggItem(Pokemon pokemon, boolean remove, int slot, PlayerPartyStorage pps,String path) {
        ItemStack item = CraftItemStack.asBukkitCopy(ItemPixelmonSprite.getPhoto(pokemon));
        ItemStack lores = getPhotoItem(pokemon,pps,slot);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lores.getItemMeta().getLore());
        itemMeta.setDisplayName(PokemonAPI.getPokemonName(pokemon));
                item.setItemMeta(itemMeta);
        if (remove) {
            pps.retrieveAll();
            pps.set(slot, null);
        }
        item.setItemMeta(itemMeta);
        UUID uuid = UUID.randomUUID();
        File f = new File(getFolder(path), uuid + ".pke");
        PokeEgg pe = new PokeEgg(f, pokemon);
        net.minecraft.item.ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsitem.func_77942_o() ? nmsitem.func_77978_p() : new NBTTagCompound();
        assert compound != null;
        compound.func_74782_a("PokeEggUUID", new NBTTagString(uuid.toString()));
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
                ColorParser.parse(PokemonAPI.getPokemonName(pokemon)+" &7(右键培养宝可梦属性)"),
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
        nbtItem.setShort("ndex", (short) pokemon.getSpecies().getNationalPokedexInteger());
        if (pokemon.isShiny()){
            nbtItem.setByte("Shiny", (byte) 1);
        }else {
            nbtItem.setByte("Shiny", (byte) 0);
        }

        nbtItem.setByte("form", pokemon.getFormEnum().getForm());
        return nbtItem.getItem();
    }

    public static ItemStack getPhotoItemSell(Pokemon pokemon){
        ItemStack PokemonInfo = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                ColorParser.parse(PokemonAPI.getPokemonName(pokemon)),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7售 价:"),
                ColorParser.parse("&r      &7(左键) &c79 &7"+ Data.SERVER_POINTS),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o本次活动仅限购买一件商品，请三思而后行！")
        );

        NBTItem nbtItem = new NBTItem(PokemonInfo);
        nbtItem.setShort("ndex", (short) pokemon.getSpecies().getNationalPokedexInteger());
        if (pokemon.isShiny()){
            nbtItem.setByte("Shiny", (byte) 1);
        }else {
            nbtItem.setByte("Shiny", (byte) 0);
        }

        nbtItem.setByte("form", pokemon.getFormEnum().getForm());
        return nbtItem.getItem();
    }

    public static ItemStack getPhotoItemSelect(Pokemon pokemon){
        ItemStack PokemonInfo = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                ColorParser.parse(PokemonAPI.getPokemonName(pokemon)),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o请谨慎选择，选择后将无法更改！")
        );

        NBTItem nbtItem = new NBTItem(PokemonInfo);
        nbtItem.setShort("ndex", (short) pokemon.getSpecies().getNationalPokedexInteger());
        if (pokemon.isShiny()){
            nbtItem.setByte("Shiny", (byte) 1);
        }else {
            nbtItem.setByte("Shiny", (byte) 0);
        }

        nbtItem.setByte("form", pokemon.getFormEnum().getForm());
        return nbtItem.getItem();
    }

    public static ItemStack getPhotoItem(Pokemon pokemon){
        ItemStack PokemonInfo = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"));

        NBTItem nbtItem = new NBTItem(PokemonInfo);
        nbtItem.setShort("ndex", (short) pokemon.getSpecies().getNationalPokedexInteger());
        if (pokemon.isShiny()){
            nbtItem.setByte("Shiny", (byte) 1);
        }else {
            nbtItem.setByte("Shiny", (byte) 0);
        }

        nbtItem.setByte("form", pokemon.getFormEnum().getForm());
        return nbtItem.getItem();
    }

    /**
     * 把某相片代表的宝可梦发送给玩家，并且清除该相片的所有数据
     * @param path: 储存该数据的path，比如PokeEggs
     */
    public static void addPokemon(Player player,ItemStack item,String path){
        net.minecraft.item.ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        if(!nmsitem.func_77942_o()){  //如果没有ItemTag
            return;
        }
        NBTTagCompound compound = nmsitem.func_77978_p();
        assert compound != null;
        if (compound.func_74764_b("PokeEggUUID")) {
            String uuid = compound.func_74779_i("PokeEggUUID");
            File f = new File(getFolder(path), uuid + ".pke");
            PokeEgg pokeEgg = new PokeEgg(f);
            PlayerPartyStorage pps = Pixelmon.storageManager.getParty(player.getUniqueId());
            pps.add(pokeEgg.getPokemon());
            f.delete();
        }
    }


    /**
     * 把某相片代表的宝可梦返回(注意：并不清除该宝可梦的数据)
     * @param path: 储存该数据的path，比如PokeEggs
     * @return 返回宝可梦
     */
    public static Pokemon getPokemon(ItemStack item,String path){
        net.minecraft.item.ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        if(!nmsitem.func_77942_o()){  //如果没有ItemTag
            return null;
        }
        NBTTagCompound compound = nmsitem.func_77978_p();
        assert compound != null;
        if (compound.func_74764_b("PokeEggUUID")) {
            String uuid = compound.func_74779_i("PokeEggUUID");
            File f = new File(getFolder(path), uuid + ".pke");
            PokeEgg pokeEgg = new PokeEgg(f);
            return pokeEgg.getPokemon();
        }
        return null;
    }

    /**
     * 删除相片代表的宝可梦数据
     * @param path: 储存该数据的path，比如PokeEggs
     */
    public static void deletePokemonFile(ItemStack item,String path){
        net.minecraft.item.ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        if(!nmsitem.func_77942_o()){  //如果没有ItemTag
            return;
        }
        NBTTagCompound compound = nmsitem.func_77978_p();
        assert compound != null;
        if (compound.func_74764_b("PokeEggUUID")) {
            String uuid = compound.func_74779_i("PokeEggUUID");
            File f = new File(getFolder(path), uuid + ".pke");
            f.delete();
        }
    }

    public static File getFolder(String path){
        File f = new File(Main.getInstance().getDataFolder() + "/"+path+"/");
        f.mkdirs();
        return f;
    }
}
