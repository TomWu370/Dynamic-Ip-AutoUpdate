package com.tom.ipautoupdatemod;

import java.io.IOException;
import java.security.*;

public class RSAKeys {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private void generateKeys() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}
