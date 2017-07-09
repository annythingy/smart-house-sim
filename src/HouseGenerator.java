import java.io.*;
import java.util.ArrayList;

/**
 * Created by Kikina on 20/05/2017.
 */
public class HouseGenerator {

    File file;
    BufferedReader bfr;
    ArrayList<House> houses;
    House currentHouse;
    Person currentPerson;
    String[] appNames = {"Boiler", "Dishwasher", "ElectricCooker", "ElectricShower", "GasCooker", "Kettle", "NightLight", "PowerShower", "Refrigerator", "TV", "WashingMachine"};

    public HouseGenerator(String path) {
        this.file = new File(path);
        try {
            bfr = new BufferedReader(new FileReader((path)));
            houses = new ArrayList<>();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<House> parseFile() {
        String line;
        try {
            while ((line = bfr.readLine()) != null) {
                String[] columns = line.split(":");
                String type = columns[0];

                if (isHouse(type)) {
                    parseHouse(columns);
                } else if (isAppliance(type)) {
                    parseAppliance(columns);
                } else if (isPerson(type)) {
                    parsePerson(columns);
                } else if (isTask(type)) {
                    parseTask(columns);
                } else throw new Exception("Bad type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houses;
    }

    public void parseHouse(String[] columns) {
        currentHouse = new House(columns[1]);
        houses.add(currentHouse);
    }

    public void parseAppliance(String[] columns) throws Exception {
        int elUse = 0;
        int gasUse = 0;
        int watUse = 0;
        String[] values;

        if (columns.length > 1) {
            values = columns[1].split(",");

            if (values.length == 3) {
                elUse = Integer.parseInt(values[0]);
                gasUse = Integer.parseInt(values[1]);
                watUse = Integer.parseInt(values[2]);
            } else if (values.length == 2) {
                elUse = Integer.parseInt(values[0]);
                gasUse = Integer.parseInt(values[1]);
            } else if (values.length == 1) {
                elUse = Integer.parseInt(values[0]);
            }
        } else values = new String[0];
        switch (columns[0]) {
            case "Boiler":
                if (values.length == 0) currentHouse.addAppliance(new Boiler());
                else currentHouse.addAppliance(new Boiler(elUse,gasUse,watUse));
                break;
            case "Dishwasher":
                if (values.length == 0) currentHouse.addAppliance(new Dishwasher());
                else currentHouse.addAppliance(new Dishwasher(elUse,gasUse,watUse));
                break;
            case "ElectricCooker":
                if (values.length == 0) currentHouse.addAppliance(new ElectricCooker());
                else currentHouse.addAppliance(new ElectricCooker(elUse,gasUse,watUse));
                break;
            case "ElectricShower":
                if (values.length == 0) currentHouse.addAppliance(new ElectricShower());
                else currentHouse.addAppliance(new ElectricShower(elUse,gasUse,watUse));
                break;
            case "GasCooker":
                if (values.length == 0) currentHouse.addAppliance(new GasCooker());
                else currentHouse.addAppliance(new GasCooker(elUse,gasUse,watUse));
                break;
            case "Kettle":
                if (values.length == 0) currentHouse.addAppliance(new Kettle());
                else currentHouse.addAppliance(new Kettle(elUse,gasUse,watUse));
                break;
            case "NightLight":
                if (values.length == 0) currentHouse.addAppliance(new NightLight());
                else currentHouse.addAppliance(new NightLight(elUse,gasUse,watUse));
                break;
            case "PowerShower":
                if (values.length == 0) currentHouse.addAppliance(new PowerShower());
                else currentHouse.addAppliance(new PowerShower(elUse,gasUse,watUse));
                break;
            case "Refrigerator":
                if (values.length == 0) currentHouse.addAppliance(new Refrigerator());
                else currentHouse.addAppliance(new Refrigerator(elUse,gasUse,watUse));
                break;
            case "TV":
                if (values.length == 0) currentHouse.addAppliance(new TV());
                else currentHouse.addAppliance(new TV(elUse,gasUse,watUse));
                break;
            case "WashingMachine":
                if (values.length == 0) currentHouse.addAppliance(new WashingMachine());
                else currentHouse.addAppliance(new WashingMachine(elUse,gasUse,watUse));
                break;
            default:
                throw new Exception("BAD");
        }
    }//todo

    public void parsePerson(String[] info) throws Exception {
        String[] params = info[1].split(",");
        if (Integer.parseInt(params[1]) >= 18)
            currentPerson = new GrownUp(params[0], Integer.parseInt(params[1]), params[2].charAt(0), currentHouse);
        else
            currentPerson = new Child(params[0], Integer.parseInt(params[1]), params[2].charAt(0), currentHouse);
    }

    public void parseTask(String[] info) {
        currentPerson.addTask(Task.valueOf(info[0].toUpperCase()), Integer.parseInt(info[1]));
    }//todo No task/time clashes Exception

    public boolean isHouse(String type) {
        if (type.equals("House")) return true;
        return false;
    }

    public boolean isAppliance(String type) {
        for (String appName : appNames) {
            if (type.equals(appName)) return true;
        }
        return false;
    }

    public boolean isPerson(String type) {
        if (type.equals("Person")) return true;
        return false;
    }

    public boolean isTask(String type) {
        for (Task task : Task.values()) {
            if (type.equals(task.getTaskName())) return true;
        }
        return false;
    }
}
