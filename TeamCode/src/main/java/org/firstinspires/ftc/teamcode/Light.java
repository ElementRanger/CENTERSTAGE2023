package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "lightTest", group = "Teleop")
public class Light extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Intake intake = new Intake();
        double value = .53;

        intake.init(this);

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);


        waitForStart();

        while (opModeIsActive()) {

            newGamePad1.updateState();

            if(newGamePad1.dpad_up.released) {
                value += .01;
            } else if(newGamePad1.dpad_down.released) {
                value -=.01;
            }
            telemetry.addData("value", value);
            telemetry.update();

            intake.lightChange(value);

        }
    }

}

