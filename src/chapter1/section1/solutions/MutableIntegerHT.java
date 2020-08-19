package chapter1.section1.solutions;

import java.lang.reflect.Field;

public class MutableIntegerHT {
    public static void main(String[] args) {
        final Integer n = 100;
        mutate(n);
        System.out.print(n);
    }

    public static void mutate(Integer n) {
        try {
            Field val = Integer.class.getDeclaredField("value");
            val.setAccessible(true);
            val.setInt(n, 3000);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
