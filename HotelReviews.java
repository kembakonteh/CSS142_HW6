import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program reads a text file that contains integers and strings. It calculates the average of each column and 
 * determines the maximum value, and then perfomes a parallel sorthing on the corresponding strings. The program then
 * displays the result.
 * 
 * @author (Kemba Konteh)
 * @version (CSC142 07/28/2018)
 */
public class HotelReviews {
    private String[] hotels;
    private int[][] reviews;
    private double[] avgRanks;
    private int row;
    private int col;
    /**
     * This method is the constructor for the class HotelReviews.
     *
     * @param  filename  It accepts a string for the data to be read.
     * 
     * @return    
     */
    public HotelReviews(String name) throws FileNotFoundException {
        if (name.length() == 0)
            throw new IllegalArgumentException("The filename cannot be empty");

        readData(name);
        calculateAvgRankings();

    }

    /**
     * This is the method that accesses the number of hotels.
     *
     * @param  
     * 
     * @return    hotels.length It returns the length or the number of hotels. 
     */

    public int getHotelCount() {
        return hotels.length;
    }

    /**
     * This method accesses the ranks of a hotel depending on the given argument. 
     *
     * @param  rowIndex it accepts an integer that is within the range 0 to 3. It throws an exception if input is 
     * out of bound. 
     * 
     * @param  colIndex it accepts an integer that is within the range 0 to 4. It throws an exception if input is 
     * out of bound. 
     * 
     * @return   reviews[colIndex][rowIndex] it returns the rating of the at the index row by column.
     */

    public int getRankHotel(int rowIndex, int colIndex) {
        if (rowIndex > row || rowIndex < 0 || colIndex > col || colIndex < 0) throw new IndexOutOfBoundsException("" +
                " You must enter a review that is within the range 0-3 and hotel within the range 0-4. Please try again:");
        return reviews[colIndex][rowIndex];

    }

    /**
     * This method accesses the name of the hotel depending on the given argument. 
     *
     * @param  index  accepts an integer value that is within the range 0 to 4. It throws an exception if the input is
     * out of bound.
     * 
     * @return   hotels[index]  it returns the name of the hotel that is passed in corresponding to the indices
     * hotel
     */

    public String getHotel(int index) {
        if (index > col || index < 0)
            throw new IndexOutOfBoundsException("You must enter an index within the range 0-4");

        return hotels[index];

    }

    /**
     * This is the method that accesses the average ranking of the hotel passed in as an argument.
     *
     * @param  index  it accepts an integer value within the range of col 0 to 3. It throws an exception if out of bound.
     * 
     * @return    avgRanks[index] it returns the average rank of the hotel at the given index of the row by col.
     */

    public double getAvgRanks(int index) {
        if (index > col || index < 0)
            throw new IndexOutOfBoundsException("You must enter an index within the range 0-4");
        return avgRanks[index];
    }

    /**
     * This method reads data from the text file and also updates the instance variables. It doea not return anything.
     *
     * @param  name  It accepts the file name that is connected to a .txt file. It also throws an exception if the file
     * is not found
     * 
     * @return   
     */

    private void readData(String name) throws FileNotFoundException {
        Scanner input = new Scanner(new File(name));
        col = input.nextInt();
        row = input.nextInt();
        reviews = new int[row][col];
        hotels = new String[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                reviews[i][j] = input.nextInt();
            }
        }
        input.nextLine();
        for (int j = 0; j < col; j++) {
            hotels[j] = input.nextLine();
        }

    }

    /**
     * This is the method that calculates the average hotel reviews. It neither has a parameter nor returns anything
     * However the method is called at the constructor.
     * 
     * @param  
     * 
     * @return    
     */

    private void calculateAvgRankings() {
        avgRanks = new double[col];
        double total;
        for (int k = 0; k < col; k++) {
            total = 0;
            for (int l = 0; l < row; l++) {
                total += reviews[l][k];
            }
            avgRanks[k] = total / row;
        }
    }

    /**
     * This is the method that displays the first or initial table of the reviews.
     *
     * @param  
     * 
     * @return  s It returns the result or the text that is to be displayed.
     */

    public String displayRow(){  // displays row review
        System.out.println("Row Reviews");
        String s = "";

        for(int i = 0; i< row; i++){
            for(int j = 0; j < col; j ++){
                s += reviews[i][j] + "\t";
            }
            s+= "\n";
        }
        return s;
    }
    
    /**
     * This is the method that displays the sorted reviews by their rank
     *
     * @param  
     * @return  s it returns the result or text to be displayed
     */

    public String disPlayrank() {  // displays the ranked reviews
        sortByRanking();
        System.out.println("Ranked Reviews");
        String s = "";
        for(int i = 0; i< row; i++){
            for(int j = 0; j<reviews[i].length; j++){
                s += reviews[i][j] + "\t";
            }
            s+= "\n";
        }
        s +="\n";
        return s;
    }

    /**
     * This is the method that displays the names of the (reviewed) hotel
     *
     * @param
     * 
     * @return s It returns the text to be displayed.
     */

    public String displayHotelNames(){
        System.out.println("Hotels");
        String s = "";
        for(int i = 0; i<col; i++){
            s += hotels[i] + "\n";
        }
        return s;
    }

    /**
     * This is the method that displays the formatted average rankings as required.
     *
     * @param  
     * 
     * @return  s  it returns the text to be displayed
     */

    public String disPlayAvgRank(){
        System.out.println("Average Rankings");
        String s = "";
        for (int i = 0; i < col; i++){
            s += avgRanks[i] + "\t";
        }
        s +="\n";
        return s;
    }

    /**
     * This is the method that sorts all the data in descending order.
     *
     * @param  
     * 
     * @return    
     */

    public void sortByRanking(){
        int temp;
        double temp1;
        String temp2;
        for(int i = 0; i< col-1; i++){
            for(int j=i+1; j<col; j++){
                if(avgRanks[i]< avgRanks[j]){
                    temp1 = avgRanks[i];
                    avgRanks[i] = avgRanks[j];
                    avgRanks[j] = temp1;
                    temp2 = hotels[i];
                    hotels[i] = hotels[j];
                    hotels[j] = temp2;

                    for(int k =0; k <row; k++){
                        temp = reviews[k][i];
                        reviews[k][i] = reviews[k][j];
                        reviews[k][j] = temp;

                    }
                }
            }
        }

    }

    /**
     * This is the method that displays the sorted names by rank and also with corresponding rank.
     *
     * @param  
     * 
     * @return  s it returns the text to be displayed.
     */

    public String displayHotelAndRank(){
        sortByRanking();
        System.out.format( "%-50s %s", "Hotels", "Ratings");
        System.out.println();
        String s = "";
        for(int i = 0; i< col; i++){
            s += String.format("%-50s %.2f", hotels[i], avgRanks[i]);
            s += "\n";
        }
        return s;
    }

    /**
     * This is the method in which the constructor HotelReviews is instantiated and passed in the data.txt file.
     * The the object or code block is then surrounded with a try catch code block to account for the non 
     * existence of the file. It throws an exception if the file does not exists.
     *
     * @param 
     * 
     * @return   
     */
    public static void test()
    {
         try{
             HotelReviews r = new HotelReviews("data.txt");
        }catch(FileNotFoundException e){
             System.out.println("This file does not exist");
         }

    }
}
