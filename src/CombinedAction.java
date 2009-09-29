//import lejos.nxt.Sound;

/**
 * Handles combining two actions together to allow
 * the robot to act in more diverse ways.
 * @author
 *
 */
public class CombinedAction implements Action {

  /**
   * The first action required in the combination.
   */
    private Action firstAction;
    /**
     * The second action required in the combination.
     */
    private Action secondAction;

    /**
     * Sets the instance variables to the parameters given
     * in this constructor.
     * @param firstAction The first action in the combination.
     * @param secondAction The second action in the combination.
     */
    public CombinedAction(Action firstAction, Action secondAction) {

        if (firstAction==null) throw new NullPointerException("firstAction cannot be null.");
        if (secondAction==null) throw new NullPointerException("firstAction cannot be null.");

        this.firstAction = firstAction;
        this.secondAction = secondAction;
    }

    /**
     * The combined actions to take place.
     */
    public void action() {
        firstAction.action();
        secondAction.action();
    }

    /**
     * The combined actions to suppress.
     */
    public void suppress() {
        firstAction.suppress();
        secondAction.suppress();
    }
}
