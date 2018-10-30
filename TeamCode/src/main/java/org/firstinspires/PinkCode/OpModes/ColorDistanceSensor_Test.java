package org.firstinspires.PinkCode.OpModes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@TeleOp(name = "Sensor: 2 REVColorDistance", group = "Sensor")
public class ColorDistanceSensor_Test extends LinearOpMode {

    ColorSensor sensorColor1;
    ColorSensor sensorColor2;
    DistanceSensor sensorDistance1;
    DistanceSensor sensorDistance2;

    @Override
    public void runOpMode() {

        // get a reference to the color sensor.
        sensorColor1 = hardwareMap.get(ColorSensor.class, "sensor_color_distance1");
        sensorColor2 = hardwareMap.get(ColorSensor.class,"sensor_color_distance2");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance1 = hardwareMap.get(DistanceSensor.class, "sensor_color_distance1");
        sensorDistance2 = hardwareMap.get(DistanceSensor.class, "sensor_color_distance2");

        // Disable Lights
        sensorColor1.enableLed(false);
        sensorColor2.enableLed(false);

        // hsvValues1 is an array that will hold the hue, saturation, and value information.
        float hsvValues1[] = {0F, 0F, 0F};
        float hsvValues2[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues1 array.
        final float values1[] = hsvValues1;
        final float values2[] = hsvValues2;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // wait for the start button to be pressed.
        waitForStart();

        // loop and read the RGB and distance data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {
            // convert the RGB values to HSV values.
            // multiply by the SCALE_FACTOR.
            // then cast it back to int (SCALE_FACTOR is a double)
            Color.RGBToHSV(
                    (int) (sensorColor1.red() * SCALE_FACTOR),
                    (int) (sensorColor1.green() * SCALE_FACTOR),
                    (int) (sensorColor1.blue() * SCALE_FACTOR),
                    hsvValues1);

            Color.RGBToHSV(
                    (int) (sensorColor2.red() * SCALE_FACTOR),
                    (int) (sensorColor2.green() * SCALE_FACTOR),
                    (int) (sensorColor2.blue() * SCALE_FACTOR),
                    hsvValues2);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Sensor 1","");
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance1.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor1.alpha());
            telemetry.addData("Red  ", sensorColor1.red());
            telemetry.addData("Green", sensorColor1.green());
            telemetry.addData("Blue ", sensorColor1.blue());
            telemetry.addData("Hue", hsvValues1[0]);

            telemetry.addData("Sensor 2","");
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance2.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor2.alpha());
            telemetry.addData("Red  ", sensorColor2.red());
            telemetry.addData("Green", sensorColor2.green());
            telemetry.addData("Blue ", sensorColor2.blue());
            telemetry.addData("Hue", hsvValues2[0]);

            telemetry.update();
        }
    }
}
