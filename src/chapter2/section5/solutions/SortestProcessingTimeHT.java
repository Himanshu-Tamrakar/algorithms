package chapter2.section5.solutions;

import java.sql.Time;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;

public class SortestProcessingTimeHT {
    private static class Job implements Comparable<Job> {
        String name;
        int processingTime;
        public Job(String name, int time) {
            this.name = name;
            this.processingTime = time;
        }

        @Override
        public int compareTo(Job that) {
            if (this.processingTime > that.processingTime) return 1;
            else if(this.processingTime < that.processingTime) return -1;
            else return 0;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "name='" + name + '\'' +
                    ", processingTime=" + processingTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Job[] jobs  = new Job[n];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int pt = scanner.nextInt();
            jobs[i] = new Job(name, pt);
        }

        Arrays.sort(jobs);

        for (Job job: jobs) {
            System.out.printf("%s\n", job);
        }
    }
}
