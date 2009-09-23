import lejos.navigation.TachoPilot;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.subsumption.Arbitrator;
import lejos.subsumption.Behavior;
import lejos.nxt.SoundSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.LCD;

/**
 * Handles the behaviours for the NXT robot.
 *
 * @author Timothy Black, Malcolm King, Trevor Finnie
 * @version September 2009 (0.4)
 */
public class NxtBehaviour {

    static final Motor LEFT_MOTOR = Motor.C;
    static final Motor RIGHT_MOTOR = Motor.A;
    static final int WHEEL_DIAMETER = 56;
    static final int TRACK_DISTANCE = 110;
    static final SensorPort SONIC_PORT = SensorPort.S1;
    static final SensorPort LIGHT_PORT = SensorPort.S4;
    static final SensorPort SOUND_PORT = SensorPort.S3;
    static final SensorPort TOUCH_PORT = SensorPort.S2;

    public static void main(String[] args) {

        // use a TachoPilot to control motors
        TachoPilot pilot = new TachoPilot(WHEEL_DIAMETER, TRACK_DISTANCE, LEFT_MOTOR, RIGHT_MOTOR);

        // create sensor objects
        LightSensor light = new LightSensor(LIGHT_PORT);
        SoundSensor sound = new SoundSensor(SOUND_PORT, true);

        // ask for yellow sample
        LCD.clear();
        LCD.drawString("Yellow paper",0,0);
        Button.waitForPress();

        // read it
        int yellow = light.readNormalizedValue();

        // display it
        LCD.clear();
        LCD.drawInt(yellow, 0, 0);

        // create the detectYellow behaviour with it
        Behavior detectYellow = new DetectColour(
                light,
                yellow-20,
                yellow+20,
                new BeepAction(),
                new TwoBeepsAction(), null);

        // wait for sound
        while (sound.readValue() < 80) {
            // waiting
        }

        // setup, start the Arbitrator
        Behavior[] behaviours = {new StandardDrive(pilot), detectYellow};
        Arbitrator arbitrator = new Arbitrator(behaviours);
        arbitrator.start();
    }

}
