package org.firstinspires.ftc.teamcode;

public class StrafePulsesCalculator {
    // Constants
    private static final double WHEEL_DIAMETER = 3.75; // in inches
    private static final double PULSES_PER_REVOLUTION = 537.7;

    /**
     * Calculate pulses.
     *
     * @param distance the distance
     * @return the number of pulses
     */
    public static int calculateStrafePulses(double distance) {
        double circumference = Math.PI * WHEEL_DIAMETER;
        double rotations = distance / circumference;
        int pulses = (int) (rotations * PULSES_PER_REVOLUTION * 1);
        return pulses;
    }
}
