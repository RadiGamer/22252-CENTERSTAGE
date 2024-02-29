package org.firstinspires.ftc.teamcode.pelusa2;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp
public class PRUEBA_RAMPA extends OpMode {

    DcMotor rampa;

    @Override
    public void init() {

        rampa = hardwareMap.dcMotor.get("rampa");

    }

    @Override
    public void loop() {

            rampa.setPower(gamepad1.right_stick_y);



    }
}


