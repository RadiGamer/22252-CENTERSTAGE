package org.firstinspires.ftc.teamcode.TeleOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp
public class CON_BRAZO extends LinearOpMode {

    private PIDFCoefficients controller;

    public static double p = 0, i = 0, d = 0;
    public static double f=0;

    public static int target = 0;

    private final double ticks_in_degree = 751.8/180;

    private DcMotorEx arm;


    DcMotor frontLeftMotor, backLeftMotor, frontRightMotor,backRightMotor, er, el;
    Servo garra, mano, avion, levantar;
    CRServo Intake;

    @Override
    public void runOpMode() throws InterruptedException {

        controller = new PIDFCoefficients(p,i,d,f);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        frontLeftMotor = hardwareMap.dcMotor.get("fl");
        backLeftMotor = hardwareMap.dcMotor.get("bl");
        frontRightMotor = hardwareMap.dcMotor.get("fr");
        backRightMotor = hardwareMap.dcMotor.get("br");

        arm = hardwareMap.get(DcMotorEx.class, "arm");

        er = hardwareMap.dcMotor.get("er");
        el = hardwareMap.dcMotor.get("el");

        avion = hardwareMap.servo.get("av");
        garra = hardwareMap.servo.get("garra");
        mano = hardwareMap.servo.get("MA");
        levantar = hardwareMap.servo.get("lv");
        Intake = hardwareMap.crservo.get("intake");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        el.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        er.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        el.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()){

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
            for(int i = 0; i < speeds.length; i++) {
                if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
            }

            if (max > 1) {
                for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
            }


            if (gamepad1.right_trigger > 0.5) {

                frontLeftMotor.setPower(speeds[0] * 0.5);
                backLeftMotor.setPower(speeds[2] * 0.5);
                frontRightMotor.setPower(speeds[1] * 0.5);
                backRightMotor.setPower(speeds[3] * 0.5);

            } else{

                frontLeftMotor.setPower(speeds[0]);
                backLeftMotor.setPower(speeds[2]);
                frontRightMotor.setPower(speeds[1]);
                backRightMotor.setPower(speeds[3]);

            }

            el.setPower(gamepad2.left_stick_y);
            er.setPower(el.getPower());


            if (gamepad2.a){

                garra.setPosition(0);

            }
            if(gamepad2.b) {

                garra.setPosition(1);
            }


            if (gamepad2.dpad_up){

                arm.setTargetPosition(221);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setPower(0.08);

            } else if(gamepad2.dpad_down){
                arm.setTargetPosition(0);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setPower(0.08);

            } else if(gamepad2.dpad_right){
                arm.setTargetPosition(25);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setPower(0.08);
            }


            if(gamepad2.right_trigger > 0.4){
                Intake.setPower(-1);

            } else if (gamepad2.left_trigger > 0.4) {

                Intake.setPower(1);

            }else {

                Intake.setPower(0);
            }


        }

    }







}

