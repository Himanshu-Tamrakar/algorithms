package chapter5.section2.algo;

import chapter3.section5.solutions.Interpreter;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.TST;

public class TSTHT {
    private final int R = 256;
    private Node root;
    private Node[] start;
    private class Node {
        private char ch;
        private Integer value;
        private Node left, right, middle;

    }

    public TSTHT() {
        start = new Node[R];
        for (char c = 0; c < R; c++) {
            Node x = new Node();
            start[c] = x;
        }
    }

    public Integer get(String s) {
        Node var0 = get(root, s, 0);
        return var0 == null ? -1 : var0.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;

        char ch = key.charAt(d);
        if (x.ch == ch) {
            return d < key.length() - 1 ? this.get(x.middle, key, d + 1) : x;
        } else if (ch < x.ch) {
            return get(x.left, key, d);
        } else {
            return get(x.right, key, d);
        }
    }

    public int nonRecursiveGet(String key) {
        Node x = root;
        int d = 0;
        while (x != null && d < key.length()) {

            char ch = key.charAt(d);
            if (ch < x.ch) {
                x = x.left;
            } else if (ch > x.ch) {
                x = x.right;
            } else {
                d++;
                if (d == key.length()) break;
                x = x.middle;
            }
        }

        return x == null ? -1 : x.value == null ? -1 : x.value;
    }

    public Integer optimizeGet(String key) {
        Node var0 = get(start[key.charAt(0)], key, 1);
        return var0 == null ? -1 : var0.value;
    }
    public void optimizePut(String key, Integer value) {
        start[key.charAt(0)] = put(start[key.charAt(0)], key, value, 1);
    }

    public void put(String key, Integer value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Integer value, int d) {
        char c = key.charAt(d);

        if (x == null) {
            x = new Node();
            x.ch = c;
        }

        if (c < x.ch) {
            x.left = this.put(x.left, key, value, d);
        } else if (c > x.ch) {
            x.right = this.put(x.right, key, value, d);
        } else if (d < key.length() - 1) {
            x.middle = this.put(x.middle, key, value, d + 1);
        } else {
            x.value = value;
        }
        return x;
    }
    public void nonRecursivePut(String key, Integer value) {
        Node x = root;
        int d = 0;
        while (d < key.length()) {
            char ch = key.charAt(d);
            if (x == null) {
                x = new Node();
                x.ch = ch;
            }

            if (x.middle == null) {
                x.middle = new Node();
                x.ch = '\0';
            } else {
                if (ch == '\0') {
                    x.ch = ch;
                } else if (ch < x.ch) {
                    x = x.left;
                } else if (ch > x.ch) {
                    x = x.right;
                } else {
                    x = x.middle;
                    d++;
                }
            }

            x = x.middle;


        }
        x.value = value;
    }

    public void delete(String key) {
        root = delete(root, key,0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;

        char c = key.charAt(d);

        if (c < x.ch) {
            x.left = delete(x.left, key, d);
        } else if(c > x.ch) {
            x.right = delete(x.right, key, d);
        } else if(d < key.length()-1) {
            x.middle = delete(x.middle, key, d+1);
        } else {
            x.value = null;
        }

        if (x.left == null && x.middle == null && x.right == null) {
            return null;
        } else {
            return x;
        }


    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> keys = new Queue();
        Node x = this.get(this.root, prefix, 0);
        if (x.value != null) {
            keys.enqueue(prefix);
        }
        this.collect(x.middle, new StringBuilder(prefix), keys);
        return keys;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> keys) {
        if (x == null) return;

        collect(x.left, prefix, keys);
        if (x.value != null) {
            // see the difference
            // keys.enqueue(prefix.append(x.ch).toString());: Wrong.
            keys.enqueue(prefix.toString() + x.ch); // Right
        }
        collect(x.middle, prefix.append(x.ch), keys);
        prefix.deleteCharAt(prefix.length()-1);
        collect(x.right, prefix, keys);
    }

    public String longestPrefixOf(String prefix) {
        int d = longestPrefix(root, prefix, 0, 0);
        return prefix.substring(0, d);
    }

    private int longestPrefix(Node x, String prefix,  int d, int index) {
        if (x == null) return index;
        if (x.value != null) index = d+1;

        if (d == prefix.length()-1) return index;

        int c = prefix.charAt(d);
        if (c < x.ch) return longestPrefix(x.left, prefix, d, index);
        else if(c > x.ch) return longestPrefix(x.right, prefix, d, index);
        else return longestPrefix(x.middle, prefix, d+1, index);
    }

    public static void main(String[] args) {
        String[] arr = new String[] {
                "shells",
                "sells",
                "surely",
                "sea",
                "shore",
                "the",
                "by",
                "are",
                "she"
        };

        TST<Integer> stst = new TST<>();

        TSTHT st = new TSTHT();
        for (int i = 0; i < arr.length; i++) {
            st.put(arr[i], i);
            stst.put(arr[i], i);
        }



//        System.out.printf("Deleting Value------------------------------Start\n");
//        st.delete("surely");
//        st.delete("sells");
//        st.delete("are");
//        System.out.printf("Deleting Value------------------------------Start\n");

        System.out.printf("Retrieving Value------------------------------Start\n");
        for (String s: arr) {
            System.out.printf("%s: %d\n", s, st.nonRecursiveGet(s));
        }
        System.out.printf("Retrieving Value------------------------------End\n");

        System.out.printf("Keys with prefix:------------------------------Start\n");
        for (String s: st.keysWithPrefix("sh")) {
            System.out.printf("%s\n", s);
        }
        System.out.printf("Keys with prefix:------------------------------End\n");

        System.out.printf("Longest Prefix:------------------------------Start\n");
        System.out.printf("%s\n", st.longestPrefixOf("shellsort"));
        System.out.printf("%s\n", st.longestPrefixOf("shel"));
        System.out.printf("%s\n", st.longestPrefixOf("shell"));
        System.out.printf("%s\n", st.longestPrefixOf("searl"));

        System.out.printf("Longest Prefix:------------------------------End\n");
    }


}
