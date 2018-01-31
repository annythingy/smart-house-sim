public class TV extends Appliance {

    public TV(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, -1);
    }

    public TV(){
        super(1,0,0,-1);
    }

    @Override
    public void use() {
        //todo
    }
}
