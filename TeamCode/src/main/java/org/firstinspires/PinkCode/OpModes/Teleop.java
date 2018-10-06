package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.PinkCode.Robot.Controls;
import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;


@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode{
    public Hardware robot = new Hardware();

    public void init() {
        robot.init(hardwareMap);
    }

    public void loop() {
        // Tank Drive Using Base Joystick Commands
        if (Controls.base_right_joystick > 0.1 || Controls.base_right_joystick < -0.1 || Controls.base_left_joystick < -0.1 || Controls.base_left_joystick > 0.1) {
            Base.drive_by_command(Controls.base_right_joystick, Controls.base_left_joystick);
        } else {
            Base.drive_by_command(0, 0);
        }

        // Extender Controls Using Tower Right Joystick Command
        if (Controls.tower_right_joystick > 0.1 || Controls.tower_right_joystick < -0.1) {
            Extender.extend_by_command(Controls.tower_right_joystick);
        } else {
            Extender.extend_by_command(0);
        }

        // Lift Controls Using Tower Right Joystick Command
        if (Controls.tower_left_joystick > 0.1 || Controls.tower_left_joystick < -0.1) {
            Lift.lift_by_command(Controls.tower_left_joystick);
        } else {
            Lift.lift_by_command(0);
        }

        //Collector Controls Using Base Bumpers.
        if (Controls.base_right_bumper) {
            Collector.collect();
        } else if (Controls.base_left_bumper){
            Collector.eject();
        } else {
            Collector.hold();
        }
    }

    public void stop() {
        Base.drive_stop();
        Extender.extend_stop();
        Lift.lift_stop();
        Collector.collect_stop();
    }
}



