package ui;


import model.Bowl;
import model.Event;
import model.EventLog;
import model.GenerateCustomer;
import model.Order;
import model.Tab;
import model.Recipe;
import ui.LogPrinter;
import persistence.JsonReadable;
import persistence.JsonWritable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.io.FileNotFoundException;
import java.io.IOException;

// a resturant where users can take, make, and serve orders
// (some code references the flashcard reviewer class in the ui package of lab 4 and
// the puppy caller lab)
public class Resturant implements LogPrinter {
    private static final String JSON_STORE = "./data/Tab.json";
    private GenerateCustomer gc;
    private Tab tab;
    private Order order;
    private Scanner scanner;
    private boolean running = true;
    private Recipe recipe;
    private Bowl bowl;
    private JsonWritable jsonWritable;
    private JsonReadable jsonReadable;

    public Resturant() {
        this.scanner = new Scanner(System.in);
        this.gc = new GenerateCustomer();
        this.tab = new Tab();
        this.recipe = new Recipe();
        this.bowl = new Bowl();
        jsonWritable = new JsonWritable(JSON_STORE);
        jsonReadable = new JsonReadable(JSON_STORE);

        

        while (this.running) {
            startMenu();
      
        }

    }
    /*
     * EFFECTS: gives users the option to start new game, continue from old game, or quit.
     */

    public void startMenu() {
        divider();

        System.out.println("Welcome to [Game Name]");
        System.out.println("1 : New Game ");
        System.out.println("2 : Continue ");
        System.out.println("3 : Quit ");
        
        String inputStartMenu = this.scanner.nextLine();
        processStart(inputStartMenu);

    }
    /*
     * MODIFIES: this
     * EFFECTS: Processes user input from main menu
     * (references displayMenu() from flashcard reviewer)
     */

    public void processStart(String input) {
        switch (input) {
            case "1": 
                mainMenu();
                break; 
            
            case "2": 
                loadTab();
                loadedFile();
                break;

            case "3":
                quit();
                break;

            default: 
                System.out.println("Invalid, try again :( ");
                startMenu();
                String inputStart = this.scanner.nextLine();
                processUserInput(inputStart);
                
        }
        
    } 

    /*
     * EFFECTS: outputs the day the user is on and the commands that can be used   
     */
    public void mainMenu() {
        divider();

        System.out.println("You have a new order!");
        order = new Order(gc.randomCustomer());
        System.out.println("" + order.getCustomersName() + " : " + order.getDishesName());

        addOrder();
        String input = this.scanner.nextLine();
        processUserInput(input);
        // stub;
    }

    /*
     * EFFECTS: allows users to input what action to perform in the main menu
     * (references displayMenu() from flashcard reviewer)
     */
    public void addOrder() {
        divider();

        System.out.println("Add order to tab?");
        System.out.println("1 : yes :) ");
        System.out.println("2 : no :( ");
        

        divider();

    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input from main menu
     * (references displayMenu() from flashcard reviewer)
     */

    public void processUserInput(String input) {
        switch (input) {
            case "1": 
                tab.addOrderToTab(order);
                System.out.println("Your order was added!");
                afterTabCall();
                break; 
            
            case "2": 
                tab.addOrderToTab(order);
                System.out.println("Your order was added anyways! You have no choice >:) ");
                afterTabCall();
                break;

            default: 
                System.out.println("Invalid, try again :( ");
                addOrder();
                String input3 = this.scanner.nextLine();
                processUserInput(input3);
                
        }
        divider();
        
    } 

    /*
     * EFFECTS: Displays options of what a user can do after adding order to tab
     */
    public void afterTabMenu() {
        divider();

        System.out.println("what would you like to do?");
        System.out.println("1 : view orders ");
        System.out.println("2 : take out bowl ");

        divider();
    }

     /*
     * MODIFIES: this
     * EFFECTS: Processes user input from after tab menu
     */

    public void inputAfterTab(String input) {
        switch (input) {
            case "1": 
                for (Order order : tab.getAllOrders()) {
                    System.out.println(order.getCustomersName() + " : " + order.getDishesName());
                }
                afterTabMenu(); 
                String inputs = this.scanner.nextLine();
                inputAfterTab(inputs);
                break;
            
            case "2": 
                bowlDisplay();
                String input5 = this.scanner.nextLine();
                bowlDisplayInput(input5);
                break;

            default: 
                System.out.println("Invalid, try again :( )");
                afterTabMenu();
                String input4 = this.scanner.nextLine();
                inputAfterTab(input4);
                break;


        }
        divider();
    }

     /*
     * EFFECTS: creates a ascii art of ingredients board
     */
    public void ingredientBoard() {
        System.out.println(" -------------------------");
        System.out.println("| 1. beef    4. salt       |");
        System.out.println("| 2. lettuce 5. Ice cream  |");
        System.out.println("| 3. potato  6. cheese     |");
        System.out.println(" -------------------------");
    }

     /*
     * EFFECTS: Displays all the ingredients that can be added and actions users can perform
     */
    public void bowlDisplay() {
        ingredientBoard();
        System.out.println("1 : check recipe");
        System.out.println("2 : add ingredient");
        System.out.println("3 : clear bowl");
        System.out.println("4 : serve order");
        System.out.println("5 : quit");


        divider();
        
    }

     /*
     * MODIFIES: this
     * EFFECTS: processes user input for bowlDisplay
     */
    public void bowlDisplayInput(String input) {
        switch (input) {
            case "1": 
                System.out.println(recipe.getRecipe(tab.getAllOrders().get(0)));
                returnToBowlMenu();
                break;
            
            case "2": 
                addIgredient();
                String inputIngredient = this.scanner.nextLine();
                addToBowl(inputIngredient);
                break;

            case "3": 
                clearBowl();
                break;

            case "4": 
                serveOrder();
                break;

            case "5": 
                quit();
               

            default: 
                System.out.println("Invalid, try again :(");
                break;
        }
        divider();
    
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks to see if ingredients in the bowl match the recipie for the dish
     */
    public void serveOrder() {
        if (bowl.size() != 0 && recipe.getRecipe(tab.getAllOrders().get(0)).containsAll(bowl.getBowl())) {
            tab.getAllOrders().get(0).serveOrder();
            tab.removeOrder(tab.getAllOrders().get(0));
            System.out.println("Order served :D ");
            mainMenu();
        } else {
            System.out.println("Incorrect recipe :(");
            returnToBowlMenu();
    
        }
    }

    //EFFECTS: displays prompt for user to add ingredients
    public void addIgredient() {
        System.out.println("what ingredient would you like to add? ");
        System.out.println("1 : beef");
        System.out.println("2 : lettuce");
        System.out.println("3 : potato");
        System.out.println("4 : salt");
        System.out.println("5 : Ice cream");
        System.out.println("6 : cheese");
    }

    //MODIFIES: this
    //EFFECTS: adds input from user to bowl
    public void addToBowl(String input) {
        switch (input) {
            case "1":
                bowl.add("beef");
                returnToBowlMenu();

            case "2":
                bowl.add("lettuce");
                returnToBowlMenu();

            case "3":
                bowl.add("potato");
                returnToBowlMenu();


            case "4":
                bowl.add("salt");
                returnToBowlMenu();
    
            case "5":
                bowl.add("Ice cream");
                returnToBowlMenu();

            case "6":
                bowl.add("cheese");
                returnToBowlMenu();
    

            default:
                System.out.println("Invalid");
                returnToBowlMenu();

        }
    }

    /*
     * MODDIFIES: this
     * EFFECTS: returns user to the bowlDisplay menu
     */
    public void returnToBowlMenu() {
        bowlDisplay();
        String input = this.scanner.nextLine();
        bowlDisplayInput(input);
    }

    // EFFECTS: asks user if they want to save before they quit
    public void quitMenu() {
        divider();

        System.out.println("Would you like to save before you quit?");
        System.out.println("1 : yes");
        System.out.println("2 : no");
        
        String inputQuit = this.scanner.nextLine();
        quitInput(inputQuit);
        divider();
    }

    //MODIFIES: this
    //EFFECTS: processes user input after quit menu
    public void quitInput(String input) {
        switch (input) {
            case "1":
                saveTab();
                this.running = false;
                System.out.println("Thanks for playing :) ");
                printLog(EventLog.getInstance());
                System.exit(0);
                

            case "2":
                this.running = false;
                System.out.println("Thanks for playing :) ");
                System.exit(0);

            default:
                System.out.println("Invalid");
                quitMenu();
        }
    }

    /*
     * MODDIFIES: this
     * EFFECTS: quits application
     */
    public void quit() {
        quitMenu(); 

    }


    /*
     * MODDIFIES: this
     * EFFECTS:c clears the bowl
     */
    public void clearBowl() {
        this.bowl.clear();
        System.out.println("bowl cleared");
        returnToBowlMenu();

    }

    /*
     * MODDIFIES: this
     * EFFECTS: returns user to the bowlDisplay menu
     */
    public void afterTabCall() {
        afterTabMenu();
        String input = this.scanner.nextLine();
        inputAfterTab(input);
    }

    // EFFECTS: saves the workroom to file
    // references WorkRoomApp from JsonSerializationDemo
    private void saveTab() {
        try {
            jsonWritable.open();
            jsonWritable.write(tab);
            jsonWritable.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to Save");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tab from file
    // references WorkRoomApp from JsonSerializationDemo
    private void loadTab() {
        try {
            tab = jsonReadable.read();
            System.out.println("Loading...");
            divider();

        } catch (IOException e) {
            System.out.println("Unable to Load File");
        }
    }
    // MODIFIES: this
    // EFFECTS: continues the game using the loaded data

    private void loadedFile() {
        if (tab.getOrders().size() != 0) {
            order = tab.getOrders().get(0);
            System.out.println("" + tab.getOrders().get(0).getCustomersName() + " : " 
                    + tab.getOrders().get(0).getDishesName());
            bowlDisplay();
            String inputLoaded = this.scanner.nextLine();
            bowlDisplayInput(inputLoaded);
        } else {
            System.out.println("Oops, seems like you dont have a save file!");
        }
    }
   
    
    /* 
     * EFFECTS: prints divider
     */
    public void divider() {
        System.out.println("________________________________");
    }

    /* 
     * EFFECTS: prints event
     */
    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }


}
