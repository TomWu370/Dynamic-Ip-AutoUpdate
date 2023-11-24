package com.tom.ipautoupdatemod;

public class Database {
    // write to database by connecting to external link
    final String connectionString;

    public Database(String connectionString) {
        this.connectionString = connectionString;
    }

    public void Update(String newURL){}

    public String getConnectionString(){return "test";}

    public String getURL(){
        // decrypt from key, if exist then return else don't
        return "test";}

    public boolean isWhiteList(String username){
        // if username in database return true else return false
        return true;
    }

}
