import java.io.File; //file class
import java.io.IOException; //handles errors
import java.io.FileWriter; //file writing
import java.time.format.DateTimeFormatter; //formatting date
import java.time.LocalDateTime; //get date and time
import java.io.BufferedReader; //reading csv
import java.io.FileReader; //reading csv
import java.io.FileNotFoundException; //handles if file can't be read

/**
* The file program contains methods that work behind
* the scenes and that deal with files.
*
* @author James Liang
* @version 1.0
* @since 2021-10-28
*/
public class file 
{
  /**
  * This method is used to create a file to store
  * future shopping list data and the current
  * date and time of the session. It doesn't have
  * any parameters and doesn't return anything
  */
  public static void fileCreator()
  {
    //get and format the date and time in UTC
    //https://www.javatpoint.com/java-get-current-date
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    //create save file
    try
    {
      File shoppinglist = new File("shoppinglist.txt");
      if(shoppinglist.createNewFile())
      {
        System.out.println("Created save file: " + shoppinglist.getName());
      }
      else
      {
        System.out.println("File already exists");
      }
    }
    catch (IOException e)
    {
      System.out.println("An error occured ):");
      e.printStackTrace();
    }

    //write new session onto file and time
    try
    {
      FileWriter myWriter = new FileWriter("shoppinglist.txt", true);
      myWriter.write("\n\nNew Session at " + dtf.format(now) + " UTC");
      myWriter.close();
      System.out.println("Sucessfully wrote to file");
    }
    catch (IOException e)
    {
      System.out.println("An error occured ):");
      e.printStackTrace();
    }
  }
  /**
  * This method is used to read the csv file and convert
  * it into a 2D array for future use.
  * 
  * @param file to get the csv file name
  * @param foodprints to get 2D array that needs to be modified
  * @return String[][] this returns the updated 2D array
  */
  //throws IOException to handle exceptions if coudn't read file
  public static String[][] csvToArray(String file, String[][] foodprints) throws IOException {
		try
    {
			//reads from the file
      BufferedReader infile = new BufferedReader (new FileReader(file));

			String line;
			System.out.println("Beginning to read the file now:");
      //set line to the contents of the file
			line=infile.readLine();

      int lineNum = 0;
      //checks to ensure there is a value on the line
			while (line!=null)
      {
				//splits contents and turns it into array elements
        String data[]=line.split(",");
        //converting the 1D array into a 2D array
        for (int i=0;i<data.length;i++)
        {
          if (i == 0)
            foodprints[lineNum][0] = data[i];
          else if (i == 1)
            foodprints[lineNum][1] = data[i];
        }
        //goes to the next line
				line=infile.readLine();
        lineNum++;
			}
			System.out.println("Closing file.");
			infile.close();
      
		}catch (FileNotFoundException e)
    {
			System.out.println("Incorrect filename or location. Please verify path and filename. ");
		}
    return foodprints;
  }
  /**
  * This method writes the chosen food, the amount of food,
  * and the emissions that food would have to the file.
  * I thought carbon footprint was most important
  *
  * @param foodChoice this is the chosen foodChoice
  * @param emissionsCarbon this is the calculated emissions
  * @param amount this is the amount of food
  * @param emissionsWater calcualted emissions water
  * @param emissionsLand calulated emissions land
  */
  public static void writeToFile(String foodChoice, double emissionsCarbon, double amount, double emissionsWater, double emissionsLand)
  {
    //writing results to file
    try
    {
      FileWriter myWriter = new FileWriter("shoppinglist.txt", true);
      myWriter.write("\n" + amount + "kg/L of " + foodChoice + "\nEmissions: " + emissionsCarbon + "kgCO₂eq Water Use: " + emissionsWater + "L Land Use: " + emissionsLand + "m²");
      myWriter.close();
      System.out.println("Sucessfully wrote to file");
    }
    catch (IOException e)
    {
      System.out.println("An error occured ):");
      e.printStackTrace();
    }
  }
  /**
  * This method is an overloaded method that writes
  * the total emissions to the file. Nothing is
  * returned from this method.
  *
  * @param totalCarbonEmissions this is the calculated
  * total for carbon emissions
  * @param totalWaterEmissions this is the calculated
  * total for water emissions
  * @param totalLandEmissions this is the calculated
  * total for land emissions
  */
  public static void writeToFile(double totalCarbonEmissions, double totalWaterEmissions, double totalLandEmissions)
  {
    //writing results to file
    try
    {
      FileWriter myWriter = new FileWriter("shoppinglist.txt", true);
      myWriter.write("\nTotal amount of emissions:\n" + "Emissions: " + Math.round(totalCarbonEmissions * 100.0)/100.0 + "kgCO₂eq Water Use: " + Math.round(totalWaterEmissions * 100.0)/100.0 + "L Land Use: " + Math.round(totalLandEmissions * 100.0)/100.0 + "m²");
      myWriter.close();
      System.out.println("Sucessfully wrote to file");
    }
    catch (IOException e)
    {
      System.out.println("An error occured ):");
      e.printStackTrace();
    }
  }
  /**
  * This method finds the size of the rows in the csv file.
  *
  * @param file this is the name of the csv file
  * @param type this is the size to be found (row/column)
  * @return int this is the size of the rows
  */
  public static int csvSize(String file, String type) throws IOException {
		int columns = 0;
    int lineNum = 0;
    try
    {
			//reads from the file
      BufferedReader infile = new BufferedReader (new FileReader(file));

			String line;
			System.out.println("Beginning to read the file now:");
      //set line to the contents of the file
			line=infile.readLine();

      //checks to ensure there is a value on the line
			String data[]=line.split(",");
      columns = data.length;
      while (line!=null)
      {
        //goes to the next line
				line=infile.readLine();
        lineNum++;
			}
			System.out.println("Closing file.");
			infile.close();
      
		}catch (FileNotFoundException e)
    {
			System.out.println("Incorrect filename or location. Please verify path and filename. ");
		}
    if (type.equalsIgnoreCase("rows"))
      return lineNum;
    return columns;
  }
}