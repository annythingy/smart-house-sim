/**
 * Created by Kikina on 20/05/2017.
 */
public abstract class Shower extends Appliance {

    public Shower(int electricityUse, int gasUse, int waterUse, int timeUse) {
        super(electricityUse, gasUse, waterUse, timeUse);
    }

    public abstract void shower();
}

class ElectricShower extends Shower {

    public ElectricShower(int elUse, int gasUse, int watUse){
        super(elUse, gasUse,watUse,1);
    }

    public ElectricShower(){
        super(12,0,4,1);
    }

    @Override
    public void use() {

    }

    @Override
    public void shower() {

    }
}

class PowerShower extends Shower {

    public PowerShower(int elUse, int gasUse, int watUse){
        super(elUse, gasUse,watUse,1);
    }

    public PowerShower(){
        super(0,10,5,1);
    }

    @Override
    public void use() {
        shower();
    }

    @Override
    public void shower() {
        turnOn();
        log.addRow("Power Showering!!!\n");
    }
}
