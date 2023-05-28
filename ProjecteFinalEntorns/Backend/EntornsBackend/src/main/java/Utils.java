import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	public static String hash(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(string.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		String hashed = String.format("%064x", new BigInteger(1, digest));
		return hashed;
	}
}
