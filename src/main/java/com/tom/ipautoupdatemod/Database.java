package com.tom.ipautoupdatemod;
import com.google.gson.Gson;

import java.io.*;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;

// TO BE DELETED
public class Database {
    // write to database by connecting to external link
    final String connectionString;

    public Database(String connectionString) {
        this.connectionString = connectionString; // the url for http requests
    }


    public static void main(String args[]) throws URISyntaxException, IOException, InterruptedException {
        String urlParameters = "asd";
        String url = "https://jsonplaceholder.typicode.com/posts";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        String body = response.body();

        // simple json conversion
        Gson g = new Gson();
        String s = g.toJson(body);
        System.out.println(s);

        // array of json conversion
        JSONArray array = new JSONArray(body);
        for (Object jso: array) {
            System.out.println(jso);

        }


    }

    public void Update(String newURL){}

    public String getURL(){
        // decrypt from key, if exist then return, else don't
        return "test";}

    public boolean isWhiteList(String username){
        // if username in database return true else return false
        return true;
    }

}
