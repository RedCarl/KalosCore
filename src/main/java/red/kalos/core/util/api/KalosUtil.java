package red.kalos.core.util.api;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

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
}
