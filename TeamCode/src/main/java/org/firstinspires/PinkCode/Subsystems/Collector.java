package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Calculations.Presets;

// Abstract Class to Define the Methods of the Collector Subsystem
public abstract class Collector extends Subsystem{
    // Method for Collecting
    public static void collect() {
        // Define Commands
        collect_command = Presets.COLLECTOR_COLLECT_POWER;
    }

    // Method for Ejecting
    public static void eject() {
        // Define Commands
        collect_command = Presets.COLLECTOR_EJECT_POWER;
    }

    // Method to Rotate the Collector
    public static void rotate_to_position(double position) {
        // Define Commands
        collect_rotate_target_position = position;
    }

    // Method for Stopping the Collector
    public static void collect_stop() {
        // Define Commands
        collect_command = 0;
    }
}