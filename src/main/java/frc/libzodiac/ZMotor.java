package frc.libzodiac;

import java.util.HashMap;

import frc.libzodiac.util.PIDProfile;

public abstract class ZMotor {

    public PIDProfile pid;

    public HashMap<String, Double> profile;

    protected double profile(String name) {
        var opt = this.profile.get(name);
        if (opt == null) {
            return 0;
        } else {
            return opt;
        }
    }

    public abstract ZMotor init();

    protected abstract ZMotor apply_pid();

    public ZMotor set_pid(PIDProfile pid) {
        this.pid = pid;
        return this.apply_pid();
    };

    public ZMotor set_pid(double k_p, double k_i, double k_d) {
        return this.set_pid(new PIDProfile(k_p, k_i, k_d));
    }

    public abstract ZMotor shutdown();

    public abstract ZMotor stop();

    public abstract ZMotor go(String profile);

    public abstract ZMotor go(double raw_unit);
}