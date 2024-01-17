package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.DriveTrain;

@TeleOp(name = "Basic: Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        double speed = .5;
        boolean isMandOpen = false;
        boolean reversed = false;
        int ArmState = 1;
        int stateSlide = 0;
        boolean stateThere = false;

        //int SlideState = 0;
        // amount to slew servo each CYCLE_MS cycle


        DriveTrain Drive = new DriveTrain();
        Intake intake = new Intake();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        Drive.init(this);
        intake.init(this);

        intake.apertureOpen();


        waitForStart();

        telemetry.addData("Speed:", speed);

        int CYCLE_MS = 50;     // period of each cycle


        while (opModeIsActive()) {
            newGamePad1.updateState();
            newGamePad2.updateState();

            if (newGamePad1.a.released) {
                if (reversed == true) {
                    reversed = false;
                } else {
                    reversed = true;
                }
            }

            if (newGamePad1.right_bumper.released) {
                speed += 0.1;
                telemetry.addData("Speed:", speed);
                if (speed >= 1.0) {
                    speed = 1.0;
                }
            }

            if (newGamePad1.left_bumper.released) {
                speed -= .1;
                telemetry.addData("Speed:", speed);
                if (speed <= 0) {
                    speed = 0.1;
                }
            }

            if (gamepad1.left_stick_y < -0.4) {
                Drive.forward(speed);
            } else if (gamepad1.left_stick_y > 0.4) {
                Drive.backward(speed);
            } else if (gamepad1.left_stick_x > 0.4) {
                Drive.strafeRight(speed);
            } else if (gamepad1.left_stick_x < -0.4) {
                Drive.strafeLeft(speed);
            } else if (gamepad1.right_stick_x > 0.4) {
                Drive.turnRight(speed);
            } else if (gamepad1.right_stick_x < -0.4) {
                Drive.turnLeft(speed);
            } else {
                Drive.stop();
            }

//            //slides movement
//            if (gamepad2.dpad_up) {
//                SlideState++;
//                if (SlideState > 3) {
//                    SlideState = 3;
//                }
//                intake.slidePos(SlideState);
//            } else if (gamepad2.dpad_down) {
//                SlideState--;
//                if (SlideState < 0) {
//                    SlideState = 0;
//                }
//                intake.slidePos(SlideState);
//            } else {
//                intake.slideStop();
//            }

            if (newGamePad2.dpad_up.released) {
                stateSlide++;
                if (stateSlide > 3) {
                    stateSlide = 3;
                }
                stateThere = false;
            } else if (newGamePad2.dpad_down.released) {
                if(stateSlide==3||ArmState==0||ArmState==1) {
                    stateSlide--;
                }
                if (stateSlide < 0) {
                    stateSlide = 0;
                }
                stateThere = false;
            }
            if (stateThere == false) {
                if (stateSlide == 0) {
                    intake.slide0();
                    stateThere = true;
                } else if (stateSlide == 1) {
                    intake.slide1();
                    stateThere = true;

                } else if (stateSlide == 2) {
                    intake.slide2();
                    stateThere = true;

                } else if (stateSlide == 3) {
                    intake.slide3();
                    stateThere = true;

                }
            }

            //telemetry.addData("Slide Pos: ", SlideState);

            //mandible (the thing that brings in the pixels)
            if (newGamePad2.left_bumper.released || newGamePad2.right_bumper.released) {
                if (isMandOpen) {
                    intake.mandClose();
                    isMandOpen = false;
                } else if (!isMandOpen) {
                    intake.mandOpen();
                    isMandOpen = true;
                }
            }

            //aperture (grips the pixels)
            if (newGamePad2.a.released) {
                intake.apertureOpen();
            } else if (newGamePad2.b.released) {
                intake.apertureClose();
            }


            //armState0: safe lifted position
            //armState1: horizontal grabbing position
            //armState2: scoring position

            if (newGamePad2.dpad_right.released) {
                if((stateSlide==2||stateSlide==3||ArmState==0)) {
                    ArmState++;
                }
                if (ArmState >= 2) {
                    ArmState = 2;
                }
            } else if (newGamePad2.dpad_left.released) {
                ArmState--;
                if (ArmState <= 0) {
                    ArmState = 0;
                }
            }

            if (ArmState == 0) {
                intake.armLift();
            } else if (ArmState == 1) {
                intake.armGrab();
            } else if (ArmState == 2) {
                intake.armScore();
            }

            telemetry.addData("Arm position: ", ArmState);
            telemetry.addData("arm hieght left", intake.LeftSlide.getCurrentPosition());
            telemetry.addData("arm hieght right", intake.RightSlide.getCurrentPosition());

            telemetry.update();

            if (gamepad2.right_stick_y < -.4) {
                intake.slideUp();
            } else if (gamepad2.right_stick_y > .4) {
                intake.slideDown();
            } else {
                intake.slideStop();
            }
        }
    }
}
