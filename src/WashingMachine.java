public class WashingMachine extends Appliance {

    public WashingMachine(int elUse, int gasUse, int watUse) {
        super(elUse, gasUse, watUse, 8);
    }

    public WashingMachine() {
        super(2, 0, 1, 8);
    }

    @Override
    public void use() {
        doWashing();
    }

    private void doWashing() {
        turnOn();
        log.addRow("Washing machine WAshEnMaSCheN!!!\n");
    }

}
