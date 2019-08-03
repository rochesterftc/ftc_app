package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Rochesterftc10303 on 10/4/2018.
 */
@Disabled
@Autonomous(name="Libetarian Lehney",group="Master")


public class naziResetButton extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor RightRL;
    DcMotor LeftRL;
    CRServo sweeper;
    int deltaARL = 10;
    int ticksPerRev = 1120;
    boolean opmodeIsFinished = false;

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        fl = hardwareMap.dcMotor.get("front left");
        fr = hardwareMap.dcMotor.get("front right");
        bl = hardwareMap.dcMotor.get("back left");
        br = hardwareMap.dcMotor.get("back right");
        RightRL = hardwareMap.dcMotor.get("right rl");
        LeftRL = hardwareMap.dcMotor.get("left rl");
        sweeper = hardwareMap.crservo.get("sweeper");

    waitForStart();

    ElapsedTime runtime = new ElapsedTime();

        while (opModeIsActive() & (opmodeIsFinished = false)) {

            telemetry.addData("Status", "Resetting...");
            telemetry.update();

            fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftRL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightRL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            opmodeIsFinished = true;
        }

        telemetry.addData("Status", "Done!");
        telemetry.addData("Completed in", runtime);
        telemetry.update();
        sleep(2000);

    }
}