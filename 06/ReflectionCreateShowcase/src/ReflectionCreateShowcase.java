import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionCreateShowcase {
    //Демонстрация создания (по имени) экземпляров класса (с помощью public и private конструкторов) и
    // выполнения его public и private методов (по имени)
    public static void main(String[] args) {
        try {
            //Поиск класса по ассоциируемому имени className
            Class<?> showcaseClass = Class.forName("ShowcaseClass");

            //Создание (по имени) экземпляра класса с помощью public конструктора
            Object firstInstance = showcaseClass.getConstructor(int.class, String.class)
                                   .newInstance(12, "firstInstance");

            //Вызов public метода (по имени) для экземпляра класс, созданного с помощью public конструктора
            System.out.println(showcaseClass.getMethod("getName").invoke(firstInstance));

            //Поиск метода у класса showcaseClass по ассоциируемому имени метода name
            Method privateClassMethod = showcaseClass.getDeclaredMethod("getAge");
            //Установить возможным вызов private метода вне класса
            privateClassMethod.setAccessible(true);
            //Вызов private метода (по имени) для экземпляра класс, созданного с помощью public конструктора
            System.out.println(privateClassMethod.invoke(firstInstance));

            //Поиск конструктора у класса showcaseClass соответствующего переданному списку параметров parameterTypes
            Constructor<ShowcaseClass> classConstructor = ShowcaseClass.class.getDeclaredConstructor(int.class);
            //Установить возможным вызов private конструктора вне класса
            classConstructor.setAccessible(true);
            //Создание (по имени) экземпляра класса с помощью private конструктора
            ShowcaseClass secondInstance = classConstructor.newInstance(10);

            //Вызов public метода (по имени) для экземпляра класс, созданного с помощью private конструктора
            System.out.println(showcaseClass.getMethod("getName").invoke(secondInstance));

            //Вызов private метода (по имени) для экземпляра класс, созданного с помощью private конструктора
            System.out.println(privateClassMethod.invoke(secondInstance));
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
