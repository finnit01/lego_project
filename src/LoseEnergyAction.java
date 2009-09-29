//import lejos.nxt.Sound;

/**
 * Handles the action involved with losing energy.
 */
public class LoseEnergyAction implements Action {

  /**
   * Reference to the Energy Level of the robot.
   */
  private EnergyLevel energy;

  /**
   * Constructor for the LoseEnergyAction. Sets instance variables to
   * parameters.
   *
   * @param energy
   *          The energy level of the robot.
   */
  public LoseEnergyAction(EnergyLevel energy) {
    this.energy = energy;
  }

  /**
   * Reduce the energy level to half of current energy level.
   */
  public void action() {
    energy.setEnergyLevel(energy.getEnergyLevel() / 2);
  }

  /**
   * Do nothing when the action is suppressed.
   */
  public void suppress() {
    // nothing to suppress
  }
}