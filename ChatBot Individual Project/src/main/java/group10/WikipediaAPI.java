package group10;

import org.fastily.jwiki.core.Wiki;

public class WikipediaAPI {
    

    
    public static String getSummaryOfMovie(String movieTitle){
        Wiki wiki = new Wiki.Builder().build();
        String movieSummary ="";
        if(wiki.exists(movieTitle) == false){
            return "Sorry, I could not find the Wikipedia page you are looking as it does not exist, please rephrase your text and try again!";
        }else
        movieSummary = wiki.getTextExtract(movieTitle);
        return movieSummary;
    }
    }
    
    

