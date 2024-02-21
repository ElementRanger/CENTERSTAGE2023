package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "AutoChoose", group = "auto")
public class AutoChoose extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


        DistanceSensor leftDistance;
        DistanceSensor rightDistance;

        boolean finished = false;
        int state = 0;
        int marker = 0;
        boolean red = false;
        boolean far = false;
        boolean rightDetected = false;
        boolean leftDetected = false;


        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        leftDistance = hardwareMap.get(DistanceSensor.class, "leftDistance");
        rightDistance = hardwareMap.get(DistanceSensor.class, "rightDistance");

        DriveTrain Drive = new DriveTrain();
        Intake intake = new Intake();

        Drive.init(this);
        intake.init(this);

        telemetry.addData("To go extra distance press: ", "b");
        telemetry.addData("To compensate for being on red alliance press: ", "a");
        telemetry.update();

        if (newGamePad2.a.released) {
            red = true;
        }
        if (newGamePad2.b.released) {
            far = true;
        }

        waitForStart();


//        marker1 == left
//        marker2 == middle
//        marker3 == right


        //drive forward
        Drive.forwardDistance(.5, 37);
        sleep(1000);

        if (leftDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 1;
        } else if (rightDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 3;
        } else {
            marker = 2;
        }

        if (marker == 1) {
            if (red) {
                Drive.turn(88, .25, true);
                telemetry.addLine("TURNING...");
                telemetry.update();
                sleep(1000);
                Drive.backwardDistance(.25, 26);
                telemetry.addLine("BACKING UP...");
                telemetry.update();
                sleep(1000);
                intake.mandOpen();
                intake.RightAp.setPosition(.35);
                if (far) {

                } else {
//                    Drive.forwardDistance(.5, 24);
                }
            } else {
                Drive.turn(90, .5, false);
            }
            //drive forward.
            //strafe left
            //deal with pixel
            //drive backward
        } else if (marker == 2) {
            //drive forward
            //deal with pixel
            //drive backward further than the other two
        } else if (marker == 3) {
            //drive forward
            //strafe right
            //deal with pixel
            //drive backward
        }


//            if (far) {
//                if (marker == 1) {
//                    // Drive.forwardDistance0(.25, -30);
        //Intake.slide1;
        // Intake.armScore
        // Intake.apertureClose
        // Drive.forwardDistance0(.25, -10);
        // Intake.armLift
        // Intake.slide0;
//                } else if (marker == 2) {
//                    //drive forward (varies based on distance)
//                } else if (marker == 3) {
//                    //drive forward (varies based on distance)
//                }
//            } else if (!far) {
//                if (marker == 1) {
//                    //drive further
//                } else if (marker == 2) {
//                    //drive forward (varies based on distance)
//                } else if (marker == 3) {
//                    //drive forward (varies based on distance)
//                }
//            }

    }
}
