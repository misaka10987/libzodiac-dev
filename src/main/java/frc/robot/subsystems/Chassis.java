package frc.robot.subsystems;

import frc.libzodiac.Zwerve;
import frc.libzodiac.hardware.Falcon;
import frc.libzodiac.hardware.Pigeon;
import frc.libzodiac.hardware.group.FalconSwerve;
import frc.libzodiac.util.PIDProfile;
import frc.libzodiac.util.Vec2D;

public class Chassis extends Zwerve {

    public static final Falcon[] speed = {
            new Falcon(10),
            new Falcon(11),
            new Falcon(8),
            new Falcon(5)
    };

    public static final Falcon.Servo[] angle = {
            new Falcon.Servo(3),
            new Falcon.Servo(2),
            new Falcon.Servo(12),
            new Falcon.Servo(6),
    };

    private static final FalconSwerve[] mods = {
            new FalconSwerve(speed[0], angle[0]),
            new FalconSwerve(speed[1], angle[1]),
            new FalconSwerve(speed[2], angle[2]),
            new FalconSwerve(speed[3], angle[3]),
    };

    private static final Pigeon gyro = new Pigeon(0);

    /**
     * Creates a new Chassis.
     */
    public Chassis() {
        super(mods, gyro, new Vec2D(114, 114));
        final var v = new PIDProfile(0.15, 0, 0);
        final var a = new PIDProfile(0.05, 0.001, 0);
        // Mod I.
        mods[0].speed_motor.set_pid(v);
        mods[0].angle_motor.set_pid(a);
        // Mod II.
        mods[1].speed_motor.set_pid(v);
        mods[1].angle_motor.set_pid(a);
        // Mod III.
        mods[2].speed_motor.set_pid(v);
        mods[2].angle_motor.set_pid(a);
        // Mod IV.
        mods[3].speed_motor.set_pid(v);
        mods[3].angle_motor.set_pid(a);
    }

    @Override
    protected Zwerve opt_init() {
        gyro.init();
        gyro.reset("yaw");
        return this;
    }
}
