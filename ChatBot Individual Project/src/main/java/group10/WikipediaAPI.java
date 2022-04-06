package group10;

import org.fastily.jwiki.core.Wiki;

public class WikipediaAPI {
    

    
    public static String getSummaryOf(String input){
        Wiki wiki = new Wiki.Builder().build();
        String inputSummary ="";
        if(wiki.exists(input) == false){
            return "Sorry, I could not find the Wikipedia page you are looking as it does not exist, please rephrase your text and try again!";
        }else
        inputSummary = wiki.getTextExtract(input);
        return inputSummary;
    }
    }
    
    

