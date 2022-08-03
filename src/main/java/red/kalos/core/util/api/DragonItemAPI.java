package red.kalos.core.util.api;

import eos.moe.armourers.api.DragonAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DragonItemAPI {

    public static ItemStack getItemStack(String dragonName,String displayName,String... lores){
        ItemStack is = ItemFacAPI.getItemStack(Material.MAGMA_CREAM,displayName,lores);
        is = DragonAPI.setItemSkin(is,dragonName);
        return is;
    }

    public static ItemStack getItemStack(String dragonName,String displayName,int amount,String... lores){
        ItemStack is = ItemFacAPI.getItemStack(Material.MAGMA_CREAM,displayName,lores);
        is = DragonAPI.setItemSkin(is,dragonName);
        is.setAmount(amount);
        return is;
    }
}
