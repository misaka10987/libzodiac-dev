package frc.libzodiac;

/**
 * Common utilities.
 */
public class Util {
    /**
     * Convert degrees to radians.
     */
    public static double rad(double deg) {
        return deg / 180 * Math.PI;
    }

    /**
     * Convert radians to degrees.
     */
    public static double deg(double rad) {
        return rad / Math.PI * 180;
    }

    /**
     * Extends the modulo operation to R.
     * <p>
     * THe definition here:
     * <p>
     * a and b are congruent modulo c, if and only if (a-b)/c is integer.
     * 
     * @return In (-mod,mod), and NaN for NaN and Infinite parameters.
     */
    public static double mod(Double num, double mod) {
        if (num.isInfinite() || num.isNaN())
            return Double.NaN;
        var ans = (int) (Math.abs(num) / mod);
        if (ans < 0)
            return num + ans * mod;
        return num - ans * mod;
    }

    /**
     * Convert a radian into (-pi,pi).
     */
    public static double mod_pi(double rad) {
        return mod(rad, Math.PI);
    }

}
