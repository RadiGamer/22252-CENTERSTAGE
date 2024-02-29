package org.firstinspires.ftc.teamcode.marin;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp
public class TeleOPFinal extends OpMode {

    DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, arm, er, el;
    Servo garra, mano, avion, levantar;
    CRServo Intake;

    @Override
    public void init() {
        // Declare our motors
        // Make sure your ID's match your configuration
        frontLeftMotor = hardwareMap.dcMotor.get("fl");
        backLeftMotor = hardwareMap.dcMotor.get("bl");
        frontRightMotor = hardwareMap.dcMotor.get("fr");
        backRightMotor = hardwareMap.dcMotor.get("br");
        arm = hardwareMap.dcMotor.get("Arm");
        er = hardwareMap.dcMotor.get("er");
        el = hardwareMap.dcMotor.get("el");

        avion = hardwareMap.servo.get("av");
        garra = hardwareMap.servo.get("garra");
        mano = hardwareMap.servo.get("MA");
        levantar = hardwareMap.servo.get("lv");
        Intake = hardwareMap.crservo.get("intake");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        el.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        er.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        el.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        levantar.setPosition(0);
    }


    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;


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

        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }


        if (gamepad1.right_trigger > 0.5) {

            frontLeftMotor.setPower(speeds[0] * 0.4);
            backLeftMotor.setPower(speeds[2] * 0.4);
            frontRightMotor.setPower(speeds[1] * 0.4);
            backRightMotor.setPower(speeds[3] * 0.4);

        } else {

            frontLeftMotor.setPower(speeds[0]);
            backLeftMotor.setPower(speeds[2]);
            frontRightMotor.setPower(speeds[1]);
            backRightMotor.setPower(speeds[3]);

        }

        el.setPower(gamepad2.left_stick_y);
        er.setPower(el.getPower());

        levantar.setPosition(0.5);

        telemetry.addData("Posicion del brazo", arm.getCurrentPosition());
        telemetry.addData("arm power", arm.getPower());
        telemetry.addData("Target Brazo", arm.getTargetPosition());
        telemetry.addData("Pos mano", mano.getPosition());
        telemetry.update();

        if (gamepad2.right_bumper) {

            garra.setPosition(0);

        }
        if (gamepad2.left_bumper) {

            garra.setPosition(1);
        }

        Range.clip(arm.getCurrentPosition(), 0, 221);


        if (gamepad2.a) {

            mano.setPosition(0);

        } else if (gamepad2.b) {

            mano.setPosition(0.9);
        }

        if (gamepad2.dpad_up) {

            arm.setTargetPosition(221);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ;
            arm.setPower(0.08);

        } else if (gamepad2.dpad_down) {
            arm.setTargetPosition(0);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ;
            arm.setPower(0.1);

        } else if (gamepad2.dpad_right) {

            arm.setTargetPosition(50);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(0.2);
        }

        Intake.setPower(-gamepad2.right_trigger);

        if (gamepad1.a) {
            avion.setPosition(1);
        } else if (gamepad1.b) {
            avion.setPosition(0);
        }
        if(gamepad2.right_trigger > 0.4){
            Intake.setPower(-1);

        } else if (gamepad2.left_trigger > 0.4) {

            Intake.setPower(1);

        }else {

            Intake.setPower(0);
        }

        telemetry.addData("pos intake", levantar.getPosition());

    }
}

