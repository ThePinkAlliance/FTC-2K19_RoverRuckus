package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Calculations.Presets;

// Abstract Class to Define the Methods of the Collector Subsystem
public abstract class Collector {
    // Define Class Members
    public static double collect_command;
    public static double collect_rotate_target_position;
    public static Hardware robot = new Hardware();

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

    // Method to Rotate the Collector
    public static boolean rotate_to_position (double position) {
        // Define Commands
/*        if (robot.right_extend.getCurrentPosition() > Presets.EXTEND_SORT_POSITION || robot.right_lift.getCurrentPosition() > Presets.LIFT_SORT_POSITION) {
            if (position > Presets.COLLECTOR_TRAVEL_POSITION) {
                collect_rotate_target_position = Presets.COLLECTOR_TRAVEL_POSITION;
            } else {
                collect_rotate_target_position = position;
            }
        } else {
            collect_rotate_target_position = position;
        }
*/
        collect_rotate_target_position = position;

        // Set Servo Position
        robot.collector_rotate.setPosition(collect_rotate_target_position);

        // Return Value
        return robot.collector_rotate.getPosition() == collect_rotate_target_position;
    }

    // Method for Stopping the Collector
    public static boolean collect_stop () {
        // Set Motor Power
        robot.collect.setPower(0);

        // Return Value
        return robot.collect.getPower() == 0;
    }
}