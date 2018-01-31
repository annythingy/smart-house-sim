public abstract class Cooker extends Appliance {

    Cooker(int electricityUse, int gasUse, int waterUse, int timeUse) {
        super(electricityUse, gasUse, waterUse, timeUse);
    }

    public abstract void cook();
}

class ElectricCooker extends Cooker {

    ElectricCooker(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, 4);
    }

    ElectricCooker() {
        super(5, 0, 0, 4);
    }

    @Override
    public void use() {
        cook();
    }

    @Override
    public void cook() {
        //todo
    }
}

class GasCooker extends Cooker {

    GasCooker(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, 4);
    }

    GasCooker() {
        super(0, 4, 0, 4);
    }

    @Override
    public void use() {
        cook();
    }

    @Override
    public void cook() {
        //todo
    }
}
