import lejos.nxt.LCD;
import lejos.subsumption.Behavior;
import lejos.navigation.TachoPilot;

public class DriveForwardAction implements Action {

    private static final int MAXIMUM_SPEED = 100;

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
        pilot.forward();
        isActive = true;
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

    private void setMoveSpeed(double speed) {
        int newspeed = ((int) (speed * MAXIMUM_SPEED));
        LCD.clear();
        LCD.drawString("Speed -> " + newspeed, 0, 0);
        pilot.setMoveSpeed((int) (speed * MAXIMUM_SPEED));
        if (newspeed > 0) {
            pilot.forward();
        }
        else {
            pilot.stop();
            isActive = false;
        }
    }
}
