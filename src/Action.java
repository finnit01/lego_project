/**
 * Interface for Actions.
 * @author Trevor Finnie
 * @version 1 October 2009
 */
 public interface Action {

    /**
     * Action to be performed.
     */
    public void action();

    /**
     * Suppresses the action.
     */
    public void suppress();
}
