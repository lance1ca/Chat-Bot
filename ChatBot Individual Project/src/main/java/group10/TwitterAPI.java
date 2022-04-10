//FINAL INDIVIDUAL PROJECT
//COSC 310 Twitter API Class **FINISHED**
//By: LANCE ROGAN, 62708938 
//Note: API from https://github.com/twitterdev/twitter-api-java-sdk which was 
//retrieved from https://developer.twitter.com/en/docs/twitter-api/tools-and-libraries/v2
package group10;

//importing require packages to access the Twitter API
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

  // Defining public static variables for later use

  // Defining the Twitter credentials bearer to be the Twitter bearer token
  // Note that this bearer token is hidden and secured in an environment variable
  // on this computer which can be retrieved via System.getenv(name of environment
  // variable)
  public static TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(
      System.getenv("TWITTER_BEARER_TOKEN"));

  // Defining the Twitter api instance
  public static TwitterApi apiInstance = new TwitterApi();

  // Defining the id of the Twitter user we would like to focus on
  // This Twitter ID is stored in an environment variable as well, and is the ID
  // for Ryan Reynolds Twitter, @VancityReynolds
  public static String id = System.getenv("TWITTER_USER_ID");

  // -------------------------------------------------------------------------------------------------------------------------------------
  // Twitter API Method #1 **FINISHED**
  // This method returns a formatted string of a maximum of 56 different Twitter
  // user names that Ryan Reynolds is following at this current moment.
  // In other words, this accesses the Twitter API and retrieves a sample of a
  // maximum size of 56 Twitter users that Ryan Reynolds follows and displays
  // their names.

  public static String getSampleOfCurrentFollowing() {

    // setting the Twitter API instance's credentials to be the credentials defined
    // earlier with the environment variable for the bearer token
    apiInstance.setTwitterCredentials(credentials);

    // initializing the string to be returned which conatains the sample of the
    // people Ryan Reynolds is following
    String currentSampleFollowing = "";

    // An integer which defines the maximum number of results
    Integer maxResults = 56;

    // Commented out and set to null for my particular use
    // String paginationToken = "paginationToken_example"; //This value is populated
    // by passing the 'next_token' or 'previous_token' returned in a request to
    // paginate through results.

    // Here we try to retrieve a sample size of a max of 56 users which Ryan
    // Reynolds follows
    try {
      UsersFollowingLookupResponse result = apiInstance.users().usersIdFollowing(id, maxResults, null);

      // if the results object's data array list of users is NOT null, aka it has
      // users in the data list
      // then we continue, otherwise we return it is null
      if (result.getData() != null) {
        // Here we iterate through the results size (object that contains other data and
        // an array list of users called data)
        // and we add the name of the specific user at that index to the following
        // string in the appropriate format
        for (int i = 0; i < result.getData().size(); i++) {

          currentSampleFollowing += result.getData().get(i).getName() + ", ";
        }
        // otherwise set the string to be the null string
      } else {
        currentSampleFollowing = "Data is Null";
      }

      // catching the API exception and printing the code, response body and headers,
      // and the errors back to the user
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#usersIdFollowers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
    // return the sample following string back to user
    return currentSampleFollowing;
  }

  // -------------------------------------------------------------------------------------------------------------------------------------
  // Twitter API Method #2 **FINISHED**
  // This method returns a formatted string of a maximum of 56 different Twitter
  // user names that are followers of Ryan Reynolds at this current moment.
  // In other words, this accesses the Twitter API and retrieves a sample of a
  // maximum size of 56 Twitter users that are followers of Ryan Reynolds and
  // displays their names.

  public static String getSampleOfCurrentFollowers() {

    // setting the Twitter API instance's credentials to be the credentials defined
    // earlier with the environment variable for the bearer token
    apiInstance.setTwitterCredentials(credentials);

    // initializing the string to be returned which conatains the sample of the
    // people who are followers of Ryan Reynolds
    String currentSampleFollowers = "";

    // An integer which defines the maximum number of results
    Integer maxResults = 56;

    // Commented out and set to null for my particular use
    // String paginationToken = "paginationToken_example"; //This value is populated
    // by passing the 'next_token' or 'previous_token' returned in a request to
    // paginate through results.

    // Here we try to retrieve a sample size of a max of 56 users which are
    // followers of Ryan Reynolds
    try {
      GenericMultipleUsersLookupResponse result = apiInstance.users().usersIdFollowers(id, maxResults, null);
      // if the results object's data array list of users is NOT null, aka it has
      // users in the data list
      // then we continue, otherwise we return it is null
      if (result.getData() != null) {

        // Here we iterate through the results size (object that contains other data and
        // an array list of users called data)
        // and we add the name of the specific user at that index to the followers
        // string in the appropriate format
        for (int i = 0; i < result.getData().size(); i++) {

          currentSampleFollowers += result.getData().get(i).getName() + ", ";
        }
        // otherwise set the string to be the null string
      } else {
        currentSampleFollowers = "Data is Null";
      }
      // catching the API exception and printing the code, response body and headers,
      // and the errors back to the user
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#usersIdFollowers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
    // returning the string back to user
    return currentSampleFollowers;
  }

  // -------------------------------------------------------------------------------------------------------------------------------------
  // Twitter API Method #3**FINISHED**
  // This method returns a formatted string of a maximum of 100 different tweets
  // posted by Ryan Reynolds in a given time range.
  // In other words, this method connects via API and retrieves all tweets posted
  // by Ryan Reynolds in a range specified by the user talking to the chat bot

  public static String getTweetsInRange(int days) {

    // setting the Twitter API instance's credentials to be the credentials defined
    // earlier with the environment variable for the bearer token
    apiInstance.setTwitterCredentials(credentials);

    // initializing the string to be returned which conatains the tweets
    // posted by Ryan Reynolds in the given time range
    String tweetsInTimeRange = "";

    // An integer which defines the maximum number of results
    Integer maxResults = 100;

    // Set<String> | The set of entities to exclude (e.g.
    // 'replies' or 'retweets')
    Set<String> exclude = new HashSet<>(Arrays.asList());

    // Getting the current date from today
    LocalDate todaysDate = LocalDate.now();

    // Creating the formatted string for this API with todays date
    String currentDate = todaysDate + "T00:00:00.000Z";

    // Getting the date a certain number of days from the current date as specified
    // in user input. i.e, we are moving back a certain number of days as specified
    // by the user
    // here we set the previous date to be todays date minus that number of days
    LocalDate prevDate = todaysDate.plusDays(-days);

    // setting the format of the previous date
    String previousDate = prevDate + "T00:00:00.000Z";

    // a print statement to check dates if needed
    // System.out.println(currentDate +" "+previousDate);

    // Leaving these as null as I do not need it
    // String sinceId = "id"; // String | The minimum Tweet ID to be included in the
    // result set. This parameter takes precedence over start_time if both are
    // specified.
    // String untilId = "id"; // String | The maximum Tweet ID to be included in the
    // result set. This parameter takes precedence over end_time if both are
    // specified.

    // Leave this as null as I do not need it
    // String paginationToken = "paginationToken_example"; // String | This
    // parameter is used to get the next 'page' of results.

    // Here we are parsing the previous date
    OffsetDateTime startTime = OffsetDateTime.parse(previousDate); // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The
                                                                   // earliest UTC timestamp from which the Tweets will
                                                                   // be provided. The since_id parameter takes
                                                                   // precedence if it is also specified.
    // Here we are parsing the current date
    OffsetDateTime endTime = OffsetDateTime.parse(currentDate); // OffsetDateTime | YYYY-MM-DDTHH:mm:ssZ. The latest UTC
                                                                // timestamp to which the Tweets will be provided. The
                                                                // until_id parameter takes precedence if it is also
                                                                // specified.
    // Defining the hash sets
    Set<String> expansions = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of fields to
                                                             // expand.
    Set<String> tweetFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Tweet fields to
                                                              // display.
    Set<String> userFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of User fields to
                                                             // display.
    Set<String> mediaFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Media fields to
                                                              // display.
    Set<String> placeFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Place fields to
                                                              // display.
    Set<String> pollFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Poll fields to
                                                             // display.

    // here we try to retrieve all the tweets from Ryan Reynolds in this given time
    // range
    try {
      GenericTweetsTimelineResponse result = apiInstance.tweets().usersIdTweets(id, null, null, maxResults, exclude,
          null, startTime, endTime, expansions, tweetFields, userFields, mediaFields, placeFields, pollFields);

      // if the results object's data array list of users is NOT null, aka it has
      // tweets in the data list then we continue, otherwise we return it is null
      if (result.getData() != null) {

        // Here we iterate through the results size (object that contains other data and
        // a list of tweets called data)
        // and we add the tweet text of the specific tweet at that index to the tweets
        // in time range
        // string in the appropriate format
        for (int i = 0; i < result.getData().size(); i++) {

          tweetsInTimeRange += result.getData().get(i).getText() + "\n";
        }
        // otherwise set the string to be the null string
      } else {
        tweetsInTimeRange = "Data is Null";
      }

      // catching the API exception and printing the code, response body and headers,
      // and the errors back to the user
    } catch (ApiException except) {
      System.err.println("Exception when calling TweetsApi#usersIdTweets");
      System.err.println("Status code: " + except.getCode());
      System.err.println("Reason: " + except.getResponseBody());
      System.err.println("Response headers: " + except.getResponseHeaders());
      except.printStackTrace();
    }
    // returning string back to user
    return tweetsInTimeRange;

  }

  // -------------------------------------------------------------------------------------------------------------------------------------
  // Twitter API Method #4**FINISHED**
  // This method returns a formatted string of a maximum of 56 different tweets
  // liked by Ryan Reynolds.
  // In other words, this method connects via API and retrieves all tweets liked
  // by Ryan Reynolds as requested by the user talking to the chat bot

  public static String getLikedTweets() {

    // setting the Twitter API instance's credentials to be the credentials defined
    // earlier with the environment variable for the bearer token
    apiInstance.setTwitterCredentials(credentials);

    // initializing the string to be returned which conatains the tweets
    // liked by Ryan Reynolds
    String likedTweets = "";

    // An integer which defines the maximum number of results
    // limiting it to 10 results as this is a reasonable amount of information to
    // display
    Integer maxResults = 10;

    // setting this to null as I dont need it
    // String paginationToken = "paginationToken_example"; // String | This
    // parameter is used to get the next 'page' of results.

    // Hash sets defined below
    Set<String> expansions = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of fields to
                                                             // expand.
    Set<String> tweetFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Tweet fields to
                                                              // display.
    Set<String> userFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of User fields to
                                                             // display.
    Set<String> mediaFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Media fields to
                                                              // display.
    Set<String> placeFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Place fields to
                                                              // display.
    Set<String> pollFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Poll fields to
                                                             // display.

    // Here we try to retrieve all of the tweets liked by Ryan Reynolds up to a
    // maximum of 56 results
    try {
      UsersIdLikedTweetsResponse result = apiInstance.tweets().usersIdLikedTweets(id, maxResults, null, expansions,
          tweetFields, userFields, mediaFields, placeFields, pollFields);

      // if the results object's data array list of users is NOT null, aka it has
      // tweets in the data list then we continue, otherwise we return it is null
      if (result.getData() != null) {

        // Here we iterate through the results size (object that contains other data and
        // a list of (liked) tweets called data)
        // and we add the tweet text of the specific tweet at that index to the tweets
        // in time range
        // string in the appropriate format
        for (int i = 0; i < result.getData().size(); i++) {

          likedTweets += result.getData().get(i).getText() + "\n";
        }
        // otherwise set the string to be the null string
      } else {
        likedTweets = "Data is Null";
      }

      // catching the API exception and printing the code, response body and headers,
      // and the errors back to the user
    } catch (ApiException e) {
      System.err.println("Exception when calling TweetsApi#usersIdLikedTweets");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
    // returning string back to user
    return likedTweets;
  }

}
