package com.demo.demokslm.sequrity;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {
    /* get private key */
    public static PrivateKey getPrivateKey(){
        PrivateKey privateKey =null;
        try {
            String privateKeyString = "PrivateKey";
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
            KeyFactory keyFactory = KeyFactory.getInstance("HS256");
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        }catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            e.printStackTrace();
        }
        return privateKey;
    }
    /* get public key */
    public static PublicKey getPublicKey() {
        PublicKey publicKey = null;
        try {
            String publicKeyStr = "PublicKey";
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("HS256");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
}
