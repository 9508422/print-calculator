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

        HashMap<String, Paper> paperTypes = readInPaperTypes();

        Display.out("Type \"exit\" to exit the program\n");

        while (true) {
            Display.out("Input desired paper type (A4):\n");

            String input;
            String tempInput = scanner.nextLine().trim();
            if (tempInput.toLowerCase().equals("exit")) {
                break;
            } else {
                // it is assumed paper types will be differentiated by more than case
                input = tempInput.toUpperCase();
            }

            if (paperTypes.containsKey(input)) {
                // allows correct output to be given - only true when the entire process is successful
                boolean success = false;

                Paper paper = paperTypes.get(input);

                while (!success) {
                    Display.out("Input desired job file (printjobs.csv):\n");

                    tempInput = scanner.nextLine().trim();
                    if (input.toLowerCase().equals("exit")) {
                        break;
                    } else {
                        input = tempInput;
                    }

                    File file = new File(input);
                    if (file.isFile()) {
                        ArrayList<Job> jobs = readInJobs(input, paper);
                        if (jobs != null) {
                            double totalCost = 0;
                            Display.out("~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

                            for (Job job : jobs) {
                                Display.out(job.toString());
                                totalCost += job.getCost();
                            }

                            Display.out("Total cost: ");
                            Display.costOut(totalCost);
                            Display.out("~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

                            success = true;
                        } else {
                            Display.out("Invalid job in jobs file!\n");
                        }
                    } else {
                        Display.out("Job file does not exist!\n");
                    }
                }
            } else {
                Display.out("Paper type does not exist!\n");
            }
        }
        scanner.close();
    }

    /**
     * Creates a HashMap of all the paper types found in the file: "papertypes.csv"
     * Makes all paper types upper case, it can be assumed paper types are not case-sensitive
     * Currently serves little purpose but allows for more paper types in the future
     *
     * @return a HashMap of paper types with name keys and Paper values
     */
    private static HashMap<String, Paper> readInPaperTypes() {
        HashMap<String, Paper> paperTypes = new HashMap<>();
        for (String line : readFile("papertypes.csv")) {
            String[] lineSplit = line.split(",");

            String paperName = lineSplit[0].trim().toUpperCase();
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
     * Returns null if there is an invalid job
     * If there are extra values given in the jobs list passed the initial three, they are ignored
     *
     * @param fileName - desired file to read
     * @param paper    - the paper type chosen
     * @return an ArrayList of all the jobs found in the given file
     */
    private static ArrayList<Job> readInJobs(String fileName, Paper paper) {
        ArrayList<Job> jobs = new ArrayList<>();
        for (String line : readFile(fileName)) {
            String[] lineSplit = line.split(",");

            int bwPages;
            int colourPages;
            int pagesPerSheet;
            try {
                // page counts must be integers
                bwPages = Integer.parseInt(lineSplit[0].trim());
                colourPages = Integer.parseInt(lineSplit[1].trim());

                // page counts must be larger than zero
                if (bwPages < 0 || colourPages < 0) {
                    return null;
                }

                // must be either single or double sided
                if (lineSplit[2].trim().equals("false")) {
                    pagesPerSheet = 1;
                } else if (lineSplit[2].trim().equals("true")) {
                    pagesPerSheet = 2;
                } else {
                    return null;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                return null;
            }

            Job job = new Job(paper, bwPages, colourPages, pagesPerSheet);
            jobs.add(job);
        }
        return jobs;
    }

    /**
     * A file reader function
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