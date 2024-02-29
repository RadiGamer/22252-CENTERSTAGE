package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class TestMotores extends OpMode {

    DcMotor fr,fl,br,bl;
    CRServo IntakeF,IntakeB;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        telemetry.addLine("Presiona A para probar BR");
        telemetry.addLine("Presiona B para probar BL");
        telemetry.addLine("Presiona Y para probar FR");
        telemetry.addLine("Presiona X para probar FL");


    }

    @Override
    public void loop() {
        if(gamepad1.a){
            br.setPower(1);
        }else{
            br.setPower(0);
        }
//
        if(gamepad1.b){
            bl.setPower(1);
        }else{
            bl.setPower(0);
        }
//
        if(gamepad1.y){
            fr.setPower(1);
        }else{
            fr.setPower(0);
        }
//
        if (gamepad1.x){
            fl.setPower(1);
        }else{
            fl.setPower(0);
        }
//
    }
}
