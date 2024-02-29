package org.firstinspires.ftc.teamcode.pelusa2;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp
public class codigo1 extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor rampa = hardwareMap.dcMotor.get("ra");
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("br");
        /*DcMotor ER = hardwareMap.dcMotor.get("er");
        DcMotor EL = hardwareMap.dcMotor.get("el");

        /*Servo brazo1 = hardwareMap.servo.get("b1");
        Servo brazo2 = hardwareMap.servo.get("b2");*/
        CRServo Intake = hardwareMap.crservo.get("intake");
        Servo lv = hardwareMap.servo.get("lv");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

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


        }

        Intake.setPower(-gamepad1.right_trigger);

        rampa.setPower(gamepad1.left_trigger);

    }






}


