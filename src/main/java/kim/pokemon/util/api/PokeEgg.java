package kim.pokemon.util.api;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import kim.pokemon.util.Base64Util;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import java.io.*;

public class PokeEgg {
    private final String uuid;
    private Pokemon pokemon;

    public PokeEgg(File f) {
        this.uuid = f.getName().replace(".pke", "");
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(f));
            NBTTagCompound nbt = CompressedStreamTools.func_74794_a(dis);
            this.pokemon = Pixelmon.pokemonFactory.create(nbt);
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PokeEgg(File f, Pokemon pokemon) {
        this.uuid = f.getName().replace(".pke", "");
        write(f, pokemon);
    }


    public PokeEgg(String uuid, String nbt) {
        this.uuid = uuid;

        try {
            NBTTagCompound nbtTagCompound = JsonToNBT.func_180713_a(Base64Util.decode(nbt));
            this.pokemon = Pixelmon.pokemonFactory.create(nbtTagCompound);
        } catch (NBTException ignored) {
            ignored.printStackTrace();
        }
    }

    public void write(File f, Pokemon pokemon) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
            NBTTagCompound nbt = new NBTTagCompound();
            pokemon.writeToNBT(nbt);
            CompressedStreamTools.func_74800_a(nbt, dos);
            dos.flush();
            dos.close();
            this.pokemon = pokemon;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUUID() {
        return this.uuid;
    }


    public Pokemon getPokemon() {
        return this.pokemon;
    }
}