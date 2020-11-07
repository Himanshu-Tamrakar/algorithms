package chapter5.section1.algo;

public class KeyIndexCoutingSort {
    private static class Student {
        private String name;
        private int section;

        public Student(String name, int section) {
            this.name = name;
            this.section = section;
        }

        @Override
        public String toString() {
            return "name: " + name + " section: " + section;
        }
    }


    public static void main(String[] args) {
        String s = "Harris Martin Moore Anderson Martinez Miller Robinson White Brown Davis Jackson Jones Taylor Williams Garcia Johnson Smith Thomas Thompson Wilson";
        String s1 = "2 3 3 4 1 3 4 3 1 2 2 1 2 4 3 4 4 2 3 2";
        int R = 4;

        String[] names = s.split(" ");
        String[] sections = s1.split(" ");

        Student[] students = new Student[names.length];
        for (int i = 0; i < names.length; i++) {
            students[i] = new Student(names[i], Integer.parseInt(sections[i]));
        }


        int[] count = new int[R+1];


        for (Student std: students) {
            count[std.section]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        Student[] sorted = new Student[students.length];

        for (int i = 0; i < students.length; i++) {
            sorted[count[students[i].section-1]++] = students[i];
        }
        students = sorted;

        for (Student std: students) {
            System.out.println(std);
        }

    }
}
