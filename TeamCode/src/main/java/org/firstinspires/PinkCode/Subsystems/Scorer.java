package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.PD;
import org.firstinspires.PinkCode.Robot.Presets;

// Abstract Class to Define the Methods of the Extender Subsystem
public abstract class Scorer {
    // Define Class Members
    public static double score_target_position;
    public static double score_right_target_position;
    public static double score_left_target_position;
    public static double score_flap_target_position;
    public static Hardware robot = new Hardware();

    // Method for Rotating the Scoring Bucket
    public static boolean score_rotate_to_position (double position) {
        // Define Commands
        if (robot.right_lift.getCurrentPosition() > Presets.LIFT_TRAVEL_POSITION || robot.left_lift.getCurrentPosition() > Presets.LIFT_TRAVEL_POSITION) {
            if (position > Presets.SCORER_TRAVEL) {
                score_target_position = Presets.SCORER_TRAVEL;
            } else {
                score_target_position = position;
            }
        } else {
            score_target_position = position;
        }
        score_left_target_position = score_target_position;
        score_right_target_position = 1 - score_target_position;

        // Set Servo Position
        robot.score_left_rotate.setPosition(score_left_target_position);
        robot.score_right_rotate.setPosition(score_right_target_position);

        // Return Value
        return robot.score_left_rotate.getPosition() == score_left_target_position && robot.score_right_rotate.getPosition() == score_right_target_position;
    }

    public static boolean score_flap_rotate_to_position (double position) {
        // Define Commands
        score_flap_target_position = position;

        // Set Servo Position
        robot.score_flap.setPosition(score_flap_target_position);

        // Return Value
        return robot.score_flap.getPosition() == score_flap_target_position;
    }
}