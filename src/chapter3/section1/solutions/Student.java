package chapter3.section1.solutions;

import java.util.Objects;

public class Student {
    private String name;
    private int section;
     public Student(String name, int section) {
         this.name = name;
         this.section = section;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return (this.section == that.section) && (this.name.equals(that.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, section);
    }
}
