package chapter1.section1.solutions;

import java.lang.reflect.Field;

public class MutableStringHT {
    public static void main(String[] args) {
        String s = "Immutable";
        String t = "Notreally";
        mutate(s, t);
        System.out.println(s);
    }

    public static void mutate(String s, String t) {
        try {
            Field val = String.class.getDeclaredField("value");
//            Field off = String.class.getDeclaredField("offset");
            val.setAccessible(true);
//            off.setAccessible(true);
//            int offset = off.getInt(s);
            char[] value = (char[]) val.get(s);
            for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
                value[i] = t.charAt(i);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
