package Com.algo;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCoder {

		public static byte[] getRawKey(byte[] seed) throws Exception {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(seed);
			kgen.init(128, sr); // 192 and 256 bits may not be available
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();
			return raw;
		}

		public static byte[] encrypt(byte[] seed, byte[] plaintext)
				throws Exception {
			byte[] raw = getRawKey(seed);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return encrypted;
		}

		public static byte[] decrypt(byte[] seed, byte[] ciphertext)
				throws Exception {
			byte[] raw = getRawKey(seed);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(ciphertext);

			return decrypted;
		}

	        public static void main(String args[]) throws Exception
	        {
//	            String plainD= "sunsoft";
//	            byte [] plain= plainD.getBytes();
//	            System.out.println("Original Data is ===>"+plainD);
//	           
//	            byte [] ar= "abcdefghijklmnopqrstuvwxyz".getBytes();
//	             
//	              byte [] key= AESCoder.getRawKey(ar);
//	              System.out.println("key is ===>"+new String(key));   // Outputs "SGVsbG8="
//	              
//	              byte [] cip= AESCoder.encrypt(plain,plain);
//	               System.out.println("Cipher data is ===>"+new String(cip)); 
//	                
//	          byte [] pl= AESCoder.decrypt(plain,cip);
//	               System.out.println("Recovered Data is ===>"+new String(pl)); 
	        	
	                long milliseconds = 1000000;

	                // long minutes = (milliseconds / 1000) / 60;
	                long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);

	                // long seconds = (milliseconds / 1000);
	                long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);

	                System.out.format("%d Milliseconds = %d minutes\n", milliseconds, minutes );
	                System.out.println("Or");
	                System.out.format("%d Milliseconds = %d seconds", milliseconds, seconds );

	                
	                
	                long millisecondsNew = 1000000;

	                long minutesNew = (millisecondsNew / 1000) / 60;
	                long secondsNew = (millisecondsNew / 1000) % 60;

	                System.out.format("%d Milliseconds = %d minutes and %d seconds.", milliseconds, minutes, seconds);

	            }
	        
}
