package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "redClose", group = "auto")
public class redClose extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        boolean finished = false;
        int state = 0;

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
            // 9 seconds left/ max: 6
            Drive.backwardDistance(.5, 4);
            Drive.turn(90, .5, true);
            Drive.correctHeading(.5);
            Drive.forwardDistance(.5, 6);
            Drive.correctHeading(.5);
            sleep(200);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(500);
            intake.armLift();
            sleep(500);
            Drive.backwardDistance(.5, 54);
            Drive.correctHeading(.5);
            sleep(200);
            intake.mandClose();
            sleep(250);
            Drive.strafeRightFunction(.5, 21);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 4);
            Drive.correctHeading(.5);
            sleep(200);
            intake.slide1();
            intake.slideMaintain();
            sleep(350);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(500);
            intake.slide0();
            sleep(500);
            Drive.forwardDistance(.5, 2);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.strafeLeftFunction(.5, 55);
            Drive.correctHeading(.5);
            Drive.backwardDistance(.5, 14);
        } else if (marker == 2) {
            Drive.forwardDistance(.5, 4);
            sleep(200);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(500);
            Drive.backwardDistance(.5, 12);
            sleep(200);
            intake.mandClose();
            sleep(200);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.turn(90, .5, true);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 47);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.strafeRightFunction(.5, 10);
            sleep(200);
            intake.slide1();
            sleep(150);
            intake.slideMaintain();
            sleep(200);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(200);
            intake.slide0();
            Drive.strafeLeftFunction(.5, 36);
            Drive.backwardDistance(.5, 12);
        } else if (marker == 3) {
            Drive.turn(90, .5, true);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.backwardDistance(.5, 22);
            Drive.correctHeading(0.5);
            sleep(200);
            intake.mandOpen();
            sleep(200);
            intake.RightAp.setPosition(.1);
            sleep(200);
            intake.armLift();
            sleep(200);
            Drive.backwardDistance(.5, 26);
            Drive.correctHeading(.5);
            sleep(200);
            Drive.strafeLeftFunction(.5, 5);
            Drive.correctHeading(.5);
            sleep(200);
            intake.slide1();
            intake.slideMaintain();
            sleep(350);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(250);
            intake.mandClose();
            sleep(200);
            intake.slide0();
            sleep(350);
            Drive.strafeLeftFunction(.5, 24);
            Drive.backwardDistance(.5, 12);
        }
    }
}