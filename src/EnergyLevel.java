import java.util.ArrayList;

public class EnergyLevel {

    private double energy;
    private ArrayList<EnergyLevelListener> listeners;

    public EnergyLevel(double initialEnergyLevel) {
        energy = initialEnergyLevel;
        listeners = new ArrayList<EnergyLevelListener>();
    }

    public double getEnergyLevel() {
        return energy;
    }

    public void setEnergyLevel(double newEnergyLevel) {
        if (newEnergyLevel!= energy) {
            energy = newEnergyLevel;
            for (EnergyLevelListener l : listeners) {
                l.energyLevelChanged();
            }
        }
    }

    public void addListener(EnergyLevelListener listener) {
        listeners.add(listener);
    }
}

