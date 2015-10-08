package au.edu.swin.gevaux.rhys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Rhys Gevaux on 8/10/15.
 * <p>
 * Printer.java
 * -Controls the program:
 *  -Handles all input (from console)
 *  -Reads all files
 *  -Sends all output through the Display class
 *
 * @author Rhys Gevaux
 */
final class Printer {
    /**
     * Runs the program
     *
     * @param args - console arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        HashMap<String, Paper> paperTypes = readInPaperTypes();
        while (!input.equals("exit")) {
            Display.out("Type \"exit\" to exit the program\nInput desired paper type (A4):\n");
            input = scanner.nextLine().trim();
            Paper paper;
            if (paperTypes.containsKey(input.toUpperCase())) {
                boolean success = false;
                while (!input.equals("exit") && !success) {
                    paper = paperTypes.get(input);

                    Display.out("Input desired job file (printjobs.csv):\n");
                    input = scanner.nextLine().trim();
                    File file = new File(input);
                    if (file.isFile()) {
                        double totalCost = 0;
                        for (Job job : readInJobs(input, paper)) {
                            Display.out(job.toString());
                            totalCost += job.getCost();

                            Display.out("Total cost: ");
                            Display.costOut(totalCost);
                            Display.out("~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                        }
                        success = true;
                    } else if (!input.equals("exit")) {
                        Display.out("Job file does not exist!\n");
                    }
                }
            } else if (!input.equals("exit")) {
                Display.out("Paper type does not exist!\n");
            }
        }
        scanner.close();
    }

    /**
     * Creates a HashMap of all the paper types found in the file: "papertypes.csv"
     *
     * @return a HashMap of paper types with name keys and Paper values
     */
    private static HashMap<String, Paper> readInPaperTypes() {
        HashMap<String, Paper> paperTypes = new HashMap<>();
        for (String line : readFile("papertypes.csv")) {
            String[] lineSplit = line.split(",");

            String paperName = lineSplit[0].trim();
            int bwSingleCost = Integer.parseInt(lineSplit[1].trim());
            int colourSingleCost = Integer.parseInt(lineSplit[2].trim());
            int bwDoubleCost = Integer.parseInt(lineSplit[3].trim());
            int colourDoubleCost = Integer.parseInt(lineSplit[4].trim());

            paperTypes.put(paperName, new Paper(
                    paperName,
                    // the cost of the ink equals the difference between
                    // printing two double sided pages and a single sided page
                    bwDoubleCost * 2 - bwSingleCost,
                    colourDoubleCost * 2 - colourSingleCost,
                    // the cost of the sheet equals the cost of printing a
                    // single sided page minus the cost of one page of ink
                    bwSingleCost - (bwDoubleCost * 2 - bwSingleCost)
            ));
        }
        return paperTypes;
    }

    /**
     * Creates an ArrayList of all the jobs
     *
     * @param fileName - desired file to read
     * @param paper    - the paper type chosen
     * @return an ArrayList of all the jobs found in the given file
     */
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

    /**
     * A file reader class
     *
     * @param fileName - desired file to read
     * @return the lines of the file
     */
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