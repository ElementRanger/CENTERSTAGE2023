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


@Autonomous(name = "blueClose", group = "auto")
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
        sleep(500);
        Drive.correctHeading(0.5);
        sleep(500);

        if (leftDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 1;
        } else if (rightDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 3;
        } else {
            marker = 2;
        }

        if (marker == 1) {
            Drive.turn(90, .5, false);
            sleep(500);
            Drive.correctHeading(0.5);
            sleep(500);
            Drive.backwardDistance(.5, 22);
            sleep(500);
            Drive.correctHeading(0.5);
            sleep(500);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(500);
            intake.slide1();
            sleep(1000);
            Drive.backwardDistance(.5,21);
            sleep(500);
            Drive.correctHeading(.5);
            sleep(500);
            Drive.strafeRightFunction(.5, 20);
            sleep(250);
            Drive.correctHeading(.5);
            sleep(500);
            intake.armScore();
            sleep(500);
            Drive.backwardDistance(.25, 5);
            sleep(1000);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(500);
            intake.mandClose();
            sleep(250);
            intake.slide0();
            sleep(1500);
            Drive.strafeRightFunction(.5, 20);
            sleep(250);
            Drive.backwardDistance(.5, 12);
        } else if(marker == 2) {
            Drive.forwardDistance(.5, 4);
            sleep(500);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(500);
            Drive.backwardDistance(.5, 12);
            sleep(250);
            Drive.correctHeading(.5);
            sleep(250);
            Drive.turn(90, .5, false);
            sleep(250);
            Drive.correctHeading(.5);
            sleep(250);
            Drive.backwardDistance(.5, 29);
            sleep(250);
            Drive.correctHeading(.5);
            sleep(250);
            intake.slide1();
            sleep(250);
            Drive.backwardDistance(.5, 19);
            sleep(250);
            Drive.correctHeading(.5);
            sleep(250);
            intake.armScore();
            sleep(500);
            intake.LeftAp.setPosition(.2);
            sleep(500);
            intake.armLift();
            sleep(500);
            intake.mandClose();
            sleep(250);
            intake.slide0();
            sleep(1500);
            Drive.strafeRightFunction(.5, 30);
            sleep(500);
            Drive.backwardDistance(.5, 12);
            sleep(500);
        } else if(marker == 3) {
            Drive.turn(90, .5, false);
            sleep(500);
            Drive.correctHeading(0.5);
            sleep(500);
            Drive.forwardDistance(.5, 6);
            sleep(250);
            Drive.correctHeading(.5);
            sleep(250);
            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            sleep(500);
            Drive.backwardDistance(.5, 56);
            sleep(250);
            Drive.correctHeading(.5);
            sleep(250);
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
            sleep(1500);
            Drive.strafeRightFunction(.5, 45);
            sleep(500);
            Drive.backwardDistance(.5, 12);
            sleep(500);
        }

    }

}
