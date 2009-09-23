import lejos.nxt.Sound;

public class BeepAction implements Action {
    public void action() {
        lejos.nxt.Sound.beep();
    }

    public void suppress() {
        // nothing to do
    }
}

