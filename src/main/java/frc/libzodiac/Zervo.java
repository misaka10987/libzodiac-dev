package frc.libzodiac;

/**
 * Note: the method Zmotor.go **MUST** be overrided.
 */
public interface Zervo {

    public Zervo set_zero(double zero);

    public double get_zero();

    public double get_pos();

    /**
     * Convaries the servo motor and returns a generic motor.
     */
    public ZMotor motor();
}
