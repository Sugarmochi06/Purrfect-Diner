package ui;


import static org.junit.jupiter.api.Assertions.fail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Bowl;
import model.Event;
import model.EventLog;
import model.GenerateCustomer;
import model.Order;
import model.Recipe;
import model.Tab;
import persistence.JsonReadable;
import persistence.JsonWritable;

import javax.swing.*;



public class LoadingScreen extends JPanel implements LogPrinter {
    private JFrame mainMenu;
    private JPanel panel;
    private JPanel newGamePanel;
    private JPanel tabboxpanel;
    private JPanel bowlPanel;
    private JLabel tabButton;
    private JLabel tabbox;
    private JLabel addOrderStatement;
    private JLabel yesText;
    private JLabel noText;
    private JLabel orderInfo;
    private JLabel newOrder;
    private JLabel box;
    private JLabel orderInfoRecipe;
    private JLabel recipeIcon;
    private JLabel recipeText;
    private JLabel bowlbeef;
    private JLabel bowllettuce;
    private JLabel bowlcheese;
    private JLabel bowlsalt;
    private JLabel bowlice;
    private JLabel bowlpotato;

    private JButton newGame;
    private JButton continueGame;
    private JButton quitGame;
    private JButton tabHidden;
    private JButton yesButton;
    private JButton noButton;
    private JButton beef;
    private JButton lettuce;
    private JButton cheese;
    private JButton salt;
    private JButton icecream;
    private JButton potato;
    private JButton checkRecipe;
    private JButton serveOrder;
    private ImageIcon background;
    private int buttonWidth = 200;
    private int buttonHeight = 50;
    private int center = 640 - (buttonWidth / 2);
    private List<String> orderRecipe;

    private static final String JSON_STORE = "./data/Tab.json";

    private GenerateCustomer gc;
    private Order order;
    private Tab tab;
    private Bowl bowl;
    private Recipe recipe;
    private JsonWritable jsonWritable;
    private JsonReadable jsonReadable;

    private boolean orderPrinted = false;
    private Boolean isBowlVisible = false;
        
    /*
        * EFFECTS: initiallizes fields and creates window for gui
        */
    public LoadingScreen() {
        this.gc = new GenerateCustomer();
        this.tab = new Tab();
        this.bowl = new Bowl();
        this.recipe = new Recipe();
        orderRecipe = new ArrayList<>();
        jsonWritable = new JsonWritable(JSON_STORE);
        jsonReadable = new JsonReadable(JSON_STORE);
        loadingScreenWindow();
        
    }
    
    /*
        * EFFECTS: creates the window for the loading screen 
        */

    public void loadingScreenWindow() {
        mainMenu = new JFrame("Purrfect Diner!");
        mainMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainMenu.setSize(1280, 780);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setResizable(false);
        panel = new JPanel();
        panel.setLayout(null);
        loadingScreenButtons();
        drawBackground();
        mainMenu.add(panel, BorderLayout.CENTER);
        mainMenu.setVisible(true);
        
    }
    /*
        * EFFECTS: draws background onto jpanel
        */

    public void drawBackground() {
        background = new ImageIcon("Images/titlepage.png");
        JLabel bg = new JLabel(background);
        bg.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        panel.add(bg);

        

    }

    /*
        * EFFECTS: creates the buttons seen on the loading screen
        */

    public void loadingScreenButtons() {
        // redo commit push again
        newGame = new JButton(new NewGameAction());
        newGame.setBounds(center,400,buttonWidth,buttonHeight);
        newGame.setContentAreaFilled(false);
        newGame.addActionListener(new NewGameAction());
        panel.add(newGame);

        continueGame = new JButton("Continue Game");
        continueGame.setBounds(center,500,buttonWidth,buttonHeight);
        panel.add(continueGame);
        continueGameButton();

        quitGame = new JButton("Quit");
        quitGame.setBounds(center,600,buttonWidth,buttonHeight);
        panel.add(quitGame);
        quitListener();

    }

    private class NewGameAction extends AbstractAction {
        NewGameAction() {
            super("New Game");
        }

        /*
        * EFFECTS: Starts a new game
        */
        public void actionPerformed(ActionEvent e) {
        
            if (e.getSource() == newGame) {
                startGame();
            }  
        }

    }

    /*
     * MODIFIES: this, tab
     * EFFECTS: loads previous saved game 
     */

    public void continueGameButton() {
        continueGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTab();
                loadedFile();

            }  
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a new panel containing tab info
     */
    
    public void newTabPanel() {
        tabboxpanel = new JPanel();
        tabboxpanel.setLayout(null);
        tabboxpanel.setOpaque(false);
        tabboxpanel.setBounds(0,0,1280,780);
        everythingTab();
        tabboxpanel.setVisible(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: starts game
     */

    public void startGame() {
        this.order = new Order(gc.randomCustomer());
        newGameScreen();
        newGamePanel.revalidate();
        newGamePanel.repaint();

    }
    /*
        * MODIFIES: this
        * EFFECTS: Creates new game screen
        */

    public void newGameScreen() {
        newGamePanel = new JPanel();
        newGamePanel.setLayout(null);
        panel.removeAll();
        JLabel bg = new JLabel(new ImageIcon("Images/background.png"));
        bg.setBounds(0, 0, 1280, 780);
        initializeBowlPanel();
        newGamePanel.add(bowlPanel);
        initializeTab();
        newTabPanel();
        newGamePanel.add(tabboxpanel);
        printOrder();
        addBowl();
        newGamePanel.add(bg);
        newGamePanel.revalidate();
        newGamePanel.repaint();
        mainMenu.add(newGamePanel, BorderLayout.CENTER);
        mainMenu.setVisible(true);

    }

    /*
        * MODIFIES: this 
        * EFFECTS: prints the order at the start of each "round"
        */

    public void printOrder() {
        orderInfo = new JLabel();
        orderInfo.setText("" + order.getCustomersName() + " : " + order.getDishesName());
        orderInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        orderInfo.setBounds(120,-100, 1280, 780);
        newGamePanel.add(orderInfo);

        newOrder = new JLabel();
        newOrder.setText("You have a new order!");
        newOrder.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        newOrder.setBounds(120,-200, 1280, 780);
        box = new JLabel(new ImageIcon("Images/textbox.png"));
        box.setBounds(0,0,1280,780);
        newGamePanel.add(newOrder);
        newGamePanel.add(box);
        orderPrinted = true;
        newGamePanel.revalidate();
        newGamePanel.repaint();
    
    }


    /*
     * MODIFIES: this
     * EFFECTS: initializes tab related items and adds to panel
     */
    public void everythingTab() {
        tabbox = new JLabel(new ImageIcon("Images/addtabbox.png"));
        tabbox.setBounds(-100,0,750,100);
        initializeTabChoices();
        tabboxpanel.add(yesText);
        tabboxpanel.add(noText);
        tabboxpanel.add(yesButton);
        tabboxpanel.add(noButton);
        tabboxpanel.add(addOrderStatement);
        tabboxpanel.add(tabbox);
    }

    /*
        * EFFECTS: initializes objects needed for addTab
        */
    public void initializeTab() {
        tabButton = new JLabel(new ImageIcon("Images/tab.png"));
        tabButton.setBounds(50,0,1280,780);
        initializeChoiceText();
        if (tabHidden == null) {
            tabHidden = new JButton("tab");
            tabHidden.setBounds(1050, 160, 220, 300);
            tabHidden.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Boolean isVisible = tabboxpanel.isVisible();
                    tabboxpanel.setVisible(!isVisible);
                    newYesAction();
                    newNoAction();
    
                }  
            });
        }
        newGamePanel.add(tabButton);
        newGamePanel.add(tabHidden);
    }

    /*
        * EFFECTS: initializes tab choices 
        */
    public void initializeTabChoices() {
        tabbox = new JLabel(new ImageIcon("Images/addtabbox.png"));
        tabbox.setBounds(0,0,1280,780);

        addOrderStatement = new JLabel();
        addOrderStatement.setText("Add order to tab?");
        addOrderStatement.setBounds(520,225,500,80);
        addOrderStatement.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));

        yesButton = new JButton("Yes");
        yesButton.setBounds(420,390,200,50);
        yesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        noButton = new JButton("No");
        noButton.setBounds(710,390,200,50);
        noButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));


    }

    /*
        * EFFECTS: initializes tab choices 
        */
    public void initializeChoiceText() {
        yesText = new JLabel("Your order was added!");
        yesText.setBounds(470,390,400,50);
        yesText.setFont(new Font("Comic Sans MS", Font.PLAIN,35));
        yesText.setVisible(false);

        noText = new JLabel("Your order was added anyways!");
        noText.setBounds(420,390,570,50);
        noText.setFont(new Font("Comic Sans MS", Font.PLAIN,35));
        noText.setVisible(false);
    }

    /*
    * EFFECTS: prints out text if user chooses yes
    */

    public void newYesAction() {
    
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbox.setVisible(true);
                yesButton.setVisible(false);
                yesText.setVisible(true);
                noText.setVisible(false);
                noButton.setVisible(false);
                yesButton.setEnabled(true);
                orderInfo.setVisible(false);
                newOrder.setVisible(false);
                box.setVisible(false);
                tab.addOrderToTab(order);
                orderRecipe.addAll(recipe.getRecipe(tab.getAllOrders().get(0)));
                newGamePanel.repaint();
                
            }
        });
    }


    /*
    * EFFECTS: prints out text if user chooses yes
    */
    public void newNoAction() {

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbox.setVisible(true);
                yesButton.setVisible(false);
                yesText.setVisible(false);
                noText.setVisible(true);
                noButton.setVisible(false);
                orderInfo.setVisible(false);
                newOrder.setVisible(false);
                box.setVisible(false);
                tab.addOrderToTab(order);  
                orderRecipe.addAll(recipe.getRecipe(tab.getAllOrders().get(0)));
                newGamePanel.repaint();
            }
        });
    }

        /*
        * MODIFIES: this
        * EFFECTS: creates the add to bowl button
        */
    public void addBowl() {
        JButton bowlButton = new JButton();
        bowlButton.setBounds(590,550,340,170);
        JLabel bowlIcon = new JLabel(new ImageIcon("Images/bowl.png"));
        bowlIcon.setBounds(0,0,1280,780);
        newGamePanel.add(bowlIcon);
        newGamePanel.add(bowlButton);
        newGamePanel.revalidate();
        newGamePanel.repaint();


        bowlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tab.getAllOrders().isEmpty()) {
                    isBowlVisible = true;
                    bowlButton.setEnabled(false);
                    bowlButton.setVisible(false);
                    bowlPanel.setVisible(true);
                    newGamePanel.repaint();
                }



            } 
        });
        
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes bowl panel
     */
    public void initializeBowlPanel() {
        bowlPanel = new JPanel();
        bowlPanel.setLayout(null);
        bowlPanel.setBounds(0,0,1280,780);
        JLabel bowlbg = new JLabel(new ImageIcon("Images/addbowl.png"));
        bowlbg.setBounds(0,0,1280,780);
        checkRecipe();
        initializeBowlIcon();
        ingredientButtons();
        clearBowl();
        menuButton();
        quitButton();
        bowlPanel.add(bowlbg);
        bowlPanel.setVisible(false);
    }


    /*
        * MODIFIES: this
        * EFFECTS: adds ingredient buttons to screen
        */
    public void ingredientButtons() {
        ImageIcon beefIcon = new ImageIcon("Images/beef.png");
        beef = new JButton(beefIcon);
        beef.setBounds(980,205,100,100);
       
        ImageIcon lettuceIcon = new ImageIcon("Images/lettuce.png");
        lettuce = new JButton(lettuceIcon);
        lettuce.setBounds(1115,205,100,100);
       
        ImageIcon saltIcon = new ImageIcon("Images/salt.png");
        salt = new JButton(saltIcon);
        salt.setBounds(980,360,100,100);

        ImageIcon cheeseIcon = new ImageIcon("Images/cheese.png");
        cheese = new JButton(cheeseIcon);
        cheese.setBounds(1115,360,100,100);
      
        ImageIcon icecreamIcon = new ImageIcon("Images/icecream.png");
        icecream = new JButton(icecreamIcon);
        icecream.setBounds(980,515,100,100);

        ImageIcon potatoIcon = new ImageIcon("Images/potato.png");
        potato = new JButton(potatoIcon);
        potato.setBounds(1115,515,100,100);
     
        addIngredientButton();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds buttons to bowlPanel
     */
    public void addIngredientButton() {
        bowlPanel.add(beef);
        bowlPanel.add(lettuce);
        bowlPanel.add(salt);
        bowlPanel.add(cheese);
        bowlPanel.add(icecream);
        bowlPanel.add(potato);
        bowlPanel.repaint();
        actionListenersIngredient();
    }

    /*
     * EFFECTS: adds action lister to button
     */
    public void actionListenersIngredient() {
        beef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bowl.add("beef");
                bowlbeef.setVisible(true);
            } 
        });
        lettuce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bowl.add("lettuce");
                bowllettuce.setVisible(true);
            } 
        });
        salt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bowl.add("salt");
                bowlsalt.setVisible(true);
            } 
        });
        actionListenersIngredientTwo();

    }

    /*
     * EFFECTS: adds action listener to buttons
     */
    public void actionListenersIngredientTwo() {
        cheese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bowl.add("cheese");
                bowlcheese.setVisible(true);
            } 
        });
        icecream.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bowl.add("Ice cream");
                bowlice.setVisible(true);
            } 
        });
        potato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bowl.add("potato");
                bowlpotato.setVisible(true);
            } 
        });
    }

    /*
     * EFFECTS: initializes bowl icons 
     */
    public void initializeBowlIcon() {
        bowlbeef = new JLabel(new ImageIcon("Images/bowlbeef.png"));
        bowllettuce = new JLabel(new ImageIcon("Images/bowllettuce.png"));
        bowlcheese = new JLabel(new ImageIcon("Images/bowlcheese.png"));
        bowlsalt = new JLabel(new ImageIcon("Images/bowlsalt.png"));
        bowlice = new JLabel(new ImageIcon("Images/bowlice.png"));
        bowlpotato = new JLabel(new ImageIcon("Images/bowlpotato.png"));
        bowlbeef.setBounds(0,0,1280,780);
        bowllettuce.setBounds(0,0,1280,780);
        bowlcheese.setBounds(0,0,1280,780);
        bowlsalt.setBounds(0,0,1280,780);
        bowlice.setBounds(0,0,1280,780);
        bowlpotato.setBounds(0,0,1280,780);
        bowlbeef.setVisible(false);
        bowllettuce.setVisible(false);
        bowlcheese.setVisible(false);
        bowlice.setVisible(false);
        bowlsalt.setVisible(false);
        bowlpotato.setVisible(false);
        addBowlImages();
       
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds bowlIcons to bowl panel
     */

    public void addBowlImages() {
        bowlPanel.add(bowlbeef);
        bowlPanel.add(bowllettuce);
        bowlPanel.add(bowlcheese);
        bowlPanel.add(bowlsalt);
        bowlPanel.add(bowlice);
        bowlPanel.add(bowlpotato);
    }

     /*
     * MODIFIES: this
     * EFFECTS: checks to see if ingredients in the bowl match the recipie for the dish
     */
    public void serveOrder() {
        if (bowl.size() != 0 && recipe.getRecipe(tab.getAllOrders().get(0)).containsAll(bowl.getBowl())) {
            tab.getAllOrders().get(0).serveOrder();
            tab.removeOrder(order);
            serveOrder.setVisible(false);
            bowlPanel.setVisible(false);
            bowl.clear();
            startGame();
        }
    }
    /*
     * MODIFIES: this
     * EFFECTS: initializes recipeText
     */

    public void addRecipeText() {
        recipeText = new JLabel();
        recipeText.setText("" + orderRecipe + "");
        recipeText.setFont(new Font("Comic Sans MS", Font.PLAIN,20));
        recipeText.setBounds(50,35,450,670);
        recipeText.setVisible(false);
        bowlPanel.add(recipeText);
        bowlPanel.revalidate();
        bowlPanel.repaint();
    }

    /*
     * MODIFIES: this 
     * EFFECTS: initializes order to add to recipe icon 
     */
    public void addOrderRecipe() {
        orderInfoRecipe = new JLabel();
        orderInfoRecipe.setText("" + order.getCustomersName() + " : " + order.getDishesName());
        orderInfoRecipe.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        orderInfoRecipe.setBounds(50,25, 250, 200);
        orderInfoRecipe.setVisible(false);
        bowlPanel.add(orderInfoRecipe);
        bowlPanel.revalidate();
        bowlPanel.repaint();
    }

     /*
     * MODIFIES: this
     * EFFECTS: allows users to serve their order
     */

    public void serveButton() {
        serveOrder = new JButton("Serve order");
        serveOrder.setBounds(545,10,200,50);
        bowlPanel.add(serveOrder);
        serveOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serveOrder();
            } 
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: allows user to check recipe for order 
     */
    public void checkRecipe() {
        addRecipeText();
        addOrderRecipe();
        serveButton();
        recipeIcon = new JLabel(new ImageIcon("Images/recipe.png"));
        recipeIcon.setBounds(0,0,1280,780);
        recipeIcon.setVisible(false);
        bowlPanel.add(recipeIcon);
        checkRecipe = new JButton("check recipe");
        checkRecipe.setBounds(85,100,200,50);
        bowlPanel.add(checkRecipe);
        checkRecipeListener();

    }

    /*
     * MODIFIES: this
     * EFFECTS: adds action listener to checkRecipe
     */

    public void checkRecipeListener() {
        checkRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderRecipe.clear();
                orderRecipe.addAll(recipe.getRecipe(tab.getAllOrders().get(0)));
                if (!tab.getAllOrders().isEmpty()) {
                    recipeText.setText("" + orderRecipe + "");
                    recipeText.setVisible(true);
                    orderInfoRecipe.setVisible(true);
                    recipeText.repaint();
                    orderInfoRecipe.repaint();
                }
                recipeIcon.setVisible(true);
            } 
        });
    }
    /*
     * MODIFIES: bowl
     * EFFECTS: clears bowl
     */

    public void clearBowl() {
        JButton clearBowl = new JButton("Clear bowl");
        clearBowl.setBounds(545,650,200,50);
        bowlPanel.add(clearBowl);
        clearBowl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bowl.clear();
                bowlbeef.setVisible(false);
                bowllettuce.setVisible(false);
                bowlcheese.setVisible(false);
                bowlice.setVisible(false);
                bowlsalt.setVisible(false);
                bowlpotato.setVisible(false);
               
            } 
        });
    }
   
    /*
     * MODIFIES: this, tab
     * EFFECTS: saves game
     */
    public void menuButton() {
        JButton save = new JButton("Save game");
        save.setBounds(85,550,200,50);
        bowlPanel.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWritable.open();
                    jsonWritable.write(tab);
                    jsonWritable.close();
                    System.out.println("Saved!");
                } catch (FileNotFoundException a) {
                    System.out.println("Unable to Save");
                }
            } 
        });
    }

    // MODIFIES: this
    // EFFECTS: loads tab from file
    // references WorkRoomApp from JsonSerializationDemo
    private void loadTab() {
        try {
            tab = jsonReadable.read();
            System.out.println("Loading...");

        } catch (IOException e) {
            System.out.println("Unable to Load File");
        }
    }

    /*
     * MODIFIES: this, tab
     * EFFECTS: loads previous saved game 
     */
    private void loadedFile() {
        if (tab.getOrders().size() != 0) {
            order = tab.getOrders().get(0);
            newContinueScreen();
        } else {
            System.out.println("Oops, seems like you dont have a save file!");
        }
    }

    /*
     * EFFECTS: quits game
     */
    public void quitButton() {
        JButton quit = new JButton("Quit game");
        quit.setBounds(85,650,200,50);
        bowlPanel.add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            } 
        });
      
    }

    /*
     * EFFECTS: quits game
     */
    public void quitListener() {
        quitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            } 
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: game screen for loaded game
     */
    public void newContinueScreen() {
        newGamePanel = new JPanel();
        newGamePanel.setLayout(null);
        panel.removeAll();
        JLabel bg = new JLabel(new ImageIcon("Images/background.png"));
        bg.setBounds(0, 0, 1280, 780);
        initializeBowlPanel();
        bowlPanel.setVisible(true);
        newGamePanel.add(bowlPanel);
        newGamePanel.revalidate();
        newGamePanel.repaint();
        mainMenu.add(newGamePanel, BorderLayout.CENTER);
        mainMenu.setVisible(true);

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
