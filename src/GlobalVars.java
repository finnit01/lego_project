/**
 * Contains global variables for various energy settings.
 *
 * @author Malcolm King
 * @version 0.4 (September 2009)
 */
public class GlobalVars {

  private static int energy = 2000;
  public static int yellow;
  public static int orange;
  public static int blue;
  public static int green;

  /**
   * Get the current energy of the NXT.
   * @return energy
   */
  public static int getEnergy() {
    return energy;
  }

  /**
   * Set the energy of the NXT
   * @param value the value to set the energy to.
   */
  public static void setEnergy(int value) {
    energy = value;
  }

}
