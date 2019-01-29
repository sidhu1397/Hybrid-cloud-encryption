package com.amazonaws.samples;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class aesencryption {

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		// TODO Auto-generated method stub
		String plaintext="this string is going to be encrypted";
		try {
			KeyGenerator keygenerator=KeyGenerator.getInstance("AES");
			keygenerator.init(128);
			SecretKey secretkey=keygenerator.generateKey();
			String key1=secretkey.toString();
			System.out.println("Secretkey type"+secretkey.getClass());
			byte[] iv=new byte[16];
			SecureRandom random=new SecureRandom();
			random.nextBytes(iv);
			System.out.println("IV"+iv);
			System.out.println(plaintext);
			byte[] ct=encrypt(plaintext.getBytes(),secretkey,iv);
			String ect=Base64.getEncoder().encodeToString(ct);
			System.out.println("Encrypted Text : "+ect );
			String decryptedtext=decrypt(ct,secretkey,iv);
			System.out.println("DecryptedText="+decryptedtext);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public static byte[] encrypt(byte[] pt,SecretKey sk,byte[] iv) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		
		
			Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
		
			SecretKeySpec sks=new SecretKeySpec(sk.getEncoded(),"AES");
			IvParameterSpec ivs=new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE,sks,ivs);
			 byte[] cipherText = cipher.doFinal(pt);
		        
		        
		
		
		return cipherText;
	}
public static String decrypt(byte[] ct,SecretKey sk,byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
{
	Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
	SecretKeySpec sks=new SecretKeySpec(sk.getEncoded(),"AES");
	IvParameterSpec ivs=new IvParameterSpec(iv);
	cipher.init(Cipher.DECRYPT_MODE,sks,ivs);
	byte[] dt=cipher.doFinal(ct);
	return new String(dt);
	
}
}
