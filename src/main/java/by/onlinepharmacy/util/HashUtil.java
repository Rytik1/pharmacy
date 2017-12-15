package by.onlinepharmacy.util;

 import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public final class HashUtil {
    private static Logger logger = LogManager.getLogger(HashUtil.class);

    private static SecureRandom random = new SecureRandom();

    private HashUtil() {
    }


    //  Computes hash value of the current string with md5 algorithm.

    public static String computeHash(String value) {
        String md5Hex = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes());
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            md5Hex = bigInt.toString(16);
            //add zero for the full 32 chars if necessary
            while (md5Hex.length() < 32) {
                md5Hex = "0" + md5Hex;
            }
        } catch (NoSuchAlgorithmException e) {
            //shouldn't happen
            logger.error("Can't create MessageDigest: ", e);
        }
        return md5Hex;
    }

}