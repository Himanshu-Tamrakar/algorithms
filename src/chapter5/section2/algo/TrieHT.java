package chapter5.section2.algo;

import edu.princeton.cs.algs4.Queue;
import sun.applet.AppletResourceLoader;

public class TrieHT {
    private Node root;
    private final int R = 256;
    private int lazyCount = 0;

    private class Node {
        Integer value;
        Node[] next = new Node[R];



        public Integer getValue() {
            return this.value;
        }
    }

    public TrieHT() {}

    public void put(String key, int value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, int value, int d) {


        if (x == null) x = new Node();
        if (d == key.length()) {
            x.value = value;
            return x;
        }
        int ch = key.charAt(d);
        x.next[ch] = put(x.next[ch], key, value, d+1);
        return x;
    }

    public int get(String key) {
        Node x = get(root, key, 0);
        return x == null ? -1 : x.value == null ? -1 : x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (key.length() == d) {
            return x;
        }
        int ch = key.charAt(d);
        return x.next[ch] == null ? null : get(x.next[ch], key, d+1);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }
    private Node delete(Node x, String key, int d) {
        if (x == null) return null;

        if (key.length() == d) {
            x.value = null;
        } else {
            int ch = key.charAt(d);
            Node var0 = delete(x.next[ch], key, d+1);
            x.next[ch] = var0;
        }

        for (int i = 0; i < R; i++) {
            if (x.next[i] != null) {
                return x;
            }
        }
        return null;


    }

    public int size() {
        return lazySize(this.root);
    }

    private int lazySize(Node x) {
        if (x == null) return 0;

        int cnt = 0;
        if (x.value != null) cnt++;

        for (int i = 0; i < R; i++) {
            if (x.next[i] != null) {
                cnt += lazySize(x.next[i]);
            }
        }
        return cnt;

    }


    public Iterable<String> keysWithPrefix(String str) {
        Queue<String> keys = new Queue<>();
        collect(get(root, str, 0), new StringBuilder(str), keys);
        return keys;
    }

    private void collect(Node x, StringBuilder pre, Queue<String> keys) {
        if (x == null) return;

        if (x.value != null) {
            keys.enqueue(pre.toString());
        }

        for (char i = 0; i < R; i++) {
            if (x.next[i] != null) {
                collect(x.next[i], pre.append(i), keys);
            }
        }
    }

    public String longestPrefix(String str) {
        int d = longestPrefix(root, str, 0, -1);
        return str.substring(0, d);
    }

    private int longestPrefix(Node x, String pre, int d, int index) {
        if (x == null) return index;

        if (x.value != null) {
            index = d;
        }

        if (d == pre.length()) {
            return index;
        } else {
            char ch = pre.charAt(d);
            return longestPrefix(x.next[ch], pre, d+1, index);
        }
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> keys = new Queue<>();
        collect(root, new StringBuilder(), pattern, keys);
        return keys;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> keys) {
        if (x == null) return;

        int d = prefix.length();
        if (d == pattern.length() && x.value != null) {
            keys.enqueue(prefix.toString());
        }

        if (d == pattern.length()) {
            return;
        }

        char ch = pattern.charAt(d);
        if (ch == '.') {
            for (char c = 0; c < R; c++) {
                collect(x.next[c], prefix.append(c), pattern, keys);
                // Needed that because we are using StringBuilder
                prefix.deleteCharAt(prefix.length()-1);
            }
        } else {
            collect(x.next[ch], prefix.append(ch), pattern, keys);
            // Needed that because we are using StringBuilder
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    public static void main(String[] args) {
        TrieHT st = new TrieHT();
        String[] arr = new String[] {
                "sea",
                "sells",
                "shells",
                "by",
                "shore",
                "the",
                "shellsort",
                "she"
        };
        st.put("sea",0);
        st.put("sells",1);
        st.put("shells",2);
        st.put("by",3);
        st.put("shore",4);
        st.put("the",5);
//        st.put("shellsort",6);
        st.put("she",7);

        st.delete("by");
//        st.delete("shells");

        for (String s: arr) {
            System.out.printf("%d\n", st.get(s));
        }

        System.out.printf("Keys With Prefix-----------------------------------\n");
        for (String s: st.keysWithPrefix("shell")) {
            System.out.printf("%s\n", s);
        }

        System.out.printf("Longest Prefix-----------------------------------\n");
        System.out.printf("%s\n", st.longestPrefix("shellsort"));

        System.out.printf("Keys With Prefix-----------------------------------\n");
        for (String s: st.keysThatMatch("s.ells")) {
            System.out.printf("%s\n", s);
        }

    }

}
