import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class RestoreFullEnergyAction implements Action {

    private EnergyLevel energy;

    public RestoreFullEnergyAction(EnergyLevel energy) {
        if (energy==null) throw new NullPointerException("energy cannot be null.");
        this.energy = energy;
    }

    public void action() {
        LCD.clear();
        LCD.drawString("Full Energy", 0, 0);
        energy.setEnergyLevel(1.0);
    }

    public void suppress() {
        // nothing to do
    }
}

