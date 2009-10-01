import lejos.subsumption.Behavior;
import lejos.nxt.UltrasonicSensor;
import lejos.navigation.TachoPilot;
import lejos.nxt.TouchSensor;

/**
 * Behaviour which detects and avoids obstacles.
 *
 * @author Malcolm King
 * @version 1 October 2009
 *
 */
public class AvoidObstaclesBehavior implements Behavior {

  /**
   * Sensors for the robot.
   */
  private UltrasonicSensor sonic;
  private TachoPilot pilot;
  private TouchSensor touch;

  /**
   * Distance from an obstacle before the avoid behaviour takes control.
   */
  private static final int DISTANCE_THRESHOLD = 10;

  /**
   * Construct an AvoidObstaclesBehavior object.
   *
   * @param sonic
   *          the ultrasonic sensor
   * @param touch
   *          the touch sensor
   * @param pilot
   *          the pilot for the robot
   */
  public AvoidObstaclesBehavior(UltrasonicSensor sonic, TouchSensor touch,
      TachoPilot pilot) {
    this.sonic = sonic;
    this.pilot = pilot;
    this.touch = touch;
  }

  /**
   * Take control when distance from an obstacle is less than the distance
   * threshold or the touch sensor is pressed.
   *
   * @return true if distance < threshold or touch sensor is pressed, false
   *         otherwise
   */
  public boolean takeControl() {
    return (sonic.getDistance() <= DISTANCE_THRESHOLD || touch.isPressed());
  }

  /**
   * Stop the motors.
   */
  public void suppress() {
    pilot.stop();
  }

  /**
   * Move away from the obstacle.
   */
  public void action() {
    // reverse from the obstacle and turn around
    pilot.travel(-100);
    pilot.rotate(-135);
  }

}
