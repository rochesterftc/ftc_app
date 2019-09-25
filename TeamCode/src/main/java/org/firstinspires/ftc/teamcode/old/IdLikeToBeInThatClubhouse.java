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
@Disabled
@TeleOp(name="TheClubhouse",group="Master")


public class IdLikeToBeInThatClubhouse extends OpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor ARL;
    DcMotor ATele;
    CRServo sweeper;

    public void init() {

        fl = hardwareMap.dcMotor.get("front left");
        fr = hardwareMap.dcMotor.get("front right");
        bl = hardwareMap.dcMotor.get("back left");
        br = hardwareMap.dcMotor.get("back right");
        ARL = hardwareMap.dcMotor.get("arm RnL");
        ATele = hardwareMap.dcMotor.get("arm tele");
        sweeper = hardwareMap.crservo.get ("sweeper");
    }

    public void loop() {

        //floats to streamline drive code
        float x = gamepad1.left_stick_x;
        float z = gamepad1.left_stick_y;
        float y = gamepad1.right_stick_x;

        /*
        Holonomic Drive:
        Gamepad 1 left and right sticks control the robot main movement, holding down a moves 5x slower
         */
        if (gamepad1.a) {
            fl.setPower((-y - x + z) / 5);
            bl.setPower((-y + x + z) / 5);
            fr.setPower((-y - x - z) / 5);
            br.setPower((-y + x - z) / 5);
        } else {
            fl.setPower(-y - x + z);
            bl.setPower(-y + x + z);
            fr.setPower(-y - x - z);
            br.setPower(-y + x - z);
        }

        /*
        ARM Raise and Lower
        Gamepad 2 left-stick
         */
        float arl = gamepad2.left_stick_y;
        ARL.setPower(arl);

        // sweeper on off gamepad2 a
        if (gamepad2.a) {
            sweeper.setPower(1);
        } else if (gamepad2.b){
            sweeper.setPower(-1);
        } else{
            sweeper.setPower(0);
        }

        /*
        arm telescope
         */
        if(gamepad2.dpad_up) {
            ATele.setPower(1);
        } else if(gamepad2.dpad_down) {
            ATele.setPower(-1);
        } else {
            ATele.setPower(0);
        }
    }
}