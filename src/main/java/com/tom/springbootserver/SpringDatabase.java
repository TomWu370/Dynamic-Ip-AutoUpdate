package com.tom.springbootserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpringDatabase {
    private Connection c = null;
    public SpringDatabase(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/SpringData",
                            "postgres", "123");
            this.c = c;
            String sql = ("CREATE TABLE IF NOT EXISTS SpringTable ("
                    + "username VARCHAR(16) PRIMARY KEY,"
                    + "publicKey BYTEA,"
                    + "encryptedIP BYTEA"
                    + ")");
            PreparedStatement statement = c.prepareStatement(sql);

            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getC(){
        return this.c;
    }

    public void printData(){
        try {
            String sql = "SELECT * FROM SpringTable";
            PreparedStatement statement = this.c.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            if (results.next()){
                String username = results.getString("username");
                String publicK = new String(results.getBytes("publicKey"));
                String ip = new String(results.getBytes("encryptedIP"));
                System.out.println("Username: " + username+ "\nPublic Key: "+ publicK + "\nEncryptedIP: "+ip);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        SpringDatabase database = new SpringDatabase();
        Connection c = database.getC();
        try {
            database.printData();

//            String sql = ("INSERT INTO SpringTable ("
//                    + "username,"
//                    + "publicKey,"
//                    + "encryptedIP)"
//                    + "VALUES (?, ?, ?) ON CONFLICT DO NOTHING");
//            PreparedStatement statement = c.prepareStatement(sql);
//
//            statement.setString(1, "myName");
//            statement.setBytes(2, "mykey".getBytes());
//            statement.setBytes(3, "hypixel.ip".getBytes());
//
//            int rows = statement.executeUpdate();
//            System.out.println("rows affected" + rows);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}