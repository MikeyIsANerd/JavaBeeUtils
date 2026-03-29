package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import JavaBee.Robot;
import JavaBee.MecanumDrivetrain;
import Javabee.Hardware;
import Javabee.Subsystem.Shooter;
import Javabee.Subsystem.ServoTransfer;

@TeleOp(name="Test Teleop", group="Linear Opmode")
public class TestTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // If you're using a regular Mecanum drivetrain
        MecanumDrivetrain drivetrain = new MaecanumDrivetrain(hardwareMap)
                .setFrontLeftMotor("frontLeft")
                .setFrontRightMotor("frontRight")
                .setBackLeftMotor("backLeft")
                .setBackRightMotor("backRight")
                .setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Robot robot = new Robot(hardwareMap)
                .setDrivetrain(drivetrain);
                .addHardware(Hardware.MOTOR, "chipper")
                .addHardware(Hardware.SERVO, "transfer_servo")
                .addHardware(Hardware.PINPOINT, "pinpoint")
                .addHardware(Hardware.COLOR_SENSOR, "color_sensor")
                .registerSubsystem("shooter", new Shooter("chipper"));

        // If you were using Pedro, you would do this
        Robot robot = new Robot(hardwareMap)
                .usePedro(Constants.createFollower())
                .setStartPose(Constants.START_POSE);

        waitForStart();
        while (opModeIsActive()) {
            // code ig
        }
    }
}
