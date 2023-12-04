package com.tom.ipautoupdatemod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpringRequestor {
    public static void main(String[] args) {
        try{
            URL url = new URL("http://localhost:80/api/data/param?param=Iamparameter&param2=bye");
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
}
