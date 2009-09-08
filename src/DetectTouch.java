import lejos.subsumption.Behavior;
//import lejos.nxt.Motor;
//import lejos.nxt.SensorPort;
//import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;

/**
 * Handles the touch detection behaviour for an NXT robot.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class DetectTouch implements Behavior {

  TouchSensor touch;

  public DetectTouch(TouchSensor touch) {
    this.touch = touch;
  }

  /**
   * Take control if the touch sensor is pressed.
   */
  public boolean takeControl() {
    return touch.isPressed();
  }

  /**
   * Instructs robot what to do when this behaviour is suppressed.
   */
  public void suppress() {
  }

  /**
   * Instructs robot what to do when this behaviour is active.
   */
  public void action() {
  }

}
