
public abstract class Meter {
    private int consumed;
    private int generated;
    private boolean canGenerate;

    Meter(int consumed, int generated, boolean canGenerate) {
        this.consumed = consumed;
        this.generated = generated;
        this.canGenerate = canGenerate;
    }

    private void setCanGenerate(boolean canGenerate) {
        this.canGenerate = canGenerate;
    }

    public abstract String getType();

    int getConsumed() {
        return consumed;
    }

    private void setConsumed(int consumed) {
        this.consumed = consumed;
    }

    int getGenerated() {
        return generated;
    }

    private void setGenerated(int generated) {
        this.generated = generated;
    }

    public void setValues(int consumed, int generated, boolean canGenerate) {
        setConsumed(consumed);
        setGenerated(generated);
        setCanGenerate(canGenerate);
    }

    void incrementConsumed(int usage) {
        consumed += usage;
    }

    void incrementGenerated(int usage) {
        generated += usage;
    }

    boolean canGenerate() {
        return canGenerate;
    }
}

class ElectricMeter extends Meter {

    ElectricMeter(int consumed, int generated, boolean canGenerate) {
        super(consumed, generated, canGenerate);
    }

    @Override
    public String getType() {
        return "Electricity";
    }
}

class GasMeter extends Meter {

    GasMeter(int consumed, int generated, boolean canGenerate) {
        super(consumed, generated, canGenerate);
    }

    @Override
    public String getType() {
        return "Gas";
    }
}

class WaterMeter extends Meter {

    WaterMeter(int consumed, int generated, boolean canGenerate) {
        super(consumed, generated, canGenerate);
    }

    @Override
    public String getType() {
        return "Water";
    }
}
