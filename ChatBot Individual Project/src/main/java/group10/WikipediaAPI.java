package group10;

import org.fastily.jwiki.core.Wiki;

public class WikipediaAPI {
    

    
    public static String getSummaryOf(String input){
        Wiki wiki = new Wiki.Builder().build();
        String inputSummary ="";
        if(wiki.exists(input) == false){
            return "Sorry, I could not find the Wikipedia page you are looking for as it does not exist. Please rephrase your text and try again, or ask me about something else!";
        }else
        inputSummary = wiki.getTextExtract(input);
        return inputSummary;
    }
    }
    
    

