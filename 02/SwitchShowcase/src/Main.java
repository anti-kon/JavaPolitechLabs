public class Main {
    public static void main(String[] args) {
        //Типы, способные учавствовать в switch
        byte byteVariable = 7;
        short shortVariable = 2000;
        int integerVariable = 44000;
        char charVariable = 'a';
        String stringVariable = "stringVariable";

        //Пример switch для переменной типа byte
        System.out.println(
            switch (byteVariable) {
                case 7 -> "Byte variable is 7";
                default -> "Byte variable is not 7";
            }
        );

        //Пример switch для переменной типа short
        System.out.println(
                switch (shortVariable) {
                    case 2 -> "Short variable is 2";
                    case 4 -> "Short variable is 4";
                    case 16 -> "Short variable is 16";
                    case 3000 -> "Short variable is 3000";
                    default -> "There is no 'case' declared for this short value";
                }
        );

        //Пример switch для переменной типа int
        System.out.println(
                switch (integerVariable) {
                    case 2 -> "Integer variable is 2";
                    case 44000 -> "Integer variable is 44000";
                    default -> "There is no 'case' declared for this integer value";
                }
        );

        //Пример switch для переменной типа char
        System.out.println(
                switch (charVariable) {
                    case 97 -> "Char variable is \'a\' (97)";
                    case 'b' -> "Char variable is \'b\' (98)";
                    default -> "There is no 'case' declared for this char value";
                }
        );

        //Пример switch для переменной типа String
        System.out.println(
                switch (stringVariable) {
                    case "lineVariable" -> "String variable is \"lineVariable\"";
                    case "strokeVariable" -> "String variable is \"strokeVariable\"";
                    case "stringVariable" -> "String variable is \"stringVariable\"";
                    default -> "There is no 'case' declared for this string value";
                }
        );
    }
}