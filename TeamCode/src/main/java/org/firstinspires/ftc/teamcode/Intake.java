package org.firstinspires.ftc.teamcode;
//"its literally just the drivetrain program, just the intake" - the venerable eat ham.

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;
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

    private ElapsedTime runtime = new ElapsedTime();

    double speed = .5;
    boolean hanging = false;


//    int SlidePos = 1;
//    int ArmPos = 2;

    DriveTrain Drive = new DriveTrain();


    GamepadStates newGamePad1 = new GamepadStates(gamepad1);
    GamepadStates newGamePad2 = new GamepadStates(gamepad2);

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opMode = opMode;
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

        RightSlide.setPower(0);
        LeftSlide.setPower(0);

        RightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftSlide.setDirection(REVERSE);

        //armDown();
        apertureOpen();
        mandClose();
    }

    //slide
    public void slide0() {
        RightSlide.setTargetPosition(0);
        LeftSlide.setTargetPosition(0);
        slideEncoders();
        LeftSlide.setPower(.5);
        RightSlide.setPower(.5);
        while (LeftSlide.isBusy() && RightSlide.isBusy()) {
        }
        slideStop();
    }

    public void slide1() {
        RightSlide.setTargetPosition(1450);
        LeftSlide.setTargetPosition(1450);
        slideEncoders();
        LeftSlide.setPower(.25);
        RightSlide.setPower(.25);
        while (LeftSlide.isBusy() && RightSlide.isBusy()) {
            Drive.stop();
        }
        slideStop();
    }

    public void slideHang() {
        RightSlide.setTargetPosition(1750);
        LeftSlide.setTargetPosition(1750);
        slideEncoders();
        RightSlide.setPower(.5);
        LeftSlide.setPower(.5);
        hanging = true;
        while (LeftSlide.isBusy() && RightSlide.isBusy()) {
        }

    }

    public void slide2() {
        RightSlide.setTargetPosition(1550);
        LeftSlide.setTargetPosition(1550);
        slideEncoders();
        RightSlide.setPower(.5);
        LeftSlide.setPower(.5);
        while (LeftSlide.isBusy() && RightSlide.isBusy()) {
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
        while(LeftSlide.isBusy()) {
        }
        slideStop();
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
        RightSlide.setPower(-.5);
        LeftSlide.setPower(-.5);
    }

    public void slideStop() {
        RightSlide.setPower(0);
        LeftSlide.setPower(0);
    }

    //obtaining pixels
    public void apertureOpen() {
        LeftAp.setPosition(.4);
        RightAp.setPosition(.35);
    }

    public void apertureClose() {
        LeftAp.setPosition(.2);
        RightAp.setPosition(.15);
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


//    public void pixels() throws InterruptedException {
//        // Automate Intake later
//
//
//        //close aperture
//        apertureClose();
//        sleep(500);
//
//        //move slides down
//        sleep(500);
//
//        // open aperture
//        apertureOpen();
//        sleep(500);
//
//        //lift slides to position one
//        sleep(500);
//
//        //move arm to scoring positions
//        armLift();
//        sleep(500);
//
//    }

    public void slideEncoders() {
        LeftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}