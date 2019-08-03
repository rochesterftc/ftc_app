package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Rochesterftc10303 on 10/4/2018.
 */
@Disabled
@TeleOp(name="Screw You!",group="Master")


public class ScrewYou extends OpMode {

    DcMotor arm;

    public void init() {

        arm = hardwareMap.dcMotor.get("arm");

    }

    public void loop() {

        float ARM = gamepad1.left_stick_y;
        arm.setPower(ARM);
        telemetry.addData("Arm Power", ARM);
        telemetry.update();
    }
}