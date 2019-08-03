package org.firstinspires.ftc.teamcode.Hal9000;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Autonomous(name="Right Auto",group="Hal Did Nothing Wrong")

    public class Dave extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    CRServo sweeper;
    ColorSensor sensorColor;


    // Declare OpMode members.
    private boolean helloThereFound;      // Sound file present flag

    public String move(double x, double y, double z, int time) {

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

        return "Moving for " + time + " second";
    }

    boolean opModeTime = true;

    @Override
    public void runOpMode() throws InterruptedException {

        // Determine Resource IDs for sounds built into the RC application.
        int helloThereID = hardwareMap.appContext.getResources().getIdentifier("hellothere", "raw", hardwareMap.appContext.getPackageName());

        // Determine if sound resources are found.
        // Note: Preloading is NOT required, but it's a good way to verify all your sounds are available before you run.
        if (helloThereID != 0) {
            helloThereFound = SoundPlayer.getInstance().preload(hardwareMap.appContext, helloThereID);
        }
        fl = hardwareMap.dcMotor.get("front left");     //Initialize Motors
        fr = hardwareMap.dcMotor.get("front right");
        bl = hardwareMap.dcMotor.get("back left");
        br = hardwareMap.dcMotor.get("back right");
        sweeper = hardwareMap.crservo.get("sweeper");

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //Change motor mode to "RUN_USING_ENCODER"
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        float hsvValues[] = {0F, 0F, 0F};   //values for the color sensor
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");


        // Display sound status
        telemetry.addData("hellothere resource",   helloThereFound ?   "Found" : "NOT found\n Add hellothere.wav to /src/main/res/raw" );
        telemetry.addData("Status", "Init complete! Press Start to Continue");
        telemetry.update();

        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, helloThereID);

        waitForStart();

        while (opModeIsActive()) {
            if (opModeTime) {

                move(0, 0.5, 0, 350);
                move(-0.5, 0, 0, 2000);
                move(0, 0, -0.5, 260);
                move(0, 0.5, 0, 1600);
                sleep(500);
                sweeper.setPower(1);
                sleep(3000);
                sweeper.setPower(0);
                sleep(500);
                move( 0, -0.5, 0, 200);
                move(0,0,-0.5,300);
                move(0,0.25,0,700);
                move(0,0,-0.5,250);
                move(0,0.5,0,2250);


                opModeTime = false;
            } else {
                telemetry.addData("Status", "Robot is stopped");
                telemetry.update();

            }
        }
    }
}