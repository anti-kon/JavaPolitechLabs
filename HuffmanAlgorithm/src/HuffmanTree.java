import java.util.*;

public class HuffmanTree {
    private Node head;

    HuffmanTree (HashMap<Character, Integer> symbols) {
        NavigableMap<Integer, Stack<Node>> nodes = new TreeMap<>();

        for (Map.Entry<Character, Integer> entry : symbols.entrySet()) {
            if (!nodes.containsKey(entry.getValue()))
                nodes.put(entry.getValue(), new Stack<>());
            nodes.get(entry.getValue()).push(new Node(entry.getKey()));
        }

        while (nodes.size() > 1 || nodes.firstEntry().getValue().size() > 1) {
            int firstWeight, secondWeight;
            Node firstNode, secondNode;

            if (nodes.firstEntry().getValue().size() > 1) {
                firstNode = nodes.firstEntry().getValue().pop();
                firstWeight = nodes.firstEntry().getKey();
            } else {
                firstNode = nodes.firstEntry().getValue().pop();
                firstWeight = nodes.pollFirstEntry().getKey();
            }

            if (nodes.firstEntry().getValue().size() > 1) {
                secondNode = nodes.firstEntry().getValue().pop();
                secondWeight = nodes.firstEntry().getKey();
            } else {
                secondNode = nodes.firstEntry().getValue().pop();
                secondWeight = nodes.pollFirstEntry().getKey();
            }


            Node newNode = new Node(firstNode, secondNode);

            if (!nodes.containsKey(firstWeight + secondWeight))
                nodes.put(firstWeight + secondWeight, new Stack<>());
            nodes.get(firstWeight + secondWeight).push(newNode);
        }

        this.head = nodes.pollFirstEntry().getValue().pop();
        if (head.left == null && head.right == null)
            head.code = "0";
        else
            head.calculateCode("");
    }

    HuffmanTree (HashMap<Character, String> decodingTable, int treeSize) {
        if (treeSize == 1) {
            char key = decodingTable.keySet().iterator().next();
            head = new Node(key, decodingTable.get(key));
        } else
            head = new Node();
        for (Map.Entry<Character, String> row : decodingTable.entrySet()) {
            String path = row.getValue();
            int charIndex = 0;
            Node curNode = head;
            while (charIndex < path.length()) {
                if (path.charAt(charIndex) == '0') {
                    if (curNode.left == null)
                        curNode.left = new Node();
                    curNode = curNode.left;
                } else {
                    if (curNode.right == null)
                        curNode.right = new Node();
                    curNode = curNode.right;
                }
                charIndex++;
            }
            curNode.code = row.getValue();
            curNode.symbol = row.getKey();
        }
    }

    public HashMap<Character, String> getCodingTable() {
        HashMap<Character, String> codingTable = new HashMap<>();
        Stack<Node> nodes = new Stack<>();
        nodes.push(head);
        while (!nodes.empty()) {
            Node curNode = nodes.pop();
            if (curNode.left != null && curNode.right != null) {
                nodes.push(curNode.left);
                nodes.push(curNode.right);
            } else
                codingTable.put(curNode.symbol, curNode.code);
        }
        return codingTable;
    }

    public void print() {
        List<List<String>> lines = new ArrayList<>();

        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(head);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getCode() == null ? "\u2502" : n.getSymbol() + ":" + n.getCode();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '\u2534' : 'f';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = 'g';
                        }
                    }
                    System.out.print(c);

                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "\u2500");
                        }
                        System.out.print(j % 2 == 0 ? "\u250C" : "\u2510");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "\u2500" : " ");
                        }
                    }
                }
                System.out.println();
            }

            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    private static class Node {
        private char symbol;
        private String code;
        private Node left, right;

        Node() {
            this('\u0000', null, null, null);
        }

        Node(char symbol) {
            this(symbol, null, null, null);
        }

        Node(char symbol, String code) {
            this(symbol, code, null, null);
        }

        Node(Node left, Node right) {
            this('\u0000', null, left, right);
        }

        Node(char symbol , String code, Node left, Node right) {
            this.symbol = symbol;
            this.code = code;
            this.left = left;
            this.right = right;
        }

        void calculateCode (String nodeCode) {
            if (left != null && right != null) {
                left.calculateCode(nodeCode + "0");
                right.calculateCode(nodeCode + "1");
            } else
                code = nodeCode;
        }

        public char getSymbol() { return symbol; }
        public String getCode() { return code; }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }

        public void setSymbol(char symbol) { this.symbol = symbol; }
        public void setCode(String code) { this.code = code; }
        public void setLeft(Node left) { this.left = left; }
        public void setRight(Node right) { this.right = right; }
    }
}
