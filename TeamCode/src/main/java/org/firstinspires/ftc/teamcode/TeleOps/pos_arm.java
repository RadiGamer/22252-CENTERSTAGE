package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp
public class pos_arm extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor Arm = hardwareMap.dcMotor.get("Arm");

        Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        while(opModeIsActive()) {

            telemetry.addData("armpos", Arm.getCurrentPosition());
            telemetry.update();

        }
    }
}
