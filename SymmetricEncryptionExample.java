// Project : Symmetric Encryption Cryptography
// Author  : Harshal Nikam
// Date    : 20.08.2023

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import java.util.Scanner;

public class SymmetricEncryptionExample {

    public static void main(String[] args) throws Exception {
        
        // Generate a random symmetric key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        // Convert the secret key to a byte array
        byte[] keyBytes = secretKey.getEncoded();

        // Imput of plaintext message from user
        System.out.println("Enter Text for Encryption : ");
        Scanner sc = new Scanner(System.in);
        String plaintext = sc.nextLine();
        System.out.println("You Entered Text is : " + plaintext);

        // Encrypt the message
        byte[] encryptedBytes = encrypt(plaintext, keyBytes);

        // Providing Encrypted Cipher to user
        System.out.println("Encrypted Bytes of your Text is :" + encryptedBytes);

        // Decrypt the message
        System.out.println ("Decryption is going on....");
        String decryptedText = decrypt(encryptedBytes, keyBytes);
        System.out.println("Decrypted Text : " + decryptedText);
    }

    public static byte[] encrypt(String plaintext, byte[] keyBytes) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decrypt(byte[] encryptedBytes, byte[] keyBytes) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
