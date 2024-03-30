// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.libzodiac.Zoystick;
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

    public Command chassis_ctrl() {
        return chassis
                .control(drive, ctrl)
                .inv(false, false, false);
    }

    public Zoystick drive = new Zoystick(0)
            .map(0, "X")
            .map(1, "Y");
    public Zoystick ctrl = new Zoystick(1);

    public RobotContainer init() {
        chassis.init();
        return this;
    }

    // Replace with CommandPS4Controller or CommandJoystick if needed
    // private final CommandXboxController m_driverController =
    // new CommandXboxController(OperatorConstants.kDriverControllerPort);

    @Deprecated
    public void driveRumble() {
        drive.setRumble(RumbleType.kLeftRumble, 1);
        drive.setRumble(RumbleType.kRightRumble, 1);
        Timer.delay(0.1);
        drive.setRumble(RumbleType.kLeftRumble, 0);
        drive.setRumble(RumbleType.kRightRumble, 0);
        Timer.delay(0.1);
    }

    @Deprecated
    public void ctrlRumble() {
        ctrl.setRumble(RumbleType.kLeftRumble, 1);
        ctrl.setRumble(RumbleType.kRightRumble, 1);
        Timer.delay(0.1);
        ctrl.setRumble(RumbleType.kLeftRumble, 0);
        ctrl.setRumble(RumbleType.kRightRumble, 0);
        Timer.delay(0.1);
    }

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
     * {@link
     * CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or
     * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        // new Trigger(m_exampleSubsystem::exampleCondition)
        // .onTrue(new ExampleCommand(m_exampleSubsystem));

        // Schedule `exampleMethodCommand` when the Xbox controller's B button is
        // pressed,
        // cancelling on release.
        // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    }

}
