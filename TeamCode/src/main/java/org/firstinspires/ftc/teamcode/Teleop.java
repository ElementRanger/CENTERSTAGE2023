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
        Test test = new Test();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        Drive.init(this);
        intake.init(this);
        test.init(this);

        intake.apertureOpen();


        waitForStart();

        telemetry.addData("Speed:", speed);

        int CYCLE_MS = 50;     // period of each cycle


        while (opModeIsActive()) {
            newGamePad1.updateState();
            newGamePad2.updateState();

            // player 1
            if (!reversed) {
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
            } else if (reversed) {
                if (newGamePad1.left_bumper.released) {
                    speed += 0.1;
                    telemetry.addData("Speed:", speed);
                    if (speed >= 1.0) {
                        speed = 1.0;
                    }
                }

                if (newGamePad1.right_bumper.released) {
                    speed -= .1;
                    telemetry.addData("Speed:", speed);
                    if (speed <= 0) {
                        speed = 0.1;
                    }
                }
            }

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
                    Drive.forward(-speed);
                } else if (gamepad1.left_stick_y > 0.4) {
                    Drive.backward(-speed);
                } else if (gamepad1.left_stick_x > 0.4) {
                    Drive.strafeRight(-speed);
                } else if (gamepad1.left_stick_x < -0.4) {
                    Drive.strafeLeft(-speed);
                } else if (gamepad1.right_stick_x > 0.4) {
                    Drive.turnLeft(-speed);
                } else if (gamepad1.right_stick_x < -0.4) {
                    Drive.turnRight(-speed);
                } else {
                    Drive.stop();
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
                        if (stateSlide == 3 || ArmState == 0 || ArmState == 1) {
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

                    if (gamepad2.left_bumper || gamepad2.right_bumper || gamepad2.a
                            || gamepad2.b || gamepad2.dpad_left || gamepad2.dpad_right) {
                        manual = 0;
                    } else if (newGamePad2.x.released) {
                        if (!state1Finished) {
                            manual = 1;
                            state1Finished = false;
                        } else if (state1Finished) {
                            manual = 3;
                            state3Finished = false;
                        }
                    }
                    if (newGamePad2.y.released) {
                        if (!state2Finished) {
                            manual = 2;
                            state2Finished = false;
                        } else if (state2Finished) {
                            manual = 4;
                            state4Finished = false;
                        }
                    }

                    if (newGamePad2.right_trigger.released) {
                        manual = 1;
                        //Drive.resetEncoders();
                        state1Finished = false;
                        state3Finished = true;
                    }

                    if (stateSlide > 0) {
                        intake.slideMaintain();

                        //armState0: safe lifted position
                        //armState1: horizontal grabbing position
                        //armState2: scoring position

                        if (newGamePad2.dpad_right.released) {
                            if ((stateSlide == 2 || stateSlide == 3 || ArmState == 0)) {
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


                        if (newGamePad1.b.released) {
                            if (reversed) {
                                reversed = false;
                            } else if (!reversed) {
                                reversed = true;
                            }
                        }

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
                            case 0:     //manual

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


                                if (gamepad2.right_stick_y < -.4) {
                                    intake.slideUp();
                                } else if (gamepad2.right_stick_y > .4) {
                                    intake.slideDown();
                                } else {
                                    intake.slideStop();
                                }

                                break;
                            case 1:
                                if (!state1Finished) {
                                    intake.apertureClose();
                                    intake.mandOpen();
                                    isMandOpen = true;
                                    intake.armLift();
                                    ArmState = 0;
                                    intake.slide0();
                                    state1Finished = true;
                                    state2Finished = false;
                                }
                                break;

                            case 2:
                                if (!state2Finished) {
                                    intake.mandClose();
                                    sleep(450);
//                        Drive.forwardDistance(.5, 2);
                                    Drive.forward(.5);
                                    sleep(500);
                                    Drive.stop();
                                    intake.armScore();
                                    sleep(250);
                                    intake.armGrab();
                                    intake.apertureOpen();
                                    intake.mandOpen();
                                    sleep(250);
                                    intake.armLift();
                                    ArmState = 0;
                                    sleep(250);
                                    intake.mandClose();
                                    isMandOpen = false;
                                    state2Finished = true;
                                    state3Finished = false;
                                    reversed = true;
                                }
                                if (newGamePad2.x.released) {
                                    manual = 3;
                                }
                                break;
                            case 3:
                                if (!state3Finished) {
                                    intake.slide1();
                                    stateSlide = 1;
                                    intake.slideMaintain();
                                    sleep(250);
                                    intake.armScore();
                                    state3Finished = true;
                                    state4Finished = false;
                                }
                                break;
                            case 4:
                                if (!state4Finished) {
                                    intake.apertureClose();
                                    sleep(250);
//                        Drive.forwardDistance(.5, 1);
                                    Drive.forward(.5);
                                    sleep(500);
                                    Drive.stop();
                                    sleep(250);
                                    intake.slideMaintain();
                                    intake.armLift();
                                    intake.slide0();
                                    sleep(250);
                                    stateSlide = 0;
                                    state4Finished = true;
                                    state1Finished = false;
                                    reversed = false;
                                }
                            default:
//                    manual = 0;
                                break;
                        }

                        //mandible (the thing that brings in the pixels)

                        telemetry.addData("Arm position: ", ArmState);
                        telemetry.addData("slide height left", intake.LeftSlide.getCurrentPosition());
                        telemetry.addData("slide height right", intake.RightSlide.getCurrentPosition());
                        telemetry.addData("State: ", manual);
                        telemetry.addData("MandOpen ", isMandOpen);
                        telemetry.addData("Reversed", reversed);
                        telemetry.update();
                    }
                }
            }
        }
    }
}