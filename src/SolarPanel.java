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

    private void sunlightMagic() {
        turnOn();
        //todo
    }
}
