package top.simba1949.util;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class ColorUtils {

    private ColorUtils() {
    }

    /**
     * 16进制颜色转换成 RGB
     * @param hexColor
     * @return
     */
    public static byte[] hexColorToBytes(int hexColor) {
        byte[] rgb = new byte[4];

        int transparency = (hexColor & 0xFF000000) >> 24; // 透明度
        int red = (hexColor & 0x00FF0000) >> 16; // 红色
        int green = (hexColor & 0x0000FF00) >> 8; // 绿色
        int blue = hexColor & 0x000000FF; // 蓝色

        rgb[0] = (byte) transparency;
        rgb[1] = (byte) red;
        rgb[2] = (byte) green;
        rgb[3] = (byte) blue;

        return rgb;
    }
}