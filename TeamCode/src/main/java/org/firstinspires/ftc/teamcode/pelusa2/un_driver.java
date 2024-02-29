package org.firstinspires.ftc.teamcode.pelusa2;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp
public class un_driver extends OpMode {

    DcMotor rampa, frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, er, el, colgar;

    CRServo Intake, brazo1, brazo2;

    Servo lv;

    @Override
    public void init() {

        rampa = hardwareMap.dcMotor.get("ra");
        frontLeftMotor = hardwareMap.dcMotor.get("fl");
        backLeftMotor = hardwareMap.dcMotor.get("bl");
        frontRightMotor = hardwareMap.dcMotor.get("fr");
        backRightMotor = hardwareMap.dcMotor.get("br");
        er = hardwareMap.dcMotor.get("er");
        el = hardwareMap.dcMotor.get("el");
        colgar = hardwareMap.dcMotor.get("colgar");

        brazo1 = hardwareMap.crservo.get("b1");
        brazo2 = hardwareMap.crservo.get("b2");
        Intake = hardwareMap.crservo.get("intake");
        lv = hardwareMap.servo.get("lv");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        el.setDirection(DcMotorSimple.Direction.REVERSE);


        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {


        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x; // Counteract imperfect strafing
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
        for (int i = 0; i < speeds.length; i++) {
            if (max < Math.abs(speeds[i])) max = Math.abs(speeds[i]);
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


        /*brazo2.setPower(-gamepad2.right_stick_y);
        brazo1.setPower(gamepad2.right_stick_y);*/

        if (gamepad1.right_trigger > 0.4) {

            brazo1.setPower(gamepad1.right_trigger);

            brazo2.setPower(-gamepad1.right_trigger);

        } else if (gamepad1.left_trigger > 0.4) {

            brazo1.setPower(-gamepad1.left_trigger);
            brazo2.setPower(gamepad1.left_trigger);

        } else {

            brazo1.setPower(0);
            brazo2.setPower(0);

        }


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


