package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;

// Class Which Defines Variables Used in Other Subsystems and Sets Powers and Commands for Teleop/Auto
public abstract class Subsystem {
    // Define Class Members
    public static Hardware robot = new Hardware();
    public static double left_wheel_command;
    public static double right_wheel_command;
    public static double collect_command;
    public static double collect_rotate_target_position;

    public static boolean set_motor_powers() {
        // Set Motor Powers
        robot.right_drive.setPower(Subsystem.right_wheel_command);
        robot.left_drive.setPower(Subsystem.left_wheel_command);
        robot.collect.setPower(Subsystem.collect_command);

        // Return Value
        return true;
    }

    public static boolean set_servo_positions() {
        // Set Servo Positions
        robot.collector_rotate.setPosition(collect_rotate_target_position);

        // Return Value
        return true;
    }

}
