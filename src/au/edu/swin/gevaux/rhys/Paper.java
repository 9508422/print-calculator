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

    public double calculateCost(int bwInkPages, int colourInkPages, boolean isDoubleSided) {
        int cost = (bwInkPages * bwInkCost + colourInkPages * colourInkCost) +
                sheetCost * (bwInkPages + colourInkPages);
        if (isDoubleSided) {
            return (double) cost / 200;
        } else {
            return (double) cost / 100;
        }
    }
}
