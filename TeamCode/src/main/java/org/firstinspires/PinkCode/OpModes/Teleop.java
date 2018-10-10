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
            if (Base.drive_by_command(Controls.base_right_joystick, Controls.base_left_joystick)) {
                telemetry.addData("Base: ", "Success - Driving by Joysticks");
            } else {
                telemetry.addData("Base: ", "Error - Not Driving");
            }
        } else {
            Base.drive_by_command(0, 0);
            if (Base.drive_by_command(0,0)) {
                telemetry.addData("Base: ", "Stopped");
            } else {
                telemetry.addData("Base: ", "Not Stopped");
            }
        }

        // Collector Controls Using Base Bumpers
        if (Controls.base_right_bumper) {
            Collector.collect();
            if (Collector.collect()) {
                telemetry.addData("Collector: ", "Success - Collecting");
            } else {
                telemetry.addData("Collector: ", "Error - Failed to Collect");
            }
        } else if (Controls.base_left_bumper){
            Collector.eject();
            if (Collector.eject()) {
                telemetry.addData("Collector: ", "Success - Ejecting");
            } else {
                telemetry.addData("Collector: ", " Error - Failed to Eject");
            }
        } else {
            Collector.hold();
            if (Collector.hold()) {
                telemetry.addData("Collector: ", "Holding Position");
            } else {
                telemetry.addData("Collector: ", "Not Holding Position");
            }
        }

        // Extender Controls Using Tower Right Joystick Command and Tower Buttons
        if (Controls.tower_right_joystick > 0.1 || Controls.tower_right_joystick < -0.1) {
            Extender.extend_by_command(Controls.tower_right_joystick);
            if (Extender.extend_by_command(Controls.tower_right_joystick)) {
                telemetry.addData("Extender: ", "Driving Using Joystick");
            } else {
                telemetry.addData("Extender: ", "Stopped");
            }
        } else if (Controls.tower_y) {
            Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
            if (Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION)) {
                telemetry.addData("Extender: ", "Success - Reached Collect Position");
            } else {
                telemetry.addData("Extender: ", "Error - Didn't Reach Collect Position");
            }
        } else if (Controls.tower_b) {
            Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
            if (Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION)) {
                telemetry.addData("Extender: ", "Success - Reached Crater Position");
            } else {
                telemetry.addData("Extender: ", "Error - Didn't Reach Crater Position");
            }
        } else if (Controls.tower_a) {
            Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
            if (Extender.extend_to_position(Presets.EXTEND_SORT_POSITION)) {
                telemetry.addData("Extender: ", "Success - Reached Sort Position");
            } else {
                telemetry.addData("Extender: ", "Error - Didn't Reach Sort Position");
            }
        } else {
            Extender.extend_hold();
            if (Extender.extend_hold()) {
                telemetry.addData("Extender: ", "Holding Position");
            } else {
                telemetry.addData("Extender: ", "Not Holding Position");
            }
        }

        // Lift Controls Using Tower Left Joystick Command and D-Pad
        if (Controls.tower_left_joystick > 0.1 || Controls.tower_left_joystick < -0.1) {
            Lift.lift_by_command(Controls.tower_left_joystick);
            if (Lift.lift_by_command(Controls.tower_left_joystick)) {
                telemetry.addData("Lift: ", "Driving Using Joystick");
            } else {
                telemetry.addData("Lift: ", "Stopped");
            }
        } else if (Controls.tower_dpad_up) {
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
            if (Lift.lift_to_position(Presets.LIFT_SCORE_POSITION)) {
                telemetry.addData("Lift: ", "Success - Reached Score Position");
            } else {
                telemetry.addData("Lift: ", "Error - Didn't Reach Score Position");
            }
        } else if (Controls.tower_dpad_down) {
            Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
            if (Lift.lift_to_position(Presets.LIFT_SORT_POSITION)) {
                telemetry.addData("Lift: ", "Success - Reached Collapsed Position");
            } else {
                telemetry.addData("Lift: ", "Error - Didn't Reach Collapse Position");
            }
        } else {
            Lift.lift_hold();
            if (Lift.lift_hold()) {
                telemetry.addData("Lift: ", "Holding Position");
            } else {
                telemetry.addData("Lift: ", "Not Holding Position");
            }
        }
    }

    // Code to Run Once When the Driver Hits Stop
    public void stop() {
        // Clear and Update Telemetry
        telemetry.clear();
        telemetry.addData("Subsystem States:", "");

        // Stop Base and Send Error Message if Base Fails to Stop Completely
        Base.drive_stop();
        if (!Base.drive_stop()) {
            telemetry.addData("Base: ","Error - Not Stopped");
        } else {
            telemetry.addData("Base: ", "Success - Stopped");
        }

        // Retract Extender and Send Error Message if Extender Fails to Retract Fully
        Extender.extend_stop();
        if (!Extender.extend_stop()) {
            telemetry.addData("Extender: ", "Error - Not Retracted");
        } else {
            telemetry.addData("Extender: ", "Success - Retracted");
        }

        // Lower Lift and Send Error Message if Lift Fails to Lower Fully
        Lift.lift_stop();
        if (!Lift.lift_stop()) {
            telemetry.addData("Lift: ", "Error - Not Retracted");
        } else {
            telemetry.addData("Lift: ", "Success - Retracted");
        }

        // Stop Collector and Send Error Message if Collector Fails to Stop Completely
        Collector.collect_stop();
        if (!Collector.collect_stop()) {
            telemetry.addData("Collector: ", "Error - Not Stopped");
        } else {
            telemetry.addData("Collector: ", "Success - Stopped");
        }
    }
}