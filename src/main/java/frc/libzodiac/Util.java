package frc.libzodiac;

public class Util {
    public static double rad(double deg) {
        return deg / 180 * Math.PI;
    }

    public static double deg(double rad) {
        return rad / Math.PI * 180;
    }

    public static double mod(Double num, double mod) {
        if (num.isInfinite() || num.isNaN())
            return Double.NaN;
        var ans = (int) (Math.abs(num) / mod);
        if (ans < 0)
            return num + ans * mod;
        return num - ans * mod;
    }

    public static double mod_pi(double rad) {
        return mod(rad, Math.PI);
    }

}
