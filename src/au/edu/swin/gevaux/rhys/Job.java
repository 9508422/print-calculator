package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 8/10/15.
 * <p>
 * Job.java
 * -Holds all the information about the job
 * -Calculates the cost of the job
 *
 * @author Rhys Gevaux
 */
class Job {
    private final Paper paper;
    private int bwPages;
    private int colourPages;
    private int pagesPerSheet;

    public Job(Paper paper, int bwPages, int colourPages, int pagesPerSheet) {
        this.paper = paper;
        this.bwPages = bwPages;
        this.colourPages = colourPages;
        this.pagesPerSheet = pagesPerSheet;
    }

    private double calculateCost() {
        double cost = (bwPages * paper.getBwInkCost() + colourPages * paper.getColourInkCost()) +
                paper.getSheetCost() * (bwPages + colourPages) / pagesPerSheet;
        cost /= 100;
        return cost;
    }

    public double getCost() {
        return calculateCost();
    }

    @Override
    public String toString() {
        return paper.toString() +
                "\n\tPage counts:" +
                "\n\t\tBlack & white: " + bwPages +
                "\n\t\tColour: " + colourPages +
                "\n\tPages per sheet: " + pagesPerSheet +
                "\nJob cost: $" + String.format("%1$,.2f", getCost()) +
                "\n--------------------------\n";
    }
}
