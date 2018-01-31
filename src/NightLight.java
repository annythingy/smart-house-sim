public class NightLight extends Appliance {

    NightLight(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, -1);
    }

    NightLight() {
        super(1, 0, 0, -1);
    }

    @Override
    public void use() {
        //todo
    }
}
