package org.firstinspires.PinkCode.Subsystems;

// Abstract Class to Define the Methods of the Extender Subsystem
public abstract class Scorer extends Subsystem {
    // Method for Rotating the Scoring Bucket to a Position
    public static void score_rotate_to_position(double position) {
        // Define Commands
        score_target_position = position;
        score_left_target_position = score_target_position;
        score_right_target_position = -score_target_position;
    }

    // Method for Rotating the Scoring Bucket Using Commands
    public static void score_rotate_by_command(double command) {
        // Define Commands
        score_left_target_position = command;
        score_right_target_position = -command;
    }

    public static void score_flap_rotate_to_position(double position) {
        // Define Commands
        score_flap_target_position = position;
    }

    public static void score_kicker_rotate_to_position(double position) {
        //Define Commands
        score_kicker_target_position = position;
    }
}