/**
 * Created by Kikina on 23/05/2017.
 */
public class SolarPanel extends Appliance {


    public SolarPanel() {
        super(0, 0, 0, -1);
        electricityProduction = 2;
        gasProduction = 0;
        waterProduction = 0;
        renewable = true;
        turnOn();
    }

    @Override
    public void use() {
        sunlightMagic();
    }

    public void sunlightMagic() {
        turnOn();
    }
}
