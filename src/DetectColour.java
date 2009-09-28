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

	/**
	 * Holds a reference to the light sensor port for the robot.
	 */
    private LightSensor light;
    /**
     * Minimum colour used in detection.
     */
    private int colourMin;
    /**
     * Maximum colour used in detection.
     */
    private int colourMax;
    /**
     * The action required when sensor is located over
     * desired colour.
     */
    private Action onColourAction;
    /**
     * The action required when sensor is located off
     * the desired colour.
     */
    private Action offColourAction;
    /**
     * The action to perform when current action is needed
     * to be suppressed.
     */
    private Action suppressAction;
    /**
     * A boolean used to indicated whether the robot is
     * currently on a colour.
     */
    private boolean onColour;

    /**
     * Creates an DetectColour object and allocates the instance variables
     * to those given as parameters.
     * @param light The reference to the light sensor used.
     * @param colourMin The minimum colour value to look for.
     * @param colourMax The maximum colour value to look for.
     * @param onColourAction The action to take when on colour.
     * @param offColourAction The action to take when off colour.
     */
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
     * @return A boolean indicating for the robot to take control.
     */
    public boolean takeControl() {
        // Take action if either:
        // 1. robot wasn't over colour, but now it sees colour; or
        // 2. robot was over colour, but it no longer sees colour
        return onColour != seeColour();
    }

    /**
     * Detects whether it is over the desired colour.
     * @return A boolean boolean value indicating whether the sensor is
     * currently over the desired colour.
     */
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
