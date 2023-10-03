public class StackArrayMain {

    //Тестирование стека на основе массива
    public static void main(String[] args) {
        //Инициализируем целочисленный и строковой стеки
        StackArray<Integer> intStackArray = new StackArray<>(4);
        StackArray<String> stringStackArray = new StackArray<>();

        //Проверим является ли целочисленный стек пустым
        System.out.println("Is integer stack empty: " + intStackArray.isEmpty());

        //Добавим в целочисленный стек элемент (5)
        intStackArray.push(5);

        //Проверим является ли целочисленный стек пустым
        System.out.println("Add \'5\' to integer stack;");
        System.out.println("Is integer stack empty: " + intStackArray.isEmpty());

        //Добавим в целочисленный стек элементы от 4 до 1 (4, 3, 2, 1)
        for (int index = 4; index > 0; index--)
            intStackArray.push(index);

        //Распечаем количество элементов в стеке
        System.out.println("In integer stack " + intStackArray.getSize() + " elements");

        //Пока целочисленный стек не опустеет, будем извлекать и печатать верхний элемент
        while (!intStackArray.isEmpty())
            System.out.println(intStackArray.pop());

        //Добавим элементы в строковый стек ("first", "second", "third")
        stringStackArray.push("first");
        stringStackArray.push("second");
        stringStackArray.push("third");

        //Распечатаем верхний элемент строкового стека
        System.out.println("The top element of string stack is \"" + stringStackArray.peek() + "\"" );

        //Пока строковый стек не опустеет, будем извлекать и печатать верхний элемент
        while (!stringStackArray.isEmpty())
            System.out.println(stringStackArray.pop());
    }
}