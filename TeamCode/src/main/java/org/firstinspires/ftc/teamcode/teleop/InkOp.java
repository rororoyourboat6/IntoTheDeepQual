package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.Usha;

import org.firstinspires.ftc.teamcode.drive.Outtake;
import org.firstinspires.ftc.teamcode.drive.Intake;
import org.firstinspires.ftc.teamcode.drive.Sensors;

@Config
@TeleOp
public class InkOp extends LinearOpMode {


//    enum OuttakeState {
//        IDLE,
//        LOW,
//        MID,
//        HIGH,
//        GROUND_S1,
//        GROUND_S2,
//        RESET_ZERO
//    }
//    enum IntakeState {
//        IDLE,
//        STAGE_1,
//        STAGE_2,
//        STAGE_3,
//        STAGE_4
//    }
//
//    public void runOpMode() throws InterruptedException {
//    }



    //todo: update mapping
    /**
     * BUTTON MAPPING
     * @Gamepad_1 (Chassis, Drone, Hang)
     *     Joysticks         - Drive
     *     A                 - Slow Drive Toggle
     *     Left Trigger      - XXXXXXXXXXXXXXXXXXXX
     *     Left/Right Bumper - XXXXXXXXXXXXXXXXXXXX
     *
     * @Gamepad_2 (Intake, Transfer, Outtake)
     *     DPad Up          - Slides High
     *     DPad Left        - Slides Mid
     *     DPad Right       - Slides Low
     *     DPad Down        - Slides Down
     *     Right Joystick    - Slides Micro Adjustment
     *     Left Joystick   - Intake Manual
     *     L/R Bumper       - Rotate Jonny Toggle
     *     L/R Trigger      - Front/Back Jonny Score
     *     A                - Auto Intake
     *     Y                - Transfer
     *     B                - Raise Intake So Chassis Can Move
     *
     */

    @Config
    @TeleOp
    public class InkOp extends LinearOpMode {
        private Usha usha;
        private Outtake.DepositLevel outtakeLevel;

        public static double driveMultiplierSlow = 0.4, driveMultiplierNormal = 0.8;
        private double driveMultiplier;

        @Override
        public void runOpMode() throws InterruptedException {
            usha = new Usha(hardwareMap);
          //TODO: Figure this out  usha.init();
            outtakeLevel = Outtake.DepositLevel.GROUND;

            Collect collect = new Collect();
            Transfer transfer = new Transfer();
            RaiseSlides raiseSlides = new RaiseSlides();
            Chassis chassis = new Chassis();

            boolean isHangUp = false;
            boolean isJonnyBackExtrude = false, isJonnyFrontExtrude = false;
            boolean slowDrive = false;
            driveMultiplier = driveMultiplierNormal;

            //TODO: Do this for our dump outtake
//            Outtake.JonnyRotatePos[] jonnyRotatePoses = new Outtake.JonnyRotatePos[]{
//                    Outtake.JonnyRotatePos.RIGHT,
//                    Outtake.JonnyRotatePos.VERTICAL,
//                    Outtake.JonnyRotatePos.LEFT,
//                    Outtake.JonnyRotatePos.HORIZONTAL,
//            };
            int jonnyRotatePosIndex = 1;

            waitForStart();

            chassis.start();

            while (!isStopRequested()) {
                /** @Gamepad_1 (Chassis, Drone, Hang) */
                if (gamepad1.a) {
                    // Slow Drive Toggle
                    slowDrive = !slowDrive;
                    driveMultiplier = slowDrive ? driveMultiplierSlow : driveMultiplierNormal;
                    while (gamepad1.a) {}
                }

                if (gamepad1.left_trigger > 0.2) {
                    //TODO: add extend intake function
                    usha.intake.raiseIntake();

                    while (gamepad1.left_trigger > 0.2) {}
                }


                /** @Gamepad_2 (Intake, Transfer, Outtake) */
                if (gamepad2.dpad_up || gamepad2.dpad_left || gamepad2.dpad_right) {
                    babaji.outtake.setOuttakeRotate(Outtake.OuttakeRotatePos.BACKDROP);
                    babaji.outtake.setJonnyRotate(Outtake.JonnyRotatePos.VERTICAL);
                    jonnyRotatePosIndex = 1;
                    babaji.outtake.setJonnyExtrudePos(Outtake.JonnyExtrudePos.COLLECT);
                }
                if (gamepad2.dpad_down) {
                    babaji.outtake.setOuttakeRotate(Outtake.OuttakeRotatePos.SHIELD);
                    babaji.outtake.setJonnyRotate(Outtake.JonnyRotatePos.VERTICAL);
                    jonnyRotatePosIndex = 1;
                }


                if (Math.abs(gamepad2.right_stick_y) > 0.3) {
                    // Slides Micro Adjustment
                    usha.outtake.setSlidesPow(gamepad2.right_stick_y * -1);
                } else {
                    usha.outtake.setSlidesPow(Outtake.restPow);
                }
                //TODO: Replace this
//                if (gamepad2.left_bumper) {
//                    // Rotate Jonny Clockwise Toggle
//                    jonnyRotatePosIndex--;
//                    if (jonnyRotatePosIndex < 0) {
//                        jonnyRotatePosIndex = 0;
//                    }
//                    babaji.outtake.setJonnyRotate(jonnyRotatePoses[jonnyRotatePosIndex]);
//                    while (gamepad2.left_bumper) {}
//                }
//                if (gamepad2.right_bumper) {
//                    // Rotate Jonny Counter-Clockwise Toggle
//                    jonnyRotatePosIndex++;
//                    if (jonnyRotatePosIndex >= jonnyRotatePoses.length) {
//                        jonnyRotatePosIndex = jonnyRotatePoses.length-1;
//                    }
//                    babaji.outtake.setJonnyRotate(jonnyRotatePoses[jonnyRotatePosIndex]);
//                    while (gamepad2.right_bumper) {}
//                }

                if (gamepad2.left_trigger > 0.2 && gamepad2.right_trigger > 0.2) {
                    // Front Jonny Score
                    isJonnyFrontExtrude = !isJonnyFrontExtrude;
                    isJonnyBackExtrude = !isJonnyBackExtrude;
                    if (isJonnyFrontExtrude) {
                        babaji.outtake.setJonnyFrontExtrudePos(Outtake.JonnyExtrudePos.SCORE);
                    } else {
                        babaji.outtake.setJonnyFrontExtrudePos(Outtake.JonnyExtrudePos.COLLECT);
                    }
                    if (isJonnyBackExtrude) {
                        babaji.outtake.setJonnyBackExtrudePos(Outtake.JonnyExtrudePos.SCORE);
                    } else {
                        babaji.outtake.setJonnyBackExtrudePos(Outtake.JonnyExtrudePos.COLLECT);
                    }
                    while (gamepad2.left_trigger > 0.2 || gamepad2.right_trigger > 0.2) {}
                } else if (gamepad2.left_trigger > 0.2) {
                    // Front Jonny Score
                    isJonnyFrontExtrude = !isJonnyFrontExtrude;
                    if (isJonnyFrontExtrude) {
                        babaji.outtake.setJonnyFrontExtrudePos(Outtake.JonnyExtrudePos.SCORE);
                    } else {
                        babaji.outtake.setJonnyFrontExtrudePos(Outtake.JonnyExtrudePos.COLLECT);
                    }
                    while (gamepad2.left_trigger > 0.2) {}
                } else if (gamepad2.right_trigger > 0.2) {
                    // Back Jonny Score
                    isJonnyBackExtrude = !isJonnyBackExtrude;
                    if (isJonnyBackExtrude) {
                        babaji.outtake.setJonnyBackExtrudePos(Outtake.JonnyExtrudePos.SCORE);
                    } else {
                        babaji.outtake.setJonnyBackExtrudePos(Outtake.JonnyExtrudePos.COLLECT);
                    }
                    while (gamepad2.right_trigger > 0.2) {}
                }


                if (gamepad2.a) {
                    // Auto Ground Intake
                    babaji.intake.intakePixel1();
//                collect.start();
//                while (gamepad2.a) {}
//                sleep(500);
                }
//            if (collect.isAlive() && Math.abs(gamepad2.left_stick_y) > 0.5) {
//                collect.interrupt();
//                babaji.intake.setPow(0);
//            }

                usha.intake.setPow(gamepad2.left_stick_y);

                if (gamepad2.y && !transfer.isAlive()) {
                    // Transfer
                    usha.intake.setPow(0.0);
               //TODO: add raise intake moving
                    //     babaji.intake.raiseIntakeMoving();
                    transfer.start();
                }
                if (gamepad2.b) {
                    //TODO: add raise intake moving
//                    babaji.intake.raiseIntakeMoving();

//                while (gamepad2.b) {}
                    sleep(500);
                }

                telemetry.addData("Slow Drive", slowDrive);
                telemetry.addData("Slides Enc Count", babaji.outtake.getEncCount());
                telemetry.update();
            }

            chassis.interrupt();
            usha.setMotorPowers(0,0,0,0);
            usha.stop();
        }

        private class Collect extends Thread {
            @Override
            public void run() {
                try {
                    babaji.collectGroundMoving();
                }
                catch (InterruptedException e) {}
                this.interrupt();
//            babaji.intake.setPow(0.0);
            }
        }

        private class Transfer extends Thread {
            @Override
            public void run() {
                try {
                    babaji.transfer();
                } catch (InterruptedException e) {}
                this.interrupt();
            }
        }

        private class RaiseSlides extends Thread {
            @Override
            public void run() {
                try {
                    babaji.outtake.raiseToHeight(outtakeLevel);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                this.interrupt();
                babaji.outtake.setSlidesPow(Outtake.restPow);
            }
        }

        private class Chassis extends Thread {
            @Override
            public void run() {
                try {
                    while (opModeIsActive()) {
                        usha.setWeightedDrivePower(
                                new Pose2d(
                                        -gamepad1.left_stick_y * driveMultiplier,
                                        -gamepad1.left_stick_x * driveMultiplier,
                                        -gamepad1.right_stick_x * driveMultiplier
                                )
                        );
                        usha.update();
                    }
                } catch (Exception e) {
                }

                this.interrupt();
            }
        }
    }
}
