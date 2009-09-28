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

    /**
     * Creates the GoCrazyAction object and sets instance
     * variables to the parameters given.
     * @param pilot The TachoPilot object of the robot.
     */    
    public GoCrazyAction(TachoPilot pilot) {
        if (pilot==null) throw new NullPointerException("pilot cannot be null.");
        this.pilot=pilot;
    }

    /**
     * When the GoCrazy Action is required, beep twice
     * then do some rotations.
     */
    public void action() {
        lejos.nxt.Sound.twoBeeps();
        pilot.rotate(-90);
        pilot.rotate(180);
    }

    /**
     * When the action is not required, stop any changes
     * with the TachoPilot object/
     */
    public void suppress() {
        pilot.stop();
    }
}

