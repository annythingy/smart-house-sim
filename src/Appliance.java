/**
 * Created by Kikina on 20/05/2017.
 */
public abstract class Appliance {

    public void setLog(LogPanel log) {
        this.log = log;
    }

    public LogPanel getLog() {
        return log;
    }

    LogPanel log;
    ElectricMeter elMeter;
    WaterMeter waterMeter;
    GasMeter gasMeter;
    protected boolean renewable;
    private int electricityUse;
    private int gasUse;
    private int waterUse;
    protected int electricityProduction;
    protected int gasProduction;
    protected int waterProduction;
    private int timeOn;
    private int timeUse;

    public boolean isCurrentState() {
        return currentState;
    }

    private boolean currentState;
    public void setHouse(House house) {
        this.house = house;
    }

    House house;

    public Appliance(int electricityUse, int gasUse, int waterUse, int timeUse) {
        this.electricityUse = electricityUse;
        this.gasUse = gasUse;
        this.waterUse = waterUse;
        this.timeUse = timeUse;
    }

    public void timePasses() {
        if (currentState) {
            elMeter.incrementConsumed(electricityUse);
            gasMeter.incrementConsumed(gasUse);
            waterMeter.incrementConsumed(waterUse);
            if (renewable) {
                if (elMeter.canGenerate()) elMeter.incrementGenerated(electricityProduction);
                if (gasMeter.canGenerate()) gasMeter.incrementGenerated(gasProduction);
                if (waterMeter.canGenerate()) waterMeter.incrementGenerated(waterProduction);
            }
            if (timeUse > 0) {
                timeOn++;
                if (timeOn == timeUse) {
                    turnOff();
                }
            }
        }
    }

    public void turnOn() {
        currentState = true;


    }

    public void turnOff() {
        currentState = false;
        timeOn = 0;
    }

    public abstract void use();

    public int getTimeUse() {
        return timeUse;
    }

    public void setElMeter(ElectricMeter elMeter) {
        this.elMeter = elMeter;
    }

    public void setWaterMeter(WaterMeter waterMeter) {
        this.waterMeter = waterMeter;
    }

    public void setGasMeter(GasMeter gasMeter) {
        this.gasMeter = gasMeter;
    }

}
