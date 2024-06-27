package frc.libzodiac;

/**
 * Note: the method Zmotor.go **MUST** be overrided.
 */
public interface Zervo {

    /**
     * Configures the zero position of the motor.
     */
    public Zervo set_zero(double zero);

    /**
     * Gets the zero position of the motor.
     */
    public double get_zero();

    /**
     * Gets the current position.
     */
    public double get();

    /**
     * Convaries the servo motor and returns a generic motor.
     */
    public ZMotor motor();
}
