package frc.libzodiac;

/**
 * A generic encoder.
 */
public abstract class ZEncoder {

    /**
     * The CAN ID.
     */
    public final int can_id;

    /**
     * Zero position of this encoder in raw units.
     */
    public double zero_pos;

    public ZEncoder(int can_id) {
        this.can_id = can_id;
    }

    /**
     * Initialize this encoder.
     */
    public abstract ZEncoder init();

    /**
     * Configures the zero position of the encoder.
     */
    public ZEncoder set_zero(double pos) {
        this.zero_pos = pos;
        return this;
    }

    /**
     * Get the current position in raw units.
     */
    public abstract double get_raw();

    /**
     * Get the current position.
     */
    public abstract double get();
}
