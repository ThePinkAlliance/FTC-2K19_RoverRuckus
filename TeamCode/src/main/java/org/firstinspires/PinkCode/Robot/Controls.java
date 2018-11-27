package org.firstinspires.PinkCode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.PinkCode.Calculations.Range;

// Abstract Class to Define the Controls of the Gamepads
public abstract class Controls extends OpMode{
    // Base Gamepad
    public double base_right_joystick_command;
    public double base_left_joystick_command;
    public double base_right_trigger_command;
    public double base_left_trigger_command;
    public boolean base_right_joystick_button_pressed = false;
    public boolean base_left_joystick_button_pressed = false;
    public boolean base_right_bumper_pressed = false;
    public boolean base_left_bumper_pressed = false;
    public boolean base_dpad_right_pressed = false;
    public boolean base_dpad_left_pressed = false;
    public boolean base_dpad_down_pressed = false;
    public boolean base_dpad_up_pressed = false;
    public boolean base_start_pressed = false;
    public boolean base_back_pressed = false;
    public boolean base_a_pressed = false;
    public boolean base_b_pressed = false;
    public boolean base_x_pressed = false;
    public boolean base_y_pressed = false;

    // Tower Gamepad
    public double tower_right_joystick_command;
    public double tower_left_joystick_command;
    public double tower_right_trigger_command;
    public double tower_left_trigger_command;
    public boolean tower_right_joystick_button_pressed = false;
    public boolean tower_left_joystick_button_pressed = false;
    public boolean tower_right_bumper_pressed = false;
    public boolean tower_left_bumper_pressed = false;
    public boolean tower_dpad_right_pressed = false;
    public boolean tower_dpad_left_pressed = false;
    public boolean tower_dpad_down_pressed = false;
    public boolean tower_dpad_up_pressed = false;
    public boolean tower_start_pressed = false;
    public boolean tower_back_pressed = false;
    public boolean tower_a_pressed = false;
    public boolean tower_b_pressed = false;
    public boolean tower_x_pressed = false;
    public boolean tower_y_pressed = false;

    // Method to Make the Base Right Joystick Read Out It's Value While Accounting for Mechanical Deflection Error
    public double base_right_joystick(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(-gamepad1.right_stick_y, minimum_deflection, maximum_deflection)) {
            base_right_joystick_command = -gamepad1.right_stick_y;
        } else {
            base_right_joystick_command = 0;
        }
        return base_right_joystick_command;
    }

    // Method to Make the Base Right Joystick Read Out It's Value While Accounting for Mechanical Deflection Error
    public double base_left_joystick(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(-gamepad1.left_stick_y, minimum_deflection, maximum_deflection)) {
            base_left_joystick_command = -gamepad1.left_stick_y;
        } else {
            base_left_joystick_command = 0;
        }
        return base_left_joystick_command;
    }

    // Method to Make the Base Right Trigger Read Out It's Value While Accounting for Mechanical Deflection Error
    public double base_right_trigger(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(gamepad1.right_trigger, minimum_deflection, maximum_deflection)) {
            base_right_trigger_command = gamepad1.right_trigger;
        } else {
            base_right_trigger_command = 0;
        }
        return base_right_trigger_command;
    }

    // Method to Make the Base Left Trigger Read Out It's Value While Accounting for Mechanical Deflection Error
    public double base_left_trigger(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(gamepad1.left_trigger, minimum_deflection, maximum_deflection)) {
            base_left_trigger_command = -gamepad1.left_trigger;
        } else {
            base_left_trigger_command = 0;
        }
        return base_left_trigger_command;
    }

    // Method to Make the Base Right Joystick Button Read Out With the Option for a Toggle
    public boolean base_right_joystick_button(boolean toggle) {
        if (toggle && gamepad1.right_stick_button) {
            if (gamepad1.right_stick_button && base_right_joystick_button_pressed) {
                base_right_joystick_button_pressed = false;
                return true;
            } else {
                base_right_joystick_button_pressed = true;
                return false;
            }
        } else {
            return gamepad1.right_stick_button;
        }
    }

    // Method to Make the Base Left Joystick Button Read Out With the Option for a Toggle
    public boolean base_left_joystick_button(boolean toggle) {
        if (toggle && gamepad1.left_stick_button) {
            if (gamepad1.left_stick_button && base_left_joystick_button_pressed) {
                base_left_joystick_button_pressed = false;
                return true;
            } else {
                base_left_joystick_button_pressed = true;
                return false;
            }
        } else {
            return gamepad1.left_stick_button;
        }
    }

    // Method to Make the Base Right Bumper Read Out With the Option for a Toggle
    public boolean base_right_bumper(boolean toggle) {
        if (toggle && gamepad1.right_bumper) {
            if (gamepad1.right_bumper && base_right_bumper_pressed) {
                base_right_bumper_pressed = false;
                return true;
            } else {
                base_right_bumper_pressed = true;
                return false;
            }
        } else {
            return gamepad1.right_bumper;
        }
    }

    // Method to Make the Base Left Bumper Read Out With the Option for a Toggle
    public boolean base_left_bumper(boolean toggle) {
        if (toggle && gamepad1.left_bumper) {
            if (gamepad1.left_bumper && base_left_bumper_pressed) {
                base_left_bumper_pressed = false;
                return true;
            } else {
                base_left_bumper_pressed = true;
                return false;
            }
        } else {
            return gamepad1.left_bumper;
        }
    }

    // Method to Make the Base D-Pad Right Read Out With the Option for a Toggle
    public boolean base_dpad_right(boolean toggle) {
        if (toggle && gamepad1.dpad_right) {
            if (gamepad1.dpad_right && base_dpad_right_pressed) {
                base_dpad_right_pressed = false;
                return true;
            } else {
                base_dpad_right_pressed = true;
                return false;
            }
        } else {
            return gamepad1.dpad_right;
        }
    }

    // Method to Make the Base D-Pad Left Read Out With the Option for a Toggle
    public boolean base_dpad_left(boolean toggle) {
        if (toggle && gamepad1.dpad_left) {
            if (gamepad1.dpad_left && base_dpad_left_pressed) {
                base_dpad_left_pressed = false;
                return true;
            } else {
                base_dpad_left_pressed = true;
                return false;
            }
        } else {
            return gamepad1.dpad_left;
        }
    }

    // Method to Make the Base D-Pad Down Read Out With the Option for a Toggle
    public boolean base_dpad_down(boolean toggle) {
        if (toggle && gamepad1.dpad_down) {
            if (gamepad1.dpad_down && base_dpad_down_pressed) {
                base_dpad_down_pressed = false;
                return true;
            } else {
                base_dpad_down_pressed = true;
                return false;
            }
        } else {
            return gamepad1.dpad_down;
        }
    }

    // Method to Make the Base D-Pad Up Read Out With the Option for a Toggle
    public boolean base_dpad_up(boolean toggle) {
        if (toggle && gamepad1.dpad_up) {
            if (gamepad1.dpad_up && base_dpad_up_pressed) {
                base_dpad_up_pressed = false;
                return true;
            } else {
                base_dpad_up_pressed = true;
                return false;
            }
        } else {
            return gamepad1.dpad_up;
        }
    }

    // Method to Make the Base Back Read Out With the Option for a Toggle
    public boolean base_start(boolean toggle) {
        if (toggle && gamepad1.start) {
            if (gamepad1.start && base_start_pressed) {
                base_start_pressed = false;
                return true;
            } else {
                base_start_pressed = true;
                return false;
            }
        } else {
            return gamepad1.start;
        }
    }

    // Method to Make the Base Back Read Out With the Option for a Toggle
    public boolean base_back(boolean toggle) {
        if (toggle && gamepad1.back) {
            if (gamepad1.back && base_back_pressed) {
                base_back_pressed = false;
                return true;
            } else {
                base_back_pressed = true;
                return false;
            }
        } else {
            return gamepad1.back;
        }
    }

    // Method to Make the Base A Read Out With the Option for a Toggle
    public boolean base_a(boolean toggle) {
        if (toggle && gamepad1.a) {
            if (gamepad1.a && base_a_pressed) {
                base_a_pressed = false;
                return true;
            } else {
                base_a_pressed = true;
                return false;
            }
        } else {
            return gamepad1.a;
        }
    }

    // Method to Make the Base B Read Out With the Option for a Toggle
    public boolean base_b(boolean toggle) {
        if (toggle && gamepad1.b) {
            if (gamepad1.b && base_b_pressed) {
                base_b_pressed = false;
                return true;
            } else {
                base_b_pressed = true;
                return false;
            }
        } else {
            return gamepad1.b;
        }
    }

    // Method to Make the Base X Read Out With the Option for a Toggle
    public boolean base_x(boolean toggle) {
        if (toggle && gamepad1.x) {
            if (gamepad1.x && base_x_pressed) {
                base_x_pressed = false;
                return true;
            } else {
                base_x_pressed = true;
                return false;
            }
        } else {
            return gamepad1.x;
        }
    }

    // Method to Make the Base Y Read Out With the Option for a Toggle
    public boolean base_y(boolean toggle) {
        if (toggle && gamepad1.y) {
            if (gamepad1.y && base_y_pressed) {
                base_y_pressed = false;
                return true;
            } else {
                base_y_pressed = true;
                return false;
            }
        } else {
            return gamepad1.y;
        }
    }

    // Method to Make the Tower Right Joystick Read Out It's Value While Accounting for Mechanical Deflection Error
    public double tower_right_joystick(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(-gamepad2.right_stick_y, minimum_deflection, maximum_deflection)) {
            tower_right_joystick_command = -gamepad2.right_stick_y;
        } else {
            tower_right_joystick_command = 0;
        }
        return tower_right_joystick_command;
    }

    // Method to Make the Tower Right Joystick Read Out It's Value While Accounting for Mechanical Deflection Error
    public double tower_left_joystick(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(-gamepad2.left_stick_y, minimum_deflection, maximum_deflection)) {
            tower_left_joystick_command = -gamepad2.left_stick_y;
        } else {
            tower_left_joystick_command = 0;
        }
        return tower_left_joystick_command;
    }

    // Method to Make the Tower Right Trigger Read Out It's Value While Accounting for Mechanical Deflection Error
    public double tower_right_trigger(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(gamepad2.right_trigger, minimum_deflection, maximum_deflection)) {
            tower_right_trigger_command = gamepad2.right_trigger;
        } else {
            tower_right_trigger_command = 0;
        }
        return tower_right_trigger_command;
    }

    // Method to Make the Tower Left Trigger Read Out It's Value While Accounting for Mechanical Deflection Error
    public double tower_left_trigger(double minimum_deflection, double maximum_deflection) {
        if (Range.out_range(gamepad2.left_trigger, minimum_deflection, maximum_deflection)) {
            tower_left_trigger_command = -gamepad2.left_trigger;
        } else {
            tower_left_trigger_command = 0;
        }
        return tower_left_trigger_command;
    }

    // Method to Make the Tower Right Joystick Button Read Out With the Option for a Toggle
    public boolean tower_right_joystick_button(boolean toggle) {
        if (toggle && gamepad2.right_stick_button) {
            if (gamepad2.right_stick_button && tower_right_joystick_button_pressed) {
                tower_right_joystick_button_pressed = false;
                return true;
            } else {
                tower_right_joystick_button_pressed = true;
                return false;
            }
        } else {
            return gamepad2.right_stick_button;
        }
    }

    // Method to Make the Tower Left Joystick Button Read Out With the Option for a Toggle
    public boolean tower_left_joystick_button(boolean toggle) {
        if (toggle && gamepad2.left_stick_button) {
            if (gamepad2.left_stick_button && tower_left_joystick_button_pressed) {
                tower_left_joystick_button_pressed = false;
                return true;
            } else {
                tower_left_joystick_button_pressed = true;
                return false;
            }
        } else {
            return gamepad2.left_stick_button;
        }
    }

    // Method to Make the Tower Right Bumper Read Out With the Option for a Toggle
    public boolean tower_right_bumper(boolean toggle) {
        if (toggle && gamepad2.right_bumper) {
            if (gamepad2.right_bumper && tower_right_bumper_pressed) {
                tower_right_bumper_pressed = false;
                return true;
            } else {
                tower_right_bumper_pressed = true;
                return false;
            }
        } else {
            return gamepad2.right_bumper;
        }
    }

    // Method to Make the Tower Left Bumper Read Out With the Option for a Toggle
    public boolean tower_left_bumper(boolean toggle) {
        if (toggle && gamepad2.left_bumper) {
            if (gamepad2.left_bumper && tower_left_bumper_pressed) {
                tower_left_bumper_pressed = false;
                return true;
            } else {
                tower_left_bumper_pressed = true;
                return false;
            }
        } else {
            return gamepad2.left_bumper;
        }
    }

    // Method to Make the Tower D-Pad Right Read Out With the Option for a Toggle
    public boolean tower_dpad_right(boolean toggle) {
        if (toggle && gamepad2.dpad_right) {
            if (gamepad2.dpad_right && tower_dpad_right_pressed) {
                tower_dpad_right_pressed = false;
                return true;
            } else {
                tower_dpad_right_pressed = true;
                return false;
            }
        } else {
            return gamepad2.dpad_right;
        }
    }

    // Method to Make the Tower D-Pad Left Read Out With the Option for a Toggle
    public boolean tower_dpad_left(boolean toggle) {
        if (toggle && gamepad2.dpad_left) {
            if (gamepad2.dpad_left && tower_dpad_left_pressed) {
                tower_dpad_left_pressed = false;
                return true;
            } else {
                tower_dpad_left_pressed = true;
                return false;
            }
        } else {
            return gamepad2.dpad_left;
        }
    }

    // Method to Make the Tower D-Pad Down Read Out With the Option for a Toggle
    public boolean tower_dpad_down(boolean toggle) {
        if (toggle && gamepad2.dpad_down) {
            if (gamepad2.dpad_down && tower_dpad_down_pressed) {
                tower_dpad_down_pressed = false;
                return true;
            } else {
                tower_dpad_down_pressed = true;
                return false;
            }
        } else {
            return gamepad2.dpad_down;
        }
    }

    // Method to Make the Tower D-Pad Up Read Out With the Option for a Toggle
    public boolean tower_dpad_up(boolean toggle) {
        if (toggle && gamepad2.dpad_up) {
            if (gamepad2.dpad_up && tower_dpad_up_pressed) {
                tower_dpad_up_pressed = false;
                return true;
            } else {
                tower_dpad_up_pressed = true;
                return false;
            }
        } else {
            return gamepad2.dpad_up;
        }
    }

    // Method to Make the Tower Back Read Out With the Option for a Toggle
    public boolean tower_start(boolean toggle) {
        if (toggle && gamepad2.start) {
            if (gamepad2.start && tower_start_pressed) {
                tower_start_pressed = false;
                return true;
            } else {
                tower_start_pressed = true;
                return false;
            }
        } else {
            return gamepad2.start;
        }
    }

    // Method to Make the Tower Back Read Out With the Option for a Toggle
    public boolean tower_back(boolean toggle) {
        if (toggle && gamepad2.back) {
            if (gamepad2.back && tower_back_pressed) {
                tower_back_pressed = false;
                return true;
            } else {
                tower_back_pressed = true;
                return false;
            }
        } else {
            return gamepad2.back;
        }
    }

    // Method to Make the Tower A Read Out With the Option for a Toggle
    public boolean tower_a(boolean toggle) {
        if (toggle && gamepad2.a) {
            if (gamepad2.a && tower_a_pressed) {
                tower_a_pressed = false;
                return true;
            } else {
                tower_a_pressed = true;
                return false;
            }
        } else {
            return gamepad2.a;
        }
    }

    // Method to Make the Tower B Read Out With the Option for a Toggle
    public boolean tower_b(boolean toggle) {
        if (toggle && gamepad2.b) {
            if (gamepad2.b && tower_b_pressed) {
                tower_b_pressed = false;
                return true;
            } else {
                tower_b_pressed = true;
                return false;
            }
        } else {
            return gamepad2.b;
        }
    }

    // Method to Make the Tower X Read Out With the Option for a Toggle
    public boolean tower_x(boolean toggle) {
        if (toggle && gamepad2.x) {
            if (gamepad2.x && tower_x_pressed) {
                tower_x_pressed = false;
                return true;
            } else {
                tower_x_pressed = true;
                return false;
            }
        } else {
            return gamepad2.x;
        }
    }

    // Method to Make the Tower Y Read Out With the Option for a Toggle
    public boolean tower_y(boolean toggle) {
        if (toggle && gamepad2.y) {
            if (gamepad2.y && tower_y_pressed) {
                tower_y_pressed = false;
                return true;
            } else {
                tower_y_pressed = true;
                return false;
            }
        } else {
            return gamepad2.y;
        }
    }
}