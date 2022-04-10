//FINAL INDIVIDUAL PROJECT
//COSC 310 Twitter API Class **FINISHED**
//By: LANCE ROGAN, 62708938 

package group10;



import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.model.*;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.OffsetDateTime;



public class TwitterAPI {
  public static TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(System.getenv("TWITTER_BEARER_TOKEN"));
  public static TwitterApi apiInstance = new TwitterApi();
  public static String id = System.getenv("TWITTER_USER_ID"); // String | The ID of the User to list Tweets of
  

  //-------------------------------------------------------------------------------------------------------------------------------------
  public static String getSampleOfCurrentFollowing(){

    apiInstance.setTwitterCredentials(credentials);
    
    String currentSampleFollowing="";
    Integer maxResults = 56; // Integer | The maximum number of results
   
    try {
           GenericMultipleUsersLookupResponse result = apiInstance.users().usersIdFollowers(id, maxResults, null);
           for(int i=0; i<result.getData().size();i++){
                    
            currentSampleFollowing += result.getData().get(i).getName()+ ", ";
        }
           

    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#usersIdFollowers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
    return currentSampleFollowing;
  }
  


  //-------------------------------------------------------------------------------------------------------------------------------------
  public static String getSampleOfCurrentFollowers(){
    apiInstance.setTwitterCredentials(credentials);
    
    String currentSampleFollowers="";
    Integer maxResults = 56; // Integer | The maximum number of results
    
    try {
           GenericMultipleUsersLookupResponse result = apiInstance.users().usersIdFollowers(id, maxResults, null);
           for(int i=0; i<result.getData().size();i++){
                    
            currentSampleFollowers += result.getData().get(i).getName()+ ", ";
        }
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#usersIdFollowers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
    return currentSampleFollowers;
  }










//-------------------------------------------------------------------------------------------------------------------------------------

    
      public static String getTweetsInRange(int days){
        String tweetsInTimeRange ="";
        apiInstance.setTwitterCredentials(credentials);

        Integer maxResults = 100; // Integer | The maximum number of results
        Set<String> exclude = new HashSet<>(Arrays.asList()); // Set<String> | The set of entities to exclude (e.g. 'replies' or 'retweets')
        
        //Getting the current date 

        LocalDate todaysDate = LocalDate.now(); 
        
        //creating the formatted string with todays date
        String currentDate = todaysDate+"T00:00:00.000Z";

        //Getting the date a certain number of days from the current date as specified in user input
       // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    
     LocalDate prevDate = todaysDate.plusDays(-days);
     String previousDate = prevDate+"T00:00:00.000Z";
     //System.out.println(currentDate +" "+previousDate);
     

       
        
        OffsetDateTime startTime = OffsetDateTime.parse(previousDate); // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The earliest UTC timestamp from which the Tweets will be provided. The since_id parameter takes precedence if it is also specified.
        OffsetDateTime endTime = OffsetDateTime.parse(currentDate); // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The latest UTC timestamp to which the Tweets will be provided. The until_id parameter takes precedence if it is also specified.
        Set<String> expansions = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of fields to expand.
        Set<String> tweetFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Tweet fields to display.
        Set<String> userFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of User fields to display.
        Set<String> mediaFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Media fields to display.
        Set<String> placeFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Place fields to display.
        Set<String> pollFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Poll fields to display.
        try {
               GenericTweetsTimelineResponse result = apiInstance.tweets().usersIdTweets(id, null, null, maxResults, exclude, null, startTime, endTime, expansions, tweetFields, userFields, mediaFields, placeFields, pollFields);
                
                for(int i=0; i<result.getData().size();i++){
                    
                    tweetsInTimeRange += result.getData().get(i).getText() + "\n";
                }
                
         
        } catch (ApiException except) {
          System.err.println("Exception when calling TweetsApi#usersIdTweets");
          System.err.println("Status code: " + except.getCode());
          System.err.println("Reason: " + except.getResponseBody());
          System.err.println("Response headers: " + except.getResponseHeaders());
          except.printStackTrace();
        }
        return tweetsInTimeRange;
    
        
      }




  //-------------------------------------------------------------------------------------------------------------------------------------

  public static String getLikedTweets(){
    apiInstance.setTwitterCredentials(credentials);
    String likedTweets ="";
    Integer maxResults = 56; // Integer | The maximum number of results
    //String paginationToken = "paginationToken_example"; // String | This parameter is used to get the next 'page' of results.
    Set<String> expansions = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of fields to expand.
    Set<String> tweetFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Tweet fields to display.
    Set<String> userFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of User fields to display.
    Set<String> mediaFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Media fields to display.
    Set<String> placeFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Place fields to display.
    Set<String> pollFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Poll fields to display.
    try {
           UsersIdLikedTweetsResponse result = apiInstance.tweets().usersIdLikedTweets(id, maxResults, null, expansions, tweetFields, userFields, mediaFields, placeFields, pollFields);
           for(int i=0; i<result.getData().size();i++){
                    
            likedTweets += result.getData().get(i).getText() + "\n";
        } 

    } catch (ApiException e) {
      System.err.println("Exception when calling TweetsApi#usersIdLikedTweets");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
    return likedTweets;
  }

     
  }

      

    

