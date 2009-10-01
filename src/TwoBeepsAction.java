//import lejos.nxt.Sound;

/**
 * Performs the required calls for the 'Two Beeps'
 * action.
 *
 * @author
 *
 */
public class TwoBeepsAction implements Action {

	/**
	 * Creates two beep noises when the TwoBeepsAction
	 * method is called.
	 */
    public void action() {
        lejos.nxt.Sound.twoBeeps();
    }

	/**
	 * No calls is required when the action is suppressed.
	 */
    public void suppress() {
        // nothing to do
    }
}

