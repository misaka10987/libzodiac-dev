// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.libzodiac.Zwerve;
import frc.libzodiac.hardware.Pigeon;
import frc.libzodiac.hardware.group.FalconSwerve;

public class Chassis extends Zwerve {

    private static final FalconSwerve[] mods = {
            new FalconSwerve(1, 2),
            new FalconSwerve(3, 4),
            new FalconSwerve(5, 6),
            new FalconSwerve(7, 8),
    };

    private static final Pigeon gyro = new Pigeon(0);

    /**
     * Creates a new Chassis.
     */
    public Chassis() {
        super(mods, gyro, 114, 114);
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
    }

    @Override
    protected Zwerve opt_init() {
        super.gyro.zero_yaw = 0;
        return this;
    }
}
