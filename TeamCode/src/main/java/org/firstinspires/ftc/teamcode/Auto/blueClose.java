package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.Intake;


@Autonomous(name = "blueClose", group = "auto", preselectTeleOp = "Teleop")
public class blueClose extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain Drive = new DriveTrain();
        Intake intake = new Intake();

        Drive.init(this);
        intake.init(this);

        DistanceSensor leftDistance;
        DistanceSensor rightDistance;

        int marker = 0;

        leftDistance = hardwareMap.get(DistanceSensor.class, "leftDistance");
        rightDistance = hardwareMap.get(DistanceSensor.class, "rightDistance");

        waitForStart();


        Drive.forwardDistance(.5, 37);
        Drive.correctHeading(0.5);
        sleep(200);

        if (leftDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 1;
        } else if (rightDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 3;
        } else {
            marker = 2;
        }

        if (marker == 1) {
            Drive.turn(90, .5, false);
            Drive.correctHeading(0.5);
            sleep(200);
            Drive.backwardDistance(.5, 22);
            Drive.correctHeading(0.5);
            sleep(200);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(200);
            intake.slide1();
            sleep(500);
            Drive.backwardDistance(.5,21);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.strafeRightFunction(.5, 20);
            Drive.correctHeading(.5);
            sleep(500);
            intake.armScore();
            sleep(500);
            Drive.backwardDistance(.25, 3);
            sleep(300);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(300);
            intake.mandClose();
            sleep(300);
            intake.slide0();
            sleep(300);
            Drive.strafeRightFunction(.5, 20);
            Drive.backwardDistance(.5, 12);
        } else if(marker == 2) {
            Drive.forwardDistance(.5, 4);
            sleep(200);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(500);
            Drive.backwardDistance(.5, 12);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.turn(90, .5, false);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 29);
            Drive.correctHeading(.5);
            sleep(200);
            intake.slide1();
            intake.slideMaintain();
            sleep(250);
            Drive.backwardDistance(.5, 25);
            Drive.correctHeading(.5);
            sleep(200);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(500);
            Drive.forwardDistance(.5, 9);
            Drive.correctHeading(.5);
            sleep(200);
            intake.mandClose();
            sleep(250);
            intake.slide0();
            sleep(350);
            Drive.strafeRightFunction(.5, 34);
            Drive.backwardDistance(.5, 12);
        } else if(marker == 3) {
            Drive.turn(90, .5, false);
            Drive.correctHeading(0.5);
            sleep(250);
            Drive.forwardDistance(.5, 6);
            Drive.correctHeading(.5);
            sleep(200);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(500);
            Drive.backwardDistance(.5, 56);
            Drive.correctHeading(.5);
            sleep(200);
            intake.slide1();
            sleep(500);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(500);
            intake.mandClose();
            sleep(250);
            intake.slide0();
            sleep(350);
            Drive.strafeRightFunction(.5, 45);
            Drive.backwardDistance(.5, 12);
        }

    }

}
