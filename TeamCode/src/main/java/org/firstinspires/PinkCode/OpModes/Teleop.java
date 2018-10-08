package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.PinkCode.Robot.Controls;
import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;

// Class for Player-Controlled Period of the Game Which Binds Controls to Subsystems
@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode{
    // Define OpMode Members
    public Hardware robot = new Hardware();

    // Code to Run Once When the Driver Hits Init
    public void init() {
        robot.init(hardwareMap);
    }

    // Code to Run Constantly While the Program is Running
    public void loop() {
        // Tank Drive Using Base Joystick Commands
        if (Controls.base_right_joystick > 0.1 || Controls.base_right_joystick < -0.1 || Controls.base_left_joystick < -0.1 || Controls.base_left_joystick > 0.1) {
            Base.drive_by_command(Controls.base_right_joystick, Controls.base_left_joystick);
        } else {
            Base.drive_by_command(0, 0);
        }

        // Collector Controls Using Base Bumpers
        if (Controls.base_right_bumper) {
            Collector.collect();
        } else if (Controls.base_left_bumper){
            Collector.eject();
        } else {
            Collector.hold();
        }

        // Extender Controls Using Tower Right Joystick Command and Tower Buttons
        if (Controls.tower_right_joystick > 0.1 || Controls.tower_right_joystick < -0.1) {
            Extender.extend_by_command(Controls.tower_right_joystick);
        } else if (Controls.tower_y) {
            Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
        } else if (Controls.tower_b) {
            Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
        } else if (Controls.tower_a) {
            Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
        } else {
            Extender.extend_hold();
        }

        // Lift Controls Using Tower Left Joystick Command and D-Pad
        if (Controls.tower_left_joystick > 0.1 || Controls.tower_left_joystick < -0.1) {
            Lift.lift_by_command(Controls.tower_left_joystick);
        } else if (Controls.tower_dpad_up) {
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
        } else if (Controls.tower_dpad_down) {
            Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
        } else {
            Lift.lift_hold();
        }
    }

    // Code to Run Once When the Driver Hits Stop
    public void stop() {
        Base.drive_stop();
        Extender.extend_stop();
        Lift.lift_stop();
        Collector.collect_stop();
    }
}