package au.edu.swin.gevaux.rhys;

/**
 * Created by Rhys Gevaux on 8/10/15.
 * <p>
 * Display.java
 * -Handles all output (to console)
 *
 * @author Rhys Gevaux
 */
final class Display {
    public static void out(String output) {
        System.out.print(output);
    }

    public static void costOut(double output) {
        System.out.println("$" + String.format("%1$,.2f", output));
    }
}
