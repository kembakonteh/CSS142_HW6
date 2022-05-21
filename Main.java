import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main method that displays the results as required
 *
 * @author (Kemba Konteh)
 * @version (a version number or a date)
 */
public class Main{
    public static void main(String[]args)throws FileNotFoundException{
        HotelReviews r = new HotelReviews("data.txt");
        System.out.println(r.displayRow());
        System.out.println(r.disPlayAvgRank());
        System.out.println(r.displayHotelNames());
        System.out.println(r.disPlayrank());
        System.out.println(r.displayHotelAndRank());
    }
}
