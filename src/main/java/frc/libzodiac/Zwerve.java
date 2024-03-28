// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.libzodiac;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.libzodiac.util.Vec2D;

public abstract class Zwerve extends SubsystemBase {

  private final double length;
  private final double width;

  private double radius() {
    return Math.hypot(this.length, this.width) / 2;
  }

  public double output = 1;

  public static interface Module {

    public Module init();

    public Module go(Vec2D velocity);

  }

  /**
   * <table>
   * <thead>
   * <tr>
   * <th>Galaxy</th>
   * <th>Index</th>
   * </tr>
   * </thead><tbody>
   * <tr>
   * <td>I</td>
   * <td>0</td>
   * </tr>
   * <tr>
   * <td>II</td>
   * <td>1</td>
   * </tr>
   * <tr>
   * <td>III</td>
   * <td>2</td>
   * </tr>
   * <tr>
   * <td>IV</td>
   * <td>3</td>
   * </tr>
   * </tbody>
   * </table>
   */
  protected final Module[] module;

  /**
   * Creates a new Zwerve.
   * 
   * For modules:
   * <table>
   * <thead>
   * <tr>
   * <th>Galaxy</th>
   * <th>Index</th>
   * </tr>
   * </thead><tbody>
   * <tr>
   * <td>I</td>
   * <td>0</td>
   * </tr>
   * <tr>
   * <td>II</td>
   * <td>1</td>
   * </tr>
   * <tr>
   * <td>III</td>
   * <td>2</td>
   * </tr>
   * <tr>
   * <td>IV</td>
   * <td>3</td>
   * </tr>
   * </tbody>
   * </table>
   * suppose the robot heads positive x direction.
   */
  public Zwerve(Module[] modules, double length, double width) {
    this.module = modules;
    this.length = length;
    this.width = width;
  }

  public Zwerve init() {
    for (Module i : this.module) {
      i.init();
    }
    return this;
  }

  /**
   * Optional initializations you would like to automatically invoke.
   */
  public abstract Zwerve init_opt();

  public Zwerve go_previous(Vec2D velocity, double omega) {
    var x = velocity.x;
    var y = velocity.y;
    var l = this.length;
    var w = this.width;
    var r = this.radius();
    var a = y - omega * l / r;
    var b = y + omega * l / r;
    var c = x - omega * w / r;
    var d = x + omega * w / r;
    var v1 = new Vec2D.Polar(Math.hypot(b, c), Math.atan2(b, c));
    var v2 = new Vec2D.Polar(Math.hypot(b, d), Math.atan2(b, d));
    var v3 = new Vec2D.Polar(Math.hypot(a, d), Math.atan2(a, d));
    var v4 = new Vec2D.Polar(Math.hypot(a, c), Math.atan2(a, c));
    var max = Math.max(Math.max(v1.r, v2.r), Math.max(v3.r, v4.r));
    if (max > 1) {
      v1 = v1.div(max);
      v2 = v2.div(max);
      v3 = v3.div(max);
      v4 = v4.div(max);
    }
    this.module[0].go(v2.into());
    this.module[1].go(v3.into());
    this.module[2].go(v4.into());
    this.module[3].go(v1.into());
    return this;
  }

  public Zwerve go(Vec2D velocity, double omega) {
    var l = this.length / 2;
    var w = this.width / 2;
    var vel = velocity;
    var pi2 = Math.PI / 2;
    var vt = omega * this.radius();
    Vec2D v[] = {
        new Vec2D(l, w).rot(pi2).with_r(vt).add(vel),
        new Vec2D(-l, w).rot(pi2).with_r(vt).add(vel),
        new Vec2D(-l, -w).rot(pi2).with_r(vt).add(vel),
        new Vec2D(l, -w).rot(pi2).with_r(vt).add(vel),
    };
    var max = v[1].max(v[2]).max(v[3]).max(v[4]).r();
    if (max > 1)
      for (var i : v)
        i = i.div(max);
    for (int i = 0; i < 4; i++)
      this.module[i].go(v[i].mul(output));
    return this;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static class Drive extends ZCommand {

    private final Zwerve chassis;
    private final Zoystick vel_ctrl;
    private final Joystick rot_ctrl;

    private boolean inv_vx = false;
    private boolean inv_vy = false;
    private boolean inv_rot = false;

    /** Creates a new Drive. */
    public Drive(Zwerve chassis, Zoystick vel, Zoystick rot) {
      // Use addRequirements() here to declare subsystem dependencies.
      this.chassis = this.require(chassis);
      this.vel_ctrl = vel;
      this.rot_ctrl = rot;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      var v_x = this.vel_ctrl.x();
      var v_y = this.vel_ctrl.y();
      var omega = this.rot_ctrl.getRawAxis(0);
      if (this.inv_vx)
        v_x = -v_x;
      if (this.inv_vy)
        v_y = -v_y;
      if (this.inv_rot)
        omega = -omega;
      if (v_x < 1e-3)
        v_x = 0;
      if (v_y < 1e-3)
        v_y = 0;
      if (omega < 1e-3)
        omega = 0;
      this.chassis.go(new Vec2D(v_x, v_y), omega);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }

    public Drive inv(boolean vx, boolean vy, boolean rot) {
      this.inv_vx = vx;
      this.inv_vy = vy;
      this.inv_rot = rot;
      return this;
    }
  }

  public Drive control(Zoystick vel, Zoystick rot) {
    return new Drive(this, vel, rot);
  }
}
