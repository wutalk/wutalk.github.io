package com.example.security;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by owu on 2017/8/9.
 */
public class CertPrinter {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
//        String certFile = "D:\\userdata\\owu\\Desktop\\wwwamazoncn.crt";
        String certFile = "D:/tmp/owu_test_pub.crt";
//        String certFile = "D:/tmp/owu_test_pub_signed.crt";
        FileInputStream fin = new FileInputStream(certFile);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(fin);


        System.out.println("Certificate information for: " + certFile);

        System.out.println("Version: V" + cert.getVersion());
        System.out.println("Serial number: " + cert.getSerialNumber());
        System.out.println("Serial number: " + hexString(cert.getSerialNumber().toByteArray()));
        System.out.println("Signature algorithm: " + cert.getSigAlgName());
        System.out.println("Issuer: " + cert.getIssuerDN());
        System.out.println("Valid from: " + format.format(cert.getNotBefore()));
        System.out.println("Valid to: " + format.format(cert.getNotAfter()));
        System.out.println("Subject: " + cert.getSubjectDN());

        RSAPublicKey pk = (RSAPublicKey) cert.getPublicKey();
        System.out.println("Public key algorithm: " + pk.getAlgorithm() + "(" + pk.getModulus().bitLength() + " Bits)" +
                ":");

        byte[] encoded = pk.getEncoded();
        SubjectPublicKeyInfo spkInfo = SubjectPublicKeyInfo.getInstance(encoded);
        System.out.println(hexString(spkInfo.getPublicKey().getDEREncoded()));

        Collection<List<?>> names = cert.getSubjectAlternativeNames();
        if (names != null && !names.isEmpty()) {
            System.out.println("Subject alternative names: ");
            Iterator<List<?>> iterator = names.iterator();
            while (iterator.hasNext()) {
                System.out.println("DNS Name=" + iterator.next().get(1));
            }
        }

        SubjectKeyIdentifier keyid = SubjectKeyIdentifier.getInstance(spkInfo);
        System.out.println("Subject key identifier: " + hexString(keyid.getKeyIdentifier()));

        MessageDigest sha1 = MessageDigest.getInstance("sha1");
        byte[] digest = sha1.digest(cert.getEncoded());
        System.out.println("Certificate fingerprints / Thumbprint sha1: " + hexString(digest));

    }


    private static String hexString(byte[] input) {
        char[] hex = Hex.encodeHex(input);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char ch : hex) {
            sb.append(ch);
            if (++i % 2 == 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
