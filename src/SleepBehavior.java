import lejos.subsumption.Behavior;

/**
 * Handles the behaviour required by the robot when it
 * is in sleep mode.
 * 
 * @author
 *
 */
public class SleepBehavior implements Behavior {

	/**
	 * The action to take when this behaviour is needed.
	 */
    private Action action;
    /**
     * The energy level of the robot.
     */
    private EnergyLevel energyLevel;

    /**
     * Checks to see valid parameters, and then sets instance variables to
     * them.
     * @param action The action to take when the behaviour is used.
     * @param energyLevel The energy level of the robot.
     */
    public SleepBehavior(Action action, EnergyLevel energyLevel) {
        if (action==null) throw new NullPointerException("action cannot be null.");

        this.energyLevel = energyLevel;
        this.action = action;
    }

    /**
     * If the robot has no energy, then the sleep behaviour is needed
     * to be ran.
     * @return A boolean indicating that this behaviour should take control.
     */
    public boolean takeControl() {
        return energyLevel.getEnergyLevel() <= 0;
    }

    /**
     * Suppress the required action when this behaviour is suppressed.
     *
     */
    public void suppress() {
        action.suppress();
    }

    /**
     * Run the required action when this behaviour is needed.
     *
     */
    public void action() {
        action.action();
    }

}
