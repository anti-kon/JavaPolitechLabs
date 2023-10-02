import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //Метод демонстрирующий конструкторы класса String (исключая устаревшие)
        StringConstructorsDemonstration();

        //Метод демонстрирующий методы класса String (исключая устаревшие)
        StringMethodDemonstration();
    }

    public static void StringConstructorsDemonstration () throws UnsupportedEncodingException {
        // Массив содержащий строки для которых будут вызваны различные конструкторы (исключая устаревшие)
        String[] stringConstructors = new String[13];

        //Инициализация новой пустой строки (эквивалентно stringConstructors[0] = "")
        stringConstructors[0] = new String();

        //Инициализация строки из byte массива, используя стандартную декодировку платформы
        stringConstructors[1] = new String(new byte[]{97, 98, 99, 100, 101});

        //Инициализация строки из byte массива, со смещением на offset (второй параметр) символов
        // и длинной length (третий параметр) символов, используя стандартную декодировку платформы
        stringConstructors[2] = new String(new byte[]{97, 98, 99, 100, 101}, 2, 2);

        //Инициализация строки из byte массива, используя декодировку с названием charsetName (второй параметр)
        stringConstructors[3] = new String(new byte[]{97, 98, 99, 100, 101}, "Windows-1251");

        //Инициализация строки из byte массива, со смещением на offset (второй параметр) символов
        // и длинной length (третий параметр) символов, используя декодировку с
        // названием charsetName (четвертый параметр)
        stringConstructors[4] = new String(new byte[]{97, 98, 99, 100, 101}, 2, 2,
                                           "Windows-1251");

        //Инициализация строки из byte массива, используя декодировку charsetName (четвертый параметр)
        stringConstructors[5] = new String(new byte[]{97, 98, 99, 100, 101}, StandardCharsets.UTF_8);

        //Инициализация строки из byte массива, со смещением на offset (второй параметр) символов
        // и длинной length (третий параметр) символов, используя декодировку charsetName (четвертый параметр)
        stringConstructors[6] = new String(new byte[]{97, 98, 99, 100, 101}, 2, 2,
                                           StandardCharsets.UTF_8);

        //Инициализация строки из char массива
        stringConstructors[7] = new String(new char[]{'a', 'b', 'c', 'd', 'e'});

        //Инициализация строки из char массива, со смещением на offset (второй параметр) символов
        // и длинной count (третий параметр)
        stringConstructors[8] = new String(new char[]{'a', 'b', 'c', 'd', 'e'}, 2, 2);

        //Инициализация строки из int массива, содержащего кодовые точки Unicode,
        // со смещением на offset (второй параметр) символов и длинной count (третий параметр)
        stringConstructors[9] = new String(new int[]{97, 98, 99, 100, 101}, 0, 5);

        //Инициализация строки, содержащую такую же последовательность символов, что и origin (первый параметр)
        stringConstructors[10] = new String("abcde");

        //Инициализация строки, с использованием объекта
        // класса StringBuffer (синхронизированный и потокобезопасный класс)
        stringConstructors[11] = new String(new StringBuffer().append("abcde"));

        //Инициализация строки, с использованием объекта
        // класса StringBuilder (непотокобезопасный класс, работает быстрее StringBuffer)
        stringConstructors[12] = new String(new StringBuilder().append("abcde"));

        //Конструктры класса StringBuffer / StringBuilder:
        //  - пустой конструктор инициализирует пустой буфер с ёмкостью 16 символов
        //  - (CharSequence seq) инициализирует буфер, содержащий символьную последовательность seq
        //  - (int capacity) инициализирует пустой буфер с ёмкостью capacity символов
        //  - (String str) инициализирует буфер, содержащий строку str
        //
        //Методы класса (отличные от методов класса String) StringBuffer / StringBuilder:
        //  - append (...) (appendCodePoint (...)) добавляет символы в буфер
        //  - capacity () возвращает емкость буфера
        //  - delete (int start, int end) (deleteCharAt (int index)) удаляет символы, из буфера
        //  - ensureCapacity (int minimumCapacity) увеличивает размер буфера как минимум до minimumCapacity
        //  - insert (...) извлекает символы из буфера
        //  - reverse () переворачивает буфер с конца в начало
        //  - setCharAt (int index, char ch) заменяет символ на месте index на ch
        //  - setLength (int newLength) устанавливает длину буфера на newLength
        //  - toString () возвращает строку из буфера
        //  - trimToSize () изменит размер буфера до минимально возможной (без потери данных)

        for (int i = 0; i < stringConstructors.length; i++)
            System.out.println((i+1) + ": " + stringConstructors[i]);
    }

    public static void StringMethodDemonstration () throws UnsupportedEncodingException {
        String example_string = "abcde";

        //charAt - возвращает символ на месте index (первый параметр)
        System.out.println(example_string.charAt(2));

        //codePointAt - возвращает кодовую точку Unicode на месте index (первый параметр)
        System.out.println(example_string.codePointAt(2));

        //codePointBefore - возвращает кодовую точку Unicode перед index (первый параметр)
        System.out.println(example_string.codePointBefore(3));

        //codePointCount - возвращает количество кодовых точек Unicode в диапозоне от beginIndex (первый параметр) до
        // endIndex (второй параметр)
        System.out.println(example_string.codePointCount(0, 4));

        //compareTo - сравнить две сторки лексикографически, возвращает лексическую разницу строк
        System.out.println(example_string.compareTo("cde"));

        //compareToIgnoreCase - сравнить две сторки лексикографически, без учёта регистра,
        // возвращает лексическую разницу строк
        System.out.println(example_string.compareToIgnoreCase("CDE"));

        //concat - объединяет текущую строку с str (первый параметр), добавляя str в конец
        System.out.println(example_string.concat("fg"));

        //contains - возвращает true, если строка содержит последовательность символов s (первый параметр)
        System.out.println(example_string.contains("cd"));

        //contentEquals - сравнивает строку с последовательностью символов cs / sb (первый параметр)
        System.out.println(example_string.contentEquals("abcde"));

        //copyValueOf - эквивалентно valueOf
        System.out.println(String.copyValueOf(new char[]{'a', 'b', 'c', 'd', 'e'}));

        //endsWith - проверяет, заканчивается ли эта строка подстрокой suffix (первый параметр)
        System.out.println(example_string.endsWith("de"));

        //equals - сравнивает строку с объектом anObject (первый параметр)
        System.out.println(example_string.equals("abcde"));

        //equalsIgnoreCase - сравнивает строку с объектом anObject (первый параметр), без учёта регистра
        System.out.println(example_string.equals("abcde"));

        //format - возвращает форматированную строку, используя переданные параметры
        System.out.println(String.format(Locale.UK, "%f, %d, %c", 1.0f, 2, 'a'));

        //getBytes() - кодирует строку в последовательность байт, используя стандартную кодировку платформы
        //getBytes(Charset charset) - кодирует строку в последовательность байт, используя заданную кодировку
        //getBytes(String charsetName) - кодирует строку в последовательность байт, используя заданную кодировку
        System.out.println(example_string.getBytes()[0]);
        System.out.println(example_string.getBytes(StandardCharsets.UTF_8)[0]);
        System.out.println(example_string.getBytes("Windows-1251")[0]);

        //getChars - копирует символы из строки, в диапозоне от srcBegin (первый параметр) до srcEnd (второй параметр)
        // в символьный массив dst (третий параметр), запись производиться с dstBegin элемент массива
        char temp_chars [] = new char[5];
        example_string.getChars(0, 5, temp_chars, 0);
        System.out.println(temp_chars.length);

        //indent - возвращает строку строку с n (первый параметр) пробелаами перед строкой
        System.out.println(example_string.indent(3));

        //indexOf (String str / int ch) - возвращает номер первого сивола подстроки str (символа ch) (первый параметр)
        // содеражащейся в строке
        //indexOf (String str / int ch, int beginIndex, int endIndex)- возвращает номер первого сивола
        // от beginIndex (второй параметр) до endIndex (третий параметр)
        // подстроки str (символа ch) (первый параметр) содеражащейся в строке, в диапазоне
        System.out.println(example_string.indexOf("de"));
        System.out.println(example_string.indexOf('e', 2));

        //intern - возвращает каноническую строку (ссылку на строку из кучи)
        System.out.println(example_string.intern());

        //isEmpty - проверяет пустая (нулевая) строка или нет (строка состоящая из пробелов = false)
        System.out.println(example_string.isEmpty());

        //join - возврвращает новую строку составленную из переданных в метод переменных
        System.out.println(String.join("ab","c", "d", "e"));

        //lastIndexOf (String str / int ch) - возвращает номер первого символа последнего включения подстроки (символа)
        // совпадющей с str (ch) (первый параметр)
        //lastIndexOf (String str / int ch, int fromIndex) - возвращает номер первого символа последнего включения подстроки (символа)
        // совпадющей с str (ch) (первый параметр), после fromIndex (второй параметр) символа
        System.out.println(example_string.lastIndexOf('c'));
        System.out.println(example_string.lastIndexOf("de", 1));

        //length - возващает длину строки
        System.out.println(example_string.length());

        //matches - проверяет подходит ли строка под регулярное выражение
        System.out.println(example_string.matches("[a-z]"));

        //offsetByCodePoints - возвращает индекс символа, находящегося от index (первый параметр) символа с отступом
        // на codePointOffset (второй параметр) кодовых точек
        System.out.println(example_string.offsetByCodePoints(1, 2));

        //regionMatches - проверяет совпадение строки, начиная с toffset (второй параметр) символа,
        // со строкой other (третий параметр) начиная с ooffset (четвертый параметр),
        // ignoreCase (первый параметр) указывает нужно ли игнорировать регистр
        System.out.println(example_string.regionMatches(true, 0, "abc", 0, 2));

        //replace - заменяет все последовательность символов/символы target/oldChar (первый параметр) в строке на
        // последовательность символов/символы replacement/newChar (второй параметр)
        System.out.println(example_string.replace('b', 'd'));
        System.out.println(example_string.replace("bc", "fg"));

        //replaceAll - заменяет все подстроки regex (первый параметр) в строке
        // на подстроку replacement (второй параметр)
        System.out.println(example_string.replaceAll("bc", "fg"));

        //replaceFirst - заменяет первую подстроку regex (первый параметр), включённую в строке,
        // на подстроку replacement (второй параметр)
        System.out.println(example_string.replaceFirst("bc", "fg"));

        //split - разбивает строку на массив String подстрок по регулярному выражению regex (первый параметр)
        System.out.println(example_string.split("c")[0]);

        //startsWith - проверяет, начинается ли строка с подстроки prefix (первый параметр)
        System.out.println(example_string.startsWith("ab"));

        //strip - уберает все пробелы а начале и в конце строки
        System.out.println(example_string.strip());

        //subSequence - возвращает последовательность символов в диапозоне
        // от beginIndex (первый параметра) символа стороки до endIndex (второй параметр) символа строки
        System.out.println(example_string.subSequence(1, 3));

        //substring - возвращает подстроку из диапозона от beginIndex (первый параметра) символа стороки до
        // endIndex (второй параметр) символа строки
        System.out.println(example_string.substring(2));

        //toCharArray - возвращает массив char, состоящий из символов строки
        System.out.println(example_string.toCharArray()[0]);

        //toLowerCase - возвращает строку в нижнем регистре
        System.out.println("ABcdE".toLowerCase());

        //toUpperCase - возвращает строку в верхнем регистре
        System.out.println(example_string.toUpperCase());

        //trim - возвращает строку без спец. симвлов отступа в начале и конец строки
        System.out.println(("   " + example_string + "   ").trim());

        //valueOf - возвращает строковое значение переданного параметра
        System.out.println(String.valueOf(2));
        System.out.println(String.valueOf(true));
        System.out.println(String.valueOf('c'));
        System.out.println(String.valueOf(example_string));
    }
}