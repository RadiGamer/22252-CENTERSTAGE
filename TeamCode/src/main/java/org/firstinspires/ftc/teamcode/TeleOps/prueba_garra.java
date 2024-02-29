package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp
public class prueba_garra extends OpMode {

    Servo garra;
    Servo mid_arm;


    @Override
    public void init() {

        garra = hardwareMap.servo.get("Garra");
        mid_arm = hardwareMap.servo.get("MA");

    }

    @Override
    public void loop() {

        if (gamepad2.a){

            garra.setPosition(0.5);

        }else if (gamepad2.b)

            garra.setPosition(0.85);

        if (gamepad2.dpad_down){

            mid_arm.setPosition(0);

        }else if (gamepad2.dpad_left) {

            mid_arm.setPosition(0.25);


        }else if (gamepad2.dpad_up){

            mid_arm.setPosition(0.65);

        }



        telemetry.addData("as", mid_arm.getPosition());
        telemetry.update();

    }
}
