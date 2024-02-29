package org.firstinspires.ftc.teamcode.pelusa2;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;
@Disabled
@TeleOp
public class codigo2_fieldcentric_linear extends LinearOpMode {

    DcMotor rampa, er, el, colgar;

    CRServo Intake, brazo1, brazo2;

    Servo lv;

    SampleMecanumDrive move = new SampleMecanumDrive(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {

        rampa = hardwareMap.dcMotor.get("ra");
        er = hardwareMap.dcMotor.get("er");
        el = hardwareMap.dcMotor.get("el");
        colgar = hardwareMap.dcMotor.get("colgar");

        brazo1 = hardwareMap.crservo.get("b1");
        brazo2 = hardwareMap.crservo.get("b2");
        Intake = hardwareMap.crservo.get("intake");
        lv = hardwareMap.servo.get("lv");

        el.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()){

            Vector2d drive = new Vector2d(-gamepad1.left_stick_y, -gamepad1.left_stick_x).rotated(-move.getPoseEstimate().getHeading());

            move.setWeightedDrivePower(new Pose2d(drive.getX(), drive.getY(), -gamepad1.right_stick_x));

            if (gamepad1.dpad_up){

                move.setPoseEstimate(new Pose2d());

            }

            move.update();


            if (gamepad2.right_trigger > 0.4) {

                Intake.setPower(-1);

                rampa.setPower(1);

            } else if (gamepad2.left_trigger > 0.4) {

                Intake.setPower(1);
                rampa.setPower(-1);

            } else {

                Intake.setPower(0);

                rampa.setPower(0);

            }


            brazo2.setPower(-gamepad2.right_stick_y);
            brazo1.setPower(gamepad2.right_stick_y);


            if (gamepad2.dpad_right) {

                lv.setPosition(1);

            } else if (gamepad2.dpad_left){

                lv.setPosition(0);

            }

            if (gamepad2.dpad_up) {

                colgar.setPower(1);

            } else if (gamepad2.dpad_down){

                colgar.setPower(-1);

            } else {

                colgar.setPower(0);

            }

            er.setPower(gamepad2.left_stick_y);
            el.setPower(er.getPower());



        }
    }

}


