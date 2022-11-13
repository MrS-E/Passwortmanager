import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class FileEncrypterDecrypter {

public FileEncrypterDecrypter() {

}
	
public static SecretKey gernerateKey(String encryptionType) {
	try {
		KeyGenerator keygen = KeyGenerator.getInstance(encryptionType);
		SecretKey myKey = keygen.generateKey();
		return myKey;
	}
	catch(Exception e)
	{
		return null;
	}
}

public static byte[] encryption(byte[] dataToEncrypt, SecretKey myKey, Cipher cipher) {
	try {
		cipher.init(Cipher.ENCRYPT_MODE, myKey);
		byte[] textEncrypted = cipher.doFinal(dataToEncrypt);
		return textEncrypted;
	}
	catch(Exception e){
		return null;
	}
	
}

public static byte[] decryption(byte[] dataToDecrypt, SecretKey myKey, Cipher cipher) {
	try {
		cipher.init(Cipher.DECRYPT_MODE, myKey);
		byte[] textDecrypted = cipher.doFinal(dataToDecrypt);
		return textDecrypted;
	}
	catch(Exception e){
		System.out.println(e);
		return null;
	}
	
}

}
