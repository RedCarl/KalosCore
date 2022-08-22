package red.kalos.core.util.api;

import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.spawning.LegendarySpawner;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import red.kalos.core.util.ColorParser;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * @Author: carl0
 * @DATE: 2022/8/3 16:33
 */
public class KalosUtil {

    public static @NotNull String multipleString(@NotNull String string, int multiple) {
        StringBuilder stringBuilder = new StringBuilder();

        for(byte b = 0; b < multiple; ++b) {
            stringBuilder.append(string);
        }

        return stringBuilder.toString();
    }

    public static String decimalFormat(double d, int length) {
        DecimalFormat decimalFormat;
        if (length <= 0) {
            decimalFormat = new DecimalFormat("########");
        } else {
            decimalFormat = new DecimalFormat("########." + multipleString("#", length));
        }

        return decimalFormat.format(d);
    }

    public static long getLegendarySpawnerTime(){
        return getTime();
    }

    public static AbstractSpawner abstractSpawner() {
        return PixelmonSpawning.coordinator.getSpawner("legendary");
    }

    public static long getTime() {
        if (abstractSpawner() instanceof LegendarySpawner) {
            long l1 = ((LegendarySpawner)abstractSpawner()).nextSpawnTime;
            long l2 = System.currentTimeMillis();
            return Double.valueOf((l1 - l2) / 1000.0D).intValue();
        }
        return -1L;
    }

    /**
     * 检测玩家背包是否没有位置了
     * @param player 玩家
     * @return 是否
     */
    public static boolean isInventoryNoNull(Player player, ItemStack itemStack){
        boolean varOn = true;
        for (int i = 0; i < 36; i++) {
            if (player.getInventory().getItem(i) == null) {
                varOn = false;
                break;
            }
        }
        if (varOn){
            for (int i = 0; i < 36; i++) {
                if (player.getInventory().getItem(i).getType() == itemStack.getType()) {
                    if (64-player.getInventory().getItem(i).getAmount()>=itemStack.getAmount()){
                        varOn = false;
                        break;
                    }
                }
            }
        }
        if (varOn){
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您的背包没有多余的位置来存放物品,请整理背包后再试!"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
            return true;
        }
        return false;
    }


    private static Pattern pattern = Pattern.compile("^[1-9]\\d*$");
    /**
     * 是否是正整数，前面不以0开头
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(str == null || "".equals(str.trim())){
            return false;
        }
        str = str.trim();
        // 包括0，不想包括0则去掉
//        if ("0".equals(str)){
//            return true;
//        }
        return pattern.matcher(str).matches();
    }

}
