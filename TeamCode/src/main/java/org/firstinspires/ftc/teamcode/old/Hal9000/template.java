package org.firstinspires.ftc.teamcode.old.Hal9000;

import android.graphics.Color;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Rochesterftc10303 on 11/3/2017.  *****REMOVE BEFORE FLIGHT*****
 */
@Disabled  //REMOVE BEFORE FLIGHT
@Autonomous (name="REMOVE BEFORE FLIGHT",group="REMOVE BEFORE FLIGHT")
public class template extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor RightRL;
    DcMotor LeftRL;
    DcMotor arm;
    CRServo sweeper;
    ColorSensor sensorColor;

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

        // Determine Resource IDs for sounds built into the RC application.
        int silverSoundID = hardwareMap.appContext.getResources().getIdentifier("silver", "raw", hardwareMap.appContext.getPackageName());
        int goldSoundID   = hardwareMap.appContext.getResources().getIdentifier("gold", "raw", hardwareMap.appContext.getPackageName());
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

                move(0, 1, 0, 250);

                opModeTime = false;
            }else {
                telemetry.addData("Status", "Robot is stopped");
                telemetry.update();
            }
        }

    }

}