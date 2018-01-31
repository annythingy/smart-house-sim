import java.io.*;
import java.util.ArrayList;

class HouseGenerator {

    File file;
    private BufferedReader bfr;
    private ArrayList<House> houses;
    private House currentHouse;
    private Person currentPerson;
    private String[] appNames = {"Boiler", "Dishwasher", "ElectricCooker", "ElectricShower", "GasCooker", "Kettle", "NightLight", "PowerShower", "Refrigerator", "TV", "WashingMachine"};

    HouseGenerator(String path) {
        this.file = new File(path);
        try {
            bfr = new BufferedReader(new FileReader((path)));
            houses = new ArrayList<>();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    ArrayList<House> parseFile() {
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
                } else if (isComment(type)) {

                } else throw new Exception("Bad type " + type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houses;
    }

    private void parseHouse(String[] columns) {
        currentHouse = new House(columns[1]);
        houses.add(currentHouse);
    }

    private void parseAppliance(String[] columns) throws Exception {
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
                else currentHouse.addAppliance(new Boiler(elUse, gasUse, watUse));
                break;
            case "Dishwasher":
                if (values.length == 0) currentHouse.addAppliance(new Dishwasher());
                else currentHouse.addAppliance(new Dishwasher(elUse, gasUse, watUse));
                break;
            case "ElectricCooker":
                if (values.length == 0) currentHouse.addAppliance(new ElectricCooker());
                else currentHouse.addAppliance(new ElectricCooker(elUse, gasUse, watUse));
                break;
            case "ElectricShower":
                if (values.length == 0) currentHouse.addAppliance(new ElectricShower());
                else currentHouse.addAppliance(new ElectricShower(elUse, gasUse, watUse));
                break;
            case "GasCooker":
                if (values.length == 0) currentHouse.addAppliance(new GasCooker());
                else currentHouse.addAppliance(new GasCooker(elUse, gasUse, watUse));
                break;
            case "Kettle":
                if (values.length == 0) currentHouse.addAppliance(new Kettle());
                else currentHouse.addAppliance(new Kettle(elUse, gasUse, watUse));
                break;
            case "NightLight":
                if (values.length == 0) currentHouse.addAppliance(new NightLight());
                else currentHouse.addAppliance(new NightLight(elUse, gasUse, watUse));
                break;
            case "PowerShower":
                if (values.length == 0) currentHouse.addAppliance(new PowerShower());
                else currentHouse.addAppliance(new PowerShower(elUse, gasUse, watUse));
                break;
            case "Refrigerator":
                if (values.length == 0) currentHouse.addAppliance(new Refrigerator());
                else currentHouse.addAppliance(new Refrigerator(elUse, gasUse, watUse));
                break;
            case "TV":
                if (values.length == 0) currentHouse.addAppliance(new TV());
                else currentHouse.addAppliance(new TV(elUse, gasUse, watUse));
                break;
            case "WashingMachine":
                if (values.length == 0) currentHouse.addAppliance(new WashingMachine());
                else currentHouse.addAppliance(new WashingMachine(elUse, gasUse, watUse));
                break;
            default:
                throw new Exception("BAD");
        }
    }//todo

    private void parsePerson(String[] info) throws Exception {
        String[] params = info[1].split(",");
        if (Integer.parseInt(params[1]) >= 18)
            currentPerson = new GrownUp(params[0], Integer.parseInt(params[1]), params[2].charAt(0), currentHouse);
        else
            currentPerson = new Child(params[0], Integer.parseInt(params[1]), params[2].charAt(0), currentHouse);
    }

    private void parseTask(String[] info) {
        currentPerson.addTask(Task.valueOf(info[0].toUpperCase()), Integer.parseInt(info[1]));
    }//todo No task/time clashes Exception

    private boolean isHouse(String type) {
        return type.equals("House");
    }

    private boolean isAppliance(String type) {
        for (String appName : appNames) {
            if (type.equals(appName)) return true;
        }
        return false;
    }

    private boolean isPerson(String type) {
        return type.equals("Person");
    }

    private boolean isTask(String type) {
        for (Task task : Task.values()) {
            if (type.equals(task.getTaskName())) return true;
        }
        return false;
    }

    private boolean isComment(String type) {
        return (type.toCharArray().length == 0 || type.toCharArray()[0] == '#');
    }
}
