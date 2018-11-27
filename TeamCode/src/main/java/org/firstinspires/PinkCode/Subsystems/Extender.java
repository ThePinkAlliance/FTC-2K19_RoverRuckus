package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Calculations.PD;
import org.firstinspires.PinkCode.Calculations.Presets;
import com.qualcomm.robotcore.util.Range;

// Abstract Class to Define the Methods of the Extender Subsystem
public abstract class Extender {
    // Define Class Members
    public static double extend_command;
    public static double extend_target_position;
    public static double extend_error;
    public static double previous_extend_position;
    public static double extend_speed;
    public static Hardware robot = new Hardware();

    // Method for Extending Using Commands
    public static boolean extend_by_command (double command) {
        // Define Commands
        extend_command = command;

        // Limit the motor power near the end of travel
        if (command > 0){
            extend_command = Range.clip(command, 0, ((Presets.EXTEND_MAX_POSITION - robot.right_extend.getCurrentPosition())*0.005));
        } else {
            extend_command = Range.clip(command,((Presets.EXTEND_MIN_POSITION - robot.right_extend.getCurrentPosition())*0.005), 0);
        }
        // Set Motor Power
        robot.right_extend.setPower(extend_command);
        robot.left_extend.setPower(extend_command);

        // Return Value
        return true;
    }

    // Method for Extending to a Position
    public static boolean extend_to_position (double position) {
        // Define Commands
        extend_target_position = position;
        extend_error = extend_target_position - robot.right_extend.getCurrentPosition();
        extend_speed = robot.right_extend.getCurrentPosition() - previous_extend_position;
        previous_extend_position = robot.right_extend.getCurrentPosition();
        extend_command = PD.get_extend_command(extend_error, extend_speed);

        // Set Motor Power
        robot.right_extend.setPower(extend_command);
        robot.left_extend.setPower(extend_command);

        // Return Value
        return (Math.abs(extend_error) < Presets.EXTEND_POSITION_THRESHOLD);
    }

    // Method for Holding the Extender in Place
    public static boolean extend_hold () {
        // Define Commands
        extend_error = previous_extend_position - robot.right_extend.getCurrentPosition();
        extend_speed = robot.right_extend.getCurrentPosition() - previous_extend_position;
        previous_extend_position = robot.right_extend.getCurrentPosition();
        extend_command = PD.get_extend_command(0, extend_speed);

        // Set Motor Power
        robot.right_extend.setPower(extend_command);
        robot.left_extend.setPower(extend_command);

        // Return Value
        return (Math.abs(extend_error) < Presets.EXTEND_POSITION_THRESHOLD);
    }

    // Method for Stopping Extender
    public static boolean extend_stop () {
        // Define Commands
        robot.right_extend.setPower(0);
        robot.left_extend.setPower(0);

        // Return Value
        return robot.right_extend.getPower() == 0;
    }
}
