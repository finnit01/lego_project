import lejos.navigation.TachoPilot;
import lejos.nxt.Sound;

public class GoCrazyAction implements Action {

    private TachoPilot pilot;

    public GoCrazyAction(TachoPilot pilot) {
        if (pilot==null) throw new NullPointerException("pilot cannot be null.");
        this.pilot=pilot;
    }

    public void action() {
        lejos.nxt.Sound.twoBeeps();
        pilot.rotate(-90);
        pilot.rotate(180);
    }

    public void suppress() {
        pilot.stop();
    }
}

