import lejos.navigation.TachoPilot;

/**
 * Handles the "GoCrazy" action for the robot.
 *
 * @author Malcolm King, Trevor Finnie
 *
 */
public class GoCrazyAction implements Action {

  /**
   * A TachoPilot object to pilot the robot.
   */
  private TachoPilot pilot;

  /**
   * The energy level object.
   */
  private EnergyLevel energy;

  /**
   * Creates the GoCrazyAction object and sets instance variables to the
   * parameters given.
   *
   * @param pilot
   *          The TachoPilot object of the robot.
   * @param energy
   *          The energy level of the robot.
   */
  public GoCrazyAction(TachoPilot pilot, EnergyLevel energy) {
    if (pilot == null) {
      throw new NullPointerException("pilot cannot be null.");
    }
    this.pilot = pilot;
    this.energy = energy;
  }

  /**
   * When the GoCrazy Action is required, beep twice then do some rotations.
   */
  public void action() {
    WombotLCD.drawCrazy(energy.getEnergyLevel());
    pilot.rotate(-90);
    pilot.rotate(180);
    pilot.rotate(-270);
  }

  /**
   * When the action is not required, stop any changes with the TachoPilot
   * object.
   */
  public void suppress() {
    pilot.stop();
  }
}
