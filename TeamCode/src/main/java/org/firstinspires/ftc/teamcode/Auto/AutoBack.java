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

@Autonomous(name = "AutoBack", group = "auto")
public class AutoBack extends LinearOpMode {

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

// red closer

//        Drive.backwardDistance(0.5,11);
        Drive.forwardDistance(.5, 37);
        sleep(500);
        Drive.correctHeading(0.5);

        if (leftDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 1;
        } else if (rightDistance.getDistance(DistanceUnit.CM) < 10) {
            marker = 3;
        } else {
            marker = 2;
        }

        if (marker == 1) {
            telemetry.addLine("marker left");
            telemetry.update();
            Drive.turn(88, .25, false);
            //Drive.backwardDistance(.25,26);
            sleep(1000);
        } else {
            Drive.backwardDistance(.5, 37);
        }
    }
}
