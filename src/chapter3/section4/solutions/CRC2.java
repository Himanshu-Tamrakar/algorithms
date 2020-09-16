package chapter3.section4.solutions;

import java.util.zip.CRC32;

public class CRC2 {
    public static void main(String[] args) {
        CRC32 checksum = new CRC32();
        String s = "" + (int)(Math.ceil(Math.random() * 1000));
        System.out.printf("String is %s\n", s);
        checksum.update(s.getBytes());
        System.out.println(checksum.getValue());
    }
}
