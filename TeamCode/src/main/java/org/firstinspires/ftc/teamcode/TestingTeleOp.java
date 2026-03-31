package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import javabee.hardware.Motor;

/**
 * This TeleOp is made to test the usage of already implemented methods
 * @author Dylan B. - 18597 RoboClovers Delta
 */
@TeleOp(name="Test Teleop", group="Linear OpMode")
public class TestingTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        Motor launcher_left = new Motor("launcher_left", hardwareMap)
                .setDirection(Motor.Direction.REVERSE)
                .setZeroPowerMode(Motor.ZeroPowerMode.BRAKE)
                .setRunMode(Motor.RunMode.VELOCITY); // POWER, POSITION, VELOCITY
        Motor launcher_right = new Motor("launcher_right", hardwareMap)
                .setDirection(Motor.Direction.FORWARD)
                .setZeroPowerMode(Motor.ZeroPowerMode.BRAKE)
                .setRunMode(Motor.RunMode.VELOCITY); // POWER, POSITION, VELOCITY
        // TODO: Add controllers to these motors when we implement the controller class
        // TODO: Add these motors to a MotorGroup when we implement the MotorGroup class

        waitForStart();
        while (opModeIsActive()) {
            // TODO: Robot.update()
            // TODO: Add velocities here
            telemetry.addData("Left Velocity", 0);
            telemetry.addData("Right Velocity", 0);
            telemetry.update();
        }
    }
}
