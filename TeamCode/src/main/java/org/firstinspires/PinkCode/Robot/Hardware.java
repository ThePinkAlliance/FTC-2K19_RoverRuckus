package org.firstinspires.PinkCode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Class to Define the Hardware of the Robot
public class Hardware {
    // Motors
    public DcMotor right_drive; // Port 0 Expansion Hub 1
    public DcMotor left_drive; // Port 0 Expansion Hub 2
    public DcMotor collect; // Port 3 Expansion Hub 2
    public DcMotor right_extend; // Port 1 Expansion Hub 1
    public DcMotor left_extend; // Port 1 Expansion Hub 2
    public DcMotor right_lift; // Port 2 Expansion Hub 1
    public DcMotor left_lift; // Port 2 Expansion Hub 2

    // Local OpMode Members
    HardwareMap hwMap = null;

    // Method Called When Referencing Robot Hardware in Subsystems
    public void init (HardwareMap ahwMap) {
        // Reference to Hardware Map
        hwMap = ahwMap;

        // Motors
        right_drive = hwMap.get(DcMotor.class, "right_drive");
        left_drive = hwMap.get(DcMotor.class, "left_drive");
        collect = hwMap.get(DcMotor.class, "collect");
        right_extend = hwMap.get(DcMotor.class, "right_extend");
        left_extend = hwMap.get(DcMotor.class, "left_extend");
        right_lift = hwMap.get(DcMotor.class, "right_lift");
        left_lift =  hwMap.get(DcMotor.class, "left_lift");

        // Motor Configuration
        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        collect.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        right_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collect.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // TODO: Confirm Motor Direction is Correct Before Running
        right_drive.setDirection(DcMotor.Direction.FORWARD);
        left_drive.setDirection(DcMotor.Direction.REVERSE);
        collect.setDirection(DcMotor.Direction.REVERSE);
        right_extend.setDirection(DcMotor.Direction.FORWARD);
        left_extend.setDirection(DcMotor.Direction.REVERSE);
        right_lift.setDirection(DcMotor.Direction.FORWARD);
        left_lift.setDirection(DcMotor.Direction.REVERSE);

        right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collect.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        right_drive.setPower(0);
        left_drive.setPower(0);
        collect.setPower(0);
        right_extend.setPower(0);
        left_extend.setPower(0);
        right_lift.setPower(0);
        left_lift.setPower(0);
    }
}