import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Scanner;

public class ReflectionInfoShowcase {
    //Демонстрация работы метода printClassInfo, печатающего через reflection полную информацию о классе,
    // переданном в качестве параметра, на примере пользовательского класса ShowcaseClass и
    // встроенных классах Scanner и Math
    public static void main(String[] args) {
        printClassInfo (ShowcaseClass.class);
        printClassInfo (Scanner.class);
        printClassInfo(Math.class);
    }

    // Метод выводящий в консольный поток с помощью reflection полную информацию о классе,
    // переданном в качестве параметра
    public static void printClassInfo(Class<?> printClass) {
        // Печатаем модификаторы доступа и имя класса
        System.out.println("Class: " + printClass.accessFlags() + " " + printClass.getCanonicalName());
        // Печатаем родительский класс
        System.out.println("Superclass: " + printClass.getSuperclass());
        System.out.print("Interfaces:");
        // Печатаем используемые интерфейсы или "null" если их нет
        if (printClass.getInterfaces().length == 0)
            System.out.println(" null");
        else {
            System.out.print("\n");
            for (Class<?> printInterface : printClass.getInterfaces())
                System.out.println("\t" + printInterface.getCanonicalName());
        }
        // Печатаем объявленные поля класса в следующем виде: модификаторы доступа, тип данных, имя поля.
        // Если у класса нет объявленных полей, печатаем "null"
        System.out.print("Field:");
        if (printClass.getDeclaredFields().length == 0)
            System.out.println(" null");
        else {
            System.out.print("\n");
            for (Field printField : printClass.getDeclaredFields())
                System.out.println("\t" + printField.accessFlags() + " " +
                        printField.getType() + " " +
                        printField.getName());
        }
        // Печатаем объявленные конструкторы класса
        System.out.println("Constructors:");
            for (Constructor<?> printConstructors : printClass.getDeclaredConstructors())
                System.out.println("\t" + printConstructors.toString());
        // Печатаем объявленные конструкторы класса или "null" если их нет
        System.out.print("Methods:");
        if (printClass.getDeclaredMethods().length == 0)
            System.out.println(" null");
        else {
            System.out.print("\n");
            for (Method printMethods : printClass.getDeclaredMethods())
                System.out.println("\t" + printMethods.toString());
        }
        // Печатаем объявленные внутренние классы или интерфейсы или "null" если их нет
        System.out.print("Nested classes (interfaces):");
        if (printClass.getDeclaredClasses().length == 0)
            System.out.println(" null");
        else {
            System.out.print("\n");
            for (Class<?> printNestClass : printClass.getDeclaredClasses())
                System.out.println("\t" + printNestClass.toString());
        }
        // Печатаем перечисления класса или "null" если их нет
        System.out.print("Enum constants:");
        if (printClass.getEnumConstants() == null)
            System.out.println(" null");
        else {
            System.out.print("\n");
            for (Object printEnumConstant : printClass.getEnumConstants())
                System.out.println("\t" + printEnumConstant.toString());
        }
        // Печатаем объявленные аннотации класса или "null" если их нет
        System.out.print("Annotations:");
        if (printClass.getDeclaredAnnotations().length == 0)
            System.out.println(" null");
        else {
            System.out.print("\n");
            for (Annotation printAnnotation : printClass.getDeclaredAnnotations())
                System.out.println("\t" + printAnnotation.toString());
        }
    }

    //Класс, содержащий только стандартный конструктор, наследующий встроенный класс исключений Exception.
    // Используется для демонстрации работы метода printClassInfo
    static class ShowcaseClass extends Exception {}
}