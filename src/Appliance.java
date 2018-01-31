public abstract class Appliance {

    boolean renewable;
    int electricityProduction;
    int gasProduction;
    int waterProduction;
    private HouseTab tab;
    LogPanel log;
    private ElectricMeter elMeter;
    private WaterMeter waterMeter;
    private GasMeter gasMeter;
    private int electricityUse;
    private int gasUse;
    private int waterUse;
    private int timeOn;
    private int timeUse;
    private boolean currentState;

    public Appliance(int electricityUse, int gasUse, int waterUse, int timeUse) {
        this.electricityUse = electricityUse;
        this.gasUse = gasUse;
        this.waterUse = waterUse;
        this.timeUse = timeUse;
    }

    LogPanel getLog() {
        return log;
    }

    void setLog(LogPanel log) {
        this.log = log;
    }

    void setTab(HouseTab tab) {
        this.tab = tab;
    }

    boolean isCurrentState() {
        return currentState;
    }

    void timePasses() {
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

    void turnOn() {
        if (tab != null) tab.switchColour(this, true);
        currentState = true;

    }

    void turnOff() {
        tab.switchColour(this, false);
        currentState = false;
        timeOn = 0;

    }

    public abstract void use();

    int getTimeUse() {
        return timeUse;
    }

    void setElMeter(ElectricMeter elMeter) {
        this.elMeter = elMeter;
    }

    void setWaterMeter(WaterMeter waterMeter) {
        this.waterMeter = waterMeter;
    }

    void setGasMeter(GasMeter gasMeter) {
        this.gasMeter = gasMeter;
    }

}
