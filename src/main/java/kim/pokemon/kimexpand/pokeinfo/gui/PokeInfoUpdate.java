package kim.pokemon.kimexpand.pokeinfo.gui;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import com.pixelmonmod.pixelmon.enums.forms.IEnumForm;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import de.tr7zw.nbtapi.NBTItem;
import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.pokeinfo.entity.CustomEntity;
import kim.pokemon.kimexpand.pokeinfo.gui.grouth.GrowthSelectGUI;
import kim.pokemon.kimexpand.pokeinfo.gui.nature.NatureSelectGUI;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class PokeInfoUpdate extends InventoryGUI {
    public PokeInfoUpdate(Player player,Pokemon pokemon) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 宝可梦信息 &7("+pokemon.getLocalizedName()+")"), player, 6);

        PlayerPartyStorage playerPartyStorage = Pixelmon.storageManager.getParty(player.getUniqueId());
        for (Pokemon p:playerPartyStorage.getAll()) {
            if (p!=null&&p.getUUID().equals(pokemon.getUUID())){
                //个体值修改
                long IVsMoney = 3000;
                int IVsPoints = 18;
                ItemStack HP = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_HP_UP"), ColorParser.parse("&a生命 &7("+p.getIVs().getStat(StatsType.HP)+")"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 00~31) &c" + IVsMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 31~31) &c" + IVsPoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                if (p.getIVs().getStat(StatsType.HP)!=0){
                    HP.setAmount(p.getIVs().getStat(StatsType.HP));
                }
                Button HPButton = new Button(HP, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=IVsMoney){
                            Main.econ.withdrawPlayer(player, IVsMoney);
                            int value = new Random().nextInt(30)+1;
                            p.getIVs().setStat(StatsType.HP,value);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=IVsPoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(), IVsPoints);
                                int value = 31;
                                p.getIVs().setStat(StatsType.HP,value);
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                pokeInfoUpdate.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(10, HPButton);


                ItemStack Attack = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_PROTEIN"), ColorParser.parse("&c攻击 &7("+p.getIVs().getStat(StatsType.Attack)+")"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 00~31) &c" + IVsMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 31~31) &c" + IVsPoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                if (p.getIVs().getStat(StatsType.Attack)!=0){
                    Attack.setAmount(p.getIVs().getStat(StatsType.Attack));
                }
                Button AttackButton = new Button(Attack, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=IVsMoney){
                            Main.econ.withdrawPlayer(player, IVsMoney);
                            int value = new Random().nextInt(30)+1;
                            p.getIVs().setStat(StatsType.Attack,value);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=IVsPoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(), IVsPoints);
                                int value = 31;
                                p.getIVs().setStat(StatsType.Attack,value);
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                pokeInfoUpdate.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(11, AttackButton);


                ItemStack Defence = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_IRON"), ColorParser.parse("&c防御 &7("+p.getIVs().getStat(StatsType.Defence)+")"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 00~31) &c" + IVsMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 31~31) &c" + IVsPoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                if (p.getIVs().getStat(StatsType.Defence)!=0){
                    Defence.setAmount(p.getIVs().getStat(StatsType.Defence));
                }
                Button DefenceButton = new Button(Defence, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=IVsMoney){
                            Main.econ.withdrawPlayer(player, IVsMoney);
                            int value = new Random().nextInt(30)+1;
                            p.getIVs().setStat(StatsType.Defence,value);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=IVsPoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(), IVsPoints);
                                int value = 31;
                                p.getIVs().setStat(StatsType.Defence,value);
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                pokeInfoUpdate.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(12, DefenceButton);


                ItemStack Speed = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_CARBOS"), ColorParser.parse("&b速度 &7("+p.getIVs().getStat(StatsType.Speed)+")"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 00~31) &c" + IVsMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 31~31) &c" + IVsPoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                if (p.getIVs().getStat(StatsType.Speed)!=0){
                    Speed.setAmount(p.getIVs().getStat(StatsType.Speed));
                }
                Button SpeedButton = new Button(Speed, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=IVsMoney){
                            Main.econ.withdrawPlayer(player, IVsMoney);
                            int value = new Random().nextInt(30)+1;
                            p.getIVs().setStat(StatsType.Speed,value);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=IVsPoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(), IVsPoints);
                                int value = 31;
                                p.getIVs().setStat(StatsType.Speed,value);
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                pokeInfoUpdate.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(19, SpeedButton);


                ItemStack SpecialAttack = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_CALCIUM"), ColorParser.parse("&6特攻 &7("+p.getIVs().getStat(StatsType.SpecialAttack)+")"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 00~31) &c" + IVsMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 31~31) &c" + IVsPoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                if (p.getIVs().getStat(StatsType.SpecialAttack)!=0){
                    SpecialAttack.setAmount(p.getIVs().getStat(StatsType.SpecialAttack));
                }
                Button SpecialAttackButton = new Button(SpecialAttack, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=IVsMoney){
                            Main.econ.withdrawPlayer(player, IVsMoney);
                            int value = new Random().nextInt(30)+1;
                            p.getIVs().setStat(StatsType.SpecialAttack,value);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=IVsPoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(), IVsPoints);
                                int value = 31;
                                p.getIVs().setStat(StatsType.SpecialAttack,value);
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                pokeInfoUpdate.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(20, SpecialAttackButton);


                ItemStack SpecialDefence = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ZINC"), ColorParser.parse("&c特防 &7("+p.getIVs().getStat(StatsType.SpecialDefence)+")"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 00~31) &c" + IVsMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 31~31) &c" + IVsPoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                if (p.getIVs().getStat(StatsType.SpecialDefence)!=0){
                    SpecialDefence.setAmount(p.getIVs().getStat(StatsType.SpecialDefence));
                }
                Button SpecialDefenceButton = new Button(SpecialDefence, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=IVsMoney){
                            Main.econ.withdrawPlayer(player, IVsMoney);
                            int value = new Random().nextInt(30)+1;
                            p.getIVs().setStat(StatsType.SpecialDefence,value);
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=IVsPoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(), IVsPoints);
                                int value = 31;
                                p.getIVs().setStat(StatsType.SpecialDefence,value);
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                pokeInfoUpdate.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(21, SpecialDefenceButton);


                //闪光
                long ShinyMoney = 30000;
                int ShinyPoints = 15;
                if (p.isShiny()){
                    ItemStack Shiny = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_SHINY_CHARM"), ColorParser.parse("&e闪光"),
                            ColorParser.parse("&r"),
                            ColorParser.parse("&7&o您的精灵已经是闪光啦!"));
                    this.setButton(15, new Button(Shiny));
                }else {
                    ItemStack Shiny = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_SHINY_CHARM") , ColorParser.parse("&7闪光"),
                            ColorParser.parse("&r"),
                            ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                            ColorParser.parse("&r"),
                            ColorParser.parse("&r  &e■ &7售 价:"),
                            ColorParser.parse("&r      &7(左键 50%) &c" + ShinyMoney + " &7"+Data.SERVER_VAULT),
                            ColorParser.parse("&r      &7(右键 100%) &c" + ShinyPoints + " &7"+Data.SERVER_POINTS),
                            ColorParser.parse("&r")
                    );
                    Button ShinyButton = new Button(Shiny, type -> {
                        if (type.isLeftClick()) {
                            if (Main.econ.getBalance(player)>=ShinyMoney){
                                Main.econ.withdrawPlayer(player, ShinyMoney);
                                int value = new Random().nextInt(2);
                                if (value==1){
                                    p.setShiny(true);
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                    PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                    pokeInfoUpdate.openInventory();
                                }else {
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                }
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                            }
                        }
                        if (type.isRightClick()){
                            try {
                                if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=ShinyPoints){
                                    Main.ppAPI.takeAsync(player.getUniqueId(), ShinyPoints);
                                    p.setShiny(true);
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                    PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                                    pokeInfoUpdate.openInventory();
                                }else {
                                    player.closeInventory();
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                                }
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    this.setButton(15, ShinyButton);
                }

                //体型
                long GrowthMoney = 5000;
                int GrowthPoints = 5;
                ItemStack Growth = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_N_LUNARIZER") , ColorParser.parse("&a"+p.getGrowth().getLocalizedName()),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 随机) &c" + GrowthMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 自选) &c" + GrowthPoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                Button GrowthButton = new Button(Growth, type -> {
                    if (p.getLocalizedName().equals("无极汰那")){
                        player.closeInventory();
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，因为该宝可梦性能问题，无法修改体型."));
                        player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                        return;
                    }
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=GrowthMoney){
                            Main.econ.withdrawPlayer(player, GrowthMoney);
                            p.setGrowth(EnumGrowth.getRandomGrowth());
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=GrowthPoints){
                                GrowthSelectGUI growthSelectGUI = new GrowthSelectGUI(player,pokemon);
                                growthSelectGUI.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(16, GrowthButton);

                //克隆
                long CloneMoney = 2480000;
                int ClonePoints = 248;
                ItemStack Clone = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_CLONING_MACHINE") , ColorParser.parse("&d梦幻 &f> &c百变怪/超梦"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 50%) &c" + CloneMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 100%) &c" + ClonePoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                Button CloneButton = new Button(Clone, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=CloneMoney){
                            Main.econ.withdrawPlayer(player, CloneMoney);

                            PokemonAPI.getCloningMachine(player,playerPartyStorage,p, playerPartyStorage.getSlot(p.getUUID()),false);

                            player.closeInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=ClonePoints){
                                Main.ppAPI.takeAsync(player.getUniqueId(),ClonePoints);

                                PokemonAPI.getCloningMachine(player,playerPartyStorage,p, playerPartyStorage.getSlot(p.getUUID()),true);

                                player.closeInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (p.getLocalizedName().equals("梦幻")){
                    this.setButton(17, CloneButton);
                }


                //性格
                long NatureMoney = 3500;
                int NaturePoints = 25;
                ItemStack Nature = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_RAGE_CANDY_BAR") , ColorParser.parse("&a"+p.getNature().getLocalizedName()),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                        ColorParser.parse("&r"),
                        ColorParser.parse("&r  &e■ &7售 价:"),
                        ColorParser.parse("&r      &7(左键 随机) &c" + NatureMoney + " &7"+Data.SERVER_VAULT),
                        ColorParser.parse("&r      &7(右键 自选) &c" + NaturePoints + " &7"+Data.SERVER_POINTS),
                        ColorParser.parse("&r")
                );
                Button NatureButton = new Button(Nature, type -> {
                    if (type.isLeftClick()) {
                        if (Main.econ.getBalance(player)>=NatureMoney){
                            Main.econ.withdrawPlayer(player, NatureMoney);
                            p.setNature(EnumNature.getRandomNature());
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                            PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,p);
                            pokeInfoUpdate.openInventory();
                        }else {
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_VAULT+" &7来进行本次的操作."));
                        }
                    }
                    if (type.isRightClick()){
                        try {
                            if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=NaturePoints){
                                NatureSelectGUI natureSelectGUI = new NatureSelectGUI(player,pokemon);
                                natureSelectGUI.openInventory();
                            }else {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.setButton(24, NatureButton);

                //特性
                long AbilityMoney = -1;
                int AbilityPoints = 88;
                if (pokemon.getAbilitySlot()==2){
                    ItemStack Ability = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ABILITY_CAPSULE") , ColorParser.parse("&a"+p.getAbility().getLocalizedName()),
                            ColorParser.parse("&r"),
                            ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                            ColorParser.parse("&r"),
                            ColorParser.parse("&r  &e■ &7售 价:"),
                            ColorParser.parse("&r      &7(左键 随机) &c" + AbilityMoney + " &7"+Data.SERVER_VAULT),
                            ColorParser.parse("&r      &7(右键 自选) &c" + AbilityPoints + " &7"+Data.SERVER_POINTS),
                            ColorParser.parse("&r")
                    );
                    Button AbilityButton = new Button(Ability, type -> {
                        if (type.isLeftClick()){
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，宝可梦已经是梦特了，无法再次购买."));
                        }
                    });
                    this.setButton(25, AbilityButton);
                }else {
                    ItemStack Ability = ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_ABILITY_CAPSULE") , ColorParser.parse("&a"+p.getAbility().getLocalizedName()),
                            ColorParser.parse("&r"),
                            ColorParser.parse("&7&o这将会对您的宝可梦进行不可逆转的培养,谨慎操作!"),
                            ColorParser.parse("&r"),
                            ColorParser.parse("&r  &e■ &7售 价:"),
                            ColorParser.parse("&r      &7(左键 随机) &c" + AbilityMoney + " &7"+Data.SERVER_VAULT),
                            ColorParser.parse("&r      &7(右键 自选) &c" + AbilityPoints + " &7"+Data.SERVER_POINTS),
                            ColorParser.parse("&r")
                    );
                    Button AbilityButton = new Button(Ability, type -> {
                        if (type.isRightClick()){
                            try {
                                if (Main.ppAPI.lookAsync(player.getUniqueId()).get()>=AbilityPoints){
                                    Main.ppAPI.takeAsync(player.getUniqueId(), AbilityPoints);
                                    pokemon.setAbilitySlot(2);
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                                    PokeInfoUpdate pokeInfoUpdate = new PokeInfoUpdate(player,pokemon);
                                    pokeInfoUpdate.openInventory();
                                }else {
                                    player.closeInventory();
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c"+Data.SERVER_POINTS+" &7来进行本次的操作."));
                                }
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    this.setButton(25, AbilityButton);
                }
            }
        }


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
        try {
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
        }catch (NullPointerException ignored){}







        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o返回上级菜单."));
        Button CloseButton = new Button(Close, type -> {
            if (type.isLeftClick()) {
                PokemonInfoMenu pokemonInfoMenu = new PokemonInfoMenu(player);
                pokemonInfoMenu.openInventory();
            }
        });
        this.setButton(53, CloseButton);
    }
}
