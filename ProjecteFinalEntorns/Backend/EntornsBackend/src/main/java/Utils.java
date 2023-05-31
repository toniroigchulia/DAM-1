import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Classe donde irian todos los metods de utilidades que podamos usar en el proyecto
public class Utils {
	
	// Classe para hashear la contraseña
	public static String hash(String string) {
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		md.update(string.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		String hashed = String.format("%064x", new BigInteger(1, digest));
		return hashed;
	}
}
