package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.PinkCode.Robot.Controls;
import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Extender;


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
    }

    public void stop() {
        Base.drive_stop();
        Extender.extend_stop();
    }
}



