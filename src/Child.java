
class Child extends Person {

    Child(String name, int age, char gender, House house) throws Exception {
        super(name, age, gender, house);
        if (age>= 18) throw new Exception("Not a kiddo anymore :/");
    }
}
