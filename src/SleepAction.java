import lejos.navigation.TachoPilot;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.Sound;

/**
 * Performs the required calls when the robot's sleep action is needed or
 * suppressed.
 *
 * @author Malcolm King, Cennyd Stott
 * @version 1 October 2009
 *
 */
public class SleepAction implements Action {

  /**
   * The minimum value from the sound sensor for the robot to respond to.
   */
  private static final int SOUND_THRESHOLD = 40;

  /**
   * Sound sensor.
   */
  private SoundSensor sound;

  /**
   * Touch sensor.
   */
  private TouchSensor touch;

  /**
   * Pilot object to control the motors.
   */
  private TachoPilot pilot;

  /**
   * Construct a SleepAction.
   *
   * @param sound
   *          The sound sensor being used.
   * @param touch
   *          The touch sensor.
   * @param pilot
   *          The pilot.
   */
  public SleepAction(SoundSensor sound, TouchSensor touch, TachoPilot pilot) {
    this.sound = sound;
    this.touch = touch;
    this.pilot = pilot;
  }

  /**
   * Do nothing until a clap or another similar noise is heard.
   */
  public void action() {
    WombotLCD.drawSleep(0.0);
    // play a lullaby tune once
    lullaby();
    // sleeping, do nothing but wait for a clap
    while (sound.readValue() < SOUND_THRESHOLD && !touch.isPressed()) {
      // wait for sound or touch
    }
  }

  /**
   * Do nothing when this action is suppressed.
   */
  public void suppress() {
    // do nothing
  }

  /**
   * Play a lullaby tune.
   */
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
        // do nothing
      }
    }
  }

}
