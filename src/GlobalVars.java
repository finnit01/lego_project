/**
 * Contains global variables for various energy settings.
 *
 * @author Malcolm King
 * @version 0.4 (September 2009)
 */
public class GlobalVars {

  private static int energy = 500;

  private static int defaultMoveSpeed = 60;

  public static int yellow;
  public static int yellowMin;
  public static int yellowMax;

  public static int green;
  public static int greenMin;
  public static int greenMax;

  public static int blue;
  public static int blueMin;
  public static int blueMax;

  private static final int TOLERANCE = 15;

  /**
   * Get the current energy of the NXT.
   * @return energy
   */
  public static int getEnergy() {
    return energy;
  }

  public static int getDefaultMoveSpeed() {
    return defaultMoveSpeed;
  }

  /**
   * Set the energy of the NXT
   * @param value the value to set the energy to.
   */
  public static void setEnergy(int value) {
    energy = value;
  }

  public static void setYellowRange(int value) {
    yellow = value;
    yellowMin = value - TOLERANCE;
    yellowMax = value + TOLERANCE;
  }

  public static void setGreenRange(int value) {
    green = value;
    greenMin = value - TOLERANCE;
    greenMax = value + TOLERANCE;
  }

  public static void setBlueRange(int value) {
    blue = value;
    blueMin = value - TOLERANCE;
    blueMax = value + TOLERANCE;
  }

  public static void sleepSong() {
    short[] note = {2349,115, 0,5, 1760,165, 0,35};

    for(int i=0;i <note.length; i+=2) {
      short w = note[i+1];
      int n = note[i];
      if (n != 0) lejos.nxt.Sound.playTone(n, w*10);
      lejos.nxt.Sound.pause(w*10);
    }
  }

  public static void greenSong() {
    short[] note = {4000,115, 0,20, 8000,200, 0,35};

    for(int i=0;i <note.length; i+=2) {
      short w = note[i+1];
      int n = note[i];
      if (n != 0) lejos.nxt.Sound.playTone(n, w*10);
      lejos.nxt.Sound.pause(w*10);
    }
  }

}
