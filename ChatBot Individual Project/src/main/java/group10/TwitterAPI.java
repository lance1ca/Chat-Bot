package group10;

// Import classes:

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.model.*;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class TwitterAPI {
    public static void main(String[] args) {
        TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(System.getenv("TWITTER_BEARER_TOKEN"));
    
        TwitterApi apiInstance = new TwitterApi();
        apiInstance.setTwitterCredentials(credentials);
        
        
        String id = System.getenv("TWITTER_USER_ID"); // String | The ID of the User to list Tweets of
       
        
        Integer maxResults = 5; // Integer | The maximum number of results
        Set<String> exclude = new HashSet<>(Arrays.asList()); // Set<String> | The set of entities to exclude (e.g. 'replies' or 'retweets')
        // String paginationToken = "paginationToken_example"; // String | This parameter is used to get the next 'page' of results.
        
        //Getting the current date 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String dateUnformatted = dtf.format(now);

        //set the date to the 2022-02-01T18:40:40.000Z format
        //year
        char a = dateUnformatted.charAt(0);
        char b = dateUnformatted.charAt(1);
        char c = dateUnformatted.charAt(2);
        char d = dateUnformatted.charAt(3);
        //dash
        //month
        char e = dateUnformatted.charAt(5);
        char f = dateUnformatted.charAt(6);
        //day
        char g = dateUnformatted.charAt(8);
        char h = dateUnformatted.charAt(9);
        //creating the formatted string with todays date
        String dateFormatted = Character.toString(a)+Character.toString(b)+Character.toString(c)+Character.toString(d)+"-"+Character.toString(e)+Character.toString(f)+"-"+Character.toString(g)+Character.toString(h)+"T00:00:00.000Z";
        System.out.println(dateFormatted);





        //Getting the date a certain number of days from the current date as specified in user input
       // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        //LocalDateTime now = LocalDateTime.now();
        //String dateUnformatted = dtf.format(now);
        LocalDate birthDate = LocalDate.of(1976, 6, 23);
        LocalDate currentDate = LocalDate.now();
        Period difference = Period.between(birthDate, currentDate);

        //set the date to the 2022-02-01T18:40:40.000Z format
        //year
        // char a = dateUnformatted.charAt(0);
        // char b = dateUnformatted.charAt(1);
        // char c = dateUnformatted.charAt(2);
        // char d = dateUnformatted.charAt(3);
        // //dash
        // //month
        // char e = dateUnformatted.charAt(5);
        // char f = dateUnformatted.charAt(6);
        // //day
        // char g = dateUnformatted.charAt(8);
        // char h = dateUnformatted.charAt(9);
        // //creating the formatted string with todays date
        // String dateFormatted = Character.toString(a)+Character.toString(b)+Character.toString(c)+Character.toString(d)+"-"+Character.toString(e)+Character.toString(f)+"-"+Character.toString(g)+Character.toString(h)+"T00:00:00.000Z";
        // System.out.println(dateFormatted);

        
        OffsetDateTime startTime = OffsetDateTime.parse("2022-02-01T18:40:40.000Z"); // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The earliest UTC timestamp from which the Tweets will be provided. The since_id parameter takes precedence if it is also specified.
        OffsetDateTime endTime = OffsetDateTime.parse(dateFormatted); // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The latest UTC timestamp to which the Tweets will be provided. The until_id parameter takes precedence if it is also specified.
        Set<String> expansions = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of fields to expand.
        Set<String> tweetFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Tweet fields to display.
        Set<String> userFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of User fields to display.
        Set<String> mediaFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Media fields to display.
        Set<String> placeFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Place fields to display.
        Set<String> pollFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Poll fields to display.
        try {
               GenericTweetsTimelineResponse result = apiInstance.tweets().usersIdTweets(id, null, null, maxResults, exclude, null, startTime, endTime, expansions, tweetFields, userFields, mediaFields, placeFields, pollFields);
                //System.out.println(result.getData());
                for(int i=0; i<result.getData().size();i++){
                    System.out.println(result.getData().get(i).getText());
                    
                }
            //    String results2 = result.toString();
            //    String[] results3 = results2.split("text:");
            //    for(int i=0; i<results3.length;i++){
                
            //        System.out.println(results3[i]);
                   
            //    }
        } catch (ApiException except) {
          System.err.println("Exception when calling TweetsApi#usersIdTweets");
          System.err.println("Status code: " + except.getCode());
          System.err.println("Reason: " + except.getResponseBody());
          System.err.println("Response headers: " + except.getResponseHeaders());
          except.printStackTrace();
        }
    
        
      }
    
}
