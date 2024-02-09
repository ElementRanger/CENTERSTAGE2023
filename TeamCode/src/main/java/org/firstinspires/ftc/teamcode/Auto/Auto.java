package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;


@Autonomous(name = "AutoTest", group = "Auto")
public class Auto extends LinearOpMode {

    ColorSensor color;

    @Override
    public void runOpMode() throws InterruptedException {

        boolean rightDetected = false;
        double distance = 0;
        boolean finished = false;

        DriveTrain Drive = new DriveTrain();
        Intake intake = new Intake();

        Drive.init(this);
        intake.init(this);

        waitForStart();

        while (opModeIsActive() && !finished) {

            color = hardwareMap.get(ColorSensor.class, "Distance");

            if (color instanceof DistanceSensor) {
                distance = ((DistanceSensor) color).getDistance(DistanceUnit.CM);
            }


            //drive to spike marks.
            if (distance <= 5) {
                rightDetected = true;
            } else {
                rightDetected = false;
            }
            sleep(1000);
            // spike mark right
            if (rightDetected) {
                Drive.turn(-60, .38, false);
                sleep(500);
            }

            intake.mandOpen();
            sleep(250);
            intake.RightAp.setPosition(.1);
            sleep(250);
            intake.armLift();
            // drive forward to place pixel

            telemetry.addData("detected: ", rightDetected);
            telemetry.update();
            sleep(5000);
            finished = true;
        }
    }
}
