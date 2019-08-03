package org.firstinspires.ftc.teamcode.Hal9000;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Rochesterftc10303 on 11/3/2017.
 */
@Disabled
@Autonomous (name="Red Right",group="Hal 9000")
public class redRight extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor RightRL;
    DcMotor LeftRL;
    DcMotor arm;
    CRServo sweeper;

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

        fl = hardwareMap.dcMotor.get("front left");
        fr = hardwareMap.dcMotor.get("front right");
        bl = hardwareMap.dcMotor.get("back left");
        br = hardwareMap.dcMotor.get("back right");
        RightRL = hardwareMap.dcMotor.get("right rl");
        LeftRL = hardwareMap.dcMotor.get("left rl");
        arm = hardwareMap.dcMotor.get("arm");
        sweeper = hardwareMap.crservo.get("sweeper");

        telemetry.addData("Status", "initialized!");

        waitForStart();

        while(opModeIsActive()) {
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