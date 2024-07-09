package com.bancomercantil.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Base64;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

public class FileManager {
	/*
	 * Purpose: Leer los bytes del archivo envíado por parametros. 
	 */
	public static String readFileContent(File file) {
		byte[] bytesArray = new byte[(int) file.length()]; 
		
		try (FileInputStream openedFile = new FileInputStream(file)){
			openedFile.read(bytesArray);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return new String(bytesArray);
	}
	
	public static boolean writeFileContent(byte[] fileContent, String compressedFileName) {
		
		byte[] bytesArray = Base64.getDecoder().decode(fileContent);
		
		File compressedFile = new File(compressedFileName);
		
		if (compressedFile.exists())
		{
			compressedFile.delete();
		}
		
		try (FileOutputStream openedFile = new FileOutputStream(compressedFile)){
			openedFile.write(bytesArray);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return true;
	}
	
	public static boolean compress(String fileName, File fileToCompress) {
		boolean success = true;
		try(SevenZOutputFile compressedFile = new SevenZOutputFile(new File(fileName)); 
				FileInputStream openedFile = new FileInputStream(fileToCompress)){
			
			SevenZArchiveEntry entry = compressedFile.createArchiveEntry(fileToCompress, fileToCompress.getName());
			
			compressedFile.putArchiveEntry(entry);
			
			byte[] bytes = new byte[1024];
            int count = 0;
            
            while ((count = openedFile.read(bytes)) > 0) {
            	compressedFile.write(bytes, 0, count);
            }
            
            compressedFile.closeArchiveEntry();
            
            compressedFile.finish();
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	public static boolean decompress(String fileName, File fileDestination) {
		boolean success = true;
		
		try(SevenZFile compressedFile = new SevenZFile(new File(fileName));) {
			SevenZArchiveEntry entry = null;
			
			while ((entry = compressedFile.getNextEntry()) != null)
	        {
	            if (entry.isDirectory()){
	                continue;
	            }
	            
	            File curfile = new File(fileDestination, entry.getName());
	            
	            File parent = curfile.getParentFile();
	            
	            if (!parent.exists()) {
	                parent.mkdirs();
	            }
	            
	            FileOutputStream out = new FileOutputStream(curfile);
	            byte[] content = new byte[(int) entry.getSize()];
	            compressedFile.read(content, 0, content.length);
	            out.write(content);
	            out.close();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
		FileInputStream openedFile = new FileInputStream(file);

		byte[] byteArray = new byte[1024];
		int bytesCount = 0; 

		while ((bytesCount = openedFile.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}

		openedFile.close();

		byte[] bytes = digest.digest();

		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		//return complete hash
		return sb.toString();
	}
}
