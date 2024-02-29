package org.firstinspires.ftc.teamcode.Config;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class Robot extends LinearOpMode {

    public static DcMotorEx Arm;
    public static DcMotor Elevator1;
    public static DcMotor Elevator2;
    public static DcMotor FL;
    public static DcMotor FR;
    public static DcMotor BL;
    public static DcMotor BR;
    public static CRServo Intake;
    public static Servo Intake_move;
    public static Servo Garra;
    public static Servo Mid_arm;
    public static Servo Airplane;
    public static IMU imu;


    @Override
    public void runOpMode() throws InterruptedException {

        Arm = hardwareMap.get(DcMotorEx.class, "Arm");
        Elevator1 = hardwareMap.get(DcMotor.class, "ER");
        Elevator2= hardwareMap.get(DcMotor.class, "EL");
        FL = hardwareMap.get(DcMotorEx.class, "FL");
        BL = hardwareMap.get(DcMotorEx.class, "BL");
        FR = hardwareMap.get(DcMotorEx.class, "FR");
        BR = hardwareMap.get(DcMotorEx.class, "BR");

        Intake = hardwareMap.crservo.get("Intake");
        Garra = hardwareMap.servo.get("GR");
        Mid_arm = hardwareMap.servo.get("MA");
        Airplane = hardwareMap.servo.get("Airplane");

        imu = hardwareMap.get(IMU.class, "imu");

        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        Elevator2.setDirection(DcMotorSimple.Direction.REVERSE);

    }


}
