package com.brandtology.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.brandtology.io.log.SystemLogger;

public class HttpClientManager {

	private CookieStore cookieStore = null;
	private Log log = LogFactory.getLog(HttpClientManager.class);
	 
	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}
	
	public String getResponse(String URL, String charset, HttpHost proxy)throws ClientProtocolException {
		
		List<String> list = getHtmlPageResult(URL, charset, null, proxy);
		
		if(list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	
	public String getResponse(String URL, String charset, Map<String, String> paramMap, HttpHost proxy)throws ClientProtocolException {
		
		List<String> list = getHtmlPageResult(URL, charset, paramMap, proxy);
			
		if(list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	
	
	public String getResponseByGet(String url, String charset, HttpHost proxy){
		String result = null;
		
		SchemeRegistry schemeRegistry = new SchemeRegistry();  
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));  
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));  
		
		ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);  
		cm.setMaxTotal(200);  
		cm.setDefaultMaxPerRoute(5);
		cm.setMaxForRoute(new HttpRoute(proxy), 15);
		
		DefaultHttpClient httpClient = new DefaultHttpClient(cm);
		
		if(cookieStore != null){
			httpClient.setCookieStore(cookieStore);
		}
		
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);
		
		if(charset != null && !"".equals(charset)){
			httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset);
		}
		
		if (proxy != null) {
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		
		HttpResponse response = null; 
		HttpEntity entity = null;
		
		HttpGet httpGet = new HttpGet(url);
		
		try {
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			result = EntityUtils.toString(entity, charset);
			
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return result;
	}
	
	
	private List<String> getHtmlPageResult(String URL, String charset, Map<String, String> paramMap, HttpHost proxy){
		
		long timestart = System.currentTimeMillis();
		//SystemLogger.printInfo("Start HTML Query At | " + new Date());
		SystemLogger.printInfo("HTML Request: " + URL );
		
		List<String> list = new ArrayList<String>();
		String result = "";
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		if(cookieStore != null){
			httpClient.setCookieStore(cookieStore);
		}
		
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);
		
		if(charset != null && !"".equals(charset)){
			httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset);
		}
		
		// 去掉Cookie Rejected警告，重新定义下CookiePolicy
		{
			httpClient.getCookieSpecs().register("customCookiePolicy", new CookieSpecFactory(){
				@Override
				public CookieSpec newInstance(HttpParams arg0) {
					return new BrowserCompatSpec() {
						public void validate(Cookie cookie, CookieOrigin origin)
								throws MalformedCookieException {

						}
					};
				}
			});
			httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "customCookiePolicy");
		}

		if (proxy != null) {
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		
		try {
			HttpGet httpGet = new HttpGet(URL);
			
			if (paramMap != null) {
				Set<String> keySet = paramMap.keySet();
				if (keySet.size() > 0) {
					Iterator<String> it = keySet.iterator();
					while (it.hasNext()) {
						String key = it.next();
						String value = paramMap.get(key);
						httpGet.addHeader(key, value);
					}
				}
			}
			
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				if(charset == null || "".equals(charset)){
					charset = EntityUtils.getContentCharSet(entity) == null ? Constants.UTF8 : EntityUtils.getContentCharSet(entity);
				}
				
				InputStream is = entity.getContent();
				result = StreamOut(is, charset);
				//result = EntityUtils.toString(entity, charset);
				
				list.add(result);
				list.add(charset);
			}
			
		} catch (IOException e) {
			// 第一次请求出错睡眠3秒使用post重新抓取一次
			// System.out.println("第一次请求出错睡眠3秒重新抓取一次");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}

			try {
				HttpPost httpPost = new HttpPost(URL);
				
				if (paramMap != null) {
					Set<String> keySet = paramMap.keySet();
					if (keySet.size() > 0) {
						Iterator<String> it = keySet.iterator();
						while (it.hasNext()) {
							String key = it.next();
							String value = paramMap.get(key);
							httpPost.addHeader(key, value);
						}
					}
				}
				
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				
				if (entity != null) {
					if(charset == null || "".equals(charset)){
						charset = EntityUtils.getContentCharSet(entity) == null ? Constants.UTF8 : EntityUtils.getContentCharSet(entity);
					}
					InputStream is = entity.getContent();
					result = StreamOut(is, charset);
					//result = EntityUtils.toString(entity, charset);
					
					list.add(result);
					list.add(charset);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		//SystemLogger.printInfo("End HTML Query, Return Data Time Used |" + (System.currentTimeMillis() - timestart) + " milliseconds");
		return list;
	}

	public String StreamOut(InputStream stream, String charset) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream,charset));
		String tempbf;
		StringBuffer html = new StringBuffer();
		while ((tempbf = br.readLine()) != null) {
			html.append(tempbf + "\n");
		}
		br.close();
		return html.toString();
	}
	
	
}
