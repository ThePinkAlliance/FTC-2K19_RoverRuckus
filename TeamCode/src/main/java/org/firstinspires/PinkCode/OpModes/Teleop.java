package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.PinkCode.Robot.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;
import org.firstinspires.PinkCode.Subsystems.Scorer;

import static org.firstinspires.PinkCode.Subsystems.Collector.robot;

// Class for Player-Controlled Period of the Game Which Binds Controls to Subsystems
@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode{
    public boolean tower_start_pressed;
    public boolean tower_dpad_pressed_up;
    public boolean tower_dpad_pressed_down;
    // Code to Run Once When the Driver Hits Init
    public void init() {
        Base.robot.init(hardwareMap);
        robot.init(hardwareMap);
        Extender.robot.init(hardwareMap);
        Lift.robot.init(hardwareMap);
        Scorer.robot.init(hardwareMap);
    }

    // Code to Run Constantly While the Program is Running
    public void loop() {
        // Tank Drive Using Base Joystick Commands
        if (gamepad1.right_stick_y > 0.1 || gamepad1.right_stick_y < -0.1 || gamepad1.left_stick_y < -0.1 || gamepad1.left_stick_y > 0.1) {
            Base.drive_by_command(gamepad1.right_stick_y, gamepad1.left_stick_y);
            if (Base.drive_by_command(gamepad1.right_stick_y, gamepad1.left_stick_y)) {
                telemetry.addData("Base: ", "Success - Driving by Joysticks");
            } else {
                telemetry.addData("Base: ", "Error - Not Driving");
            }
        } else {
            Base.drive_by_command(0, 0);
            if (Base.drive_by_command(0, 0)) {
                telemetry.addData("Base: ", "Stopped");
            } else {
                telemetry.addData("Base: ", "Not Stopped");
            }
        }

        // Collector Controls Using Base Bumpers
        if (gamepad2.right_bumper) {
            Collector.collect();
        } else if (gamepad2.left_bumper) {
            Collector.eject();
        } else if (gamepad1.right_bumper) {
            Collector.collect();
            Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
            if (Collector.collect()) {
                telemetry.addData("Collector: ", "Success - Collecting");
            } else {
                telemetry.addData("Collector: ", "Error - Failed to Collect");
            }
        } else if (gamepad1.left_bumper) {
            Collector.eject();
            Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
            if (Collector.eject()) {
                telemetry.addData("Collector: ", "Success - Ejecting");
            } else {
                telemetry.addData("Collector: ", " Error - Failed to Eject");
            }
        } else if (gamepad2.b) {
            Collector.hold();
            Collector.rotate_to_position(Presets.COLLECTOR_SORT_POSITION);
        } else {
            Collector.hold();
            if (Collector.hold()) {
                telemetry.addData("Collector: ", "Holding Position");
            } else {
                telemetry.addData("Collector: ", "Not Holding Position");
            }
        }

        // Extender Controls Using Tower Right Joystick Command and Tower Buttons
        if (gamepad1.right_trigger > 0.1) {
            if (Extender.extend_by_command(gamepad1.right_trigger)) {
                telemetry.addData("Extender: ", "Driving Using Joystick");
            } else {
                telemetry.addData("Extender: ", "Stopped");
            }
        } else if (gamepad1.left_trigger > 0.1) {
                if (Extender.extend_by_command(-gamepad1.left_trigger)) {
                    telemetry.addData("Extender: ", "Driving Using Joystick");
                } else {
                    telemetry.addData("Extender: ", "Stopped");
                }
        } else {
            if (Extender.extend_hold()) {
                telemetry.addData("Extender: ", "Holding Position");
            } else {
                telemetry.addData("Extender: ", "Not Holding Position");
            }
        }

        // Lift Controls Using Tower Left Joystick Command and D-Pad
        if (gamepad2.right_stick_y > 0.1 || gamepad2.right_stick_y < -0.1) {
            if (Lift.lift_by_command(gamepad2.right_stick_y)) {
                telemetry.addData("Lift: ", "Driving Using Joystick");
            } else {
                telemetry.addData("Lift: ", "Stopped");
            }
        } else if(gamepad2.y){
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
        } else if(gamepad2.a) {
            Lift.lift_to_position(Presets.LIFT_TRAVEL_POSITION);
        } else if (gamepad1.dpad_up && !tower_dpad_pressed_up) {
            tower_dpad_pressed_up = true;
            Collector.rotate_to_position(robot.collector_rotate.getPosition() + 0.1);
        } else if (gamepad1.dpad_down && !tower_dpad_pressed_down) {
            tower_dpad_pressed_down = true;
            Collector.rotate_to_position(robot.collector_rotate.getPosition() + 0.1);
        } else {
            tower_dpad_pressed_up = false;
            tower_dpad_pressed_down = false;
        }

        // Tower Bucket Controls
        if (gamepad2.dpad_right || gamepad2.y) {
            Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
        } else if (gamepad2.dpad_left || gamepad2.a) {
            Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
        }

        //Tower Flap Controls
        if (gamepad2.right_bumper) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        } else if (gamepad2.left_bumper) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (gamepad2.y) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        }else if (gamepad2.a) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        }
        telemetry.update();
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

        telemetry.update();
    }
}