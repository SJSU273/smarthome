package tv.services;

/**
 * Created by Scott on 6/26/15.
 */
public class PrintMenu {
    private static String stars = new String(new char[50]).replace("\0", "-");
    private static String welcome = "         Welcome to Smart Home             ";

    static public void menu() {

        System.out.println(stars);
        System.out.printf("|%48s|\n",welcome);
        System.out.println(stars);
        System.out.printf("|%-48s|\n", " 1: Bootstrap");
        System.out.printf("|%-48s|\n", " 2: Register");
        System.out.printf("|%-48s|\n", "    2.1 Register");
        System.out.printf("|%-48s|\n", "    2.2 update");
        System.out.printf("|%-48s|\n", "    2.3 De-Register");
        System.out.printf("|%-48s|\n", " 3: Device Management");
        System.out.printf("|%-48s|\n", " 4: Information Report");
        System.out.printf("|%-48s|\n", " 5: Tool");
        System.out.printf("|%-48s|\n", "    5.1: Change Channel");
        System.out.printf("|%-48s|\n", "    5.2: Show Status");
        System.out.println(stars);
        System.out.println("Please input here:");
    }
}
