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
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.wait;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.drive_forward;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.lift_lower;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.extend_crater;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.collect;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.collector_rotate;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.extender_retract;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.collector_sort;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.lift_raise;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.score;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.unscore;


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
        wait,
        lower_robot,
        drive_forward,
        sample,
        lift_lower,
        extend_crater,
        scan_middle,
        set,
        score_and_set,
        continuous_score,
        collect,
        collector_rotate,
        extender_retract,
        collector_sort,
        lift_raise,
        scorer_rotate,
        score,
        unscore,




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
        center_auto = center_initialize;
    }
    public void loop() {
        // Center Auto Switch Statement
        telemetry.addData("Auto Phase: ", phase);
        telemetry.update();

        switch (center_auto) {
            case center_initialize:// Initialize
                markedTime = runtime.milliseconds();
                center_auto = release_hook;
                break;

            case release_hook: // Extend the collector arm out to release the head
                Collector.collect_stop();
                Collector.rotate_to_position(0);
                Extender.extend_to_position(200);
                Lift.lift_by_command(Presets.LIFT_RELEASE_BREAK);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                robot.hook.setPosition(0.5);
                if ((runtime.milliseconds() - markedTime) > 500) {
                    markedTime = runtime.milliseconds();
                    center_auto = wait;
                }
                break;

            case wait: // Unlatch the tower hook so we can land
                Collector.collect_stop();
                Collector.rotate_to_position(0);
                Extender.extend_stop();
                Lift.lift_by_command(Presets.LIFT_RELEASE_BREAK);  // Pull a bit to free the hook
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                robot.hook.setPosition(0.5);
                // Wait for 2 seconds
                if (runtime.milliseconds() - markedTime > 1000) {
                    markedTime = runtime.milliseconds();
                    center_auto = lower_robot;
                }
                break;

            case lower_robot: // Raise the tower to land the robot and clear the hook
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
                    center_auto = drive_forward;
                }
                break;

            case drive_forward: // Drive out a tiny bit to clear the lander
                Collector.collect_stop();
                Collector.rotate_to_position(-110);
                Extender.extend_stop();
                Lift.lift_stop();
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                Base.drive_by_command(0.3, 0.3);
                if (runtime.milliseconds() - markedTime > 2000) {
                    center_auto = sample;
                }
                break;

            case sample: // Turn towards the gold cube AND lower the collector bucket
                Collector.collect_stop();
                Collector.rotate_to_position(-110);
                Extender.extend_stop();
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                Base.drive_stop();
                // Change the target position here and wait for a couple of seconds
                markedTime = runtime.milliseconds();
                center_auto = lift_lower;
                break;

            case lift_lower:  // Collect the gold cube
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

//            case 8:  // Raise the collector bucket
//                Collector.collect();
//                Collector.rotate_to_position(-60);
//                Extender.extend_to_position(600);
//                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
//                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
//                if (runtime.milliseconds() - markedTime > 1000) {
//                    phase = 9;
//                }
//                break;
//
//            case 9:  // Turn back to the center
//                Collector.collect_stop();
//                Collector.rotate_to_position(-60);
//                Extender.extend_to_position(600);
//                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
//                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
//                if (runtime.milliseconds() - markedTime > 1000) {
//                    phase = 10;
//                }
//                break;

            case extend_crater:  // Extend the arm to enter the crater
                Collector.collect_stop();
                Collector.rotate_to_position(-60);
                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                if (runtime.milliseconds() - markedTime > 2000) {
                    center_auto = center_stop;
                }
                break;

            case collect:
                telemetry.addData("Status: ", "collect");
                telemetry.update();
                Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
                Collector.collect();
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if (runtime.seconds() - markedTime > 2) {
                    markedTime = runtime.seconds();
                    center_auto = collector_rotate;
                    break;
                } else {
                    center_auto = collect;
                    break;
                }
            case collector_rotate:
                telemetry.addData("Status: ", "collector_rotate");
                telemetry.update();
                Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
                Collector.eject();
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if(runtime.seconds() - markedTime > 2) {
                    center_auto = extender_retract;
                    break;
                }
                else {
                    center_auto = collector_rotate;
                    break;
                }
            case extender_retract:
                Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if (Subsystem.robot.right_extend.getCurrentPosition() <= Presets.EXTEND_SORT_POSITION) {
                    markedTime = runtime.seconds();
                    center_auto = collector_sort;
                    break;
                }
                else
                {
                    center_auto = extender_retract;
                    break;
                }
            case collector_sort:
                Collector.rotate_to_position(Presets.COLLECTOR_SORT_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if(runtime.seconds() - markedTime > 2) {
                    center_auto = lift_raise;
                    break;
                }else {
                    center_auto = collector_sort;
                    break;
                }
            case lift_raise:
                Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
                Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if(Subsystem.robot.right_lift.getCurrentPosition() >= Presets.LIFT_SCORE_POSITION) {
                    markedTime = runtime.seconds();
                    center_auto = score;
                    break;
                }else {
                    center_auto = lift_raise;
                    break;
                }
            case score:
                Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if(runtime.seconds() - markedTime > 1.5)
                {
                    Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
                    Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_KICK);
                    Subsystem.set_motor_powers();
                    Subsystem.set_servo_positions();
                    Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_STOW);
                    Subsystem.set_servo_positions();
                    Subsystem.set_motor_powers();
                    center_auto = unscore;
                    break;
                }
                else {
                    center_auto = score;
                    break;
                }

            case unscore:
                Scorer.score_rotate_to_position(Presets.SCORER_COLLECT);
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if(Subsystem.robot.right_lift.getCurrentPosition() <= Presets.LIFT_SORT_POSITION) {
                    center_auto = extend_crater;
                    break;
                }
                else{
                    center_auto = unscore;
                    break;
                }

            case center_stop:  // Lower the collector bucket to start teleop at zero = down
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