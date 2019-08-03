package org.firstinspires.ftc.teamcode.Hal9000;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Rochesterftc10303 on 11/3/2017.
 */
@Disabled
@Autonomous (name="Master Blue Right(OLD)",group="Middle")
public class MasterBlueRight extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor slide;
    Servo leftClamp;
    Servo rightClamp;
    Servo arm;
    ColorSensor sensorColor;

    boolean opModeTime = true;

    /**DRIVE method that turns motors for double speed
     * for int time, and changes telemetry Status;
     * positive values are forwards
     */
    public void drive(double speed, int time) {

        telemetry.addData("Status", "Moving for " + time + " seconds at speed: " + speed);
        telemetry.update();

        fl.setPower(-speed);
        bl.setPower(-speed);
        fr.setPower(speed);
        br.setPower(speed);

        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);

    }

    /**STRAFE method that turns motors for double speed
     * for int time, and changes telemetry Status;
     * positive values are right
     */
    public void strafe(double speed, int time) {

        telemetry.addData("Status", "Strafing for" + time + "seconds at speed:" + speed);
        telemetry.update();

        fl.setPower(-speed);
        bl.setPower(speed);
        fr.setPower(-speed);
        br.setPower(speed);

        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);

    }

    /**TURN method that turns motors for double speed
     * for int time, and changes telemetry Status;
     * positive values are left
     */
    public void turn(double speed, int time) {

        telemetry.addData("Status", "Turning for" + time + "seconds at speed:" + speed);
        telemetry.update();

        fl.setPower(speed);
        bl.setPower(speed);
        fr.setPower(speed);
        br.setPower(speed);

        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;
        fl = hardwareMap.dcMotor.get("front left");
        fr = hardwareMap.dcMotor.get("front right");
        bl = hardwareMap.dcMotor.get("back left");
        br = hardwareMap.dcMotor.get("back right");
        slide = hardwareMap.dcMotor.get("slide");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        leftClamp = hardwareMap.servo.get("left clamp");
        rightClamp = hardwareMap.servo.get("right clamp");
        arm = hardwareMap.servo.get("arm");

        leftClamp.setPosition(0);
        rightClamp.setPosition(1);
        arm.setPosition(1);
        waitForStart();

        while(opModeIsActive()) {
            if(opModeTime) {
                leftClamp.setPosition(1);
                rightClamp.setPosition(0);
                sleep(500);
                slide.setPower(1);
                sleep(500);
                slide.setPower(0);
                arm.setPosition(0);
                sleep(2000);
                if (sensorColor.blue() > sensorColor.red()){
                    strafe(0.25, 250);
                    arm.setPosition(1);
                    drive(0.25, 250);
                    strafe(-0.25, 250);
                } else {
                    strafe(-0.25, 250);
                    arm.setPosition(1);
                    drive(0.25, 250);
                    strafe(0.25, 250);
                }
                sleep(2000);
                strafe(-0.5, 2000);
                drive(0.5, 500);
                turn(1, 1100);
                drive(0.25, 2000);
                slide.setPower(-1);
                sleep(500);
                slide.setPower(0);
                leftClamp.setPosition(0);
                rightClamp.setPosition(1);
                drive(-0.25, 250);
                drive(0.25, 500);
                drive(-0.25, 250);
                opModeTime = false;
            }else {
                telemetry.addData("Status", "Robot is stopped");
                telemetry.update();
            }
        }

    }

}

