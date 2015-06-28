package LWM2MBootstrapServer.services;

/**
 * Created by Scott on 6/27/15.
 */
public class PrintMenu {
    private static String stars = new String(new char[50]).replace("\0", "-");
    private static String welcome = "        Welcome to Bootstrap Server         ";

    static public void menu() {

        System.out.println(stars);
        System.out.printf("|%48s|\n",welcome);
        System.out.println(stars);
        System.out.printf("|%-48s|\n", " 0: Delete all the Bootstrap Devices");
        System.out.printf("|%-48s|\n", " 1: Show all the Bootstrap Devices");
        System.out.println(stars);
        System.out.println("Please input here:");
    }
}
