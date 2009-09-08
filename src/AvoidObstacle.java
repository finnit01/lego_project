//
import lejos.subsumption.Behavior;
import lejos.navigation.TachoPilot;
//import lejos.nxt.Motor;
//import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * Handle the bahaviour when the NXT drives within range of an obstacle.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class AvoidObstacle implements Behavior
{

  final int DISTANCE_THRESHOLD = 20;

  TachoPilot pilot;
  UltrasonicSensor sonic;

  public AvoidObstacle(TachoPilot pilot, UltrasonicSensor sonic) {
    this.pilot = pilot;
    this.sonic = sonic;
  }

    /**
     * Take control if the NXT is within range of an obstacle.
     */
    public boolean takeControl()
    {
        return (sonic.getDistance() < DISTANCE_THRESHOLD); //TODO: Adjust the ultrasonic distance detection value.
    }

    /**
     * Instructs robot what to do when this behaviour is
     * suppressed.
     */
    public void suppress()
    {
        pilot.stop();
    }

    /**
     * Instructs robot what to do when this behaviour is
     * active.
     */
    public void action()
    {
        pilot.rotate(90);
    }

}
