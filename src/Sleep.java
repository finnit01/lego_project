import lejos.subsumption.Behavior;
//import lejos.nxt.Motor;
//import lejos.nxt.SensorPort;
//import lejos.nxt.LightSensor; //TODO: ColorLightSensor or LightSensor.
import lejos.navigation.TachoPilot;

/**
 * Handle the behaviour of the NXT when it runs out of energy.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class Sleep implements Behavior {

  TachoPilot pilot;

  public Sleep(TachoPilot pilot) {
    this.pilot = pilot;
  }

  /**
   * Take control if the energy level of the NXT is 0 or below.
   */
  public boolean takeControl() {
    return (GlobalVars.getEnergy() <= 0);
  }

  /**
   * Instructs robot what to do when this behaviour is suppressed.
   */
  public void suppress() {
  }

  /**
   * Stop the motors.
   */
  public void action() {
    pilot.stop();
    // play a song when sleeping. as this is the highest priority behaviour it will repeat.
    GlobalVars.sleepSong();
  }

}
