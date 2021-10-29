import java.io.IOException; // handles errors
/*
csv source
https://ourworldindata.org/environmental-impacts-of-food
*/

/**
* The Main program executes the methods from other files.
*
* @author James Liang
* @version 1.0
* @since 2021-10-28
*/
public class Main 
{
  //throws IOException to handle exceptions if coudn't read file
  public static void main(String[] args) throws IOException {
    //delcaring variables
    String fileCarbon = "food-footprints.csv";
    String fileWater = "food-footprints-wateruse.csv";
    String fileLand = "food-footprints-landuse.csv";
    int repeat = 0;
    //finding rows for arrays
    int csvSize = file.csvSize(fileCarbon);
    System.out.println(csvSize);
    int csvSizeWater = file.csvSize(fileWater);
    System.out.println(csvSizeWater);
    int csvSizeLand = file.csvSize(fileLand);
    System.out.println(csvSizeLand);
    //finding columns for arrays
    int csvColumns = file.csvColumns(fileCarbon);
    System.out.println(csvColumns);
    int csvColumnsWater = file.csvColumns(fileWater);
    System.out.println(csvColumnsWater);
    int csvColumnsLand = file.csvColumns(fileLand);
    System.out.println(csvColumnsLand);
    //creating arrays
    String foodprints[][] = new String [csvSize][csvColumns];
    String footprintsWater[][] = new String [csvSizeWater][csvColumnsWater];
    String footprintsLand[][] = new String [csvSizeLand][csvColumnsLand];
    //creating save file
    file.fileCreator();
    //gives 2D arrays values from csv
    foodprints = file.csvToArray(fileCarbon, foodprints);
    footprintsWater = file.csvToArray(fileWater, footprintsWater);
    footprintsLand = file.csvToArray(fileLand, footprintsLand);
    //greets user and gives facts
    output.greeting();
    //loops as long as user wants to continue
    do
    {
      //asks user which category
      String category = output.categoryChoice();
      System.out.println(category);
      //sets array to values of selected category
      String selection[] = output.foodOptions(category);
      //asks user which food 
      String food = output.food(category, selection);
      System.out.println(food);
      //tells user the emissions of the food
      output.gasEmissions(food, foodprints, footprintsWater, footprintsLand);
      //confirms user wants to add food to list
      output.confirmAddition(food);
      //asks user if they want to add more
      repeat = output.repetition();
    } while (repeat == 0);
    //gives user total carbon emissions from session
    output.conclusion();
    //exits with exit code 0
    System.exit(0);
  } 
}