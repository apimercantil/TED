package com.bancomercantil.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.bancomercantil.file.FileManager;

public class AES {
	final static String CIPHER_INSTANCE = "AES/ECB/PKCS5Padding";
	
	private static SecretKeySpec secretkey;
	
	public static String encrypt(String masterkey, String strToEncrypt) {
		try
		{
			setKey(masterkey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(String masterkey, String strToDecrypt) {
		try
		{
			setKey(masterkey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretkey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean encryptFile(String masterkey, File inputFile, File outputFile) {
		boolean success = true;
		String encryptedString = "";
		setKey(masterkey);
		
		try(FileOutputStream openedFile = new FileOutputStream(outputFile);) {
			Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
			cipher.init(1, secretkey);
			
			String inputFileContent = FileManager.readFileContent(inputFile);
			
			encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(inputFileContent.getBytes(StandardCharsets.UTF_8)));
			
			byte[] encryptedBytes = encryptedString.getBytes(StandardCharsets.UTF_8);
			
			
			openedFile.write(encryptedBytes);
		} catch (NoSuchAlgorithmException | 
				NoSuchPaddingException | 
				InvalidKeyException | 
				IllegalBlockSizeException | 
				BadPaddingException | 
				IOException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static boolean decryptFile(String masterkey, File inputFile, File outputFile) {
		boolean success = true;
		String decryptedString = "";
		setKey(masterkey);
		
		try(FileOutputStream openedFile = new FileOutputStream(outputFile);) {
			Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
			cipher.init(2, secretkey);
			
			String inputFileContent = FileManager.readFileContent(inputFile);
			
			decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(inputFileContent)));
			
			byte[] encryptedBytes = decryptedString.getBytes(StandardCharsets.UTF_8);
			
			
			openedFile.write(encryptedBytes);
		} catch (NoSuchAlgorithmException | 
				NoSuchPaddingException | 
				InvalidKeyException | 
				IllegalBlockSizeException | 
				BadPaddingException | 
				IOException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	private static void setKey(String myKey)
	{		
		try {
			byte[] key;
			key = myKey.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretkey = new SecretKeySpec(key, "AES");
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
