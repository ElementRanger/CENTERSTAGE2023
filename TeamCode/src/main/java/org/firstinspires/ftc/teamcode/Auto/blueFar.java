package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "blueFar", group = "auto")
public class blueFar extends LinearOpMode {
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
           Drive.forwardDistance(.5, 8);
           Drive.correctHeading(.5);
           intake.mandOpen();
           sleep(200);
           intake.RightAp.setPosition(.1);
           sleep(250);
           intake.armLift();
           sleep(250);
           intake.mandClose();
           Drive.backwardDistance(.5, 10);
           Drive.correctHeading(.5);
           sleep(200);
           Drive.strafeLeftFunction(.5, 36);
           Drive.correctHeading(.5);
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
        } else if(marker == 3) {

        }
    }
}

