package org.firstinspires.ftc.teamcode.old;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Rochesterftc10303 on 10/4/2018.
 */

@TeleOp(name="Screw You!",group="Master")


public class ScrewYou extends OpMode {

    DcMotor test1;
    DcMotor test2;

    public void init() {

        test1 = hardwareMap.dcMotor.get("test1");
        test2 = hardwareMap.dcMotor.get("test2");

    }

    public void loop() {

        float power = gamepad1.left_stick_y;
        test1.setPower(power);
        test2.setPower(power);
        telemetry.addData("Power", power);
        telemetry.update();
    }
}