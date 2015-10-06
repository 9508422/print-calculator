package au.edu.swin.gevaux.rhys;

public class Main {

    public static void main(String[] args) {
        Paper a4Paper = new Paper(5, 15, 10);
        System.out.println(a4Paper.calculateCost(2, 1, false));
    }
}