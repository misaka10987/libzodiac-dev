package frc.libzodiac.hardware;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.libzodiac.Constant;
import frc.libzodiac.ZEncoder;

public class TalonSRXEncoder extends ZEncoder {

    public TalonSRXEncoder(int can_id) {
        super(can_id);
    }

    protected TalonSRX encoder;

    @Override
    public ZEncoder init() {
        this.encoder = new TalonSRX(this.can_id);
        return this;
    }

    @Override
    public double get_raw() {
        return this.encoder.getSelectedSensorPosition();
    }

    @Override
    public double get_rad() {
        return this.get_raw() / Constant.TALONSRX_ENCODER_UNIT;
    }

}
