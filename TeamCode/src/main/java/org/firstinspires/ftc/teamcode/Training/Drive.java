package org.firstinspires.ftc.teamcode.Training;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.MotorPulsesCalculator.calculatePulses;
import static org.firstinspires.ftc.teamcode.StrafePulsesCalculator.*;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.DriveTrain;

public class Drive {
    private DcMotor FrontLM = null;
//    private DcMotor FrontRM = null;
//    private DcMotor BackLM = null;
//    private DcMotor BackRM = null;

    private LinearOpMode opmode = null;

    public Drive() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        FrontLM = hwMap.dcMotor.get("FrontLM");
//        FrontRM = hwMap.dcMotor.get("FrontRM");
//        BackLM = hwMap.dcMotor.get("BackLM");
//        BackRM = hwMap.dcMotor.get("BackRM");

        FrontLM.setDirection(FORWARD);
//        FrontRM.setDirection(FORWARD);
//        BackLM.setDirection(FORWARD);
//        BackRM.setDirection(FORWARD);

//        FrontRM.setPower(0);
        FrontLM.setPower(0);
//        BackLM.setPower(0);
//        BackRM.setPower(0);


    }
    //even funnyer code i younked
    public void forward(double speed) {
//        FrontRM.setPower(speed);
        FrontLM.setPower(speed);
//        BackLM.setPower(speed);
//        BackRM.setPower(speed);
    }

    public void stop() {
        FrontLM.setPower(0);
    }

    public void backward(double speed) {
//        FrontRM.setPower(-speed);
        FrontLM.setPower(-speed);
//        BackLM.setPower(-speed);
//        BackRM.setPower(-speed);
    }

    public void turnLeft(double speed) {
//        FrontRM.setPower(speed);
        FrontLM.setPower(-speed);
//        BackLM.setPower(-speed);
//        BackRM.setPower(speed);
    }

    public void turnRight(double speed) {
//        FrontRM.setPower(-speed);
        FrontLM.setPower(speed);
//        BackLM.setPower(speed);
//        BackRM.setPower(-speed);
    }

    public void strafeRight(double speed) {
//        FrontRM.setPower(-speed);
        FrontLM.setPower(-speed);
//        BackRM.setPower(speed);
//        BackLM.setPower(speed);
    }


    public void strafeLeft(double speed) {
//        FrontRM.setPower(speed);
        FrontLM.setPower(speed);
//        BackLM.setPower(-speed);
//        BackRM.setPower(-speed);
    }

}