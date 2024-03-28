package frc.libzodiac;

import java.util.HashMap;
import java.util.HashSet;

import edu.wpi.first.wpilibj.Joystick;

public class Zoystick extends Joystick {

    public HashMap<String, Integer> key_map;

    public HashSet<Integer> inv;

    public boolean inv_x = false;
    public boolean inv_y = false;

    /**
     * Threshold for axis values to filter minor errors.
     */
    public double axis_thre = 0;

    public Zoystick(int port) {
        super(port);
    }

    public Zoystick inv(int axis, boolean inv) {
        if (inv)
            this.inv.add(axis);
        else
            this.inv.remove(axis);
        return this;
    }

    public Zoystick inv(int axis) {
        return this.inv(axis, true);
    }

    public Zoystick thre(double th) {
        this.axis_thre = th;
        return this;
    }

    public double axis(int axis) {
        var a = this.getRawAxis(axis);
        if (Math.abs(a) < this.axis_thre)
            return 0;
        if (this.inv.contains(axis))
            a = -a;
        return a;
    }

    public double x() {
        return this.axis(0);
    }

    public double y() {
        return this.axis(1);
    }

    public Zoystick map(int button, String name) {
        this.key_map.put(name, button);
        return this;
    }

    public boolean button(String button) {
        return this.getRawButton(this.key_map.get(button));
    }

    public boolean button_pressed(String button) {
        return this.getRawButtonPressed(this.key_map.get(button));
    }

    public boolean button_released(String button) {
        return this.getRawButtonReleased(this.key_map.get(button));
    }
}
