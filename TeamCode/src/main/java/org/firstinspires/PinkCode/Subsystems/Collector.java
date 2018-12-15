package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Calculations.Presets;
import org.firstinspires.PinkCode.Calculations.PD;
import com.qualcomm.robotcore.util.Range;


// Abstract Class to Define the Methods of the Collector Subsystem
public abstract class Collector extends Subsystem{

    static double collect_rotate_previous_position = 0;

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
        double collect_rotate_target_position;
        double collect_rotate_position_error;
        double collect_rotate_current_position;
        double collect_rotate_speed;

        // Define Commands
        collect_rotate_target_position = position;
        collect_rotate_current_position = robot.collector_rotate.getCurrentPosition();
        collect_rotate_position_error = collect_rotate_target_position - collect_rotate_current_position;
        collect_rotate_speed = collect_rotate_current_position - collect_rotate_previous_position;
        collect_rotate_previous_position = collect_rotate_current_position;
        collector_rotate_command = PD.get_collector_rotate_command(collect_rotate_position_error, collect_rotate_speed);
    }
    public static void rotate_by_command(double power) {
        collector_rotate_command = Range.clip(power, -0.7, 0.7);
        collect_rotate_previous_position = robot.collector_rotate.getCurrentPosition();
    }

    //    // Method for Holding the Collector Rotate Position
    public static void collect_rotate_hold() {
        double collect_rotate_position_error;
        // Define Commands
        collect_rotate_position_error = collect_rotate_previous_position - robot.collector_rotate.getCurrentPosition();
        collector_rotate_command = PD.get_collector_rotate_command(collect_rotate_position_error, 0);
    }

    // Method for Stopping the Collector
    public static void collect_stop() {
        // Define Commands
        collect_command = 0;
    }
}