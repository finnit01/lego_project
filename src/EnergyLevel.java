import java.util.ArrayList;

/**
 * Holds the energy level of the NXT-Robot and handles
 * any listeners associated with them.
 * @author
 *
 */
public class EnergyLevel {

	/**
	 * The energy level of the robot.
	 */
    private double energy;
    /**
     * An array of listeners that are associated with the
     * robot's energy level.
     */
    private ArrayList<EnergyLevelListener> listeners;

    /**
     * Constructs the EnergyLevel object and assigns
     * instance variables to the parameters.
     * @param initialEnergyLevel The energy level at start time.
     */
    public EnergyLevel(double initialEnergyLevel) {
        energy = initialEnergyLevel;
        listeners = new ArrayList<EnergyLevelListener>();
    }

    /**
     * Returns the value of the energy level.
     * @return The energy level of the robot.
     */
    public double getEnergyLevel() {
        return energy;
    }

    /**
     * Sets the value of the energy level.
     * @param newEnergyLevel The new energy level of the robot.
     */
    public void setEnergyLevel(double newEnergyLevel) {
        if (newEnergyLevel != energy) {
            energy = newEnergyLevel;
            for (EnergyLevelListener l : listeners) {
                l.energyLevelChanged();
            }
        }
    }

    /**
     * Adds a listener for the use of the energy level.
     * @param listener The listener wanted to be added.
     */
    public void addListener(EnergyLevelListener listener) {
        listeners.add(listener);
    }
}

