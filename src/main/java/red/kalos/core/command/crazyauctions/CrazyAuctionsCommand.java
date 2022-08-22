package red.kalos.core.command.crazyauctions;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import red.kalos.core.database.BanItemManager;
import red.kalos.core.manager.crazyauctions.Methods;
import red.kalos.core.manager.crazyauctions.api.*;
import red.kalos.core.manager.crazyauctions.api.*;
import red.kalos.core.manager.crazyauctions.api.events.AuctionListEvent;
import red.kalos.core.manager.crazyauctions.controllers.GUI;
import red.kalos.core.util.ColorParser;
import red.kalos.core.util.api.PokemonPhotoAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CrazyAuctionsCommand implements CommandExecutor {

    public static CrazyAuctions crazyAuctions = CrazyAuctions.getInstance();

    public CrazyAuctionsCommand() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {

        if (commandLable.equalsIgnoreCase("kim") && sender instanceof Player) {
            Player player = Bukkit.getPlayer(((Player) sender).getUniqueId());
            if (args.length > 1 && (args[0].equalsIgnoreCase("Sell") || args[0].equalsIgnoreCase("Bid"))) {
                return SellItem(sender, args);
            } else if (args.length == 3 && (args[0].equalsIgnoreCase("poke") || args[0].equalsIgnoreCase("pokeBid"))) {
                String numStr = args[1];
                String moneyStr = args[2];
                int num;
                int money;
                try{
                    num = Integer.parseInt(numStr);
                    money = Integer.parseInt(moneyStr);
                }
                catch (Exception e) {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您输入有误，请重新输入数字。"));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                    return false;
                }
                if(!(num>0 && num<7)){
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您只能输入 &c1-6 &7之前的数字，对应背包内的宝可梦。"));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                    return false;
                }
                if(money <= 0){
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您出售的价格请大于 &c0 &7否则无法上架。"));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                    return false;
                }
                Pokemon pokemon = Pixelmon.storageManager.getParty(player.getUniqueId()).get(num-1);
                if(pokemon == null){
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该位置上并没有宝可梦，请重新输入。"));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                    return false;
                }
                if(Pixelmon.storageManager.getParty(player.getUniqueId()).getTeam().size()==1){
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您背包内至少要拥有 &c1 &7只以上的宝可梦。"));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                    return false;
                }
                ItemStack is = PokemonPhotoAPI.getPokeEggItem(pokemon,true,num-1,Pixelmon.storageManager.getParty(player.getUniqueId()),"CrazyAuctions/PokeEggs");

                if(SellPokemon(sender, new String[]{ args[0].equalsIgnoreCase("pokeBid") ? "bid" : "sell",moneyStr},is)){
                    PlayerPartyStorage pps = Pixelmon.storageManager.getParty(player.getUniqueId());
                    pps.set(num-1,pokemon);
                    PokemonPhotoAPI.deletePokemonFile(is,"CrazyAuctions/PokeEggs");
                }
            } else {
                GUI.openShop(player, ShopType.SELL, Category.NONE, 1);
            }
        }
        return false;
    }

    private ArrayList<Material> getDamageableItems() {
        ArrayList<Material> ma = new ArrayList<>();
        if (Version.isNewer(Version.v1_12_R1)) {
            ma.add(Material.matchMaterial("GOLDEN_HELMET"));
            ma.add(Material.matchMaterial("GOLDEN_CHESTPLATE"));
            ma.add(Material.matchMaterial("GOLDEN_LEGGINGS"));
            ma.add(Material.matchMaterial("GOLDEN_BOOTS"));
            ma.add(Material.matchMaterial("WOODEN_SWORD"));
            ma.add(Material.matchMaterial("WOODEN_AXE"));
            ma.add(Material.matchMaterial("WOODEN_PICKAXE"));
            ma.add(Material.matchMaterial("WOODEN_AXE"));
            ma.add(Material.matchMaterial("WOODEN_SHOVEL"));
            ma.add(Material.matchMaterial("STONE_SHOVEL"));
            ma.add(Material.matchMaterial("IRON_SHOVEL"));
            ma.add(Material.matchMaterial("DIAMOND_SHOVEL"));
            ma.add(Material.matchMaterial("WOODEN_HOE"));
            ma.add(Material.matchMaterial("GOLDEN_HOE"));
            ma.add(Material.matchMaterial("CROSSBOW"));
            ma.add(Material.matchMaterial("TRIDENT"));
            ma.add(Material.matchMaterial("TURTLE_HELMET"));
        } else {
            ma.add(Material.matchMaterial("GOLD_HELMET"));
            ma.add(Material.matchMaterial("GOLD_CHESTPLATE"));
            ma.add(Material.matchMaterial("GOLD_LEGGINGS"));
            ma.add(Material.matchMaterial("GOLD_BOOTS"));
            ma.add(Material.matchMaterial("WOOD_SWORD"));
            ma.add(Material.matchMaterial("WOOD_AXE"));
            ma.add(Material.matchMaterial("WOOD_PICKAXE"));
            ma.add(Material.matchMaterial("WOOD_AXE"));
            ma.add(Material.matchMaterial("WOOD_SPADE"));
            ma.add(Material.matchMaterial("STONE_SPADE"));
            ma.add(Material.matchMaterial("IRON_SPADE"));
            ma.add(Material.matchMaterial("DIAMOND_SPADE"));
            ma.add(Material.matchMaterial("WOOD_HOE"));
            ma.add(Material.matchMaterial("GOLD_HOE"));
        }
        ma.add(Material.DIAMOND_HELMET);
        ma.add(Material.DIAMOND_CHESTPLATE);
        ma.add(Material.DIAMOND_LEGGINGS);
        ma.add(Material.DIAMOND_BOOTS);
        ma.add(Material.CHAINMAIL_HELMET);
        ma.add(Material.CHAINMAIL_CHESTPLATE);
        ma.add(Material.CHAINMAIL_LEGGINGS);
        ma.add(Material.CHAINMAIL_BOOTS);
        ma.add(Material.IRON_HELMET);
        ma.add(Material.IRON_CHESTPLATE);
        ma.add(Material.IRON_LEGGINGS);
        ma.add(Material.IRON_BOOTS);
        ma.add(Material.LEATHER_HELMET);
        ma.add(Material.LEATHER_CHESTPLATE);
        ma.add(Material.LEATHER_LEGGINGS);
        ma.add(Material.LEATHER_BOOTS);
        ma.add(Material.BOW);
        ma.add(Material.STONE_SWORD);
        ma.add(Material.IRON_SWORD);
        ma.add(Material.DIAMOND_SWORD);
        ma.add(Material.STONE_AXE);
        ma.add(Material.IRON_AXE);
        ma.add(Material.DIAMOND_AXE);
        ma.add(Material.STONE_PICKAXE);
        ma.add(Material.IRON_PICKAXE);
        ma.add(Material.DIAMOND_PICKAXE);
        ma.add(Material.STONE_AXE);
        ma.add(Material.IRON_AXE);
        ma.add(Material.DIAMOND_AXE);
        ma.add(Material.STONE_HOE);
        ma.add(Material.IRON_HOE);
        ma.add(Material.DIAMOND_HOE);
        ma.add(Material.FLINT_AND_STEEL);
        ma.add(Material.ANVIL);
        ma.add(Material.FISHING_ROD);
        return ma;
    }

    private boolean allowBook(ItemStack item) {
        if (item != null && item.hasItemMeta() && item.getItemMeta() instanceof BookMeta) {
            System.out.println("[Crazy Auctions] Checking " + item.getType() + " for illegal unicode.");
            FileManager.Files.TEST_FILE.getFile().set("Test", item);
            FileManager.Files.TEST_FILE.saveFile();
            System.out.println("[Crazy Auctions] " + item.getType() + " has passed unicode checks.");
            return ((BookMeta) item.getItemMeta()).getPages().stream().mapToInt(String::length).sum() < 2000;
        }
        return true;
    }

    public boolean SellPokemon(CommandSender sender, String[] args,ItemStack item) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.PLAYERS_ONLY.getMessage());
            return true;
        }
        if (args.length >= 2) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("Sell")) {
                if (!crazyAuctions.isSellingEnabled()) {
                    player.sendMessage(Messages.SELLING_DISABLED.getMessage());
                    return true;
                }
                if (!Methods.hasPermission(player, "Sell")) return true;
            }
            if (args[0].equalsIgnoreCase("Bid")) {
                if (!crazyAuctions.isBiddingEnabled()) {
                    player.sendMessage(Messages.BIDDING_DISABLED.getMessage());
                    return true;
                }
                if (!Methods.hasPermission(player, "Bid")) return true;
            }
            if (BanItemManager.getBanDrops().contains(item.getType().name())&&(!item.getType().name().contains("PIXELMON_PIXELMON_SPRITE"))) {
                player.getInventory().remove(item);
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该物品已经被服务器禁止使用。"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                return true;
            }
            int amount = item.getAmount();
            if (args.length >= 3) {
                if (!Methods.isInt(args[2])) {
                    HashMap<String, String> placeholders = new HashMap<>();
                    placeholders.put("%Arg%", args[2]);
                    placeholders.put("%arg%", args[2]);
                    player.sendMessage(Messages.NOT_A_NUMBER.getMessage(placeholders));
                    return true;
                }
                amount = Integer.parseInt(args[2]);
                if (amount <= 0) amount = 1;
                if (amount > item.getAmount()) amount = item.getAmount();
            }
            if (!Methods.isLong(args[1])) {
                HashMap<String, String> placeholders = new HashMap<>();
                placeholders.put("%Arg%", args[1]);
                placeholders.put("%arg%", args[1]);
                player.sendMessage(Messages.NOT_A_NUMBER.getMessage(placeholders));
                return true;
            }
            long price = Long.parseLong(args[1]);
            if (args[0].equalsIgnoreCase("Bid")) {
                if (price < FileManager.Files.CONFIG.getFile().getLong("Settings.Minimum-Bid-Price")) {
                    player.sendMessage(Messages.BID_PRICE_TO_LOW.getMessage());
                    return true;
                }
                if (price > FileManager.Files.CONFIG.getFile().getLong("Settings.Max-Beginning-Bid-Price")) {
                    player.sendMessage(Messages.BID_PRICE_TO_HIGH.getMessage());
                    return true;
                }
            } else {
                if (price < FileManager.Files.CONFIG.getFile().getLong("Settings.Minimum-Sell-Price")) {
                    player.sendMessage(Messages.SELL_PRICE_TO_LOW.getMessage());
                    return true;
                }
                if (price > FileManager.Files.CONFIG.getFile().getLong("Settings.Max-Beginning-Sell-Price")) {
                    player.sendMessage(Messages.SELL_PRICE_TO_HIGH.getMessage());
                    return true;
                }
            }
            if (!player.hasPermission("crazyauctions.bypass")) {
                int SellLimit = 0;
                int BidLimit = 0;
                for (PermissionAttachmentInfo permission : player.getEffectivePermissions()) {
                    String perm = permission.getPermission();
                    if (perm.startsWith("crazyauctions.sell.")) {
                        perm = perm.replace("crazyauctions.sell.", "");
                        if (Methods.isInt(perm)) {
                            if (Integer.parseInt(perm) > SellLimit) {
                                SellLimit = Integer.parseInt(perm);
                            }
                        }
                    }
                    if (perm.startsWith("crazyauctions.bid.")) {
                        perm = perm.replace("crazyauctions.bid.", "");
                        if (Methods.isInt(perm)) {
                            if (Integer.parseInt(perm) > BidLimit) {
                                BidLimit = Integer.parseInt(perm);
                            }
                        }
                    }
                }
                for (int i = 1; i < 100; i++) {
                    if (SellLimit < i) {
                        if (player.hasPermission("crazyauctions.sell." + i)) {
                            SellLimit = i;
                        }
                    }
                    if (BidLimit < i) {
                        if (player.hasPermission("crazyauctions.bid." + i)) {
                            BidLimit = i;
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("Sell") || args[0].equalsIgnoreCase("poke")) {
                    if (crazyAuctions.getItems(player, ShopType.SELL).size() >= SellLimit) {
                        player.sendMessage(Messages.MAX_ITEMS.getMessage());
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("Bid")) {
                    if (crazyAuctions.getItems(player, ShopType.BID).size() >= BidLimit) {
                        player.sendMessage(Messages.MAX_ITEMS.getMessage());
                        return true;
                    }
                }
            }
            for (String id : FileManager.Files.CONFIG.getFile().getStringList("Settings.BlackList")) {
                if (item.getType() == Methods.makeItem(id, 1).getType()) {
                    player.sendMessage(Messages.ITEM_BLACKLISTED.getMessage());
                    return true;
                }
            }
            if (!FileManager.Files.CONFIG.getFile().getBoolean("Settings.Allow-Damaged-Items")) {
                for (Material i : getDamageableItems()) {
                    if (item.getType() == i) {
                        if (item.getDurability() > 0) {
                            player.sendMessage(Messages.ITEM_DAMAGED.getMessage());
                            return true;
                        }
                    }
                }
            }
            if (!allowBook(item)) {
                player.sendMessage(Messages.BOOK_NOT_ALLOWED.getMessage());
                return true;
            }
            String seller = player.getName();
            // For testing as another player
            //String seller = "Test-Account";
            int num = 1;
            Random r = new Random();
            for (; FileManager.Files.DATA.getFile().contains("Items." + num); num++) ;
            FileManager.Files.DATA.getFile().set("Items." + num + ".Price", price);
            FileManager.Files.DATA.getFile().set("Items." + num + ".Seller", seller);
            if (args[0].equalsIgnoreCase("Bid")) {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Time-Till-Expire", Methods.convertToMill(FileManager.Files.CONFIG.getFile().getString("Settings.Bid-Time")));
            } else {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Time-Till-Expire", Methods.convertToMill(FileManager.Files.CONFIG.getFile().getString("Settings.Sell-Time")));
            }
            FileManager.Files.DATA.getFile().set("Items." + num + ".Full-Time", Methods.convertToMill(FileManager.Files.CONFIG.getFile().getString("Settings.Full-Expire-Time")));
            int id = r.nextInt(999999);
            // Runs 3x to check for same ID.
            for (String i : FileManager.Files.DATA.getFile().getConfigurationSection("Items").getKeys(false))
                if (FileManager.Files.DATA.getFile().getInt("Items." + i + ".StoreID") == id)
                    id = r.nextInt(Integer.MAX_VALUE);
            for (String i : FileManager.Files.DATA.getFile().getConfigurationSection("Items").getKeys(false))
                if (FileManager.Files.DATA.getFile().getInt("Items." + i + ".StoreID") == id)
                    id = r.nextInt(Integer.MAX_VALUE);
            for (String i : FileManager.Files.DATA.getFile().getConfigurationSection("Items").getKeys(false))
                if (FileManager.Files.DATA.getFile().getInt("Items." + i + ".StoreID") == id)
                    id = r.nextInt(Integer.MAX_VALUE);
            FileManager.Files.DATA.getFile().set("Items." + num + ".StoreID", id);
            ShopType type = ShopType.SELL;
            if (args[0].equalsIgnoreCase("Bid")) {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Biddable", true);
                type = ShopType.BID;
            } else {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Biddable", false);
            }
            FileManager.Files.DATA.getFile().set("Items." + num + ".TopBidder", "None");
            ItemStack I = item.clone();
            I.setAmount(amount);
            FileManager.Files.DATA.getFile().set("Items." + num + ".Item", I);
            FileManager.Files.DATA.saveFile();
            Bukkit.getPluginManager().callEvent(new AuctionListEvent(player, type, I, price));
            HashMap<String, String> placeholders = new HashMap<>();
            placeholders.put("%Price%", price + "");
            placeholders.put("%price%", price + "");
            player.sendMessage(Messages.ADDED_ITEM_TO_AUCTION.getMessage(placeholders));
            Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7玩家 &a"+player.getName()+" &7向全球市场发布了一个 &a"+item.getItemMeta().getDisplayName()+" &7宝可梦。"));
            return false;
        }
        return false;
    }


    public boolean SellItem(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.PLAYERS_ONLY.getMessage());
            return true;
        }
        if (args.length >= 2) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("Sell")) {
                if (!crazyAuctions.isSellingEnabled()) {
                    player.sendMessage(Messages.SELLING_DISABLED.getMessage());
                    return true;
                }
                if (!Methods.hasPermission(player, "Sell")) return true;
            }
            if (args[0].equalsIgnoreCase("Bid")) {
                if (!crazyAuctions.isBiddingEnabled()) {
                    player.sendMessage(Messages.BIDDING_DISABLED.getMessage());
                    return true;
                }
                if (!Methods.hasPermission(player, "Bid")) return true;
            }
            ItemStack item = Methods.getItemInHand(player);
            if (BanItemManager.getBanDrops().contains(item.getType().name())) {
                player.getInventory().remove(item);
                player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，该物品已经被服务器禁止使用。"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                return true;
            }
            int amount = item.getAmount();
            if (args.length >= 3) {
                if (!Methods.isInt(args[2])) {
                    HashMap<String, String> placeholders = new HashMap<>();
                    placeholders.put("%Arg%", args[2]);
                    placeholders.put("%arg%", args[2]);
                    player.sendMessage(Messages.NOT_A_NUMBER.getMessage(placeholders));
                    return true;
                }
                amount = Integer.parseInt(args[2]);
                if (amount <= 0) amount = 1;
                if (amount > item.getAmount()) amount = item.getAmount();
            }
            if (!Methods.isLong(args[1])) {
                HashMap<String, String> placeholders = new HashMap<>();
                placeholders.put("%Arg%", args[1]);
                placeholders.put("%arg%", args[1]);
                player.sendMessage(Messages.NOT_A_NUMBER.getMessage(placeholders));
                return true;
            }
            if (Methods.getItemInHand(player).getType() == Material.AIR) {
                player.sendMessage(Messages.DOSENT_HAVE_ITEM_IN_HAND.getMessage());
                return false;
            }
            long price = Long.parseLong(args[1]);
            if (args[0].equalsIgnoreCase("Bid")) {
                if (price < FileManager.Files.CONFIG.getFile().getLong("Settings.Minimum-Bid-Price")) {
                    player.sendMessage(Messages.BID_PRICE_TO_LOW.getMessage());
                    return true;
                }
                if (price > FileManager.Files.CONFIG.getFile().getLong("Settings.Max-Beginning-Bid-Price")) {
                    player.sendMessage(Messages.BID_PRICE_TO_HIGH.getMessage());
                    return true;
                }
            } else {
                if (price < FileManager.Files.CONFIG.getFile().getLong("Settings.Minimum-Sell-Price")) {
                    player.sendMessage(Messages.SELL_PRICE_TO_LOW.getMessage());
                    return true;
                }
                if (price > FileManager.Files.CONFIG.getFile().getLong("Settings.Max-Beginning-Sell-Price")) {
                    player.sendMessage(Messages.SELL_PRICE_TO_HIGH.getMessage());
                    return true;
                }
            }
            if (!player.hasPermission("crazyauctions.bypass")) {
                int SellLimit = 0;
                int BidLimit = 0;
                for (PermissionAttachmentInfo permission : player.getEffectivePermissions()) {
                    String perm = permission.getPermission();
                    if (perm.startsWith("crazyauctions.sell.")) {
                        perm = perm.replace("crazyauctions.sell.", "");
                        if (Methods.isInt(perm)) {
                            if (Integer.parseInt(perm) > SellLimit) {
                                SellLimit = Integer.parseInt(perm);
                            }
                        }
                    }
                    if (perm.startsWith("crazyauctions.bid.")) {
                        perm = perm.replace("crazyauctions.bid.", "");
                        if (Methods.isInt(perm)) {
                            if (Integer.parseInt(perm) > BidLimit) {
                                BidLimit = Integer.parseInt(perm);
                            }
                        }
                    }
                }
                for (int i = 1; i < 100; i++) {
                    if (SellLimit < i) {
                        if (player.hasPermission("crazyauctions.sell." + i)) {
                            SellLimit = i;
                        }
                    }
                    if (BidLimit < i) {
                        if (player.hasPermission("crazyauctions.bid." + i)) {
                            BidLimit = i;
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("Sell")) {
                    if (crazyAuctions.getItems(player, ShopType.SELL).size() >= SellLimit) {
                        player.sendMessage(Messages.MAX_ITEMS.getMessage());
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("Bid")) {
                    if (crazyAuctions.getItems(player, ShopType.BID).size() >= BidLimit) {
                        player.sendMessage(Messages.MAX_ITEMS.getMessage());
                        return true;
                    }
                }
            }
            for (String id : FileManager.Files.CONFIG.getFile().getStringList("Settings.BlackList")) {
                if (item.getType() == Methods.makeItem(id, 1).getType()) {
                    player.sendMessage(Messages.ITEM_BLACKLISTED.getMessage());
                    return true;
                }
            }
            if (!FileManager.Files.CONFIG.getFile().getBoolean("Settings.Allow-Damaged-Items")) {
                for (Material i : getDamageableItems()) {
                    if (item.getType() == i) {
                        if (item.getDurability() > 0) {
                            player.sendMessage(Messages.ITEM_DAMAGED.getMessage());
                            return true;
                        }
                    }
                }
            }
            if (!allowBook(item)) {
                player.sendMessage(Messages.BOOK_NOT_ALLOWED.getMessage());
                return true;
            }
            String seller = player.getName();
            // For testing as another player
            //String seller = "Test-Account";
            int num = 1;
            Random r = new Random();
            for (; FileManager.Files.DATA.getFile().contains("Items." + num); num++) ;
            FileManager.Files.DATA.getFile().set("Items." + num + ".Price", price);
            FileManager.Files.DATA.getFile().set("Items." + num + ".Seller", seller);
            if (args[0].equalsIgnoreCase("Bid")) {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Time-Till-Expire", Methods.convertToMill(FileManager.Files.CONFIG.getFile().getString("Settings.Bid-Time")));
            } else {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Time-Till-Expire", Methods.convertToMill(FileManager.Files.CONFIG.getFile().getString("Settings.Sell-Time")));
            }
            FileManager.Files.DATA.getFile().set("Items." + num + ".Full-Time", Methods.convertToMill(FileManager.Files.CONFIG.getFile().getString("Settings.Full-Expire-Time")));
            int id = r.nextInt(999999);
            // Runs 3x to check for same ID.
            for (String i : FileManager.Files.DATA.getFile().getConfigurationSection("Items").getKeys(false))
                if (FileManager.Files.DATA.getFile().getInt("Items." + i + ".StoreID") == id)
                    id = r.nextInt(Integer.MAX_VALUE);
            for (String i : FileManager.Files.DATA.getFile().getConfigurationSection("Items").getKeys(false))
                if (FileManager.Files.DATA.getFile().getInt("Items." + i + ".StoreID") == id)
                    id = r.nextInt(Integer.MAX_VALUE);
            for (String i : FileManager.Files.DATA.getFile().getConfigurationSection("Items").getKeys(false))
                if (FileManager.Files.DATA.getFile().getInt("Items." + i + ".StoreID") == id)
                    id = r.nextInt(Integer.MAX_VALUE);
            FileManager.Files.DATA.getFile().set("Items." + num + ".StoreID", id);
            ShopType type = ShopType.SELL;
            if (args[0].equalsIgnoreCase("Bid")) {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Biddable", true);
                type = ShopType.BID;
            } else {
                FileManager.Files.DATA.getFile().set("Items." + num + ".Biddable", false);
            }
            FileManager.Files.DATA.getFile().set("Items." + num + ".TopBidder", "None");
            ItemStack I = item.clone();
            I.setAmount(amount);
            FileManager.Files.DATA.getFile().set("Items." + num + ".Item", I);
            FileManager.Files.DATA.saveFile();
            Bukkit.getPluginManager().callEvent(new AuctionListEvent(player, type, I, price));
            HashMap<String, String> placeholders = new HashMap<>();
            placeholders.put("%Price%", price + "");
            placeholders.put("%price%", price + "");
            player.sendMessage(Messages.ADDED_ITEM_TO_AUCTION.getMessage(placeholders));
            Bukkit.broadcastMessage(ColorParser.parse("&8[&e&l!&8] &7玩家 &a"+player.getName()+" &7向全球市场发布了商品。"));
            if (item.getAmount() <= 1 || (item.getAmount() - amount) <= 0) {
                Methods.setItemInHand(player, new ItemStack(Material.AIR));
            } else {
                item.setAmount(item.getAmount() - amount);
            }
            return false;
        }
        return false;
    }
}
