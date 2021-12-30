package kim.pokemon.kimexpand.pokeinfo.gui;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import de.tr7zw.nbtapi.NBTItem;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.api.PokemonPhotoAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class PokemonInfoMenu extends InventoryGUI {
    public PokemonInfoMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 宝可梦信息"), player, 6);


        PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(player.getUniqueId());

        for (int i = 0; i < playerPartyStorage.getAll().length; i++) {
            if (playerPartyStorage.get(i) != null){
                Pokemon pokemon = playerPartyStorage.get(i);

                ItemStack PokemonPhoto =  PokemonPhotoAPI.getPhotoItem(pokemon,playerPartyStorage,i);

                Button pokemonButton = new Button(PokemonPhoto, type -> {
                    if (type.isLeftClick()){
                        PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,pokemon);
                        pokeInfoUpdate.openInventory();
                    }
                });
                
                switch (i){
                    case 0:
                        this.setButton(11, pokemonButton);
                        break;
                    case 1:
                        this.setButton(13, pokemonButton);
                        break;
                    case 2:
                        this.setButton(15, pokemonButton);
                        break;
                    case 3:
                        this.setButton(20, pokemonButton);
                        break;
                    case 4:
                        this.setButton(22, pokemonButton);
                        break;
                    case 5:
                        this.setButton(24, pokemonButton);
                        break;
                }
            }
        }

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }


        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回至主菜单."));
        Button CloseButton = new Button(Close, type -> {
            if (type.isLeftClick()) {
                MainMenu mainMenu = new MainMenu(player);
                mainMenu.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }
}
