import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.Sound;

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
    // play a lullaby tune once
    lullaby();
    // sleeping, do nothing but wait for a clap
    while (sound.readValue() < SOUND_THRESHOLD && !touch.isPressed()) {
    }
  }

  /**
   * Do nothing when this action is suppressed.
   */
  public void suppress() {
    // do nothing
  }

  private void lullaby() {
    short[] note = { 988, 1090, 0, 0, 1175, 545, 0, 0, 880, 1090, 0, 0, 784,
        273, 0, 0, 880, 273, 0, 0, 988, 1090, 0, 0, 1175, 545, 0, 0, 880, 1090,
        0, 0, 494, 1090, 0, 0, 587, 545, 0, 0, 880, 1090, 0, 0, 784, 545, 0, 0,
        588, 1090, 0, 0, 523, 273, 0, 0, 494, 273, 0, 0, 440, 1090 };

    for (int i = 0; i < note.length; i += 2) {
      int w = (int) note[i + 1];
      int n = (int) note[i];
      if (n != 0) {
        Sound.playTone(n, w);
      }
      try {
        Thread.sleep(w);
      } catch (InterruptedException e) {
      }
    }
  }

}
