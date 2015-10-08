package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 8/10/15.
 *
 * @author Rhys Gevaux
 */
public class Display {
    private static final Display ourInstance = new Display();
    
    private Display() {
    }
    
    public static Display getInstance() {
        return ourInstance;
    }

    public void out(String output) {
        System.out.print(output);
    }

    public void printJobOutput(Paper paper, Job job) {
        out(paper.toString() +
                "\n\tPage counts:" +
                "\n\t\tBlack & white: " + job.getBwPages() +
                "\n\t\tColour: " + job.getColourPages() +
                "\n\tPages per sheet: " + job.getPagesPerSheet() +
                "\nJob cost: $" + String.format("%1$,.2f", paper.calculateCost(job)) +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
