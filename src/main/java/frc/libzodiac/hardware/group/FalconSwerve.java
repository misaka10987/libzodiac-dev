package frc.libzodiac.hardware.group;

import frc.libzodiac.Util;
import frc.libzodiac.Zwerve.Module;
import frc.libzodiac.hardware.Falcon;
import frc.libzodiac.util.Vec2D;

public class FalconSwerve implements Module {
    public final Falcon speed_motor;
    public final FalconWithEncoder angle_motor;

    public FalconSwerve(Falcon speed_motor, FalconWithEncoder angle_motor) {
        this.speed_motor = speed_motor;
        this.angle_motor = angle_motor;
    }

    @Override
    public Module init() {
        this.speed_motor.init();
        this.angle_motor.init();
        return this;
    }

    @Override
    public Module go(Vec2D velocity) {
        var dtheta = velocity.theta() - this.angle_motor.get_pos();
        var angle = velocity.theta();
        var speed = velocity.r();
        if (Math.abs(dtheta) > Math.PI / 2) {
            angle = -angle;
            speed = -speed;
        }
        var curr = this.angle_motor.get_pos();
        this.angle_motor.go(curr + Util.mod_pi(angle));
        this.speed_motor.go(speed);
        return this;
    }
}
