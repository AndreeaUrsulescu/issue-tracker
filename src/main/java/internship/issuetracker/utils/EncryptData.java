package internship.issuetracker.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptData {
	public static String sha256(String password){
		String hashPassword="";
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			 
		    byte byteData[] = md.digest();
		    StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        hashPassword=sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return hashPassword;
	}
}
