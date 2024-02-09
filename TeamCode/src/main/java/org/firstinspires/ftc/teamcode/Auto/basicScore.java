package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "basicScore", group = "auto")
public class basicScore extends LinearOpMode {

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
                    sleep(4000);
                    Drive.stop();
                    Drive.forward(-0.25);
                    sleep(300);
                    Drive.stop();

                    //other
                    finished = true;
            }
        }
    }

}

