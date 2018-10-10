package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.PinkCode.Robot.Controls;
import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Collector;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;

import java.util.Timer;

// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Auto", group="Autonomous")
public class Auto extends OpMode{

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
    private double runTime = getRuntime();
    private double wait;



    public Hardware robot = new Hardware();

    public void init() {
        robot.init(hardwareMap);
    }


    public void loop() {
        if(Controls.base_x)
        {
            //Set main auto path to false
            telemetry.addData("Say", "Starting Position: Side");
            Center = false;

        }
        else if(Controls.base_b)
        {
            //Set alternate auto path to false
            Side = false;
            telemetry.addData("Say", "Starting Position: Center");
        }
        else
        {
            Side = false;
            telemetry.addData("Say", "Starting Position: Center");
        }


        //Alternate Auto Path
        if(Side = true)
        {
            //Set everything to correct positions
            Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
            Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
            //TODO: Scan for gold cube

            //Turn to and collect gold cube
            if(left)
            {
                //Turn to the left
                Base.drive_by_command(.25 , 0);
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                //Collect Gold Cube
                Collector.collect();
            }
            else if(right)
            {
                //Turn to the right
                Base.drive_by_command(0, .25);
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                //Collect Gold Cube
                Collector.collect();
            }
            else
            {
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_MID_GOLD_POSITION);
                //Collect Cube
                Collector.collect();
            }
            //Score Cube
            Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
            //TODO: Scoring Code
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

        //Main Auto Path
        else
        {
            //Set everything to correct positions
            Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
            Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
            //TODO: Scan for gold cube

            //Turn to and collect gold cube
            if(left)
            {
                //Turn to the left
                Base.drive_by_command(.25 , 0);
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                //Collect Gold Cube
                Collector.collect();
            }
            else if(right)
            {
                //Turn to the right
                Base.drive_by_command(0, .25);
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_GOLD_POSITION);
                //Collect Gold Cube
                Collector.collect();
            }
            else
            {
                //Extend to gold cube
                Extender.extend_to_position(Presets.EXTEND_MID_GOLD_POSITION);
                //Collect Cube
                Collector.collect();
            }
            //Score cube and set collector to just outside the crater
            Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
            Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
            //TODO: Scoring Code
            Extender.extend_to_position(Presets.EXTEND_CRATER_POSITION);
            //While Loop for continuous scoring
            while(Cycle)
            {
                //if timer reaches 28 seconds, extend to crater
                if (runTime > 28000)
                {
                    //Extend to crater
                    Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
                    //End
                    stop();
                }
                //if timer > 28s continuous scoring
                else
                {
                    //collect game pieces
                    Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
                    //wait 2 seconds to get collector past crater wall before collecting
                    //TODO: add delay
                    Collector.collect();
                    //move collector back, sort, and score
                    Extender.extend_to_position(Presets.EXTEND_SORT_POSITION);
                    Lift.lift_to_position(Presets.LIFT_SCORE_POSITION);
                    Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
                }
            }
        }
    }
}
