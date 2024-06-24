package frc.libzodiac.hardware.group;

import frc.libzodiac.ZEncoder;
import frc.libzodiac.hardware.Falcon;

/**
 * Falcon's built-in encoder resets its zero position to the position when power-on.
 * Therefore, a <code>TalonSRXEncoder</code> is used so that it is able to control the servo with absolute positions.
 */
public class FalconWithEncoder extends Falcon.Servo {
    protected final ZEncoder encoder;

    public FalconWithEncoder(int motor_can_id, ZEncoder encoder) {
        super(motor_can_id);
        this.encoder = encoder;
    }

    @Override
    public FalconWithEncoder init() {
        super.init();
        this.encoder.init();
        return this;
    }

    /**
     * Set the zero position of this motor. Note that the position is relative to the absolute encoder, note Falcon.
     */
    @Override
    public FalconWithEncoder set_zero(double abs_zero) {
        this.encoder.zero = abs_zero;
        this.zero += this.get_pos() - this.encoder.get();
        return this;
    }
}
