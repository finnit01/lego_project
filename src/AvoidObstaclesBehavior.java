import lejos.subsumption.Behavior;
import lejos.nxt.UltrasonicSensor;
import lejos.navigation.TachoPilot;
import lejos.nxt.TouchSensor;

public class AvoidObstaclesBehavior implements Behavior {

  private UltrasonicSensor sonic;
  private TachoPilot pilot;
  private TouchSensor touch;
  private static final int DISTANCE_THRESHOLD = 10;

  public AvoidObstaclesBehavior(UltrasonicSensor sonic, TouchSensor touch, TachoPilot pilot) {
    this.sonic = sonic;
    this.pilot = pilot;
    this.touch = touch;
  }

  public boolean takeControl() {
    return (sonic.getDistance() <= DISTANCE_THRESHOLD || touch.isPressed());
  }

  public void suppress() {
    // nothing to suppress
  }

  public void action() {
    // reverse from the obstacle and turn around
    pilot.travel(-100);
    pilot.rotate(-135);
  }

}
