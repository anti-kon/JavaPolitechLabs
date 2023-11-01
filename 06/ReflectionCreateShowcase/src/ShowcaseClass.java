//Класс ShowcaseClass, используется для демонстрации возможности (с помощью reflection)
// создавать (по имени) экземпляры класса (с помощью public и private конструкторов) и
// выполнять его public и private методы (по имени)
public class ShowcaseClass {
    private final int age;
    private final String name;

    public ShowcaseClass(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private ShowcaseClass(int age) {
        this.age = age;
        name = "standard";
    }

    public String getName() { return name; }
    private int getAge() { return age; }
}