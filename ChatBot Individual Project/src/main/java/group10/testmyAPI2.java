package group10;

import java.util.HashSet;
import java.util.Set;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.*;

public class testmyAPI2 {

  public static void main(String[] args) {
    /**
     * Set the credentials for the required APIs.
     * The Java SDK supports TwitterCredentialsOAuth2, TwitterCredentialsBearer & TwitterCredentialsOAuth1.
     * Check the 'security' tag of the required APIs in https://api.twitter.com/2/openapi.json in order
     * to use the right credential object.
     */
    TwitterApi apiInstance = new TwitterApi();
    TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(System.getenv("TWITTER_BEARER_TOKEN"));
    apiInstance.setTwitterCredentials(credentials);

    Set<String> tweetFields = new HashSet<>();
    tweetFields.add("author_id");
    tweetFields.add("id");
    tweetFields.add("created_at");

    try {

     // findTweetById
     SingleTweetLookupResponse result = apiInstance.tweets().findTweetById("number", null, tweetFields, null , null, null, null);
     if(result.getErrors() != null && result.getErrors().size() > 0) {
       System.out.println("Error:");

       result.getErrors().forEach(e -> {
         System.out.println(e.toString());
         if (e instanceof ResourceUnauthorizedProblem) {
           System.out.println(((ResourceUnauthorizedProblem) e).getTitle() + " " + ((ResourceUnauthorizedProblem) e).getDetail());
         }
       });
     } else {
       System.out.println("findTweetById - Tweet Text: " + result.toString());
     }
    } catch (ApiException e) {
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

  }
}