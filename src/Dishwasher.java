/**
 * Created by Kikina on 20/05/2017.
 */
public class Dishwasher extends Appliance {

    public Dishwasher(int elUse, int gasUse, int watUse){
        super(elUse,gasUse,watUse,6);
    }

    public Dishwasher(){
        super(2,0,1,6);
    }

    @Override
    public void use() {
        washDishes();
    }

    public void washDishes() {
        turnOn();
        log.addRow("Dishwasher dishwashes!!!\n");
    }
}
