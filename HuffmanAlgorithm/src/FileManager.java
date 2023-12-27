import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private final File file;
    BufferedReader mainBufferReader = null;
    DataInputStream mainInputStream = null;
    BufferedWriter mainBufferWriter = null;
    DataOutputStream mainOutputStream = null;

    FileManager (String filePath, boolean isRead, boolean isByteFormat) throws IOException {
        file = new File(filePath);
        file.createNewFile();
        if(isByteFormat) {
            mainInputStream = isRead ? new DataInputStream (new FileInputStream(file)) : null;
            mainOutputStream = isRead ? null : new DataOutputStream(new FileOutputStream(file));
        }
        else {
            mainBufferReader = isRead ? new BufferedReader (new FileReader(file)) : null;
            mainBufferWriter = isRead ? null : new BufferedWriter(new FileWriter(file));
        }
    }

    char[] getNext() throws IOException {
        char[] output = new char[256];
        if (mainBufferReader.read(output) != -1)
            return output;
        else
            return null;
    }

    byte[] getNextByte() throws IOException {
        byte[] output = new byte[256];
        int byteCount;
        if ((byteCount = mainInputStream.read(output)) != -1) {
            byte[] convertOutput = new byte[byteCount];
            System.arraycopy(output, 0, convertOutput, 0, byteCount);
            return convertOutput;
        }
        else
            return null;
    }

    void printByteText(String input) throws IOException {
        byte[] codeArray = new byte[(input.length() / 8) + (input.length() % 8 == 0 ? 0 : 1)];
        for (int codeDigit = 0; codeDigit < input.length(); codeDigit++)
            if (input.charAt(codeDigit) == '1') {
                BigDecimal codeByte = (new BigDecimal(codeDigit)).
                        divide(BigDecimal.valueOf(8), RoundingMode.FLOOR).
                        setScale(0, RoundingMode.FLOOR);
                codeArray[codeByte.intValue()] |= (0b10000000 >>> codeDigit%8);
            }
        mainOutputStream.write(codeArray);
    }

    public void printText(String input) throws IOException {
        mainBufferWriter.write(input);
    }

    public void closeStreams () throws IOException {
        if (mainBufferWriter != null)
            mainBufferWriter.close();
        if (mainBufferReader != null)
            mainBufferReader.close();
        if (mainInputStream != null)
            mainInputStream.close();
        if (mainOutputStream != null)
            mainOutputStream.close();
    }

    HashMap<Character, Integer> getSymbolsStatistic () throws IOException {
        HashMap<Character, Integer> result = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int symbol;
        while ((symbol = reader.read()) != -1) {
            if (result.containsKey((char) symbol))
                result.put((char) symbol, result.get((char) symbol) + 1);
            else
                result.put((char) symbol, 1);
        }

        reader.close();

        return result;
    }

    public void saveTree(HashMap<Character, String> codingTable) throws IOException {
        mainOutputStream.writeInt(codingTable.size());
        int maxCodeLength = 0;

        for (Map.Entry<Character, String> symbol : codingTable.entrySet())
            maxCodeLength = Math.max(symbol.getValue().length(), maxCodeLength);

        mainOutputStream.write((byte) maxCodeLength);

        byte index = 0;
        byte buffer = 0;
        for (Map.Entry<Character, String> symbol : codingTable.entrySet()) {
            byte [] symbolBytes = symbol.getKey().toString().getBytes(StandardCharsets.UTF_16LE);

            for (byte symbolByte : symbolBytes) {
                String symbolBites = String.format("%8s",
                        Integer.toBinaryString(symbolByte)).replace(' ', '0');
                for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                    if (symbolBites.charAt(bitIndex) == '1')
                        buffer |= (0b10000000 >>> index);
                    index++;
                    if (index == 8) {
                        mainOutputStream.write(buffer);
                        buffer = 0;
                        index = 0;
                    }
                }
            }

            String codeLenghtString = "0".repeat(Integer.toBinaryString(maxCodeLength).length() -
                    Integer.toBinaryString(symbol.getValue().length()).length()) +
                    Integer.toBinaryString(symbol.getValue().length()) +
                    symbol.getValue();
            for (int bitIndex = 0; bitIndex < codeLenghtString.length(); bitIndex++) {
                if (codeLenghtString.charAt(bitIndex) == '1')
                    buffer |= (0b10000000 >>> index);
                index++;
                if (index == 8) {
                    mainOutputStream.write(buffer);
                    buffer = 0;
                    index = 0;
                }
            }
        }
        if (index != 0)
            mainOutputStream.write(buffer);
    }

    public HashMap<Character, String> loadTree() throws IOException {
        HashMap<Character, String> output = new HashMap<>();

        int treeSize = mainInputStream.readInt();
        int maxCodeSize = mainInputStream.read();

        int inputByte;
        byte[] symbolBuffer = new byte[2];
        int symbolIndex = 0;
        char[] codeSize = new char[Integer.toBinaryString(maxCodeSize).length()];
        int codeSizeIndex = 0;
        char[] codeBuffer = new char[0];
        int codeIndex = 0;
        while (output.size() < treeSize) {
            inputByte = mainInputStream.read();
            String inputByteString = String.format("%8s",
                    Integer.toBinaryString(inputByte)).replace(' ', '0');
            for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                if (symbolIndex < 16) {
                    if (inputByteString.charAt(bitIndex) == '1')
                        symbolBuffer[symbolIndex / 8] |= (0b10000000 >>> symbolIndex%8);
                    symbolIndex++;
                } else if (codeSizeIndex < codeSize.length) {
                    codeSize[codeSizeIndex] = inputByteString.charAt(bitIndex) == '1' ? '1' : '0';
                    codeSizeIndex++;
                    if (codeSizeIndex == Integer.toBinaryString(maxCodeSize).length())
                        codeBuffer = new char[Integer.parseInt(new String(codeSize), 2)];
                } else {
                    codeBuffer[codeIndex] = inputByteString.charAt(bitIndex) == '1' ? '1' : '0';
                    codeIndex++;
                    if (codeIndex == codeBuffer.length) {
                        output.put(new String(symbolBuffer, StandardCharsets.UTF_16LE).charAt(0),
                                new String(codeBuffer));
                        symbolIndex = 0;
                        codeIndex = 0;
                        symbolBuffer = new byte[2];
                        codeBuffer = new char[0];
                        codeSize = new char[Integer.toBinaryString(maxCodeSize).length()];
                        codeSizeIndex = 0;
                    }
                }
            }
        }
        return output;
    }

    public long getLength () {return file.length();}

    public void saveFileInfo(long oldFileSize) throws IOException {
        mainOutputStream.writeLong(oldFileSize);
    }

    public long getFileInfo () throws IOException {
        return mainInputStream.readLong();
    }
}
