package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.PD;
import org.firstinspires.PinkCode.Robot.Presets;

// Abstract Class to Define the Methods of the Extender Subsystem
public abstract class Scorer {
    // Define Class Members
    public static double rotate_command;
    public static double score_target_position;
    public static double score_right_target_position;
    public static double score_left_target_position;
    public static double score_flap_target_position;
    public static double score_kicker_target_position;
    public static Hardware robot = new Hardware();

    // Method for Rotating the Scoring Bucket to a Position
    public static boolean score_rotate_to_position (double position) {
        // Define Commands
//        if (robot.right_lift.getCurrentPosition() > Presets.LIFT_SCORE_POSITION || robot.left_lift.getCurrentPosition() > Presets.LIFT_SCORE_POSITION) {
//            if (position > Presets.SCORER_TRAVEL) {
//                score_target_position = Presets.SCORER_TRAVEL;
//            } else {
//                score_target_position = position;
//            }
//        } else {
            score_target_position = position;
//        }
        score_left_target_position = score_target_position;
        score_right_target_position = 1 - score_target_position;

        // Set Servo Position
        robot.score_left_rotate.setPower(score_left_target_position);
        robot.score_right_rotate.setPower(score_right_target_position);

        // Return Value
        return  robot.score_right_rotate.getPower() == score_right_target_position;
    }

    // Method for Rotating the Scoring Bucket Using Commands
    public static boolean score_rotate_by_command (double command) {
        // Define Commands

        // Set Motor Power
        robot.score_left_rotate.setPower(-command);
        robot.score_right_rotate.setPower(command);

        // Return Value
        return true;
    }

    public static boolean score_flap_rotate_to_position (double position) {
        // Define Commands
        score_flap_target_position = position;

        // Set Servo Position
        robot.score_flap.setPosition(score_flap_target_position);

        // Return Value
        return robot.score_flap.getPosition() == score_flap_target_position;
    }
    //public static boolean score_kicker_rotate_to_position (double position)
   // {
        //Define Commands
        //score_kicker_target_position = position;

        //Set Servo Position
        //robot.score_kicker.setPosition(score_kicker_target_position);

        //Return Value
        //return robot.score_kicker.getPosition() == score_kicker_target_position;
  //  }
}