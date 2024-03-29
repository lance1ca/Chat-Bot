//FINAL INDIVIDUAL PROJECT
//COSC 310 Chat Bot Class **FINISHED**
//By: LANCE ROGAN, 62708938 
//NOTE: GROUP PORTIONS DONE BY GROUP 10 (LANCE ROGAN, STUDENT #62708938 BLAKE ABLITT, STUDENT #37099595 BEN VAN BERGEYK, STUDENT #95307054 
//GRIFFIN WILCHUK, STUDENT #75303370 CARLA MATHER, STUDENT #22779193)
package group10; //importing group10 package for maven project

//importing all required packages for the program
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//----------------------------------------------------------------------------------------------------------------------------
// our class ChatBot which extends JFrame and implements the action listener
public class ChatBot extends JFrame implements ActionListener {

  // ----------------------------------------------------------------------------------------------------------------------------

  // creating a static ryan reynolds object so its accessible by all methods
  public static RyanReynolds r = new RyanReynolds("6ft 2", 190, "hazel", "light brown", "male", "Vancouver",
      "October 23 1976",
      "Blake Lively", "@vancityreynolds", 18900000, 41600000, 18700000, "$150 M", "Scarlett Johansson");

  // boolean to keep track if the bot asked a question
  static boolean askAQuestion = false;
  // boolean for start up statements
  static boolean startUp = true;
  // boolean to keep track if the text was translated
  public static boolean textWasTranslated;
  // strings to track user inputs
  static String userInput;
  static String userInputUnformatted;
  // string to track which movie title was asked about
  static String movieTitleAsked;
  // string to track which personal question was asked about
  static String personalQuestionAsked;
  // string to track which business name was asked about
  static String businesNameAsked;

  // arraylists for the list of movies, movie questions, personal questions, and
  // greeting responses
  static ArrayList<Movie> listOfMovies = new ArrayList<>();
  static ArrayList<String> movieQuestion = new ArrayList<>();
  static ArrayList<String> personalQuestion = new ArrayList<>();
  static ArrayList<String> greetingResponses = new ArrayList<>();

  // creating the map for the personal attributes
  static HashMap<String, String> personalQuestionMap = new HashMap<String, String>();

  // creating the arrays for the business attributes
  static ArrayList<Business> listOfBusiness = new ArrayList<>(); /* adding list of businesses */
  static ArrayList<String> businessQuestion = new ArrayList<>(); /* adding business questions */

  // creating the maps for the differentbusiness attributes
  static HashMap<String, String> businessNameMap = new HashMap<String, String>(); /* adding business hashmap */
  static HashMap<String, String> yearStartedMap = new HashMap<String, String>(); /* adding business hashmap */
  static HashMap<String, String> businessLocationMap = new HashMap<String, String>(); /* adding business hashmap */
  static HashMap<String, String> businessPositionMap = new HashMap<String, String>(); /* adding business hashmap */

  // Here we are creating the maps for the different movie attributes
  // Initializing the imdb map
  static HashMap<String, String> imdbMap = new HashMap<String, String>();
  // Initializing the year map
  static HashMap<String, String> yearMap = new HashMap<String, String>();
  // Initializing the rating map
  static HashMap<String, String> ratingMap = new HashMap<String, String>();
  // Initializing the cast map
  static HashMap<String, String> castMap = new HashMap<String, String>();
  // Initializing the director map
  static HashMap<String, String> directorMap = new HashMap<String, String>();
  // Initializing the genre map
  static HashMap<String, String> genreMap = new HashMap<String, String>();
  // Initializing the awards map
  static HashMap<String, String> awardsMap = new HashMap<String, String>();
  // Initializing the box office map
  static HashMap<String, String> boxOfficeMap = new HashMap<String, String>();
  // Initializing the location map
  static HashMap<String, String> locationMap = new HashMap<String, String>();
  // Initializing the timeToFilm map
  static HashMap<String, String> timeToFilmMap = new HashMap<String, String>();
  // Initializing the duration map
  static HashMap<String, String> durationMap = new HashMap<String, String>();
  // Initializing the budget map
  static HashMap<String, String> budgetMap = new HashMap<String, String>();
  // Initializing the wikipedia movie map
  static HashMap<String, String> wikipediaMovieMap = new HashMap<String, String>();
  // Initializing the wikipedia business map
  static HashMap<String, String> wikipediaBusinessMap = new HashMap<String, String>();

  // creating a public and static JFrame frame
  public static JFrame frame;
  // creating a public and static JPanel panel
  public static JPanel panel;
  // creating a public and static JTextArea called chatArea
  public static JTextArea chatArea;
  // creating a public and static JTextField called chatField
  public static JTextField chatField;
  // creating a public and static JScrollPane called scrollPane
  public static JScrollPane scrollPane;
  // creating a public and static JButton called button
  public static JButton button;
  // creating a public and static ImageIcon called image
  public static ImageIcon image;
  // creating a public and static JPanel called panel2
  public static JPanel panel2;

  // creating a public and static boolean spelt correctly to track user spelling
  static boolean speltCorrectly = false;
  // creating a public and static boolean oneWordWrong to indicate if a user spelt
  // a single word wrong which then changes speltCorrectly which tracks the whole
  // sentence
  static boolean oneWordWrong;
  // creating a public and static font to be used in the GUI
  public static Font font;
  // creating a public and static Tokenizer object to access the classes methods
  public static Tokenizer t = new Tokenizer();

  // creating variables for first pre-GUI
  JTextField userName;
  JFrame preFrame;
  JTextField userTextField;
  JButton userEnterButton;
  JTextArea chat;
  public static String name;

  // ----------------------------------------------------------------------------------------------------------------------------
  // creating a display for user to enter in their user name
  public void preDisplay() {
    // creating a new frame
    JFrame newFrame = new JFrame();
    // setting the frame to not be visible
    newFrame.setVisible(false);
    // creating a user name frame before arriving to the main frame
    preFrame = new JFrame("Ryan Reynold's ChatBot");
    image = new ImageIcon("ryan_reynolds.jpg");
    preFrame.setIconImage(image.getImage()); // changes icon of frame

    // creating a label for the user to to enter name
    JLabel userNameLabel = new JLabel("Enter your name: ");

    // creating a new text field to type in user name
    userName = new JTextField(15);
    Font font2 = new Font("Monospaced", Font.PLAIN, 12);
    userName.setFont(font2);
    userName.setBackground(Color.black);
    userName.setForeground(Color.yellow);
    userName.setCaretColor(Color.yellow);
    // creating a button to enter the Ryan Reynolds ChatBot frame
    JButton ryanReynoldsChatBot = new JButton("Enter");

    // calling the actionlistener class when button is pressed
    ryanReynoldsChatBot.addActionListener(new ryanReynoldsChatBotButtonListener());

    // creating a prepanel with grid layout
    // A GridBagLayout places components in a grid of rows and columns,
    // allowing specified components to span multiple rows or columns.
    JPanel prePanel = new JPanel(new GridBagLayout());

    // The way the program specifies the size and position characteristics of its
    // components is by specifying constraints for each component.
    // The preferred approach to set constraints on a component is to use the
    // Container.add variant, passing it a GridBagConstraints object,
    // as demonstrated in the next sections.

    // setting up the left and right constraints for the prepanel
    GridBagConstraints preRight = new GridBagConstraints();
    GridBagConstraints preLeft = new GridBagConstraints();

    // Insets: Specifies the external padding of the component --
    // the minimum amount of space between the component and
    // the edges of its display area. The value is specified as an Insets object.
    // By default, each component has no external padding.
    preRight.insets = new Insets(0, 0, 0, 10);
    preLeft.insets = new Insets(0, 10, 10, 10);

    // anchor:is used when the component is smaller than its display area to
    // determine where
    // (within the area) to place the component. Valid values (defined as
    // GridBagConstraints constants)
    // are CENTER (the default), PAGE_START, PAGE_END, LINE_START, LINE_END,
    // FIRST_LINE_START, FIRST_LINE_END, LAST_LINE_END, and LAST_LINE_START.
    preRight.anchor = GridBagConstraints.LINE_END;
    preLeft.anchor = GridBagConstraints.LINE_START;

    // Fill: is used when the component's display area is larger than the
    // component's requested
    // size to determine whether and how to resize the component. Valid values
    // (defined as GridBagConstraints constants)
    // include NONE (the default), HORIZONTAL (make the component wide enough to
    // fill its display area horizontally, but do not change its height),
    // VERTICAL (make the component tall enough to fill its display area vertically,
    // but do not change its width), and BOTH (make the component fill its display
    // area entirely).
    preRight.fill = GridBagConstraints.HORIZONTAL;// HORIZONTAL (make the component wide enough to fill its display area
                                                  // horizontally

    // Gridwidth and Gridheight: Specify the number of columns (for gridwidth) or
    // rows (for gridheight) in the component's display area.
    // These constraints specify the number of cells the component uses, not the
    // number of pixels it uses.
    // The default value is 1. Use GridBagConstraints.REMAINDER to specify that the
    // component be the last one in its row (for gridwidth) or column (for
    // gridheight).
    // Use GridBagConstraints.RELATIVE to specify that the component be the next to
    // last one in its row (for gridwidth) or column (for gridheight).
    // We recommend specifying the gridwidth and gridheight values for each
    // component rather than just using GridBagConstraints.RELATIVE and
    // GridBagConstraints.REMAINDER; this tends to result in more predictable
    // layouts.
    preRight.gridwidth = GridBagConstraints.REMAINDER; // to specify that the component be the last one in its row (for
                                                       // gridwidth)

    // setting the panels background to a hex code
    prePanel.setBackground(Color.decode("#8b1a1a"));
    // adding the panels to draw
    // panel for user name label on left
    prePanel.add(userNameLabel, preLeft);
    // panel for user name text on right
    prePanel.add(userName, preRight);
    // panel center
    preFrame.add(prePanel, BorderLayout.CENTER);
    // panel for Ryan Renolds chatbot name
    preFrame.add(ryanReynoldsChatBot, BorderLayout.SOUTH);
    // setting the size of the frame
    preFrame.setSize(500, 500);

    // making the frame visible
    preFrame.setVisible(true);
  }

  // ----------------------------------------------------------------------------------------------------------------------------

  // user enters their name and when button is pushed this listener is activated
  public class ryanReynoldsChatBotButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      // user name equals the text entered
      name = userName.getText();
      // if user name is less then 1 character then try again is printed
      if (name.length() < 1) {
        System.out.println("Try entering your name again!");
      } else {
        // sets the preFrame to not visiable
        preFrame.setVisible(false);
        // then creates the main GUI
        ChatBot gui = new ChatBot();
        // then calls the set up method and sets up the main GUI
        gui.setUpMyGUI();
      }
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // BELOW WE ARE CREATING THE MAIN GUI FOR THE CHATBOT
  public ChatBot() {

    // //getting the laptop screen size, and setting the frame to be full screen
    // GraphicsEnvironment graphics =
    // GraphicsEnvironment.getLocalGraphicsEnvironment();
    // GraphicsDevice device = graphics.getDefaultScreenDevice();
    // frame = new JFrame("Fullscreen");
    frame = new JFrame(); // creating a new JFrame
    // device.setFullScreenWindow(frame);

    // setting the frame size to be 500 by 500
    frame.setSize(500, 500);

    // creating the 2 JPanels
    panel = new JPanel();
    panel2 = new JPanel();

    // creating the image icon the by the ryan reynolds image
    // image sourced from
    // https://commons.wikimedia.org/wiki/File:Deadpool_2_Japan_Premiere_Red_Carpet_Ryan_Reynolds_(cropped_2).jpg
    image = new ImageIcon("ryan_reynolds.jpg");

    // creating the specified font
    font = new Font("Monospaced", Font.PLAIN, 12);
    // creating the JTextArea and setting the chatArea size
    chatArea = new JTextArea(36, 130);
    // setting the chatArea font
    chatArea.setFont(font);
    // creating the chatField as a JTextField
    chatField = new JTextField();
    // creating a JButton
    button = new JButton("Ask:");
    // creating and setting up the JScrollPane to correspond with the chatArea etc.
    scrollPane = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

  }

  // ----------------------------------------------------------------------------------------------------------------------------

  // this method sets up the main GUI with specified backgrounds, etc, and adds
  // all the panels to the frame
  public void setUpMyGUI() {

    // adding an action listener to the button
    button.addActionListener(this);

    // setting the ChatAreas background and foreground
    chatArea.setBackground(Color.black);
    chatArea.setForeground(Color.yellow);
    // setting lineWrap in chat area to be true
    chatArea.setLineWrap(true);

    // setting the chatFields size
    chatField = new JTextField(40);
    // setting chatFields font
    chatField.setFont(font);
    // setting the ChatFields caret color, background, and foreground
    chatField.setCaretColor(Color.yellow);
    chatField.setBackground(Color.black);
    chatField.setForeground(Color.yellow);

    // NOTE: navy blue is #00117
    // setting the panels backgrounds to be a hex code color
    panel2.setBackground(Color.decode("#8b1a1a"));
    panel.setBackground(Color.decode("#8b1a1a"));
    // adding to the panel the scroll pane, button, and chat field respectively
    panel.add(scrollPane);
    panel2.add(button);
    panel2.add(chatField);

    // adding the panels to the frame in their layout order
    frame.add(panel, BorderLayout.CENTER);
    frame.add(panel2, BorderLayout.PAGE_END);

    // setting the frames icon image to the image icon
    frame.setIconImage(image.getImage()); // changes icon of frame
    // setting the frames title
    frame.setTitle("Ryan Reynold's ChatBot");
    // setting the frame to be visible
    frame.setVisible(true);
    // setting the frame to be resizable
    frame.setResizable(true);
    // setting the frame to exit on close
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // HERE WE ARE CREATING AN ACTION LISTENER AND MAKING AN ACTION PERFORMED METHOD
  // WHICH INCLUDES ALL OF OUR MAIN CODE
  // EXCEPT FOR THE METHODS USED IN THE CODE WHICH ARE OUTSIDE THIS ACTION
  // PERFORMED METHOD

  // this is the action performed method which tracks the action event of clicking
  // the button
  public void actionPerformed(ActionEvent e) {

    // if the action event is the button click, then we enter into this main section
    // of our code
    if (e.getSource() == button) {

      // if the chatField text is less than 1, aka empty, don't do anything
      if (chatField.getText().length() < 1) {
        // do nothing
        // otherwise, if the text is .clear, then clear all messages and indicate it was
        // cleared
      } else if (chatField.getText().equals(".clear")) {
        chatArea.setText("Cleared all messages\n");
        chatField.setText("");
        // otherwise, we proceed to our section of the chat bot class and it proceeds as
        // normal
      } else {

        if (startUp == true) {
          // initializing the greeting repsonse list
          greetingResponses.add("hi");
          greetingResponses.add("hello");
          greetingResponses.add("hey");

          // here we are setting up the possible movie questions, and the possible movies
          // to be asked about
          // Initializing Movie Questions ArrayList
          movieQuestion.add("imdb");
          movieQuestion.add("year");
          movieQuestion.add("rating");
          movieQuestion.add("cast");
          movieQuestion.add("director");
          movieQuestion.add("genre");
          movieQuestion.add("awards");
          movieQuestion.add("box office");
          movieQuestion.add("location");
          movieQuestion.add("time");
          movieQuestion.add("film");
          movieQuestion.add("length");
          movieQuestion.add("duration");
          movieQuestion.add("budget");
          movieQuestion.add("summary");
          movieQuestion.add("about");
          movieQuestion.add("explain");

          // Initializing the list of movies
          listOfMovies.add(r.getDeadpool2());
          listOfMovies.add(r.getDeadpool());
          listOfMovies.add(r.getFreeGuy());
          listOfMovies.add(r.getRIPD());
          listOfMovies.add(r.getGreenLantern());
          listOfMovies.add(r.getBuried());
          listOfMovies.add(r.getSixUnderground());
          listOfMovies.add(r.getRedNotice());
          listOfMovies.add(r.getSelfLess());
          listOfMovies.add(r.getTheHitmansBodyguard());
          listOfMovies.add(r.getChangeUp());
          listOfMovies.add(r.getTheProposal());

          // Initializing the imdb map
          fillInMovieMap(imdbMap, "imdb");
          // Initializing the year map
          fillInMovieMap(yearMap, "year");
          // Initializing the rating map
          fillInMovieMap(ratingMap, "rating");
          // Initializing the cast map
          fillInMovieMap(castMap, "cast");
          // Initializing the director map
          fillInMovieMap(directorMap, "director");
          // Initializing the genre map
          fillInMovieMap(genreMap, "genre");
          // Initializing the awards map
          fillInMovieMap(awardsMap, "awards");
          // Initializing the box office map
          fillInMovieMap(boxOfficeMap, "box office");
          // Initializing the location map
          fillInMovieMap(locationMap, "location");
          // Initializing the timeToFilm map
          fillInMovieMap(timeToFilmMap, "time");
          // Initializing the duration map
          fillInMovieMap(durationMap, "duration");
          // Initializing the budget map
          fillInMovieMap(budgetMap, "budget");
          // initializing the wikipedia movie map
          fillInMovieMap(wikipediaMovieMap, "summary");

          // ----------------------------------------------------------------------------------------------------------------------------

          // here we add all the key words into the personal question list
          personalQuestion.add("height");
          personalQuestion.add("weight");
          personalQuestion.add("hair colour");
          personalQuestion.add("eye colour");
          personalQuestion.add("gender");
          personalQuestion.add("birthplace");
          personalQuestion.add("birthdate");
          personalQuestion.add("wife");
          personalQuestion.add("kids names");
          personalQuestion.add("social media handle");
          personalQuestion.add("twitter followers");
          personalQuestion.add("instagram followers");
          personalQuestion.add("tiktok followers");
          personalQuestion.add("net worth");
          personalQuestion.add("previous marriage");
          personalQuestion.add("other awards");
          personalQuestion.add("old");
          personalQuestion.add("tweets");
          personalQuestion.add("following");
          personalQuestion.add("followers");
          personalQuestion.add("liked");
          personalQuestion.add("yourself");
          personalQuestion.add("tell");

          // here we are initializing the personal question map
          fillInPersonalMap(personalQuestionMap);

          // here we are setting up the possible business questions, and the possible
          // businesses
          // to be asked about
          // Initializing business Questions ArrayList
          businessQuestion.add("year");
          businessQuestion.add("location");
          businessQuestion.add("position");
          businessQuestion.add("summary");
          businessQuestion.add("about");
          businessQuestion.add("explain");

          // Initializing the list of business
          listOfBusiness.add(r.getMintMoblie());
          listOfBusiness.add(r.getMaximumEffort());
          listOfBusiness.add(r.getAviationAmericanGin());
          listOfBusiness.add(r.getwrexhamAFC());
          listOfBusiness.add(r.getgroupEffortInitiative());
          listOfBusiness.add(r.getMNTN());

          // Initializing the business maps
          // Initializing the yearStarted map
          fillInBusinessMap(yearStartedMap, "year");
          // Initializing the businessLocation map
          fillInBusinessMap(businessLocationMap, "location");
          // Initializing the businessPosition map
          fillInBusinessMap(businessPositionMap, "position");
          // initializing the wikipedia business map
          fillInBusinessMap(wikipediaBusinessMap, "summary");

          // ----------------------------------------------------------------------------------------------------------------------------

          // here determines if the startup of the program is true
          // if so, we display the start text to the user, and set the boolean to be false
          // to it never does this again until it is re run from the start
          // otherwise, we don't do anything and proceed as normal.

          // a cool feature saying the chatbot is booting up for a delay of 2 seconds
          chatArea.setText("Ryan Reynolds Chat bot booting up..." + "\n");
          try {
            TimeUnit.SECONDS.sleep(2); // delaying the program for 2 seconds
          } catch (Exception g) {
            chatArea.setText("Error Occurred"); // catching an error
          }
          // setting the GUI text to be the greeting text below
          chatArea.setText("Ryan Reynolds: " +
              "Hello! Nice to meet you! I am Ryan Reynolds, but in chat bot form...\nAsk me a question about myself or my movies!"
              + "\n");
          startUp = false; // indicating we have started the program and there is no need to display this
                           // anymore or do the mapping initializations
        }

        // initializing the askAQuestion to false to start
        askAQuestion = false;
        // append a new line
        chatArea.append("\n");
        // grab user input from the chatField as unformatted and formatted in all
        // lowercase
        userInputUnformatted = chatField.getText();
        userInput = chatField.getText().toLowerCase();

        // Using the AzureTranslate API
        // here we try to detect the users language being spoken to the bot, and we then
        // send this text to be
        // translated from the language being spoken into english for processing
        // responses etc
        try {
          // here we detect which language the user is speaking to the bot (es,en,fr, etc)
          AzureTranslate.detectLanguage(userInput);

          // Here we use the API to automatically translate this user text from the
          // specific language text to english to
          // read in our system to determine our output.
          // Note: we only translate if the language is not in english, otherwise we
          // continue since its already in english
          // and we indicate if it was translated using the boolean
          if (AzureTranslate.targetLanguage != "en") {
            // if user input not in english, then translate it from target language to
            // english for processing
            userInput = AzureTranslate.translateToEnglish(userInput).toLowerCase();
            // set that the text was translated to true
            textWasTranslated = true;
          } else {
            // if the text was already in english, no translation is necessary for
            // processing in english, so set text was translated to false
            textWasTranslated = false;
          }

          // if an error occurs we display the error code
        } catch (Exception g) {
          chatArea.append("Error in Translation occurred");
          return;
        }

        // PREVIOUS TRANSLATE API USED (USING AZURE MICROSOFT TRANSLATE API INSTEAD
        // ^(SEEN ABOVE)^)
        // try{
        // //here we detect which language the user is speaking to the bot
        // //Then it automatically translates this language text to english to read in
        // our system to determine our output
        // userInput = AzureTranslate.detectLanguage(userInput);

        // // *************NOTE: This API allows 100 requests per hour, so comment this
        // out
        // // for testing at great amounts****************
        // // Specify your translation requirements here:
        // String fromLang = AzureTranslate.targetLanguage;
        // String toLang = "en";

        // // setting the user input to be the translated text from target language to
        // english
        // // and as lowercase
        // userInput2 = Translate.translate(fromLang, toLang, userInput).toLowerCase();
        // if(userInput.equals(userInput2)){
        // translateCheck = true;
        // }else{
        // translateCheck = false;
        // }
        // // System.out.println(userInput);
        // } catch (Exception g) {
        // System.out.println(g.getStackTrace());
        // return;
        // }

        // Here we then use our second toolkit API to tokenize our user input after it
        // has been translated
        // Tokenize means we split our user input into each word, comma, or segment into
        // a string array
        try {
          // calling the class to create a token / tokenize our user input which doesnt
          // return anything but sets the
          // tokens string array to be this tokenized input
          Tokenizer.createAToken(userInput);
        } catch (IOException e1) { // if an error just print the error trace

          e1.printStackTrace();
        }

        // Here we then use our third toolkit API to add POS tags to our tokenized user
        // input to indicate on of the following:
        // NNP,VBZ for noun singular and verbs, CD for cardinal number, NNS for noun
        // plural, and JJ for adjective.
        try {
          // call the POSTagging class and method with the tokens string array created
          // above in the class
          POSTagging.posTag(Tokenizer.tokens);
        } catch (IOException e1) {
          // if an error print the stack trace
          e1.printStackTrace();
        }

        // here we set the chatField to be the empty text to reset it after the user
        // entered their question
        chatField.setText("");
        // here we append their name and input in the specified format to the chatArea
        chatArea.append(name + ": " + userInputUnformatted + "\n");

        // if the user input equals goodbye in the users preferred language, then we
        // append the goodbye message, delay
        // the program for 3 seconds, and then end the program
        // Note we are checking if it equals the translated version of goodbye in the
        // specified language of choice by the user
        if (userInput.contains(AzureTranslate.translateToTarget("goodbye"))
            || userInput.contains(AzureTranslate.translateToTarget("goodbye"))) {
          // appending the goodbye message
          chatArea.append("Ryan Reynolds: "
              + AzureTranslate.translateToTarget("Goodbye! Nice meeting you! I am shutting down now.") + "\n");
          try {
            TimeUnit.SECONDS.sleep(3); // delaying the program for 2 seconds
          } catch (Exception g) {
            chatArea.append("Error Occurred"); // catching an error
          }
          System.exit(0);

          // otherwise, call the chatbot function which calls the analyze function
          // which analyzes the input and then the method figures out how the chat bot
          // responds back to the user
        } else {

          chatBot(userInput); // call the chatbot function with the user input

        }

      }

    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------

  // this is our main method for the class
  public static void main(String[] args) {

    // *****USE THIS TO ENSURE JAVA IMAGE ICON, BIN FILE, AND DICTONARY WORKS, make
    // sure image and txt files are in here!*****
    String dir = System.getProperty("user.dir");
    // // // directory from where the program was launched
    System.out.println("Directory to go to-->" + dir);
    // if these three files are not in here it will not work

    // running the pre GUI to get the users name
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      // run method
      public void run() {

        try {
          UIManager.setLookAndFeel(UIManager
              .getSystemLookAndFeelClassName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        // creating a new gui
        ChatBot preChatGUI = new ChatBot();
        // calling the preDisplay gui first
        preChatGUI.preDisplay();

      }
    });

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // this is a method which fills in each movie map with its respective key and
  // value depending on which map it is
  // via a process of elimination by if and else which determines what the key
  // will be
  public static void fillInMovieMap(HashMap<String, String> map, String value) {

    // this loops through each movie object, and initializes the respective map with
    // this movie key
    for (int i = 0; i < listOfMovies.size(); i++) {

      if (value == "imdb") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(),
            " has " + listOfMovies.get(i).getImdbStars() + " IMDb stars!");

      } else if (value == "year") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(), " was released in " + listOfMovies.get(i).getYear());

      } else if (value == "rating") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(), "is rated " + listOfMovies.get(i).getRating());

      } else if (value == "cast") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(), "'s cast includes: " + listOfMovies.get(i).getCast());

      } else if (value == "director") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(), "'s director was: " + listOfMovies.get(i).getDirector());

      } else if (value == "genre") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(), "'s genre is " + listOfMovies.get(i).getGenre());

      } else if (value == "awards") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(),
            " has won these awards: " + listOfMovies.get(i).getAwards());

      } else if (value == "box office") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(),
            "'s box office profit is " + listOfMovies.get(i).getBoxOffice());

      } else if (value == "location") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(),
            "'s filming location was " + listOfMovies.get(i).getLocation());

      } else if (value == "time") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(),
            "'s time taken to film was " + listOfMovies.get(i).getTimeToFilm());

      } else if (value == "duration") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(), " is " + listOfMovies.get(i).getDuration() + " long");

      } else if (value == "budget") {
        map.put(listOfMovies.get(i).getTitle().toLowerCase(),
            " had a budget of " + listOfMovies.get(i).getBudgetOfMovie());

      } else if (value == "summary") {
        // calling Wikipedia API to get the summary of that movie and enter it into the
        // map
        map.put(listOfMovies.get(i).getTitle().toLowerCase(),
            WikipediaAPI.getSummaryOf(listOfMovies.get(i).getTitle()));

      } else {

      }

    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // this is a method which fills in the personal map with its respective key and
  // value depending on which map it is
  // via a process of elimination by if and else which determines what the key
  // will be
  public static void fillInPersonalMap(HashMap<String, String> map) {
    // loops through the personal questions and adds into the map the correct value
    // for each key
    for (int i = 0; i < personalQuestion.size(); i++) {
      if (personalQuestion.get(i) == "height") {
        map.put(personalQuestion.get(i).toLowerCase(), "I am " + r.getheight() + "!");

      } else if (personalQuestion.get(i) == "weight") {
        map.put(personalQuestion.get(i).toLowerCase(), "I am " + r.getweight() + "lbs!");

      } else if (personalQuestion.get(i) == "hair colour") {
        map.put(personalQuestion.get(i).toLowerCase(), "I have " + r.gethaircolour() + " hair.");

      } else if (personalQuestion.get(i) == "eye colour") {
        map.put(personalQuestion.get(i).toLowerCase(), "I have " + r.geteyecolour() + " eyes.");

      } else if (personalQuestion.get(i) == "gender") {
        map.put(personalQuestion.get(i).toLowerCase(), "I am a " + r.getgender());

      } else if (personalQuestion.get(i) == "birthplace") {
        map.put(personalQuestion.get(i).toLowerCase(), "I was born in " + r.getbirthPlace() + "!");

      } else if (personalQuestion.get(i) == "birthdate") {
        map.put(personalQuestion.get(i).toLowerCase(), "My birthdate is " + r.getbirthDate() + "!");

      } else if (personalQuestion.get(i) == "wife") {
        map.put(personalQuestion.get(i).toLowerCase(), "My wife is " + r.getwifeName() + "!");

      } else if (personalQuestion.get(i) == "kids names") {
        map.put(personalQuestion.get(i).toLowerCase(), "My kids names are: " + r.getkidNames());

      } else if (personalQuestion.get(i) == "social media handle") {
        map.put(personalQuestion.get(i).toLowerCase(), "My social media handle is " + r.getsocialMediaHandle());

      } else if (personalQuestion.get(i) == "twitter followers") {
        map.put(personalQuestion.get(i).toLowerCase(),
            "I have approximately " + r.gettwitterFollowers() + " followers on Twitter");

      } else if (personalQuestion.get(i) == "instagram followers") {
        map.put(personalQuestion.get(i).toLowerCase(),
            "I have approximately " + r.getinstagramFollowers() + " followers on Instagram");

      } else if (personalQuestion.get(i) == "tiktok followers") {
        map.put(personalQuestion.get(i).toLowerCase(),
            "I have approximately " + r.getinstagramFollowers() + " followers on TikTok");

      } else if (personalQuestion.get(i) == "net worth") {
        map.put(personalQuestion.get(i).toLowerCase(), "My net worth is approximately " + r.getnetWorth());

      } else if (personalQuestion.get(i) == "previous marriage") {
        map.put(personalQuestion.get(i).toLowerCase(), "I was previously married to " + r.getpreviousMarriage());

      } else if (personalQuestion.get(i) == "other awards") {
        map.put(personalQuestion.get(i).toLowerCase(), "Some of my other awards include: " + r.getotherAwards());

      } else if (personalQuestion.get(i) == "old") {
        // initializing birthDate, current date and period between the two dates
        LocalDate birthDate = LocalDate.of(1976, 6, 23);
        LocalDate currentDate = LocalDate.now();
        Period difference = Period.between(birthDate, currentDate);
        // taking current age as the years of the period determined above
        int currentAge = difference.getYears();

        // update dates to be even more current

        // converting Ryan Reynolds age to days, hours, minutes, and seconds
        int currentDays = currentAge * 365;
        int currentHours = currentAge * 8760;
        int currentMinutes = currentAge * 525600;
        // initialize as long since this value exceeds integer storage
        long currentSeconds = (currentAge) * 31540000000L;
        // print statement with all of the data above

        map.put(personalQuestion.get(i).toLowerCase(), "I am currently " + currentAge + " years old!"
            + " Haha, or just because I am a robot, more specifically I am " + currentDays + " days, "
            + currentHours + " hours, " + currentMinutes + " minutes, and " + currentSeconds + " seconds " + "old!");

      } else if (personalQuestion.get(i) == "tweets") {
        // this is dealt with in the actual personal chat function in order to provide
        // the user more functionality to provide a range
        // for a number of dates to get tweets from

        // calling Twitter API's to get the twitter data and add it into the respective
        // map with the key value pair
      } else if (personalQuestion.get(i) == "following") {
        map.put(personalQuestion.get(i).toLowerCase(),
            "Some of the people I am following are... " + "\n" + TwitterAPI.getSampleOfCurrentFollowing());

      } else if (personalQuestion.get(i) == "followers") {
        map.put(personalQuestion.get(i).toLowerCase(),
            "Some of my followers are..." + "\n" + TwitterAPI.getSampleOfCurrentFollowers());

      } else if (personalQuestion.get(i) == "liked") {
        map.put(personalQuestion.get(i).toLowerCase(),
            "Some of the tweets I have liked are..." + "\n" + TwitterAPI.getLikedTweets());

      } else if (personalQuestion.get(i) == "yourself") {
        map.put(personalQuestion.get(i).toLowerCase(),
            "Here is a little bit about myself..." + "\n" + WikipediaAPI.getSummaryOf("Ryan Reynolds"));
      }
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // this is a method which fills in the business map with its respective key and
  // value depending on which map it is
  // via a process of elimination by if and else which determines what the key
  // will be
  public static void fillInBusinessMap(HashMap<String, String> map, String value) {

    // this loops through each movie object, and initializes the respective map with
    // this movie value
    for (int i = 0; i < listOfBusiness.size(); i++) {

      if (value == "year") {
        map.put(listOfBusiness.get(i).getbusinessName().toLowerCase(), "The year that Ryan Reynolds started "
            + listOfBusiness.get(i).getbusinessName().toLowerCase() + " is " + listOfBusiness.get(i).getyearStarted());

      } else if (value == "location") {
        map.put(listOfBusiness.get(i).getbusinessName().toLowerCase(),
            " The location is " + listOfBusiness.get(i).getbusinessLocation().toLowerCase());

      } else if (value == "position") {
        map.put(listOfBusiness.get(i).getbusinessName().toLowerCase(),
            " The position is " + listOfBusiness.get(i).getbusinessPosition().toLowerCase() + " of "
                + listOfBusiness.get(i).getbusinessName());
      } else if (value == "summary") {
        // calling the Wikipedia API to get the business summary and add it into the map
        // in the correct location
        map.put(listOfBusiness.get(i).getbusinessName().toLowerCase(),
            WikipediaAPI.getSummaryOf(listOfBusiness.get(i).getbusinessName()));

      } else {

      }

    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // below is the chatbot and analyze function methods to determine chatbots
  // reponse

  // this method is the chatbot method which goes through a series of pre-input
  // checks and then if it passes
  // it then calls the analyze function method to determine the chatbot response
  public static void chatBot(String userInput) {

    // here we check via the checkPOSProb method returns false with the double[]
    // probs array in the POSTagging class created from the user input
    // if it is false, we then send the input for further checking using the
    // wordForWord dictonary check with the tokenized input
    if (checkPOSProb(POSTagging.probs) == false) {

      // FURTHER spell check of phrase using the tokenized input
      wordForWord(Tokenizer.tokens);
    } else {
      // if all words have a probability of the POS tag and being spelt correctly
      // above 0.6 then it returns true and no further checks are needed so we proceed
      speltCorrectly = true;
    }

    // if the user input is spelt correctly, then we analyze it to determine how to
    // respond.
    if (speltCorrectly == true) {
      analyzeInput(userInput);
    } else {
      // otherwise, if the user input is spelt wrong, we indicate there is an error
      // and say try again, and the process repeats
      chatArea.append(AzureTranslate.translateToTarget("Your message is spelt wrong! Try again.") + "\n");
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // this method takes in the users input and directs how the robot is going to
  // respond by creating sub problems to solve
  // with other methods
  public static void analyzeInput(String userInput) {

    // here we check if the input is a greeting or not
    for (int i = 0; i < greetingResponses.size(); i++) {
      // if the user input contains a greeting we simply call the greeting function to
      // respond
      if (userInput.contains(greetingResponses.get(i).toLowerCase())) {
        greetingChatFunction();
        return;
      }
    }

    // otherwise...
    // if the userInput contains a movie title, we segregate the users query to
    // being about movies
    for (int j = 0; j < listOfMovies.size(); j++) {
      if (userInput.contains(listOfMovies.get(j).getTitle().toLowerCase())) {
        movieTitleAsked = listOfMovies.get(j).getTitle().toLowerCase();

        // then we send the user input and the movie title asked about to a movie chat
        // function which determines
        // the robots reponse
        movieChatFunction(userInput, movieTitleAsked);
        return;

      }
    }

    // otherwise...
    // if the userInput does not contain a movie title or greeting but contains a
    // personal question,
    // we segregate the users query to being about the bots personal life
    for (int k = 0; k < personalQuestion.size(); k++) {

      if (userInput.contains(personalQuestion.get(k).toLowerCase())) {
        personalQuestionAsked = personalQuestion.get(k).toLowerCase();
        // then we send the user input and the personal question asked about to a
        // personal chat
        // function which determines
        // the robots reponse
        personalChatFunction(userInput, personalQuestionAsked);
        return;
      }
    }

    // otherwise
    // if the userInput contains a business name , we segregate ithe users query to
    // being about business
    for (int j = 0; j < listOfBusiness.size(); j++) {
      if (userInput.contains(listOfBusiness.get(j).getbusinessName().toLowerCase())) {
        businesNameAsked = listOfBusiness.get(j).getbusinessName().toLowerCase();

        // then we send the user input and the movie title asked about to a business
        // chat
        // function which determines
        // the robots reponse
        businessChatFunction(userInput, businesNameAsked);
        return;

      }
    }

    // otherwise we call the default response if all other checks don't find a match
    defaultResponse();
    return;

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // This is the movie chat function for the chatbot, which loops through the
  // movie questions and determines
  // depending on which question is asked how the bot will respond using the
  // corresponding map and key value pair
  public static void movieChatFunction(String userInput, String movieTitleAsked) {

    // if the user input contains the movie question at index 0, respond with the
    // string specified with the imdbMap value for the key = movieTitleAsked
    // this same process goes on for each possible keyword for questions to be asked

    // NOTE: Here we call the AzureTranslate method which translates the given
    // response from English to the target language
    // In other words, if the user was speaking spanish to the Bot, then the target
    // language is es (spanish), and we convert the user text
    // from spanish to english to do our processing and obtain our correct response,
    // and then we translate the chat bots output to the user
    // from English back into this target language of spanish (or whatever language
    // the user
    // spoke, could be: en,fr,etc)

    // Furthermore, we note that if the textWasTranslated is false, i.e the text
    // written by user is in english
    // then there is no need to translate it and we proceed as normal with
    // processing and returning output.
    // Else, or AKA otherwise we translate the user text from the language to
    // english for processing and then the chat bots ouput from english to the
    // language the user spoke

    if (userInput.contains(movieQuestion.get(0))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + imdbMap.get(movieTitleAsked) + "\n");
        return;

      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(imdbMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(1))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + yearMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(yearMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(2))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + ratingMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(ratingMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(3))) {
      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + castMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(castMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(4))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + directorMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(directorMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(5))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + genreMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(genreMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(6))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + awardsMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(awardsMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(7))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + boxOfficeMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(boxOfficeMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(8))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + locationMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(locationMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(9)) || userInput.contains(movieQuestion.get(10))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + timeToFilmMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(timeToFilmMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(11)) || userInput.contains(movieQuestion.get(12))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + durationMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(durationMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(13))) {
      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + movieTitleAsked + " "
            + budgetMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(budgetMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else if (userInput.contains(movieQuestion.get(14)) || userInput.contains(movieQuestion.get(15))
        || userInput.contains(movieQuestion.get(16))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: Here is a summary of " + movieTitleAsked + "... \n"
            + wikipediaMovieMap.get(movieTitleAsked) + "\n");
        return;
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget(movieTitleAsked) + " "
            + AzureTranslate.translateToTarget(wikipediaMovieMap.get(movieTitleAsked)) + "\n");
        return;
      }

    } else {
      defaultResponse();
      return;
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------

  // This is the personal chat function for the chatbot which prints out the
  // response from the personal map
  // that gets the key of the question asked and prints the value of that key
  // value pair

  // NOTE: Here we call the AzureTranslate method which translates the given
  // response from English to the target language
  // In other words, if the user was speaking spanish to the Bot, then the target
  // language is es (spanish), and we convert the user text
  // from spanish to english to do our processing and obtain our correct response,
  // and then we translate the chat bots output to the user
  // from English back into this target language of spanish (or whatever language
  // the user
  // spoke, could be: en,fr,etc)

  // Furthermore, we note that if the textWasTranslated is false, i.e the text
  // written by user is in english
  // then there is no need to translate it and we proceed as normal with
  // processing and returning output.
  // Else, or AKA otherwise we translate the user text from the language to
  // english for processing and then the chat bots ouput from english to the
  // language the user spoke

  public static void personalChatFunction(String userInput, String personalQuestionAsked) {

    // Here in these if statements we are checking for some special cases of
    // questions
    // if the personal question asked is about tweets in a given range we enter this
    // if statement
    if (personalQuestionAsked == "tweets") {

      // initializing the number of days
      int days = 0;

      // for each token in the tokens array, if any of the tokens are a string number,
      // we parse that integer and save the integer into days
      for (int i = 0; i < Tokenizer.tokens.length; i++) {

        // if the string is a number then parse it and add it into days
        if (Tokenizer.tokens[i].contains("1") || Tokenizer.tokens[i].contains("2") || Tokenizer.tokens[i].contains("3")
            || Tokenizer.tokens[i].contains("4") || Tokenizer.tokens[i].contains("5")
            || Tokenizer.tokens[i].contains("6") || Tokenizer.tokens[i].contains("7")
            || Tokenizer.tokens[i].contains("8") || Tokenizer.tokens[i].contains("9")) {
          days = Integer.parseInt(Tokenizer.tokens[i]);
        }
      }
      // set the string tweets in a given range to be the Twitter API class method
      // that retrieves the tweets in that range over those number of days
      // this method returns a string
      String tweetsInRange = TwitterAPI.getTweetsInRange(days);

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + "My most recent tweets in the past " + days
            + " days are... " + "\n" + tweetsInRange
            + "\n");
        return;
      } else {
        // append the result to the GUI in the correct format
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("My most recent tweets in the past ")
            + days
            + AzureTranslate.translateToTarget(" days are... ") + "\n" + AzureTranslate.translateToTarget(tweetsInRange)
            + "\n");
        return;
      }

      // Otherwise, if the personal question asked is wife, then we want to check if
      // the user is asking for a summary of his wife or just who his wife is
    } else if (personalQuestionAsked == "wife") {

      // if the user input contains about, summary, or explain, then the user is
      // asking for a summary of his wife and we call the WikipediaAPI to retrieve the
      // information
      if (userInput.contains("about") || userInput.contains("summary") || userInput.contains("explain")) {
        // append the result of the Wikipedia API for his wife
        String wifeSummary = WikipediaAPI.getSummaryOf("Blake Lively");

        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Here is a little bit about my wife..."
              + "\n" + wifeSummary + "\n");

          // return
          return;
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Here is a little bit about my wife...")
              + "\n" + AzureTranslate.translateToTarget(wifeSummary) + "\n");

          // return
          return;
        }

        // otherwise, we just return and append the normal map response for his wife
        // name
      } else {
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: "
              + personalQuestionMap.get(personalQuestionAsked) + "\n");
        } else {
          chatArea.append("Ryan Reynolds: "
              + AzureTranslate.translateToTarget(personalQuestionMap.get(personalQuestionAsked)) + "\n");
        }

      }

      // otherwise, if the personal question asked contains yourself or you AND tell
      // then we know
      // its asking about / to learn more and get a summary on the Ryan Reynolds chat
      // bot so return
      // the response for the yourself key value pair
      // This accounts for some variability in translation of languages when asking
      // about somebody
    } else if ((userInput.contains("yourself") || userInput.contains("you")) && userInput.contains("tell")) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + personalQuestionMap.get("yourself") + "\n");
      } else {
        chatArea
            .append("Ryan Reynolds: " + AzureTranslate.translateToTarget(personalQuestionMap.get("yourself")) + "\n");
      }

    } else {
      // otherwise, we do our default mapping response if none of these special cases
      // are triggered
      // prints the value in the map corresponding the the key = personalQuestionAsked

      if (textWasTranslated == false) {
        chatArea.append(
            "Ryan Reynolds: " + personalQuestionMap.get(personalQuestionAsked) + "\n");
      } else {
        chatArea.append(
            "Ryan Reynolds: " + AzureTranslate.translateToTarget(personalQuestionMap.get(personalQuestionAsked))
                + "\n");
      }

    }
    askAQuestionResponse(); // asks a question back to the user 1/6 of the time
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // below is the greeting method
  // this is the greeting method, this method generates a random number from 0 to
  // 10 and determines how the chatbot would
  // respond to the user saying hello

  // NOTE: Here we call the AzureTranslate method which translates the given
  // response from English to the target language
  // In other words, if the user was speaking spanish to the Bot, then the target
  // language is es (spanish), and we convert the user text
  // from spanish to english to do our processing and obtain our correct response,
  // and then we translate the chat bots output to the user
  // from English back into this target language of spanish (or whatever language
  // the user
  // spoke, could be: en,fr,etc)

  // Furthermore, we note that if the textWasTranslated is false, i.e the text
  // written by user is in english
  // then there is no need to translate it and we proceed as normal with
  // processing and returning output.
  // Else, or AKA otherwise we translate the user text from the language to
  // english for processing and then the chat bots ouput from english to the
  // language the user spoke

  public static void greetingChatFunction() {

    // generate random number from 0 to 10
    int randomNumber = (int) (Math.random() * 11);

    // switch statement to determine responses to a greeting
    switch (randomNumber) {
      case 0:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hello!" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hello!") + "\n");
        }

        break;
      case 1:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hey Hey!" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hey Hey!") + "\n");
        }

        break;
      case 2:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hi there" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hi there") + "\n");
        }

        break;
      case 3:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hi how are ya!" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hi how are ya!") + "\n");
        }

        break;
      case 4:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hello there" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hello there") + "\n");
        }

        break;
      case 5:
        if (textWasTranslated == false) {
          chatArea.append(
              "Ryan Reynolds: " + "Hey! I am Ryan Reynolds, nice to meet you!" + "\n");
        } else {
          chatArea.append(
              "Ryan Reynolds: " + AzureTranslate.translateToTarget("Hey! I am Ryan Reynolds, nice to meet you!")
                  + "\n");
        }

        break;
      case 6:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: "
              +
              "What a beautiful day to meet someone as great as me hey? Haha, Hi nice to meet you!"
              + "\n");
        } else {
          chatArea.append("Ryan Reynolds: "
              + AzureTranslate.translateToTarget(
                  "What a beautiful day to meet someone as great as me hey? Haha, Hi nice to meet you!")
              + "\n");
        }

        break;
      case 7:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Yo!" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Yo!") + "\n");
        }

        break;
      case 8:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hey there" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hey there") + "\n");
        }

        break;
      case 9:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hi, nice to meet you!" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hi, nice to meet you!") + "\n");
        }

        break;
      case 10:
        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: " + "Hey!" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Hey!") + "\n");
        }

        break;
      default:
        return;

    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // This is the business chat function for the chatbot, which loops through the
  // business questions and determines
  // depending on which question is asked how the bot will respond using the
  // corresponding map and key value pair
  public static void businessChatFunction(String userInput, String businessNameAsked) {

    // if the user input contains the business question at index 0, we respond with
    // the year started value for key = businessNameAsked

    // NOTE: Here we call the AzureTranslate method which translates the given
    // response from English to the target language
    // In other words, if the user was speaking spanish to the Bot, then the target
    // language is es (spanish), and we convert the user text
    // from spanish to english to do our processing and obtain our correct response,
    // and then we translate the chat bots output to the user
    // from English back into this target language of spanish (or whatever language
    // the user
    // spoke, could be: en,fr,etc)

    // Furthermore, we note that if the textWasTranslated is false, i.e the text
    // written by user is in english
    // then there is no need to translate it and we proceed as normal with
    // processing and returning output.
    // Else, or AKA otherwise we translate the user text from the language to
    // english for processing and then the chat bots ouput from english to the
    // language the user spoke

    if (userInput.contains(businessQuestion.get(0))) {

      if (textWasTranslated == false) {
        chatArea
            .append("Ryan Reynolds: " + yearStartedMap.get(businessNameAsked) + "\n");
      } else {
        chatArea
            .append("Ryan Reynolds: " + AzureTranslate.translateToTarget(yearStartedMap.get(businessNameAsked)) + "\n");
      }

      return;

    } else if (userInput.contains(businessQuestion.get(1))) {

      if (textWasTranslated == false) {
        chatArea.append(
            "Ryan Reynolds: " + businessLocationMap.get(businessNameAsked) + "\n");
      } else {
        chatArea.append(
            "Ryan Reynolds: " + AzureTranslate.translateToTarget(businessLocationMap.get(businessNameAsked)) + "\n");
      }

      return;

    } else if (userInput.contains(businessQuestion.get(2))) {

      if (textWasTranslated == false) {
        chatArea.append(
            "Ryan Reynolds: " + businessPositionMap.get(businessNameAsked) + "\n");
      } else {
        chatArea.append(
            "Ryan Reynolds: " + AzureTranslate.translateToTarget(businessPositionMap.get(businessNameAsked)) + "\n");
      }

      return;
    } else if (userInput.contains(businessQuestion.get(3)) || userInput.contains(businessQuestion.get(4))
        || userInput.contains(businessQuestion.get(5))) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + "Here is a summary of "
            + businessNameAsked + ":\n"
            + wikipediaBusinessMap.get(businessNameAsked));
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Here is a summary of ")
            + AzureTranslate.translateToTarget(businessNameAsked) + ":\n"
            + AzureTranslate.translateToTarget(wikipediaBusinessMap.get(businessNameAsked)));
      }

    } else {
      defaultResponse();
      return;
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------

  // below is the ask a question method which determines if the bot asks the
  // question back to the user or not

  // NOTE: Here we call the AzureTranslate method which translates the given
  // response from English to the target language
  // In other words, if the user was speaking spanish to the Bot, then the target
  // language is es (spanish), and we convert the user text
  // from spanish to english to do our processing and obtain our correct response,
  // and then we translate the chat bots output to the user
  // from English back into this target language of spanish (or whatever language
  // the user
  // spoke, could be: en,fr,etc)

  // Furthermore, we note that if the textWasTranslated is false, i.e the text
  // written by user is in english
  // then there is no need to translate it and we proceed as normal with
  // processing and returning output.
  // Else, or AKA otherwise we translate the user text from the language to
  // english for processing and then the chat bots ouput from english to the
  // language the user spoke

  public static void askAQuestionResponse() {
    int random = (int) (Math.random() * 6); // 1/6 of the time the chat bot asks a question back to the user

    // if the number is 1, then we append asking a question back, set the field text
    // to blank, and get the users input
    // and then respond with "Really!"
    if (random == 1) {

      if (textWasTranslated == false) {
        chatArea.append("Ryan Reynolds: " + "How about you?" + "\n");
        userInputUnformatted = chatField.getText();
        chatField.setText("");
        chatArea.append(name + ": " + userInputUnformatted + "\n");
        chatArea.append("Ryan Reynolds: " + "Really!" + "\n");
      } else {
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("How about you?") + "\n");
        userInputUnformatted = chatField.getText();
        chatField.setText("");
        chatArea.append(name + ": " + AzureTranslate.translateToTarget(userInputUnformatted) + "\n");
        chatArea.append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Really!") + "\n");
      }

    } else {
      // otherwise return
      return;
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  // this is a method that is called as a default response if the chat bot is
  // unable to determine how to respond

  // NOTE: Here we call the AzureTranslate method which translates the given
  // response from English to the target language
  // In other words, if the user was speaking spanish to the Bot, then the target
  // language is es (spanish), and we convert the user text
  // from spanish to english to do our processing and obtain our correct response,
  // and then we translate the chat bots output to the user
  // from English back into this target language of spanish (or whatever language
  // the user
  // spoke, could be: en,fr,etc)

  // Furthermore, we note that if the textWasTranslated is false, i.e the text
  // written by user is in english
  // then there is no need to translate it and we proceed as normal with
  // processing and returning output.
  // Else, or AKA otherwise we translate the user text from the language to
  // english for processing and then the chat bots ouput from english to the
  // language the user spoke

  public static void defaultResponse() {
    // If all else fails and the chat bot does not not how to respond, we have these
    // 6 statements set as
    // the chat bot's default responses to any questions it does not know

    // random value to select a response
    int selector = (int) (Math.random() * 6);

    switch (selector) {
      // case statements: each is a unique response when the question is not
      // understood
      case 0:

        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: "
              + "I'm sorry I don't understand the question. Please ask me again!"
              + "\n");
        } else {
          chatArea.append("Ryan Reynolds: "
              + AzureTranslate.translateToTarget("I'm sorry I don't understand the question. Please ask me again!")
              + "\n");
        }

        break;
      case 1:

        if (textWasTranslated == false) {
          chatArea
              .append("Ryan Reynolds: " + "Pardon? I didn't quite get that." + "\n");
        } else {
          chatArea
              .append("Ryan Reynolds: " + AzureTranslate.translateToTarget("Pardon? I didn't quite get that.") + "\n");
        }

        break;
      case 2:

        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: "
              +
              "I'm sorry I don't understand the question. Maybe it's because of your accent hahaha!"
              + "\n");
        } else {
          chatArea.append("Ryan Reynolds: "
              + AzureTranslate.translateToTarget(
                  "I'm sorry I don't understand the question. Maybe it's because of your accent hahaha!")
              + "\n");
        }

        break;
      case 3:

        if (textWasTranslated == false) {
          chatArea.append(
              "Ryan Reynolds: " + "Sorry, you will have to ask that again." + "\n");
        } else {
          chatArea.append(
              "Ryan Reynolds: " + AzureTranslate.translateToTarget("Sorry, you will have to ask that again.") + "\n");
        }

        break;
      case 4:

        if (textWasTranslated == false) {
          chatArea.append("Ryan Reynolds: "
              + "That was a confusing question! Can you ask me again?" + "\n");
        } else {
          chatArea.append("Ryan Reynolds: "
              + AzureTranslate.translateToTarget("That was a confusing question! Can you ask me again?") + "\n");
        }

        break;
      default:
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------

  // this method takes in a tokenized userInput string array and sends it further
  // into a spell check which checks each word
  // to see if it is in a dictonary of words
  public static void wordForWord(String[] userInput) {

    oneWordWrong = false; // initialize oneWordWrong to false
    // for each item in the userInput, if no words are wrong, continue to check each
    // word if its spelt right
    for (int i = 0; i < userInput.length; i++) {
      if (oneWordWrong == false) {
        // call the spelling method to check the string at index i against the dictonary
        // file
        // Note: base dictonary without personal additions from
        // http://www.gwicks.net/dictionaries.htm?fbclid=IwAR3mDOwygLdUXX98hdnaLi
        isMySpellingRight(userInput[i], "dictonary.txt");
      } else {
        // otherwise, oneWordWrong is true, and thus there is a spelling mistake so we
        // break
        break;
      }
    }

  }

  // this method takes in a String userinput and a filepath and checks the
  // singular string against a dictonary file
  public static void isMySpellingRight(String userInput, String filePath) {
    // try to do the following
    try {
      // open a fileReader with the filepath to the dictonary file
      FileReader fr = new FileReader(filePath);
      // created a buffered reader so it reads it faster
      BufferedReader in = new BufferedReader(fr);

      String word; // initialize the string word
      // while the word = nextLine is not null, aka there is another word to check, do
      // the following
      while ((word = in.readLine()) != null) {

        // if the user input is equal to one of the words in the dictonary, set spelt
        // correctly to true, and oneWordWrong to false
        // then break;
        if (userInput.equals(word)) {
          speltCorrectly = true;
          oneWordWrong = false;
          break;
          // otherwise, if it doesn't match with any words, set spelt correctly to false,
          // oneWordWrong to true and break
        } else {
          speltCorrectly = false;
          oneWordWrong = true;
        }
      }

      in.close(); // close the input reader stream
    } catch (Exception e) {
      // catch the exception and just return
      return;
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------

  // this method is checking the double[] probability array created from the
  // tokenized input and POS tags that each probability for
  // each word is greater then 0.6, aka most likely spelt correctly and the POS
  // tag is correct.
  public static boolean checkPOSProb(double[] prob) {
    // for each probability in the array, check the condition
    for (int i = 0; i < prob.length; i++) {
      // if there is a probability less than 0.6, return false so it then further
      // checks the input in a dictonary
      if (prob[i] < 0.60) {
        return false;
      }
    }
    // otherwise, if all words probabilities are higher than 0.6, return true and
    // there is no need for further checking
    return true;
  }

}
