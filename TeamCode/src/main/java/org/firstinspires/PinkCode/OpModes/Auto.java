package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Calculations.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;
import org.firstinspires.PinkCode.Subsystems.Scorer;
import org.firstinspires.PinkCode.OpModes.Phone;

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
        Base.robot.init(hardwareMap);
        robot.init(hardwareMap);
        Collector.robot.init(hardwareMap);
        Extender.robot.init(hardwareMap);
        Lift.robot.init(hardwareMap);
        Scorer.robot.init(hardwareMap);
        center_auto = center_initialize;
    }
    public void loop() {
        // Center Auto Switch Statement
        switch (center_auto) {
            case center_stop:
                // Does Nothing Until center_auto is set to center_initialize
                telemetry.addData("Status: ", "Stopped");
                telemetry.update();
                break;

            case center_initialize:
                //Reset All Encoders
                //Scan for gold cube
                //Scans area for gold cube
                telemetry.addData("Status", "Scanning for Cube");
                telemetry.update();

                //TODO: Scan for Gold Cube
                center_auto = release_hook;
                markedTime = runtime.seconds();
                break;

            case release_hook:
                //Releases robot from lander
                telemetry.addData("Status", "Releasing Hook");
                telemetry.update();
                //releases hook holding robot up
                robot.hook.setPosition(0.5);
                Lift.lift_by_command(Presets.LIFT_RELEASE_BREAK);
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
                center_auto = sample;
                break;



            case sample:
                if (left) {
                    //If left area is gold cube
                    telemetry.addData("Status", "Scanning for left");
                    telemetry.update();
                    //Turn to the left
                    Base.drive_by_command(.25, 0);
                    //Extend to gold cube
                    Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                    //Collect Gold Cube
                    Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
                    Collector.collect();
                    Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
                    Collector.eject();
                    center_auto = score_and_set;
                    break;
                } else if(right) {
                    //if right are is gold cube
                    telemetry.addData("Status", "Scanning for right");
                    telemetry.update();
                    //Turn to the right
                    Base.drive_by_command(0, .25);
                    //Extend to gold cube
                    Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                    //Collect Gold Cube
                    Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
                    Collector.collect();
                    Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
                    Collector.eject();
                    center_auto = set;
                    break;
                } else if(!middle) {
                    //If gold cube is centered
                    telemetry.addData("Status", "Scanning for middle");
                    telemetry.update();
                    //Extend to gold cube
                    Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
                    Collector.collect();
                    Extender.extend_to_position(Presets.EXTEND_MID_GOLD_POSITION);
                    //Collect Cube
                    if (runtime.seconds() - markedTime > 3) {
                        center_auto = set;
                        markedTime = runtime.seconds();
                        break;
                    } else {
                        center_auto = scan_middle;
                    }
                    break;
                } else{
                    center_auto = set;
                    break;
                }
            case set:
                telemetry.addData("Status", "Setting");
                telemetry.update();
                Collector.eject();
                Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
                if (runtime.seconds() - markedTime > 3) {
                    telemetry.addData("Status:", "Stop - Success");
                    telemetry.update();
                    center_auto = center_stop;
                }else {
                    center_auto = set;
                }
                break;
            case score_and_set:
                //Scores material from sample and sets all motors to desired location
                telemetry.addData("Status", "Scoring and Setting");
                telemetry.update();
                //Score cube and set collector to just outside the crater
                Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
                Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
                Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
                Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                while (flag)
                    if (robot.left_extend.getCurrentPosition() >= Presets.LIFT_SCORE_POSITION || robot.right_extend.getCurrentPosition() >= Presets.LIFT_SCORE_POSITION) {
                        Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
                        //Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_KICK);
                        flag = false;
                    }
                //Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_STOW);
                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                center_auto = continuous_score;
                break;
            //While Loop for continuous scoring

            case continuous_score:
                //Continuously scores materials into lander
                telemetry.addData("Status", "Continuous Scoring");
                telemetry.update();
                while (Cycle) {
                    //if timer reaches 28 seconds, extend to crater
                    if (runtime.seconds() >= 28) {
                        //Extend to crater
                        Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
                        Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                        //End
                        stop();
                    }
                    //if timer > 28s continuous scoring
                    else {
                        //collect game pieces
                        while (!flag) {
                            if (robot.left_lift.getCurrentPosition() <= Presets.LIFT_SCORE_POSITION && robot.right_lift.getCurrentPosition() <= Presets.LIFT_SCORE_POSITION) {
                                Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
                                flag = true;
                            } else {
                                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                            }
                        }
                        //If statement to not allow collector to drop before inside crater.
                        while (flag)
                            if (robot.left_extend.getCurrentPosition() >= Presets.EXTEND_COLLECT_POSITION && robot.right_extend.getCurrentPosition() >= Presets.EXTEND_COLLECT_POSITION) {
                                Collector.rotate_to_position(Presets.COLLECTOR_COLLECT_POSITION);
                                Collector.collect();
                                Collector.rotate_to_position(Presets.COLLECTOR_TRAVEL_POSITION);
                                Collector.eject();
                                flag = false;
                            } else {
                                Collector.collect_stop();
                            }
                        //move collector back, sort, and score
                        while (!flag) {
                            if (robot.left_lift.getCurrentPosition() <= Presets.LIFT_SORT_POSITION && robot.right_lift.getCurrentPosition() <= Presets.LIFT_SORT_POSITION) {
                                Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                                flag = true;
                            } else {
                                Extender.extend_stop();
                            }
                        }
                        Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_CLOSED);
                        Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
                        Scorer.score_rotate_to_position(Presets.SCORER_SCORE);
                        if (markedTime - runtime.seconds() < 3000000) {
                            markedTime = runtime.seconds();
                            Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                            while (flag)
                                if (robot.left_extend.getCurrentPosition() >= Presets.EXTEND_CRATER_POSITION && robot.right_extend.getCurrentPosition() >= Presets.EXTEND_CRATER_POSITION) {
                                    Scorer.score_flap_rotate_to_position(Presets.SCORER_FLAP_OPEN);
                                    //Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_KICK);
                                    flag = false;
                                }
                        }
                        //Scorer.score_kicker_rotate_to_position(Presets.SCORER_KICKER_STOW);
                        Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                    }
                }
        }
    }
}