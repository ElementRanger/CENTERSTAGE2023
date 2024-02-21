package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Intake;

@Autonomous(name = "simpleRed", group = "auto")
public class simpleRed extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

//        int state = 0;

        DriveTrain Drive = new DriveTrain();
        Intake intake = new Intake();

        Drive.init(this);
        intake.init(this);

        waitForStart();


//        Drive.strafeRightFunction(.5, 9);
//        Drive.correctHeading(0.5);

        Drive.strafeLeftFunction(.5, 9);
        Drive.correctHeading(0.5);

//        intake.armLift();
//        sleep(500);
//        intake.slide1();
//        sleep(2000);
//        intake.slide0();
//        sleep(2000);
    }

}

