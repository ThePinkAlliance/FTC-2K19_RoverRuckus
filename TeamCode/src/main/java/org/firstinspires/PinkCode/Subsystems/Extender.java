package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Calculations.PD;
import org.firstinspires.PinkCode.Calculations.Presets;

import com.qualcomm.robotcore.util.Range;

// Abstract Class to Define the Methods of the Extender Subsystem
public abstract class Extender extends Subsystem{
    // Method for Extending Using Commands
    public static void extend_by_command(double command) {
        // Define Commands
        extend_command = command;
//
//        // Limit the motor power near the end of travel
//        if (command > 0){
//            extend_command = Range.clip(command, 0, ((Presets.EXTEND_MAX_POSITION - robot.right_extend.getCurrentPosition())*0.005));
//        } else {
//            extend_command = Range.clip(command,((Presets.EXTEND_MIN_POSITION - robot.right_extend.getCurrentPosition())*0.005), 0);
//        }
    }

    // Method for Extending to a Position
    public static void extend_to_position(double position) {
        // Define Commands
        extend_target_position = position;
        extend_error = extend_target_position - robot.right_extend.getCurrentPosition();
        extend_speed = robot.right_extend.getCurrentPosition() - previous_extend_position;
        previous_extend_position = robot.right_extend.getCurrentPosition();
        extend_command = PD.get_extend_command(extend_error, extend_speed);
    }

    // Method for Stopping Extender
    public static void extend_stop() {
        // Define Commands
        extend_command = 0;
    }
}
