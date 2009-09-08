import lejos.subsumption.Behavior;
//import lejos.nxt.SensorPort;
//import lejos.nxt.SoundSensor;
//import lejos.nxt.TouchSensor;
import lejos.navigation.TachoPilot;
import lejos.nxt.Sound;
import lejos.nxt.LightSensor;

/**
 * Handles the touch detection behaviour for an NXT robot.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class DetectYellow implements Behavior {

  TachoPilot pilot;
  lejos.nxt.LightSensor light;

  public DetectYellow(TachoPilot pilot, LightSensor light) {
    this.pilot = pilot;
    this.light = light;
  }

  /**
   * Take control if yellow is detected.
   */
  public boolean takeControl() {
    return (light.readNormalizedValue() > GlobalVars.yellow - 20 && light.readNormalizedValue() < GlobalVars.yellow + 20);
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
    lejos.nxt.Sound.twoBeeps();
  }

}
