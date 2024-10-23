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
    private static ColorSensor intakeFrontColor;
    private static DistanceSensor intakeFrontDistance;


    public enum SAMPLE_COLOR {
        YELLOW,
        RED,
        BLUE,
        UNKNOWN
    }

    private SAMPLE_COLOR color;

    public Sensors(HardwareMap hardwareMap) {
        // Distance Sensor facing the front of the robot
//        backDistanceSensor = hardwareMap.get(DistanceSensor.class, "backDistanceSensor");
        // Color sensor looking in the Intake Box
        intakeFrontColor = hardwareMap.get(ColorSensor.class, "intakeColor");
        intakeFrontDistance = hardwareMap.get(DistanceSensor.class, "intakeColor");
    }

    public void init() {
        color = SAMPLE_COLOR.UNKNOWN;
    }


    /** Sensor Functions */

    public static double getIntakeFrontAlpha() {
        return intakeFrontColor.alpha();
    }
    
    public static double getIntakeFrontArgb() {
        return intakeFrontColor.argb();
    }

    public static double getIntakeFrontRed() {
        return intakeFrontColor.red();
    }

    public static double getIntakeFrontGreen() {
        return intakeFrontColor.green();
    }

    public static double getIntakeFrontBlue(){
        return intakeFrontColor.blue();
    }

    public static double getDistanceColor(){
        return intakeFrontDistance.getDistance(DistanceUnit.CM);
    }

//jank but it distinguished blue and red! just not yellow or unknown buttttttttt
    public static SAMPLE_COLOR getIntakeColor() {
        SAMPLE_COLOR color;

        if (getDistanceColor() > 5){
            color = SAMPLE_COLOR.UNKNOWN;
        }
        else{
            if (getIntakeFrontBlue() > getIntakeFrontRed()) {
                color = SAMPLE_COLOR.BLUE;
            }
            else if (getIntakeFrontRed() > getIntakeFrontBlue()){
                if(getIntakeFrontGreen() > 600) {
                    color = SAMPLE_COLOR.RED;
                }
                else {
                    color = SAMPLE_COLOR.YELLOW;
                }
            }
            else{
                color = SAMPLE_COLOR.UNKNOWN;
            }
        }

        return color;
    }
//

    

//    public double getBackDistance() {
//        return backDistanceSensor.getDistance(DistanceUnit.CM);
//    }
//

}