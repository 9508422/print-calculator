package au.edu.swin.gevaux.rhys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Rhys Gevaux on 8/10/15.
 *
 * @author Rhys Gevaux
 */
final class Printer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        HashMap<String, Paper> paperTypes = readInPaperTypes();
        while (!input.equals("exit")) {
            Display.out("Input desired paper type (A4):\n");
            Paper paper = paperTypes.get(scanner.nextLine().trim().toUpperCase());
            Display.out("Input desired job file (printjobs.csv):\n");
            double totalCost = 0;
            for (Job job : readInJobs(scanner.nextLine().trim(), paper)) {
                Display.out(job.toString());
                totalCost += job.getCost();
            }
            Display.out("Total cost: ");
            Display.costOut(totalCost);
            Display.out("~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
    }

    private static HashMap<String, Paper> readInPaperTypes() {
        HashMap<String, Paper> paperTypes = new HashMap<>();
        for (String line : readFile("papertypes.csv")) {
            String[] lineSplit = line.split(",");
            Paper paper = new Paper(
                    lineSplit[0].trim(),
                    Integer.parseInt(lineSplit[3].trim()) * 2 - Integer.parseInt(lineSplit[1].trim()),
                    Integer.parseInt(lineSplit[4].trim()) * 2 - Integer.parseInt(lineSplit[2].trim()),
                    Integer.parseInt(lineSplit[1].trim()) - (Integer.parseInt(lineSplit[3].trim()) * 2 -
                            Integer.parseInt(lineSplit[1].trim()))
            );
            paperTypes.put(lineSplit[0].trim(), paper);
        }
        return paperTypes;
    }

    private static ArrayList<Job> readInJobs(String fileName, Paper paper) {
        ArrayList<Job> jobs = new ArrayList<>();
        for (String line : readFile(fileName)) {
            Job job = new Job(paper);
            String[] lineSplit = line.split(",");
            job.setBwPages(Integer.parseInt(lineSplit[0].trim()));
            job.setColourPages(Integer.parseInt(lineSplit[1].trim()));
            if (lineSplit[2].trim().equals("true")) {
                job.setTwoPagesPerSheet();
            }
            jobs.add(job);
        }
        return jobs;
    }

    private static ArrayList<String> readFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            Display.out("File does not exist.");
        }
        return lines;
    }
}