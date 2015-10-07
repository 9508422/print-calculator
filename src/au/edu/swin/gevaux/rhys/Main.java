package au.edu.swin.gevaux.rhys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Paper a4Paper = new Paper(5, 15, 10);
        for (int[] job : readFile("printjobs.csv")) {
            System.out.println(a4Paper.calculateCost(job));
        }
    }

    private static ArrayList<int[]> readFile(String fileName) throws IOException {
        ArrayList<int[]> jobs = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int[] job = {0, 0, 1};
                String[] lineSplit = line.split(",");
                job[0] = Integer.parseInt(lineSplit[0].trim());
                job[1] = Integer.parseInt(lineSplit[1].trim());
                if (lineSplit[2].trim().equals("true")) {
                    job[2] = 2;
                }
                jobs.add(job);
            }
        }
        return jobs;
    }
}