public class Boiler extends Appliance {

    Boiler(int elUse, int gasUse, int watUse){
        super(elUse,gasUse,watUse,-1);
    }

    Boiler(){
        super(0,1,0,-1);
    }

    public void use(){
        //todo
    }
}
