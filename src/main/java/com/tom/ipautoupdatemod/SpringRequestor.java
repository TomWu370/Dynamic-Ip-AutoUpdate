package com.tom.ipautoupdatemod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpringRequestor {
    public static void main(String[] args) {
        try{
            String databaseLink = "http://localhost:80/api/data/param?";
            URL url = new URL(String.format(databaseLink+"param=%s&param2=%s", "Iamparameter", "bye"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();

            System.out.println("Response from Spring Boot: " + response.toString());

            connection.disconnect();


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getURL(String username){
        // decrypt from key, if exist then return, else don't
        return "test";}

}
