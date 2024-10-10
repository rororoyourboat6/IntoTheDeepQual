package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Sensors {

    /** Hardware/Constants */

    private  DistanceSensor backDistanceSensor;
    private ColorSensor intakeFrontColor;

    
    public enum SAMPLE_COLOR {
        YELLOW,
        RED,
        BLUE,
        UNKNOWN
    }

    public Sensors(HardwareMap hardwareMap) {
        // Distance Sensor facing the front of the robot
        backDistanceSensor = hardwareMap.get(DistanceSensor.class, "backDistanceSensor");
        // Color sensor looking in the Intake Box
        intakeFrontColor = hardwareMap.get(ColorSensor.class, "intakeColor");
    }
    

    /** Sensor Functions */

    public double getIntakeFrontAlpha() {
        return intakeFrontColor.alpha();
    }
    
    public double getIntakeFrontArgb() {
        return intakeFrontColor.argb();
    }

    public double getIntakeFrontRed() {
        return intakeFrontColor.red();
    }

    public double getIntakeFrontGreen() {
        return intakeFrontColor.green();
    }

//    public SAMPLE_COLOR getIntakeColor() {
//        SAMPLE_COLOR color = SAMPLE_COLOR.WHITE;
//
//        return color;
//    }
//

    

    public double getBackDistance() {
        return backDistanceSensor.getDistance(DistanceUnit.CM);
    }


}