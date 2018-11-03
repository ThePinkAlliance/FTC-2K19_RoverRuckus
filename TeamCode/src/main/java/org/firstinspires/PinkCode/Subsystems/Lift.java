package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.PD;
import org.firstinspires.PinkCode.Robot.Presets;

// Abstract Class to Define the Methods of the Lift Subsystem
public abstract class Lift {
    // Define Class Members
    public static double lift_command;
    public static double lift_target_position;
    public static double lift_error;
    public static double lift_speed;
    public static double previous_lift_position;
    public static Hardware robot = new Hardware();

    // Method for Raising/Lowering the Lift Using Commands
    public static boolean lift_by_command (double command) {
        // Define Commands
        lift_command = command;

        // Set Motor Power
        robot.right_lift.setPower(lift_command);
        robot.left_lift.setPower(lift_command);

        // Return Value
        return lift_command > 0;
    }

    // Method for Controlling the Lift Using Positions
    public static boolean lift_to_position (double position) {
        // Define Commands
        lift_target_position = position;
        lift_error = lift_target_position - ((robot.right_lift.getCurrentPosition() + robot.left_lift.getCurrentPosition())/2);
        lift_speed = ((robot.right_lift.getCurrentPosition() + robot.left_lift.getCurrentPosition())/2) - previous_lift_position;
        previous_lift_position = ((robot.right_lift.getCurrentPosition() + robot.left_lift.getCurrentPosition())/2);
        lift_command = PD.get_lift_command(lift_error, lift_speed);

        // Set Motor Power
        robot.right_lift.setPower(lift_command);
        robot.left_lift.setPower(lift_command);

        // Return Value
        return (Math.abs(lift_error) < Presets.LIFT_POSITION_THRESHOLD);
    }

    // Method for Holding the Lift in Place
    public static boolean lift_hold () {
        // Define Commands
        lift_speed = ((robot.right_lift.getCurrentPosition() + robot.left_lift.getCurrentPosition())/2) - previous_lift_position;
        previous_lift_position = ((robot.right_lift.getCurrentPosition() + robot.left_lift.getCurrentPosition())/2);
        lift_command = PD.get_lift_command(0, lift_speed);

        // Set Motor Command
        robot.right_lift.setPower(lift_command);
        robot.left_lift.setPower(lift_command);

        // Return Value
        return (Math.abs(lift_error) < Presets.LIFT_POSITION_THRESHOLD);
    }

    // Method for Stopping the Lift
    public static boolean lift_stop () {
        // Set Motor Power
        robot.right_lift.setPower(0);
        robot.left_lift.setPower(0);

        // Return Value
        return robot.right_lift.getPower() == 0 && robot.left_lift.getPower() == 0;
    }
}
