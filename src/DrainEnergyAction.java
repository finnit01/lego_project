import lejos.util.Timer;
import lejos.util.TimerListener;

public class DrainEnergyAction implements Action {

    private static final double DECREASE_AMOUNT = 0.05;
    private static final int TIMER_INTERVAL_MILLIS = 1000;

    private EnergyLevel energyLevel;
    private Timer timer;

    public DrainEnergyAction(EnergyLevel energyLevel) {

        if (energyLevel==null) throw new NullPointerException("energyLevel cannot be null.");

        this.energyLevel = energyLevel;

        // Create the timer that will fire every TIMER_INTERVAL_MILLS
        timer = new Timer(TIMER_INTERVAL_MILLIS, new DecreaseEnergy(DECREASE_AMOUNT));
    }

    // Action methods
    public void action() {
        timer.start();
    }

    // TODO: add suppress to Action interface
    public void suppress() {
        timer.stop();
    }

    private class DecreaseEnergy implements TimerListener {

        private double amount;

        public DecreaseEnergy(double amount) {
            this.amount = amount;
        }

        public void timedOut() {
            double energy = energyLevel.getEnergyLevel();
            energy-=amount;
            if (energy > 0) {
                energyLevel.setEnergyLevel(energy);
            }
            else {
                energyLevel.setEnergyLevel(0);
            }
        }

    }

}

