package frc.libzodiac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Inheritd from WPILib's <code>Joystick</code>,
 * added some utilities to enhance using experience.
 */
public class Zoystick extends Joystick {

    /**
     * Button names to button IDs mappings.
     */
    public HashMap<String, Integer> key_map;

    /**
     * Defines the collection of axis to invert.
     */
    public HashSet<Integer> inv;

    /**
     * Whether to invert x-axis.
     */
    public boolean inv_x = false;
    /**
     * Whether to invert y-axis.
     */
    public boolean inv_y = false;

    /**
     * Function to pre-process the input value of joystick.
     */
    public Function<Double, Double> filter_fn = (x) -> x;

    public Zoystick(int port) {
        super(port);
    }

    /**
     * Set the output invert status of an axis.
     */
    public Zoystick inv(int axis, boolean inv) {
        if (inv)
            this.inv.add(axis);
        else
            this.inv.remove(axis);
        return this;
    }

    /**
     * Set an axis to be inverted.
     */
    public Zoystick inv(int axis) {
        return this.inv(axis, true);
    }

    /**
     * Get the filtered input of an axis.
     */
    public double axis(int axis) {
        var a = this.filter_fn.apply(this.getRawAxis(axis));
        if (this.inv.contains(axis))
            a = -a;
        return a;
    }

    /**
     * Gets the x-axis of the joystick.
     */
    public double x() {
        return this.axis(0);
    }

    /**
     * Gets the y-axis of the joystick.
     */
    public double y() {
        return this.axis(1);
    }

    /**
     * Set the input filter function.
     */
    public Zoystick set_filter(Function<Double, Double> f) {
        this.filter_fn = f;
        return this;
    }

    /**
     * Gives the specified button ID a name for future visits.
     */
    public Zoystick map(int button, String name) {
        this.key_map.put(name, button);
        return this;
    }

    /**
     * Get the button status with the specified name.
     */
    public boolean button(String button) {
        return this.getRawButton(this.key_map.get(button));
    }

    /**
     * Similar to <code>button</code>,
     * but returns true only if the button is pressed from the release status since
     * last call.
     */
    public boolean button_pressed(String button) {
        return this.getRawButtonPressed(this.key_map.get(button));
    }

    /**
     * Similar to <code>button</code>,
     * but returns true only if the button is released from pressed status since
     * last call.
     */
    public boolean button_released(String button) {
        return this.getRawButtonReleased(this.key_map.get(button));
    }

    /**
     * Filters the input value as a parabola, e.g. -0.5 -> -0.25.
     */
    public static final Function<Double, Double> quad_filter = (x) -> x / Math.abs(x) * x * x;

    /**
     * Filters the input value with a threshold, inputs with absolute values less
     * than it will be ignored.
     */
    public static Function<Double, Double> thre_filter(double thre) {
        Function<Double, Double> lambda = (x) -> {
            if (Math.abs(x) > thre) {
                return x;
            }
            return 0.0;
        };
        return lambda;
    }
}
