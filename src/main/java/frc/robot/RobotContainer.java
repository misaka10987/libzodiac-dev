// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.libzodiac.Zambda;
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
            xbox.ly()
                    .inverted()
                    .map(Axis.QUAD_FILTER)
                    .threshold(.1),

            xbox.lx()
                    .inverted()
                    .map(Axis.QUAD_FILTER)
                    .threshold(.1),

            xbox.rx()
                    .inverted()
                    .threshold(.1));

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        this.xbox.x().on_press(new Zambda(this.chassis, () -> {
            this.chassis.mod_reset();
            // this.xbox.rumble();
        }));
    }

    public RobotContainer init() {
        chassis.init();
        return this;
    }

}
