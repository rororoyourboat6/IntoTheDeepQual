package org.firstinspires.ftc.teamcode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.drive.Sensors;

@Config
@TeleOp(group = "test")
public class SensorsTest extends LinearOpMode {

    private FtcDashboard dashboard = FtcDashboard.getInstance();
//    public static String pattern = "WHITE";

    @Override
    public void runOpMode() throws InterruptedException {
       // MecanumDrive usha = new MecanumDrive(hardwareMap);
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());

//        Sensors sensors = new Sensors(hardwareMap);
        waitForStart();

        //    telemetry.addData("getIntakeColor", Sensors.SAMPLE_COLOR);

           //saying it needs to be static
            telemetry.addData("Argb", Sensors.getIntakeFrontArgb());
            telemetry.addData("Red", Sensors.getIntakeFrontRed());
            telemetry.addData("Green", Sensors.getIntakeFrontGreen());
            telemetry.addData("Alpha", Sensors.getIntakeFrontAlpha());
            telemetry.update();
        }
    }
