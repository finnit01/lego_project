import lejos.subsumption.Behavior;

/**
 * Handles the defaulted behaviour of the robot when
 * no other behaviour is required.
 * @author
 *
 */
public class DefaultBehaviour implements Behavior {

	/**
	 * The action to take place during the defaulted behaviour.
	 */
    Action action;

    /**
     * Create a DefaultBehaviour object with an action associated with the
     * defaulted behaviour.
     * @param action The default action to be performed.
     */
    public DefaultBehaviour(Action action) {
        if (action==null) throw new NullPointerException("action cannot be null.");

        this.action = action;
    }

    /**
     * Allows the defaulted behaviour to always be running,
     * and to only take control when no other behaviours are
     * being used.
     * @return True regardless
     */
    public boolean takeControl() {
        return true;
    }

    /**
     * The actions required when this behaviour is suppressed.
     */
    public void suppress() {
        action.suppress();
    }

    /**
     * The actions required when this action is taking place.
     */
    public void action() {
        action.action();
    }

}
