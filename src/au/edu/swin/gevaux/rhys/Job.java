package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 8/10/15.
 *
 * @author Rhys Gevaux
 */
class Job {
    private int bwPages;
    private int colourPages;
    private int pagesPerSheet;

    public Job() {
        this.bwPages = 0;
        this.colourPages = 0;
        this.pagesPerSheet = 1;
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

    public int getPagesPerSheet() {
        return pagesPerSheet;
    }

    public void setPagesPerSheet(int pagesPerSheet) {
        this.pagesPerSheet = pagesPerSheet;
    }
}
