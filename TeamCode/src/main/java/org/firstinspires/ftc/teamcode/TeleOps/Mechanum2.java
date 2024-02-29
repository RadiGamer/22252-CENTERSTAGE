package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.units.qual.A;
@Disabled
@TeleOp
public class Mechanum2 extends LinearOpMode {

    public static final double ARM_P = 0.006;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("br");
        DcMotor Arm = hardwareMap.dcMotor.get("Arm");
        DcMotor ER = hardwareMap.dcMotor.get("er");
        DcMotor EL = hardwareMap.dcMotor.get("el");

        Servo garra = hardwareMap.servo.get("Garra");
        CRServo Intake = hardwareMap.crservo.get("Intake");
        Servo Mid_arm = hardwareMap.servo.get("MA");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Arm.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        int armTarget = 0;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]

            double[] speeds = {
                    (y + x + rx),
                    (y - x - rx),
                    (y - x + rx),
                    (y + x - rx)
            };

            double max = Math.abs(speeds[0]);
            for(int i = 0; i < speeds.length; i++) {
                if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
            }

            // If and only if the maximum is outside of the range we want it to be,
            // normalize all the other speeds based on the given speed value.
            if (max > 1) {
                for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
            }


            frontLeftMotor.setPower(speeds[0]);
            backLeftMotor.setPower(speeds[2]);
            frontRightMotor.setPower(speeds[1]);
            backRightMotor.setPower(speeds[3]);

            EL.setPower(gamepad2.left_stick_y);
            ER.setPower(EL.getPower());

            int armError = (armTarget - (-Arm.getCurrentPosition()));


            if (gamepad2.dpad_down) {
                armTarget = 0;
                Arm.setPower(armError * ARM_P * 0.4);
            } else if (gamepad2.dpad_right) {
                armTarget = 15;
                Arm.setPower(armError * ARM_P * 0.4);
            } else if (gamepad2.dpad_up) {
                armTarget = 220;
                Arm.setPower(armError * ARM_P);
            }


            if(gamepad2.y) {
                Mid_arm.setPosition(0);
            } else if (gamepad2.x){
                Mid_arm.setPosition(0.25);
            } else if (gamepad1.x){
                Mid_arm.setPosition(0.6);
            }

            if (gamepad2.dpad_down) {
                armTarget = 0;

            } else if (gamepad2.dpad_right) {
                armTarget = 15;


            } else if (gamepad2.dpad_up) {
                armTarget = 220;

            }



            /*
            if (Arm.getCurrentPosition() < 10) {//> <{

                Mid_arm.setPosition(0.25);

        } else if (Arm.getCurrentPosition() > 10 && Arm.getCurrentPosition() < 30) {

                Mid_arm.setPosition(0.0);

            } else if (Arm.getCurrentPosition() > 30) {

                Mid_arm.setPosition(0.60);


            } */


        telemetry.addData("pos", Mid_arm.getPosition());

            telemetry.addData("arm power", Arm.getPower());
            telemetry.addData("arm target", armTarget);
            telemetry.addData("arm error", armError);

            telemetry.addData("pos_BRAZO", Arm.getCurrentPosition());
            telemetry.update();

            if (gamepad2.a){

                garra.setPosition(0.5);

            } else if(gamepad2.b) {

                garra.setPosition(0.85);
            }

            if (gamepad1.a) {

                Intake.setPower(-1);
            } else if (gamepad1.b) {

                Intake.setPower(1);
            } else if (gamepad1.y) {

                Intake.setPower(0);
            }

        }

        }
    }
