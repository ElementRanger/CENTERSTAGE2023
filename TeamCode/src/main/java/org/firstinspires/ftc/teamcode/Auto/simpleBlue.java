package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "simpleBlue", group = "auto")
@Disabled
public class simpleBlue extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        boolean finished = false;
        int state = 0;

        DriveTrain Drive = new DriveTrain();
        Intake intake = new Intake();

        Drive.init(this);
        intake.init(this);

        waitForStart();

        while (opModeIsActive() && !finished) {

            telemetry.addData("current state: ", state);
            telemetry.update();
            switch (state) {
                case 0:
                    //go to middle line
                    Drive.forward(0.25);
                    sleep(3500);
                    Drive.stop();

                    //drop pixel
                    intake.mandOpen();
                    sleep(1000);
                    intake.RightAp.setPosition(0.1);
                    sleep(1000);
                    intake.armLift();
                    sleep(1000);


                    //backup and turn and backup again (get into position)
                    Drive.forward(-0.25);
                    sleep(1550);
                    Drive.stop();
                    intake.mandClose();
                    sleep(250);

                    Drive.turn(90, .25, false);

                    Drive.forward(-0.25);
                    sleep(5500);
                    Drive.stop();

                    //score pixel on backboard
                    intake.slide3();
                    sleep(500);
                    intake.armScore();
                    sleep(500);
                    intake.LeftAp.setPosition(0.2);
                    sleep(1000);
                    intake.armLift();
                    sleep(500);
                    intake.slide0();
                    sleep(2500);
                    Drive.strafeRight(.5);
                    sleep(1000);
                    Drive.stop();

                    //other
                    finished = true;
            }
        }
    }

}

