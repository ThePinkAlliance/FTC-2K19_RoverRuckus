package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Hardware {

    //-----------------------------------------Motors-----------------------------------------//
    public DcMotor leftDrive1   = null;
    public DcMotor leftDrive2   = null;
    public DcMotor rightDrive1  = null;
    public DcMotor rightDrive2  = null;
    public DcMotor towerExtend1 = null;
    public DcMotor towerExtend2 = null;
    public DcMotor towerRotate  = null;
    public DcMotor collectorIntake = null;

    //----------------------------------Local OpMode Members----------------------------------//
    HardwareMap hwMap = null;

    //---------------------Initialization of Standard Hardware Interfaces---------------------//
    public void init (HardwareMap ahwMap) {

        //--------------------Saving the Reference to the Hardware Map--------------------//
        hwMap = ahwMap;

        //-------------------------------------Motors-------------------------------------//
        leftDrive1      = hwMap.get(DcMotor.class, "leftDrive1");
        leftDrive2      = hwMap.get(DcMotor.class, "leftDrive2");
        rightDrive1     = hwMap.get(DcMotor.class, "rightDrive1");
        rightDrive2     = hwMap.get(DcMotor.class, "rightDrive2");
        towerExtend1    = hwMap.get(DcMotor.class, "towerExtend1");
        towerExtend2    = hwMap.get(DcMotor.class, "towerExtend2");
        towerRotate     = hwMap.get(DcMotor.class, "towerRotate");
        collectorIntake = hwMap.get(DcMotor.class, "towerCollect");

        //-------------------------------Motor Configuration------------------------------//
        leftDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        towerExtend1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        towerExtend2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        towerRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        collectorIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        towerExtend1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        towerExtend2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        towerRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectorIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // TODO: Confirm Motor Direction is Correct Before Running
        leftDrive1.setDirection(DcMotor.Direction.REVERSE);
        leftDrive2.setDirection(DcMotor.Direction.REVERSE);
        rightDrive1.setDirection(DcMotor.Direction.REVERSE);
        rightDrive2.setDirection(DcMotor.Direction.REVERSE);
        towerExtend1.setDirection(DcMotor.Direction.REVERSE);
        towerExtend2.setDirection(DcMotor.Direction.REVERSE);
        towerRotate.setDirection(DcMotor.Direction.REVERSE);
        collectorIntake.setDirection(DcMotor.Direction.REVERSE);

        leftDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        towerExtend1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        towerExtend2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        towerRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collectorIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive1.setPower(0);
        leftDrive2.setPower(0);
        rightDrive1.setPower(0);
        rightDrive2.setPower(0);
        towerExtend1.setPower(0);
        towerExtend2.setPower(0);
        towerRotate.setPower(0);
        collectorIntake.setPower(0);
    }
}