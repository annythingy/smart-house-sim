/**
 * Created by Kikina on 20/05/2017.
 */
public abstract class Cooker extends Appliance {

    public Cooker(int electricityUse, int gasUse, int waterUse, int timeUse) {
        super(electricityUse, gasUse, waterUse, timeUse);
    }

    public abstract void cook();
}

class ElectricCooker extends Cooker {

    public ElectricCooker(int elUse, int gasUse, int watUse){
        super(elUse, gasUse,watUse,4);
    }

    public ElectricCooker(){
        super(5,0,0,4);
    }

    @Override
    public void use() {
        cook();
    }

    @Override
    public void cook() {

    }
}

class GasCooker extends Cooker {

    public GasCooker(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, 4);
    }

    public GasCooker() {
        super(0,4,0,4);
    }

    @Override
    public void use() {
        FKNowrk();
    }

    @Override
    public void cook() {

    }

    public void FKNowrk(){
        System.out.println("Fu");
    }
}
