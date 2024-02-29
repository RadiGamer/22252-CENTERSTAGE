package org.firstinspires.ftc.teamcode.Autonomos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@Autonomous
public class Base_autonomo extends LinearOpMode {

    DcMotor rampa, er, el;

    CRServo Intake, brazo1, brazo2;

    Servo lv;


    @Override
    public void runOpMode() throws InterruptedException {

        rampa = hardwareMap.dcMotor.get("ra");
        er = hardwareMap.dcMotor.get("er");
        el = hardwareMap.dcMotor.get("el");

        brazo1 = hardwareMap.crservo.get("b1");
        brazo2 = hardwareMap.crservo.get("b2");
        Intake = hardwareMap.crservo.get("intake");
        lv = hardwareMap.servo.get("lv");

        waitForStart();

        while(opModeIsActive()){


        }

    }

    public void mover_brazo(double power, int time){

        brazo1.setPower(power);
        brazo2.setPower(-power);
        sleep(time);

        brazo1.setPower(0);
        brazo2.setPower(0);
        sleep(50);

    }

    public void elevador(double power, int time){

        el.setPower(power);
        er.setPower(power);
        sleep(time);

        el.setPower(power);
        er.setPower(power);
        sleep(50);

    }


    }



