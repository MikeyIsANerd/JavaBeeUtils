package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;

import JavaBee.Robot;
import JavaBee.MecanumDrivetrain;
import Javabee.Hardware;
import Javabee.Subsystem.Shooter;
import Javabee.Subsystem.ServoTransfer;

/*
 * Turn off syntax highlighting for this file in the top right of your editor.
 * This is a "vision" of what we want JavaBee to look like, not all of these methods exist yet.
 */
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

        // If you were using Pedro, you would do this (Pedro will be integreated as a drivetrain)
        PedroDrivetrain drivetrain = new PedroDrivetrain(Constants.createFollower(hardwareMap));

        // Flywheel motor example
        Hardware.Motor launcher_left = new Hardware.Motor("launcher_left", hardwareMap)
                .setDirection(Hardware.Motor.Direction.REVERSE);
                .setZeroPowerMode(Hardware.Motor.ZeroPowerMode.BRAKE)
                .setRunMode(Hardware.Motor.RunMode.VELOCITY); // POWER, POSITION, VELOCITY

        Hardware.Motor launcher_right = new Hardware.Motor("launcher_right", hardwareMap)
                .setDirection(Hardware.Motor.Direction.FORWARD);
                .setZeroPowerMode(Hardware.Motor.ZeroPowerMode.BRAKE)
                .setRunMode(Hardware.Motor.RunMode.VELOCITY); // POWER, POSITION, VELOCITY

        // Both motors will be controlled together when they are linked
        Hardware.LinkedMotors launcher = new Hardware.LinkedMotors(launcher_left, launcher_right);

        Hardware.Servo transferServo = new Hardware.Servo("transfer_servo", hardwareMap)
                .setType(Hardware.Servo.Type.STANDARD) // STANDARD, CONTINUOUS_ROTATION
                .setIdleMode(Hardware.Servo.IdleMode.BRAKE) // BRAKE, FLOAT
                .setInitialPosition(0.5);

        // NOTE: This wouldn't have to be done for Pedro users
        Hardware.PinpointOdometry pinpoint = new Hardware.PinpointOdometry("pinpoint", hardwareMap)
                .setDistanceUnit(Hardware.PinpointOdometry.DistanceUnit.INCH)
                .setAngleUnit(Hardware.PinpointOdometry.AngleUnit.DEGREES)
                .setXPodOffset(0)
                .setYPodOffset(0)
                .setPods(Hardware.PinpointOdometry.Pods.GOBILDA_4_BAR) // or .setCustomPodResolution(double)
                .xPodDirection(Hardware.PinpointOdometry.Direction.FORWARD) // FORWARD, REVERSE
                .yPodDirection(Hardware.PinpointOdometry.Direction.FORWARD) // FORWARD, REVERSE
                .setStartPose(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));

        Robot robot = new Robot(hardwareMap)
                .setBulkReads(Robot.BulkReadMode.MANUAL) // AUTO, MANUAL, NONE
                .setDrivetrain(drivetrain); // Works for both Pedro and regular Mecanum
                .addHardware(launcher) // Hardware.LinkedMotor
                .addHardware(Hardware.SERVO, "transfer_servo")
                .addHardware(Hardware.PINPOINT, "pinpoint")
                .addHardware(Hardware.COLOR_SENSOR, "color_sensor")
                .registerSubsystem("shooter", new Shooter("chipper"));

        waitForStart();
        while (opModeIsActive()) {
            // code ig
        }
    }
}
