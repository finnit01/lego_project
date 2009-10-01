//import lejos.nxt.LCD;
//import lejos.subsumption.Behavior;
import lejos.navigation.TachoPilot;
import javax.microedition.lcdui.Graphics;

/**
 * Handles the actions required for the robot to drive forward.
 *
 * @author
 *
 */
public class DriveForwardAction implements Action {
  /**
   * Maximum speed for the robot to move.
   */
  private static final int MAXIMUM_SPEED = 100;
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

    if (pilot == null)
      throw new NullPointerException("pilot cannot be null.");
    if (energyLevel == null)
      throw new NullPointerException("energyLevel cannot be null.");

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

  private class EnergyListener implements EnergyLevelListener {
    public void energyLevelChanged() {
      if (isActive) {
        setMoveSpeed(energyLevel.getEnergyLevel());
      }
    }
  }

  private void setMoveSpeed(double energy) {

    int speed = (energy < 0.2) ? SLOW_SPEED : MAXIMUM_SPEED;

    drawEnergy();
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

  private void drawEnergy() {
    // draw a box for the energy level
    Graphics g = new Graphics();
    g.clear();
    g.drawRect(5, 5, 90, 20);
    g.fillRect(5, 5, (int) (energyLevel.getEnergyLevel() * 90), 20);
  }
}
