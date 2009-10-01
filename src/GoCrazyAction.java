import lejos.navigation.TachoPilot;
//import lejos.nxt.Sound;

/**
 * Handles the "GoCrazy" action for the robot.
 * @author
 *
 */
public class GoCrazyAction implements Action {

	/**
	 * Holds the reference to the TachoPilot object
	 * for the robot.
	 */
    private TachoPilot pilot;
    private EnergyLevel energy;

    /**
     * Creates the GoCrazyAction object and sets instance
     * variables to the parameters given.
     * @param pilot The TachoPilot object of the robot.
     * @paramt energy The energy level of the robot.
     */
    public GoCrazyAction(TachoPilot pilot, EnergyLevel energy) {
        if (pilot == null) {
          throw new NullPointerException("pilot cannot be null.");
        }
        this.pilot = pilot;
        this.energy = energy;
    }

    /**
     * When the GoCrazy Action is required, beep twice
     * then do some rotations.
     */
    public void action() {
        //pilot.backward();
        //lejos.nxt.Sound.playTone(2000, 2000);
        //try {
        //  Thread.sleep(1000);
        //} catch (Exception e) {}
        //lejos.nxt.Sound.beepSequenceUp();
        //pilot.rotate((int)(Math.random()*540), false);
        WombotLCD.drawCrazy(energy.getEnergyLevel());
        pilot.rotate(-90);
        pilot.rotate(180);
        pilot.rotate(-270);
    }

    /**
     * When the action is not required, stop any changes
     * with the TachoPilot object.
     */
    public void suppress() {
        pilot.stop();
    }
}

