package LWM2MServer.services;

/**
 * Created by Scott on 6/27/15.
 */
public class PrintMenu {
    private static String stars = new String(new char[50]).replace("\0", "-");
    private static String welcome = "          Welcome to Register Server         ";

    static public void menu() {

        System.out.println(stars);
        System.out.printf("|%48s|\n",welcome);
        System.out.println(stars);
        System.out.printf("|%-48s|\n", " 1: Register");
        System.out.printf("|%-48s|\n", "    1.1: Delete all the Registered Devices");
        System.out.printf("|%-48s|\n", "    1.2: Show all the Registered Devices");
        System.out.printf("|%-48s|\n", " 2: Device Management & Service Enablement");
        System.out.printf("|%-48s|\n", "    2.1: Read");
        System.out.printf("|%-48s|\n", "    2.2: Discover");
        System.out.printf("|%-48s|\n", "    2.3: Write");
        System.out.printf("|%-48s|\n", "    2.4: Write Attributes");
        System.out.printf("|%-48s|\n", "    2.5: Execute");
        System.out.printf("|%-48s|\n", "    2.6: Create");
        System.out.printf("|%-48s|\n", "    2.6: Delete");
        System.out.printf("|%-48s|\n", " 3: Information Reporting");
        System.out.printf("|%-48s|\n", "    3.1: Observe");
        System.out.printf("|%-48s|\n", "    3.2: Notify");
        System.out.printf("|%-48s|\n", "    3.3: Cancel Observation");
        System.out.println(stars);
        System.out.println("Please input here:");
    }
}
