package org.firstinspires.ftc.teamcode;
//"its literally just the drivetrain program, just the intake" - the venerable eat ham.

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Thread.sleep;

import android.transition.Slide;
import android.transition.Transition;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

// this code is the drive train for Intake/outtake systems

public class Intake {
    public DcMotor RightSlide = null;
    public DcMotor LeftSlide = null;
    public Servo LeftAp = null;
    public Servo RightAp = null;
    public Servo RightMand = null;
    public Servo LeftMand = null;
    public Servo RightArm = null;
    public Servo LeftArm = null;
    public Servo Light = null;
    public Servo plane = null;

    private ElapsedTime runtime = new ElapsedTime();

    private LinearOpMode opmode = null;

    double speed = .5;
    boolean hanging = false;

    DriveTrain Drive = new DriveTrain();


    GamepadStates newGamePad1 = new GamepadStates(gamepad1);
    GamepadStates newGamePad2 = new GamepadStates(gamepad2);

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        Drive.init(opMode);

        RightSlide = hwMap.dcMotor.get("RightSlide");
        LeftSlide = hwMap.dcMotor.get("LeftSlide");

        LeftAp = hwMap.servo.get("LeftAp");
        RightAp = hwMap.servo.get("RightAp");

        RightMand = hwMap.servo.get("RightMand");
        LeftMand = hwMap.servo.get("LeftMand");

        RightArm = hwMap.servo.get("RightArm");
        LeftArm = hwMap.servo.get("LeftArm");

        plane = hwMap.servo.get("plane");

        Light = hwMap.servo.get("Light");

        RightSlide.setPower(0);
        LeftSlide.setPower(0);


        RightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        LeftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftSlide.setDirection(REVERSE);

        armGrab();
        apertureOpen();
        mandClose();
        plane.setPosition(.5);
    }

    //slide
    public void slide0() {
        RightSlide.setTargetPosition(0);
        //LeftSlide.setTargetPosition(0);
        slideEncoders();
//        LeftSlide.setPower(.5);
//        RightSlide.setPower(.5);

        while (/*LeftSlide.isBusy() && */RightSlide.isBusy()) {
            RightSlide.setPower(-.5);
            LeftSlide.setPower(-.5);
        }
        slideStop();
        //LeftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void slide1() {
        RightSlide.setTargetPosition(1450);
        //LeftSlide.setTargetPosition(1450);
        slideEncoders();
//        LeftSlide.setPower(.5);
//        RightSlide.setPower(.5);
        while (/*LeftSlide.isBusy() && */ RightSlide.isBusy()) {
            slideUp();
            Drive.stop();
        }
        slideStop();
    }

    public void slideHang() {
        RightSlide.setTargetPosition(2450);
//        LeftSlide.setTargetPosition(2450);
        slideEncoders();
        hanging = true;
        while (RightSlide.isBusy()) {
            RightSlide.setPower(.5);
            LeftSlide.setPower(.5);
            opmode.telemetry.addData("Slide position: ", RightSlide.getCurrentPosition());
            opmode.telemetry.update();
        }
        slideStop();
    }

    public void slide2() {
        RightSlide.setTargetPosition(1950);
        slideEncoders();

        while (RightSlide.isBusy()) {
            RightSlide.setPower(.5);
            LeftSlide.setPower(.5);
            Drive.stop();
        }
        slideStop();
    }

    public void slide3() {
        RightSlide.setTargetPosition(1575);
        LeftSlide.setTargetPosition(1575);
        slideEncoders();
        LeftSlide.setPower(.5);
        RightSlide.setPower(.5);
        while (LeftSlide.isBusy() && RightSlide.isBusy()) {
        }
        slideStop();
    }

    public void slideReach() {
        RightSlide.setTargetPosition(2540);
        LeftSlide.setTargetPosition(2540);
        slideEncoders();
        LeftSlide.setPower(.5);
        RightSlide.setPower(.5);
        while (LeftSlide.isBusy()) {
        }
        slideStop();
    }

    public void slideEncoders() {
//        LeftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        RightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //LeftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void slideMaintain() {
        RightSlide.setPower(.15);
        LeftSlide.setPower(.15);
    }

    public void slideUp() {
        RightSlide.setPower(.5);
        LeftSlide.setPower(.5);
    }

    public void slideDown() {
        RightSlide.setPower(-.7);
        LeftSlide.setPower(-.7);
    }

    public void slideStop() {
        RightSlide.setPower(0);
        LeftSlide.setPower(0);
    }

    //obtaining pixels
    public void apertureOpen() {
        LeftAp.setPosition(.39);
        RightAp.setPosition(.4);
    }

    public void apertureClose() {
        LeftAp.setPosition(.2);
        RightAp.setPosition(.1);
    }

    //scooping pixels
    public void mandClose() {
        RightMand.setPosition(.75);
        LeftMand.setPosition(.25);
    }

    public void mandOpen() {
        RightMand.setPosition(.35);
        LeftMand.setPosition(.65);
    }

    public void mandSecure() {
        RightMand.setPosition(.5);
        LeftMand.setPosition(.5);
    }

    //arm

    public void armGrab() {
        RightArm.setPosition(0.35);
        LeftArm.setPosition(0.65);
    }

    public void armLift() {
        RightArm.setPosition(0.5);
        LeftArm.setPosition(0.5);

    }

    public void armScore() {
        RightArm.setPosition(0.05);
        LeftArm.setPosition(0.95);

    }

    public void plane(){
        plane.setPosition(.4);
    }



    public void lightChange(double value) {
        Light.setPosition(value);
    }
}