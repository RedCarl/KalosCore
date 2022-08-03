package kim.pokemon.manager.mysteriousstore;

import kim.pokemon.Main;
import kim.pokemon.configFile.Data;
import kim.pokemon.manager.menu.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.ItemAPI;
import kim.pokemon.util.PokemonAPI;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CharmanderShop extends InventoryGUI {
    public CharmanderShop(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 小火龙の活动"), player, 6);


        ItemStack A1 = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&f春节集福活动 &c#1"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7任务要求:"),
                ColorParser.parse("&r          &c爱国福*10"),
                ColorParser.parse("&r          &c友善福*10"),
                ColorParser.parse("&r          &c和谐福*10"),
                ColorParser.parse("&r          &c富强福*18"),
                ColorParser.parse("&r          &c敬业福*18"),
                ColorParser.parse("&r  &a■ &7礼包内容:"),
                ColorParser.parse("&r          &c胡帕*1")

        );
        Button A1Button = new Button(A1, type -> {
            if (type.isLeftClick()){
                if (
                        player.getInventory().containsAtLeast(ItemAPI.agf, 10)&&
                        player.getInventory().containsAtLeast(ItemAPI.ysf, 10)&&
                        player.getInventory().containsAtLeast(ItemAPI.hxf, 10)&&
                        player.getInventory().containsAtLeast(ItemAPI.fqf, 18)&&
                        player.getInventory().containsAtLeast(ItemAPI.jyf, 18)
                ){
                    player.closeInventory();
                    ArrayList<ItemStack> itemStacks = new ArrayList<>();
                    ItemStack itemStack;

                    itemStack=ItemAPI.agf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.ysf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.hxf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.fqf;
                    itemStack.setAmount(18);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.jyf;
                    itemStack.setAmount(18);
                    itemStacks.add(itemStack);

                    for (ItemStack i:itemStacks) {
                        player.getInventory().removeItem(i);
                    }

                    PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Hoopa"));

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c物品 &7来兑换这个奖励."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(0, A1Button);


        ItemStack A2 = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&f春节集福活动 &c#2"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7任务要求:"),
                ColorParser.parse("&r          &c爱国福*10"),
                ColorParser.parse("&r          &c友善福*3"),
                ColorParser.parse("&r          &c富强福*10"),
                ColorParser.parse("&r          &c敬业福*10"),
                ColorParser.parse("&r  &a■ &7礼包内容:"),
                ColorParser.parse("&r          &c班基拉斯进化石*1"),
                ColorParser.parse("&r          &c爆飞龙进化石*1")

        );
        Button A2Button = new Button(A2, type -> {
            if (type.isLeftClick()){
                if (
                        player.getInventory().containsAtLeast(ItemAPI.agf, 10)&&
                                player.getInventory().containsAtLeast(ItemAPI.ysf, 3)&&
                                player.getInventory().containsAtLeast(ItemAPI.fqf, 10)&&
                                player.getInventory().containsAtLeast(ItemAPI.jyf, 10)
                ){
                    player.closeInventory();
                    ArrayList<ItemStack> itemStacks = new ArrayList<>();
                    ItemStack itemStack;

                    itemStack=ItemAPI.agf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.ysf;
                    itemStack.setAmount(3);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.fqf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.jyf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    for (ItemStack i:itemStacks) {
                        player.getInventory().removeItem(i);
                    }

                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_TYRANITARITE"),1));
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_SALAMENCITE"),1));

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c物品 &7来兑换这个奖励."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(1, A2Button);


        ItemStack A3 = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&f春节集福活动 &c#3"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7任务要求:"),
                ColorParser.parse("&r          &c爱国福*10"),
                ColorParser.parse("&r          &c友善福*1"),
                ColorParser.parse("&r  &a■ &7礼包内容:"),
                ColorParser.parse("&r          &c强制锻炼器*1"),
                ColorParser.parse("&r          &c学习装置*3")

        );
        Button A3Button = new Button(A3, type -> {
            if (type.isLeftClick()){
                if (
                        player.getInventory().containsAtLeast(ItemAPI.agf, 10)&&
                                player.getInventory().containsAtLeast(ItemAPI.ysf, 1)
                ){
                    player.closeInventory();
                    ArrayList<ItemStack> itemStacks = new ArrayList<>();
                    ItemStack itemStack;

                    itemStack=ItemAPI.agf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    itemStack=ItemAPI.ysf;
                    itemStack.setAmount(1);
                    itemStacks.add(itemStack);

                    for (ItemStack i:itemStacks) {
                        player.getInventory().removeItem(i);
                    }

                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_MACHO_BRACE"),1));
                    player.getInventory().addItem(ItemFactoryAPI.getItemStack(Material.getMaterial("PIXELMON_EXP_SHARE"),3));

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c物品 &7来兑换这个奖励."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(2, A3Button);


        ItemStack A4 = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&f春节集福活动 &c#4"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7任务要求:"),
                ColorParser.parse("&r          &c爱国福*1"),
                ColorParser.parse("&r  &a■ &7礼包内容:"),
                ColorParser.parse("&r          &c卡洛币*30")

        );
        Button A4Button = new Button(A4, type -> {
            if (type.isLeftClick()){
                if (
                        player.getInventory().containsAtLeast(ItemAPI.agf, 1)
                ){
                    player.closeInventory();
                    ArrayList<ItemStack> itemStacks = new ArrayList<>();
                    ItemStack itemStack;

                    itemStack=ItemAPI.agf;
                    itemStack.setAmount(1);
                    itemStacks.add(itemStack);

                    for (ItemStack i:itemStacks) {
                        player.getInventory().removeItem(i);
                    }

                    Main.econ.depositPlayer(player,30);

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c物品 &7来兑换这个奖励."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(3, A4Button);


        ItemStack A5 = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&f春节集福活动 &c#5"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7任务要求:"),
                ColorParser.parse("&r          &c友善福*5"),
                ColorParser.parse("&r  &a■ &7礼包内容:"),
                ColorParser.parse("&r          &c谢米1")

        );
        Button A5Button = new Button(A5, type -> {
            if (type.isLeftClick()){
                if (
                        player.getInventory().containsAtLeast(ItemAPI.ysf, 5)
                ){
                    player.closeInventory();
                    ArrayList<ItemStack> itemStacks = new ArrayList<>();
                    ItemStack itemStack;

                    itemStack=ItemAPI.ysf;
                    itemStack.setAmount(5);
                    itemStacks.add(itemStack);

                    for (ItemStack i:itemStacks) {
                        player.getInventory().removeItem(i);
                    }

                    PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Shaymin"));

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c物品 &7来兑换这个奖励."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(4, A5Button);


        ItemStack A6 = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&f春节集福活动 &c#6"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7任务要求:"),
                ColorParser.parse("&r          &c友善福*10"),
                ColorParser.parse("&r  &a■ &7礼包内容:"),
                ColorParser.parse("&r          &c比克提尼*1")

        );
        Button A6Button = new Button(A6, type -> {
            if (type.isLeftClick()){
                if (
                        player.getInventory().containsAtLeast(ItemAPI.ysf, 10)
                ){
                    player.closeInventory();
                    ArrayList<ItemStack> itemStacks = new ArrayList<>();
                    ItemStack itemStack;

                    itemStack=ItemAPI.ysf;
                    itemStack.setAmount(10);
                    itemStacks.add(itemStack);

                    for (ItemStack i:itemStacks) {
                        player.getInventory().removeItem(i);
                    }

                    PokemonAPI.GivePokemon(player,false,0,0,false,PokemonAPI.SpawnPokemon("Victini"));

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c物品 &7来兑换这个奖励."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(5, A6Button);


        ItemStack A7 = ItemFactoryAPI.getItemStack(Material.PAPER,
                ColorParser.parse("&f春节集福活动 &c#7"),
                ColorParser.parse("&r"),
                ColorParser.parse("&r  &e■ &7任务要求:"),
                ColorParser.parse("&r          &c敬业福*1"),
                ColorParser.parse("&r  &a■ &7礼包内容:"),
                ColorParser.parse("&r          &c卡洛币*50")

        );
        Button A7Button = new Button(A7, type -> {
            if (type.isLeftClick()){
                if (
                        player.getInventory().containsAtLeast(ItemAPI.jyf, 1)
                ){
                    player.closeInventory();
                    ArrayList<ItemStack> itemStacks = new ArrayList<>();
                    ItemStack itemStack;

                    itemStack=ItemAPI.jyf;
                    itemStack.setAmount(1);
                    itemStacks.add(itemStack);

                    for (ItemStack i:itemStacks) {
                        player.getInventory().removeItem(i);
                    }

                    Main.econ.depositPlayer(player,50);

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您没有足够的 &c物品 &7来兑换这个奖励."));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        });
        this.setButton(6, A7Button);


        ItemStack Line = ItemFactoryAPI.getItemStackWithDurability(Material.STAINED_GLASS_PANE,(short)15, ColorParser.parse("&r"));
        for (int i = 0; i < 9; i++) {
            this.setButton(36+i, new Button(Line));
        }

        //伊布の折扣店
        ItemStack A = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ce0752ba89de69834a47f65a17c88498d07c87e54d9926f4fb9be2ce4d94f96f",
                ColorParser.parse("&e伊布の折扣店"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o每日随机的折扣商品供您挑选."));
        Button AButton = new Button(A, type -> {
            if (type.isLeftClick()) {
                EeveeShop eeveeShop = new EeveeShop(player);
                eeveeShop.openInventory();
            }
        });
        this.setButton(45, AButton);

        //限时累计充值
        ItemStack B = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/137f05b86b1a7fd14f29c24e15db3636d8912276ca0d566658a95645b3339f23",
                ColorParser.parse("&6皮卡丘の宝藏"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o一些限时的礼包和商品."));
        Button BButton = new Button(B, type -> {
            if (type.isLeftClick()) {
                PikachuShop pikachuShop = new PikachuShop(player);
                pikachuShop.openInventory();
            }
        });
        this.setButton(46, BButton);

        //活动兑换
        ItemStack C = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/6eac4e2908e39750a9b97fdf8c272f8c647786d452fb26a73dc37d39f5a864b9",
                ColorParser.parse("&c小火龙の活动"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o用于活动兑换物品."));
        Button CButton = new Button(C, type -> {
            if (type.isLeftClick()) {
                CharmanderShop charmanderShop = new CharmanderShop(player);
                charmanderShop.openInventory();
            }
        });
        this.setButton(47, CButton);

        //返回主菜单
        ItemStack Close = ItemFactoryAPI.getItemStack(Material.BARRIER, ColorParser.parse("&c返回"),
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
