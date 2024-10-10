package org.firstinspires.ftc.teamcode.drive;

import static java.lang.Thread.sleep;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

@Config
//TODO:
public class Intake {

    /** Hardware/Constants */
    private DcMotorEx intake;
    private Servo rightRotate, leftRotate;
    private Sensors sensor;

    public static double collectPow = 0.8, spitOutPow = -1, alignPow = 0.4;
    public static double rotateRightUpPos = 0.6, rotateLeftUpPos = 0.32;
    public static double rotateRightDownPos = 0.05, rotateLeftDownPos = 0.87;
    public static double pixel5PosRight = 0.21; public static double pixel5PosLeft = 0.69;
    public static double pixel4PosRight = 0.17; public static double pixel4PosLeft = 0.74;
    public static double pixel3PosRight = 0.13; public static double pixel3PosLeft = 0.78;
    public static double pixel2PosRight = 0.09; public static double pixel2PosLeft = 0.82;
    public static double pixel1PosRight = 0.04; public static double pixel1PosLeft = 0.87;
    public static double rotateRightIntakeMovingPos = 0.5; public static double rotateLeftIntakeMovingPos = 0.42;

    public static double pursuitDistance = 5;
    public static double backUpDist = 16, backUpDist2 = 15;
    public static double closeUpDist = 12.3;
    public static double closeUpDist2 = 12;
    public static double closeUpDist3 = 12;
    public static double wiggleFront = 18;
    public static double intakePursuitPow = 0.2;
    public static double armDropTime = 0.35;
    public static double stackWaitTime = 0.3;

    public Intake(HardwareMap hardwareMap, Sensors sensor) {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        rightRotate = hardwareMap.get(Servo.class, "intakeRightRotate");
        leftRotate = hardwareMap.get(Servo.class, "intakeLeftRotate");

        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        this.sensor = sensor;
    }




    /** Mechanism Functions */
//    public void collectTopPixelsAuto(Babaji babaji) throws InterruptedException {
//        babaji.setMotorPowers(0.0, 0.0, 0.0, 0.0);
//        ElapsedTime timer = new ElapsedTime();
//        setPow(collectPow);
//        intakePixel5();
//        timer.reset();
//        while (!sensor.intakeBackHasPixel()) {
//            if (timer.time(TimeUnit.SECONDS) > 2.5) {
//                break;
//            }
//        }
//        sleep(1000);
//        intakePixel4();
//        timer.reset();
//        while (!sensor.intakeFrontHasPixel()) {
//            if (timer.time(TimeUnit.SECONDS) > 2.5) {
//                intakePixel3();
//            } else if (timer.time(TimeUnit.SECONDS) > 5) {
//                break;
//            }
//        }
//        sleep(1000);
//        setPow(0);
//    }

    public void incrementRotate(double increment) {
        rightRotate.setPosition(rotateRightUpPos + increment);
        leftRotate.setPosition(rotateLeftUpPos - increment);
    }

//    public void collectPixels() throws InterruptedException {
//        ElapsedTime timer = new ElapsedTime();
//        boolean cont = true;
//        setPow(collectPow);
//        intakePixel1();
//        while (!sensor.intakeBackHasPixel()) {
//            if (timer.time(TimeUnit.SECONDS) > 2) {
//                setPow(0);
//                cont = false;
//                break;
//            }
//        }
//        sleep(1000);
//        timer.reset();
//        if (cont) {
//            while (!sensor.intakeFrontHasPixel()) {
//                if (timer.time(TimeUnit.SECONDS) > 2) {
//                    setPow(0);
//                    break;
//                }
//            }
//        }
////        setPow(collectPow * 1.2);
////        sleep(1000);
//        setPow(0);
//    }
//
////    public void collectPixels() throws InterruptedException {
////        boolean hasTwoPixels = false;
////
////        setPow(collectPow);
////        while (!hasTwoPixels) {
////            if (sensor.intakeBackHasPixel()) {
////                sleep(500);
////                setPow(collectPow * 0.75);
////                if (sensor.intakeFrontHasPixel()) {
////                    hasTwoPixels = true;
////                }
////            } else {
////                hasTwoPixels = false;
////            }
////        }
////        setPow(collectPow * 0.5);
////        sleep(500);
////        setPow(spitOutPow);
////        sleep(1000);
////        setPow(0);
////    }

    public void setPow(double pow) {
        intake.setPower(pow);
    }

    public void raiseIntake() {
        rightRotate.setPosition(rotateRightUpPos);
        leftRotate.setPosition(rotateLeftUpPos);
    }

    public void raiseIntakeMoving() {
        rightRotate.setPosition(rotateRightIntakeMovingPos);
        leftRotate.setPosition(rotateLeftIntakeMovingPos);
    }

    public void intakePixel5() {
        rightRotate.setPosition(pixel5PosRight);
        leftRotate.setPosition(pixel5PosLeft);
    }

    public void intakePixel4() {
        rightRotate.setPosition(pixel4PosRight);
        leftRotate.setPosition(pixel4PosLeft);
    }

    public void intakePixel3() {
        rightRotate.setPosition(pixel3PosRight);
        leftRotate.setPosition(pixel3PosLeft);
    }

    public void intakePixel2() {
        rightRotate.setPosition(pixel2PosRight);
        leftRotate.setPosition(pixel2PosLeft);
    }

    public void intakePixel1() {
        rightRotate.setPosition(pixel1PosRight);
        leftRotate.setPosition(pixel1PosLeft);
    }

    public void lowerIntake() {
        rightRotate.setPosition(rotateRightDownPos);
        leftRotate.setPosition(rotateLeftDownPos);
    }
}