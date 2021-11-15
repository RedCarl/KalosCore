package kim.pokemon.ui.pokemoninfo;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import de.tr7zw.nbtapi.NBTItem;
import kim.pokemon.ui.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class PokemonInfoMenu extends InventoryGUI {
    public PokemonInfoMenu(Player player) {
        super(ColorParser.parse("&0Kim / 宝可梦信息"), player, 6);

        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(player.getUniqueId());

        for (int i = 0; i < playerPartyStorage.getAll().length; i++) {
            if (playerPartyStorage.get(i) != null){
                Pokemon pokemon = playerPartyStorage.get(i);

                //临时，后面写成实体类
                DecimalFormat df = new DecimalFormat("#0.##");
                int ivSum = pokemon.getIVs().getStat(StatsType.HP) + pokemon.getIVs().getStat(StatsType.Attack) + pokemon.getIVs().getStat(StatsType.Defence) +
                        pokemon.getIVs().getStat(StatsType.SpecialAttack) + pokemon.getIVs().getStat(StatsType.SpecialDefence) + pokemon.getIVs().getStat(StatsType.Speed);
                int evSum = pokemon.getEVs().getStat(StatsType.HP) + pokemon.getEVs().getStat(StatsType.Attack) + pokemon.getEVs().getStat(StatsType.Defence) +
                        pokemon.getEVs().getStat(StatsType.SpecialAttack) + pokemon.getEVs().getStat(StatsType.SpecialDefence) + pokemon.getEVs().getStat(StatsType.Speed);
                String totalEVs = df.format((int) (evSum / 510.0 * 100.0)) + "%";
                String totalIVs = df.format((int) (ivSum / 186.0 * 100.0)) + "%";

                ItemStack PokemonInfo = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PIXELMON_SPRITE"),
                        ColorParser.parse(PokemonAPI.getPokemonName(pokemon)),
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

                switch (i){
                    case 0:
                        this.setButton(11, new Button(nbtItem.getItem()));
                        break;
                    case 1:
                        this.setButton(13, new Button(nbtItem.getItem()));
                        break;
                    case 2:
                        this.setButton(15, new Button(nbtItem.getItem()));
                        break;
                    case 3:
                        this.setButton(20, new Button(nbtItem.getItem()));
                        break;
                    case 4:
                        this.setButton(22, new Button(nbtItem.getItem()));
                        break;
                    case 5:
                        this.setButton(24, new Button(nbtItem.getItem()));
                        break;
                }
            }
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
