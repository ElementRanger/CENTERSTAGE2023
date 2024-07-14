package org.firstinspires.ftc.teamcode;


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

@TeleOp(name = "Basic: Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        double speed = .5;
        boolean reversed = false;
        boolean isMandOpen = false;
        int ArmState = 1;
        int stateSlide = 0;
        boolean stateThere = true;
        int manual = 0;
        boolean state1Finished = false;
        boolean state2Finished = false;
        boolean state3Finished = false;
        boolean state4Finished = false;
        boolean hung = false;
        boolean matchFinished = false;


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


        int CYCLE_MS = 50;     // period of each cycle


        while (opModeIsActive()) {
            newGamePad1.updateState();
            newGamePad2.updateState();


            // player 1
            if (!reversed) {
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
            } else if (reversed) {
                if (gamepad1.left_stick_y < -0.4) {
                    Drive.backward(speed);
                } else if (gamepad1.left_stick_y > 0.4) {
                    Drive.forward(speed);
                } else if (gamepad1.left_stick_x > 0.4) {
                    Drive.strafeLeft(speed);
                } else if (gamepad1.left_stick_x < -0.4) {
                    Drive.strafeRight(speed);
                } else if (gamepad1.right_stick_x > 0.4) {
                    Drive.turnRight(speed);
                } else if (gamepad1.right_stick_x < -0.4) {
                    Drive.turnLeft(speed);
                } else {
                    Drive.stop();
                }
            }

            if (newGamePad1.b.released) {
                if (reversed) {
                    reversed = false;
                } else {
                    reversed = true;
                }
                telemetry.addData("Reversed? ", reversed);
                telemetry.update();
            }


            if (newGamePad1.right_bumper.released) {
                speed += 0.1;
                if (speed >= 1.0) {
                    speed = 1.0;
                }
            }

            if (newGamePad1.left_bumper.released) {
                speed -= 0.1;
                if (speed <= 0) {
                    speed = 0.1;
                }
            }

            telemetry.addData("Speed:", speed);
            telemetry.update();

            // player 2

            if (newGamePad2.a.released) {
                intake.apertureOpen();
            }

            if (newGamePad2.b.released) {
                intake.apertureClose();
            }

//            if (newGamePad2.dpad_up.released) {
//                stateSlide++;
//                stateThere = false;
//            }
//
//            if (newGamePad2.dpad_down.released) {
//                stateSlide--;
//                stateThere = false;
//            }
//
//            if (newGamePad2.dpad_up.released) {
//                stateSlide++;
//                if (stateSlide > 3) {
//                    stateSlide = 3;
//                }
//                stateThere = false;
//            }

//            if (newGamePad2.dpad_down.released) {
//                if (stateSlide == 3 && (ArmState == 0 || ArmState == 1)) {
//                    stateSlide--;
//                }
//                if (stateSlide < 0) {
//                    stateSlide = 0;
//                }
//                stateThere = false;
//            }
//
//            if (stateThere == false) {
//                if (stateSlide == 0) {
//                    intake.slide0();
//                    stateThere = true;
//                } else if (stateSlide == 1) {
//                    intake.slide1();
//                    stateThere = true;
//                } else if (stateSlide == 2) {
//                    intake.slide2();
//                    stateThere = true;
//                } else if (stateSlide == 3) {
//                    intake.slide3();
//                    stateThere = true;
//                }
//            }
//
            if (gamepad2.left_bumper || gamepad2.right_bumper || gamepad2.a
                    || gamepad2.b || gamepad2.dpad_left || gamepad2.dpad_right) {
                manual = 0;
            } else if (newGamePad2.x.released) {
                if (!state1Finished) {
                    manual = 1;
                } else if(!state2Finished && state1Finished) {
                    manual = 2;
                }else if (state2Finished && !state3Finished && state1Finished) {
                    manual = 3;
                } else if(state3Finished && state2Finished && state1Finished && !state4Finished) {
                    manual = 4;
                }
            }

            if(manual == 3 && state3Finished && !state4Finished) {
                if(newGamePad2.y.released) {
                    intake.slide2();
                    intake.slideMaintain();
                }
            }

//
            if (newGamePad2.right_trigger.released) {
                state1Finished = false;
                state2Finished = false;
                state3Finished = false;
                state4Finished = false;
                manual = 1;
            }
//
            if (stateSlide > 0) {
                intake.slideMaintain();
            }

            if(newGamePad2.left_bumper.released) {
                intake.plane();
            }

            //armState0: safe lifted position
            //armState1: horizontal grabbing position
            //armState2: scoring position

//                if (newGamePad2.dpad_right.released) {
//                    if ((stateSlide == 2 || stateSlide == 3 || ArmState == 0)) {
//                        ArmState++;
//                    }
//                    if (ArmState >= 2) {
//                        ArmState = 2;
//                    }
//                } else if (newGamePad2.dpad_left.released) {
//                    ArmState--;
//                    if (ArmState <= 0) {
//                        ArmState = 0;
//                    }
//                }
//
//                if (ArmState == 0) {
//                    intake.armLift();
//                } else if (ArmState == 1) {
//                    intake.armGrab();
//                } else if (ArmState == 2) {
//                    intake.armScore();
//                }
//
//                telemetry.addData("Arm position: ", ArmState);
//                telemetry.addData("arm hieght left", intake.LeftSlide.getCurrentPosition());
//                telemetry.addData("arm hieght right", intake.RightSlide.getCurrentPosition());
//
//                telemetry.update();
//
            if (newGamePad2.left_trigger.released) {
                if (!hung) {
                    intake.armLift();
                    ArmState = 1;
                    intake.slideHang();
                    stateSlide = 1;
                    hung = true;
                } else {
                    intake.slide0();

                    while (!matchFinished) {
                        intake.RightSlide.setPower(.25);
                        intake.LeftSlide.setPower(.25);
                        sleep(3000);
                        intake.RightSlide.setPower(0.1);
                        intake.LeftSlide.setPower(0.1);
                        matchFinished = true;
                    }
                    hung = false;
                }
            }

            switch (manual) {
                //manual control
                case 0:
                    if (newGamePad2.right_bumper.released) {
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
                        ArmState++;
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

                    break;

                case 1:
                    //prepare to grab

                    if (!state1Finished) {
                        state2Finished = false;
                        state3Finished = false;
                        state4Finished = false;
                        intake.apertureClose();
                        intake.mandOpen();
                        isMandOpen = true;
                        intake.armLift();
                        sleep(200);
                        ArmState = 0;
                        intake.slide0();
                        state1Finished = true;
                    } else {
                        manual = 0;
                    }
                    break;

                case 2:
                    if(state1Finished && !state2Finished) {
                        state3Finished = false;
                        state4Finished = false;
                        intake.mandClose();
                        sleep(450);
                        Drive.forwardDistance(.5, 2);
                        intake.armScore();
                        sleep(275);
                        intake.apertureOpen();
                        sleep(250);
                        intake.armGrab();
                        sleep(150);
                        intake.mandOpen();
                        sleep(150);
                        intake.armLift();
                        ArmState = 0;
                        sleep(150);
                        intake.mandClose();
                        isMandOpen = false;
                        state2Finished = true;
                    }
                    break;

                case 3:
                    if(!state3Finished && state2Finished && state1Finished) {
                        state4Finished = false;
                        intake.slide1();
                        intake.slideMaintain();
                        intake.armScore();
                        ArmState = 2;
                        state3Finished = true;
                    }
                    break;

                case 4:
                    if(state1Finished && state2Finished && state3Finished && !state4Finished) {
                        intake.apertureClose();
                        sleep(150);
                        intake.armLift();
                        sleep(350);
                        intake.slide0();
                        state4Finished = true;
                        state1Finished = false;
                    }
            }

//                    case 4:
//                        if (!state4Finished) {
//                            intake.apertureClose();
//                            sleep(250);
////                        Drive.forwardDistance(.5, 1);
//                            Drive.forward(.5);
//                            sleep(500);
//                            Drive.stop();
//                            sleep(250);
//                            intake.slideMaintain();
//                            intake.armLift();
//                            intake.slide0();
//                            sleep(250);
//                            stateSlide = 0;
//                            state4Finished = true;
//                            state1Finished = false;
//                            reversed = false;
//                        }*/
//                    default:
//                        manual = 0;
//                        break;
//                }
//
//                //mandible (the thing that brings in the pixels)
//
//                telemetry.addData("Arm position: ", ArmState);
//                telemetry.addData("slide height left", intake.LeftSlide.getCurrentPosition());
//                telemetry.addData("slide height right", intake.RightSlide.getCurrentPosition());
//                telemetry.addData("State: ", manual);
//                telemetry.addData("MandOpen ", isMandOpen);
//                telemetry.addData("Reversed", reversed);
//                telemetry.update();
//            }
        }
    }
}