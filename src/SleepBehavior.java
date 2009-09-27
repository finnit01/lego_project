import lejos.subsumption.Behavior;

public class SleepBehavior implements Behavior {

    Action action;
    EnergyLevel energyLevel;

    public SleepBehavior(Action action, EnergyLevel energyLevel) {
        if (action==null) throw new NullPointerException("action cannot be null.");

        this.energyLevel = energyLevel;
        this.action = action;
    }

    public boolean takeControl() {
        return energyLevel.getEnergyLevel() <= 0;
    }

    public void suppress() {
        action.suppress();
    }

    public void action() {
        action.action();
    }

}
