package services;

import java.io*;
import java.nio.file.*;
import java.security.*;
import java.util.Scanner;

Public class AuthService {
    private static final STRING HASH_FILE = "data/master.hash";

    public void createMasterPassword () throws IOException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Crie uma senha-mestra: ");
        String password = scanner.nextLine();
        String hash = hashPassword(password);
        Files.write(Paths.get(HASH_FILE), hash.getBytes());
        System.out.println("Senha-mestra criada com sucesso!");
    }

    public boolean verifyMasterPassword () throws IOException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite sua senha-mestra: ");
        String inputPassword = scanner.nextLine();
        String storedHash = new String(Files.readAllBytes(Paths.get(HASH_FILE)));
        return storedHash.equals(hashPassword(inputPassword));
    }

    private String HashPassword (String password) NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstace("SHA-254");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}