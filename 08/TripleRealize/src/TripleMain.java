public class TripleMain {

    //Тестирование пользовательского класс тройки (Triple <T>)
    public static void main(String[] args) {
        //Инициализируем тройку целочисленных чисел (в обёртке Integer)
        Triple<Integer> intengerTriple = new Triple<>(1, 2, 3);
        //Распечатаем тройку целочисленных чисел
        System.out.println("Integer triple: " + intengerTriple);
        //Распечатаем наименьший по значению элемент из тройки целочисленных чисел, если сравнение невозможно,
        // распечатаем сообщение об ошибке
        try { System.out.println("Min in integer triple: " + intengerTriple.min()); }
        catch (Exception e) { System.out.println(e.getMessage()); }
        //Распечатаем средний по значению элемент из тройки целочисленных чисел, если сравнение невозможно,
        // распечатаем сообщение об ошибке
        try { System.out.println("Mean in integer triple: " + intengerTriple.mean()); }
        catch (UndefinedComponentException e) { System.out.println(e.getMessage()); }
        //Распечатаем наибольший  по значению элемент из тройки целочисленных чисел, если сравнение невозможно,
        // распечатаем сообщение об ошибке
        try { System.out.println("Max in integer triple: " + intengerTriple.max()); }
        catch (UndefinedComponentException e) { System.out.println(e.getMessage()); }

        //Установим правый (третий) элемент тройки целочисленных чисел равный 1
        intengerTriple.setRight(1);
        //Распечатаем обновленную тройку целочисленных чисел
        System.out.println("\nRefresh integer triple: " + intengerTriple);
        //Распечатаем наименьший по значению элемент из обновленной тройки целочисленных чисел,
        // если сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Min in refresh integer triple: " + intengerTriple.min()); }
        catch (Exception e) { System.out.println(e.getMessage()); }
        //Распечатаем средний по значению элемент из обновленной тройки целочисленных чисел,
        // если сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Mean in refresh integer triple: " + intengerTriple.mean()); }
        catch (UndefinedComponentException e) { System.out.println(e.getMessage()); }
        //Распечатаем наибольший по значению элемент из обновленной тройки целочисленных чисел,
        // если сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Max in refresh integer triple: " + intengerTriple.max()); }
        catch (UndefinedComponentException e) { System.out.println(e.getMessage()); }

        //Инициализируем тройку строковых переменных
        Triple<String> stringTriple = new Triple<>("September","October", "November");
        //Распечатаем тройку строковых переменных
        System.out.println("\nString triple: " + stringTriple);
        //Распечатаем наименьший по значению (для строк используется алфавитный порядок) элемент из тройки
        // строковых переменных, если сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Min in string triple: " + stringTriple.min()); }
        catch (UndefinedComponentException e) { System.out.println(e.getMessage()); }
        //Распечатаем средний  по значению (для строк используется алфавитный порядок) элемент из тройки
        // строковых переменных, если сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Mean in string triple: " + stringTriple.mean()); }
        catch (UndefinedComponentException e) { System.out.println(e.getMessage()); }
        //Распечатаем наибольший  по значению (для строк используется алфавитный порядок) элемент из тройки
        // строковых переменных, если сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Max in string triple: " + stringTriple.max()); }
        catch (UndefinedComponentException e) { System.out.println(e.getMessage()); }

        //Инициализируем тройку экзепляров пользовательского класса Person
        // (класс Person реализует интерфейс Comparable<Person>)
        Triple<Person> mixTriple = new Triple<>(
                new Person(14, "First"),
                new Person(30, "Second"),
                new Person(23, "Third"));
        //Распечатаем тройку экзепляров пользовательского класса
        System.out.println("\nMix triple: " + mixTriple);
        //Распечатаем наименьший по значению элемент из тройки экзепляров пользовательского класса, если
        // сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Min in mix triple: " + mixTriple.min()); }
        catch (UndefinedComponentException | ClassCastException e) { System.out.println(e.getMessage()); }
        //Распечатаем средний  по значению элемент из тройки экзепляров пользовательского класса, если
        // сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Mean in mix triple: " + mixTriple.mean()); }
        catch (UndefinedComponentException | ClassCastException e) { System.out.println(e.getMessage()); }
        //Распечатаем наибольший  по значению элемент из тройки экзепляров пользовательского класса, если
        // сравнение невозможно, распечатаем сообщение об ошибке
        try { System.out.println("Max in mix triple: " + mixTriple.max()); }
        catch (UndefinedComponentException | ClassCastException e) { System.out.println(e.getMessage()); }
    }

    //Класс Person, используется для демонстрации работы методов класса Triple <T> с экземплярами
    // пользовательских классов
    //Класс Person реализует интерфейс Comparable<Person> (возможность сравнения двух объектов типа Person),
    // для сравнения используется значение целочисленной переменной age
    private static class Person implements Comparable<Person> {
        int age;
        String name;

        Person (int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return age - o.age;
        }

        @Override
        public String toString() {
            return "(Person) { " + "age=" + age + ", name=\"" + name + '\"' + '}';
        }
    }
}
