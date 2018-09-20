package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp (name = "PINK Teleop")
public class Teleop extends OpMode {

    // Declare OpMode Members
    private Hardware robot = new Hardware();
    private double  leftJoystickBase;
    private double  rightJoystickBase;
    private boolean rightBumperBase;
    private boolean leftBumperBase;
    private double  leftJoystickTower;
    private double  rightJoystickTower;
    private boolean buttonATower;
    private boolean buttonBTower;
    private boolean toggleATower;
    private boolean toggleBTower;
    private double  leftWheelsMotorCmd;
    private double  rightWheelsMotorCmd;
    private double  towerExtendCommand;
    private double  towerRotateCommand;
    private double  towerCollectCmd;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init () {
        // Initialize the hardware variables.
        robot.init(hardwareMap);
        toggleATower = false;
        toggleBTower = false;

        // Send telemetry message to signify robot waiting
        telemetry.addData("Say", "Hello Driver");
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop () {
    }

    // Code to run ONCE when the driver hits PLAY
    @Override
    public void start () {
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop () {

        // BASE CONTROL /////////////////////////////////////////////////////
        leftJoystickBase = -gamepad1.left_stick_y;
        rightJoystickBase = -gamepad1.right_stick_y;

        if (gamepad1.left_trigger > 0.2) {
            leftWheelsMotorCmd = leftJoystickBase * 1.0;
            rightWheelsMotorCmd = rightJoystickBase * 1.0;
        }
        else {
            leftWheelsMotorCmd = leftJoystickBase * 0.7;
            rightWheelsMotorCmd = rightJoystickBase * 0.7;
        }

        robot.leftDrive1.setPower(leftWheelsMotorCmd);
        robot.leftDrive2.setPower(leftWheelsMotorCmd);
        robot.rightDrive1.setPower(rightWheelsMotorCmd);
        robot.rightDrive2.setPower(rightWheelsMotorCmd);


        // TOWER CONTROL /////////////////////////////////////////////////////
        leftJoystickTower = -gamepad2.left_stick_y;
        rightJoystickTower = -gamepad2.right_stick_y;
        buttonATower = gamepad2.a;
        buttonBTower = gamepad2.b;

        towerExtendCommand = leftJoystickTower;
        if (-0.2 < rightJoystickTower || 0.2 > rightJoystickTower){
            towerRotateCommand = rightJoystickTower;
        }
        else if (buttonATower && toggleATower) {
            toggleATower = false;
            towerRotateCommand = Presets.TOWER_COLLECT_POS;
        }
        else if (buttonATower && !toggleATower) {
            toggleATower = true;
        }
        else if (buttonBTower && toggleBTower) {
            towerRotateCommand = Presets.TOWER_SCORE_POS;
        }
        else if (buttonBTower && !toggleBTower) {
            toggleBTower = true;
        }
        else {
            towerRotateCommand = Presets.TOWER_TRAVEL_POS;
        }

        robot.towerExtend1.setPower(towerExtendCommand);
        robot.towerExtend2.setPower(towerExtendCommand);
        robot.towerRotate.setPower(towerRotateCommand);


        // COLLECTOR CONTROL /////////////////////////////////////////////////////
        rightBumperBase = gamepad1.right_bumper;
        leftBumperBase = gamepad1.left_bumper;

        if (rightBumperBase) {
            towerCollectCmd = Presets.COLLECTOR_COLLECT;
        }
        else if (leftBumperBase) {
            towerCollectCmd = Presets.COLLECTOR_EJECT;
        }
        else {
            towerCollectCmd = Presets.COLLECTOR_HOLD;
        }

        robot.collectorIntake.setPower(towerCollectCmd);

        // TELEMETRY /////////////////////////////////////////////////////

    }

    // Code to run ONCE after the driver hits STOP
    @Override
    public void stop () {
    }
}