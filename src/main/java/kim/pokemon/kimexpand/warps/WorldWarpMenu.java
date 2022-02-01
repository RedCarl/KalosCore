package kim.pokemon.kimexpand.warps;

import kim.pokemon.configFile.Data;
import kim.pokemon.kimexpand.menu.MainMenu;
import kim.pokemon.util.ColorParser;
import kim.pokemon.util.UnexpectedSpawn;
import kim.pokemon.util.gui.Button;
import kim.pokemon.util.gui.InventoryGUI;
import kim.pokemon.util.gui.inventory.ItemFactoryAPI;
import kim.pokemon.util.gui.inventory.SkullAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class WorldWarpMenu extends InventoryGUI {
    public WorldWarpMenu(Player player) {
        super(ColorParser.parse("&0"+ Data.SERVER_NAME+" / 世界坐标"), player, 6);

        //主城世界
        ItemStack Spawn = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/75e5b562b8c0028d929d011099a4ef30836c92a5312db21311b9067871ffdc6d",ColorParser.parse("&6精灵主城 &f// &7Spawn World"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o可以在这里训练宝可梦或者进行交易."));
        Button SpawnButton = new Button(Spawn, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                Location location = new Location(Bukkit.getWorld("spawn"),-20.5,10,47.5);
                location.setYaw(90);
                player.teleport(location);
            }
        });
        this.setButton(0, SpawnButton);


        //精灵世界
        ItemStack TheOverworld = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/212a03a4c11b4d472472e7e4593d2e126a6259e33cc81f44eb05cf042d076967",ColorParser.parse("&a主世界 &f// &7The Overworld"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&oMinecraft的起始维度与主要维度."));
        Button TheOverworldButton = new Button(TheOverworld, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.teleport(new UnexpectedSpawn().randomLocation(Bukkit.getWorld("world")));
                player.sendTitle(ColorParser.parse("&aKalos &f// &a主世界"),ColorParser.parse("&f尽情的享受这个世界吧!"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        });
        this.setButton(1, TheOverworldButton);

        //地狱世界
        ItemStack TheNether = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/d500292f4afe52d10f299dfb26036322830450331e003084bb220333530664e1",ColorParser.parse("&c下届 &f// &7The Nether"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o来探索一个可怕的新维度吧！"));
        Button TheNetherButton = new Button(TheNether, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.teleport(new UnexpectedSpawn().randomLocation(Bukkit.getWorld("DIM-1")));
                player.sendTitle(ColorParser.parse("&cKalos &f// &c下届"),ColorParser.parse("&f这是一个可怕的世界."),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        });
        this.setButton(2, TheNetherButton);

        //末地世界末地（The End）
        ItemStack TheEnd = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/46994d71b875f087e64dea9b4a0a5cb9f4eb9ab0e8d9060dfde7f6803baa1779",ColorParser.parse("&d末地 &f// &7The End"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o末地石是这里唯一会生成的方块."));
        Button TheEndButton = new Button(TheEnd, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.teleport(new Location(Bukkit.getWorld("DIM1"),0,70,0));
                player.sendTitle(ColorParser.parse("&dKalos &f// &d末地"),ColorParser.parse("&f末影龙?"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        });
        this.setButton(3, TheEndButton);

        //究极空间 (Ultra Space)
        ItemStack UltraSpace = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/c3687e25c632bce8aa61e0d64c24e694c3eea629ea944f4cf30dcfb4fbce071",ColorParser.parse("&6究极空间 &f// &7Ultra Space"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o天空会随着时间的推移会慢慢改变颜色."));
        Button UltraSpaceButton = new Button(UltraSpace, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                player.teleport(new UnexpectedSpawn().randomLocation(Bukkit.getWorld("DIM72")));
                player.sendTitle(ColorParser.parse("&6Kalos &f// &6究极世界"),ColorParser.parse("&f天空的颜色好像在变化着?"),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        });
        this.setButton(4, UltraSpaceButton);

        //训练场
        ItemStack DrownedWorld = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/e6799bfaa3a2c63ad85dd378e66d57d9a97a3f86d0d9f683c498632f4f5c",ColorParser.parse("&3训练场 &f// &7Training"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o训练宝可梦的地方,快带着你的宝可梦来历练吧!"));
        Button DrownedWorldButton = new Button(DrownedWorld, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                Location location = new Location(Bukkit.getWorld("spawn"),0.5,10,47.5);
                location.setYaw(-90);
                player.teleport(location);
                player.sendTitle(ColorParser.parse("&3Kalos &f// &3宝可梦训练场"),ColorParser.parse("&f尽情的训练您的宝可梦去吧."),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        });
        this.setButton(5, DrownedWorldButton);

        //训练场
        ItemStack PVPWorld = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/3d1164f8ed8a89bee10a7fa2fec84ecc0221d85097ea60a4a70d378ca00b0241",ColorParser.parse("&c战斗世界 &f// &7PVP World"),
                ColorParser.parse("&r"),
                ColorParser.parse("&7&o危险的世界，与其他玩家一决生死!"));
        Button PVPWorldButton = new Button(PVPWorld, type -> {
            if (type.isLeftClick()) {
                player.closeInventory();
                Location location = new Location(Bukkit.getWorld("pvp"), -120.5,88.5,-52.5);
                location.setYaw(-90);
                player.teleport(location);
                player.sendTitle(ColorParser.parse("&cKalos &f// &c战斗世界"),ColorParser.parse("&f您进入了战斗世界，可以互相攻击."),10,60,10);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        });
        this.setButton(6, PVPWorldButton);

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
