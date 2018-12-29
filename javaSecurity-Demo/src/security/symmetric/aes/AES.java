package security.symmetric.aes;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * <b>�޸ļ�¼��</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018��12��28��
 * </li>
 * </p>
 * 
 * <b>��˵����</b>
 * <p> 
 * 
 * </p>
 */
public class AES {

	private static String src = "Hello World~";

	public static void main(String[] args) {
		jdkAES();
		jdkAESCBC();
	}

	public static void jdkAES() {
		try {
			//����key(128,192,256) 
			//Illegal key size or default parameters~
			//��Ҫ192,256��ȥ�ٷ���jceL:local_policy.jar,US_export_policy.jar
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			//key��ת��
			Key key = new SecretKeySpec(keyBytes, "AES");

			//����ECB
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk aes encrypt:" + Base64.encodeBase64String(result));

			//����ECB
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println("jdk aes desrypt:" + new String(result));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//������128λ��
	private static String AES_IV = "1234567891234567";

	public static void jdkAESCBC() {
		try {
			//����key(128,192,256) 
			//Illegal key size or default parameters~
			//��Ҫ192,256��ȥ�ٷ���jceL:local_policy.jar,US_export_policy.jar
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();

			//key��ת��
			Key key = new SecretKeySpec(keyBytes, "AES");

			//����
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(AES_IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encryptBytes = cipher.doFinal(src.getBytes());
			System.out.println("jdk aes(CBC) encrypt:" + new String(Base64.encodeBase64(encryptBytes)));

			//����
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] result = cipher.doFinal(encryptBytes);
			System.out.println("jdk aes(CBC) desrypt:" + new String(result));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
