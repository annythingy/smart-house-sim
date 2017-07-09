/**
 * Created by Kikina on 20/05/2017.
 */
public class NightLight extends Appliance {

    public NightLight(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, -1);
    }

    public NightLight() {
        super(1, 0, 0, -1);
    }

    @Override
    public void use() {
    }
}
