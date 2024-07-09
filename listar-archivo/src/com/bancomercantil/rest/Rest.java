package com.bancomercantil.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class Rest {
	public static URL url;
	public static String clientId;
	public static String requestMethod;
	public static String contentType;
	public static HttpURLConnection conn = null;
	public static String executeHttp(String request) {
		BufferedReader br = null;

		String sRespuesta = "";

		try
		{
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("X-IBM-Client-ID", clientId);
			conn.setRequestProperty("Content-Type", contentType);

			OutputStream os = conn.getOutputStream();
			os.write(request.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) 
			{
				System.out.println("Failed : HTTP error code : " + conn.getResponseCode());

				br = new BufferedReader(new InputStreamReader(
						(conn.getErrorStream())));
			}
			else
			{
				br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

			}

			String response;
			while ((response = br.readLine()) != null) 
			{
				sRespuesta = response;
			}


			conn.disconnect();
			
			return sRespuesta;
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {

			e.printStackTrace();
		}
		
		return "";
	}
}
