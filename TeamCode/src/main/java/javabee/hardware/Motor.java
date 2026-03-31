package javabee.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

/**
 * This class is a wrapper around the DcMotorEx class that provides additional functionality and a more user-friendly API.
 * It also allows for easy integration with controllers (PID, PVS, etc.).
 * @author Dylan B. - 18597 RoboClovers Delta
 */
public class Motor extends BaseHardware {
    private final DcMotorEx motor;

    public Motor(String name, HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, name);
    }

    public Motor setDirection(Direction direction) {
        motor.setDirection(direction == Direction.FORWARD ?
                DcMotorEx.Direction.FORWARD : DcMotorEx.Direction.REVERSE);
        return this;
    }

    public Motor setZeroPowerMode(ZeroPowerMode mode) {
        motor.setZeroPowerBehavior(mode == ZeroPowerMode.BRAKE ?
                DcMotorEx.ZeroPowerBehavior.BRAKE : DcMotorEx.ZeroPowerBehavior.FLOAT);
        return this;
    }

    public Motor setRunMode(RunMode mode) {
        switch (mode) {
            case POWER:
                motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
                break;
            case POSITION:
                motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
            case VELOCITY:
                motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
                break;
        }
        return this;
    }

    public void update() {
        // TODO: Update motor power based on controller if applicable
    }

    public void setPower(double power) {
        motor.setPower(power);
    }

    public double getPower() {
        return motor.getPower();
    }

    // TODO: Add methods for setting velocity, target position, etc.
    // TODO: Add controller support when we implement the controller class

    public enum Direction {
        FORWARD,
        REVERSE
    }

    public enum ZeroPowerMode {
        BRAKE,
        FLOAT
    }

    public enum RunMode {
        POWER,
        POSITION,
        VELOCITY
    }
}
