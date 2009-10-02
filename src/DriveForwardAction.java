import lejos.nxt.LCD;
import lejos.navigation.TachoPilot;

/**
 * Handles the actions required for the robot to drive forward.
 *
 * @author Trevor Finnie, Malcolm King
 * @version 1 October 2009
 *
 */
public class DriveForwardAction implements Action {

  /**
   * Maximum speed for the robot to move.
   */
  private static final int MAXIMUM_SPEED = 60;
  /**
   * Minimum speed for the robot to move.
   */
  private static final int SLOW_SPEED = 10;

  /**
   * Reference to the TachoPilot object.
   */
  private TachoPilot pilot;
  /**
   * Reference to the robot's EnergyLevel object.
   */
  private EnergyLevel energyLevel;
  /**
   * Indicates whether the robot is active.
   */
  private boolean isActive;

  /**
   * Constructor for the DriveForwardAction object.
   *
   * @param pilot
   *          The TachoPilot object used for the robot.
   * @param energyLevel
   *          The EnergyLevel object used for the robot.
   */
  public DriveForwardAction(TachoPilot pilot, EnergyLevel energyLevel) {

    if (pilot == null) {
      throw new NullPointerException("pilot cannot be null.");
    }
    if (energyLevel == null) {
      throw new NullPointerException("energyLevel cannot be null.");
    }

    this.pilot = pilot;
    this.energyLevel = energyLevel;
    isActive = false;

    // set up listener on EnergyLevel so can update speed
    energyLevel.addListener(new EnergyListener());

  }

  /**
   * Sets the moving speed based on the current energy level.
   */
  public void action() {
    setMoveSpeed(energyLevel.getEnergyLevel());
  }

  /**
   * Stops the robot, and stops the robot's activity.
   */
  public void suppress() {
    pilot.stop();
    isActive = false;
  }

  /**
   * Listen for a change in the energy level.
   */
  private class EnergyListener implements EnergyLevelListener {
    /**
     * Set the move speed when the energy level changes.
     */
    public void energyLevelChanged() {
      if (isActive) {
        setMoveSpeed(energyLevel.getEnergyLevel());
      }
    }
  }

  /**
   * Set the move speed of the robot depending on it's current energy.
   *
   * @param energy
   *          The current energy level
   */
  private void setMoveSpeed(double energy) {

    int speed;

    LCD.clear();
    if (energy < 0.2) {
      WombotLCD.drawNeutral(energy);
      speed = SLOW_SPEED;
    } else {
      WombotLCD.drawSmile(energy);
      speed = MAXIMUM_SPEED;
    }

    // drawEnergy();
    // LCD.clear();
    // LCD.drawString("Energy: " + energy, 0, 0);
    // LCD.drawString("Speed: " + speed, 0, 1);

    pilot.setMoveSpeed(speed);
    if (speed > 0) {
      pilot.forward();
      isActive = true;
    } else {
      pilot.stop();
      isActive = false;
    }
  }

}
