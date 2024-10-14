package org.firstinspires.ftc.teamcode.drive;

import static java.lang.Thread.sleep;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.util.Subsystem;

import java.util.concurrent.TimeUnit;

@Config
public class Outtake extends Subsystem {

    /** Hardware/Constants */
    private DcMotorEx slides;
    private Servo dump, specimenClaw;
    public static double allowableError = 0;
    public static double restPow = 0.05, maxPow = 0.9;

    public static int ground = 0;
    public static int low = 15000;
    public static int high = 40000;
    public static double slidesPosPow = 1;
    public static double slidesNegPow = -0.6;

//    public static double slidesEncoderSlip = 0.0;

    public static double dumpOutPos = 0, dumpInPos = 0, dumpInitPos = 0;
    public static double specimenClawInitPos = 0;
    public static double specimenClawIntakePos = 0;
    public static double specimenClawOuttakePos = 0;

    public enum DepositLevel {
        GROUND,
        LOW,
        HIGH
    }

    public enum DumpPos {
        IN,
        OUT,
        INIT
    }
    public enum SpecimenClawPos {
        INIT,
        INTAKE,
        OUTTAKE
    }

    public Outtake(HardwareMap hardwareMap, Sensors sensor) {
        slides = hardwareMap.get(DcMotorEx.class, "slides");

        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        dump = hardwareMap.get(Servo.class, "dump");
        specimenClaw = hardwareMap.get(Servo.class, "specimenClaw");
    }

    /** Base Functions */
    @Override
    public void init() {
        setDump(DumpPos.INIT);
        setSpecimenClaw(SpecimenClawPos.INIT);
    }

    public void stop() {
    }

    /** Mechanism Functions */
    public void setSlidesPow(double pow) {
        slides.setPower(pow);
    }
    public double getSlidesCurrent() {
        return this.slides.getCurrent(CurrentUnit.MILLIAMPS);
    }

    public void setDump(DumpPos pos) {
        switch (pos) {
            case INIT:
                dump.setPosition(dumpInitPos);
                break;
            case OUT:
                dump.setPosition(dumpOutPos);
                break;
            case IN:
                dump.setPosition(dumpInPos);
                break;
        }
    }

    public void setSpecimenClaw(SpecimenClawPos pos) {
        switch (pos) {
            case INIT:
                specimenClaw.setPosition(specimenClawInitPos);
                break;
            case INTAKE:
                specimenClaw.setPosition(specimenClawIntakePos);
                break;
            case OUTTAKE:
                specimenClaw.setPosition(specimenClawOuttakePos);
                break;
        }
    }
}
