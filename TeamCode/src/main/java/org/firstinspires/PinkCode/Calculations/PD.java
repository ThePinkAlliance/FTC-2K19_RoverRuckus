package org.firstinspires.PinkCode.Calculations;

import com.qualcomm.robotcore.util.Range;

// Abstract Class to Find the Motor Commands for Each Subsystem Using a PD
public abstract class PD {
    // Define Class Members
    public static double extend_command;
    public static double lift_command;

    // Use a PD to Determine the Extend Motor Command
    public static double get_extend_command (double error, double currentVel) {
        // Calculate Motor Command
        extend_command = (Presets.EXTEND_Kp * error) - (Presets.EXTEND_Kd * currentVel);
        extend_command = Range.clip(extend_command, Presets.EXTEND_MIN_POWER, Presets.EXTEND_MAX_POWER);

        // Return Value
        return extend_command;
    }

    // Use a PD to Determine the Lift Motor Command
    public static double get_lift_command (double error, double currentVel) {
        // Calculate Motor Command
        lift_command = (Presets.LIFT_Kp * error) - (Presets.LIFT_Kd * currentVel);
        lift_command = Range.clip(lift_command, Presets.LIFT_MIN_POWER, Presets.LIFT_MAX_POWER);

        // Return Value
        return lift_command;
    }
}
