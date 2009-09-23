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

  private static int COLOUR_THRESHOLD = 20;

  TachoPilot pilot;
  lejos.nxt.LightSensor light;
  boolean onYellow; // are we currently over yellow?

  public DetectYellow(TachoPilot pilot, LightSensor light) {
    this.pilot = pilot;
    this.light = light;
    onYellow = seeYellow(); // we might start off on yellow, so
                            // handle this correctly
  }

  /**
   * Take control if crossing onto or off yellow region.
   */
  public boolean takeControl() {
      // Take action if either:
      // 1. robot wasn't over yellow, but now it sees yellow; or
      // 2. robot was over yellow, but it no longer sees yellow
      return onYellow != seeYellow();
  }

  private boolean seeYellow() {
      int colour = light.readNormalizedValue();
      return colour > GlobalVars.yellow - COLOUR_THRESHOLD 
          && colour < GlobalVars.yellow + COLOUR_THRESHOLD;
  }

  /**
   * Instructs robot what to do when this behaviour is suppressed.
   */
  public void suppress() {
    // No action required
  }

  /**
   * Instructs robot what to do when this behaviour is active.
   */
  public void action() {
    onYellow = !onYellow; // we either moved onto yellow, or off of yellow so update state appropriately
    if (onYellow) {
      // Beep whenever we first see yellow
      lejos.nxt.Sound.twoBeeps();
    }
  }

}
