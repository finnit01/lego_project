import lejos.subsumption.Behavior;
//import lejos.nxt.Motor;
//import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
//import lejos.nxt.LCD;

/**
 * Handle behaviour when a loud sound is heard and the NXT is sleeping.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class DetectSoundWhileSleeping implements Behavior {

  final int SOUND_THRESHOLD = 70;

  SoundSensor sound;

  public DetectSoundWhileSleeping(SoundSensor sound) {
    this.sound = sound;
  }

  /**
   * Take control if a loud sound is heard and the NXT is sleeping.
   */
  public boolean takeControl() {
    return (GlobalVars.getEnergy() <= 0 && sound.readValue() > SOUND_THRESHOLD);
  }

  /**
   * Instructs robot what to do when this behaviour is suppressed.
   */
  public void suppress() {
  }

  /**
   * Increase energy level.
   */
  public void action() {
    GlobalVars.setEnergy(2000);
  }

}
