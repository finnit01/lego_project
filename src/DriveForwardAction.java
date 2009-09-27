import lejos.nxt.LCD;
//import lejos.subsumption.Behavior;
import lejos.navigation.TachoPilot;

public class DriveForwardAction implements Action {

    private static final int MAXIMUM_SPEED = 20;
    private static final int SLOW_SPEED = 5;

    private TachoPilot pilot;
    private EnergyLevel energyLevel;
    private boolean isActive;

    public DriveForwardAction(TachoPilot pilot, EnergyLevel energyLevel) {

        if (pilot==null) throw new NullPointerException("pilot cannot be null.");
        if (energyLevel==null) throw new NullPointerException("energyLevel cannot be null.");

        this.pilot = pilot;
        this.energyLevel = energyLevel;
        isActive = false;

        // set up listener on EnergyLevel so can update speed
        energyLevel.addListener(new EnergyListener());

    }

    public void action() {
        setMoveSpeed(energyLevel.getEnergyLevel());
    }

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

        LCD.clear();
        LCD.drawString("Energy: " + energy, 0, 0);
        LCD.drawString("Speed:  " + speed, 0, 1);

        pilot.setMoveSpeed(speed);
        if (speed > 0) {
            pilot.forward();
            isActive = true;
        }
        else {
            pilot.stop();
            isActive = false;
        }
    }
}
