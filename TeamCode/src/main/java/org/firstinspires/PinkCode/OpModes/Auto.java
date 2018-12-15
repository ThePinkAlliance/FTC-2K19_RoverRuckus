package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Calculations.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;
import org.firstinspires.PinkCode.Subsystems.Scorer;
import org.firstinspires.PinkCode.OpModes.Phone;
import org.firstinspires.PinkCode.Subsystems.Subsystem;

import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.center_initialize;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.center_stop;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.continuous_score;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.lower_robot;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.release_hook;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.set;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.scan_middle;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.score_and_set;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.sample;


// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Auto", group="Autonomous")
public class Auto extends OpMode{
    // Set Up Center Auto Case Statement
    static int phase = 0;
    public center_auto center_auto;
    public enum center_auto {
        center_stop,
        center_initialize,
        release_hook,
        lower_robot,
        sample,
        scan_middle,
        set,
        score_and_set,
        continuous_score
    }

    @Override
    public double getRuntime() {
        return super.getRuntime();
    }

    private static boolean Cycle = true;
    private static boolean left = false;
    private static boolean middle = false;
    private static boolean right = false;
    private static boolean flag = true;
    private ElapsedTime runtime = new ElapsedTime();
    private double markedTime;
    public Hardware robot = new Hardware();


    public void init() {
        // Initialize Robot Hardware
        Subsystem.robot.init(hardwareMap);
        robot.init(hardwareMap);
        robot.hook.setPosition(Presets.HOOK_LATCH_POSITION);
        Subsystem.robot.score_flap.setPosition(Presets.SCORER_FLAP_OPEN);
        Subsystem.robot.score_kicker.setPosition(Presets.SCORER_KICKER_STOW);
        Subsystem.robot.right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Subsystem.robot.left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        phase = 0;
    }
    public void loop() {
        // Center Auto Switch Statement
        telemetry.addData("Auto Phase: ", phase);
        telemetry.update();

        switch (phase) {
            case 0: // Initialize
                markedTime = runtime.milliseconds();
                phase = 1;
                break;

            case 1: // Extend the collector arm out to release the head
                Collector.collect_stop();
                Collector.rotate_to_position(0);
                Extender.extend_to_position(200);
                Lift.lift_by_command(Presets.LIFT_RELEASE_BREAK);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                robot.hook.setPosition(0.5);
                if ((runtime.milliseconds() - markedTime) > 500) {
                    markedTime = runtime.milliseconds();
                    phase = 2;
                }
                break;

            case 2: // Unlatch the tower hook so we can land
                Collector.collect_stop();
                Collector.rotate_to_position(0);
                Extender.extend_stop();
                Lift.lift_by_command(Presets.LIFT_RELEASE_BREAK);  // Pull a bit to free the hook
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                robot.hook.setPosition(0.5);
                // Wait for 2 seconds
                if (runtime.milliseconds() - markedTime > 1000) {
                    markedTime = runtime.milliseconds();
                    phase = 4;
                }
                break;

            case 4: // Raise the tower to land the robot and clear the hook
                Collector.collect_stop();
                Collector.rotate_to_position(-80);
                Extender.extend_stop();
                Lift.lift_by_command(Presets.LIFT_SCORE_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                if (Subsystem.robot.right_lift.getCurrentPosition() > Presets.LIFT_CLEAR_POSITION){
                    markedTime = runtime.milliseconds();
                    Subsystem.robot.left_drive.setTargetPosition(300);
                    Subsystem.robot.right_drive.setTargetPosition(300);
                    Subsystem.robot.right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Subsystem.robot.left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    phase = 5;
                }
                break;

            case 5: // Drive out a tiny bit to clear the lander
                Collector.collect_stop();
                Collector.rotate_to_position(-110);
                Extender.extend_stop();
                Lift.lift_stop();
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                Base.drive_by_command(0.3, 0.3);
                if (runtime.milliseconds() - markedTime > 2000) {
                    phase = 6;
                }
                break;

            case 6: // Turn towards the gold cube AND lower the collector bucket
                Collector.collect_stop();
                Collector.rotate_to_position(-110);
                Extender.extend_stop();
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                Base.drive_stop();
                // Change the target position here and wait for a couple of seconds
                markedTime = runtime.milliseconds();
                phase = 7;
                break;

            case 7:  // Collect the gold cube
                Collector.collect();
                Collector.rotate_to_position(-110);  // Stowed is 0, deployed is -130
                Extender.extend_to_position(600);
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                if (runtime.milliseconds() - markedTime > 2000) {
                    markedTime = runtime.milliseconds();
                    phase = 8;
                }
                break;

            case 8:  // Raise the collector bucket
                Collector.collect();
                Collector.rotate_to_position(-60);
                Extender.extend_to_position(600);
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                if (runtime.milliseconds() - markedTime > 1000) {
                    phase = 9;
                }
                break;

            case 9:  // Turn back to the center
                Collector.collect_stop();
                Collector.rotate_to_position(-60);
                Extender.extend_to_position(600);
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                if (runtime.milliseconds() - markedTime > 1000) {
                    phase = 10;
                }
                break;

            case 10:  // Extend the arm to enter the crater
                Collector.collect_stop();
                Collector.rotate_to_position(-60);
                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                if (runtime.milliseconds() - markedTime > 2000) {
                    phase = 11;
                }
                break;

            case 11:  // Lower the collector bucket to start teleop at zero = down
                Collector.collect_stop();
                Collector.rotate_to_position(0);  // Drop down so teleop starts here at zero
                Extender.extend_stop();
                Lift.lift_stop();
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                break;
        }

        Subsystem.set_motor_powers();
        Subsystem.set_servo_positions();
    }
}