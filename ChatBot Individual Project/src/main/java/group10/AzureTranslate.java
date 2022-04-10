
package group10;
import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;
import com.squareup.okhttp.*;




public class AzureTranslate {
  
    private static String subscriptionKey = System.getenv("TRANSLATE_SUB_KEY");

    
    // Add your location, also known as region. The default is global.
    // This is required if using a Cognitive Services resource.
    private static String location = System.getenv("TRANSLATE_LOCATION");
    private static String translatedInput;
   
    public static String translateToLanguage(String input){
        try {
            AzureTranslate translateRequest = new AzureTranslate();
            String response = translateRequest.Post();
            translatedInput = prettify(response);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return translatedInput;
    }

    HttpUrl url = new HttpUrl.Builder()
        .scheme("https")
        .host("api.cognitive.microsofttranslator.com")
        .addPathSegment("/translate")
        .addQueryParameter("api-version", "3.0")
        .addQueryParameter("from", "en")
        .addQueryParameter("to", "es")
        .build();

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
        "[{\"Text\": \"Hello World!\"}]");
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


    public static void main(String[] args) {
        try {
            AzureTranslate translateRequest = new AzureTranslate();
            String response = translateRequest.Post();
            //System.out.println(prettify(response));
            translateToLanguage(prettify(response));
String[] parseJSONText = translatedInput.split("\\\"text\": \"|\\\",");
System.out.println(parseJSONText[1]);


           
           // System.out.println(translatedInput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}