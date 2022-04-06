package group10;

import com.twitter.clientlib.ApiClient;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.Configuration;
import com.twitter.clientlib.auth.*;
import com.twitter.clientlib.model.*;

import org.fastily.jwiki.core.Wiki;

import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.TwitterCredentialsOAuth1;
import com.twitter.clientlib.api.TwitterApi;

import com.twitter.clientlib.api.UsersApi;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.io.IOException;
import java.time.OffsetDateTime;


public class test {
    public static void main(String[]args) throws IOException{
       // System.out.println(TwitterAPI.getLikedTweets());
       Wiki wiki = new Wiki.Builder().build();
       System.out.println(wiki.getTextExtract("Buried (film)"));
//        String[] inputSummary = {"Deadpool",
//         "Deadpool 2",
//         "Free Guy",
//         "RIPD",
//         "Green Lantern",
//         "6 underground",
//         "Red Notice",
//         "Self/Less",
//         "The Hitmans Bodyguard",
//         "The Change-up",
//         "The Proposal",
//         "Mint Mobile",
// "Maximum Effort",
// "Aviation American Gin",
// "Wrexham AFC",
// "Group Effort Initiative",
// "MNTN"
// };

//        for(int i=0; i<inputSummary.length;i++){
//         String input = inputSummary[i];
//         if(wiki.exists(input) == false){
//             inputSummary[i] = "could NOT find " + input;
//         }else
//         inputSummary[i] = "Text here\n"+wiki.getTextExtract(input);
        
//     }
//     for(int i=0; i<inputSummary.length;i++){
//         System.out.println(inputSummary[i]+"\n\n\n\n");
//     }
       }
       
            
    }


