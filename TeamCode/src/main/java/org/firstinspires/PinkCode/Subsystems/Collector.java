package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Presets;

// Abstract Class to Define the Methods of the Collector Subsystem
public abstract class Collector extends Subsystem{

    // Method for Collecting
    public static boolean collect() {
        // Define Commands
        collect_command = Presets.COLLECTOR_COLLECT_POWER;

        // Return Value
        return true;
    }

    // Method for Ejecting
    public static boolean eject() {
        // Define Commands
        collect_command = Presets.COLLECTOR_EJECT_POWER;

        // Return Value
        return true;
    }

    // Method to Rotate the Collector
    public static boolean rotate_to_position(double position) {
        // Define Commands
        collect_rotate_target_position = position;

        // Return Value
        return true;
    }

    // Method for Stopping the Collector
    public static boolean collect_stop() {
        // Define Commands
        collect_command = 0;

        // Return Value
        return true;
    }
}