package org.firstinspires.PinkCode.Subsystems;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.Presets;

public class Collector {
    public static double collect_cmd;

    private static Hardware robot = new Hardware();

    public static boolean collect () {
        collect_cmd = Presets.COLLECTOR_COLLECT_POWER;

        robot.collect.setPower(collect_cmd);

        return true;
    }

    public static boolean eject () {
        collect_cmd = Presets.COLLECTOR_EJECT_POWER;

        robot.collect.setPower(collect_cmd);

        return true;
    }

    public static boolean hold () {
        collect_cmd = Presets.COLLECTOR_HOLD_POWER;

        robot.collect.setPower(collect_cmd);

        return true;
    }

    public static boolean collect_stop () {
        robot.collect.setPower(0);

        return robot.collect.getPower() == 0;
    }

}