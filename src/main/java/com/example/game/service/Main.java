package com.example.game.service;

import com.google.cloud.translate.v3.*;
import com.google.rpc.context.AttributeContext;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://just-translated.p.rapidapi.com/?lang=fr&text=Hello%2C%20how%20are%20you%3F")
                .get()
                .addHeader("X-RapidAPI-Key", "14d3e894b6msha6982da9dc1c91ap100ba0jsna31fe25ac4f0")
                .addHeader("X-RapidAPI-Host", "just-translated.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.toString());
    }

    // Set and pass variables to overloaded translateText() method for translation.
    public static void translateText() throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String projectId = "AIzaSyDZnWK-g7z-t57plpeDrmknl0zDGex_vJ8";
        // Supported Languages: https://cloud.google.com/translate/docs/languages
        String targetLanguage = "rus";
        String text = "hello world, this message from my first project";
        translateText(projectId, targetLanguage, text);
    }

    // Translate text to target language.
    public static void translateText(String projectId, String targetLanguage, String text)
            throws IOException {

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            // Supported Locations: `global`, [glossary location], or [model location]
            // Glossaries must be hosted in `us-central1`
            // Custom Models must use the same location as your model. (us-central1)
            LocationName parent = LocationName.of(projectId, "global");

            // Supported Mime Types: https://cloud.google.com/translate/docs/supported-formats
            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode(targetLanguage)
                            .addContents(text)
                            .build();

            TranslateTextResponse response = client.translateText(request);

            // Display the translation for each input text provided
            for (Translation translation : response.getTranslationsList()) {
                System.out.printf("Translated text: %s\n", translation.getTranslatedText());
            }
        }
    }
}
