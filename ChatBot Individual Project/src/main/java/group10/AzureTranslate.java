//FINAL INDIVIDUAL PROJECT
//COSC 310 Microsoft Azure Translate Class **FINISHED**
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

//importing required packages
import java.io.*;
import com.google.gson.*;
import okhttp3.*;

public class AzureTranslate {

    // Defining public and private static variables to be used later:

    // subscription key for Microsoft Azure as an environment variable called
    // TRANSLATE_SUB_KEY
    private static String subscriptionKey = System.getenv("TRANSLATE_SUB_KEY");

    // location for Microsoft Azure as an environment variable called
    // TRANSLATE_LOCATIOn
    private static String location = System.getenv("TRANSLATE_LOCATION");

    // translated input for the text which is translated back to the user
    public static String translatedInput;

    // userText which is set to the initial user input recieved
    private static String userText;

    // url for the HttpUrl connection
    private static HttpUrl url;

    // Target language is the code (en,es,fr etc) for what language was detected
    // coming into the system, i.e, what we want to translate
    // our text to so the user will understand the text in their language of choice
    public static String targetLanguage;

    // String array for the parsed JSON data to retrieve the text from
    public static String[] parseJSONText;

    // -------------------------------------------------------------------------------------------------------------------------------
    // Microsoft Azure METHOD #1 **FINISHED**
    // This method takes in a string input and sets the user text tt be this input
    // Then this method makes an attempt to convert the language from english to the
    // target language specified by the users input

    public static String translateToTarget(String input) {
        // setting user text to be the input
        userText = input;

        // specifiying url and the from language as english and to language to be the
        // target language
        url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.cognitive.microsofttranslator.com")
                .addPathSegment("/translate")
                .addQueryParameter("api-version", "3.0")
                .addQueryParameter("from", "en")
                .addQueryParameter("to", targetLanguage)
                .build();
        // try to make the translation request and prettify it into a Json format
        try {
            AzureTranslate translateRequest = new AzureTranslate();
            String response = translateRequest.Post();
            translatedInput = prettify(response);

            // This code parses the JSON text string in two places
            // It splits it at "text": and also at ",
            // This ensures we perfectly extract the translated text form a json string that
            // looks like this:
            /*
             * [
             * {
             * "detectedLanguage": {
             * "language": "en",
             * "score": 1.0
             * },
             * "translations": [
             * {
             * "text": "Hallo Welt!",
             * "to": "de"
             * },
             * {
             * "text": "Salve, mondo!",
             * "to": "it"
             * }
             * ]
             * }
             * ]
             */
            parseJSONText = translatedInput.split("\\\"text\": \"|\\\",");

            // we set translatedInput to be this parsed json text at index 1 (where the text
            // is in this string
            // array)
            translatedInput = parseJSONText[1];

            // if an error, we catch the exception and just return the original input
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return input;

            /*
             * {
             * "error": {
             * "code": 400000,
             * "message": "One of the request inputs is not valid."
             * }
             * }
             */
        }
        // return the translated input
        return translatedInput;

    }

    // -------------------------------------------------------------------------------------------------------------------------------
    // Microsoft Azure METHOD #2 **FINISHED**
    // This method takes in a string input and sets the user text to be this input
    // Then this method makes an attempt to convert the language from the target
    // language specified by the user to english

    public static String translateToEnglish(String input) {
        // setting user text to be the input
        userText = input;
        // specifiying url and the from language as target language and to language to
        // be english
        url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.cognitive.microsofttranslator.com")
                .addPathSegment("/translate")
                .addQueryParameter("api-version", "3.0")
                .addQueryParameter("from", targetLanguage)
                .addQueryParameter("to", "en")
                .build();

        // try to make the translation request and prettify it into a Json format
        try {
            AzureTranslate translateRequest = new AzureTranslate();
            String response = translateRequest.Post();
            translatedInput = prettify(response);

            // This code parses the JSON text string in two places
            // It splits it at "text": and also at ",
            parseJSONText = translatedInput.split("\\\"text\": \"|\\\",");

            // we set translatedInput to be this parsed json text at index 1 (where the text
            // is in this string
            // array)
            translatedInput = parseJSONText[1];
            // if an error, we catch the exception
        } catch (Exception e) {
            System.out.println(e);
            return "Translation Error";
        }
        // return the translated input
        return translatedInput;

    }

    // -------------------------------------------------------------------------------------------------------------------------------
    // Microsoft Azure METHOD #3 **FINISHED**
    // This method takes in a string input and sets the user text to be this input
    // Then this method makes an attempt to detect which language is being spoken
    // from the user and then sets the target language to be this
    // language code (i.e en,es,fr etc)
    public static void detectLanguage(String input) {
        // setting user text to be the input
        userText = input;
        // specifiying url and the to language to be english
        url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.cognitive.microsofttranslator.com")
                .addPathSegment("/translate")
                .addQueryParameter("api-version", "3.0")
                .addQueryParameter("to", "en")
                .build();
        // try to make the translation request and prettify it into a Json format
        try {
            AzureTranslate translateRequest = new AzureTranslate();
            String response = translateRequest.Post();
            translatedInput = prettify(response);

            // This code parses the JSON text string in two places
            // It splits it at "language": and also at ",
            String[] parseJSONText = translatedInput.split("\\\"language\": \"|\\\",");

            // here we set the target language to be the code value at index 1, i.e the
            // en,es,fr etc
            targetLanguage = parseJSONText[1];

            // if an error, we catch the exception
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // -------------------------------------------------------------------------------------------------------------------------------
    // This code below is fairly unmodified and makes the API requests sourced from
    // the Microsoft Azure API documentation and code

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        // make a request with the user text as the text being passed in
        RequestBody body = RequestBody.create(mediaType,
                "[{\"Text\": \"" + userText + "\"}]");
        Request request = new Request.Builder().url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
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