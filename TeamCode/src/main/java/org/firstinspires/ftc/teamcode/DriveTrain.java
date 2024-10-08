package org.firstinspires.ftc.teamcode;

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

public class DriveTrain {
    private DcMotor FrontLM = null;
    private DcMotor FrontRM = null;
    private DcMotor BackLM = null;
    private DcMotor BackRM = null;

    IMU imu;

    private LinearOpMode opmode = null;

    public DriveTrain() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));

        imu.resetYaw();

        FrontRM = hwMap.dcMotor.get("FrontRM");
        FrontLM = hwMap.dcMotor.get("FrontLM");
        BackRM = hwMap.dcMotor.get("BackRM");
        BackLM = hwMap.dcMotor.get("BackLM");

        FrontLM.setDirection(REVERSE);
        FrontRM.setDirection(FORWARD);
        BackLM.setDirection(REVERSE);
        BackRM.setDirection(REVERSE);

        FrontRM.setPower(0);
        FrontLM.setPower(0);
        BackRM.setPower(0);
        BackLM.setPower(0);
    }

    public void forward(double speed) {
        FrontRM.setPower(speed);
        FrontLM.setPower(speed);
        BackLM.setPower(speed);
        BackRM.setPower(speed);
    }

    public void backward(double speed) {
        FrontRM.setPower(-speed);
        FrontLM.setPower(-speed);
        BackLM.setPower(-speed);
        BackRM.setPower(-speed);
    }

    public void turnLeft(double speed) {
        FrontRM.setPower(speed);
        FrontLM.setPower(-speed);
        BackLM.setPower(-speed);
        BackRM.setPower(speed);
    }

    public void turnRight(double speed) {
        FrontRM.setPower(-speed);
        FrontLM.setPower(speed);
        BackLM.setPower(speed);
        BackRM.setPower(-speed);
    }

    public void strafeRight(double speed) {
        FrontRM.setPower(-speed);
        FrontLM.setPower(-speed);
        BackRM.setPower(speed);
        BackLM.setPower(speed);
    }


    public void strafeLeft(double speed) {
        FrontRM.setPower(speed);
        FrontLM.setPower(speed);
        BackLM.setPower(-speed);
        BackRM.setPower(-speed);
    }
//the ones with distance parameter- telescoping functions

    //
    public void forwardDistance(double speed, double distance) {
        imu.resetYaw();
        resetEncoders();
        int pulses = calculatePulses(distance);
        BackLM.setTargetPosition(pulses);
        //BackRM.setTargetPosition(pulses);
        FrontLM.setTargetPosition(pulses);
        FrontRM.setTargetPosition(pulses);
        runEncoders();

        while (FrontLM.isBusy() && FrontRM.isBusy() /*&& BackRM.isBusy() */ && BackLM.isBusy()) {
            forward(speed);
        }
        stop();

        motorReset();
    }

    public void backwardDistance(double speed, double distance) {
        resetEncoders();
        int pulses = calculatePulses(-distance);
        BackLM.setTargetPosition(pulses);
        //BackRM.setTargetPosition(pulses);
        FrontLM.setTargetPosition(pulses);
        FrontRM.setTargetPosition(pulses);
        runEncoders();

        while (FrontLM.isBusy() && FrontRM.isBusy() /*&& BackRM.isBusy() */ && BackLM.isBusy()) {
            backward(speed);
        }
        stop();
        motorReset();
    }

    public void forwardDistance0(double speed, int distance) {
        resetEncoders();
        int pulses = calculatePulses(distance);
        BackLM.setTargetPosition(pulses);
//        BackRM.setTargetPosition(pulses);
        FrontLM.setTargetPosition(pulses);
        FrontRM.setTargetPosition(pulses);

        prepareEncoders();

        BackLM.setPower(speed);
        BackRM.setPower(speed);
        FrontLM.setPower(speed);
        FrontRM.setPower(speed);

        while (BackLM.isBusy()) {
        }
        stop();
    }

    public void prepareEncoders() {
        BackLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        BackRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        BackRM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void strafeLeftFunction(double speed, int distance) {
        FrontRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int pulses = calculateStrafePulses(distance);
//        BackLM.setTargetPosition(pulses);
//        BackRM.setTargetPosition(pulses);
        FrontRM.setTargetPosition(pulses);
//        FrontRM.setTargetPosition(pulses);
//        runEncoders();

        FrontRM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        while (FrontRM.isBusy()) {
            strafeLeft(speed);
        }
        stop();
        FrontRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void strafeRightFunction(double speed, int distance) {
        BackLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int pulses = calculateStrafePulses(distance);
        BackLM.setTargetPosition(pulses);
//        BackRM.setTargetPosition(pulses);
//        FrontLM.setTargetPosition(-pulses);
//        FrontRM.setTargetPosition(-pulses);
//        runEncoders();
        BackLM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        while (BackLM.isBusy()) {
            strafeRight(speed);
        }
        stop();
        BackLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void motorReset() {
        BackLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stop() {
        FrontRM.setPower(0);
        FrontLM.setPower(0);
        BackLM.setPower(0);
        BackRM.setPower(0);
    }

    public void resetEncoders() {
        BackLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //BackRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void runEncoders() {
        BackLM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //BackRM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetIMU() {
        imu.resetYaw();
    }

    public void turn(double angle, double speed, boolean isLeft) {
        imu.resetYaw();
        boolean isDone = false;
        while (!isDone) {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            opmode.telemetry.addData("Yaw", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
            opmode.telemetry.update();
            if (Math.abs(orientation.getYaw(AngleUnit.DEGREES)) < Math.abs(angle)) {
                if (isLeft) {
                    turnLeft(speed);
                }
                if (!isLeft) {
                    turnRight(speed);
                }
            } else {
                imu.resetYaw();
                stop();
                isDone = true;
            }
        }
    }

    public void correctHeading(double speed) {
        double targetAngle = 0;
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();

        opmode.telemetry.addData("Yaw", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
        opmode.telemetry.update();
        //opmode.sleep(500);

        while (orientation.getYaw(AngleUnit.DEGREES) > targetAngle) {
            double correction = Math.abs(orientation.getYaw(AngleUnit.DEGREES) - targetAngle);
            correction *= 0.15;
            turnRight((speed * correction) + 0.1);
            orientation = imu.getRobotYawPitchRollAngles();
            opmode.telemetry.addData("Yaw", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
            opmode.telemetry.update();
        }

        while (orientation.getYaw(AngleUnit.DEGREES) < targetAngle) {
            double correction = Math.abs(orientation.getYaw(AngleUnit.DEGREES) - targetAngle);
            correction *= 0.15;
            turnLeft((speed * correction) + 0.1);
            orientation = imu.getRobotYawPitchRollAngles();
            opmode.telemetry.addData("Yaw", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
            opmode.telemetry.update();
        }

        stop();
        orientation = imu.getRobotYawPitchRollAngles();
        opmode.telemetry.addData("Yaw", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
        opmode.telemetry.update();
//        opmode.sleep(1000);
    }

//    public void turn2Angle(double targetAngle, double speed) {
//        // left is negative, right is positive
//        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
//        if (targetAngle > 0) {
//            while (orientation.getYaw(AngleUnit.DEGREES) <= targetAngle) {
//                opmode.telemetry.addData("angle: ", orientation.getYaw(AngleUnit.DEGREES));
//            }
//        } else if (targetAngle < 0) {
//            while (orientation.getYaw(AngleUnit.DEGREES) >= targetAngle) {
//                turnRight(speed);
//                opmode.telemetry.addData("angle: ", orientation.getYaw(AngleUnit.DEGREES));
//                opmode.telemetry.update();
//            }
//        }
//        stop();
//        opmode.telemetry.addData("angle: ", orientation.getYaw(AngleUnit.DEGREES));
//        opmode.telemetry.update();
//    }

    public void turnMath(double angle, double kP) {
        imu.resetYaw();
        Boolean isDone = false;
        double speed = 0;
        while (!isDone) {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            opmode.telemetry.addData("Yaw", "%.2f Deg. (Heading)", orientation.getYaw(AngleUnit.DEGREES));
            opmode.telemetry.update();
            if (Math.abs(orientation.getYaw(AngleUnit.DEGREES)) < Math.abs(angle)) {
                speed = (angle - orientation.getYaw(AngleUnit.DEGREES)) * kP;

                if (angle < 0) {
                    turnLeft(speed);
                }
                if (angle > 0) {
                    turnRight(speed);
                }
            } else {
                imu.resetYaw();
                stop();
                isDone = true;
                break;
            }
        }
    }
}
