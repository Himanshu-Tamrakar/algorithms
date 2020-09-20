package chapter3.section5.solutions;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

enum instructors {
    Himanshu, Aman, Anshul, Anoop
}

public class RegisterarScheduling {

    HashMap<String, ArrayList<TeachClass>> map;
    public RegisterarScheduling() {
        map = new HashMap<>();
    }

    private static class TeachClass {
        private String className;
        private String at;

        private TeachClass(String className, String at) {
            this.className = className;
            this.at = at;
        }

        @Override
        public String toString() {
            return "TeachClass{" +
                    "className='" + className + '\'' +
                    ", at=" + at +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TeachClass that = (TeachClass) o;
            return  at.compareTo(that.at) == 0;
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("hasCode() not implemented");
        }
    }

    public boolean scheduleInstructor(String instructor, TeachClass obj){
        if (instructor == null) {
            throw new IllegalArgumentException("instructor name can not be null");
        }

        if (obj == null) {
            throw new IllegalArgumentException("TeachClass can not be null");
        }

        ArrayList<TeachClass> schedules = map.get(instructor);
        if (schedules == null) {
            ArrayList<TeachClass> list = new ArrayList<>();
            list.add(obj);
            map.put(instructor, list);
            System.out.printf("Instructor: %s assigned to teach %s", instructor, obj);
            return true;
        }

        for (TeachClass teachClass: schedules) {
            if (teachClass.equals(obj)) {
                System.out.printf("Instructor: %s already assign to teach %s", instructor, obj);
                return false;
            }
        }

        schedules.add(obj);
        System.out.printf("Instructor: %s assigned to teach %s", instructor, obj);
        return true;
    }


    public static void main(String[] args) {
        RegisterarScheduling registerarScheduling = new RegisterarScheduling();
        boolean b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Himanshu), new TeachClass("CLASS1", "09:30"));
        System.out.printf("\t, return: %b\n", b);

        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Aman), new TeachClass("CLASS1", "09:30"));
        System.out.printf("\t, return: %b\n", b);

        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Aman), new TeachClass("CLASS1", "09:30"));
        System.out.printf("\t, return: %b\n", b);

        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Anoop), new TeachClass("CLASS1", "09:30"));
        System.out.printf("\t, return: %b\n", b);
        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Anoop), new TeachClass("CLASS1", "09:30"));
        System.out.printf("\t, return: %b\n", b);
    }
}
