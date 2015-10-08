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
    private static final int ZERO_PAGES = 0;
    private static final int ONE_PAGE_PER_SHEET = 1;
    private final Paper paper;
    private int bwPages;
    private int colourPages;
    private int pagesPerSheet;
    private double cost;

    public Job(Paper paper) {
        this.paper = paper;
        this.bwPages = ZERO_PAGES;
        this.colourPages = ZERO_PAGES;
        this.pagesPerSheet = ONE_PAGE_PER_SHEET;
        cost = 0;
    }

    private void calculateCost() {
        cost = ((bwPages * paper.getBwInkCost() + colourPages * paper.getColourInkCost()) +
                paper.getSheetCost() * (bwPages + colourPages)) / pagesPerSheet;
        cost /= 100;
    }

    public void setBwPages(int bwPages) {
        this.bwPages = bwPages;
    }

    public void setColourPages(int colourPages) {
        this.colourPages = colourPages;
    }

    public double getCost() {
        calculateCost();
        return cost;
    }

    public void setTwoPagesPerSheet() {
        this.pagesPerSheet = 2;
    }

    @Override
    public String toString() {
        return paper.toString() +
                "\n\tPage counts:" +
                "\n\t\tBlack & white: " + bwPages +
                "\n\t\tColour: " + colourPages +
                "\n\tPages per sheet: " + pagesPerSheet +
                "\nJob cost: $" + String.format("%1$,.2f", getCost()) +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    }
}
