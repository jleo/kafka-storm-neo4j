/**
 * 
 */
package com.brandtology.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import com.brandtology.io.log.SystemLogger;

/**
 * @author leah
 *
 */
public class FileHandler {

	/**
	 * 
	 */
	public static String getFileContent(String fileName){
		BufferedReader IN = null;
		//PrintStream ps = null;
		StringBuffer res = new StringBuffer();
		try {
			IN = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			//System.out.println(IN.readLine());
			String line = null;
			while((line=IN.readLine())!=null)
				res.append(line+"\n");
			//System.out.println(res.toString());
		}
		catch (IOException e) {
			//throw new RuntimeException("Problem : " + e.getMessage());
			SystemLogger.printWarning(e.getMessage());
		}
		finally {
			try {
				if (IN != null) IN.close();
			}
			catch (IOException e) {}

		}
		return res.toString();
	}

	/**
	 * 
	 */
	public static void putContentToFile(String fileName, String content){
		File file = new File(fileName);

		int index = fileName.lastIndexOf("/");
		File dir = new File(fileName.substring(0, index));
		if(!dir.exists())
			dir.mkdirs();
		//use buffering
		Writer output = null;
		try {
			//FileWriter always assumes default encoding is OK!
			output = new BufferedWriter(new FileWriter(file));
			output.write( content );
			output.close();
		}catch(IOException e){
			SystemLogger.printWarning(e.getMessage());
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
