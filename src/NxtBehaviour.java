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

        // set up EnergyLevel object to keep track of current energy level
        // start with full energy (1.0);
        EnergyLevel energyLevel = new EnergyLevel(1.0);

        // ask for green sample
        LCD.clear();
        LCD.drawString("Green paper",0,0);
        Button.waitForPress();

        // read it
        int green = light.readNormalizedValue();

        // display it
        LCD.clear();
        LCD.drawInt(green, 0, 0);

        // create the detectGreen behaviour with it
        Behavior detectGreen = new DetectColour(
                light,
                green-20,
                green+20,
                new RestoreFullEnergyAction(energyLevel),
                new TwoBeepsAction());

        // wait for sound
        while (sound.readValue() < 40) {
            // waiting
        }

        // combine the DriveForward and DrainEnergy actions so that both can
        // run as one action
        CombinedAction driveAndLoseEnergy = new CombinedAction(
            // pass the pilot and energyLevel which it will
            // monitor for changes and adjust speed accordingly
            new DriveForwardAction(pilot, energyLevel),
            // pass the energyLevel so it can decrease it at
            // regular intervals
            new DrainEnergyAction(energyLevel)
            );

        DefaultBehaviour defaultBehaviour = new DefaultBehaviour(
            driveAndLoseEnergy
            );

        // setup, start the Arbitrator
        Behavior[] behaviours = {defaultBehaviour, detectGreen};
        Arbitrator arbitrator = new Arbitrator(behaviours);
        arbitrator.start();
    }

}
