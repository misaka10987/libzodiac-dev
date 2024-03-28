package frc.libzodiac;

public abstract class ZEncoder {

    public final int can_id;

    public double zero_pos;

    public ZEncoder(int can_id) {
        this.can_id = can_id;
    }

    public abstract ZEncoder init();

    public ZEncoder set_zero(double pos) {
        this.zero_pos = pos;
        return this;
    }

    public abstract double get_raw();

    public abstract double get();
}
