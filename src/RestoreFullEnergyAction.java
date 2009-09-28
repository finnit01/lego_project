import lejos.nxt.LCD;
//import lejos.nxt.Sound;

/**
 * Handles the robot's actions when the
 * energy of the robot is fully restored.
 * @author 
 *
 */
public class RestoreFullEnergyAction implements Action {

	/**
	 * Holds the energy level of the robot.
	 */
    private EnergyLevel energy;

	/**
	 * Creates a new instance of the action taken when
	 * the energy level is fully restored.
	 * @param energy The new energy level.
	 */
    public RestoreFullEnergyAction(EnergyLevel energy) {
        if (energy==null) throw new NullPointerException("energy cannot be null.");
        this.energy = energy;
    }

	/**
	 * The action performed when energy is fully restored.
	 */
    public void action() {
        LCD.clear();
        LCD.drawString("Full Energy", 0, 0);
        energy.setEnergyLevel(1.0);
    }

	/**
	 * The action performed when the energy is no longer
	 * needed to be fully restored.
	 */
    public void suppress() {
        // nothing to do
    }
}

