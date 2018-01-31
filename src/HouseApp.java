import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HouseApp extends JFrame {

    private ArrayList<House> houses;

    private HouseApp() throws Exception {
        super("This is cute");
        houses = new ArrayList<>();
        setSize(600, 500);
        addInfo();
        addComponents();
        centreWindow(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            HouseApp app = new HouseApp();
            app.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addInfo() throws Exception {
        HouseGenerator generator = new HouseGenerator("src/config.txt");
        if (generator.file.exists()) houses = generator.parseFile();
        else; //todo create config in the same folder
    }

    private void addComponents() {
        JTabbedPane tabs = new JTabbedPane();
        for (House house : houses) {
            tabs.add(house.getHouseholdName(), new HouseTab(house, false));
        }
        tabs.add("Custom family", new HouseTab(new House("Custom"), true));
        setContentPane(tabs);
    }

    private void centreWindow(Window frame) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screen.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}

class HouseTab extends JPanel {
    private House house;
    private boolean customisable;
    private JPanel peoplePanel;
    private JPanel appliancePanel;
    private JLabel[] meterLabels;
    private JLabel valueNames;
    private JLabel valueNumbers;

    HouseTab(House house, boolean customisable) {
        this.house = house;
        this.customisable = customisable;
        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        LogPanel logPanel = new LogPanel(this, house, textArea);
        house.setLogPanel(logPanel);
        house.setTab(this);
        for (Appliance app : house.getAppliances()) {
            app.setTab(this);
        }
        add(logPanel, BorderLayout.CENTER);
        addComponents();
    }

    private void addComponents() {
        addGo();
        addPeople();
        addMeters();
        addAppliances();
    }

    private void addGo() {
        JPanel up = new JPanel();
        JPanel goPanel = new JPanel(new BorderLayout());
        JPanel timePanel = new JPanel(new GridLayout(2, 1));
        up.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton go = new JButton("GO!");
        go.setPreferredSize(new Dimension(200, 90));
        valueNames = new JLabel("<html>" + "Day:" + "<br>Hour:" + "<br>Electricity Use:" + "<br>Gas Use:" + "<br>Water Use:" + "</html>");
        valueNumbers = new JLabel("<html>" + "------" + "<br>" + "------" + "<br>" + "------" + "<br>" + "------" + "<br>" + "------" + "</html>");
        JTextField time = new JTextField(5);
        SpinnerModel hoursModel = new SpinnerNumberModel(12, 1, 24, 1);
        JSpinner hours = new JSpinner(hoursModel);
        time.setFont(new Font(getFont().getName(), Font.BOLD, 10));
        time.setPreferredSize(new Dimension(20, 20));
        timePanel.add(new JLabel("<html>Number<br>of hours:</html>"));
        timePanel.add(hours);
        goPanel.add(timePanel, BorderLayout.WEST);
        goPanel.add(go, BorderLayout.CENTER);
        go.addActionListener(e -> house.go(this, (Integer) hoursModel.getValue()*4));
        up.add(goPanel);
        up.add(valueNames);
        up.add(valueNumbers);
        add(up, BorderLayout.NORTH);
    }

    private void addPeople() {
        JPanel panel = new JPanel(new BorderLayout());
        peoplePanel = new JPanel();
        JButton addPerson = new JButton("<html>Add a<br>family<br>member</html>");
        JButton addTask = new JButton("Add a task");

        peoplePanel.setLayout(new GridLayout(house.getFamily().size() + 1, 1));
        for (Person pal : house.getFamily()) {
            peoplePanel.add(new JLabel(pal.getName(), SwingConstants.RIGHT));
        }
        addPerson.addActionListener(e -> addNewPerson());
        addTask.addActionListener(e -> addNewTask());
        if (customisable) {
            panel.add(addPerson, BorderLayout.NORTH);
            panel.add(addTask);
        }
        panel.add(peoplePanel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel, BorderLayout.WEST);
    }

    private void addMeters() {
        JPanel meterPanel = new JPanel();
        meterLabels = new JLabel[3];

        for (int i = 0; i < 3; i++) {
            meterLabels[i] = new JLabel("<html>" + house.getMeters()[i].getType() + " generated - " + "---------" + "<br></html>");
            meterPanel.add(meterLabels[i]);
        }

        add(meterPanel, BorderLayout.SOUTH);
    }

    private void addAppliances() {
        appliancePanel = new JPanel();
        appliancePanel.setLayout(new GridLayout(house.numAppliances(), 1));
        JButton addAppliance = new JButton("<html>Add a new<br>appliance</html>");

        for (Appliance appliance : house.getAppliances()) {
            appliancePanel.add(new JLabel("<html>" + appliance.getClass().getName() + "<br></html>"));
        }
        if (customisable) appliancePanel.add(addAppliance);
        appliancePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(appliancePanel, BorderLayout.EAST);
    }

    void switchColour(Appliance app, boolean turning) {
        for (Component x : appliancePanel.getComponents()) {
            if (((JLabel) x).getText().contains(app.getClass().getName())) {
                if (!turning) x.setForeground(Color.BLUE);
                else x.setForeground(Color.darkGray);//todo set text back to normal at switch off
            }
        }
        revalidate();
        repaint();
    }

    private void addNewPerson() {
        JDialog popup = new JDialog();
        popup.setSize(300, 150);

        JPanel regPanel = new JPanel(new GridLayout(4, 2));
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField sex = new JTextField();
        JButton add = new JButton("Add");
        JButton cancel = new JButton("Cancel");

        regPanel.add(new JLabel("Name:", SwingConstants.RIGHT));
        regPanel.add(name);
        regPanel.add(new JLabel("Age:", SwingConstants.RIGHT));
        regPanel.add(age);
        regPanel.add(new JLabel("Sex:", SwingConstants.RIGHT));
        regPanel.add(sex);
        regPanel.add(add);
        regPanel.add(cancel);
        popup.add(regPanel);
        popup.setVisible(true);

        add.addActionListener(e -> {
            if (Integer.parseInt(age.getText()) < 18) try {
                house.addPerson(new Child(name.getText(), Integer.parseInt(age.getText()), sex.getText().toCharArray()[0], house));
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            else
                house.addPerson(new GrownUp(name.getText(), Integer.parseInt(age.getText()), sex.getText().toCharArray()[0], house));
            peoplePanel.add(new JLabel(name.getText()));
            peoplePanel.setLayout(new GridLayout(house.getFamily().size() + 1, 1));
            revalidate();
            repaint();
        });
    }

    private void addNewTask() {
        JDialog popup = new JDialog();
        popup.setSize(300, 150);

        JPanel regPanel = new JPanel(new GridLayout(4, 2));
        //JComboBox person = new JComboBox(house.getFamily().toString());
        JMenuItem task = new JMenuItem();
        JButton add = new JButton("Add");
        JButton cancel = new JButton("Cancel");

        regPanel.add(new JLabel("Person:", SwingConstants.RIGHT));
        //regPanel.add(person);
        regPanel.add(new JLabel("Task:", SwingConstants.RIGHT));
        regPanel.add(task);
        regPanel.add(add);
        regPanel.add(cancel);
        popup.add(regPanel);
        popup.setVisible(true);

        add.addActionListener(e -> {

        });
    }

    JLabel[] getMeterLabels() {
        return meterLabels;
    }

    JLabel getValueNames() {
        return valueNames;
    }

    JLabel getValueNumbers() {
        return valueNumbers;
    }
}

class LogPanel extends JScrollPane {

    private HouseTab tab;
    private House house;

    JTextArea getTextArea() {
        return textArea;
    }

    private JTextArea textArea;
    private ArrayList<JLabel> rows;

    LogPanel(HouseTab tab, House house, JTextArea textArea) {
        super(textArea);
        this.textArea = textArea;
        this.tab = tab;
        this.house = house;
        for (Appliance app : house.getAppliances()) {
            app.setLog(this);
        }

        rows = new ArrayList<>();
        addComponents();
    }

    private void addComponents() {
        textArea.setEditable(false);
        //DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.setText("Here we go:\n");
    }

    void addRow(String text) {
        textArea.append(text);
        textArea.paintImmediately(textArea.getVisibleRect());
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
