public class Kettle extends Appliance {
    
    public Kettle(int elUse, int gasUse, int watUse){
        super(elUse,gasUse,watUse,1);
    }
    
    public Kettle(){
        super(20,0,1,1);
    }
    
    @Override
    public void use() {
        boil();
    }

    private void boil() {
        turnOn();
        log.addRow("Kettle Kettling!!!\n");
    }
}
