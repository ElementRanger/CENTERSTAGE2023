package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.MotorPulsesCalculator.calculatePulses;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class Test {

    private DcMotor TestM = null;

    private LinearOpMode opMode = null;



        public Test(){
        }

        public void init(LinearOpMode opMode) {

            HardwareMap hwMap;

            opMode = opMode;
            hwMap = opMode.hardwareMap;

          TestM = hwMap.dcMotor.get("TestM");

          TestM.setPower(0);
        }


        public void forwardDistance(double speed, double distance) {
            TestM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            int pulses = calculatePulses(distance);
            TestM.setTargetPosition(pulses);

            TestM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            TestM.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            TestM.setPower(.25);

            while(TestM.isBusy()) {
            }
            TestM.setPower(0);
        }



}
