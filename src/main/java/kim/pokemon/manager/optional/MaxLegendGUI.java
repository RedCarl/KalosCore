package kim.pokemon.manager.optional;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import kim.pokemon.configFile.Data;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.api.CustomItem;
import kim.pokemon.util.api.PokemonPhotoAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MaxLegendGUI extends InventoryGUI {
    public MaxLegendGUI(Player player) {
        super(ColorParser.parse("&0" + Data.SERVER_NAME + " / 传奇宝可梦 (自选)"), player, 6);
        String[] legend = {
                "Mewtwo",
                "Ho-Oh",
                "Lugia",
                "Kyogre",
                "Groudon",
                "Rayquaza",
                "Dialga",
                "Palkia",
                "Giratina",
                "Regigigas",
                "Reshiram",
                "Zekrom",
                "Xerneas",
                "Yveltal",
                "Zygarde",
                "Hoopa",
                "Lunala",
                "Solgaleo",
                "Zacian",
                "Zamazenta",
                "Eternatus"
        };

        int index = 0;
        for (String s:legend) {
            Pokemon pokemon = PokemonAPI.SpawnPokemon(s);
            this.setButton(index,new Button(PokemonPhotoAPI.getPhotoItemSelect(pokemon), type -> {
                if (CustomItem.useEncryptionItem(player,player.getItemInHand())){
                    PokemonAPI.GivePokemon(player,false,2,0,false,pokemon);
                }
                player.closeInventory();
            }));
            index++;
        }


        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }


        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c取消"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o取消本次操作，不会消耗物品."));
        Button CloseButton = new Button(Close, type -> {
            player.closeInventory();
        });
        this.setButton(53, CloseButton);
    }
}
