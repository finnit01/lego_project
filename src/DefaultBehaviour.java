import lejos.subsumption.Behavior;

public class DefaultBehaviour implements Behavior {

    Action action;

    public DefaultBehaviour(Action action) {
        if (action==null) throw new NullPointerException("action cannot be null.");

        this.action = action;
    }

    public boolean takeControl() {
        return true;
    }

    public void suppress() {
        action.suppress();
    }

    public void action() {
        action.action();
    }

}
