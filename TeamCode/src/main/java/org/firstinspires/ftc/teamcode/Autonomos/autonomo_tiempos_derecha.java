package org.firstinspires.ftc.teamcode.Autonomos;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous
public class autonomo_tiempos_derecha extends LinearOpMode {

    DcMotor fl, bl, fr, br, rampa, er, el;
    CRServo Intake, brazo1, brazo2;
    Servo lv, tope;


    IMU imu;

    private double previousAngle;

    private double globalAngle;

    double Pid = 0.006;

    double toleranciaGrados = 2;
    double poderMinimo = 0.15;

    @Override
    public void runOpMode() throws InterruptedException {
        rampa = hardwareMap.dcMotor.get("ra");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");
        rampa = hardwareMap.dcMotor.get("ra");
        er = hardwareMap.dcMotor.get("er");
        el = hardwareMap.dcMotor.get("el");

        brazo1 = hardwareMap.crservo.get("b1");
        brazo2 = hardwareMap.crservo.get("b2");
        Intake = hardwareMap.crservo.get("intake");
        lv = hardwareMap.servo.get("lv");
        tope = hardwareMap.servo.get("tope");

        imu = hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot orientationRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP);
                imu.initialize(new IMU.Parameters(orientationRobot));

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        el.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rampa.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        el.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        er.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        tope.setPosition(1);

        levantar_intake();

        waitForStart();

        enfrente(0.2, 3100);
        atras(0.2, 800);
        giroangulo(87);
        atras(0.2, 2450);
        derecha(0.2, 2500);

        girar_brazo(0.7, 3500);
        atras(0.2, 1100);

        girar_brazo(-0.7, 1800);
    }

    void giroangulo(double grados) {

        imu.resetYaw();

        double error;

        do {

            double anguloImu = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
            double anguloDelta = anguloImu - previousAngle;

            if(anguloDelta < -180) {
                anguloDelta += 360;
            } else if(anguloDelta > 180) {
                anguloDelta -= 360;
            }

            globalAngle += anguloDelta;

            error = grados - globalAngle;

            double power = error * Pid;

            if (Math.abs(power) < poderMinimo) {
                power = Math.signum(power) * poderMinimo;
            }

            telemetry.addData("angulo", globalAngle);
            telemetry.addData("Error", error);
            telemetry.addData("Power", power);

            telemetry.update();


                fl.setPower(-power);
                bl.setPower(-power);
                fr.setPower(power);
                br.setPower(power);


            previousAngle = anguloImu;
        } while (Math.abs(error) > toleranciaGrados && opModeIsActive());

            sleep(500);


        }


    public void enfrente (double power, int time){

        fl.setPower(power);
        bl.setPower(power);
        fr.setPower(power);
        br.setPower(power);
        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        sleep(50);

    }

    public void atras (double power, int time){

        fl.setPower(-power);
        bl.setPower(-power);
        fr.setPower(-power);
        br.setPower(-power);
        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        sleep(50);

    }

    public void derecha (double power, int time){

        fl.setPower(power);
        bl.setPower(-power);
        fr.setPower(-power);
        br.setPower(power);
        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        sleep(50);

    }

    public void izquierda (double power, int time){

        fl.setPower(-power);
        bl.setPower(power);
        fr.setPower(power);
        br.setPower(-power);
        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        sleep(50);

    }

    public void giro_derecha (double power, int time){

        fl.setPower(power);
        bl.setPower(power);
        fr.setPower(-power);
        br.setPower(-power);
        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        sleep(50);

    }

    public void giro_izquierda (double power, int time){

        fl.setPower(-power);
        bl.setPower(-power);
        fr.setPower(power);
        br.setPower(power);
        sleep(time);

        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        sleep(50);

    }

    public void levantar_intake (){

        lv.setPosition(0);

    } public void bajar_intake(){

        lv.setPosition(0.9);

    }

    public void mover_brazo(double power, int time){

        brazo1.setPower(power);
        brazo2.setPower(power);

        sleep(time);

        brazo1.setPower(0);
        brazo2.setPower(0);
        sleep(50);

    }

    public void pixeles(double power, int time){

        Intake.setPower(power);
        sleep(time);

        Intake.setPower(0);
        sleep(50);

    }

    void giroizquierda(double potencia) {
        fr.setPower(potencia);
        fl.setPower(-potencia);
        bl.setPower(-potencia);
        br.setPower(potencia);

    }

    void giroderecha(double potencia) {
        fr.setPower(-potencia);
        fl.setPower(potencia);
        bl.setPower(potencia);
        br.setPower(-potencia);

    }

    void podermotor(double potencia) {

        fr.setPower(potencia);
        fl.setPower(potencia);
        bl.setPower(potencia);
        br.setPower(potencia);

    }

    void girar_brazo(double power, int time){

        brazo1.setPower(-power);
        brazo2.setPower(power);
        sleep(time);

        brazo1.setPower(0);
        brazo2.setPower(0);
        sleep(50);

    }


}
