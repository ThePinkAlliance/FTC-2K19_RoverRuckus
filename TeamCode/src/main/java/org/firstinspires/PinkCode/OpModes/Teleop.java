package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.PinkCode.Calculations.Average;
import org.firstinspires.PinkCode.Calculations.Presets;
import org.firstinspires.PinkCode.Robot.Controls;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;
import org.firstinspires.PinkCode.Subsystems.Scorer;

// Class for Player-Controlled Period of the Game Which Binds Controls to Subsystems
@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends Controls {
    // Code to Run Once When the Driver Hits Init
    public void init() {
        // Initialization of Each Subsystem's Hardware Map
        Base.robot.init(hardwareMap);
        Collector.robot.init(hardwareMap);
        Extender.robot.init(hardwareMap);
        Lift.robot.init(hardwareMap);
        Scorer.robot.init(hardwareMap);
    }

    // Code to Run Constantly While the Program is Running
    public void loop() {
        // Drive Train Control
        Base.drive_by_command(base_right_joystick(-0.5, 0.5), base_left_joystick(-0.5, 0.5));

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
        // Extender Controls
        Extender.extend_by_command(base_right_trigger(0, 0.5));
        Extender.extend_by_command(base_left_trigger(0, 0.5));

        // Lift Controls
        Lift.lift_by_command(tower_right_joystick(-0.5, 0.5));
        if (tower_back(false)){
            Lift.robot.right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift.robot.left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift.robot.right_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lift.robot.left_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else if(tower_y(false)){
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
        } else {
            Lift.lift_hold();
        }

        // Scorer Controls
        // y=Mx+b to convert 1 to -1 joy to 0 to 1 servo (y = -0.5X + 0.5)
        Scorer.score_rotate_by_command((-0.5*tower_left_joystick(-0.5, 0.5))+0.5);
        if (tower_dpad_right(false) || tower_y(false)) {
            Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
        } else if (tower_dpad_left(false) || tower_a(false)) {
            Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
        }

        //Tower Flap Controls
        if (tower_right_bumper(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        } else if (tower_left_bumper(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (tower_y(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (tower_a(false) || tower_b(false)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        }

        // Add Telemetry to Phone for Debugging and Testing
        telemetry.addData("Powers: ","");
        telemetry.addData("Base Right Power: ", Base.robot.right_drive.getPower());
        telemetry.addData("Base Left Power: ", Base.robot.left_drive.getPower());
        telemetry.addData("Collector Power: ", Collector.robot.collect.getPower());
        telemetry.addData("Extender Arm Power: ", Average.two_values(Extender.robot.right_extend.getPower(), Extender.robot.left_extend.getPower()));
        telemetry.addData("Lift Power: ", Average.two_values(Lift.robot.right_lift.getPower(), Lift.robot.left_lift.getPower()));
        telemetry.addData("Scorer Rotate Power: ", Average.two_values(Scorer.robot.score_right_rotate.getPower(), Scorer.robot.score_left_rotate.getPower()));
        telemetry.addData("Positions: ","");
        telemetry.addData("Base Right Position: ", Base.robot.right_drive.getCurrentPosition());
        telemetry.addData("Base Left Position: ", Base.robot.left_drive.getCurrentPosition());
        telemetry.addData("Collector Rotate Target Position: ", Collector.robot.collector_rotate.getPosition());
        telemetry.addData("Extender Arm Position: ", Average.two_values(Extender.robot.right_lift.getCurrentPosition(), Extender.robot.left_extend.getCurrentPosition()));
        telemetry.addData("Lift Position: ", Average.two_values(Lift.robot.right_lift.getCurrentPosition(), Lift.robot.left_lift.getCurrentPosition()));
        telemetry.update();
    }

    // Code to Run Once When the Driver Hits Stop
    public void stop() {
        // Clear and Update Telemetry
        telemetry.clear();
        telemetry.addData("Subsystem States:", "");

        // Stop Base and Send Error Message if Base Fails to Stop Completely
        if (!Base.drive_stop()) {
            telemetry.addData("Base: ","Error - Not Stopped");
        } else {
            telemetry.addData("Base: ", "Success - Stopped");
        }

        // Retract Extender and Send Error Message if Extender Fails to Retract Fully
        if (!Extender.extend_stop()) {
            telemetry.addData("Extender: ", "Error - Not Retracted");
        } else {
            telemetry.addData("Extender: ", "Success - Retracted");
        }

        // Lower Lift and Send Error Message if Lift Fails to Lower Fully
        if (!Lift.lift_stop()) {
            telemetry.addData("Lift: ", "Error - Not Retracted");
        } else {
            telemetry.addData("Lift: ", "Success - Retracted");
        }

        // Stop Collector and Send Error Message if Collector Fails to Stop Completely
        if (!Collector.collect_stop()) {
            telemetry.addData("Collector: ", "Error - Not Stopped");
        } else {
            telemetry.addData("Collector: ", "Success - Stopped");
        }

        telemetry.update();
    }
}