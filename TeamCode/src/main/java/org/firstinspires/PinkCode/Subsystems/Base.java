package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;


public class Base {

    public static double left_wheel_command;
    public static double right_wheel_command;

    private static Hardware robot = new Hardware();

    public static boolean drive_by_command(double right, double left) {
        right_wheel_command = right;
        left_wheel_command = left;

        robot.right_front_drive.setPower(right_wheel_command);
        robot.right_back_drive.setPower(right_wheel_command);
        robot.left_front_drive.setPower(left_wheel_command);
        robot.left_back_drive.setPower(left_wheel_command);

        return true;
    }

    public static boolean drive_stop() {
        robot.right_front_drive.setPower(0);
        robot.right_back_drive.setPower(0);
        robot.left_front_drive.setPower(0);
        robot.left_back_drive.setPower(0);

        return robot.right_front_drive.getPower() == 0 && robot.right_back_drive.getPower() == 0 && robot.left_front_drive.getPower() == 0 && robot.left_back_drive.getPower() == 0;
    }
}