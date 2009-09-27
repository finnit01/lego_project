import lejos.nxt.SoundSensor;

public class SleepAction implements Action {

  private static final int SOUND_THRESHOLD = 40;
  private SoundSensor sound;

  public SleepAction(SoundSensor sound) {
    this.sound = sound;
  }

  public void action() {
    //sleeping, do nothing but wait for a clap
    while(sound.readValue() < SOUND_THRESHOLD) {}
  }

  public void suppress() {
    //do nothing
  }

}
