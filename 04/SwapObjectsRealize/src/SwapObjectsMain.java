public class SwapObjectsMain {
    public static void main(String[] args) {
        //Чтобы производить обмен значениями будем использовать класс-обёртку ObjectWrapper

        //Инициализируем целочисленный переменные в обёртке Integer
        Integer firstInteger = 10, secondInteger = 256;
        //Инициализируем строковые переменные
        String firstString = "abc", secondString = "def";
        //Инициализируем экземпляры класса Person
        Person firstPerson = new Person(2, "First"), secondPerson = new Person(5, "Second");

        //Инициализируем обёртки для целочисленных значений
        ObjectWrapper<Integer> firstIntegerWrap = new ObjectWrapper<>(firstInteger);
        ObjectWrapper<Integer> secondIntegerWrap = new ObjectWrapper<>(secondInteger);
        //Распечатаем значения перед обменом
        System.out.println("Before swap:\n" +
                           "\tFirst integer: " + firstInteger + "\n" +
                           "\tSecond integer: " + secondInteger);
        //Произведем обмен значений между firstIntegerWrapper и secondIntegerWrapper
        firstIntegerWrap.swap(secondIntegerWrap);
        //Присвоим firstInteger и secondInteger новые значения (после обмена)
        firstInteger = firstIntegerWrap.getData();
        secondInteger = secondIntegerWrap.getData();
        //Распечатаем значения после обмена
        System.out.println("After swap:\n" +
                           "\tFirst integer: " + firstInteger + "\n" +
                           "\tSecond integer: " + secondInteger);

        //Инициализируем обёртки для строковых значений
        ObjectWrapper<String> firstStringWrap = new ObjectWrapper<>(firstString);
        ObjectWrapper<String> secondStringWrap = new ObjectWrapper<>(secondString);
        //Распечатаем значения перед обменом
        System.out.println("\nBefore swap:\n" +
                           "\tFirst string: " + firstString + "\n" +
                           "\tSecond string: " + secondString);
        //Произведем обмен значений между firstStringWrapper и secondStringWrapper,
        // используя статический метод swapObject класса ObjectWrapper
        ObjectWrapper.SwapObjects(firstStringWrap, secondStringWrap);
        //Присвоим firstString и secondString новые значения (после обмена)
        firstString = firstStringWrap.getData();
        secondString = secondStringWrap.getData();
        //Распечатаем значения после обмена
        System.out.println("After swap:\n" +
                           "\tFirst string: " + firstString + "\n" +
                           "\tSecond string: " + secondString);

        //Инициализируем  обёртки для экземпляров класса Person
        ObjectWrapper<Person> firstPersonWrap = new ObjectWrapper<>(firstPerson);
        ObjectWrapper<Person> secondPersonWrap = new ObjectWrapper<>(secondPerson);
        //Распечатаем значения перед обменом
        System.out.println("\nBefore swap:\n" +
                           "\tFirst person: " + firstPerson + "\n" +
                           "\tSecond person: " + secondPerson);
        //Произведем первый обмен значений между firstPersonWrap и secondPersonWrap
        ObjectWrapper.SwapObjects(firstPersonWrap, secondPersonWrap);
        //Присвоим firstPerson и secondPerson новые значения (после первого обмена)
        firstPerson = firstPersonWrap.getData();
        secondPerson = secondPersonWrap.getData();
        //Распечатаем значения после первого обмена
        System.out.println("After first swap:\n" +
                           "\tFirst person: " + firstPerson + "\n" +
                           "\tSecond person: " + secondPerson);
        //Произведем второй обмен значений (возврат к первоначальным значениям) между
        // firstPersonWrap и secondPersonWrap
        ObjectWrapper.SwapObjects(firstPersonWrap, secondPersonWrap);
        //Присвоим firstPerson и secondPerson новые значения (после второго обмена)
        firstPerson = firstPersonWrap.getData();
        secondPerson = secondPersonWrap.getData();
        //Распечатаем значения после второго обмена (возврата к первоначальным значениям)
        System.out.println("After second swap:\n" +
                           "\tFirst person: " + firstPerson + "\n" +
                           "\tSecond person: " + secondPerson);
    }

    //Класс Person, используется для демонстрации возможности обмена значений между экземплярами
    // пользовательских классов
    private static class Person {
        int age;
        String name;

        Person (int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "(Person) { " + "age=" + age + ", name=\"" + name + '\"' + '}';
        }
    }
}