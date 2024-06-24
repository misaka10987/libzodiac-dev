// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.libzodiac.ZEncoder;
import frc.libzodiac.Zwerve;
import frc.libzodiac.hardware.Falcon;
import frc.libzodiac.hardware.Pigeon;
import frc.libzodiac.hardware.TalonSRXEncoder;
import frc.libzodiac.hardware.group.FalconSwerve;
import frc.libzodiac.hardware.group.FalconWithEncoder;
import frc.libzodiac.util.Vec2D;

public class Chassis extends Zwerve {

    public static final Falcon[] speed_motor = {
            new Falcon(1),
            new Falcon(2),
            new Falcon(3),
            new Falcon(4)
    };

    public static final ZEncoder[] swervemod_encoder = {
            new TalonSRXEncoder(9).set_zero(0),
            new TalonSRXEncoder(10).set_zero(0),
            new TalonSRXEncoder(11).set_zero(0),
            new TalonSRXEncoder(12).set_zero(0),
    };

    public static final FalconWithEncoder[] angle_motor = {
            new FalconWithEncoder(5, swervemod_encoder[0]),
            new FalconWithEncoder(6, swervemod_encoder[1]),
            new FalconWithEncoder(7, swervemod_encoder[2]),
            new FalconWithEncoder(8, swervemod_encoder[3]),
    };

    private static final FalconSwerve[] mods = {
            new FalconSwerve(speed_motor[0], angle_motor[0]),
            new FalconSwerve(speed_motor[1], angle_motor[1]),
            new FalconSwerve(speed_motor[2], angle_motor[2]),
            new FalconSwerve(speed_motor[3], angle_motor[3]),
    };

    private static final Pigeon gyro = new Pigeon(0);

    /**
     * Creates a new Chassis.
     */
    public Chassis() {
        super(mods, gyro, new Vec2D(114, 114));
        // Mod I.
        mods[0].speed_motor.set_pid(0, 0, 0);
        mods[0].angle_motor.set_pid(0, 0, 0);
        mods[0].angle_motor.set_zero(0);
        // Mod II.
        mods[1].speed_motor.set_pid(0, 0, 0);
        mods[1].angle_motor.set_pid(0, 0, 0);
        mods[1].angle_motor.set_zero(0);
        // Mod III.
        mods[2].speed_motor.set_pid(0, 0, 0);
        mods[2].angle_motor.set_pid(0, 0, 0);
        mods[2].angle_motor.set_zero(0);
        // Mod IV.
        mods[3].speed_motor.set_pid(0, 0, 0);
        mods[3].angle_motor.set_pid(0, 0, 0);
        mods[3].angle_motor.set_zero(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        super.periodic();
    }

    @Override
    protected Zwerve opt_init() {
        super.gyro.zero_yaw = 0;
        return this;
    }
}
