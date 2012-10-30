/**
 * 
 */
package com.brandtology.test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.brandtology.util.FileHandler;

/**
 * @author leah
 *
 */
public class JSONParseTest {
	
	/**
	 * 
	 */
	public static List parse(String value) throws JSONException{
		List results = new ArrayList();
		JSONObject json = new JSONObject(value);
        Enumeration indexes = json.keys();
        while(indexes.hasMoreElements()){
        	String element = (String)indexes.nextElement();
        	System.out.println(element);
        	
        	/*JSONObject index = new JSONObject(element);
        	System.out.println(index.toString());*/
        }
		return results;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String fileName = System.getProperty("user.dir")+"/data/list_indexes_response.json";
		String value = FileHandler.getFileContent(fileName);
		parse(value);
	}

}
