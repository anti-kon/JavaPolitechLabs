//Класс, реализующий пользовательское исключение, пробрасываемое в случае невозможности определить
// наибольший/средний/наименьший по значению элемент
//Класс UndefinedComponentException наследуется от встроенного класса Exception (класс исключения)
class UndefinedComponentException extends Exception {
    //Конструктор класса вызывает конструктор родительского класса
    public UndefinedComponentException (String message) {
        super(message);
    }
}