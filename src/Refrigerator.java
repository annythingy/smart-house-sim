/**
 * Created by Kikina on 20/05/2017.
 */
public class Refrigerator extends Appliance {

    public Refrigerator(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, -1);
    }

    public Refrigerator() {
        super(1, 0, 0, -1);
        turnOn();
    }

    @Override
    public void use() {

    }
}
