package red.kalos.core.manager.optional;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import red.kalos.core.configFile.Data;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.PokemonAPI;
import red.kalos.core.util.api.CustomItem;
import red.kalos.core.util.api.PokemonPhotoAPI;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OptionalGUI extends InventoryGUI {
    public OptionalGUI(Player player, String Type) {
        super(ColorParser.parse("&0" + Data.SERVER_NAME + " / 请选择宝可梦"), player, 6);
        String[] poke = new String[0];

        if (Type.equals("MaxLegend")){
            poke = new String[]{
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
        }

        int index = 0;
        for (String s:poke) {
            Pokemon pokemon = PokemonAPI.SpawnPokemon(s);
            this.setButton(index,new Button(PokemonPhotoAPI.getPhotoItemSelect(pokemon), type -> {
                if (CustomItem.useEncryptionItem(player,player.getItemInHand())!=null){
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
