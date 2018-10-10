package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.Presets;

// Abstract Class to Define the Methods of the Collector Subsystem
public abstract class Collector {
    // Define Class Members
    public static double collect_command;
    private static Hardware robot = new Hardware();

    // Method for Collecting
    public static boolean collect () {
        // Define Commands
        collect_command = Presets.COLLECTOR_COLLECT_POWER;

        // Set Motor Power
        robot.collect.setPower(collect_command);

        // Return Value
        return collect_command == 1;
    }

    // Method for Ejecting
    public static boolean eject () {
        // Define Commands
        collect_command = Presets.COLLECTOR_EJECT_POWER;

        // Set Motor Power
        robot.collect.setPower(collect_command);

        // Return Value
        return collect_command == -1;
    }

    // Default Method for Collector
    public static boolean hold () {
        // Define Commands
        collect_command = Presets.COLLECTOR_HOLD_POWER;

        // Set Motor Power
        robot.collect.setPower(collect_command);

        // Return Value
        return collect_command == 0;
    }

    // Method for Stopping the Collector
    public static boolean collect_stop () {
        // Set Motor Power
        robot.collect.setPower(0);

        // Return Value
        return robot.collect.getPower() == 0;
    }
}