//FINAL INDIVIDUAL PROJECT
//COSC 310 Wikipedia API Class **FINISHED*
//By: LANCE ROGAN, 62708938 

package group10;

import org.fastily.jwiki.core.Wiki;


public class WikipediaAPI {
    

    
    public static String getSummaryOf(String input){
        Wiki wiki = new Wiki.Builder().build();

        if(input.equals("Deadpool") || input.equals("Deadpool 2")|| input.equals("Green Lantern")|| input.equals("6 Underground")|| input.equals("Red Notice")|| input.equals("Buried")){
            input += " (film)";
        }else if(input.equals("The Proposal")){
            input += " (2009 film)";
        }

        String inputSummary ="";
        if(wiki.exists(input) == false){
            return "Sorry, I could not find the Wikipedia page you are looking for as it does not exist. Please rephrase your text and try again, or ask me about something else!";
        }else
        inputSummary = wiki.getTextExtract(input);
        return inputSummary;
    }
    }
    
    

