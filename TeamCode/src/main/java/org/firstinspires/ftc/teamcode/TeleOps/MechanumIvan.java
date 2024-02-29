package org.firstinspires.ftc.teamcode.TeleOps;

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
public class MechanumIvan extends OpMode {

    DcMotor frontLeftMotor, backLeftMotor, frontRightMotor,backRightMotor, arm, er, el;
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


                if (gamepad2.b){

                    garra.setPosition(0);

                }
                if(gamepad2.a) {

                    garra.setPosition(1);
                }


               if (gamepad2.right_bumper){

                 mano.setPosition(0);

                } else if(gamepad2.left_bumper) {

                 mano.setPosition(0.7);

                } else if (gamepad2.y){

                   mano.setPosition(0.9);

               }


            arm.setPower(gamepad2.right_stick_y * 0.6);


               if(gamepad2.right_trigger > 0.4){
                Intake.setPower(-1);

               } else if (gamepad2.left_trigger > 0.4) {

                   Intake.setPower(1);

               }else {

                   Intake.setPower(0);
               }

               if (gamepad2.dpad_down){

                   levantar.setPosition(0);

               }

                /*if (gamepad1.a) {
                    avion.setPosition(1);
                }else if(gamepad1.b){
                    avion.setPosition(0);
                }*/



        /*if (arm.getCurrentPosition() < 20) {//> <{

            mano.setPosition(0.25);

        } else if (arm.getCurrentPosition() > 20 && arm.getCurrentPosition() < 40) {

            mano.setPosition(0.0);

        } else if (arm.getCurrentPosition() > 40) {

            mano.setPosition(0.60);

        }
        */
    }

}

