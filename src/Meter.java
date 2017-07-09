import java.util.Map;

/**
 * Created by Kikina on 20/05/2017.
 */
public abstract class Meter {
    private int consumed;

    public void setConsumed(int consumed) {
        this.consumed = consumed;
    }

    public void setGenerated(int generated) {
        this.generated = generated;
    }

    public void setCanGenerate(boolean canGenerate) {
        this.canGenerate = canGenerate;
    }

    private int generated;
    private boolean canGenerate;

    public Meter(int consumed, int generated, boolean canGenerate) {
        this.consumed = consumed;
        this.generated = generated;
        this.canGenerate = canGenerate;
    }

    public abstract String getType();

    public int getConsumed() {
        return consumed;
    }

    public int getGenerated() {
        return generated;
    }

    public void setValues(int consumed,int generated,boolean canGenerate){
        setConsumed(consumed);
        setGenerated(generated);
        setCanGenerate(canGenerate);
    }

    public void incrementConsumed(int usage) {
        consumed += usage;
    }

    public void incrementGenerated(int usage) {
        generated += usage;
    }

    public boolean canGenerate() {
        return canGenerate;
    }
}

class ElectricMeter extends Meter {

    private String type = "Electricity";

    public ElectricMeter(int consumed, int generated, boolean canGenerate) {
        super(consumed, generated, canGenerate);
    }

    @Override
    public String getType() {
        return type;
    }
}

class GasMeter extends Meter {

    private String type = "Gas";

    public GasMeter(int consumed, int generated, boolean canGenerate) {
        super(consumed, generated, canGenerate);
    }

    @Override
    public String getType() {
        return type;
    }
}

class WaterMeter extends Meter {

    private String type = "Water";

    public WaterMeter(int consumed, int generated, boolean canGenerate) {
        super(consumed, generated, canGenerate);
    }

    @Override
    public String getType() {
        return type;
    }
}
