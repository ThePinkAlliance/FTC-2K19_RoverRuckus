package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.PinkCode.Robot.Controls;
import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;

import static org.firstinspires.PinkCode.OpModes.Auto.auto_picker.side;
import static org.firstinspires.PinkCode.OpModes.Auto.auto_picker.center;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.center_initialize;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.scan;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.scan_left;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.scan_middle;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.scan_right;
import static org.firstinspires.PinkCode.OpModes.Auto.center_auto.score_and_set;
import static org.firstinspires.PinkCode.OpModes.Auto.side_auto.side_initialize;



// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Auto", group="Autonomous")
public class Auto extends OpMode {

    // Set Up Auto Picker Case Statement
    public auto_picker auto_picker;
    public enum auto_picker {
        center,
        side
    }
    // Set Up Center Auto Case Statement
    public center_auto center_auto;
    public enum center_auto {
        center_stop,
        center_initialize,
        scan,
        scan_left,
        scan_right,
        scan_middle,
        score_and_set,
        continuous_score
    }
    // Set Up Side Auto Case Statement
    public side_auto side_auto;
    public enum side_auto {
        side_stop,
        side_initialize,
        scan,
        scan_left,
        scan_right,
        scan_middle,
        score_and_set,
        score
    }


    @Override
    public double getRuntime() {
        return super.getRuntime();
    }

    private static boolean Center = true;
    private static boolean Side = true;
    private static boolean Cycle = true;
    private static boolean left = false;
    private static boolean middle = false;
    private static boolean right = false;
    private static boolean flag = true;
    private double runTime = getRuntime();
    public Hardware robot = new Hardware();


    public void init() {
        // Initialize Robot Hardware
        robot.init(hardwareMap);

        // Set Auto Program Starting Position
        if(Controls.base_x) {
            // Select Auto Program for Starting on the Side Facing the Depot
            auto_picker = side;
            // Add Telemetry to Tell the Drivers What Starting Position is Selected
            telemetry.addData("Starting Position: ", "Side");
        } else if(Controls.base_b) {
            // Select Auto Program for Starting on the Side Facing the Crater
            auto_picker = center;
            // Add Telemetry to Tell the Drivers What Starting Position is Selected
            telemetry.addData("Starting Position: ", "Center");
        } else {
            // Add Telemetry to Tell the Drivers That No Auto is Selected
            telemetry.addData("Starting Position: ", "Error - No Position Chosen");
        }

        // Switch Statement to Pick an Autonomous Program
        switch (auto_picker) {
            // Case if Drivers Don't Select an Autonomous Program
            default:
            // Select Auto Program for Starting Facing the Crater on Either Alliance
            case center:
                // Send Telemetry to Tell Drivers That the Robot is Resetting Encoders
                telemetry.addData("Status: ", "Resetting Encoders");
                telemetry.update();
                // Reset Encoders
                robot.left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.right_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.left_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.collect.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.right_extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.left_extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.collect.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.right_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.left_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                // Set Starting Position to Center
                center_auto = center_initialize;
                // Send Telemetry to Tell Drivers That the Robot is Ready
                telemetry.addData("Status: ", "Waiting for Start");
                telemetry.update();

            // Select Auto Program for Starting Facing the Depot on Either Alliance
            case side:
                // Send Telemetry to Tell Drivers That the Robot is Resetting Encoders
                telemetry.addData("Status: ", "Resetting Encoders");
                telemetry.update();
                // Reset Encoders
                robot.left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.right_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.left_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.collect.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.right_extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.left_extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.collect.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.right_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.left_lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                // Set Starting Position to Side
                side_auto = side_initialize;
                // Send Telemetry to Tell Drivers That the Robot is Ready
                telemetry.addData("Status: ", "Waiting for Start");
                telemetry.update();
        }
    }

    public void loop() {
        // Center Auto Switch Statement
        switch (center_auto) {
            case center_stop:
                // Does Nothing Until center_auto is set to center_initialize
            case center_initialize:
                //Reset All Encoders
                //Scan for gold cube
            case scan:
                telemetry.addData("Status", "Scanning for Cube");
                telemetry.update();
                //TODO: Scan for gold cube
                if(left)
                {
                    center_auto = scan_left;
                }
                else if (right)
                {
                    center_auto = scan_right;
                }
                else if(Center)
                {
                    center_auto = scan_middle;
                }
                else
                {
                    telemetry.addData("Status", "Did not see gold");
                    telemetry.update();
                    center_auto = score_and_set;
                }
                     case scan_left:
                        telemetry.addData("Status", "Scanning for left");
                        telemetry.update();
                        //Turn to the left
                        Base.drive_by_command(.25, 0);
                        //Extend to gold cube
                        Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                        //Collect Gold Cube
                        Collector.collect();
                    case scan_right:
                        telemetry.addData("Status", "Scanning for right");
                        telemetry.update();
                        //Turn to the right
                        Base.drive_by_command(0, .25);
                        //Extend to gold cube
                        Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                        //Collect Gold Cube
                        Collector.collect();
                    case scan_middle:
                        telemetry.addData("Status", "Scanning for middle");
                        telemetry.update();
                        //Extend to gold cube
                        Extender.extend_to_position(Presets.EXTEND_MID_GOLD_POSITION);
                        //Collect Cube
                        Collector.collect();
                    case score_and_set:
                        telemetry.addData("Status", "Scoring and Setting");
                        telemetry.update();
                        //Score cube and set collector to just outside the crater
                        Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                        Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
                        //TODO: Scoring Code
                        Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                        //While Loop for continuous scoring
                    case continuous_score:
                        telemetry.addData("Status", "Continuous Scoring");
                        telemetry.update();
                        while (Cycle) {
                            //if timer reaches 28 seconds, extend to crater
                            if (runTime > 28000) {
                                //Extend to crater
                                Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
                                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                                //End
                                stop();
                            }
                            //if timer > 28s continuous scoring
                            else {
                                //collect game pieces
                                while(flag)
                                {
                                    if (robot.left_lift.getCurrentPosition() <= Presets.LIFT_SCORE_POSITION && robot.right_lift.getCurrentPosition() <= Presets.LIFT_SCORE_POSITION) {
                                        Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
                                        flag = false;
                                    } else {
                                        Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                                    }
                                }
                                //If statement to not allow collector to drop before inside crater.
                                while (!flag)
                               if(robot.left_extend.getCurrentPosition() >= Presets.EXTEND_COLLECT_POSITION && robot.right_extend.getCurrentPosition() >= Presets.EXTEND_COLLECT_POSITION)
                               {
                                   Collector.collect();
                                   flag = true;
                               }
                               else
                               {
                                   Collector.hold();
                               }
                                //move collector back, sort, and score
                               while(flag)
                               {
                                   if (robot.left_lift.getCurrentPosition() <= Presets.LIFT_SORT_POSITION && robot.right_lift.getCurrentPosition() <= Presets.LIFT_SORT_POSITION) {
                                       Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                                       flag = false;
                                   }
                                   else
                                   {
                                       Extender.extend_hold();
                                   }
                               }
                                Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
                                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                                Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                                flag = true;



                            }
                        }
        }
        // Side Auto Switch Statement
        switch (side_auto) {
            case side_stop:
                // Does Nothing Until side_auto is set to side_initialize
            case side_initialize:
                //Set everything to correct positions
            case scan:
                telemetry.addData("Status", "Scanning for Cube");
                telemetry.update();
                //TODO: Scan for gold cube
                if(left)
                {
                    center_auto = scan_left;
                }
                else if (right)
                {
                    center_auto = scan_right;
                }
                else
                {
                    center_auto = scan_middle;
                }
            case scan_left:
                telemetry.addData("Status", "Scanning for left");
                telemetry.update();
                //Turn to the left
                Base.drive_by_command(.25, 0);
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                //Collect Gold Cube
                Collector.collect();
            case scan_right:
                telemetry.addData("Status", "Scanning for right");
                telemetry.update();
                //Turn to the right
                Base.drive_by_command(0, .25);
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                //Collect Gold Cube
                Collector.collect();
            case scan_middle:
                telemetry.addData("Status", "Scanning for middle");
                telemetry.update();
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_MID_GOLD_POSITION);
                //Collect Cube
                Collector.collect();
            case score_and_set:
                telemetry.addData("Status", "Scoring and Setting");
                telemetry.update();
                //Score cube and set collector to just outside the crater
                Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
                //TODO: Scoring Code
                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
            case score:
                telemetry.addData("Status", "Scoring");
                telemetry.update();
                //Drive forward
                Base.drive_by_command(3,3);
                //Turn Right
                Base.drive_by_command(-.5,.5);
                //extend into crater and collect
                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
                Collector.collect();
                //Turn back and sort
                Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                Base.drive_by_command(.5,-.5);
                //Score
                //TODO: Scoring Code
                //Drive forward
                Base.drive_by_command(3,3);
                //Turn Right
                Base.drive_by_command(-.5,.5);
                //extend into crater
                Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
        }
    }
}