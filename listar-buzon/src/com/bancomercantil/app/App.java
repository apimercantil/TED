package com.bancomercantil.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.bancomercantil.crypto.AES;
import com.bancomercantil.models.ClientIdentify;
import com.bancomercantil.models.MerchantIdentify;
import com.bancomercantil.models.Mobile;
import com.bancomercantil.msg.ErrorRS;
import com.bancomercantil.msg.InboxListRQ;
import com.bancomercantil.msg.InboxListRS;
import com.bancomercantil.rest.Rest;
import com.google.gson.Gson;

public class App {
	/*
	 * CREDENCIALES
	 */
	private static final String URL = "https://apimbu.mercantilbanco.com/mercantil-banco/sandbox/v2/ted/listar-buzon";
	private static final String MASTERKEY = "";
	private static final String CLIENT_ID = "";
	private static final String MERCHANT_ID = "";
	
	/*
	 * PARAMETROS DE BÚSQUEDA
	 */
	private static final String INBOX_TYPE = "entrada";
	
	public static void main(String[] args) {		
		
		try {
			
			Rest.url = new URL(URL);
			Rest.clientId = CLIENT_ID;
			Rest.contentType = "application/json";
			Rest.requestMethod = "POST";
			
			MerchantIdentify merchantIdentify = new MerchantIdentify();
			ClientIdentify clientIdentify = new ClientIdentify();
			InboxListRQ request = new InboxListRQ();
			InboxListRS response;
			ErrorRS responseError;
			String rawResponse;
			Mobile mobile = new Mobile();
			
			final String ENCRYPTED_INBOX_TYPE = AES.encrypt(MASTERKEY, INBOX_TYPE);
			
			Gson gson = new Gson();
			
			merchantIdentify.setMerchantId(Long.parseLong(MERCHANT_ID));				
			merchantIdentify.setIntegratorId(Long.parseLong("31"));
			merchantIdentify.setTerminalId("abcde");
			
			mobile.setManufacturer("Samsung");
			
			clientIdentify.setIpaddress("127.0.0.1");
			clientIdentify.setBrowserAgent("Chrome 18.1.3");
			clientIdentify.setMobile(mobile);
			
			request.setMerchantIdentify(merchantIdentify);
			request.setClientIdentify(clientIdentify);
			request.setInboxType(ENCRYPTED_INBOX_TYPE);
			request.setClientId(MERCHANT_ID);
			
			System.out.println("====================");
			System.out.println("REQUEST\n");
			
			System.out.println("request: " + request.toString());
			
			System.out.println("\nInbox Type: " + INBOX_TYPE);
			
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
				response = gson.fromJson(rawResponse, InboxListRS.class);				
				
				System.out.println("response: " + response.toString());
				
				for(int i = 0; i < response.getInboxList().size(); i++) {
					System.out.println("\nBuzón " + (i + 1));
					
					System.out.println("  Nombre: " + response.getInboxList().get(i));
				}
				
			}
			
			
			System.out.println("\nFIN RESPONSE");
			System.out.println("====================");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
}
