package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;

public class Collector {
    public static double collect_cmd;

    private static Hardware robot = new Hardware();

    public static boolean collect () {
        collect_cmd = 1;

        robot.extend.setPower(collect_cmd);

        return true;
    }

    public static boolean eject () {
        collect_cmd = -1;

        robot.extend.setPower(collect_cmd);

        return true;
    }

    public static boolean hold () {
        collect_cmd = 0;

        robot.extend.setPower(collect_cmd);

        return true;
    }

    public static boolean collect_stop () {
        robot.extend.setPower(0);

        return robot.extend.getPower() == 0;
    }

}