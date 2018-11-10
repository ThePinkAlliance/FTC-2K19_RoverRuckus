package org.firstinspires.PinkCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.PinkCode.Robot.Hardware;
import org.firstinspires.PinkCode.Robot.Presets;
import org.firstinspires.PinkCode.Subsystems.Base;
import org.firstinspires.PinkCode.Subsystems.Extender;
import org.firstinspires.PinkCode.Subsystems.Lift;

@Autonomous (name="Land & Park", group="Autonomous")
public class AutoLand extends OpMode {

    private static int i = 0;
    public Hardware robot = new Hardware();


    public void init() {
        // Initialize Robot Hardware
        robot.init(hardwareMap);
        robot.collector_rotate.setPosition(Presets.COLLECTOR_STOW_POSITION);
    }
        public void loop(){

        //Releases robot from lander
        telemetry.addData("Status","Releasing robot");
        telemetry.update();
        //releases hook holding robot up
        robot.hook.setPosition(-1);
        //for 3 seconds causes the robot to slowly release from the lander
        for (i = 0; i < 146000; i++)
        {
            Lift.lift_by_command(Presets.LIFT_RELEASE_BREAK);
        }
        Lift.lift_to_position(Presets.LIFT_RELEASE_POSITION);
        Base.drive_by_command(1,1);
        Lift.lift_to_position(Presets.LIFT_SORT_POSITION);
        Extender.extend_to_position(Presets.EXTEND_COLLECT_POSITION);
        telemetry.addData("Status", "Release Successful!");
        telemetry.update();
        stop();
    }
}
