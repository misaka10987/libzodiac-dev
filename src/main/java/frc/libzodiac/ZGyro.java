package frc.libzodiac;

public abstract class ZGyro {
    protected final int can_id;
    public double zero = 0;

    public ZGyro(int can_id) {
        this.can_id = can_id;
    }

    public abstract ZGyro init();

    protected abstract double get_yaw();

    protected abstract double get_pitch();

    protected abstract double get_roll();

    public double yaw() {
        return this.get_yaw() - this.zero;
    }

    public double pitch() {
        return this.get_pitch() - this.zero;
    }

    public double roll() {
        return this.get_roll() - this.zero;
    }
}
