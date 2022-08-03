package red.kalos.core.entity;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import red.kalos.core.util.Base64Util;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public class PokeEgg {
    private Pokemon pokemon;

    public PokeEgg(String nbt) {
        try {
            NBTTagCompound nbtTagCompound = JsonToNBT.func_180713_a(Base64Util.decode(nbt));
            this.pokemon = Pixelmon.pokemonFactory.create(nbtTagCompound);
        } catch (NBTException ignored) {
        }
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }
}
