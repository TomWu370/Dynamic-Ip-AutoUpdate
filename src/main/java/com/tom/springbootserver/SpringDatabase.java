package com.tom.springbootserver;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpringDatabase {
    private Connection c = null;
    enum Columns {
        USERNAME,
        PUBLICKEY,
        ENCRYPTEDIP
    }
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
                String username = results.getString(Columns.USERNAME.ordinal());
                String publicK = new String(results.getBytes(Columns.PUBLICKEY.ordinal()));
                String ip = new String(results.getBytes(Columns.ENCRYPTEDIP.ordinal()));
                System.out.println("Username: " + username+ "\nPublic Key: "+ publicK + "\nEncryptedIP: "+ip);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public byte[] getEncryptedIP(String username){
        try{
            String sql = "SELECT encryptedIP FROM SpringTable WHERE username = ?";
            PreparedStatement statement = this.c.prepareStatement(sql);
            // index starts from 1
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()){

                return result.getBytes(String.valueOf(Columns.ENCRYPTEDIP));
            }
            // check if nothing
        } catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }

    public void updateIP(String username, byte[] newIP){
        try{
            String sql = "UPDATE SpringTable SET encryptedIP = ? WHERE username = ?";
            PreparedStatement statement = this.c.prepareStatement(sql);
            statement.setBytes(1, newIP);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateAllIP(byte[] newIP){
        // when detect primary ip change
        try{
            Cipher encryptor = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            KeyFactory kf = KeyFactory.getInstance("RSA");

            String sql = "SELECT username, publicKey FROM SpringTable";
            PreparedStatement statement = this.c.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            // perform scalability test
            while (results.next()) {
                X509EncodedKeySpec xks = new X509EncodedKeySpec(results.getBytes(Columns.PUBLICKEY.ordinal()));
                PublicKey pKey = kf.generatePublic(xks);
                encryptor.init(Cipher.ENCRYPT_MODE, pKey);
                byte[] newEncryptedIP = encryptor.doFinal(newIP);

                PreparedStatement tempStatement = this.c.prepareStatement("UPDATE SpringTable SET encryptedIP = ?");
                tempStatement.setBytes(1, newEncryptedIP);
                tempStatement.executeUpdate();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String args[]) {
        SpringDatabase database = new SpringDatabase();
        Connection c = database.getC();
        try {
            System.out.println(new String(database.getEncryptedIP("Rikka")));

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