package com.bancomercantil.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.bancomercantil.crypto.AES;
import com.bancomercantil.file.FileManager;
import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;
import com.bancomercantil.models.Mobile;
import com.bancomercantil.msg.ErrorRS;
import com.bancomercantil.msg.UploadFileRQ;
import com.bancomercantil.msg.UploadFileRS;
import com.bancomercantil.rest.Rest;
import com.google.gson.Gson;

public class App {
	/*
	 * CREDENCIALES
	 */
	private static final String URL = "https://apimbu.mercantilbanco.com/mercantil-banco/sandbox/v2/ted/cargar-archivo";
	private static final String MASTERKEY = "";
	private static final String CLIENT_ID = "";
	private static final String MERCHANT_ID = "";
	
	/*
	 * NOMBRE DE ARCHIVOS A UTILIZAR
	 */
	private static final String INPUT_FILE_NAME = "paymul.txt";
	private static final String OUTPUT_FILE_NAME= "cipher-file.cif";
	private static final String COMPRESSED_FILE_NAME = "compressed-file.7z";

	/*
	 * PARAMETROS DE LA PETICIÓN
	*/
	private static final String ORDER_TYPE = "paymbu";
	
	public static void main(String[] args) {		
		File inputFile = new File(INPUT_FILE_NAME);
		File outputFile = new File(OUTPUT_FILE_NAME);
		File compressedFile = new File(COMPRESSED_FILE_NAME);
		
		AES.encryptFile(MASTERKEY, inputFile, outputFile);
		FileManager.compress(COMPRESSED_FILE_NAME, outputFile);
		
		try(FileInputStream openedFile = new FileInputStream(compressedFile);) {
			
			Rest.url = new URL(URL);
			Rest.clientId = CLIENT_ID;
			Rest.contentType = "application/json";
			Rest.requestMethod = "POST";
			
			MerchantIdentify merchantIdentify = new MerchantIdentify();
			ClientIdentify clientIdentify = new ClientIdentify();
			UploadFileRQ request = new UploadFileRQ();
			UploadFileRS response;
			ErrorRS responseError;
			String rawResponse;
			Mobile mobile = new Mobile();
			Gson gson = new Gson();
			
			final MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
			
			final String ENCRYPTED_FILE_NAME = AES.encrypt(MASTERKEY, INPUT_FILE_NAME);
			final String ENCRYPTED_ORDER_TYPE = AES.encrypt(MASTERKEY, ORDER_TYPE);
			final String ENCRYPTED_CHECKSUM = AES.encrypt(MASTERKEY, FileManager.getFileChecksum(shaDigest, inputFile));
			
			byte[] fileContent = new byte[(int) outputFile.length()];
			openedFile.read(fileContent);	
			
			final String ENCRYPTED_FILE_CONTENT = Base64.getEncoder().encodeToString(fileContent);
			
			merchantIdentify.setMerchantId(Long.parseLong(MERCHANT_ID));				
			merchantIdentify.setIntegratorId(Long.parseLong("31"));
			merchantIdentify.setTerminalId("abcde");
			
			mobile.setManufacturer("Samsung");
			
			clientIdentify.setIpaddress("127.0.0.1");
			clientIdentify.setBrowserAgent("Chrome 18.1.3");
			clientIdentify.setMobile(mobile);
			
			request.setMerchantIdentify(merchantIdentify);
			request.setClientIdentify(clientIdentify);
			request.setFileContent(ENCRYPTED_FILE_CONTENT);
			request.setFileName(ENCRYPTED_FILE_NAME);
			request.setOrderType(ENCRYPTED_ORDER_TYPE);
			request.setShaChecksum(ENCRYPTED_CHECKSUM);
			request.setClientId(MERCHANT_ID);
			
			rawResponse = Rest.executeHttp(request.toString());
			
			
			if(Rest.conn.getResponseCode() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
				System.out.println("response: " + rawResponse);
			}	
			else if(Rest.conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				responseError = gson.fromJson(rawResponse, ErrorRS.class);
				System.out.println("response: " + responseError.toString());
				for(int i = 0; i < responseError.getErrorList().size(); i++) {
					System.out.println("\nError " + (i + 1) + ":");
					System.out.println("  Error code: " + responseError.getErrorList().get(i).getErrorCode());
					System.out.println("  Description: " + responseError.getErrorList().get(i).getDescription());				
				}
			} 
					
			else {
				response = gson.fromJson(rawResponse, UploadFileRS.class);
				
				System.out.println("response: " + response.toString());
				
				System.out.println("File ID desencriptado: " + AES.decrypt(MASTERKEY, response.getFileId()));
			}
			
			System.out.println("request: " + request.toString());
			
		
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
			
	}
}
