package group10;

import org.fastily.jwiki.core.Wiki;

public class WikipediaAPI {
    public static void main(String[]args){
        Wiki wiki = new Wiki.Builder().build();
        System.out.println( wiki.getTextExtract("Green Lantern"));
    }

    //need to account for usage of different cases, aka free guy is null, Free Guy is the movie
    public static String getSummaryOfMovie(String movieTitle){
        Wiki wiki = new Wiki.Builder().build();
        String movieSummary = wiki.getTextExtract(movieTitle);
        return movieSummary;
    }
    
}
