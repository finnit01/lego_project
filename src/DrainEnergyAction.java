import lejos.util.Timer;
import lejos.util.TimerListener;

/**
 * Handles the actions that is required for when the energy
 * of the robot is being drained.
 * @author
 *
 */
public class DrainEnergyAction implements Action {

    /**
     * The amount to decrease the energy level each time quanta.
     */
    private static final double DECREASE_AMOUNT_ABOVE_CUTOFF = 0.05;
    private static final double DECREASE_AMOUNT_BELOW_CUTOFF = 0.01;


    /**
     * Cutoff point for energy level, where to start draining more
     * slowly
     */
    private static final double LOW_ENERGY_CUTOFF = 0.2;

    /**
     * The time to wait during each time quanta.
     */
    private static final int TIMER_INTERVAL_MILLIS = 300;
    /**
     * The current energy level of the robot.
     */
    private EnergyLevel energyLevel;
    /**
     * A timer used to keep track each time quanta.
     */
    private Timer timer;

    // TODO: pass ing decrease amount and interval
    /**
     * Creates the DrainEnergy object and sets instance variables to those
     * given in the parameters.
     * @param energyLevel The energy level of the robot.
     */
    public DrainEnergyAction(EnergyLevel energyLevel) {

        if (energyLevel==null) throw new NullPointerException("energyLevel cannot be null.");

        this.energyLevel = energyLevel;

        // Create the timer that will fire every TIMER_INTERVAL_MILLS
        timer = new Timer(TIMER_INTERVAL_MILLIS, new DecreaseEnergy());
    }

    /**
     * Start the timer to performed time-based events
     * when action is needed.
     */
    public void action() {
        timer.start();
    }

    /**
    * Stop the timer to stop time-based events when
    * the action is not needed.
    */
    public void suppress() {
        timer.stop();
    }

    /**
     * Implements the decrement of energy over each time
     * quanta.
     * @author
     *
     */
    private class DecreaseEnergy implements TimerListener {

    	/**
    	 * Performs these actions each time quanta.
    	 */
        public void timedOut() {
            double energy = energyLevel.getEnergyLevel();
            energy-= energy < LOW_ENERGY_CUTOFF ? DECREASE_AMOUNT_BELOW_CUTOFF : DECREASE_AMOUNT_ABOVE_CUTOFF;
            if (energy > 0) {
                energyLevel.setEnergyLevel(energy);
            }
            else {
                energyLevel.setEnergyLevel(0);
            }
        }

    }

}

