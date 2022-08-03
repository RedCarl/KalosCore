package kim.pokemon.util.api;

/**
 * @Author: carl0
 * @DATE: 2022/7/15 0:18
 */
public class DateUtil {
    /**
     * 秒格式化
     */
    public static String getDate(long date){
        if (date<60) {
            return "§c"+date+" §7秒";
        }else if (date>60&&date<3600) {
            long m = date/60;
            long s = date%60;
            return "§c"+m+" §7分"+" §c"+s+" §7秒";
        }else {
            long h = date/3600;
            long m = (date%3600)/60;
            long s = (date%3600)%60;
            return "§c"+h+" §7小时"+" §c"+m+" §7分"+" §c"+s+" §7秒";
        }
    }
}
