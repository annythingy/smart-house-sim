/**
 * Created by Kikina on 20/05/2017.
 */
public class Child extends Person {

    public Child(String name, int age, char gender, House house) throws Exception {
        super(name, age, gender, house);
        if (age>= 18) throw new Exception("Not a kiddo anymore :/");
    }
}
