/**
 * 
 */
package com.brandtology.io.log;

/**
 * @ Author: Lim Teck Lee
 * @ Date: 27 March 2009
 */
public class ExceptionHandler {
	public static final int ERROR_SLEEP = 102;
	public static final int ERROR_FILE = 103;
	public static final int ERROR_CLASSNOTFOUND = 105;
	public static final int ERROR_DATABASE = 106;
	public static final int ERROR_DATEBASE_INITIALISE = 110;
	public static final int ERROR_NETWORK = 111;
	public static final int ERROR_WEBSERVICE = 112;
	
	public static void logSevere(int error_code, String description, Exception e) {
		
		String str_exception = null;
		
		switch(error_code) {
		
			case ERROR_SLEEP:{
				str_exception = "Error code:" + error_code + " - Thread Sleep Error\n";
				str_exception += "Description:  " + description + "\n";
				if(e != null) str_exception += "Exception:  " + e.getMessage();
				else str_exception += "Exception:  null";
				break;
			}
			
			case ERROR_FILE:{
				str_exception = "Error code:" + error_code + " - File Error \n";
				str_exception += "Description:  " + description + "\n";
				if(e != null) str_exception += "Exception:  " + e.getMessage();
				else str_exception += "Exception:  null";
				break;
			}
			
			case ERROR_CLASSNOTFOUND:{
				str_exception = "Error code:" + error_code + " - Class Not Found Error \n";
				str_exception += "Description:  " + description  + "\n";
				if(e != null) str_exception += "Exception:  " + e.getMessage();
				else str_exception += "Exception:  null";
				break;
			}
			
			case ERROR_DATABASE:{
				str_exception = "Error code:" + error_code + " - Database Error \n";
				str_exception += "Description:  " + description  + "\n";
				if(e != null) {
					str_exception += "Exception:  " + e.getMessage();
					e.printStackTrace();
				}
				else str_exception += "Exception:  null";
				break;
			}	
			
			case ERROR_DATEBASE_INITIALISE:{
				str_exception = "Error code:" + error_code + " - Database Initialisation Error \n";
				str_exception += "Description:  " + description  + "\n";
				if(e != null) {
					str_exception += "Exception:  " + e.getMessage();
					e.printStackTrace();
				}
				else str_exception += "Exception:  null";
				break;
			}
			
			case ERROR_NETWORK:{
				str_exception = "Error code:" + error_code + " - Network Error \n";
				str_exception += "Description:  " + description  + "\n";
				if(e != null) {
					str_exception += "Exception:  " + e.getMessage();
					e.printStackTrace();
				}
				else str_exception += "Exception:  null";
				break;
			}
			
			case ERROR_WEBSERVICE:{
				str_exception = "Error code:" + error_code + " - Web Service Error \n";
				str_exception += "Description:  " + description  + "\n";
				if(e != null) {
					str_exception += "Exception:  " + e.getMessage();
					e.printStackTrace();
				}
				else str_exception += "Exception:  null";
				break;
			}
		}		
		SystemLogger.printSevere(str_exception);		
	}
}
