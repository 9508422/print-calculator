package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 8/10/15.
 *
 * @author Rhys Gevaux
 */
class Job {
    private Paper paper;
    private int bwPages;
    private int colourPages;
    private int pagesPerSheet;
    private double cost;

    public Job(Paper paper) {
        this.paper = paper;
        this.bwPages = 0;
        this.colourPages = 0;
        this.pagesPerSheet = 1;
        cost = 0;
    }

    public void calculateCost() {
        cost = ((bwPages * paper.getBwInkCost() + colourPages * paper.getColourInkCost()) +
                paper.getSheetCost() * (bwPages + colourPages)) / pagesPerSheet;
        cost /= 100;
    }

    public int getBwPages() {
        return bwPages;
    }

    public void setBwPages(int bwPages) {
        this.bwPages = bwPages;
    }

    public int getColourPages() {
        return colourPages;
    }

    public void setColourPages(int colourPages) {
        this.colourPages = colourPages;
    }

    public double getCost() {
        calculateCost();
        return cost;
    }

    public int getPagesPerSheet() {
        return pagesPerSheet;
    }

    public void setPagesPerSheet(int pagesPerSheet) {
        this.pagesPerSheet = pagesPerSheet;
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
