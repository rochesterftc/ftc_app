package org.firstinspires.ftc.teamcode.old;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.ftccommon.SoundPlayer;

/**
 * Created by Rochesterftc10303 on 10/4/2018.
 */
@Disabled
@TeleOp(name="Nothing to See Here",group="Master")


public class
Gulag extends OpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor RightRL;
    DcMotor LeftRL;
    DcMotor arm;
    CRServo sweeper;
    int deltaARL = 2;
    int ticksPerRev = 1120;
    double sweeperPower;

    // Declare OpMode members.
    private boolean goldFound;      // Sound file present flags
    private boolean silverFound;

    public void init() {

        fl = hardwareMap.dcMotor.get("front left");
        fr = hardwareMap.dcMotor.get("front right");
        bl = hardwareMap.dcMotor.get("back left");
        br = hardwareMap.dcMotor.get("back right");
        RightRL = hardwareMap.dcMotor.get("right rl");
        LeftRL = hardwareMap.dcMotor.get("left rl");
        arm = hardwareMap.dcMotor.get("arm");
        sweeper = hardwareMap.crservo.get("sweeper");

        // Determine Resource IDs for sounds built into the RC application.
        //int hellothereID = hardwareMap.appContext.getResources().getIdentifier("hellothere", "raw", hardwareMap.appContext.getPackageName());

        // Determine if sound resources are found.
        // Note: Preloading is NOT required, but it's a good way to verify all your sounds are available before you run.
        //if (hellothereID != 0) {
        //    hellothere = SoundPlayer.getInstance().preload(hardwareMap.appContext, hellothereID);
        //}

        //SoundPlayer.getInstance().startPlaying(hellothere);

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
        float arl = -gamepad2.left_stick_y/deltaARL;
        LeftRL.setPower(arl);
        RightRL.setPower(arl);
        //double power;
        //if(LeftRL.getCurrentPosition()> 0) {
           // power = (LeftRL.getCurrentPosition()/12);
        //} else if (LeftRL.getCurrentPosition()< 0) {
            //power =(-LeftRL.getCurrentPosition()/12);
        //}

        telemetry.addData("Lift speed",  (arl*100)+"%");
        telemetry.addData("ARL", arl);
        telemetry.addData("Left Lift Rotation", LeftRL.getCurrentPosition());
        telemetry.addData ("Right Lift Rotation", RightRL.getCurrentPosition());

        // sweeper on off gamepad2 a
        if (gamepad1.left_bumper) {
           sweeperPower = .5;
        } else if (gamepad1.right_bumper) {
            sweeperPower = -.5;
        } else {
            sweeperPower = 0;
        }
        sweeper.setPower(sweeperPower);
        telemetry.addData("Sweeper Power", sweeperPower);


        float ARM = gamepad2.left_trigger - gamepad2.right_trigger;
        arm.setPower(ARM);
        telemetry.addData("Arm Slide Power", ARM);

        //if(gamepad1.x) {
        //    SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, hellothere);
        //}
        //if(gamepad2.x) {
        //    SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, hellothere);
        //}

        telemetry.update();
    }
}