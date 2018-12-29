package security.symmetric.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * <b>修改记录：</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018年12月28日
 * </li>
 * </p>
 * 
 * <b>类说明：</b>
 * <p> 
 * 
 * </p>
 */
public class ThreeDES {
	private static String src = "Hello World~";

	public static void main(String[] args) {
		jdk3DES();
	}

	public static void jdk3DES() {
		try {
			//生成KEY(密钥位数是8的倍数,112 ~ 168之间)
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(112);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			//KEY转换
			DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			Key convertSecretKey = factory.generateSecret(desKeySpec);

			//加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk 3des encrypt:" + Hex.encodeHexString(result));

			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk 3des decrypt:" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
