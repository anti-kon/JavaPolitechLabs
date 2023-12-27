import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner consoleScanner = new Scanner(System.in);
    private static byte enterWorkMode() {
        System.out.println("""
                        Specify the operating mode of the program:
                        Enter: 1 - coding mode; 2 - decoding mode; 3 - information mode;""");
        while (true) {
            switch (consoleScanner.nextByte()) {
                case 1 -> {
                    return 1;
                }
                case 2 -> {
                    return 2;
                }
                case 3 -> {
                    return 3;
                }
                default -> System.out.println("Incorrect input. Try again");
            }
        }
    }

    private static String enterInputFile() {
        System.out.println("Enter the path to the input file:");
        String filePath;

        while (true) {
            filePath = consoleScanner.nextLine();
            File file = new File(filePath);
            if (!filePath.equals("")) {
                if (!file.exists())
                    System.out.println("File not found. Try again");
                else {
                    break;
                }
            }
        }
        return filePath;
    }

    private static String enterOutputFile() {
        System.out.println("Enter the path to the output file or 0 to not specify name of output file:");
        String filePath = consoleScanner.nextLine();
        consoleScanner.close();
        return filePath;
    }


    public static void main(String[] args) throws IOException {
        byte workMode = 0;
        String inputFile;
        String outputFile = ".\\output_" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".txt";

        switch (args.length) {
            case 0 -> workMode = enterWorkMode();
            case 1, 2, 3 -> {
                if (args[0].equals("--c") || args[0].equals("--d") || args[0].equals("--i")) {
                    if (args[0].equals("--c"))
                        workMode = 1;
                    if (args[0].equals("--d"))
                        workMode = 2;
                    if (args[0].equals("--i"))
                        workMode = 3;
                } else
                    workMode = enterWorkMode();
            }
        }

        if (args.length == 0 || args.length == 1) {
            inputFile = enterInputFile();
            if (workMode != 3) {
                String newOutputFile = enterOutputFile();
                outputFile = newOutputFile.equals("0") ? outputFile : newOutputFile;
            }
        }
        else if (args.length == 2)
            if (args[0].equals("--c") || args[0].equals("--d") || args[0].equals("--i")) {
                inputFile = args[1];
                if (workMode != 3) {
                    String newOutputFile = enterOutputFile();
                    outputFile = newOutputFile.equals("0") ? outputFile : newOutputFile;
                }
            } else  {
                inputFile = args[0];
                outputFile = args[1];
            }
        else {
            inputFile = args[1];
            outputFile = args[2];
        }

        consoleScanner.close();;

        switch (workMode) {
            case 1 -> {
                FileManager input = new FileManager(inputFile, true, false);
                FileManager output = new FileManager(outputFile, false, true);

                output.saveFileInfo(input.getLength());

                HashMap<Character, Integer> statistic = input.getSymbolsStatistic();
                HuffmanTree tree = new HuffmanTree(statistic);
                output.saveTree(tree.getCodingTable());

                char [] charsToCode;
                String codedString = "";
                while ((charsToCode = input.getNext()) != null) {
                    codedString = Cryptographer.code(charsToCode, tree.getCodingTable());
                    output.printByteText(codedString);
                }
                output.printByteText(String.format("%8s",
                        Integer.toBinaryString(codedString.length() % 8)).replace(' ', '0'));
                input.closeStreams();
                output.closeStreams();
            }
            case 2 -> {
                FileManager input = new FileManager(inputFile, true, true);
                FileManager output = new FileManager(outputFile, false, false);

                input.getFileInfo();

                HashMap<Character, String> treeTable = input.loadTree();
                HashMap<String,Character> decodingTable = new HashMap<>();
                for (Map.Entry<Character, String> row : treeTable.entrySet())
                    decodingTable.put(row.getValue(), row.getKey());

                byte[] bytesToDecode = input.getNextByte();
                StringBuilder bitesToDecode = new StringBuilder();
                byte unusedBits = bytesToDecode[bytesToDecode.length - 1];
                for (byte byteToDecode : bytesToDecode) {
                    bitesToDecode.append(String.format("%8s",
                            Integer.toBinaryString(byteToDecode & 0xFF)).
                            replace(' ', '0'));
                }

                while ((bytesToDecode = input.getNextByte()) != null) {
                    output.printText(Cryptographer.decode(bitesToDecode.toString(), decodingTable));
                    bitesToDecode.setLength(0);
                    for (byte byteToDecode : bytesToDecode)
                        bitesToDecode.append(Integer.toBinaryString(byteToDecode));
                    unusedBits = bytesToDecode[bytesToDecode.length - 1];
                }

                output.printText(Cryptographer.decode(
                        bitesToDecode.substring(0, bitesToDecode.length() - unusedBits - 8),
                        decodingTable));
                input.closeStreams();
                output.closeStreams();
            }
            case 3 -> {
                FileManager input = new FileManager(inputFile, true, true);
                long oldFileSize = input.getFileInfo();

                HashMap<Character, String> treeTable = input.loadTree();
                HuffmanTree tree = new HuffmanTree(treeTable, treeTable.size());
                tree.print();
                System.out.println("Source file weight: " + oldFileSize + " bytes");
                System.out.println("Coded file weight (with additional information): " +
                        input.getLength() + " bytes");
                System.out.println("Compression ratio: " + input.getLength() * 100 /oldFileSize + "%");
            }
        }
    }
}