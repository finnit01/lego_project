import lejos.subsumption.Behavior;
//import lejos.nxt.Motor;
import lejos.navigation.TachoPilot;
//import lejos.nxt.LCD;

/**
 * Handle the standard behaviour for the NXT robot which is driving
 * forward using the left and right motors. This is the lowest priority
 * behaviour.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class StandardDrive implements Behavior {

  TachoPilot pilot;

  public StandardDrive(TachoPilot pilot) {
    this.pilot = pilot;
  }

  /**
   * Take control in any situation.
   * @return true
   */
  public boolean takeControl() {
    return true;
  }

  /**
   * Stop when this behaviour is suppressed.
   */
  public void suppress() {
    pilot.stop();
  }

  /**
   * Drive forward and use energy.
   */
  public void action() {
//    pilot.reset();
    pilot.forward();
    while (pilot.isMoving()) {
      try {
        Thread.sleep(1000);
      }
      catch (Exception e) {}
      GlobalVars.setEnergy(GlobalVars.getEnergy() - (int)pilot.getMoveSpeed());
//      LCD.drawInt(GlobalVars.getEnergy(),0,0);
//      LCD.drawString(""+pilot.getMoveSpeed(), 0, 1);
    }
  }

}
