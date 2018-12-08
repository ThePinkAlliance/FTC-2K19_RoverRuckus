package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.PinkCode.Calculations.Presets;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Subsystem;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Scorer;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Lift;
import org.firstinspires.PinkCode.Robot.Controls;

// Class for Player-Controlled Period of the Game Which Binds Controls to Subsystems
@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends Controls {
    // Code to Run Once When the Drivers Press Init
    public void init() {
        // Initialization of Each Subsystem's Hardware Map
        Subsystem.robot.init(hardwareMap);

        // Telemetry Update to Inform Drivers That the Program is Initialized
        telemetry.addData("Status: ", "Waiting for Driver to Press Play");
        telemetry.update();
    }

    // Code to Run Constantly After the Drivers Press Play and Before They Press Stop
    public void loop() {
        // Drive Train Control TODO Controls for Joysticks
        Base.drive_by_command(-gamepad1.right_stick_y, -gamepad1.left_stick_y);

        // Collector Controls
        if (base_y(false)) {
            Collector.collect();
        } else if (base_b(false)) {
            Collector.eject();
        } else if (base_right_bumper(false)) {
            Collector.collect();
            Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
        } else if (base_left_bumper(false)) {
            Collector.eject();
            Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
        } else if (tower_b(false)) {
            Collector.collect_stop();
            Collector.rotate_to_position(Presets.COLLECTOR_SORT_POSITION);
        } else {
            Collector.collect_stop();
        }

        // Extender Controls TODO: Controls for Triggers
        if (gamepad1.right_trigger > 0.1) {
            Extender.extend_by_command(gamepad1.right_trigger);
        } else if (gamepad1.left_trigger > 0.1) {
            Extender.extend_by_command(-gamepad1.left_trigger);
        } else {
            Extender.extend_by_command(0);
        }

        // Lift Controls TODO: Controls for Joysticks
        if (gamepad2.right_stick_y < -.1 || gamepad2.right_stick_y > .1) {
            Lift.lift_by_command(-gamepad2.right_stick_y);
        } else if (tower_back(false)) {
            Lift.robot.right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift.robot.left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift.robot.right_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lift.robot.left_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else if(tower_y(false)) {
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
        } else if(tower_a(false)) {
            Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
        } else if (gamepad2.x) {
            Lift.lift_to_position(Presets.LIFT_HANG_POSITION);
        } else {
            Lift.lift_stop();
        }

        // Scorer Controls TODO: Controls for Joysticks
        if (gamepad2.left_stick_y > 0.1 || gamepad2.left_stick_y < -0.1) {
            Scorer.score_rotate_by_command(-gamepad2.left_stick_y);
        } else if (tower_dpad_right(false) || tower_y(false)) {
            Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
        } else if (tower_dpad_left(false) || tower_a(false)) {
            Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
        } else {
            Scorer.score_rotate_by_command(0);
        }

        //Tower Flap Controls
        if (tower_right_bumper(false)) {
            Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_KICK);
        } else {
            Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_STOW);
        }

        if (tower_right_bumper(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        } else if (tower_left_bumper(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (tower_y(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (tower_a(false) || tower_b(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        }

        // Set Motor Powers and Servos to Their Commands
        Subsystem.set_motor_powers();
        Subsystem.set_servo_positions();

        // Add Telemetry to Phone for Debugging and Testing if it is Activated
        if (tower_start(false)) {
            telemetry.addData("Status: ", "Running Teleop");
            telemetry.addData("Powers: ", "");
            telemetry.addData("Base Right Power: ", Subsystem.robot.right_drive.getPower());
            telemetry.addData("Base Left Power: ", Subsystem.robot.left_drive.getPower());
            telemetry.addData("Collector Power: ", Subsystem.robot.collect.getPower());
            telemetry.addData("Lift Power: ", Subsystem.robot.right_lift.getPower());
            telemetry.addData("Tower Right Rotate Power: ", Subsystem.robot.score_right_rotate.getPower());
            telemetry.addData("Tower Left Rotate Power: ", Scorer.robot.score_left_rotate.getPower());
            telemetry.addData("Right Extend Power: ", Subsystem.robot.right_extend.getPower());
            telemetry.addData("Left Extend Power: ", Subsystem.robot.left_extend.getPower());
            telemetry.addData("Positions: ", "");
            telemetry.addData("Base Right Position: ", Subsystem.robot.right_drive.getCurrentPosition());
            telemetry.addData("Base Left Position: ", Subsystem.robot.left_drive.getCurrentPosition());
            telemetry.addData("Lift Position: ", Subsystem.robot.right_lift.getCurrentPosition());
            telemetry.addData("Collector Rotate Target Position: ", Subsystem.robot.collector_rotate.getPosition());
            telemetry.addData("Flap Target Position: ", Subsystem.robot.score_flap.getPosition());
            telemetry.addData("Right Extend Position: ", Subsystem.robot.right_extend.getCurrentPosition());
            telemetry.addData("Left Extend Position: ", Subsystem.robot.left_extend.getCurrentPosition());
            telemetry.update();
        } else {
            // Telemetry Update to Inform Drivers That the Program is Running and how to Access Telemetry
            telemetry.addData("Status: ", "Running Teleop");
            telemetry.addData("Press Start on the Tower Gamepad for Telemetry", "");
            telemetry.update();
        }
    }

    // Code to Run Once When the Drivers Press Stop
    public void stop() {
        // Stop Sending Commands to Each Subsystem
        Base.drive_stop();
        Collector.collect_stop();
        Extender.extend_stop();
        Lift.lift_stop();

        // Set Motor Powers and Servos to Their Commands
        Subsystem.set_motor_powers();
        Subsystem.set_servo_positions();

        // Telemetry Update to Inform Drivers That the Program is Stopped
        telemetry.addData("Status: ", "Stopped");
        telemetry.update();
    }
}