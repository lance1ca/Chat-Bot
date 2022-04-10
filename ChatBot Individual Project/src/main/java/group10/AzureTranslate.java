//FINAL INDIVIDUAL PROJECT
//COSC 310 Twitter API Class **FINISHED**
//By: LANCE ROGAN, 62708938 
//Note: API from https://docs.microsoft.com/en-us/azure/cognitive-services/translator/quickstart-translator?tabs=java
//This uses the google gson dependency
//From this API documentation, it was noted to use:
/*
<dependency>
      <groupId>com.squareup.okhttp</groupId>
      <artifactId>okhttp</artifactId>
      <version>2.7.5</version>
    </dependency>
*/ 
//But this was causing errors, so I used the okhttp3 import (as VS Code suggested automatically) rather than that dependency

package group10;
import java.io.*;
import com.google.gson.*;
import okhttp3.*;






public class AzureTranslate {
  
    private static String subscriptionKey = System.getenv("TRANSLATE_SUB_KEY");

    
    // Add your location, also known as region. The default is global.
    // This is required if using a Cognitive Services resource.
    private static String location = System.getenv("TRANSLATE_LOCATION");
    public static String translatedInput;
    private static String userText;
   private static HttpUrl url;
   public static String targetLanguage;

   //-------------------------------------------------------------------------------------------------------------------------------
   public static String translateToTarget(String input){
    userText = input;
    url = new HttpUrl.Builder()
    .scheme("https")
    .host("api.cognitive.microsofttranslator.com")
    .addPathSegment("/translate")
    .addQueryParameter("api-version", "3.0")
    .addQueryParameter("from", "en")
    .addQueryParameter("to", targetLanguage)
    .build();
    try {
        AzureTranslate translateRequest = new AzureTranslate();
        String response = translateRequest.Post();
        translatedInput = prettify(response);
       
        //method to parse the json text and get the translated text
String[] parseJSONText = translatedInput.split("\\\"text\": \"|\\\",");

return parseJSONText[1].toLowerCase();
        
    } catch (Exception e) {
        System.out.println(e);
    }
    return translatedInput;
}


//-------------------------------------------------------------------------------------------------------------------------------
    

//-------------------------------------------------------------------------------------------------------------------------------
    public static String translateToEnglish(String input){
        userText = input;
        url = new HttpUrl.Builder()
        .scheme("https")
        .host("api.cognitive.microsofttranslator.com")
        .addPathSegment("/translate")
        .addQueryParameter("api-version", "3.0")
        .addQueryParameter("from", targetLanguage)
        .addQueryParameter("to", "en")
        .build();
        try {
            AzureTranslate translateRequest = new AzureTranslate();
            String response = translateRequest.Post();
            translatedInput = prettify(response);
           
            //method to parse the json text and get the translated text
String[] parseJSONText = translatedInput.split("\\\"text\": \"|\\\",");
//System.out.println(parseJSONText[1].toLowerCase());
return parseJSONText[1].toLowerCase();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return translatedInput;
    }


//-------------------------------------------------------------------------------------------------------------------------------

    public static String detectLanguage(String input){
        userText = input;
        url = new HttpUrl.Builder()
        .scheme("https")
        .host("api.cognitive.microsofttranslator.com")
        .addPathSegment("/translate")
        .addQueryParameter("api-version", "3.0")
        .addQueryParameter("to", "en")
        .build();
        try {
            AzureTranslate translateRequest = new AzureTranslate();
            String response = translateRequest.Post();
            translatedInput = prettify(response);
            
            //method to parse the json text and get the translated text
String[] parseJSONText = translatedInput.split("\\\"language\": \"|\\\",");
targetLanguage = parseJSONText[1];

            
        } catch (Exception e) {
            System.out.println(e);
        }
        return translateToEnglish(input);
    }
    
//-------------------------------------------------------------------------------------------------------------------------------
    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
        "[{\"Text\": \""+userText+"\"}]");
        Request request = new Request.Builder().url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type","application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        // jsonString is of type java.lang.String
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }


}