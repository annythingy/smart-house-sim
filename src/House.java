import javax.swing.*;
import java.util.ArrayList;

public class House {

    private HouseTab tab;
    private LogPanel logPanel;
    private String householdName;
    private ElectricMeter electricMeter;
    private GasMeter gasMeter;
    private WaterMeter waterMeter;
    private Meter[] meters;
    private ArrayList<Appliance> appliances;
    private ArrayList<Person> family;
    private int daysPassed;
    private int timePassed;

    public House(String household) {
        this.householdName = household;
        meters = new Meter[3];
        electricMeter = new ElectricMeter(0,0,true);
        gasMeter = new GasMeter(0,0,true);
        waterMeter = new WaterMeter(0,0,true);
        meters[0] = electricMeter;
        meters[1] = gasMeter;
        meters[2] = waterMeter;
        family = new ArrayList<>();
        appliances = new ArrayList<>();
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    String getHouseholdName() {
        return householdName;
    }

    Meter[] getMeters() {
        return meters;
    }

    ArrayList<Person> getFamily() {
        return family;
    }

//    public void go() {
//        int day = 96;
//        try {
//            System.out.println("DAY 1");
//            timePasses(day);
//        } catch (InterruptedException ignored) {
//        }
//    }

    void go(HouseTab tab, int time) {
        try {
            tab.revalidate();
            tab.repaint();
            timePasses(time, tab);
            tab.revalidate();
            tab.repaint();
        } catch (InterruptedException ignored) {
        }
    }

    private void timePasses(int time) throws InterruptedException {
        for (int i = 0; i < time; i++) {
            for (Person pal : family) pal.timePasses();
            for (Appliance app : appliances) app.timePasses();
            timePassed++;
            Thread.sleep(250);
            if (timePassed == 96) {
                timePassed = 0;
                daysPassed++;
            }
            monitorUsage();
        }
    }

    private void timePasses(int time, HouseTab tab) throws InterruptedException {
        for (int i = 0; i < time; i++) {
            for (Person pal : family) pal.timePasses();
            for (Appliance app : appliances) app.timePasses();
            timePassed++;
            Thread.sleep(50);
            if (timePassed == 96) {
                timePassed = 0;
                daysPassed++;
            }
            monitorUsage(tab);
        }
    }

    private void monitorUsage() {
        if (timePassed == 96) System.out.println("DAY " + daysPassed);
        if (timePassed % 4 == 0) {
            System.out.println("Hour " + timePassed / 4 + ":");
            System.out.println("EleUse - " + electricMeter.getConsumed());
            System.out.println("WatUse - " + waterMeter.getConsumed());
            System.out.println("GasUse - " + gasMeter.getConsumed() + "\n");
        }
    }

    private void monitorUsage(HouseTab tab) {
        if (timePassed == 96) System.out.println("DAY " + daysPassed);
        if (timePassed % 4 == 0) {
            setText(tab.getValueNames(), "<html>" + "Day:" + "<br>Hour:" + "<br>Electricity Use:" + "<br>Gas Use:" + "<br>Water Use:" + "</html>");
            setText(tab.getValueNumbers(), "<html>" + daysPassed + "<br>" + timePassed / 4 + "<br>" + electricMeter.getConsumed() + "<br>" + gasMeter.getConsumed() + "<br>" + waterMeter.getConsumed() + "</html>");

            for (int i = 0; i < 1; i++) {
                tab.getMeterLabels()[i].setText("<html>" + meters[i].getType() + " generated - " + meters[i].getGenerated() + "   </html>");
                tab.getMeterLabels()[i].paintImmediately(tab.getMeterLabels()[i].getVisibleRect());
            }
            //logPanel.getTextArea().setCaretPosition(logPanel.getTextArea().getDocument().getLength());
            logPanel.getTextArea().paintImmediately(logPanel.getTextArea().getVisibleRect());
            logPanel.paintImmediately(logPanel.getVisibleRect());
        }
    }

    private void setText(JLabel label, String text) {
        label.setText(text);
        label.paintImmediately(label.getVisibleRect());
    }

    void addPerson(Person pal) {
        family.add(pal);
    }

    void addAppliance(Appliance app) throws Exception {
        if (appliances.size() < 25) {
            appliances.add(app);
            app.setElMeter(getElectricMeter());
            app.setGasMeter(getGasMeter());
            app.setWaterMeter(getWaterMeter());
        } else throw new Exception("You can't have more than 25 appliances");
    }

    public void removeAppliance(Appliance app) throws Exception {
        if (appliances.contains(app)) appliances.remove(app);
        throw new Exception("It wasn't here to begin with.");
    }

    ArrayList<Appliance> getAppliances() {
        return appliances;
    }

    int getTimePassed() {
        return timePassed;
    }

    int numAppliances() {
        return appliances.size();
    }

    private ElectricMeter getElectricMeter() {
        return electricMeter;
    }

    private GasMeter getGasMeter() {
        return gasMeter;
    }

    private WaterMeter getWaterMeter() {
        return waterMeter;
    }

    void setLogPanel(LogPanel logPanel) {
        this.logPanel = logPanel;
    }

    void setTab(HouseTab tab) {
        this.tab = tab;
    }
}
