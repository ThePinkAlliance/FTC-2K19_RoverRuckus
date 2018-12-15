package org.firstinspires.PinkCode.Calculations;

import com.qualcomm.robotcore.util.Range;

// Abstract Class to Find the Motor Commands for Each Subsystem Using a PD
public abstract class PD {
    // Use a PD to Determine the Extend Motor Command
    public static double get_extend_command (double error, double currentVel) {
        // Calculate Motor Command
        double extend_command;
        extend_command = (Presets.EXTEND_Kp * error) - (Presets.EXTEND_Kd * currentVel);
        extend_command = Range.clip(extend_command, Presets.EXTEND_MIN_POWER, Presets.EXTEND_MAX_POWER);

        // Return Value
        return extend_command;
    }

    // Use a PD to Determine the Lift Motor Command
    public static double get_lift_command (double error, double currentVel) {
        // Define Variables
        double lift_command;

        // Calculate Motor Command
        lift_command = (Presets.LIFT_Kp * error) - (Presets.LIFT_Kd * currentVel);
        lift_command = Range.clip(lift_command, Presets.LIFT_MIN_POWER, Presets.LIFT_MAX_POWER);

        // Return Value
        return lift_command;
    }

    // Use a PD to Determine the Lift Motor Command
    public static double get_collector_rotate_command (double error, double currentVel) {
        // Define Variables
        double collector_rotate_command;

        // Calculate Motor Command
        collector_rotate_command = (Presets.COLLECTOR_ROTATE_Kp * error) - (Presets.COLLECTOR_ROTATE_Kd * currentVel);
        collector_rotate_command = Range.clip(collector_rotate_command, Presets.COLLECTOR_ROTATE_MIN_POWER, Presets.COLLECTOR_ROTATE_MAX_POWER);

        // Return Value
        return collector_rotate_command;
    }
}
