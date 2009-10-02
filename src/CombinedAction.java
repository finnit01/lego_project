/**
 * Combines two actions together to allow the robot to act in more diverse ways.
 *
 * @author Trevor Finnie
 * @version 1 October 2009
 *
 */
public class CombinedAction implements Action {

  /**
   * The first action to perform as part of the combined action.
   */
  private Action firstAction;

  /**
   * The second action to perform as part of the combined action.
   */
  private Action secondAction;

  /**
   * Construct a CombinedAction object.
   *
   * @param firstAction
   *          The first action in the combination.
   * @param secondAction
   *          The second action in the combination.
   */
  public CombinedAction(Action firstAction, Action secondAction) {

    if (firstAction == null) {
      throw new NullPointerException("firstAction cannot be null.");
    }
    if (secondAction == null) {
      throw new NullPointerException("secondAction cannot be null.");
    }

    this.firstAction = firstAction;
    this.secondAction = secondAction;
  }

  /**
   * Run the first action and then the second action.
   */
  public void action() {
    firstAction.action();
    secondAction.action();
  }

  /**
   * Suppress the first action and then the second action.
   */
  public void suppress() {
    firstAction.suppress();
    secondAction.suppress();
  }
}
