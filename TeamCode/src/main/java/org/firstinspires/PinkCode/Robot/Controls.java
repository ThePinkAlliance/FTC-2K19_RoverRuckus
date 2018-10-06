package org.firstinspires.PinkCode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public abstract class Controls extends OpMode{
    // Base Gamepad
    public static boolean base_right_joystick_button;
    public static boolean base_left_joystick_button;
    public static float   base_right_joystick;
    public static float   base_left_joystick;
    public static float   base_right_trigger;
    public static float   base_left_trigger;
    public static boolean base_right_bumper;
    public static boolean base_left_bumper;
    public static boolean base_dpad_right;
    public static boolean base_dpad_left;
    public static boolean base_dpad_down;
    public static boolean base_dpad_up;
    public static boolean base_start;
    public static boolean base_back;
    public static boolean base_a;
    public static boolean base_b;
    public static boolean base_x;
    public static boolean base_y;

    // Tower Gamepad
    public static boolean tower_right_joystick_button;
    public static boolean tower_left_joystick_button;
    public static float   tower_right_joystick;
    public static float   tower_left_joystick;
    public static float   tower_right_trigger;
    public static float   tower_left_trigger;
    public static boolean tower_right_bumper;
    public static boolean tower_left_bumper;
    public static boolean tower_dpad_right;
    public static boolean tower_dpad_left;
    public static boolean tower_dpad_down;
    public static boolean tower_dpad_up;
    public static boolean tower_start;
    public static boolean tower_back;
    public static boolean tower_a;
    public static boolean tower_b;
    public static boolean tower_x;
    public static boolean tower_y;

    @Override
    public void loop() {
        // Joystick Definitions
        tower_right_joystick = -gamepad2.right_stick_y;
        base_right_joystick  = -gamepad1.right_stick_y;
        tower_left_joystick  = -gamepad2.left_stick_y;
        base_left_joystick   = -gamepad1.left_stick_y;

        // Button Definitions
        tower_right_joystick_button = gamepad2.right_stick_button;
        base_right_joystick_button  = gamepad1.right_stick_button;
        tower_left_joystick_button  = gamepad2.left_stick_button;
        base_left_joystick_button   = gamepad1.left_stick_button;
        tower_right_trigger         = gamepad2.right_trigger;
        base_right_trigger          = gamepad1.right_trigger;
        tower_left_trigger          = gamepad2.left_trigger;
        base_left_trigger           = gamepad1.left_trigger;
        tower_right_bumper          = gamepad2.right_bumper;
        base_right_bumper           = gamepad1.right_bumper;
        tower_left_bumper           = gamepad2.left_bumper;
        base_left_bumper            = gamepad1.left_bumper;
        tower_dpad_right            = gamepad2.dpad_right;
        base_dpad_right             = gamepad1.dpad_right;
        tower_dpad_left             = gamepad2.dpad_left;
        base_dpad_left              = gamepad1.dpad_left;
        tower_dpad_down             = gamepad2.dpad_down;
        base_dpad_down              = gamepad1.dpad_down;
        tower_dpad_up               = gamepad2.dpad_up;
        base_dpad_up                = gamepad1.dpad_up;
        tower_start                 = gamepad2.start;
        base_start                  = gamepad1.start;
        tower_back                  = gamepad2.back;
        base_back                   = gamepad1.back;
        tower_a                     = gamepad2.a;
        tower_b                     = gamepad2.b;
        tower_x                     = gamepad2.x;
        tower_y                     = gamepad2.y;
        base_a                      = gamepad1.a;
        base_b                      = gamepad1.b;
        base_x                      = gamepad1.x;
        base_y                      = gamepad1.y;
    }
}