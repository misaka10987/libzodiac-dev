package frc.libzodiac;

public final class Constant {
    // 2023.6.24: going to measure
    /**
     * How many radians a raw <code>TalonSRX</code>'s unit is equal to.
     */
    public static double TALONSRX_ENCODER_UNIT = 2 * Math.PI / 4096;

    /**
     * Falcon uses rotation as its position unit.
     */
    public static double FALCON_POSITION_UNIT = 2 * Math.PI;
}
