import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;

/**
 * Performs the required calls when the robot's sleep action is needed or
 * suppressed.
 *
 * @author
 *
 */
public class SleepAction implements Action {
  /**
   * The minimum value from the sound sensor for the robot to respond to.
   */
  private static final int SOUND_THRESHOLD = 40;
  /**
   * Reference to the sound sensor being used to detect noise over.
   */
  private SoundSensor sound;
  private TouchSensor touch;

  /**
   * Sets the instance variables to those given as parameters.
   *
   * @param sound
   *          The sound sensor being used.
   */
  public SleepAction(SoundSensor sound, TouchSensor touch) {
    this.sound = sound;
    this.touch = touch;
  }

  /**
   * Do nothing until a clap or another similar noise is heard.
   */
  public void action() {
    // sleeping, do nothing but wait for a clap
    while (sound.readValue() < SOUND_THRESHOLD && !touch.isPressed()) {
    }
  }

  /**
   * Do nothing when this action is supressed.
   */
  public void suppress() {
    // do nothing
  }

}
