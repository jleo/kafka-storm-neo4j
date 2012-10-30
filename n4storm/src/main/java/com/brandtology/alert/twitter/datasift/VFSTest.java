/**
 * 
 */
package com.brandtology.alert.twitter.datasift;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;

import com.brandtology.io.log.SystemLogger;
import com.brandtology.util.FormatConstant;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

/**
 * @author leah
 *
 */
public class VFSTest {

	private static final long MINUTE_MILI = 1000*60;

	private static final int PORT = 22;
	private static final String HOST_ADDRESS = "192.168.52.151";
	private static final String USERNAME = "stella";
	private static final String PASSWORD = "Stella!@312";

	private static Session session;
	private static ChannelSftp chan;

	public static void getConnection(){
		Properties props = new Properties();
		props.setProperty("StrictHostKeyChecking", "false");
		JSch jsch = new JSch();

		try{
			session = jsch.getSession(USERNAME, HOST_ADDRESS, PORT);
			session.setUserInfo(new UserInfo()
			{
				public String getPassphrase(){		return null;}

				public String getPassword(){	return null;}

				public boolean promptPassword(String string){	return false;}

				public boolean promptPassphrase(String string){		return false;}

				public boolean promptYesNo(String string){		return true;}

				public void showMessage(String string){		}
			});
			session.setPassword(PASSWORD);
			session.connect();

			chan = (ChannelSftp) session.openChannel("sftp");
			chan.connect();

			SystemLogger.printInfo("Session and Channel connected.");
		}catch(JSchException e){
			SystemLogger.printWarning(e.getMessage());
		}

	}

	/**
	 * 
	 */
	public static List getFileList(String dir){
		List results = new ArrayList();

		try{
			Vector<?> list = chan.ls(dir);
			Iterator<?> iterList = list.iterator();
			while (iterList.hasNext())
			{
				LsEntry fo = (LsEntry)iterList.next();
				String filename = fo.getFilename();


				SystemLogger.printInfo(filename);
			}
		}catch(SftpException e){
			SystemLogger.printWarning(e.getMessage());
		}
		return results;
	}

	/**
	 * 
	 */
	public static String readJSONFiles(String remoteDir, String localDir, String countryCode, long startTime, long endTime){
		StringBuffer res = new StringBuffer("{\"list\":[");
		BufferedReader br = null;

		String suffix = new String("_done.json");

		List timeStrs = getFileNames(startTime, endTime);

		try{
			String dir = new String(remoteDir+"/"+countryCode+"/archive");
			Vector<?> list = chan.ls(dir);
			Iterator<?> iterList = list.iterator();
			int counter = 0;
			while (iterList.hasNext())
			{
				LsEntry fo = (LsEntry)iterList.next();
				String filename = fo.getFilename();
				for(int i=0;i<timeStrs.size();i++){
					String prefix = "datasift_file_"+countryCode+"_"+(String)timeStrs.get(i);
					if(filename.startsWith(prefix) && filename.endsWith(suffix)){
						counter++;
						//SystemLogger.printInfo(filename);

						File path = new File(localDir+"/"+countryCode);
						if(!path.exists())
							path.mkdirs();

						File dst = new File(path+"/"+filename);
						//SystemLogger.printInfo(dst.getPath());

						chan.get(dir+"/"+filename, dst.getPath());

						String currentLine;
						try{
							br = new BufferedReader(new FileReader(dst));
							while ((currentLine = br.readLine()) != null) {
								res.append(currentLine);
							}	
						}catch(IOException e){
							SystemLogger.printWarning(e.getMessage());
						}

						dst.delete();
					}
				}
			}
			SystemLogger.printInfo(counter+" files scanned. ");
		}catch(SftpException e){
			SystemLogger.printWarning(e.getMessage());
		}

		res.append("]}");
		return res.toString();
	}

	/**
	 * 
	 */
	public static void closeConnection(){
		chan.disconnect();
		session.disconnect();
		SystemLogger.printInfo("Session and Channel closed.");
	}

	/**
	 * 
	 */
	public static List getFileNames(long startTime, long endTime){
		List results = new ArrayList();

		for(long time = startTime;time<endTime;time+=5*MINUTE_MILI){
			String timeStr = FormatConstant.DATASIFT_FILE_DF.format(time);
			//SystemLogger.printInfo(timeStr);
			results.add(timeStr);
		}

		return results;
	}

	/**
	 * 
	 */
	public static List getFileList(){
		List results = new ArrayList();

		StaticUserAuthenticator auth = new StaticUserAuthenticator(USERNAME, PASSWORD, null); 
		FileSystemOptions opts = new FileSystemOptions(); 
		try{
			/*SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fsOptions, "no");
			FileSystemManager fsManager = VFS.getManager();
			String uri = "sftp://"+USERNAME+":"+PASSWORD+"@"+HOST_ADDRESS+":"+PORT+"/opt/datasift";
			FileObject fo = fsManager.resolveFile(uri, fsOptions);*/

			DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth); 
			FileObject fo = VFS.getManager().resolveFile("sftp://"+HOST_ADDRESS+":"+PORT+"/opt/datasift", opts); 

			FileObject[] children = fo.getChildren();
			for(int i=0;i<children.length;i++){
				FileObject file = children[i];
				SystemLogger.printInfo(file.getName().toString());
			}
		}catch(FileSystemException e){
			SystemLogger.printWarning(e.getMessage());
		}
		return results;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long startTime = FormatConstant.SIMPLE_DF.parse("2012-06-25 17:00:00").getTime();
		long endTime = FormatConstant.SIMPLE_DF.parse("2012-06-25 18:00:00").getTime();

		getConnection();
		//getFileList("/opt/datasift/SG/archive");
		String json = readJSONFiles("/opt/datasift/SG/archive", System.getProperty("user.dir")+"/datasift", "SG", startTime, endTime);
		SystemLogger.printInfo(json);
		closeConnection();
	}

}
