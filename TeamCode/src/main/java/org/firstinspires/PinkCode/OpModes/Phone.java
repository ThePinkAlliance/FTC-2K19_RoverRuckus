package org.firstinspires.PinkCode.OpModes;

import android.graphics.Bitmap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.PinkCode.robotcontroller.internal.CameraOp;

@TeleOp(name = "Phone", group = "Testing")
public class Phone extends CameraOp {

    int ds2 = 2;  // additional downsampling of the image
    private int looped = 0;
    private long lastLoopTime = 0;
    public String colorString = "";
    // set to 1 to disable further downsampling

    /*
     * Code to run when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {
        setCameraDownsampling(8);
        // parameter determines how downsampled you want your images
        // 8, 4, 2, or 1.
        // higher number is more downsampled, so less resolution but faster
        // 1 is original resolution, which is detailed but slow
        // must be called before super.init sets up the camera

        super.init(); // inits camera functions, starts preview callback
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        long startTime = System.currentTimeMillis();

        if (imageReady()) { // only do this if an image has been returned from the camera
            int whiteValue = 0;
            int goldValue = 0;

            // get image, rotated so (0,0) is in the bottom left of the preview window
            Bitmap rgbImage;
            rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);

            for (int x = 0; x < rgbImage.getWidth(); x++) {
                for (int y = 0; y < rgbImage.getHeight(); y++) {
                    int pixel = rgbImage.getPixel(x, y);
                    whiteValue += white(pixel);
                    goldValue += gold(pixel);
                }
            }
            int color = highestColor(whiteValue, goldValue);

            switch (color) {
                case 0:
                    colorString = "WHITE";
                    break;
                case 1:
                    colorString = "GOLD";
            }
            telemetry.addData("Color:", "Color detected is: " + colorString);

        }
    }
}
