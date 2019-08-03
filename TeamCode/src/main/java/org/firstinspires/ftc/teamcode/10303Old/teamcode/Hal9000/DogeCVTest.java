package org.firstinspires.ftc.teamcode.Hal9000;

import android.graphics.Color;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Rochesterftc10303 on 11/3/2017.  *****REMOVE BEFORE FLIGHT*****
 */
//@Disabled  //REMOVE BEFORE FLIGHT
@Autonomous (name="Computer Vision Test",group="REMOVE BEFORE FLIGHT")
public class DogeCVTest extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor RightRL;
    DcMotor LeftRL;
    DcMotor arm;
    CRServo sweeper;
    ColorSensor sensorColor;

    // Detector object
    private GoldAlignDetector detector;


    // Declare OpMode members.
    private boolean goldFound;      // Sound file present flags
    private boolean silverFound;
    private boolean hellothereFound;

    public void move(double x, double y, double z, int time) {

        telemetry.addData("Status", "Moving" + time + "milliseconds for x:" + x + "y:" + y + "z:" + z);
        telemetry.update();

        fl.setPower(-y - x + z);
        bl.setPower(-y + x + z);
        fr.setPower(y - x + z);
        br.setPower(y + x + z);

        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);

        sleep(100);

        telemetry.addData("Status", "Moving for"+time/1000+"seconds: x:"+x+", y:"+y+", z:"+z);
        telemetry.update();

    }

    boolean opModeTime = true;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!

        // Determine Resource IDs for sounds built into the RC application.
        int silverSoundID = hardwareMap.appContext.getResources().getIdentifier("silver", "raw", hardwareMap.appContext.getPackageName());
        int goldSoundID   = hardwareMap.appContext.getResources().getIdentifier("gold",   "raw", hardwareMap.appContext.getPackageName());
        int hellothereID = hardwareMap.appContext.getResources().getIdentifier("hellothere", "raw", hardwareMap.appContext.getPackageName());

        // Determine if sound resources are found.
        // Note: Preloading is NOT required, but it's a good way to verify all your sounds are available before you run.
        if (goldSoundID != 0)
            goldFound   = SoundPlayer.getInstance().preload(hardwareMap.appContext, goldSoundID);

        if (silverSoundID != 0)
            silverFound = SoundPlayer.getInstance().preload(hardwareMap.appContext, silverSoundID);

        if (hellothereID != 0)
            hellothereFound = SoundPlayer.getInstance().preload(hardwareMap.appContext, hellothereID);

        fl = hardwareMap.dcMotor.get("front left");
        fr = hardwareMap.dcMotor.get("front right");
        bl = hardwareMap.dcMotor.get("back left");
        br = hardwareMap.dcMotor.get("back right");
        RightRL = hardwareMap.dcMotor.get("right rl");
        LeftRL = hardwareMap.dcMotor.get("left rl");
        arm = hardwareMap.dcMotor.get("arm");
        sweeper = hardwareMap.crservo.get("sweeper");


        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!

        float hsvValues[] = {0F, 0F, 0F};   //values for the color sensor
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");

        // Display sound status
        telemetry.addData("gold resource",   goldFound ?   "Found" : "NOT FOUND!\n Add gold.wav to /src/main/res/raw" );
        telemetry.addData("silver resource", silverFound ? "Found" : "NOT FOUND!\n Add silver.wav to /src/main/res/raw" );
        telemetry.addData("hellothere", hellothereFound ? "Found" : "NOT FOUND!\n Add hellothere.wav to /src/main/res/raw");

        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, hellothereID);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "initialized!");
        telemetry.addData(">", "Press Start to continue");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {

            // convert the RGB values to HSV values.
            // multiply by the SCALE_FACTOR.
            // then cast it back to int (SCALE_FACTOR is a double)
            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            if(opModeTime) {
                telemetry.addData("Status", "Completing Sampling");
                telemetry.addData("Targeting","Standby");
                telemetry.update();

                /*
                Moving Robot to scan first set of minerals
                 */
                move(0, 0.25, 0, 700);
                move(-0.25, 0, 0, 4000);
                move(0,0,-0.1,2000);

                /*
                Set the robot to run motors until "detector.getAlligned()" equals false
                 */
                fl.setPower(-0.1);
                fr.setPower(0.1);
                bl.setPower(-0.1);
                br.setPower(0.1);
                telemetry.addData("Targeting","Scanning...");
                telemetry.update();
                while (detector.getXPosition()!=0){
                    sleep(33);
                }
                telemetry.addData("Targeting","Mineral found! Aligning...");
                telemetry.update();
                while (!detector.getAligned()){
                    sleep(33);
                }
                move(0,0,0,0);
                telemetry.addData("Targeting","Aligned! Executing Sampling Maneuver...");
                telemetry.update();

                opModeTime = false;
            }else {
                telemetry.addData("Status", "Robot is stopped");
                telemetry.update();
            }
        }

    }

}