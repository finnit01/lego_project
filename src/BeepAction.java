//import lejos.nxt.Sound;

/**
 * Handles the robot's actions for the "Beep Action".
 * @author
 *
 */
public class BeepAction implements Action {
	
	/**
	 * Make the robot beep.
	 */
    public void action() {
        lejos.nxt.Sound.beep();
    }

    /**
     * Do nothing more when the beep action is not
     * needed.
     */  
    public void suppress() {
        // nothing to do
    }
}

