package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "redFar", group = "auto")
public class redFar extends LinearOpMode {
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

        if(marker == 1) {
            Drive.turn(90, .5, true);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.forwardDistance(.5, 6);
            Drive.correctHeading(.5);
            sleep(200);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(350);
            intake.armLift();
            sleep(350);
            Drive.backwardDistance(.5, 6);
            Drive.correctHeading(.5);
            sleep(200);
            intake.mandClose();
            sleep(250);
            Drive.strafeLeftFunction(.5, 37);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 65);
            Drive.correctHeading(.5);
            Drive.strafeRightFunction(.5, 5);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 28);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.strafeRightFunction(.5, 47);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 18);
            Drive.correctHeading(.5);
            sleep(200);
            intake.slide2();
            intake.slideMaintain();
            sleep(250);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(350);
            intake.slide0();
        } else if(marker == 2) {
            Drive.forwardDistance(.5, 4);
            Drive.correctHeading(.5);
            sleep(200);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(350);
            Drive.backwardDistance(.5, 12);
            sleep(200);
            intake.mandClose();
            sleep(200);
            Drive.correctHeading(.5);
            sleep(250);
            Drive.backwardDistance(.5, 24);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.turn(90, .5, true);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.turn(3, .5, true);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 65);
            Drive.strafeRightFunction(.5, 5);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.turn(3, .5, false);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 28);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.strafeRightFunction(.5, 26);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 20);
            Drive.correctHeading(.5);
            sleep(200);
            intake.slide2();
            intake.slideMaintain();
            sleep(250);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(350);
            intake.slide0();
        } else if(marker == 3) {
            Drive.turn(90, .5, false);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.forwardDistance(.5, 8);
            Drive.correctHeading(.5);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(500);
            Drive.backwardDistance(.5, 10);
            Drive.correctHeading(.5);
            intake.mandClose();
            Drive.strafeRightFunction(.5, 38);
            Drive.correctHeading(.5);
            Drive.forwardDistance(.5, 65);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.turn(90, .5, true);
            Drive.correctHeading(.5);
            sleep(100);
            Drive.turn(90, .5, true);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 34);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.strafeRightFunction(.5, 20);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 15);
            Drive.correctHeading(.5);
            sleep(200);
            intake.slide2();
            intake.slideMaintain();
            sleep(350);
            intake.armScore();
            sleep(500);
            intake.apertureClose();
            sleep(500);
            intake.armLift();
            sleep(350);
            intake.slide0();
        }
    }
}

