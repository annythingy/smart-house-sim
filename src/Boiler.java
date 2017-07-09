/**
 * Created by Kikina on 20/05/2017.
 */
public class Boiler extends Appliance {

    House house;

    public Boiler(int elUse, int gasUse, int watUse){
        super(elUse,gasUse,watUse,-1);
    }

    public Boiler(){
        super(0,1,0,-1);
    }

    public void use(){
        //todo
    }
}
