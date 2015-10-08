package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 6/10/15.
 *
 * @author Rhys Gevaux
 */
class Paper {
    private final String type;
    private final int bwInkCost;
    private final int colourInkCost;
    private final int sheetCost;

    public Paper(String type, int bwInkCost, int colourInkCost, int sheetCost) {
        this.type = type;
        this.bwInkCost = bwInkCost;
        this.colourInkCost = colourInkCost;
        this.sheetCost = sheetCost;
    }

    /**
     * @param job - job to calculate cost of
     * @return the cost of the job in dollars
     */
    public double calculateCost(Job job) {
        double cost = ((job.getBwPages() * bwInkCost + job.getColourPages() * colourInkCost) +
                sheetCost * (job.getBwPages() + job.getColourPages())) / job.getPagesPerSheet();
        return cost / 100;
    }

    @Override
    public String toString() {
        return "Paper type: " + type +
                "\n\tCost per sheet: " + sheetCost +
                "\n\tInk costs per page:" +
                "\n\t\tBlack & white: " + bwInkCost +
                "\n\t\tColour: " + colourInkCost;
    }
}
