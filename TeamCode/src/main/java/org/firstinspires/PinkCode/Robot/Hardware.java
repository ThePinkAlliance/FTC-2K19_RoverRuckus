package org.firstinspires.PinkCode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

// Class to Define the Hardware of the Robot
public class Hardware {
    // Motors
    public DcMotor right_drive; // Port 3 Expansion Hub 1
    public DcMotor left_drive; // Port 3 Expansion Hub 2
    public DcMotor collect; // Port 1 Expansion Hub 1
    public DcMotor collector_rotate; // Port 1 Expansion Hub 2
    public DcMotor right_extend; // Port 2 Expansion Hub 1
    public DcMotor left_extend; // Port 2 Expansion Hub 2
    public DcMotor right_lift; // Port 0 Expansion Hub 1
    public DcMotor left_lift; // Port 0 Expansion Hub 2

    // Servos
    public Servo score_left_rotate;
    public Servo score_right_rotate;
    public Servo score_flap;
    public Servo score_kicker;
    public Servo hook;

    // Local OpMode Members
    private HardwareMap hwMap = null;

    // Method Called When Referencing Robot Hardware in Subsystems
    public void init (HardwareMap ahwMap) {
        // Reference to Hardware Map
        hwMap = ahwMap;

        // Motors
        right_drive = hwMap.get(DcMotor.class, "right_drive");
        left_drive = hwMap.get(DcMotor.class, "left_drive");
        collect = hwMap.get(DcMotor.class, "collect");
        collector_rotate = hwMap.get(DcMotor.class, "collector_rotate");
        right_extend = hwMap.get(DcMotor.class, "right_extend");
        left_extend = hwMap.get(DcMotor.class, "left_extend");
        right_lift = hwMap.get(DcMotor.class, "right_lift");
        left_lift =  hwMap.get(DcMotor.class, "left_lift");

        // Motor Configuration
        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        collect.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        collector_rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        right_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collect.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collector_rotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        right_drive.setDirection(DcMotor.Direction.REVERSE);
        left_drive.setDirection(DcMotor.Direction.REVERSE);
        collect.setDirection(DcMotor.Direction.FORWARD);
        collector_rotate.setDirection(DcMotor.Direction.REVERSE);
        right_extend.setDirection(DcMotor.Direction.REVERSE);
        left_extend.setDirection(DcMotor.Direction.FORWARD);
        right_lift.setDirection(DcMotor.Direction.FORWARD);
        left_lift.setDirection(DcMotor.Direction.FORWARD);

        right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collect.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collector_rotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        right_drive.setPower(0);
        left_drive.setPower(0);
        collect.setPower(0);
        collector_rotate.setPower(0);
        right_extend.setPower(0);
        left_extend.setPower(0);
        right_lift.setPower(0);
        left_lift.setPower(0);

        // Servos
        score_left_rotate = hwMap.get(Servo.class, "score_left_rotate");
        score_right_rotate = hwMap.get(Servo.class, "score_right_rotate");
        score_flap = hwMap.get(Servo.class, "score_flap");
        hook = hwMap.get(Servo.class, "hook");
        score_kicker = hwMap.get(Servo.class, "score_kicker");
    }
}