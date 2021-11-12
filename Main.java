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
    int csvSizeCarbon = Backend.csvSize(fileCarbon, "rows");
    System.out.println(csvSizeCarbon);
    int csvSizeWater = Backend.csvSize(fileWater, "rows");
    System.out.println(csvSizeWater);
    int csvSizeLand = Backend.csvSize(fileLand, "rows");
    System.out.println(csvSizeLand);
    //finding columns for arrays
    int csvColumnsCarbon = Backend.csvSize(fileCarbon, "columns");
    System.out.println(csvColumnsCarbon);
    int csvColumnsWater = Backend.csvSize(fileWater, "columns");
    System.out.println(csvColumnsWater);
    int csvColumnsLand = Backend.csvSize(fileLand, "columns");
    System.out.println(csvColumnsLand);
    //creating arrays
    String foodprintsCarbon[][] = new String [csvSizeCarbon][csvColumnsCarbon];
    String footprintsWater[][] = new String [csvSizeWater][csvColumnsWater];
    String footprintsLand[][] = new String [csvSizeLand][csvColumnsLand];
    //creating save file
    Backend.fileCreator();
    //gives 2D arrays values from csv
    foodprintsCarbon = Backend.csvToArray(fileCarbon, foodprintsCarbon);
    footprintsWater = Backend.csvToArray(fileWater, footprintsWater);
    footprintsLand = Backend.csvToArray(fileLand, footprintsLand);
    //greets user and gives facts
    UserInterface.greeting();
    //loops as long as user wants to continue
    do
    {
      //asks user which category
      String category = UserInterface.categoryChoice();
      System.out.println(category);
      //sets array to values of selected category
      String selection[] = UserInterface.foodOptions(category);
      //asks user which food 
      String food = UserInterface.pickFood(category, selection);
      System.out.println(food);
      //tells user the emissions of the food
      UserInterface.gasEmissions(food, foodprintsCarbon, footprintsWater, footprintsLand);
      //confirms user wants to add food to list
      UserInterface.confirmAddition(food);
      //asks user if they want to add more
      repeat = UserInterface.repetition();
    } while (repeat == 0);
    //gives user total carbon emissions from session
    double totalCarbonEmissions = UserInterface.conclusion();
    //compares carbon emissions
    UserInterface.comparedToOthers(totalCarbonEmissions);
    //exits with exit code 0
    System.exit(0);
  } 
}