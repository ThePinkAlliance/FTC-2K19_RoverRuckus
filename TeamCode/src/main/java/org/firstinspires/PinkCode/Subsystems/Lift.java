package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;

public class Lift {
    public static double lift_command;

    private static Hardware robot = new Hardware();

    public static boolean lift_by_command (double command) {
        lift_command = command;

        robot.lift.setPower(lift_command);

        return true;
    }

    public static boolean lift_stop () {
        robot.lift.setPower(0);

        return robot.lift.getPower() == 0;
    }
}
