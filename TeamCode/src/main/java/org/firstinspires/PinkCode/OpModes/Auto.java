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
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.collect;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.collector_rotate;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.collector_sort;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.extender_collect;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.extender_retract;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.lift_lower;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.lift_raise;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.lower_robot;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.release_hook;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.score;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.set;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.scan_middle;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.score_and_set;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.sample;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.unscore;


// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Auto", group="Autonomous")
public class Auto extends OpMode {
    // Set Up Center Auto Case Statement
<<<<<<< HEAD
    static int phase = 0;
    public center_auto center_auto;
=======
    private center_auto center_auto;

>>>>>>> 0385351ef046579bb1d16c41b67bfabc7b96d692
    public enum center_auto {
        center_stop,
        center_initialize,
        release_hook,
        lower_robot,
        sample,
        scan_middle,
        set,
        score_and_set,
        extender_collect,
        collect,
        collector_rotate,
        extender_retract,
        collector_sort,
        lift_raise,
        scorer_rotate,
        score,
        unscore,
        lift_lower,
    }

    @Override
    public double getRuntime() {
        return super.getRuntime();
    }

    private static boolean Cycle = true;
    private static boolean left = false;
    private static boolean middle = false;
    private static boolean right = false;
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

<<<<<<< HEAD
        switch (phase) {
            case 0: // Initialize
                markedTime = runtime.milliseconds();
                phase = 1;
                break;
=======
            case center_initialize:
                robot.hook.setPosition(0);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                //Reset All Encoders
                //Scan for gold cube
                //Scans area for gold cube
                telemetry.addData("Status", "Scanning for Cube");
                telemetry.update();
>>>>>>> 0385351ef046579bb1d16c41b67bfabc7b96d692

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

<<<<<<< HEAD
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
=======
            case release_hook:
                //Releases robot from lander
                telemetry.addData("Status", "Releasing Hook");
                telemetry.update();
                //releases hook holding robot up
                Extender.extend_to_position(Presets.EXTEND_RELEASE_COLLECTOR);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                robot.hook.setPosition(0.5);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                Lift.lift_by_command(Presets.LIFT_RELEASE_BREAK);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                // Wait for 3 seconds
                if (runtime.seconds() - markedTime > 5) {
                    center_auto = lower_robot;
                } else {
                    center_auto = release_hook;
                }
                break;

            case lower_robot:
                telemetry.addData("Status: ", "Lowering Robot");
                telemetry.update();
                Lift.lift_to_position(Presets.LIFT_RELEASE_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                markedTime = runtime.seconds();
                center_auto = sample;
>>>>>>> 0385351ef046579bb1d16c41b67bfabc7b96d692
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

<<<<<<< HEAD
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
=======
            case sample:
//                if (left) {
//                    //If left area is gold cube
//                    telemetry.addData("Status", "Scanning for left");
//                    telemetry.update();
//                    //Turn to the left
//                    Base.drive_by_command(.25, 0);
//                    //Extend to gold cube
//                    Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
//                    //Collect Gold Cube
//                    Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
//                    Collector.collect();
//                    Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
//                    Collector.eject();
//                    Subsystem.set_motor_powers();
//                    Subsystem.set_servo_positions();
//                    center_auto = score_and_set;
//                    break;
//                } else if(right) {
//                    //if right are is gold cube
//                    telemetry.addData("Status", "Scanning for right");
//                    telemetry.update();
//                    //Turn to the right
//                    Base.drive_by_command(0, .25);
//                    //Extend to gold cube
//                    Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
//                    //Collect Gold Cube
//                    Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
//                    Collector.collect();
//                    Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
//                    Collector.eject();
//                    Subsystem.set_motor_powers();
//                    Subsystem.set_servo_positions();
//                    center_auto = set;
//                    break;
//                } else if(!middle) {
                //If gold cube is centered
                telemetry.addData("Status", "Scanning for middle");
                telemetry.update();
                //Extend to gold cube
                Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                Collector.collect();
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                Extender.extend_to_position(Presets.EXTEND_MID_GOLD_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if (runtime.seconds() - markedTime > 3) {
                    markedTime = runtime.seconds();
                    center_auto = set;
                    break;
                } else {
                    center_auto = scan_middle;
                }

//                } else{
//                    center_auto = set;
//                    break;
//                }
            case set:
                telemetry.addData("Status", "Setting");
                telemetry.update();
                Collector.eject();
                Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
                if (runtime.seconds() - markedTime > 2) {
                    center_auto = extender_retract;
                    break;
                } else {
                    center_auto = set;
                }

//            case score_and_set:
//                //Scores material from sample and sets all motors to desired location
//                telemetry.addData("Status", "Scoring and Setting");
//                telemetry.update();
//                //Score cube and set collector to just outside the crater
//                Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Collector.rotate_to_position(Presets.COLLECTOR_SORT_POSITION);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_KICK);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_STOW);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
//                Subsystem.set_motor_powers();
//                Subsystem.set_servo_positions();
//                markedTime = runtime.seconds();
//                center_auto = extender_collect;
//                break;
            //While Loop for continuous scoring
// Stuff I Changed
            case extender_collect:
                //Continuously scores materials into lander
                telemetry.addData("Status", "extender_collect");
                telemetry.update();
                Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
                Subsystem.set_motor_powers();
                Subsystem.set_servo_positions();
                if (Subsystem.robot.right_extend.getCurrentPosition() >= Presets.EXTEND_COLLECT_POSITION) {
                    markedTime = runtime.seconds();
                    center_auto = collect;
                    break;
                } else {
                    center_auto = extender_collect;
                    break;
                }
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
                    center_auto = extender_collect;
                    break;
                }
                else{
                    center_auto = unscore;
                    break;
>>>>>>> 0385351ef046579bb1d16c41b67bfabc7b96d692
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