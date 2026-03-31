package javabee.hardware;

/**
 * This is the base class for all hardware components in JavaBee.
 * It provides a common interface for updating hardware components every loop.
 * All hardware components (motors, servos, sensors, etc.) should extend this class.
 * @author Dylan B. - 18597 RoboClovers Delta
 */
public abstract class BaseHardware {
    /**
     * This method will be called every loop by the main Robot class.
     * Things like updating controllers, holding positions, etc. should be done here.
     * This should not be used by the user's OpModes.
     */
    public abstract void update();
}
