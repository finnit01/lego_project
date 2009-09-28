//import lejos.nxt.Sound;

public class LoseEnergyAction implements Action {

  private EnergyLevel energy;

  public LoseEnergyAction(EnergyLevel energy) {
    this.energy = energy;
  }

  /**
   * Reduce the energy level to half of current energy level.
   */
  public void action() {
    energy.setEnergyLevel(energy.getEnergyLevel()/2);
  }

  public void suppress() {
    // nothing to suppress
  }
}
