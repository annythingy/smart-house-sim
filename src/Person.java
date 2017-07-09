import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kikina on 20/05/2017.
 */
public abstract class Person {

    private House house;

    public String getName() {
        return name;
    }

    private String name;
    private int age;
    private char gender;
    private HashMap<Integer, Task> tasks;

    public Person(String name, int age, char gender, House house) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.house = house;
        house.addPerson(this);
        tasks = new HashMap<>();
    }

    public void timePasses() {
        if (!tasks.isEmpty()) {
            for (Map.Entry<Integer,Task> plan : tasks.entrySet()) {
                Task task = plan.getValue();
                int timeToDo = plan.getKey();
                if (house.getTimePassed() == timeToDo) task.doTask(house);
            }
        }
    }

    public void addTask(Task task, int time) {
        tasks.put(time,task);
    }
}
