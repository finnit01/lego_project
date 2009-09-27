import lejos.subsumption.Behavior;
//import lejos.nxt.SensorPort;
//import lejos.nxt.SoundSensor;
//import lejos.nxt.TouchSensor;
//import lejos.navigation.TachoPilot;
//import lejos.nxt.Sound;
import lejos.nxt.LightSensor;

/**
 * Handles the touch detection behaviour for an NXT robot.
 *
 * @author Timothy Black, Malcolm King, Trevor Finnie
 * @version September 2009 (0.4)
 */
public class DetectColour implements Behavior {

    private lejos.nxt.LightSensor light;
    private int colourMin;
    private int colourMax;
    private Action onColourAction;
    private Action offColourAction;
    private Action suppressAction;

    private boolean onColour; // are we currently over colour?

    public DetectColour(LightSensor light, int colourMin,
            int colourMax, Action onColourAction, Action offColourAction) {
        this.light = light;
        this.colourMin = colourMin;
        this.colourMax = colourMax;
        this.onColourAction = onColourAction;
        this.offColourAction = offColourAction;
        onColour = seeColour(); // we might start off on colour, so
        // handle this correctly
    }

    /**
     * Take control if crossing onto or off colour region.
     */
    public boolean takeControl() {
        // Take action if either:
        // 1. robot wasn't over colour, but now it sees colour; or
        // 2. robot was over colour, but it no longer sees colour
        return onColour != seeColour();
    }

    private boolean seeColour() {
        int colour = light.readNormalizedValue();
        return colour >= colourMin && colour <= colourMax;
    }

    /**
     * Instructs robot what to do when this behaviour is suppressed.
     */
    public void suppress() {
        if (suppressAction!=null) {
            suppressAction.suppress();
            suppressAction = null;
        }
    }

    /**
     * Instructs robot what to do when this behaviour is active.
     */
    public void action() {
        onColour = !onColour; // we either moved onto colour, or off of colour so update state appropriately
        if (onColour && onColourAction!=null) {
            onColourAction.action();
            suppressAction = onColourAction;
        }
        else if (!onColour && offColourAction!=null) {
            offColourAction.action();
            suppressAction = offColourAction;
        }
    }

}
