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
    int repeat = 0;
    String foodprints[][] = new String [38][2];
    String footprintsWater[][] = new String [38][2];
    String footprintsLand[][] = new String [38][2];
    //calling methods
    file.fileCreator();
    foodprints = file.csvToArray("food-footprints.csv", foodprints);
    footprintsWater = file.csvToArray("food-footprints-wateruse.csv", footprintsWater);
    footprintsLand = file.csvToArray("food-footprints-landuse.csv", footprintsLand);
    output.greeting();
    //loops as long as user wants to continue
    do
    {
      String category = output.categoryChoice();
      System.out.println(category);
      String selection[] = output.foodOptions(category);
      String food = output.food(category, selection);
      System.out.println(food);
      output.gasEmissions(food, foodprints, footprintsWater, footprintsLand);
      output.confirmAddition(food);
      repeat = output.repetition();
    } while (repeat == 0);
    output.conclusion();
    //exits with exit code 0
    System.exit(0);
  } 
}