package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Config.Robot;
import org.firstinspires.ftc.teamcode.Config.Movimiento;
@Disabled
@TeleOp
public class Centro_frente extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    }

    /*@Override
    public void runOpMode() throws InterruptedException {

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        Robot.imu.initialize(parameters);

        Movimiento.STOP_AND_RESET_CHASIS();
        Movimiento.STOP_AND_RESET_ELEVATOR();
        Movimiento.RUN_USING_ENCODER_ELEVATOR();
        Movimiento.RUN_USING_ENCODER_CHASIS();


        Robot.Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.options) {
                Robot.imu.resetYaw();
            }

            double botHeading = Robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

            double[] speeds = {
                    (rotY + rotX + rx) / denominator,
                    (rotY - rotX + rx) / denominator,
                    (rotY - rotX - rx) / denominator,
                    (rotY + rotX - rx) / denominator

            };

            Robot.FL.setPower(speeds[0]);
            Robot.BL.setPower(speeds[1]);
            Robot.FR.setPower(speeds[2]);
            Robot.BR.setPower(speeds[3]);

            Robot.Elevator1.setPower(gamepad2.left_stick_y);
            Robot.Elevator2.setPower(gamepad2.left_stick_y);

            int target1 = 25;
            int target2 = 200;


            if (gamepad2.dpad_down) {

                Movimiento.move_arm(0);

                Robot.Mid_arm.setPosition(0.25);

            } else if (gamepad2.dpad_right) {

                Movimiento.move_arm(5);

            } else if (gamepad2.dpad_up) {

                Movimiento.move_arm(target2);

            }


            if (Robot.Arm.getCurrentPosition() < target1) {

                Robot.Mid_arm.setPosition(0.25);

            } else if (Robot.Arm.getCurrentPosition() > target1 && Robot.Arm.getCurrentPosition() < (target1 + 25)) {

                Robot.Mid_arm.setPosition(0);

            } else if (Robot.Arm.getCurrentPosition() > (target1 + 25)) {

                Robot.Mid_arm.setPosition(0.60);

            }


                if (gamepad2.a) {

                    Movimiento.close_claw();

                } else if (gamepad2.b) {

                    Movimiento.open_claw();
                }


                if (gamepad2.right_bumper) {

                    Movimiento.up_intake();
                } else if (gamepad2.left_bumper) {

                    Movimiento.down_intake();
                }


        }

    } */
}



