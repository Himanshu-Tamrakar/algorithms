package chapter2.section5.solutions;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogestProcessingJobFirstHT {
    private static class Job implements Comparable<Job> {
        String name;
        int processingTime;

        public Job(String name, int time) {
            this.name = name;
            this.processingTime = time;
        }

        @Override
        public int compareTo(Job that) {
            if (this.processingTime < that.processingTime) return 1;
            else if (this.processingTime > that.processingTime) return -1;
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

    private static class Processor implements Comparable<Processor> {
        List<Job> jobs;
        String processorName;
        int totalTime;

        public Processor(String name) {
            this.jobs = new ArrayList<>();
            this.processorName = name;
            this.totalTime = 0;
        }

        public int getTotalTime() {
            return this.totalTime;
        }

        public void addJob(Job job) {
            this.jobs.add(job);
            this.calculateTotalTime();
        }

        private void calculateTotalTime() {
            for (Job j: jobs) {
                this.totalTime += j.processingTime;
            }
        }

        @Override
        public int compareTo(Processor that) {
            if (this.totalTime > that.totalTime) return 1;
            else if (this.totalTime <  that.totalTime) return -1;
            else return 0;
        }

        @Override
        public String toString() {
            return "Processor{" +
                    "jobs=" + jobs +
                    ", processorName='" + processorName + '\'' +
                    ", totalTime=" + totalTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        Job[] jobs  = new Job[] {
                new Job("Himanshu", 4),
                new Job("Monty", 1),
                new Job("Aman", 3),
                new Job("Harbu", 2),
                new Job("Shetty", 5),
                new Job("Anoop", 6)

        };
        MinPQ<Processor> processors= new MinPQ<>(m);
        Arrays.sort(jobs);

        for (int i = 0; i < m; i++) {
            String name = "M"+(i+1);
            processors.insert(new Processor(name));
        }

        for (int i = 0; i < n; i++) {
            Processor var0 = processors.delMin();
            var0.addJob(jobs[i]);
            processors.insert(var0);
        }

        while (!processors.isEmpty()) {
            System.out.printf("%s\n", processors.delMin());
        }

    }
}
