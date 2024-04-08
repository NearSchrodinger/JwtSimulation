package com.wegood;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class Main {
    public static void main(String[] args){
        String username = "usuario123";
        String password = "contraseña123";
        String role = "USER";
        TokenBuilder builder = new TokenBuilder();
        String jwt = "";

        TokenMaker tokenMaker = new TokenMaker();

        // Hashear la contraseña
        String hashedPassword = tokenMaker.hashPassword(password);
        
        try {
            jwt = builder.build(username, role);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("jwt");
        System.out.println(jwt);

        JwtDecoder decoder = new JwtDecoder();
        decoder.decodeJwt(jwt);
    }
}
