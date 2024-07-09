package com.bancomercantil.utils;

import java.io.File;

public class Utils {
	public static boolean deleteDirectory(File decompressedFile) {
		boolean success = true;
		
		File[] files = decompressedFile.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			files[i].delete();
		}
		
		success = decompressedFile.delete();
		
		return success;
	}
}
