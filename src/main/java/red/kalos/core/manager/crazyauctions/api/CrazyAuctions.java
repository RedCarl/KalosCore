package red.kalos.core.manager.crazyauctions.api;

import red.kalos.core.manager.crazyauctions.controllers.GUI;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class CrazyAuctions {

    private static final CrazyAuctions instance = new CrazyAuctions();
    private Boolean sellingEnabled;
    private Boolean biddingEnabled;

    public static CrazyAuctions getInstance() {
        return instance;
    }

    public void loadCrazyAuctions() {
        if (FileManager.Files.CONFIG.getFile().contains("Settings.Feature-Toggle.Selling")) {
            this.sellingEnabled = FileManager.Files.CONFIG.getFile().getBoolean("Settings.Feature-Toggle.Selling");
        } else {
            this.sellingEnabled = true;
        }
        if (FileManager.Files.CONFIG.getFile().contains("Settings.Feature-Toggle.Bidding")) {
            this.biddingEnabled = FileManager.Files.CONFIG.getFile().getBoolean("Settings.Feature-Toggle.Bidding");
        } else {
            this.biddingEnabled = true;
        }
    }

    public Boolean isSellingEnabled() {
        return sellingEnabled;
    }

    public Boolean isBiddingEnabled() {
        return biddingEnabled;
    }

    public ArrayList<ItemStack> getItems(Player player) {
        FileConfiguration data = FileManager.Files.DATA.getFile();
        ArrayList<ItemStack> items = new ArrayList<>();
        if (data.contains("Items")) {
            for (String i : data.getConfigurationSection("Items").getKeys(false)) {
                if (UUID.fromString(data.getString("Items." + i + ".Seller")).equals(player.getUniqueId())) {
                    items.add(data.getItemStack("Items." + i + ".Item").clone());
                }
            }
        }
        return items;
    }

    public ArrayList<ItemStack> getItems(Player player, ShopType type) {
        FileConfiguration data = FileManager.Files.DATA.getFile();
        ArrayList<ItemStack> items = new ArrayList<>();
        if (data.contains("Items")) {
            for (String i : data.getConfigurationSection("Items").getKeys(false)) {
            	
            	String seller_the_name = data.getString("Items." + i + ".Seller");
				if (seller_the_name.length() == 36) {
					seller_the_name = GUI.getOfflinePlayerFromUUID(seller_the_name).getName();
				}            	
            	
                if (seller_the_name.equals(player.getName())) {
                    if (data.getBoolean("Items." + i + ".Biddable")) {
                        if (type == ShopType.BID) {
                            items.add(data.getItemStack("Items." + i + ".Item").clone());
                        }
                    } else {
                        if (type == ShopType.SELL) {
                            items.add(data.getItemStack("Items." + i + ".Item").clone());
                        }
                    }
                }
            }
        }
        return items;
    }

}