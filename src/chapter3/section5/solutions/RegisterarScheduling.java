package chapter3.section5.solutions;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

enum instructors {
    Himanshu, Aman, Anshul, Anoop
}

enum classes {
    Class1, Class2, Class3, Class4, Class5
}

public class RegisterarScheduling {
    private HashMap<String, TreeSet<TimeClass>> map;
    private HashMap<String, TreeSet<TimeClass>> inverseMap;
    private ArrayList<TeachClass> list;

    public RegisterarScheduling() {
        map = new HashMap<String, TreeSet<TimeClass>>();
        inverseMap = new HashMap<String, TreeSet<TimeClass>>();
        list = new ArrayList<>();
    }

    public boolean scheduleInstructor(String instructor, String classOf, String time){

        if (instructor == null || classOf == null || time == null) {
            throw new IllegalArgumentException("Instructor: " + instructor + ", classOf: " + classOf + ", time: " + time + " can not be null");
        }

        TreeSet instuctorList = map.get(instructor);
        if (instuctorList != null && instuctorList.contains(new TimeClass(time))) {
            System.out.printf("Instructor: %s is already assigned for given time: %s\n", instructor, time);
            return false;
        }

        TreeSet classList = inverseMap.get(classOf);
        if (classList != null && classList.contains(new TimeClass(time))) {
            System.out.printf("Class: %s is already assigned for given time %s\n", classOf, time);
            return false;
        }

        System.out.printf("instuctor %s is assigned to class %s at time: %s\n", instructor, classOf, time);
        if (instuctorList == null) {
            TreeSet<TimeClass> t = new TreeSet();
            t.add(new TimeClass(time));
            map.put(instructor, t);
        } else {
            instuctorList.add(new TimeClass(time));
        }

        if (classList == null) {
            TreeSet<TimeClass> t = new TreeSet();
            t.add(new TimeClass(time));
            inverseMap.put(classOf, t);
        }  else {
            classList.add(new TimeClass(time));
        }

        list.add(new TeachClass(instructor, classOf, time));
        return true;


    }

    private static class TimeClass implements Comparable<TimeClass> {
        String time;

        public TimeClass(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }


        @Override
        public int compareTo(TimeClass that) {
            return this.time.compareTo(that.time);
        }
    }

    private static class TeachClass {
        private String instructor;
        private String classOf;
        private String time;

        private TeachClass(String instructor, String classOf, String time) {
            this.instructor = instructor;
            this.classOf = classOf;
            this.time = time;
        }
    }



    public static void main(String[] args) {
        RegisterarScheduling registerarScheduling = new RegisterarScheduling();
        boolean b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Himanshu), String.valueOf(classes.Class5), String.valueOf("09:00"));
        System.out.printf("return: %b\n", b);

        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Aman), String.valueOf(classes.Class5), String.valueOf("10:00"));
        System.out.printf("return: %b\n", b);

        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Himanshu), String.valueOf(classes.Class5), String.valueOf("10:00"));
        System.out.printf("return: %b\n", b);

        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Himanshu), String.valueOf(classes.Class5), String.valueOf("12:00"));
        System.out.printf("return: %b\n", b);

        b = registerarScheduling.scheduleInstructor(String.valueOf(instructors.Anoop), String.valueOf(classes.Class5), String.valueOf("12:00"));
        System.out.printf("return: %b\n", b);
    }
}
