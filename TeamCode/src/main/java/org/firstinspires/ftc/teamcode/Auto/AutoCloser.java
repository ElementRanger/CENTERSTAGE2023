package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptTensorFlowObjectDetectionEasy;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "AutoCloser", group = "auto")
public class AutoCloser extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        boolean leftLoc = false;
        boolean midLoc = false;
        boolean finished = false;
        int state = 0;

        DriveTrain Drive = new DriveTrain();
        Intake Intake = new Intake();
        ConceptTensorFlowObjectDetectionEasy CV = new ConceptTensorFlowObjectDetectionEasy();

        Drive.init(this);
        Intake.init(this);
        CV.init();


        waitForStart();


        while (opModeIsActive() && !finished) {

            telemetry.addData("Current state: ", state);
            telemetry.update();

            switch (state) {
                case 0:
                    Drive.resetEncoders();
                    Drive.forwardDistance(.7, 15);
                    sleep(250);
                    Drive.resetIMU();
                    Drive.turn(45, 0.6, true);
                    state = 1;
                    break;
                case 1:
                    CV.getTfod();
                    break;
            }
//            finished = true;
//            Drive.turn(45, .5, true);
//
//            if(CV.equals("Pixel")) {
//                leftLoc = true;
//            }

        }
    }
}