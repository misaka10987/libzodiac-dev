package frc.libzodiac.hardware.group;

import frc.libzodiac.Util;
import frc.libzodiac.ZmartDash;
import frc.libzodiac.Zwerve.Module;
import frc.libzodiac.hardware.Falcon;
import frc.libzodiac.util.Vec2D;

public final class FalconSwerve implements Module, ZmartDash {
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
        this.debug("go", "" + velocity);
        var angle = velocity.theta();
        var speed = velocity.r();
        var curr = this.angle_motor.get();
        var dtheta = angle - curr;
        if (Math.abs(Util.mod_pi(dtheta)) > Math.PI / 2) {
            angle = -angle;
            speed = -speed;
        }
        this.angle_motor.go(curr + Util.mod_pi(angle - curr));
        this.speed_motor.go(speed);
        return this;
    }

    @Override
    public String key() {
        return "FalconSwerve(" + this.speed_motor.key() + ",...)";
    }
}
