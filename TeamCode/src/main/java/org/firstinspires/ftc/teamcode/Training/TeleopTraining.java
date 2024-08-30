package org.firstinspires.ftc.teamcode.Training;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.GamepadStates;


@TeleOp(name = "Basic: TeleopTraining", group = "Teleop")
public class TeleopTraining extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        double speed;

        Drive Drive = new Drive();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        Drive.init(this);

        waitForStart();

        while (opModeIsActive()) {
            newGamePad1.updateState();
            newGamePad2.updateState();

            if (true) {
                Drive.forward(gamepad1);
            } else {
                Drive.stop();

                if (newGamePad1.y.state) {
                    Drive.forward(-1);
                } else {
                    Drive.stop();
                }
            }

        }

    }
}
