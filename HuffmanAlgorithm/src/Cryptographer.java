import java.util.HashMap;

public class Cryptographer {
    static String code (char[] input, HashMap<Character, String> codingTable) {
        StringBuilder code = new StringBuilder();

        for (char c : input)
            if (c != '\u0000')
                code.append(codingTable.get(c));
            else break;

        return code.toString();
    }

    static String decode (String input, HashMap<String, Character> decodingTable) {
        StringBuilder curCode = new StringBuilder();
        StringBuilder output = new StringBuilder();

        for (char bit : input.toCharArray()) {
            curCode.append(bit);
            if (decodingTable.containsKey(curCode.toString())) {
                output.append(decodingTable.get(curCode.toString()));
                curCode.setLength(0);
            }
        }

        return output.toString();
    }
}
