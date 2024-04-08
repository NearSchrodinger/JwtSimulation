package com.wegood;

import org.json.JSONObject;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JwtDecoder {

    public void decodeJwt(String jwt) {

        String[] parts = jwt.split("\\.");
        String header = parts[0];
        String payload = parts[1];
        String signature = parts[2];
        String calculatedSignature = "";

        TokenMaker tokenMaker = new TokenMaker();
        byte[] decodedHeader = Base64.getDecoder().decode(header);
        byte[] decodedPayload = Base64.getDecoder().decode(payload);

        String headerJson = new String(decodedHeader);
        String payloadJson = new String(decodedPayload);

        JSONObject headerObj = new JSONObject(headerJson);
        JSONObject payloadObj = new JSONObject(payloadJson);

        System.out.println("Header: " + headerObj.toString(4));
        System.out.println("Payload: " + payloadObj.toString(4));


        try {
            calculatedSignature = tokenMaker.signatureMaker(header + "." + payload);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



        System.out.println("Signature: " + signature);
        System.out.println("calculatedSignature: " + signature);
        if (calculatedSignature.equals(signature)) {
            System.out.println("Signature verified.");
        } else {
            System.out.println("Signature verification failed.");
        }
    }

}
