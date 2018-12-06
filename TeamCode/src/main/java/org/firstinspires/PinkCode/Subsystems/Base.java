package org.firstinspires.PinkCode.Subsystems;

// Abstract Class to Define the Methods of the Base Subsystem
public abstract class Base extends Subsystem {
    // Method for Driving in Tank Drive using Commands
    public static void drive_by_command(double right, double left) {
        // Define Commands
        right_wheel_command = right;
        left_wheel_command = left;
    }

    // Method for Stopping the Drive Train
    public static void drive_stop() {
        // Define Commands
        right_wheel_command = 0;
        left_wheel_command = 0;
    }
}