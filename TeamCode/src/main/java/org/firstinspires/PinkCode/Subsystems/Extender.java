package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;

public class Extender {

    public static double extend_command;

    private static Hardware robot = new Hardware();

    public static boolean extend_by_command (double command) {
        extend_command = command;

        robot.extend.setPower(extend_command);

        return true;
    }

    public static boolean extend_stop () {
        robot.extend.setPower(0);

        return robot.extend.getPower() == 0;
    }

}
