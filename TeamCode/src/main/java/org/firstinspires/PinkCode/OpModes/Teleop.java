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
            if (Base.drive_by_command(-gamepad1.right_stick_y, -gamepad1.left_stick_y)) {
                telemetry.addData("Base: ", "Success - Driving by Joysticks");
            } else {
                telemetry.addData("Base: ", "Error - Not Driving");
            }
        } else {
            if (Base.drive_by_command(0, 0)) {
                telemetry.addData("Base: ", "Stopped");
            } else {
                telemetry.addData("Base: ", "Not Stopped");
            }
        }

        // Collector Controls Using Base Bumpers
        if (gamepad1.y) {
            Collector.collect();
        } else if (gamepad1.b) {
            Collector.eject();
        } else if (gamepad1.right_bumper) {
            Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
            if (Collector.collect()) {
                telemetry.addData("Collector: ", "Success - Collecting");
            } else {
                telemetry.addData("Collector: ", "Error - Failed to Collect");
            }
        } else if (gamepad1.left_bumper) {
            Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
            if (Collector.eject()) {
                telemetry.addData("Collector: ", "Success - Ejecting");
            } else {
                telemetry.addData("Collector: ", " Error - Failed to Eject");
            }
        } else if (gamepad2.b) {
            Collector.collect_stop();
            Collector.rotate_to_position(Presets.COLLECTOR_SORT_POSITION);
        } else {
            if (Collector.collect_stop()) {
                telemetry.addData("Collector: ", "Holding Position");
            } else {
                telemetry.addData("Collector: ", "Not Holding Position");
            }
        }

        // Extender Controls Using Base Trigger Commands
        if (gamepad1.right_trigger > 0.05) {
            if (Extender.extend_by_command(gamepad1.right_trigger)) {
                telemetry.addData("Extender: ", "Driving Using Trigger %1$.2f", gamepad1.right_trigger);
            } else {
                telemetry.addData("Extender: ", "Stopped");
            }
        } else if (gamepad1.left_trigger > 0.05) {
                if (Extender.extend_by_command(-gamepad1.left_trigger)) {
                    telemetry.addData("Extender: ", "Driving Using Trigger %1$.2f", gamepad1.left_trigger);
                } else {
                    telemetry.addData("Extender: ", "Stopped");
                }
        } else {
            if (Extender.extend_stop()) {
                telemetry.addData("Extender: ", "Holding Position");
            } else {
                telemetry.addData("Extender: ", "Not Holding Position");
            }
        }

        // Lift Controls Using Tower Left Joystick Command and D-Pad
        if (gamepad2.right_stick_y > 0.05 || gamepad2.right_stick_y < -0.05) {
            if (Lift.lift_by_command(-gamepad2.right_stick_y)) {
                telemetry.addData("Lift: ", "Driving Using Joystick %1$.2f", gamepad2.right_stick_y);
            } else {
                telemetry.addData("Lift: ", "Stopped");
            }
        } else if(gamepad2.y){
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
        } else {
            Lift.lift_stop();
        }

        // Tower Bucket (Scorer) Rotate
        if (gamepad2.left_stick_y > 0.05 || gamepad2.left_stick_y < -0.05) {
            // y=Mx+b to convert 1 to -1 joy to 0 to 1 servo (y = -0.5X + 0.5)
//            if (Scorer.score_rotate_by_command((-0.5*gamepad2.left_stick_y)+0.5)) {
                if (Scorer.score_rotate_by_command(gamepad2.left_stick_y)) {
                telemetry.addData("Tower Bucket: ", "Driving Using Joystick %1$.2f", (gamepad2.left_stick_y));
            } else {
                telemetry.addData("Tower Bucket: ", "Stopped");
            }
        } else {
            Scorer.score_rotate_by_command(0);
        }


        /*        if (gamepad1.dpad_up) {
            Scorer.score_rotate_to_position(robot.score_left_rotate.getPosition() + 0.01);
        } else if (gamepad1.dpad_down) {
            Scorer.score_rotate_to_position(robot.score_left_rotate.getPosition() - 0.01);
        } else {
            tower_dpad_pressed_up = false;
            tower_dpad_pressed_down = false;
            //telemetry.clear();
            telemetry.addData("Score Left Rotate", robot.score_left_rotate.getPosition());
            telemetry.addData("Score Right Rotate", robot.score_right_rotate.getPosition());
            telemetry.update();
        }

        // Tower Bucket Controls
        if (gamepad2.dpad_right || gamepad2.y) {
            Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
        } else if (gamepad2.dpad_left || gamepad2.a) {
            Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
        }
*/

        //Tower Flap Controls
        if (gamepad2.right_bumper) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        } else if (gamepad2.left_bumper) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if (gamepad2.y) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
        } else if ((gamepad2.a)|| (gamepad2.b)) {
            Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
        }
        telemetry.addData("Lift Pos: ", "%1$d", robot.right_lift.getCurrentPosition());
        telemetry.addData("Extender Pos: ", "%1$d", robot.right_extend.getCurrentPosition());

        telemetry.update();
    }

    // Code to Run Once When the Driver Hits Stop
    public void stop() {
        // Clear and Update Telemetry
        telemetry.clear();
        telemetry.addData("Subsystem States:", "");

        // Stop Base and Send Error Message if Base Fails to Stop Completely
        if (!Base.drive_stop()) {
            //telemetry.addData("Base: ","Error - Not Stopped");
        } else {
            //telemetry.addData("Base: ", "Success - Stopped");
        }

        // Retract Extender and Send Error Message if Extender Fails to Retract Fully
        if (!Extender.extend_stop()) {
            //telemetry.addData("Extender: ", "Error - Not Retracted");
        } else {
            //telemetry.addData("Extender: ", "Success - Retracted");
        }

        // Lower Lift and Send Error Message if Lift Fails to Lower Fully
        if (!Lift.lift_stop()) {
            //telemetry.addData("Lift: ", "Error - Not Retracted");
        } else {
            //telemetry.addData("Lift: ", "Success - Retracted");
        }

        // Stop Collector and Send Error Message if Collector Fails to Stop Completely
        if (!Collector.collect_stop()) {
            //telemetry.addData("Collector: ", "Error - Not Stopped");
        } else {
            //telemetry.addData("Collector: ", "Success - Stopped");
        }

        telemetry.update();
    }
}