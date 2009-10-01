import javax.microedition.lcdui.Graphics;

/**
 * Code for the Wombot's LCD Display to be integrated later.
 *
 * @author Cenydd Stott
 * @version 1 October 2009
 */
public class WombotLCD {

  /**
   * The display screen.
   */
  private static Graphics g = new Graphics();

  /**
   * Draw the energy meter and current energy level.
   *
   * @param energy
   *          The current energy level.
   */
  private static void drawEnergy(double energy) {
    g.drawString("WOMBOT", 40, 50);
    g.drawRect(5, 5, 90, 20);
    g.drawString("0", 2, 30, false);
    g.drawString("100", 81, 30, false);
    g.drawString("ENERGY", 30, 30, false);
    g.fillRect(5, 5, (int) (energy * 90), 20);
  }

  /**
   * Draw a neutral face.
   *
   * @param energy
   *          The current energy level.
   */
  public static void drawNeutral(double energy) {
    drawEnergy(energy);
    g.drawLine(10, 59, 19, 59);
    g.drawRect(5, 47, 6, 6);
    g.drawRect(19, 47, 6, 6);
  }

  /**
   * Draw a crazy face.
   *
   * @param energy
   *          The current energy level.
   */
  public static void drawSmile(double energy) {
    drawEnergy(energy);
    g.drawLine(10, 59, 19, 59);
    g.drawLine(6, 55, 9, 58);
    g.drawLine(20, 58, 23, 55);
    g.drawRect(5, 47, 6, 6);
    g.drawRect(19, 47, 6, 6);
  }

  /**
   * Draw a frown face.
   *
   * @param energy
   *          The current energy level.
   */
  public static void drawFrown(double energy) {
    drawEnergy(energy);
    g.drawLine(10, 59, 19, 59);
    g.drawLine(6, 63, 9, 60);
    g.drawLine(20, 60, 23, 63);
    g.drawLine(5, 47, 10, 52);
    g.drawLine(19, 52, 24, 47);
  }

  /**
   * Draw a sleeping face.
   *
   * @param energy
   *          The current energy level.
   */
  public static void drawSleep(double energy) {
    drawEnergy(energy);
    g.drawLine(10, 59, 19, 59);
    g.drawLine(5, 52, 10, 52);
    g.drawLine(19, 52, 24, 52);
    g.drawString("z", 23, 43, false);
    g.drawString("Z", 26, 40, false);
  }

  /**
   * Draw a crazy face.
   *
   * @param energy
   *          The current energy level.
   */
  public static void drawCrazy(double energy) {
    drawEnergy(energy);
    g.drawRect(5, 47, 6, 6);
    g.drawRect(19, 52, 4, 4);
    g.drawRect(4, 55, 19, 8);
  }

}
