package utils;

/**
 * Ce fichier contient deux m�thodes offrant des services pour la gestion s�curitaire
 * du mot de passe. La strat�gie employ� est celle du salage.
 * 
 * Services publiques:
 * - obtenirSalt, obtenir un salt
 * - hashMotDePasse, h�cher le mot de passe
 * 
 * @author Fred Simard | ETS
 * @version Hiver 2025
 * @ref https://www.baeldung.com/java-password-hashing
 */
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class UtilitairesDB {

	private static final int LONGUEUR_SALT = 16;
	
	protected static SecureRandom random = new SecureRandom();

	/**
	 * m�thode retournant un salt.
	 * @return salt dans un tableau de byte
	 */
	public static byte[] obtenirSalt() {
		byte[] salt = new byte[LONGUEUR_SALT];
		random.nextBytes(salt);
		return salt;
	}

	/**
	 * m�thode qui hash le mot de passe et le salt
	 * @param motDePasse mot de passe a sauvegarder
	 * @param salt salt a utiliser
	 * @return hash du mot de passe sous forme d'un tableau de byte
	 */
	public static byte[] hashMotDePasse(String motDePasse, byte[] salt) {
		KeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			return null; 
		}catch (InvalidKeySpecException e) {
			return null;
		}
	}

}
