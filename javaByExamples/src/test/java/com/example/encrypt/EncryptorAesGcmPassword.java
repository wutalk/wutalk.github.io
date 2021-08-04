package com.example.encrypt;

import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.Console;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES Password-based encryption – (The secret key will derive from a given password).
 * 256-bit AES encryption with Galois Counter Mode (GCM).
 */
public class EncryptorAesGcmPassword {
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

    private static final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
    private static final int IV_LENGTH_BYTE = 12;
    private static final int SALT_LENGTH_BYTE = 16;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    // return a base64 encoded AES encrypted text
    public static String encrypt(byte[] pText, String password) throws Exception {

        // 16 bytes salt
        byte[] salt = CryptoUtils.getRandomNonce(SALT_LENGTH_BYTE);

        // GCM recommended 12 bytes iv?
        byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);

        // secret key from password
        SecretKey aesKeyFromPassword = CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        // ASE-GCM needs GCMParameterSpec
        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

        byte[] cipherText = cipher.doFinal(pText);

        // prefix IV and Salt to cipher text
        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
                .put(iv)
                .put(salt)
                .put(cipherText)
                .array();

        // string representation, base64, send this string to other for decryption.
        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);

    }

    // we need the same password, salt and iv to decrypt it
    private static String decrypt(String cText, String password) throws Exception {

        byte[] decode = Base64.getDecoder().decode(cText.getBytes(UTF_8));

        // get back the iv and salt from the cipher text
        ByteBuffer bb = ByteBuffer.wrap(decode);

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);

        byte[] salt = new byte[SALT_LENGTH_BYTE];
        bb.get(salt);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        // get back the aes key from the same password and salt
        SecretKey aesKeyFromPassword = CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

        byte[] plainText = cipher.doFinal(cipherText);

        return new String(plainText, UTF_8);

    }

    public static void main(String[] args) throws Exception {
//        doTest();
        if (args.length != 2) {
            System.out.println("Usage: java XXX [d|e] path2file");
            System.exit(0);
        }
        String op = args[0];
        String fileName = args[1];
        File f = new File(fileName);
        if (!f.exists() || f.isDirectory()) {
            System.out.printf("File %s not exist or is directory\n", fileName);
            System.exit(0);
        }
        String PASSWORD = "this is a password";
        if ("e".equalsIgnoreCase(op)) {
            String ciperText = EncryptorAesGcmPassword.encrypt(FileUtils.readFileToByteArray(f), PASSWORD);
            System.out.println(ciperText);
            FileUtils.write(new File(fileName + ".out"), ciperText, UTF_8);
        } else if ("d".equalsIgnoreCase(op)) {
            String decrypt = EncryptorAesGcmPassword.decrypt(FileUtils.readFileToString(f, UTF_8), getPwd());
            System.out.println(decrypt);
        } else {
            System.out.println("non-supported operation: " + op);
        }
        String base64Str = "";
        System.out.println(new String(Base64.getDecoder().decode(base64Str)));
    }

    private static String getPwd() {
        String pwd = "";
        Console cons;
        if ((cons = System.console()) != null) {
            char[] pass = null;
            try {
                pass = cons.readPassword("Input your Password:");
                pwd = new String(pass);
                System.out.println("Your password was: " + pwd);
            } finally {
                if (pass != null) {
//                    java.util.Arrays.fill(pass, ' ');

                }
            }
        } else {
            throw new RuntimeException("Can't get password...No console");
        }
        return pwd;
    }

    private static void doTest() throws Exception {
        String OUTPUT_FORMAT = "%-30s:%s";
        String PASSWORD = "this is a password";
        String pText = "AES-GSM Password-Bases encryption!高级加密标准";

        String encryptedTextBase64 = EncryptorAesGcmPassword.encrypt(pText.getBytes(UTF_8), PASSWORD);

        System.out.println("\n------ AES GCM Password-based Encryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (base64) ", encryptedTextBase64));

        System.out.println("\n------ AES GCM Password-based Decryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (base64)", encryptedTextBase64));

        String decryptedText = EncryptorAesGcmPassword.decrypt(encryptedTextBase64, PASSWORD);
        System.out.println(String.format(OUTPUT_FORMAT, "Decrypted (plain text)", decryptedText));

        String fn = "C:\\Users\\owen\\Documents\\init-config.md";
        String ciperText = EncryptorAesGcmPassword.encrypt(FileUtils.readFileToByteArray(new File(fn)), PASSWORD);
        System.out.println(ciperText);

        fn = "C:\\Users\\owen\\Documents\\init-config-cipher.txt";
        String decrypt = EncryptorAesGcmPassword.decrypt(FileUtils.readFileToString(new File(fn), UTF_8), PASSWORD);
        System.out.println(decrypt);
    }


}
