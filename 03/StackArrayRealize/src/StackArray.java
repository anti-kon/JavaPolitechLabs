import java.util.Arrays;

//Класс стека на основе массива
public class StackArray <DataType> {
    private DataType[] data; //Массив, хранящий элементы стека
    private int size = 0; //Количество элементов в стеке

    //Пустой конструктор стека (задаёт ёмкость по умолчанию = 16)
    public StackArray () { this(16); }

    //Конструктор стека, с указанием изначальной ёмкости
    public StackArray (int capacity) { data = (DataType[]) new Object[capacity]; }

    //Вернуть верхний элемент стека
    public DataType peek () { return data[(size - 1)]; }

    //Извлечь и вернуть верхний элемент стека
    public DataType pop () {
        if (size == 0) return null;
        size--;
        DataType savedData = data[size];
        data[size] = null;
        return savedData;
    }

    //Добавить новый элемент на вершину стека
    public void push (DataType newElement) {
        if (size == data.length)
            data = Arrays.copyOf(data, 2 * data.length);
        data[size] = newElement;
        size++;
    }

    //Проверить является ли стек пустым
    public boolean isEmpty () { return size == 0; }

    //Вернуть количество элементов в стеке
    public int getSize () { return size; }
}