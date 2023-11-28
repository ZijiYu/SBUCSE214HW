package HW2.Q2;

import java.util.ArrayList;
import java.util.List;

public class DynamicIntegerSet implements DynamicSet {
    public static class Node implements PrintableNode {
        Integer  data;
        Node left, right;
        Node(int x) { this(x, null, null); }
        Node(int x, Node left, Node right) {
            this.data  = x;
            this.left  = left;
            this.right = right;
        }
        @Override
        public String getValueAsString() { return data.toString(); }
        @Override
        public PrintableNode getLeft() { return left; }
        @Override
        public PrintableNode getRight() { return right; }
    }

    Node root;

    @Override
    //returns the number of elements in the set
    public int size() {
        return sizeRec(this.root);
    }
    int sizeRec(PrintableNode root){
        if (root == null) {
            return 0;
        }
        return 1 + sizeRec(root.getLeft()) + sizeRec(root.getRight());
    }

    @Override
    public boolean contains(Integer x) {
        return containsRec(root,x);
    }

    boolean containsRec(PrintableNode root,Integer x){
        if(root == null){
            return false;
        }
        int value = Integer.parseInt(root.getValueAsString());
        if(x < value){
            return containsRec(root.getLeft(),x);
        } else if(x > value){
            return containsRec(root.getRight(),x);
        }else{
            return true;
        }
    }

    @Override
    //adds an element to the dynamic set
    public boolean add(Integer x) {
        int originalSize = size();
        root = addRec(root, x);
        return size() > originalSize;
    }
    Node addRec(Node root, Integer x){
        if (root == null) {
            return new Node(x);
        }
        int rootValue = root.data;
        if (x < rootValue) {
            root.left = addRec(root.left, x);
        } else if (x > rootValue) {
            root.right = addRec(root.right, x);
        }
        return root;
    }

    @Override
    //removes an element from the dynamic set
    public boolean remove(Integer x) {
        int originalSize = size();
        root = removeRec(root,x);
        return originalSize>size();
    }
    Node removeRec(Node root, Integer x){
        if(root==null||!contains(x)){
            return root;
        }
        int value = root.data;
        if(x<value){
            root.left = removeRec(root.left,x);
        }else if(x>value){
            root.right = removeRec(root.right,x);
        }else{
            // Node with only one child or no child
            if(root.left==null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = removeRec(root.right, root.data);
        }
        return root;
    }
    int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }
    // this method must be there exactly in this form
    public Node root() {
        return this.root;
    }

    public static void main(String[] args) {
        DynamicIntegerSet set = new DynamicIntegerSet();
        set.add(7);
        set.add(6);
        set.add(11);
        set.add(5);
        set.add(10);
        System.out.println(set.size());
        System.out.println(set.contains(5));
        printTree(set.root());
        set.remove(10);
        printTree(set.root());
    }

    /*
    print tree method for testing not changeable
     */
    public static void printTree(PrintableNode node) {
        List<List<String>>  lines = new ArrayList<>();
        List<PrintableNode> level = new ArrayList<>();
        List<PrintableNode> next  = new ArrayList<>();

        level.add(node);
        int nn = 1;
        int widest = 0;
        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getValueAsString();
                    line.add(aa);
                    if (aa.length() > widest)
                        widest = aa.length();
                    next.add(n.getLeft());
                    next.add(n.getRight());
                    if (n.getLeft() != null)
                        nn++;
                    if (n.getRight() != null)
                        nn++;
                }
            }
            if (widest%2 == 1)
                widest++;
            lines.add(line);
            List<PrintableNode> tmp = level;
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

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (String f : line) {
                if (f == null) f = "";
                final float a    = perpiece / 2f - f.length() / 2f;
                int   gap1 = (int) Math.ceil(a);
                int   gap2 = (int) Math.floor(a);

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
}

