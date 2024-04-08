package com.wegood;


import org.json.JSONObject;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TokenBuilder {

    public String build(String username, String role) throws InvalidKeyException, NoSuchAlgorithmException {
        String token;
        String signature;
        String jwt;

        TokenMaker tokenMaker = new TokenMaker();
        JSONObject payload = new JSONObject();
        JSONObject header = new JSONObject();
        

        // Crear el payload (carga útil)
        payload.put("username", username);
        payload.put("role", role);

        // Crear el encabezado (header)
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        String base64Header = Base64.getEncoder().encodeToString(header.toString().getBytes());
        String base64Payload = Base64.getEncoder().encodeToString(payload.toString().getBytes());

        // Generar el token concatenando el header y el payload codificados con un punto entre ellos
        token = base64Header + "." + base64Payload;

        signature = tokenMaker.signatureMaker(token);


        jwt = token + "." + signature;

        return jwt;
    }
}
