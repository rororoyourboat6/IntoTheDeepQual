package org.firstinspires.ftc.teamcode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.drive.Sensors;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
@TeleOp(group = "test")
public class SensorsTest extends LinearOpMode {

    private FtcDashboard dashboard = FtcDashboard.getInstance();
//    public static String pattern = "WHITE";
//    HardwareMap hardwareMap;
//    ColorSensor intakeFrontColor = hardwareMap.get(ColorSensor.class, "intakeColor");
//    DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "distance");
    @Override
    public void runOpMode() throws InterruptedException {
       // MecanumDrive usha = new MecanumDrive(hardwareMap);
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());

        Sensors sensors = new Sensors(hardwareMap);
        waitForStart();

        while (!isStopRequested()) {
            telemetry.addData("Argb", Sensors.getIntakeFrontArgb());
//        telemetry.addData("distance", Sensors.getDistance(DistanceUnit.MM));
            telemetry.addData("Red", Sensors.getIntakeFrontRed());
            telemetry.addData("Green", Sensors.getIntakeFrontGreen());
            telemetry.addData("Alpha", Sensors.getIntakeFrontAlpha());
            telemetry.addData("Blue", Sensors.getIntakeFrontBlue());
            telemetry.update();
        }
    }
    }
