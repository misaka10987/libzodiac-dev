// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.libzodiac.ui.Axis;
import frc.libzodiac.ui.Xbox;
import frc.robot.subsystems.Chassis;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

    public Chassis chassis = new Chassis();

    public Xbox xbox = new Xbox(0);

    public Command drive = chassis.drive(
            xbox.lx()
                    .threshold(.01)
                    .map(Axis.QUAD_FILTER),
            xbox.ly()
                    .threshold(.01)
                    .map(Axis.QUAD_FILTER),
            xbox.rx()
                    .threshold(.01));

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
    }

    public RobotContainer init() {
        chassis.init();
        return this;
    }

}
