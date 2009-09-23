import lejos.subsumption.Behavior;
//import lejos.nxt.SensorPort;
//import lejos.nxt.SoundSensor;
//import lejos.nxt.TouchSensor;
import lejos.navigation.TachoPilot;
//import lejos.nxt.Sound;
import lejos.nxt.LightSensor;

/**
 * Handles the touch detection behaviour for an NXT robot.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class DetectBlue implements Behavior {

  TachoPilot pilot;
  LightSensor light;
  boolean flag = false;

  public DetectBlue(TachoPilot pilot, LightSensor light) {
    this.pilot = pilot;
    this.light = light;
  }

  private boolean withinTolerance() {
    int lightValue = light.readNormalizedValue();
    return (lightValue > GlobalVars.blueMin && lightValue < GlobalVars.blueMax);
  }

  /**
   * Take control if blue is detected.
   */
  public boolean takeControl() {
    if (withinTolerance() && !flag) {
      flag = true;
      lejos.nxt.Sound.beep(); // for testing
      return true;
    } else if (!withinTolerance() && flag) {
      flag = false;
      lejos.nxt.Sound.twoBeeps(); // for testing
    }
    return false;
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
    lejos.nxt.LCD.clear();
    lejos.nxt.LCD.drawString("DetectBlue",0,0);
    GlobalVars.greenSong();
  }

}
