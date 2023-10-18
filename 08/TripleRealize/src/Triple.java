// Пользовательский обобщённый класс тройки, универсальный параметр должен включать
// интерфейс Comparable (должен реализовывать возможность сравнения)
public class Triple <DataType extends Comparable <DataType>> {
    //Переменная обобщенного типа left содержит значение левого (первого) элемента тройки
    private DataType left;
    //Переменная обобщенного типа middle содержит значение среднего (второго) элемента тройки
    private DataType middle;
    //Переменная обобщенного типа middle содержит значение среднего (третьего) элемента тройки
    private DataType right;

    //Конструктор класса Triple
    public Triple(DataType left, DataType middle, DataType right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    //Метод поиска наименьшего по значению элемента тройки
    public DataType min() throws UndefinedComponentException {
        //Обработаем случаи, когда как минимум два элемента тройки равны по значению между собой,
        // если оставшийся элемент меньше по значению чем равные между собой по значению элементы тройки,
        // то возвращаем его, иначе пробрасываем исключение невозможности определить наименьший по значению
        // элемент тройки
        if (left.compareTo(right) == 0) {
            if (middle.compareTo(left) < 0) return middle;
            else throw new UndefinedComponentException("Impossible to determine the smallest Triple component");
        }
        if (left.compareTo(middle) == 0) {
            if (right.compareTo(left) < 0) return right;
            else throw new UndefinedComponentException("Impossible to determine the smallest Triple component");
        }
        if (middle.compareTo(right) == 0) {
            if (left.compareTo(middle) < 0) return left;
            else throw new UndefinedComponentException("Impossible to determine the smallest Triple component");
        }

        //Попарно сравнивая элементы тройки, возвращаем наименьший по значению
        if (left.compareTo(middle) < 0) {
            if (left.compareTo(right) < 0) return left;
            else return right;
        } else {
            if (middle.compareTo(right) < 0) return middle;
            else return right;
        }
    }

    //Метод поиска наибольшего по значению элемента тройки
    public DataType max() throws UndefinedComponentException {
        //Обработаем случаи, когда как минимум два элемента тройки равны по значению между собой,
        // если оставшийся элемент больше по значению чем равные между собой по значению элементы тройки,
        // то возвращаем его, иначе пробрасываем исключение невозможности определить наибольший по значению
        // элемент тройки
        if (left.compareTo(right) == 0) {
            if (middle.compareTo(left) > 0) return middle;
            else throw new UndefinedComponentException("Impossible to determine the biggest Triple component");
        }
        if (left.compareTo(middle) == 0) {
            if (right.compareTo(left) > 0) return right;
            else throw new UndefinedComponentException("Impossible to determine the biggest Triple component");
        }
        if (middle.compareTo(right) == 0) {
            if (left.compareTo(middle) > 0) return left;
            else throw new UndefinedComponentException("Impossible to determine the biggest Triple component");
        }

        //Попарно сравнивая элементы тройки, возвращаем наибольший по значению
        if (left.compareTo(middle) > 0) {
            if (left.compareTo(right) > 0) return left;
            else return right;
        } else {
            if (middle.compareTo(right) > 0) return middle;
            else return right;
        }
    }

    //Метод поиска среднего по значению элемента тройки
    public DataType mean() throws UndefinedComponentException {
        //Обработаем случай, когда как минимум два элемента тройки равны по значению между собой, если это верно,
        // то пробрасываем исключение невозможности определить средний по значению элемент тройки
        if (left.compareTo(middle) == 0 || middle.compareTo(right) == 0 || left.compareTo(right) == 0)
            throw new UndefinedComponentException("Impossible to determine the mean Triple component");

        //Попарно сравнивая элементы тройки, возвращаем средний по значению
        if (left.compareTo(middle) > 0) {
            if (left.compareTo(right) < 0) return left;
            else return (middle.compareTo(right) > 0 ? middle : right);
        } else {
            if (middle.compareTo(right) < 0) return middle;
            else return left.compareTo(right) > 0 ? left : right;
        }
    }

    //Переопределим метод toString родительского класса Object, для печати всех элементов тройки
    @Override
    public String toString() { return "{ " + left + ", " + middle + ", " + right + '}'; }

    //Геттер левого (первого) элемента тройки
    public DataType getLeft() { return left; }
    //Геттер среднего (второго) элемента тройки
    public DataType getMiddle() { return middle;}
    //Геттер правого (третьего) элемента тройки
    public DataType getRight() { return right; }

    //Сеттер левого (первого) элемента тройки
    public void setLeft(DataType left) { this.left = left; }
    //Сеттер среднего (второго) элемента тройки
    public void setMiddle(DataType middle) { this.middle = middle; }
    //Сеттер правого (третьего) элемента тройки
    public void setRight(DataType right) { this.right = right; }
}