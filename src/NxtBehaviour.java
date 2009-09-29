import lejos.navigation.TachoPilot;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.subsumption.Arbitrator;
import lejos.subsumption.Behavior;
import lejos.nxt.SoundSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.LCD;

/**
 * Handles the behaviours for the NXT robot.
 *
 * @author Timothy Black, Malcolm King, Trevor Finnie
 * @version September 2009 (0.4)
 */
public class NxtBehaviour {

  /**
   * Static variable for left motor port.
   */
  static final Motor LEFT_MOTOR = Motor.C;
  /**
   * Static variable for right motor port.
   */
  static final Motor RIGHT_MOTOR = Motor.A;
  /**
   * Static variable for the wheel diameter of the robot.
   */
  static final int WHEEL_DIAMETER = 56;
  /**
   * Static variable for the track distance of the robot.
   */
  static final int TRACK_DISTANCE = 119;
  /**
   * Static variable for the ultra-sonic sensor port.
   */
  static final SensorPort SONIC_PORT = SensorPort.S1;
  /**
   * Static variable for the light sensor port.
   */
  static final SensorPort LIGHT_PORT = SensorPort.S4;
  /**
   * Static variable for the sound sensor port.
   */
  static final SensorPort SOUND_PORT = SensorPort.S3;
  /**
   * Static variable for the touch sensor port.
   */
  static final SensorPort TOUCH_PORT = SensorPort.S2;

  /**
   * Displays the current light sensor value on the NXT LCD screen and returns
   * it.
   *
   * @param light
   *          The light sensor port.
   * @param prompt
   *          User message displayed prior to displaying the light sensor value.
   * @return The current value of the light sensor.
   */
  private static int getColourSample(LightSensor light, SoundSensor sound, String prompt) {

    // ask for sample
    LCD.clear();
    LCD.drawString(prompt, 0, 0);
    //Button.waitForPress();
    while (sound.readValue() < 40) {}

    // read it
    int colour = light.readNormalizedValue();

    // display it
    LCD.clear();
    LCD.drawInt(colour, 0, 0);

    return colour;
  }

  /**
   * Sets up and runs the main interfaces associated with the NXT robot.
   *
   * @param args
   *          Any arguments that is needed to be passed to the robot.
   */
  public static void main(String[] args) {

    // use a TachoPilot to control motors
    TachoPilot pilot = new TachoPilot(WHEEL_DIAMETER, TRACK_DISTANCE,
        LEFT_MOTOR, RIGHT_MOTOR);

    // create sensor objects
    LightSensor light = new LightSensor(LIGHT_PORT);
    SoundSensor sound = new SoundSensor(SOUND_PORT, true);
    UltrasonicSensor sonic = new UltrasonicSensor(SONIC_PORT);

    // set up EnergyLevel object to keep track of current energy level
    // start with full energy (1.0);
    EnergyLevel energyLevel = new EnergyLevel(1.0);

    int green = getColourSample(light, sound, "Green Paper");
    int yellow = getColourSample(light, sound, "Yellow Paper");
    int red = getColourSample(light, sound, "Red Paper");

    Action restoreEnergyAndBeep = new CombinedAction(
        new RestoreFullEnergyAction(energyLevel), new BeepAction());

    // create the detectGreen behaviour
    Behavior detectGreen = new DetectColour(light, green - 20, green + 20,
        restoreEnergyAndBeep, null);

    Behavior detectYellow = new DetectColour(light, yellow - 20, yellow + 20,
        new GoCrazyAction(pilot), null);

    Behavior detectRed = new DetectColour(light, red - 20, red + 20,
        new LoseEnergyAction(energyLevel), null);

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
        new DrainEnergyAction(energyLevel));

    CombinedAction sleepAndGainEnergy = new CombinedAction(new SleepAction(
        sound), restoreEnergyAndBeep);

    SleepBehavior sleepBehavior = new SleepBehavior(sleepAndGainEnergy,
        energyLevel);

    AvoidObstaclesBehavior avoidObstaclesBehavior = new AvoidObstaclesBehavior(
        sonic, pilot);

    DefaultBehaviour defaultBehaviour = new DefaultBehaviour(driveAndLoseEnergy);

    // setup, start the Arbitrator
    Behavior[] behaviours = { defaultBehaviour, detectGreen, detectYellow,
        detectRed, avoidObstaclesBehavior, sleepBehavior };
    Arbitrator arbitrator = new Arbitrator(behaviours);
    arbitrator.start();
  }

}
