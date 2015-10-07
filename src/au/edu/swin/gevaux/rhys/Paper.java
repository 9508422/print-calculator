package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 6/10/15.
 *
 * @author Rhys Gevaux
 */
public class Paper {
    private final int bwInkCost;
    private final int colourInkCost;
    private final int sheetCost;

    public Paper(int bwInkCost, int colourInkCost, int sheetCost) {
        this.bwInkCost = bwInkCost;
        this.colourInkCost = colourInkCost;
        this.sheetCost = sheetCost;
    }

    /**
     * @param job - job information: 0 = black and white pages, 1 = colour pages, 2 = number of pages per paper
     * @return the cost of the job in dollars
     */
    public double calculateCost(int[] job) {
        double cost = ((job[0] * bwInkCost + job[1] * colourInkCost) + sheetCost * (job[0] + job[1])) / job[2];
        return cost / 100;
    }
}
