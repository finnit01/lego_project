import lejos.navigation.TachoPilot;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.subsumption.Arbitrator;
import lejos.subsumption.Behavior;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.SoundSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.Button;
import lejos.nxt.LCD;

/**
 * Handles the behaviours for the NXT robot.
 *
 * @author Timothy Black, Malcolm King
 * @version September 2009 (0.4)
 */
public class NxtBehaviour {

  static final Motor LEFT_MOTOR = Motor.C;
  static final Motor RIGHT_MOTOR = Motor.A;
  static final int WHEEL_DIAMETER = 56;
  static final int TRACK_DISTANCE = 122;
  static final SensorPort SONIC_PORT = SensorPort.S1;
  static final SensorPort LIGHT_PORT = SensorPort.S4;
  static final SensorPort SOUND_PORT = SensorPort.S3;
  static final SensorPort TOUCH_PORT = SensorPort.S2;

  TachoPilot pilot;
  UltrasonicSensor sonic;
  TouchSensor touch;
  LightSensor light;
  SoundSensor sound;

  /**
   * Creates the NxtBehaviour class, and adds the desired behaviours to an
   * arbitrator object.
   */
  public NxtBehaviour() {

    /*
     * Initialise motors and sensors.
     */
    pilot = new TachoPilot(WHEEL_DIAMETER, TRACK_DISTANCE, LEFT_MOTOR,
        RIGHT_MOTOR);
    pilot.setMoveSpeed(GlobalVars.getDefaultMoveSpeed());
    sonic = new UltrasonicSensor(SONIC_PORT);
    light = new LightSensor(LIGHT_PORT);
    sound = new SoundSensor(SOUND_PORT, true);
    // touch = new TouchSensor(TOUCH_PORT);

    /*
     * Calibrate light sensor.
     */
    LCD.clear();
    LCD.drawString("Green paper",0,0);
    Button.waitForPress();
    GlobalVars.setGreenRange(light.readNormalizedValue());
    LCD.clear();
    LCD.drawString("Yellow paper",0,0);
    Button.waitForPress();
    GlobalVars.setYellowRange(light.readNormalizedValue());
    LCD.clear();
    LCD.drawString("Blue paper",0,0);
    Button.waitForPress();
    GlobalVars.setBlueRange(light.readNormalizedValue());

    // TODO for testing purposes
    LCD.clear();
    LCD.drawString("Green "+GlobalVars.green, 0, 0);
    LCD.drawString("Yellow "+GlobalVars.yellow, 0, 1);
    LCD.drawString("Blue "+GlobalVars.blue, 0, 2);

    LCD.drawString("Clap to start", 0, 4);
    Sound.beepSequence();

    // Wait for a clap.
    while (sound.readValue() < 60) {

    }

    /* TODO
     * Change the order of behaviours in the array below depending
     * on importance. High array index indicates high importance
     * while low index indicates low importance. NOTE: StandardDrive must be the
     * lowest indexed behaviour.
     */
    Behavior[] behaviours = {
        new StandardDrive(pilot),
        new DetectBlue(pilot, light),
        new DetectYellow(pilot, light),
        new DetectGreen(pilot, light),
        new AvoidObstacle(pilot, sonic),
        new Sleep(pilot),
        new DetectSoundWhileSleeping(sound)
    };
    Arbitrator behaviourSystem = new Arbitrator(behaviours);
    behaviourSystem.start();
  }

  public static void main(String[] args) {
    new NxtBehaviour();
  }

}
