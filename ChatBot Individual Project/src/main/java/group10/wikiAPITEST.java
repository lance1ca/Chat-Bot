package group10;

import org.fastily.jwiki.core.Wiki;



public class wikiAPITEST {
    public static void main(String[]args){
        Wiki wiki = new Wiki.Builder().build();
        System.out.println( wiki.getTextExtract("Deadpool") );
    }
    
}
