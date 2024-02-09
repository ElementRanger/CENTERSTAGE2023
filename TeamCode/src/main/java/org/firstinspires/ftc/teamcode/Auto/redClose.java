package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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

        waitForStart();

        while (opModeIsActive() && !finished) {

            telemetry.addData("current state: ", state);
            telemetry.update();


            switch (state) {
                case 0:
//                    Drive.strafeRightFunction(0.3, 12);
                    Drive.forward(.5);
                    sleep(5000);
                    Drive.backward(.25);
                    sleep(250);
                    Drive.stop();
                    state++;
                case 1:
//                        intake.RightAp.setPosition(.1);
                    state++;
                case 2:
//                    Drive.turn;
                    state++;
                case 3:
//                    Drive.turn(90, .25, false);
                    state++;
                case 4:
                    state++;
                case 5:
//                    Drive.strafeRightFunction(0.3, 12);
                    state++;
                    finished = true;
            }

        }
    }
}