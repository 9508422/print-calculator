package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 6/10/15.
 * <p>
 * Paper.java
 * -Holds all the information about the paper type
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

    public int getBwInkCost() {
        return bwInkCost;
    }

    public int getColourInkCost() {
        return colourInkCost;
    }

    public int getSheetCost() {
        return sheetCost;
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
