package com.fireworks.kundalini.helper;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
 
import org.apache.commons.codec.binary.Base64;
 
public class EncrypterHelper {
 
       private static final String ENCODING = "UTF-8";
       private static final String SECRET_KEY_ALGO = "AES";
       private static final String SECRET_KEY_FACTORY_ALGO = "PBKDF2WithHmacSHA1";
       private int keySize;
       private int iterationCount;
       private Cipher cipher;
       private String salt;
       private String iv;
       private String passphrase;
 
       public EncrypterHelper() {
              //All Keys has to be added, don't push the key details in GIT
              try {
                     cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
              } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                     throw fail(e);
              }
 
       }
 
 
       public String encrypt(String plaintext) {
              try {
                     SecretKey key = generateKey(this.salt, this.passphrase);
                     byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, plaintext.getBytes(ENCODING));
                     return base64(encrypted);
              } catch (UnsupportedEncodingException e) {
                     throw fail(e);
              }
       }
 
       public String decrypt(String ciphertext) {
                     try {
                           SecretKey key = generateKey(this.salt, this.passphrase);
                           byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, base64(ciphertext));
                           return new String(decrypted, ENCODING);
                     } catch (UnsupportedEncodingException e) {
                           throw fail(e);
                     }
       }
 
       private byte[] doFinal(int encryptMode, SecretKey key, byte[] bytes) {
              try {
                     cipher.init(encryptMode, key, new IvParameterSpec(this.iv.getBytes(ENCODING)));
                     return cipher.doFinal(bytes);
              } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
                           | UnsupportedEncodingException e) {
                     throw fail(e);
              }
       }
 
       private SecretKey generateKey(String salt, String passphrase) {
              try {
                     SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGO);
                     KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), salt.getBytes(), iterationCount, keySize);
                     return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), SECRET_KEY_ALGO);
              } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                     throw fail(e);
              }
       }
 
       private String base64(byte[] bytes) {
              return Base64.encodeBase64String(bytes);
       }
 
       private byte[] base64(String str) {
              return Base64.decodeBase64(str);
       }
 
       private IllegalStateException fail(Exception e) {
              return new IllegalStateException(e);
       }
      
 
       public static void main(String[] args) {
              EncrypterHelper helper = new EncrypterHelper();
              String encrypted = helper.encrypt("AlBAl");
              System.out.println("encrypted : " + encrypted);
              System.out.println("decrypted : " + helper.decrypt(encrypted));
             
       }
      
      
}