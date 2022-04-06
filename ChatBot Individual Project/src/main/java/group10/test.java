package group10;

import com.twitter.clientlib.ApiClient;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.Configuration;
import com.twitter.clientlib.auth.*;
import com.twitter.clientlib.model.*;
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
     
            TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(System.getenv("TWITTER_BEARER_TOKEN"));
            TwitterApi apiInstance = new TwitterApi();
            apiInstance.setTwitterCredentials(credentials);
            // Set the params values
            String id = System.getenv("TWITTER_USER_ID"); // String | The ID of the user for whom to return results
            Integer maxResults = 56; // Integer | The maximum number of results
            //String paginationToken = "paginationToken_example"; // String | This value is populated by passing the 'next_token' or 'previous_token' returned in a request to paginate through results.
            try {
                   UsersFollowingLookupResponse result = apiInstance.users().usersIdFollowing(id, maxResults, null);
                    System.out.println(result);
            } catch (ApiException e) {
              System.err.println("Exception when calling UsersApi#usersIdFollowing");
              System.err.println("Status code: " + e.getCode());
              System.err.println("Reason: " + e.getResponseBody());
              System.err.println("Response headers: " + e.getResponseHeaders());
              e.printStackTrace();
            }
          
        // String userInput = "how many tweets in the past 10 days";
       
        
        // Tokenizer.createAToken(userInput);
        // int days=0;
        // for(int i=0; i<Tokenizer.tokens.length;i++){
        // System.out.println(Tokenizer.tokens[i]);
        
        // if(Tokenizer.tokens[i].contains("1")){
        //     days = Integer.parseInt(Tokenizer.tokens[i]);
        // }
        // }
        // System.out.println(days);
    //     userInput = userInput.replaceAll("[^\\d]", " ");
  
    //     // Remove extra spaces from the beginning
    //     // and the ending of the string
    //     userInput = userInput.trim();
  
    //     // Replace all the consecutive white
    //     // spaces with a single space
    //     userInput = userInput.replaceAll(" +", " ");
    //    System.out.println(userInput);

    //    if (NumberUtils.isCreatable(userInput)) {
    //     System.out.println("String contains a creatable number!");
    // } else {
    //     System.out.println("String doesn't contain creatable number.");
    // }
    }
}

