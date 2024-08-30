package org.firstinspires.ftc.teamcode.Training;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PidClass {


    double Kp = 0;
    double Ki = 0;
    double Kd = 0;

    double reference = 0;

    double integralSum = 0;
    double lastError = 0;

//    ElapsedTime = new ElapsedTime();
}