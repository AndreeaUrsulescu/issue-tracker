package internship.issuetracker.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EncryptData {
    private static final Logger LOG = Logger.getLogger(EncryptData.class.getName());

    private EncryptData() {

    }

    public static String sha256(String password) {

        String hashPassword = "";

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));

            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.log(Level.WARNING, "No Such Algorithm Exception", e);
            return "";
        } catch (UnsupportedEncodingException e) {
            LOG.log(Level.WARNING, "Unsupported Encoding Exception", e);
            return "";
        }
        return hashPassword;
    }
}
