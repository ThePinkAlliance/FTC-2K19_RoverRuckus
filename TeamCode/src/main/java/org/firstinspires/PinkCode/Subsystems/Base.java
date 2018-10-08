package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;

// Abstract Class to Define the Methods of the Base Subsystem
public abstract class Base {
    // Define Class Members
    public static double left_wheel_command;
    public static double right_wheel_command;
    private static Hardware robot = new Hardware();

    // Method for Driving in Tank Drive using Commands
    public static boolean drive_by_command(double right, double left) {
        // Define Commands
        right_wheel_command = right;
        left_wheel_command = left;

        // Set Motor Power
        robot.right_front_drive.setPower(right_wheel_command);
        robot.right_back_drive.setPower(right_wheel_command);
        robot.left_front_drive.setPower(left_wheel_command);
        robot.left_back_drive.setPower(left_wheel_command);

        // Return Value
        return true;
    }

    // Method for Stopping the Drive Train
    public static boolean drive_stop() {
        // Set Motor Power
        robot.right_front_drive.setPower(0);
        robot.right_back_drive.setPower(0);
        robot.left_front_drive.setPower(0);
        robot.left_back_drive.setPower(0);

        // Return Value
        return robot.right_front_drive.getPower() == 0 && robot.right_back_drive.getPower() == 0 && robot.left_front_drive.getPower() == 0 && robot.left_back_drive.getPower() == 0;
    }
}