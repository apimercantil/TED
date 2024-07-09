package com.bancomercantil.app;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.bancomercantil.crypto.AES;
import com.bancomercantil.file.FileManager;
import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;
import com.bancomercantil.models.Mobile;
import com.bancomercantil.msg.DownloadFileRQ;
import com.bancomercantil.msg.DownloadFileRS;
import com.bancomercantil.msg.ErrorRS;
import com.bancomercantil.rest.Rest;
import com.google.gson.Gson;

public class App {
	/*
	 * CREDENCIALES
	 */
	private static final String MASTERKEY = "";
	private static final String URL = "https://apimbu.mercantilbanco.com/mercantil-banco/sandbox/v2/ted/descargar-archivo";
	private static final String CLIENT_ID = "";
	private static final String MERCHANT_ID = "";
	
	/*
	 * NOMBRES DE ARCHIVOS A UTILIZAR
	 */
	private static final String OUTPUT_FILE_NAME = "result.txt";
	private static final String CIPHER_OUTPUT_FILE_PATH= "cipher-file";
	private static final String COMPRESSED_FILE_NAME = "compressed-file.7z";

	/*
	 * PARAMETROS DE BÚSQUEDA
	 */
	private static final String ORDER_TYPE = "";
	private static final String FILE_ID = "";
	
	public static void main(String[] args) {		
		
		try {
			
			Rest.url = new URL(URL);
			Rest.clientId = CLIENT_ID;
			Rest.contentType = "application/json";
			Rest.requestMethod = "POST";
			
			MerchantIdentify merchantIdentify = new MerchantIdentify();
			ClientIdentify clientIdentify = new ClientIdentify();
			DownloadFileRQ request = new DownloadFileRQ();
			DownloadFileRS response;
			ErrorRS responseError;
			Mobile mobile = new Mobile();
			Gson gson = new Gson();
			
			File tempFile = new File(CIPHER_OUTPUT_FILE_PATH);
			File outputFile = new File(OUTPUT_FILE_NAME);
			File cipherOutputFile = new File(tempFile + File.separator + FILE_ID + ".cif");

			final String ENCRYPTED_ORDER_TYPE = AES.encrypt(MASTERKEY, ORDER_TYPE);
			final String ENCRYPTED_FILE_ID = AES.encrypt(MASTERKEY, FILE_ID);
			
			String rawResponse;
			
			merchantIdentify.setMerchantId(Long.parseLong(MERCHANT_ID));				
			merchantIdentify.setIntegratorId(Long.parseLong("31"));
			merchantIdentify.setTerminalId("abcde");
			
			mobile.setManufacturer("Samsung");
			
			clientIdentify.setIpaddress("127.0.0.1");
			clientIdentify.setBrowserAgent("Chrome 18.1.3");
			clientIdentify.setMobile(mobile);
			
			request.setMerchantIdentify(merchantIdentify);
			request.setClientIdentify(clientIdentify);
			request.setOrderType(ENCRYPTED_ORDER_TYPE);
			request.setFileId(ENCRYPTED_FILE_ID);
			
			rawResponse = Rest.executeHttp(request.toString());
			
			System.out.println("request: " + request.toString());
			
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
			} else {
				response = gson.fromJson(rawResponse, DownloadFileRS.class);			
				FileManager.writeFileContent(response.getFileContent().getBytes(), COMPRESSED_FILE_NAME);
				FileManager.decompress(COMPRESSED_FILE_NAME, tempFile);				
				AES.decryptFile(MASTERKEY, cipherOutputFile, outputFile);
				System.out.println("response: " + response.toString());
			}			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
}
