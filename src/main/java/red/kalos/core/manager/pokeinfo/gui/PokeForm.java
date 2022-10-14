package red.kalos.core.manager.pokeinfo.gui;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.enums.forms.IEnumForm;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.Main;
import red.kalos.core.configFile.Data;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PokeForm extends InventoryGUI {

    public PokeForm(Player player, Pokemon pokemon) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 宝可梦形态 &7("+pokemon.getLocalizedName()+")"), player, 6);

        //宝可梦形态更改
        File file = new File(Main.getInstance().getDataFolder(), "CustomSkin/data.yml");
        //宝可梦原版皮肤
        List<IEnumForm> forms = pokemon.getSpecies().getPossibleForms(false);
        int a=0;
        for (IEnumForm form : forms) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            int leftTime = config.getInt("PokeAward." + player.getName() + "." + pokemon.getSpecies().getLocalizedName() + "." + form.getLocalizedName());
            if (leftTime > 0) {
                ItemStack PokeAward = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                        ColorParser.parse("&f" + form.getLocalizedName()),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o将您的该宝可梦皮肤更改为此皮肤!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7信 息:"),
                        ColorParser.parse("&r      &7(左键 更换) &c剩余 &c"+leftTime+" &7个"),
                        ColorParser.parse("&r")
                );

                NBTItem nbtItem = new NBTItem(PokeAward);
                nbtItem.setShort("ndex", (short) pokemon.getSpecies().getNationalPokedexInteger());
                /*nbtItem.setByte("form", poke.getFormEnum().getForm());*/

                Button PokeAwardButton = new Button(nbtItem.getItem(), (type) -> {
                    if (type.isLeftClick()) {
                        pokemon.setForm(form.getForm());
                        config.set("PokeAward." + player.getName() + "." + pokemon.getSpecies().getLocalizedName() + "." + form.getLocalizedName(), leftTime - 1);
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                        player.closeInventory();
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                setButton(27 + a, PokeAwardButton);
                a++;
            }
        }




        //宝可梦自定义皮肤
        File CustomFile = new File(Main.getInstance().getDataFolder(), "CustomSkin/config.yml");
        FileConfiguration CustomConfig = YamlConfiguration.loadConfiguration(CustomFile);
        //玩家拥有的皮肤
        FileConfiguration PlayerConfig = YamlConfiguration.loadConfiguration(file);
        for (String s:PlayerConfig.getConfigurationSection("PokeAward."+player.getName()).getKeys(false)) {
            if (pokemon.getLocalizedName().equals(s)){
                for (String sa:PlayerConfig.getConfigurationSection("PokeAward."+player.getName()+"."+s).getKeys(false)) {
                    int amount = PlayerConfig.getInt("PokeAward." + player.getName() + "." + s + "." + sa);
                    if (PlayerConfig.getInt("PokeAward." + player.getName() + "." + s + "." + sa)>0){
                        if (Data.CUSTOM_SKIN.contains(sa+"·"+s)){
                            ItemStack PokeAward = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                                    ColorParser.parse("&6" + sa+"·"+s),
                                    ColorParser.parse("&r"),
                                    ColorParser.parse("&7&o将您的该宝可梦皮肤更改为此皮肤!"),
                                    ColorParser.parse("&r"),
                                    ColorParser.parse("&r  &e■ &7信 息:"),
                                    ColorParser.parse("&r      &7(左键 更换) &c剩余 &c"+amount+" &7个"),
                                    ColorParser.parse("&r")
                            );

                            NBTItem nbtItem = new NBTItem(PokeAward);
                            nbtItem.setShort("ndex", (short) pokemon.getSpecies().getNationalPokedexInteger());

                            /*nbtItem.setByte("form", poke.getFormEnum().getForm());*/

                            Button PokeAwardButton = new Button(nbtItem.getItem(), (type) -> {
                                if (type.isLeftClick()) {
                                    pokemon.setNickname(ColorParser.parse("&6"+sa+" - "+s));
                                    pokemon.setShiny(false);
                                    pokemon.setForm(0);
                                    pokemon.setCustomTexture(CustomConfig.getString("skins." + sa+"·"+s + "." + "texture"));
                                    PlayerConfig.set("PokeAward." + player.getName() + "." + s + "." + sa, amount - 1);
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                                    player.closeInventory();
                                    try {
                                        PlayerConfig.save(file);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            setButton(27 + a, PokeAwardButton);
                            a++;
                        }
                    }
                }
            }
        }
    }
}
