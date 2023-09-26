package com.compressibleflowcalculator.shopping_api.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Invitation")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    public UUID getId() {
        return id;
    }

    @Column(name = "binary_code", columnDefinition = "bytea")
    private byte[] code;

    /*
     * public byte[] getCode() {
     * return this.code;
     * }
     */

    @OneToOne()
    @JoinColumn(name = "groupid", nullable = false)
    Group groupid;

    @Column(name = "expires", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime expires;

    public String getExpires() {
        return expires.toString();
        // return expires;
    }

    @Column(name = "userid")
    private UUID userid;

    public UUID getUserid() {
        return userid;
    }

    public Group getGroup() {
        return this.groupid;
    }

    private static byte[] GenerateHash(byte[] code) {
        // SecureRandom secureRandom = new SecureRandom();
        // byte[] randomData = new byte[32];
        // secureRandom.nextBytes(randomData);
        byte[] randomData = code;

        try {

            // Hash the random data using SHA-256
            byte[] hashedData = hashWithSHA256(randomData);

            // Encode the hashed data in Base64-URL format
            // String base64URL = base64UrlEncode(hashedData);

            return hashedData;
            // return base64URL;
        } catch (Exception e) {

        }
        byte[] array = { 45 };
        return array;

    }

    public Invitation() {

    }

    public Invitation(Group groupid, UUID userid, byte[] code) {
        this.groupid = groupid;
        this.code = GenerateHash(code);
        this.expires = ZonedDateTime.now().plus(1, ChronoUnit.DAYS);
        // this.code = GenerateHash();

    }

    public String GetUrl64EncodeCode() {
        return base64UrlEncode(code);
    }

    public boolean VerifyHash(String base64URL) {
        // Replace with the encoded string to verify
        byte[] originalHashBytes = code;

        // Decode the Base64-URL encoded string
        byte[] decodedData = base64UrlDecode(base64URL);

        try {
            // Hash the decoded data using SHA-256
            byte[] hashedData = hashWithSHA256(decodedData);

            // Compare the new hash to the original one (assuming you have the original
            // hash)
            boolean isMatch = verifyHash(originalHashBytes, hashedData);

            return isMatch;
        } catch (Exception e) {
            return false;
        }

    }

    private static byte[] hashWithSHA256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    }

    public static String base64UrlEncode(byte[] data) {
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(data);
    }

    public static byte[] base64UrlDecode(String base64URL) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        return decoder.decode(base64URL);
    }

    private static boolean verifyHash(byte[] originalHash, byte[] newHash) {
        // Compare the two hash arrays for equality
        return MessageDigest.isEqual(originalHash, newHash);
    }

}
