package com.tom.ipautoupdatemod;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Encryptor {
    //Cipher as private variable
    private Cipher cipher;
    public Encryptor() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }
    public byte[] encrypt(String data, PublicKey publicKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return this.cipher.doFinal(data.getBytes());
    }

    public String decrypt(byte[] data, PrivateKey privateKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(this.cipher.doFinal(data));
    }
}
