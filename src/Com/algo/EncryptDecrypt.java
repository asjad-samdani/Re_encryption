package Com.algo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Encoder;

public class EncryptDecrypt {

    Cipher ecipher;
    Cipher dcipher;
    public static final String PBE_WITH_MD5_AND_DES = "PBEWithMD5AndDES";
    private static final int INTERACTION = 19;
    public final static String DES = "DES";

    /** Encrytp encoding */
    public final static String UTF8 = "UTF8";

    static byte[] salt = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
   
    int iterationCount = 19;
    public EncryptDecrypt() { 

    }

    public static String encrypt(String secretKey, String plainText) 
            throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException{
    	 
    	KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, INTERACTION);
         SecretKey key = SecretKeyFactory.getInstance(PBE_WITH_MD5_AND_DES)
                   .generateSecret(keySpec);
         Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
         AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
                   INTERACTION);
         ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
         byte[] utf8 = plainText.getBytes(UTF8);
         byte[] enc = ecipher.doFinal(utf8);
         //return new sun.misc.BASE64Encoder().encode(enc);

//        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
//        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
//       
//        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
//
//        
//        ecipher = Cipher.getInstance(key.getAlgorithm());
//        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);      
//        String charSet="ISO-8859-1";       
//        byte[] in = plainText.getBytes(charSet);
//        byte[] out = ecipher.doFinal(in);
        String encStr=new BASE64Encoder().encode(enc);
        return encStr;
    }
    
    public static String decrypt(String secretKey, String encryptedText)
     throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            IOException{
    	 KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt,
                 INTERACTION);

       SecretKey key = SecretKeyFactory.getInstance(PBE_WITH_MD5_AND_DES)
                 .generateSecret(keySpec);
       byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
       Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
       AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
                 INTERACTION);
       dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
       byte[] utf8 = dcipher.doFinal(dec);

//        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
//        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
//        
//        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
//      
//        dcipher=Cipher.getInstance(key.getAlgorithm());
//        dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
//        byte[] enc = new BASE64Decoder().decodeBuffer(encryptedText);
//        byte[] utf8 = dcipher.doFinal(enc);
//        String charSet="ISO-8859-1";     
       String plainStr = new String(utf8, UTF8);
        return plainStr;
    }    
    public static void main(String[] args) throws Exception {
        EncryptDecrypt cryptoUtil=new EncryptDecrypt();
        String key = "FHOEF";   
      
        String plain = "This is msg";
        String enc=cryptoUtil.encrypt(key, plain);
        System.out.println("Original text: "+plain);
        System.out.println("Encrypted text: "+enc);
        String plainAfter=cryptoUtil.decrypt(key, enc);
        System.out.println("Original text after decryption: "+plainAfter);
    }
}