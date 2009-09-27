//import lejos.nxt.Sound;

public class TwoBeepsAction implements Action {
    public void action() {
        lejos.nxt.Sound.twoBeeps();
    }

    public void suppress() {
        // nothing to do
    }
}

