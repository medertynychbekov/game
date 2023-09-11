package com.example.game.service;

import com.example.game.model.enitity.Country;
import com.example.game.model.enitity.CurrencyInfo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class Parser {

//    public Country parser(String URL) {
//        Country country = new Country();
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        try {
//
//            URL obj = new URL(URL);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("GET");
//
//            int responseCode = con.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//
//            StringBuilder response = new StringBuilder();
//            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//            }
//
//            JsonElement jsonElement = new com.google.gson.Parser().parse(response.toString());
//
//            String parsedJson = gson.toJson(jsonElement);
//            JSONArray jsonArray = new JSONArray(parsedJson);
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                JSONObject nameObject = jsonObject.getJSONObject("name");
//
//                JSONObject currencies = jsonObject.getJSONObject("currencies");
//
//                JSONArray capitals = jsonObject.getJSONArray("capital");
//
//                JSONObject languages = jsonObject.getJSONObject("languages");
//
//                JSONArray borders = jsonObject.getJSONArray("borders");
//
//                JSONArray timesZones = jsonObject.getJSONArray("timezones");
//
//                country.setName(nameObject.getString("common"));
//                country.setOfficialName(nameObject.getString("official"));
//
//
//                List<CurrencyInfo> currencyInfoList = new ArrayList<>();
//
//                Iterator<String> currencyKeys = currencies.keys();
//                while (currencyKeys.hasNext()) {
//                    String currencyCode = currencyKeys.next();
//                    JSONObject currencyObject = currencies.getJSONObject(currencyCode);
//                    String currencyName = currencyObject.getString("name");
//                    String currencySymbol = currencyObject.getString("symbol");
//                    CurrencyInfo currencyInfo = new CurrencyInfo(currencyCode, currencyName, currencySymbol);
////                    currencyInfo.setCountry(country);
//                    currencyInfoList.add(currencyInfo);
//                }
//
//                country.setCurrencyInfos(currencyInfoList);
//                country.setCapital(capitals.getString(0));
//
//                Map<String, String> languagesMap = new HashMap<>();
//                Iterator<String> languageKeys = languages.keys();
//
//                while (languageKeys.hasNext()) {
//                    String key = languageKeys.next();
//                    String value = languages.getString(key);
//                    languagesMap.put(key, value);
//                }
//
//                country.setLanguage(languagesMap);
//
//                List<String> borderList = new ArrayList<>();
//                for (int j = 0; j < borders.length(); j++) {
//                    String borderCountry = borders.getString(j);
//                    borderList.add(borderCountry);
//                }
//                country.setBorders(borderList);
//
//
//                country.setPopulation(jsonObject.getDouble("population"));
//
//                List<String> timeZoneList = new ArrayList<>();
//                for (int j = 0; j < timesZones.length(); j++) {
//                    String timeZone = timesZones.getString(j);
//                    timeZoneList.add(timeZone);
//                }
//                country.setTimeZone(timeZoneList);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return country;
//    }

    public String getResult(String url) {
        StringBuilder response = new StringBuilder();
        try {

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response.toString();
    }

    public List<Country> getCountryFromJson(String url) {
        String response = getResult(url);
        List<Country> countries = new ArrayList<>();
        Country country = new Country();

        Gson gson = new Gson();

        JsonElement jsonElement = new JsonParser().parse(response);

        String parsedJson = gson.toJson(jsonElement);
        JSONArray jsonArray = new JSONArray(parsedJson);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            JSONObject nameObject = jsonObject.getJSONObject("name");
            JSONObject currencies = jsonObject.getJSONObject("currencies");
            JSONArray capitals = jsonObject.getJSONArray("capital");
            JSONObject languages = jsonObject.getJSONObject("languages");
            // JSONArray borders = jsonObject.getJSONArray("borders");
            JSONArray timesZones = jsonObject.getJSONArray("timezones");

            country.setName(nameObject.getString("common"));
            country.setOfficialName(nameObject.getString("official"));

            List<CurrencyInfo> currencyInfoList = new ArrayList<>();

            Iterator<String> currencyKeys = currencies.keys();
            while (currencyKeys.hasNext()) {
                String currencyCode = currencyKeys.next();
                JSONObject currencyObject = currencies.getJSONObject(currencyCode);
                String currencyName = currencyObject.getString("name");
                String currencySymbol = currencyObject.getString("symbol");
                CurrencyInfo currencyInfo = new CurrencyInfo(currencyCode, currencyName, currencySymbol);
                currencyInfoList.add(currencyInfo);
            }

            country.setCurrencyInfos(currencyInfoList);
            country.setCapital(capitals.getString(0));

            Map<String, String> languagesMap = new HashMap<>();
            Iterator<String> languageKeys = languages.keys();

            while (languageKeys.hasNext()) {
                String key = languageKeys.next();
                String value = languages.getString(key);
                languagesMap.put(key, value);
            }

//            country.setLanguage(languagesMap);

//            List<String> borderList = new ArrayList<>();
//            for (int j = 0; j < borders.length(); j++) {
//                String borderCountry = borders.getString(j);
//                borderList.add(borderCountry);
//            }
//            country.setBorders(borderList);
            country.setPopulation(jsonObject.getDouble("population"));

            List<String> timeZoneList = new ArrayList<>();
            for (int j = 0; j < timesZones.length(); j++) {
                String timeZone = timesZones.getString(j);
                timeZoneList.add(timeZone);
            }
            country.setTimeZone(timeZoneList);
            countries.add(country);
        }
        return countries;
    }

    public List<Country> autoParser() {
        Gson gson = new Gson();
        String json = getResult("https://restcountries.com/v3.1/name/kyrgyzstan");
        return List.of(gson.fromJson(json, Country[].class));
    }


}

