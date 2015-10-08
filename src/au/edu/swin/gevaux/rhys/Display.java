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

    public void costOut(double output) {
        System.out.println("$" + String.format("%1$,.2f", output));
    }
}
