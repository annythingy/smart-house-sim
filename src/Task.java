/**
 * Created by Kikina on 20/05/2017.
 */
public enum Task {
    BOIL(Kettle.class, "Boil"),
    COOK(Cooker.class, "Cook"),
    SHOWER(Shower.class, "Shower"),
    DOWASHING(WashingMachine.class, "DoWashing"),
    WASHDISHES(Dishwasher.class, "WashDishes"),
    TURNONTV(TV.class, "TurnOnTV", true),
    TURNOFFTV(TV.class, "TurnOffTV", false),
    TURNBOILERON(Boiler.class, "TurnOnBoiler", true),
    TURNBOILEROFF(Boiler.class, "TurnOffBoiler", false),
    TURNNIGHTLIGHTON(NightLight.class, "TurnOffNightLight", true),
    TURNNIGHTLIGHTOFF(NightLight.class, "TurnOffNightLight", false);

    private Class appClass;
    private String taskName;
    private boolean switchState;

    Task(Class appClass, String taskName) {
        this.appClass = appClass;
        this.taskName = taskName;
    }

    Task(Class appClass, String taskName, boolean switchState) {
        this.appClass = appClass;
        this.taskName = taskName;
        this.switchState = switchState;
    }

    public void doTask(House house) {
        for (Appliance appliance : house.getAppliances()) {
            if (appClass.isInstance(appliance)) {
                if (appliance.getTimeUse() > 0) appliance.use();
                else turnOnOff(appliance);
                break;
            }
        }
    }

    public String getTaskName() {
        return taskName;
    }

    private void turnOnOff(Appliance appliance) {
        if (switchState) appliance.turnOn();
        else appliance.turnOff();
        if(appliance.isCurrentState()) appliance.getLog().addRow(appliance.getClass().getName() + " is on" + "\n");
        else appliance.getLog().addRow(appliance.getClass().getName() + " is off" + "\n");
    }
}
