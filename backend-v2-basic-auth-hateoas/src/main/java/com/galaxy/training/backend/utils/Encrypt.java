package com.galaxy.training.backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public static String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    
    public static void main(String[] args) {
        String[] passwords = {"1234", "4321", "password"};
        
        for (String pwd : passwords) {
            System.out.println("Clave: " + pwd);
            System.out.println("Hash: " + encrypt(pwd));
            System.out.println();
        }
    }
}
