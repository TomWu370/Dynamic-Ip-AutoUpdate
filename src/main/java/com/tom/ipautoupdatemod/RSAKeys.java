package com.tom.ipautoupdatemod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeys {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    private String absolutePath;

    public RSAKeys(String saveLocation) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        this.absolutePath = saveLocation;

        Path priPath = Paths.get(this.absolutePath + "/private.key");
        Path pubPath = Paths.get(this.absolutePath + "/public.key");
        if (Files.exists(priPath) && Files.exists(pubPath)) {

            // Generate private key
            byte[] bytes = Files.readAllBytes(priPath);
            PKCS8EncodedKeySpec pks = new PKCS8EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            this.privateKey = kf.generatePrivate(pks);


            // Generate private key
            bytes = Files.readAllBytes(pubPath);
            X509EncodedKeySpec xks = new X509EncodedKeySpec(bytes);
            this.publicKey = kf.generatePublic(xks);
        } else {
            generateKeys();
        }
    }

    private void generateKeys() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();

        FileOutputStream writer = new FileOutputStream(this.absolutePath + "/private.key");
        writer.write(this.privateKey.getEncoded());
        writer.close();
        writer = new FileOutputStream(this.absolutePath + "/public.key");
        writer.write(this.publicKey.getEncoded());
        writer.close();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}
