import lejos.nxt.Sound;

/**
 * Action for a single beep.
 *
 * @author Trevor Finnie
 * @version 1 October 2009
 */
public class BeepAction implements Action {

  /**
   * Beep once.
   */
  public void action() {
    Sound.beep();
  }

  /**
   * Nothing to suppress.
   */
  public void suppress() {
    // nothing to do
  }
}
