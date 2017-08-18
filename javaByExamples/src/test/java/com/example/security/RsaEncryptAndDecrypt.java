package com.example.security;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * keytool -genkeypair -dname "cn=Owen Wu, ou=nbi, o=AA, c=CN" -alias owu_test -keyalg RSA -keypass changeit
 * -keystore D:/tmp/owu_test.jks -storepass changeit -validity 180
 * <p>
 * D:/tmp/owu_test.jks
 * D:/tmp/owu_test_pub.crt
 * <p>
 * keytool -list -keystore D:/tmp/owu_test.jks -storepass changeit
 * keytool -exportcert -rfc -alias "owu_test" -file D:/tmp/owu_test_pub.crt -keystore "D:/tmp/owu_test.jks"
 * -storepass changeit
 * keytool -printcert -file D:/tmp/owu_test_pub.crt
 * keytool -certreq -alias owu_test -file D:/tmp/owu_test.csr -keystore D:/tmp/owu_test.jks -storepass changeit
 * <p>
 * Created by owu on 2017/8/15.
 */
public class RsaEncryptAndDecrypt {

    public static final String UTF_8 = "utf-8";
    public static final String SIG_ALGORITHM = "SHA256withRSA";

    public static void main(String[] args) throws Exception {

        // cryptography
        String plainText = "hello world";
        System.out.println("plainText: " + plainText);

        String cipherText = encrypt(plainText);
        System.out.println("cipherText: " + cipherText);

        String decryptText = decrypt(cipherText);
        System.out.println("decryptText: " + decryptText);

        // digital signature
        String signature = sign("foobar", getPrivateKey());
        System.out.println("signature: " + signature);

        //Let's check the signature
        boolean isCorrect = verify("foobar", signature, getPublicKey());
        System.out.println("Signature correct: " + isCorrect);
    }

    private static String decrypt(String cipherText) throws Exception {
        PrivateKey privateKey = getPrivateKey();

        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decryptCipher.doFinal(bytes), UTF_8);
    }

    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance(SIG_ALGORITHM);
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance(SIG_ALGORITHM);
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

    private static PrivateKey getPrivateKey() throws Exception {
        // Private Keystore
        FileInputStream privateKeyFile = new FileInputStream("D:/tmp/owu_test.jks");

        // Read the private keystore and get Private Key
        KeyStore privateKeyStore = KeyStore.getInstance("JKS");
        // System.out.println("Type of private key: " + privateKeyStore.getDefaultType());
        String keystorePass = "changeit";
        privateKeyStore.load(privateKeyFile, keystorePass.toCharArray());
        KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(keystorePass.toCharArray());
        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) privateKeyStore.getEntry("owu_test", protParam);
        return pkEntry.getPrivateKey();
    }

    private static String encrypt(String plainText) throws Exception {
        RSAPublicKey pk = getPublicKey();

        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, pk);
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    private static RSAPublicKey getPublicKey() throws Exception {
        String certFile = "D:/tmp/owu_test_pub.crt";
        FileInputStream fin = new FileInputStream(certFile);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(fin);
        return (RSAPublicKey) cert.getPublicKey();
    }
}
