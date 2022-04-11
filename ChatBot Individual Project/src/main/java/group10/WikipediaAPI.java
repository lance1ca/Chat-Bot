//FINAL INDIVIDUAL PROJECT
//COSC 310 Wikipedia API Class **FINISHED*
//By: LANCE ROGAN, 62708938 
//Note: API from https://github.com/fastily/jwiki which was retrieved from https://www.mediawiki.org/wiki/API:Client_code 

package group10;

//importing required packages
import org.fastily.jwiki.core.Wiki;

public class WikipediaAPI {

    // Wikipedia Summary Method
    // This method returns a string which includes a summary from Wikipedia about
    // whatever the user input was inquiring about.
    // More specifically, if the user asks for a summary or more information about
    // Ryan Reynolds movies, businesses, or himself,
    // this will use Wikipedia API to obtain more information and provide this back
    // to the user

    public static String getSummaryOf(String input) {

        // Here we are creating a new wiki and building it
        Wiki wiki = new Wiki.Builder().build();

        // These if and else if statements are for the special cases for the various
        // movie titles
        // For example, there is a Wikipedia page for Deadpool the Marvel character and
        // Deadpool the Film. We want Deadpool
        // the film, so we append (film) onto it and so forth.
        // Here we are checking if the user input equals one of these special case movie
        // names, and
        // if so we add "(film)" to the end for formatting purposes to access the
        // correct Wikipedia Page.
        if (input.equals("Deadpool") || input.equals("Green Lantern") || input.equals("6 Underground")
                || input.equals("Red Notice") || input.equals("Buried")) {
            input += " (film)";
            // Otherwise, if the movie being asked about is the proposal, then we add "
            // (2009) film" onto the end for formatting purposes to access the correct
            // wikipedia page
        } else if (input.equals("The Proposal")) {
            input += " (2009 film)";
        }

        // Initializing the string summary
        String inputSummary = "";

        // if the wiki page does NOT exist, aka wiki.exists of the input string is
        // false, then we just return a message that this wiki page does not exist
        if (wiki.exists(input) == false) {
            return "Sorry, I could not find the Wikipedia page you are looking for as it does not exist. Please rephrase your text and try again, or ask me about something else!";

            // otherwise, we set the input summary to be the wiki object extracting the text
            // from the wiki page for that specified input to get the summary of
            // and then return this summary string back to the user
        } else {
            inputSummary = wiki.getTextExtract(input);
        }
        return inputSummary;
    }
}
