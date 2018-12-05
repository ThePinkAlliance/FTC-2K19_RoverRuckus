package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.PinkCode.Robot.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;
import org.firstinspires.PinkCode.Subsystems.Scorer;
import org.firstinspires.PinkCode.Subsystems.Subsystem;

// Class for Player-Controlled Period of the Game Which Binds Controls to Subsystems
@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode {
    // Code to Run Once When the Driver Hits Init
    public void init() {
        // Initialization of Each Subsystem's Hardware Map
        Subsystem.robot.init(hardwareMap);
        Extender.robot.init(hardwareMap);
        Lift.robot.init(hardwareMap);
        Scorer.robot.init(hardwareMap);
    }

    // Code to Run Constantly While the Program is Running
    public void loop() {
        // Drive Train Control
        Base.drive_by_command(-gamepad1.right_stick_y, -gamepad1.left_stick_y);

        // Collector Controls
        if (gamepad1.y) {
            Collector.collect();
        } else if (gamepad1.b) {
            Collector.eject();
        } else if (gamepad1.right_bumper) {
            Collector.collect();
            Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
        } else if (gamepad1.left_bumper) {
            Collector.eject();
            Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
        } else if (gamepad2.b) {
            Collector.collect_stop();
            Collector.rotate_to_position(Presets.COLLECTOR_SORT_POSITION);
        } else {
            Collector.collect_stop();
        }
        // Extender Controls
        Extender.extend_by_command(gamepad1.right_trigger);
        Extender.extend_by_command(-gamepad1.left_trigger);

        // Lift Controls
        if (gamepad2.right_stick_y < -.1 || gamepad2.right_stick_y > .1) {
            Lift.lift_by_command(-gamepad2.right_stick_y);
        } else if (gamepad2.back){
            Lift.robot.right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift.robot.left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift.robot.right_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lift.robot.left_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else if(gamepad2.y){
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
        } else if(gamepad2.a){
            Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
        } else {
            Lift.lift_stop();
        }

        // Scorer Controls
        // y=Mx+b to convert 1 to -1 joy to 0 to 1 servo (y = -0.5X + 0.5)
        if (gamepad2.left_stick_y > 0.1 || gamepad2.left_stick_y < -0.1) {
            Scorer.score_rotate_by_command(-gamepad2.left_stick_y);
        } else if (gamepad2.dpad_right || gamepad2.y) {
            Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
        } else if (gamepad2.dpad_left || gamepad2.a) {
            Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
        } else {
            Scorer.score_rotate_by_command(0);
        }

        //Tower Flap Controls
        if (gamepad2.right_bumper) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
           // Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_KICK);
            //TODO MAY BE CAUSING ISSUES
           // Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_STOW);
        } else if (gamepad2.left_bumper) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (gamepad2.y) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (gamepad2.a || gamepad2.b) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
           //Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_STOW);
        }

        // Set Motor Powers and Servos to Their Commands
        Subsystem.set_motor_powers();
        Subsystem.set_servo_positions();

        // Add Telemetry to Phone for Debugging and Testing
        telemetry.addData("Powers: ","");
        telemetry.addData("Base Right Power: ", Subsystem.robot.right_drive.getPower());
        telemetry.addData("Base Left Power: ", Subsystem.robot.left_drive.getPower());
        telemetry.addData("Collector Power: ", Subsystem.robot.collect.getPower());
        telemetry.addData("Lift Power: ", Subsystem.robot.right_lift.getPower());
        telemetry.addData("Tower Right Rotate: ", Scorer.robot.score_right_rotate.getPower());
        telemetry.addData("Tower Left Rotate: ", Scorer.robot.score_left_rotate.getPower());
        telemetry.addData("Positions: ","");
        telemetry.addData("Base Right Position: ", Subsystem.robot.right_drive.getCurrentPosition());
        telemetry.addData("Base Left Position: ", Subsystem.robot.left_drive.getCurrentPosition());
        telemetry.addData("Lift Position: ", Lift.robot.right_lift.getCurrentPosition());
        telemetry.addData("Collector Rotate Target Position: ", Subsystem.robot.collector_rotate.getPosition());
        telemetry.addData("Flap Target Position: ", Scorer.robot.score_flap.getPosition());
        telemetry.update();
    }

    // Code to Run Once When the Driver Hits Stop
    public void stop() {
        Base.drive_stop();
    }
}