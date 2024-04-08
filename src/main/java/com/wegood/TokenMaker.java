package com.wegood;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TokenMaker {

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public String signatureMaker(String token) throws NoSuchAlgorithmException, InvalidKeyException{
        String secretKey = "mementoMori";
        String algorithm;
        Mac hmac;
        

        // Crear firma HMAC con SHA-256
        algorithm = "HmacSHA256";
        hmac = Mac.getInstance(algorithm);

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
        hmac.init(secretKeySpec);
        byte[] signatureBytes = hmac.doFinal(token.getBytes());

        return Base64.getEncoder().encodeToString(signatureBytes);
    }
}
