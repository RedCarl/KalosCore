package red.kalos.core.manager.props.ivs;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.kalos.core.configFile.Data;
import red.kalos.core.manager.item.CustomItem;
import red.kalos.core.manager.props.SelectPokemon;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.gui.Button;
import red.kalos.core.util.gui.InventoryGUI;
import red.kalos.core.util.gui.inventory.ItemFactoryAPI;

/**
 * @Author: carl0
 * @DATE: 2022/8/12 22:17
 */
public class IVs extends InventoryGUI {
    public IVs(Player player, Pokemon pokemon) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 选择个体"), player, 1);

        //个体值修改
        ItemStack HP = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HP_UP"), ColorParser.parse("&a生命 &7("+pokemon.getIVs().getStat(StatsType.HP)+")"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7点击将更改此个体值为 &a31 &7请谨慎操作。")
        );
        if (pokemon.getIVs().getStat(StatsType.HP)!=0){
            HP.setAmount(pokemon.getIVs().getStat(StatsType.HP));
        }
        Button HPButton = new Button(HP, type -> {
            if (CustomItem.useEncryptionItem(player,player.getItemInHand())==null){
                return;
            }
            pokemon.getIVs().setStat(StatsType.HP,31);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            player.closeInventory();
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功将 "+pokemon.getLocalizedName()+" &7的 &a"+StatsType.HP.getLocalizedName()+" &7更改为 &a31 &7注意查看。"));
        });
        this.setButton(0, HPButton);


        ItemStack Attack = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PROTEIN"), ColorParser.parse("&c攻击 &7("+pokemon.getIVs().getStat(StatsType.Attack)+")"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7点击将更改此个体值为 &a31 &7请谨慎操作。")
        );
        if (pokemon.getIVs().getStat(StatsType.Attack)!=0){
            Attack.setAmount(pokemon.getIVs().getStat(StatsType.Attack));
        }
        Button AttackButton = new Button(Attack, type -> {
            if (CustomItem.useEncryptionItem(player,player.getItemInHand())==null){
                return;
            }
            pokemon.getIVs().setStat(StatsType.Attack,31);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            player.closeInventory();
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功将 "+pokemon.getLocalizedName()+" &7的 &a"+StatsType.Attack.getLocalizedName()+" &7更改为 &a31 &7注意查看。"));
        });
        this.setButton(1, AttackButton);


        ItemStack Defence = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_IRON"), ColorParser.parse("&c防御 &7("+pokemon.getIVs().getStat(StatsType.Defence)+")"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7点击将更改此个体值为 &a31 &7请谨慎操作。")
        );
        if (pokemon.getIVs().getStat(StatsType.Defence)!=0){
            Defence.setAmount(pokemon.getIVs().getStat(StatsType.Defence));
        }
        Button DefenceButton = new Button(Defence, type -> {
            if (CustomItem.useEncryptionItem(player,player.getItemInHand())==null){
                return;
            }
            pokemon.getIVs().setStat(StatsType.Defence,31);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            player.closeInventory();
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功将 "+pokemon.getLocalizedName()+" &7的 &a"+StatsType.Defence.getLocalizedName()+" &7更改为 &a31 &7注意查看。"));
        });
        this.setButton(2, DefenceButton);


        ItemStack Speed = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_CARBOS"), ColorParser.parse("&b速度 &7("+pokemon.getIVs().getStat(StatsType.Speed)+")"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7点击将更改此个体值为 &a31 &7请谨慎操作。")
        );
        if (pokemon.getIVs().getStat(StatsType.Speed)!=0){
            Speed.setAmount(pokemon.getIVs().getStat(StatsType.Speed));
        }
        Button SpeedButton = new Button(Speed, type -> {
            if (CustomItem.useEncryptionItem(player,player.getItemInHand())==null){
                return;
            }
            pokemon.getIVs().setStat(StatsType.Speed,31);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            player.closeInventory();
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功将 "+pokemon.getLocalizedName()+" &7的 &a"+StatsType.Speed.getLocalizedName()+" &7更改为 &a31 &7注意查看。"));
        });
        this.setButton(3, SpeedButton);


        ItemStack SpecialAttack = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_CALCIUM"), ColorParser.parse("&6特攻 &7("+pokemon.getIVs().getStat(StatsType.SpecialAttack)+")"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7点击将更改此个体值为 &a31 &7请谨慎操作。")
        );
        if (pokemon.getIVs().getStat(StatsType.SpecialAttack)!=0){
            SpecialAttack.setAmount(pokemon.getIVs().getStat(StatsType.SpecialAttack));
        }
        Button SpecialAttackButton = new Button(SpecialAttack, type -> {
            if (CustomItem.useEncryptionItem(player,player.getItemInHand())==null){
                return;
            }
            pokemon.getIVs().setStat(StatsType.SpecialAttack,31);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            player.closeInventory();
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功将 "+pokemon.getLocalizedName()+" &7的 &a"+StatsType.SpecialAttack.getLocalizedName()+" &7更改为 &a31 &7注意查看。"));
        });
        this.setButton(4, SpecialAttackButton);


        ItemStack SpecialDefence = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ZINC"), ColorParser.parse("&c特防 &7("+pokemon.getIVs().getStat(StatsType.SpecialDefence)+")"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7点击将更改此个体值为 &a31 &7请谨慎操作。")
        );
        if (pokemon.getIVs().getStat(StatsType.SpecialDefence)!=0){
            SpecialDefence.setAmount(pokemon.getIVs().getStat(StatsType.SpecialDefence));
        }
        Button SpecialDefenceButton = new Button(SpecialDefence, type -> {
            if (CustomItem.useEncryptionItem(player,player.getItemInHand())==null){
                return;
            }
            pokemon.getIVs().setStat(StatsType.SpecialDefence,31);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            player.closeInventory();
            player.sendMessage(ColorParser.parse("&8[&a&l!&8] &7您成功将 "+pokemon.getLocalizedName()+" &7的 &a"+StatsType.SpecialDefence.getLocalizedName()+" &7更改为 &a31 &7注意查看。"));
        });
        this.setButton(5, SpecialDefenceButton);


        //取消
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER,ColorParser.parse("&c返回"));
        Button CloseButton = new Button(Close, type -> {
            new SelectPokemon(player,"ivs").openInventory();
        });
        this.setButton(8, CloseButton);
    }
}
