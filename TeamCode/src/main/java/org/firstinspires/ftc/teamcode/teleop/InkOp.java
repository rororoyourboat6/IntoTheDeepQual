package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp
public class InkOp extends LinearOpMode {


    enum OuttakeState {
        IDLE,
        LOW,
        MID,
        HIGH,
        GROUND_S1,
        GROUND_S2,
        RESET_ZERO
    }
    enum IntakeState {
        IDLE,
        STAGE_1,
        STAGE_2,
        STAGE_3,
        STAGE_4
    }

    public void runOpMode() throws InterruptedException {
    }
}
