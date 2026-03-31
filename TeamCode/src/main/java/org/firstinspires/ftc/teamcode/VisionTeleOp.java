package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import JavaBee.Robot;
import JavaBee.MecanumDrivetrain;
import Javabee.hardware;
import Javabee.utils.controllers.PVSController;

/**
 * Turn off syntax highlighting for this file in the top right of your editor.
 * This is a "vision" of what we want JavaBee to look like, not all of these methods exist yet.
 * @author Dylan B. - 18597 RoboClovers Delta
 */
@TeleOp(name="Vision Teleop", group="Linear Opmode")
public class VisionTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // NOTE: This wouldn't have to be done for Pedro users
        hardware.localizers.Pinpoint pinpoint = new hardware.Localizer.Pinpoint("pinpoint", hardwareMap)
                .setDistanceUnit(DistanceUnit.INCH)
                .setAngleUnit(AngleUnit.DEGREES)
                .setXPodOffset(0)
                .setYPodOffset(0)
                .setPods(Hardware.Localizer.Pinpoint.Pods.GOBILDA_4_BAR) // or .setCustomPodResolution(double)
                .xPodDirection(Hardware.Localizer.Pinpoint.Direction.FORWARD) // FORWARD, REVERSE
                .yPodDirection(Hardware.Localizer.Pinpoint.Direction.FORWARD) // FORWARD, REVERSE
                .setStartPose(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));

        // If you're using a regular Mecanum drivetrain
        MecanumDrivetrain drivetrain = new MaecanumDrivetrain(hardwareMap)
                .setFrontLeftMotor("frontLeft")
                .setFrontRightMotor("frontRight")
                .setBackLeftMotor("backLeft")
                .setBackRightMotor("backRight")
                .addLocalizer(pinpoint)
                .setMotorZeroPowerBehavior(Hardware.Motor.ZeroPowerMode.BRAKE);

        // If you were using Pedro, you would do this (Pedro will be integreated as a drivetrain)
        PedroDrivetrain drivetrain = new PedroDrivetrain(Constants.createFollower(hardwareMap));

        // Flywheel motors with PVS controller
        PVSController launcherController = new PVSController(0.1, 0.01, 0.05) // (P, V, S)
                .setSetpoint(1000) // Target velocity in ticks per second (this is changeable on the fly)
                .setTolerance(50) // Acceptable error in ticks per second (.atTarget() will return true if the error is within this range)
                .setOutputRange(-1, 1);
        Hardware.Motor launcher_left = new Hardware.Motor("launcher_left", hardwareMap)
                .setDirection(Hardware.Motor.Direction.REVERSE)
                .setZeroPowerMode(Hardware.Motor.ZeroPowerMode.BRAKE)
                .setRunMode(Hardware.Motor.RunMode.VELOCITY) // POWER, POSITION, VELOCITY
                .setController(launcherController); // Set the PVS controller for this motor
        Hardware.Motor launcher_right = new Hardware.Motor("launcher_right", hardwareMap)
                .setDirection(Hardware.Motor.Direction.FORWARD)
                .setZeroPowerMode(Hardware.Motor.ZeroPowerMode.BRAKE)
                .setRunMode(Hardware.Motor.RunMode.VELOCITY) // POWER, POSITION, VELOCITY
                .setController(launcherController.copy()); // Use a copy of the controller for the second motor to avoid shared state issues
        Hardware.MotorGroup launcherGroup = new Hardware.MotorGroup("launcher")
                .addMotor(launcher_left)
                .addMotor(launcher_right);

        Hardware.Servo transferServo = new Hardware.Servo("transfer_servo", hardwareMap)
                .setType(Hardware.Servo.Type.STANDARD) // STANDARD, CONTINUOUS_ROTATION
                .setIdleMode(Hardware.Servo.IdleMode.BRAKE) // BRAKE, FLOAT
                .setInitialPosition(0.5);

        Robot robot = new Robot(hardwareMap)
                .setBulkReads(Robot.BulkReadMode.MANUAL) // AUTO, MANUAL, NONE
                .setFieldCentric(false) // Set to true for field-centric control, false for robot-centric
                .setDrivetrain(drivetrain) // Works for both Pedro and regular Mecanum
                .addHardware(launcher)
                .addHardware(transferServo);

        waitForStart();
        while (opModeIsActive()) {
            robot.update();
            Pose2D pose = robot.getPose(); // Get the current pose of the robot from the localizer
            Pose2D velocity = robot.getVelocity(); // Get the current velocity of the robot from the localizer

            robot.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.motorGroup("launcher").setSetpoint(gamepad2.a ? 1000 : 0); // 1000 ticks per second when A is pressed, 0 otherwise
            robot.servo("transfer_servo").setPosition(gamepad2.b ? 1.0 : 0.0); // 1.0 (fully forward) when B is pressed, 0.0 (fully backward) otherwise
        }
    }
}
