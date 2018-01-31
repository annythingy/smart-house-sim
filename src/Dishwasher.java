
public class Dishwasher extends Appliance {

    Dishwasher(int elUse, int gasUse, int watUse){
        super(elUse,gasUse,watUse,6);
    }

    Dishwasher(){
        super(2,0,1,6);
    }

    @Override
    public void use() {
        washDishes();

    }

    private void washDishes() {
        turnOn();
        log.addRow("Dishwasher is washing dishes!!!\n");
    }
}
