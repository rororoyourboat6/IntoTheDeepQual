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
public class Intake extends Subsystem {
// TODO: don't know if this is correct
    private DcMotorEx intake;


    /** Hardware/Constants */
    private Servo armLeft, armRight, linkageLeft, linkageRight, tubing;
    public static double armLeftInitPos = 0, armLeftTransferPos = 0, armLeftIntakePos = 0;
    public static double armRightInitPos = 0, armRightTransferPos = 0, armRightIntakePos = 0;
    public static double linkageLeftInitPos = 0, linkageLeftExtendPos = 0, linkageLeftRetractPos = 0;
    public static double  linkageRightInitPos = 0, linkageRightExtendPos = 0, linkageRightRetractPos = 0;

    public enum ArmPos {
        INIT,
        INTAKE,
        TRANSFER
    }

    public enum LinkagePos {
        INIT,
        EXTEND,
        RETRACT
    }
    public enum TubingDirection {
        IN,
        OUT,
        REST
    }

    public void setPow(double pow) {
        intake.setPower(pow);
    }

    public Intake(HardwareMap hardwareMap, Sensors sensor) {
        armLeft = hardwareMap.get(Servo.class, "armLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");
        linkageLeft = hardwareMap.get(Servo.class, "linkageLeft");
        linkageRight = hardwareMap.get(Servo.class, "linkageRight");
        tubing = hardwareMap.get(Servo.class, "tubing");
    }

    /** Base Functions */
    @Override
    public void init() {
        setArm(ArmPos.INIT);
        setLinkage(LinkagePos.INIT);

    }

    public void stop() {
    }

    /** Mechanism Functions */

    public void setArm(ArmPos pos) {
        switch (pos) {
            case INIT:
                armLeft.setPosition(armLeftInitPos);
                armRight.setPosition(armRightInitPos);
                break;
            case INTAKE:
                armLeft.setPosition(armLeftIntakePos);
                armRight.setPosition(armRightIntakePos);
                break;
            case TRANSFER:
                armLeft.setPosition(armLeftTransferPos);
                armRight.setPosition(armRightTransferPos);
                break;
        }
    }

    public void setLinkage(LinkagePos pos) {
        switch (pos) {
            case INIT:
                linkageLeft.setPosition(linkageLeftInitPos);
                linkageRight.setPosition(linkageRightInitPos);
                break;
            case EXTEND:
                linkageLeft.setPosition(linkageLeftExtendPos);
                linkageRight.setPosition(linkageRightExtendPos);
                break;
            case RETRACT:
                linkageLeft.setPosition(linkageLeftRetractPos);
                linkageRight.setPosition(linkageRightRetractPos);
                break;
        }
    }
    // TODO: I do not know how to use continuous  servos
    public void setTubingDirection(TubingDirection pos) {
        switch (pos) {
            case IN:
//                tubing.setPosition();
                break;
            case OUT:
//                specimenClaw.setPosition(specimenClawIntakePos);
                break;
            case REST:
//                specimenClaw.setPosition(specimenClawOuttakePos);
                break;
        }
    }
}
