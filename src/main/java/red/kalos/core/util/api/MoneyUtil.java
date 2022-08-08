package red.kalos.core.util.api;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author: carl0
 * @DATE: 2022/7/16 13:58
 */
public class MoneyUtil {
    //数值转换
    public static String toNumber(float number) {
        String str = String.valueOf(number);
        if (number >= 0 && number < 1000) {
            BigDecimal b = new BigDecimal(number);
            str = String.valueOf(b.setScale(2, RoundingMode.DOWN).doubleValue());
        } else if (number >= 1000 && number < 10000) {
            double num = number / 1000;
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(2, RoundingMode.DOWN).doubleValue();
            str = f1 + " K";
        } else if (number >= 10000 && number < 1000000){
            double num = number / 10000;
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(2, RoundingMode.DOWN).doubleValue();
            str = f1 + " W";
        } else if (number >= 1000000 && number < 1000000000){
            double num = number / 1000000;
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(2, RoundingMode.DOWN).doubleValue();
            str = f1 + " M";
        } else if (number >= 1000000000 && number < 1000000000000f){
            double num = number / 1000000000;
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(2, RoundingMode.DOWN).doubleValue();
            str = f1 + " B";
        } else if (number >= 1000000000000f && number < 1000000000000000f){
            double num = number / 1000000000000f;
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(2, RoundingMode.DOWN).doubleValue();
            str = f1 + " T";
        } else if (number >= 1000000000000000f){
            double num = number / 1000000000000000f;
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(2, RoundingMode.DOWN).doubleValue();
            str = f1 + " Q";
        }
        return str;
    }
}
