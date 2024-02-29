package org.firstinspires.ftc.teamcode.Config;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
public class Movimiento extends OpMode{
    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    /* public static final double TPR_arm = 537.7;

    public static final double Angle_arm = TPR_arm/180;

    public static PIDController controller;
    public static double p = 0, i = 0, d = 0;
    public static double f = 0;
    private static ElapsedTime timing;


    @Override
    public void init() {

        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


    }

    @Override
    public void loop() {



    }

    public static void move_arm(double target) {


        controller.setPID(p, i, d);

        int arm_pos = Robot.Arm.getCurrentPosition();
        double pid = controller.calculate(arm_pos, target);
        double ff = Math.cos(Math.toRadians(target/Angle_arm)) * f;

        double power = pid + ff;

        Robot.Arm.setPower(power);

    }

    public static void move_intake() {
        Robot.Intake.setPower(-1);
    }

    public static void back_intake() {
        Robot.Intake.setPower(1);
    }

    public static void stop_intake() {
        Robot.Intake.setPower(0);
    }

    public static void move_elevator(int position){

        RUN_USING_ENCODER_ELEVATOR();
        Robot.Elevator1.setTargetPosition(position);
        Robot.Elevator2.setTargetPosition(position);
        RUN_TO_POSITION_ELEVATOR();
        Robot.Elevator1.setPower(0.8);
        Robot.Elevator2.setPower(Robot.Elevator1.getPower());

    }
    public static void move_claw (double pos) {

        Robot.Mid_arm.setPosition(pos);

    }

    public static void  lauch_airplane (double angulo) {

        Robot.Airplane.setPosition(0.8);

    }

    public static void up_intake() {
        Robot.Intake_move.setPosition(0.4);

    }
    public static void down_intake() {
        Robot.Intake_move.setPosition(0);

    }
    public static void STOP_AND_RESET_CHASIS () {

        Robot.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public static void RUN_USING_ENCODER_CHASIS () {

        Robot.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public static void RUN_TO_POSITION_CHASIS () {

        Robot.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public static void STOP_AND_RESET_ELEVATOR () {

        Robot.Elevator1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Elevator2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public static void RUN_USING_ENCODER_ELEVATOR() {

        Robot.Elevator1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.Elevator2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public static void RUN_TO_POSITION_ELEVATOR () {

        Robot.Elevator1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.Elevator2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void open_claw () {

        Robot.Garra.setPosition(0.85);

    }

    public static void close_claw () {

        Robot.Garra.setPosition(0.5);
    } */
}
