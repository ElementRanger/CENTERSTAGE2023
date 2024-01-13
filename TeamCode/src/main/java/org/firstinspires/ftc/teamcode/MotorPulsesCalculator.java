package org.firstinspires.ftc.teamcode;

public class MotorPulsesCalculator {
    // Constants
    private static final double WHEEL_DIAMETER = 3.75; // in inches
    private static final double PULSES_PER_REVOLUTION = 537.7;

    /**
     * Calculate pulses.
     *
     * @param distance the distance
     * @return the number of pulses
     */
    public static int calculatePulses(double distance) {
        double circumference = Math.PI * WHEEL_DIAMETER;
        double rotations = distance / circumference;
        int pulses = (int) (rotations * PULSES_PER_REVOLUTION);
        return pulses;
    }
}
