package com.bancomercantil.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.bancomercantil.crypto.AES;
import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;
import com.bancomercantil.models.Mobile;
import com.bancomercantil.msg.ErrorRS;
import com.bancomercantil.msg.FileListRQ;
import com.bancomercantil.msg.FileListRS;
import com.bancomercantil.rest.Rest;
import com.google.gson.Gson;

public class App {
	/*
	 * CREDENCIALES.
	 */
	private static final String URL = "https://apimbu.mercantilbanco.com/mercantil-banco/sandbox/v2/ted/listar-lote";
	private static final String CLIENT_ID = "";
	private static final String MASTERKEY = "";
	private static final String MERCHANT_ID = "";
	
	/*
	 * PARAMETROS DE BÚSQUEDA
	 */
	private static final String FILE_ID = "";
	private static final String FROM_DATE = "";
	private static final String TO_DATE = "";
	
	public static void main(String[] args) {		
		
		try {
			Rest.url = new URL(URL);
			Rest.clientId = CLIENT_ID;
			Rest.contentType = "application/json";
			Rest.requestMethod = "POST";
			
			MerchantIdentify merchantIdentify = new MerchantIdentify();
			ClientIdentify clientIdentify = new ClientIdentify();
			FileListRQ request = new FileListRQ();
			FileListRS response;
			ErrorRS responseError;
			String rawResponse;
			Mobile mobile = new Mobile();
			
			String encryptedFileID = "";
			String encryptedFromDate = "";
			String encryptedToDate = "";
			
			Gson gson = new Gson();
			
			if(!FILE_ID.equals(""))
				encryptedFileID = AES.encrypt(MASTERKEY, FILE_ID);
			
			if(!FROM_DATE.equals(""))
				encryptedFromDate = AES.encrypt(MASTERKEY, FROM_DATE);
			
			if(!TO_DATE.equals(""))
				encryptedToDate = AES.encrypt(MASTERKEY, TO_DATE);
			
			merchantIdentify.setMerchantId(Long.parseLong(MERCHANT_ID));				
			merchantIdentify.setIntegratorId(Long.parseLong("31"));
			merchantIdentify.setTerminalId("abcde");
			
			mobile.setManufacturer("Samsung");
			
			clientIdentify.setIpaddress("127.0.0.1");
			clientIdentify.setBrowserAgent("Chrome 18.1.3");
			clientIdentify.setMobile(mobile);
			
			request.setMerchantIdentify(merchantIdentify);
			request.setClientIdentify(clientIdentify);
			request.setFileId(encryptedFileID);
			request.setFromDate(encryptedFromDate);
			request.setToDate(encryptedToDate);
			request.setClientId(MERCHANT_ID);
			
			System.out.println("====================");
			System.out.println("REQUEST\n");
			
			System.out.println("request: " + request.toString());
			
			System.out.println("\nFile ID: " + FILE_ID);
			System.out.println("File ID encriptado: " + encryptedFileID);
			
			System.out.println("\nFrom Date: " + FROM_DATE);
			System.out.println("From Date encriptado: " + encryptedFromDate);
			
			System.out.println("\nTo Date: " + TO_DATE);
			System.out.println("To Date encriptado: " + encryptedToDate);
			
			System.out.println("\nFIN REQUEST");
			System.out.println("====================");
			
			
			rawResponse = Rest.executeHttp(request.toString());	
			
			System.out.println("\n====================");
			System.out.println("RESPONSE\n");
			
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
				response = gson.fromJson(rawResponse, FileListRS.class);				
				
				System.out.println("response: " + response.toString());
				
				for(int i = 0; i < response.getFileList().size(); i++) {
					System.out.println("\nArchivo " + (i + 1));
					
					System.out.println("\n  File ID: " + response.getFileList().get(i).getFileId());
					System.out.println("  File ID desencriptado: " + AES.decrypt(MASTERKEY, response.getFileList().get(i).getFileId()));	
					
					System.out.println("\n  Status: " + response.getFileList().get(i).getFileStatus());
					System.out.println("  Status desencriptado: " + AES.decrypt(MASTERKEY, response.getFileList().get(i).getFileStatus()));			
					
					System.out.println("\n  Creation Date: " + response.getFileList().get(i).getCreationDate());
					
					System.out.println("\n  Creation Time: " + response.getFileList().get(i).getCreationTime());
					
					System.out.println("\n  Last Modification Date: " + response.getFileList().get(i).getLastModificationDate());
					
					System.out.println("\n  Last Modification Time: " + response.getFileList().get(i).getLastModificationTime());
				}
				
			}
			
			
			System.out.println("\nFIN RESPONSE");
			System.out.println("====================");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
}
