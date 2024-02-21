package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.Intake;
@Disabled
@Autonomous(name = "ComplexZone", group = "ComplexZone")
public class complexZone extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain Drive = new DriveTrain();
        Intake intake = new Intake();

        Drive.init(this);
        intake.init(this);

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        Boolean changeAngle = false;
        int extraDistance = 0;
        telemetry.addData("To go extra distance press: ", "b");
        telemetry.addData("To compensate for being on blue alliance press: ", "b");
        telemetry.update();

        if (newGamePad2.a.released) {
            changeAngle = true;
        }
        if (newGamePad2.b.released) {
            extraDistance = 24;
        }

        waitForStart();

        //Drive.forwardDistance(0.5, 10 + extraDistance);
        Drive.forward(0.5);
        sleep(1000+extraDistance);
        if (!changeAngle) {
            Drive.turn(0.5, 90, true);
        } else {
            Drive.turn(0.5, 90, false);
        }
        Drive.forwardDistance(0.5, 20);
    }
}