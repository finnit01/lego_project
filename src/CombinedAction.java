import lejos.nxt.Sound;

public class CombinedAction implements Action {

    private Action firstAction;
    private Action secondAction;

    public CombinedAction(Action firstAction, Action secondAction) {

        if (firstAction==null) throw new NullPointerException("firstAction cannot be null.");
        if (secondAction==null) throw new NullPointerException("firstAction cannot be null.");

        this.firstAction = firstAction;
        this.secondAction = secondAction;
    }

    public void action() {
        firstAction.action();
        secondAction.action();
    }

    public void suppress() {
        firstAction.suppress();
        secondAction.suppress();
    }
}

