package com.example.game;

import com.example.game.service.Parser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;


@SpringBootApplication
public class GameApplication {

    public static void main(String[] args) {

        Parser parser = new Parser();

        System.out.println(parser.autoParser());

    }

    public void method() {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost request = getHttpPost();

            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                // Обработайте ответ
                String responseBody = EntityUtils.toString(responseEntity);
                System.out.println("Ответ от сервера: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static HttpPost getHttpPost() throws UnsupportedEncodingException {
        HttpPost request = new HttpPost("https://translate.api.cloud.yandex.net/translate/v2/translate");

        // Установите заголовки, если необходимо
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Bearer 20230907T101455Z.c99e36688d357b50.d01487696b10df8676f148408c498281708b4880");

        // Задайте тело запроса в формате JSON
        String jsonBody = "{" +
                "text\": \"Привет, мир!\"," +
                "sourceLanguageCode\": \"ru\", " +
                "targetLanguageCode\": \"en\"" +
                "}";
        StringEntity entity = new StringEntity(jsonBody);
        request.setEntity(entity);
        return request;
    }


}
