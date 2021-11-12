import javax.swing.JOptionPane; //fancy GUI
import javax.swing.ImageIcon; //image icons

/**
* The UserInferface program contains methods that intereact
* directly with the user such as GUI inputs and outputs.
*
* @author James Liang
* @version 1.0
* @since 2021-10-28
*/
public class UserInterface 
{
  //creating global variables
  private static int index = 0;
  private static ImageIcon shoppinglisticon = new ImageIcon("images/shoppinglist.png");
  public static String emissionsCarbon = "";
  public static String emissionsWater = "";
  public static String emissionsLand = "";
  public static double totalCarbonEmissions = 0;
  public static double totalWaterEmissions = 0;
  public static double totalLandEmissions = 0;
  /**
  * This method is used to greet the user and
  * give them facts.
  *
  * @param N/A there are no parameters
  * @return void this returns nothing
  */
  public static void greeting()
  {
    //https://www.un.org/sustainabledevelopment/sustainable-consumption-production/
    //greeting messages and facts
    JOptionPane.showMessageDialog(null, "One third of food all food produced ends up rotten or spoiled.\nThat is around 1.3 billion tonnes of food!", "Did you know?", JOptionPane.INFORMATION_MESSAGE, shoppinglisticon);
    JOptionPane.showMessageDialog(null, "This program will help you pick food and teach you\nabout the impact of what you eat.\nFor comparisons please plan for a week of groceries.", "Shopping List Creator", JOptionPane.INFORMATION_MESSAGE, shoppinglisticon);
  }
  /**
  * This method is used to give the user different
  * categories of food to pick from.
  * 
  * @param N/A there are no parameters
  * @return String this returns the category chosen
  */
  public static String categoryChoice(){
    //gives user choices to pick from
    String[] categoryChoices = {"Fruits", "Vegetables", "Grains", "Protein", "Dairy", "Other"};
      String category = (String) JOptionPane.showInputDialog(null, "Pick a food group.", "Food Group Options", JOptionPane.QUESTION_MESSAGE, null, categoryChoices, categoryChoices[0]);
      return category;
  }
  /**
  * This method is used to find the list of items
  * that are related to the category selected.
  * 
  * @param category this is the category from before
  * @return String[] this returns the list of foods
  */
  public static String[] foodOptions(String category)
  {
    String optionsFood[] = new String [10]; 
    //check what category was selected and set above array to the desired items
    if (category.equals("Fruits"))
    {
      String fruits[] = {"Apples", "Bananas", "Berries & Grapes", "Citrus Fruit", "Groundnuts", "Nuts", "Tomatoes", "Other Fruit"};
      optionsFood = fruits;
    }
    else if (category.equals("Vegetables"))
    {
      String vegetables[] = {"Beet Sugar", "Brassicas", "Cane Sugar", "Cassava", "Onions & Leeks", "Peas", "Potatoes", "Root Vegetables", "Other Vegetables"};
      optionsFood = vegetables;
    }
    else if (category.equals("Grains"))
    {
      String grains[] = {"Barley", "Maize", "Oatmeal", "Rice", "Wheat & Rye"};
      optionsFood = grains;
    }
    else if (category.equals("Protein"))
    {
      String protein[] = {"Beef (beef herd)", "Beef (dairy herd)", "Eggs", "Fish (farmed)", "Lamb & Mutton", "Pig Meat", "Poultry Meat", "Prawns (farmed)", "Tofu (soybeans)", "Other Pulses"};
      optionsFood = protein;
    }
    else if (category.equals("Dairy"))
    {
      String dairy[] = {"Cheese", "Milk", "Soymilk"};
      optionsFood = dairy;
    }
    else if (category.equals("Other"))
    {
      String other[] = {"Coffee", "Dark Chocolate", "Wine"};
      optionsFood = other;
    }
    return optionsFood;
  }
  /**
  * This method asks the user to pick one of the foods
  * from a list of foods
  * 
  * @param category this is the category from before
  * @param optionsFood this is the list of foods
  * @return String this returns the food chosen
  */
  public static String pickFood(String category, String[] optionsFood)
  {
    //asks user to pick food from list
    String food = (String) JOptionPane.showInputDialog(null,"Pick a food.", category, JOptionPane.QUESTION_MESSAGE, null, optionsFood, optionsFood[0]);
    return food;
  }
  /**
  * This method finds the emissions of the food that
  * was selected.
  *
  * @param foodChoice this is the food that was chosen
  * @param foodprints this is the csv contianing emissions
  * @return void this returns nothing
  */
  public static void gasEmissions(String foodChoice, String[][] foodprints, String[][] footprintsWater, String[][] footprintsLand)
  {
    //finds foods and their matching emissions
    for (int i=0;i<foodprints.length;i++)
    {
      if (foodprints[i][0].equalsIgnoreCase(foodChoice))
      {
        index = i;
        emissionsCarbon = foodprints[index][1];
        emissionsWater = footprintsWater[index][1];
        emissionsLand = footprintsLand[index][1];
      }
    }
    //output telling user findings
    JOptionPane.showMessageDialog(null, "The carbon emissions of " + foodChoice + " are:\n" + emissionsCarbon + " kilograms of carbon dioxide per kilogram of food product\n" + emissionsWater + " litres of water per kilogram of food product\n" + emissionsLand + " metres squared of land per kilogram of food product", "Emissions", JOptionPane.INFORMATION_MESSAGE, shoppinglisticon);
  }
  /**
  * This method confirms the addition of the food to the
  * shopping list. If yes, it asks the user how much of 
  * the product the user wants. It then calls another method
  * to write the selections to the file.
  *
  * @param foodChoice this is the chosen food
  * @return void this returns nothing
  */
  public static void confirmAddition(String foodChoice)
  {
    //asks user if they are sure about their option
    int confirm = JOptionPane.showConfirmDialog(null, "Would you like to add " + foodChoice + " to your shopping list?", "Confirmation", JOptionPane.YES_NO_OPTION);
    //if user is sure, emissions are calcuated and written to file
    if (confirm == 0)
    {
      double amount = Double.parseDouble(JOptionPane.showInputDialog("How many kilograms/litres do you want?"));
      double adjustedEmissions = Math.round(amount * Double.parseDouble(emissionsCarbon) * 100.0)/100.0;
      double adjustedEmissionsWater = Math.round(amount * Double.parseDouble(emissionsWater) * 100.0)/100.0;
      double adjustedEmissionsLand = Math.round(amount * Double.parseDouble(emissionsLand) * 100.0)/100.0;
      totalCarbonEmissions += adjustedEmissions;
      totalWaterEmissions += adjustedEmissionsWater;
      totalLandEmissions += adjustedEmissionsLand;
      Backend.writeToFile(foodChoice, adjustedEmissions, amount, adjustedEmissionsWater, adjustedEmissionsLand);
    }
  }
  /**
  * This method will ask if the user wants to continue
  * adding products to their shopping list. There are no
  * parameters.
  *
  * @param N/A there are no parameters
  * @return int this returns the repeat value for the loop
  */
  public static int repetition()
  {
    int repeat = 1;
    //asks user if they'd like to repeat
    repeat = JOptionPane.showConfirmDialog(null, "Would you like select another item?", "Repetition", JOptionPane.YES_NO_OPTION);
    //yes = 0, no = 1
    return repeat;
  }
  /**
  * This method tells the user the total emissions of
  * their shopping list.
  *
  * @param N/A there are no parameters
  * @return double this returns the total carbon emissions
  */
  public static double conclusion()
  {
    JOptionPane.showMessageDialog(null, "The total emissions for this shopping list:\n" + Math.round(totalCarbonEmissions * 100.0)/100.0 + " kilograms of carbon dioxide per kilogram of food product\n" + Math.round(totalWaterEmissions * 100.0)/100.0 + " litres of water per kilogram of food product\n" + Math.round(totalLandEmissions * 100.0)/100.0 + " metres squared of land per kilogram of food product", "Total Emissions", JOptionPane.INFORMATION_MESSAGE, shoppinglisticon);
    Backend.writeToFile(totalCarbonEmissions, totalWaterEmissions, totalLandEmissions);
    return totalCarbonEmissions;
  }
  /**
  * This method compares the carbon emissions of the
  * shopping list to what an average Canadian would have
  * for that same period.
  *
  * @param totalCarbonEmissions this is the carbon emissions
  * @return void this returns nothing
  */
  public static void comparedToOthers(double totalCarbonEmissions)
  {
    //https://ourworldindata.org/explorers/co2?facet=none&country=~CAN&Gas=CO%E2%82%82&Accounting=Production-based&Fuel=Total&Count=Per+capita
    //Canadian carbon emissions per capita in 2020 is 14.20 tonnes
    //https://css.umich.edu/factsheets/carbon-footprint-factsheet
    //Food emissions could for 10-30% of a household's emissions, assume 20%
    //convert to tonne to kg
    double weeklyFoodEmissions = 14.20*1000*0.2/52;
    //explain assumptions
    JOptionPane.showMessageDialog(null, "The following assumptions are made:\nYou are Canadian\nFood is 20% of your carbon foodprint\nThese groceries are for a week of consumption\n2020 data is reflective of current emissions", "Disclaimer", JOptionPane.INFORMATION_MESSAGE, shoppinglisticon);
    //find how much more or less carbon emissions in percentage
    double differenceEmissions = Math.round((weeklyFoodEmissions - totalCarbonEmissions)/weeklyFoodEmissions * 100 * 100.0)/100.0;
    //different response based on if more or less than average Canadian
    if (differenceEmissions < 0)
    {
      JOptionPane.showMessageDialog(null, "You use " + (differenceEmissions * -1) + "% more carbon per capita\ncompared to other Canadians", "Comparison", JOptionPane.INFORMATION_MESSAGE, shoppinglisticon);
    }
    else if (differenceEmissions >= 0)
    {
      JOptionPane.showMessageDialog(null, "You use " + differenceEmissions + "% less carbon per capita\ncompared to other Canadians", "Comparison", JOptionPane.INFORMATION_MESSAGE, shoppinglisticon);
    }
  }
}