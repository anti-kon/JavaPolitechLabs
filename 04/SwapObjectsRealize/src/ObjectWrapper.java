//Класс-обёртка, реализующий метод для обмена значениями между двумя объектами одного класса
class ObjectWrapper <ObjectType> {
    //Переменная обобщенного типа data содержит значение объекта, который нужно обменять
    private ObjectType data;

    //Конструктор класса-обёртки ObjectWrapper
    public ObjectWrapper (ObjectType data) { this.data = data; }

    //Метод экземпляра класса, производящий обмен значений между текущим экземпляром и
    // передаваемым в качестве аргумента
    public void swap (ObjectWrapper swapObject) {
        SwapObjects(this, swapObject);
    }

    //Статический метод, производящий обмен значений между экземплярами, передаваемыми в качестве аргументов метода
    public static void SwapObjects (ObjectWrapper firstObject, ObjectWrapper secondObject) throws ClassCastException {
        //Проверяем являются ли объекты, значения которых нужно обменять экземплярами одного класса, если не являются,
        // то пробрасываем исключение приведения типов с сообщение об ошибке
        if (firstObject.getData().getClass() == secondObject.getData().getClass()) {
            //Производим обмен значений, при помощи буфера dataSaveObject
            Object dataSaveObject = secondObject.data;
            secondObject.data = firstObject.data;
            firstObject.data = dataSaveObject;
        } else throw new ClassCastException("Not possible to exchange values between an instance of class " +
                firstObject.getData().getClass().getName() +  " and an instance of class" +
                secondObject.getData().getClass().getName());
    }

    //Геттер для значения обёртки
    public ObjectType getData () { return data; }
    //Сеттер для значения обёртки
    public void setData (ObjectType data) { this.data = data; }
}